package org.sherwoodhs.Chesscapades;

import org.sherwoodhs.Chesscapades.Game.ChessGame;
import org.sherwoodhs.Game;

import javax.swing.*;
import java.awt.*;

public class Chess extends Game {
    public static void main(String args[]) {

    }

    @Override
    protected void initializeGame() {

    }

    @Override
    protected void startGame() {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int)size.getHeight();

        JFrame frame = new ChessGame(height-200);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/java/org/sherwoodhs/Chesscapades/resources/wKnight.png"));
        frame.pack();
        frame.setTitle("Chesscapades");
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    protected void endGame() {

    }

    @Override
    protected String getName() {
        return "Chesscapades";
    }

    @Override
    protected String getDescription() {
        return "A chess implementation";
    }
}
