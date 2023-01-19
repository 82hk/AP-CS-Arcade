package org.sherwoodhs.Chesscapades.pieces;

import org.sherwoodhs.Chesscapades.Game.Board;
import org.sherwoodhs.Chesscapades.Game.ChessGame;
import org.sherwoodhs.Chesscapades.Game.Tile;

import javax.swing.*;

public class Pawn extends Piece {
    public Pawn(int color) {
        super(color);
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            return(new ImageIcon("src/resources/bPawn.png"));
        } else if(color == 1) {
            return(new ImageIcon("src/resources/wPawn.png"));
        } else {
            return null;
        }
    }

    @Override
    public boolean isLegalMove(int x, int y, int newX, int newY, Board board, boolean forReal) {
        Tile destination = board.getTile(board.getLocationFromCords(newX, newY));
        int dy = newY - y;
        if (newX != x)
        {
            int dx = Math.abs(newX - x);
            if (dx == 1)
            {
                if(destination.isOccupied() && dy == getForwardDirection())
                {
                    if (destination.getPiece().getColor() != getColor())
                    {
                        return true;
                    }
                } else if ((board.getTile(Board.getLocationFromCords(newX, y)).isOccupied() && (dy == getForwardDirection()))){
                     if (board.getTile(Board.getLocationFromCords(newX, y)).getPiece().moved2 != 0){
                        return true;
                    }
                    return false;
                }

            }
            return false;
        }
        if(destination.isOccupied())
        {
            return false;
        }
        if(dy == getForwardDirection() || ((isOnStartingSquare(y) && dy == getForwardDirection() * 2)) && !board.getTile(Board.getLocationFromCords(newX, newY - getForwardDirection())).isOccupied())
        {
            if (forReal) {
                moved2 = 2;
            }
            return true;
        }
        return false;
    }

    private int getForwardDirection()
    {
        if(getColor() == 0)
        {
            return 1;
        }
        else
        {
            return -1;
        }
    }

    private boolean isOnStartingSquare(int y)
    {
        if(getColor() == 0)
        {
            return y == 1;
        }
        else
        {
            return y == 6;
        }
    }
}
