package org.sherwoodhs.Checkers;

import org.sherwoodhs.Game;

import javax.swing.*;

public class CheckersGame implements Game {

    private JFrame frame;
    private final int frameSize = 800;

    public CheckersBoard board;

    public void initializeGame() {
        frame = new JFrame("Checkers");
        frame.setSize(frameSize, frameSize);
        frame.setVisible(true);
        board = new CheckersBoard(frameSize);
        frame.add(board);
    }

    @Override
    public void start() {
        initializeGame();
        new CheckersGame();
    }

    @Override
    public void stop() {}

    @Override
    public String getName() {
        return "Checkers";
    }

    @Override
    public String getDescription() {
        return "Checkers, also known as draughts, is a group of strategy board games for two players which involve diagonal moves of uniform game pieces and mandatory captures by jumping over opponent pieces." +
                "\n\nBy: Trenton";
    }
}
