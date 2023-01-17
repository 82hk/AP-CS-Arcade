package pieces;

import Game.Board;
import Game.Tile;

import javax.swing.*;

public class Bishop extends Piece {
    public Bishop(int color) {
        super(color);
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            return(new ImageIcon("src/resources/bBishop.png"));
        } else if(color == 1) {
            return(new ImageIcon("src/resources/wBishop.png"));
        } else {
            return null;
        }
    }
    @Override
    public boolean isLegalMove(int x, int y, int newX, int newY, Board board, boolean forReal){
        int yoffset = newY - y;
        int xoffset = newX - x;
        if (Math.abs(xoffset) == Math.abs(yoffset)){
            for (int i = 1; i < Math.abs(yoffset); i++){
                if (board.getTile(Board.getLocationFromCords((int)(x + (i * Math.signum(xoffset))), (int)(y + (i * Math.signum(yoffset))))).getPiece() != null){
                    return false;
                }
            }
            Tile destination = board.getTile(Board.getLocationFromCords(newX, newY));
            if(destination.isOccupied())
            {
                if(destination.getPiece().getColor() == getColor())
                {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
