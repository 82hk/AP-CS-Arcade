package org.sherwoodhs.Chesscapades.pieces;

import org.sherwoodhs.Chesscapades.Game.Board;

import javax.swing.*;


public abstract class Piece {
    public final int color;
    public Piece(int color) {
        this.color = color;
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

    public boolean isLegalMove(int x, int y, int newX, int newY, Board board) {return true;}

    private static boolean isLegalBoardState(Board board, int turn)
    {
        return true;
    }
}
