package org.sherwoodhs.Wordle;

import org.sherwoodhs.Game;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class WordleGame implements Game {
    public static void main(String[] args) {
    }

    public static void runGUI() {
        JFrame.setDefaultLookAndFeelDecorated(false);
        new Wordle();
    }

    @Override
    public void start() {
        javax.swing.SwingUtilities.invokeLater(WordleGame::runGUI);
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
        return "<html>Guess the 6 letter word in 6 tries.<br><br>By: Tyler Nguyen, Landon Malan, & Cyrus Ho</html>";
    }

    @Override
    public BufferedImage getThumbnail() {
        final String path = "/wordle.png";
        return checkThumbnail(path);
    }
}