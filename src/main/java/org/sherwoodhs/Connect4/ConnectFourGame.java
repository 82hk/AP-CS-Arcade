package org.sherwoodhs.Connect4;

import org.sherwoodhs.Game;

import java.awt.image.BufferedImage;

public class ConnectFourGame implements Game {
    public static void main(String[] args) {
    }

    @Override
    public void start() {
    }

    @Override
    public void stop() {

    }

    @Override
    public String getName() {
        return "Connect 4";
    }

    @Override
    public String getDescription() {
        return "Enter your description here.";
    }

    @Override
    public BufferedImage getThumbnail() {
        final String path = "/connect4.png";
        return checkThumbnail(path);
    }
}
