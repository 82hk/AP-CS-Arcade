package org.sherwoodhs.TicTacToe;

import org.sherwoodhs.Game;

import java.awt.image.BufferedImage;

public class TicTacToeGame implements Game {

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
        return "The original Tic Tac Toe, now in Java. \n\nBy: Chris Portinga";
    }

    @Override
    public BufferedImage getThumbnail() {
        final String path = "/tictactoe.png";
        return checkThumbnail(path);
    }
}
