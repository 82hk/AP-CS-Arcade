package org.sherwoodhs.Checkers;

import org.sherwoodhs.Game;

import javax.swing.*;

public class CheckersGame {

    private JFrame frame;
    private final int frameSize = 800;

    public CheckersBoard board;

    public CheckersGame() {
        frame = new JFrame("Checkers");
        frame.setSize(frameSize, frameSize);
        frame.setVisible(true);
        board = new CheckersBoard(frameSize);
        frame.add(board);
    }
}
