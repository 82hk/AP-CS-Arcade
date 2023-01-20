package org.sherwoodhs.TicTacToe;

import org.sherwoodhs.Game;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Main implements Game {

    @Override
    public void start() {
        new TicTacToe();
    }

    @Override
    public void stop() {

    }

    @Override
    public String getName() {
        return "Tic Tac Toe";
    }

    @Override
    public String getDescription() {
        return "Enter your description here.";
    }

    @Override
    public BufferedImage getThumbnail() {
        final String path = "/tictactoe.png";
        return checkThumbnail(path);
    }
}
