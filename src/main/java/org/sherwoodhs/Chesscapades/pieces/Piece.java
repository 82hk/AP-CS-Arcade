package org.sherwoodhs.Chesscapades.pieces;

import javax.swing.*;

import org.sherwoodhs.Chesscapades.Game.Board;
import org.sherwoodhs.Chesscapades.Game.Tile;

public abstract class Piece {
    public int moved2;
    public final int color;

    public Piece(int color) {
        this.color = color;
        this.moved2 = 0;
    }

    public ImageIcon getImageIcon() {
        return null;
    }

    public int getColor() {
        return color;
    }

    public boolean isMovable() {
        return true;
    }

    public boolean canJump() {
        return false;
    }

    public abstract boolean isLegalMove(int x, int y, int newX, int newY, Board board, boolean forReal);

}
