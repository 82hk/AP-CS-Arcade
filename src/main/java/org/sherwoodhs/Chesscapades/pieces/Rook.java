package org.sherwoodhs.Chesscapades.pieces;

import org.sherwoodhs.Chesscapades.Game.Board;
import org.sherwoodhs.Chesscapades.Game.Tile;

import javax.swing.*;

public class Rook extends Piece {

    private boolean canCastle;
    public Rook(int color) {
        super(color);
        canCastle = true;
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            return(new ImageIcon("src/resources/bRook.png"));
        } else if(color == 1) {
            return(new ImageIcon("src/resources/wRook.png"));
        } else {
            return null;
        }
    }
    @Override
    public boolean isLegalMove(int x, int y, int newX, int newY, Board board, boolean forReal){
        int yoffset = newY - y;
        int xoffset = newX - x;
        Tile destination = board.getTile(Board.getLocationFromCords(newX, newY));
        if(destination.isOccupied())
        {
            if(destination.getPiece().getColor() == getColor())
            {
                return false;
            }
        }
        if (yoffset == 0){
            for (int i = 1; i < Math.abs(xoffset); i++){
                if (board.getTile(Board.getLocationFromCords((int)(x + (i * Math.signum(xoffset))), y)).getPiece() != null){
                    return false;
                }
            }
            return true;
        }else if (xoffset == 0){
            for (int i = 1; i < Math.abs(yoffset); i++){
                if (board.getTile(Board.getLocationFromCords(x, (int)(y + (i * Math.signum(yoffset))))).getPiece() != null){
                    return false;
                }
            }
            return true;
        }else{
            return false;
        }
    }

    public boolean canCastle()
    {
        return canCastle;
    }

    public void setCastleable(boolean b)
    {
        canCastle = b;
    }
}
