package org.sherwoodhs;

import java.awt.*;
import java.security.*;
import java.net.*;
import java.util.zip.*;
import java.io.*;

import static org.sherwoodhs.Menu.MENU;

public class Main {
    public static void main(String[] args) {

        try {

            String currentPath = new java.io.File(".").getCanonicalPath();
            System.out.println("Current dir:" + currentPath);

            String currentDir = System.getProperty("user.dir");
            System.out.println("Current dir using System:" + currentDir);

            File dir = new File("src/main/jar");
            File[] filesList = dir.listFiles();
            for (File file : filesList) {
                if (file.isFile()) {
                    System.out.println(file.getName());
                }
            }

            String fileName = "src/main/jar/public.jar";
            File file = new File(fileName);

            CodeSource src  = Main.class.getProtectionDomain().getCodeSource();

            if (src != null) {
                URL jar = file.toURI().toURL();
                //  URL jar = src.getLocation();
                ZipInputStream zip = new ZipInputStream(jar.openStream());

                while (true) {
                    ZipEntry e = zip.getNextEntry();
                    if (e == null)
                        break;
                    String name = e.getName();
                    System.out.println("JAR file contains entry: " + name);

                    if (name.startsWith("Public")) {
                        System.out.println("This uses the Public directory...");
                    }

                }
            } else {
                System.out.println("FAIL");
            }

            System.out.println("Done!");

        } catch (Exception e) {
            e.printStackTrace();
        }

        EventQueue.invokeLater(() -> {
            MENU.initialize();
        });
    }

}