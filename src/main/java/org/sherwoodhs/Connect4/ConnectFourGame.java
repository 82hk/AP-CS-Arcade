package org.sherwoodhs.Connect4;

import org.sherwoodhs.Game;

import java.awt.image.BufferedImage;

public class ConnectFourGame implements Game {
    public static void main(String[] args) {
    }

    @Override
    public void start() {
        new ConnectFour();
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
        return convertToMultiline("A Java implementation of Connect 4. \n\n By: Ken Hardesty");
    }

    public static String convertToMultiline(String orig)
    {
        return "<html>" + orig.replaceAll("\n", "<br>");
    }

    @Override
    public BufferedImage getThumbnail() {
        final String path = "/connect4.png";
        return checkThumbnail(path);
    }
}
