package org.sherwoodhs.WordleGame.src.com.shs.Wordlegame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                runGUI();
            }
        });
    }

    public static void runGUI() {
        JFrame.setDefaultLookAndFeelDecorated(false);
        WordleGame game = new WordleGame();
    }
}