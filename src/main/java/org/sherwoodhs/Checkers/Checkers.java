package org.sherwoodhs.Checkers;

import org.sherwoodhs.Game;

import java.awt.image.BufferedImage;

public class Checkers implements Game {

    public static void main(String args[]) {}

    @Override
    public void start() {
        new CheckersGame();
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
        return "Java implementation of Checkers by Trenton";
    }

    @Override
    public BufferedImage getThumbnail() {
        final String path = "/checkers.png";
        return checkThumbnail(path);
    }
}
