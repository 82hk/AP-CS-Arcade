package org.sherwoodhs;

import org.sherwoodhs.Chesscapades.Chess;

import java.awt.*;

import static org.sherwoodhs.Menu.MENU;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            MENU.initialize();
        });
        Game chess = new Chess();
        chess.startGame();
    }

}