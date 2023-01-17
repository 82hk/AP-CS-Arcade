package com.shs.Wordlegame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(Main::runGUI);
    }

    public static void runGUI() {
        JFrame.setDefaultLookAndFeelDecorated(false);
        new WordleGame();
    }
}