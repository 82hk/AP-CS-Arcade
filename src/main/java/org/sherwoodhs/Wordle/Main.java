package org.sherwoodhs.Wordle;

import org.sherwoodhs.Game;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class Main implements Game {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(Main::runGUI);
    }

    public static void runGUI() {
        JFrame.setDefaultLookAndFeelDecorated(false);
        new WordleGame();
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public String getName() {
        return "Wordle";
    }

    @Override
    public String getDescription() {
        return "Enter your description here.";
    }

    @Override
    public BufferedImage getThumbnail() {
        final String path = "/wordle.png";
        return checkThumbnail(path);
    }
}