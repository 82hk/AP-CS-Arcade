package org.sherwoodhs.Checkers;

import org.sherwoodhs.Game;

import javax.swing.*;

public class CheckersGame extends Game {

    private JFrame frame;
    private final int frameSize = 800;

    public CheckersBoard board;

    public boolean isOver() {
        if (board.tiles.isEmpty())
            return true;
        return false;
    }

    public void initializeGame() {
        frame = new JFrame("Checkers");
        frame.setSize(frameSize, frameSize);
        frame.setVisible(true);
        board = new CheckersBoard(frameSize);
        frame.add(board);
    }

    public void startGame() {
        CheckersGame game = new CheckersGame();
    }

    public void endGame() {}

    protected String getName() {
        return "Checkers";
    }

    protected String getDescription() {
        return "Checkers, also known as draughts, is a strategy board game for two players which involve diagonal moves of uniform game pieces and mandatory captures by jumping over opponent pieces.";
    }
}
