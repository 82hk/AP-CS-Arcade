package org.sherwoodhs.Chesscapades;

import org.sherwoodhs.Chesscapades.Game.ChessGame;

import javax.swing.*;

public class Main {
    public static void main(String args[]) {JFrame frame = new ChessGame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setTitle("Chesscapades");
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
