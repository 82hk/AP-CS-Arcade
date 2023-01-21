package org.sherwoodhs.Chesscapades;

import org.sherwoodhs.Chesscapades.Game.ChessGame;
import org.sherwoodhs.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Main implements Game {
    public static void main(String args[]) {

    }

    @Override
    public void start() {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int)size.getHeight();

        JFrame frame = new ChessGame(height-200);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/java/org/sherwoodhs/Chesscapades/resources/wKnight.png"));
        frame.pack();
        frame.setTitle("Chesscapades");
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void stop() {

    }

    @Override
    public BufferedImage getThumbnail() {
        final String path = "/Chesscapades.png";
        return checkThumbnail(path);
    }

    @Override
    public String getName() {
        return "Chesscapades";
    }

    @Override
    public String getDescription() {
        return "A chess implementation.";
    }
}
