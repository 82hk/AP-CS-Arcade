package pieces;

import Game.Board;
import Game.Tile;

import javax.swing.*;

public class Queen extends Piece {
    public Queen(int color) {
        super(color);
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            return(new ImageIcon("src/resources/bQueen.png"));
        } else if(color == 1) {
            return(new ImageIcon("src/resources/wQueen.png"));
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
        if (Math.abs(xoffset) == Math.abs(yoffset)){
            for (int i = 1; i < Math.abs(yoffset); i++){
                if (board.getTile(Board.getLocationFromCords((int)(x + (i * Math.signum(xoffset))), (int)(y + (i * Math.signum(yoffset))))).getPiece() != null){
                    return false;
                }
            }
            return true;
        }else{
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
    }
}
