package org.sherwoodhs.Checkers;

import org.sherwoodhs.Game;

import java.awt.image.BufferedImage;

public class Main implements Game {

    public static void main(String args[]) {
        CheckersGame game = new CheckersGame();
        game.start();
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public String getName() {
        return "Checkers";
    }

    @Override
    public String getDescription() {
        return "Enter your description here.";
    }

    @Override
    public BufferedImage getThumbnail() {
        final String path = "/checkers.png";
        return checkThumbnail(path);
    }
}
