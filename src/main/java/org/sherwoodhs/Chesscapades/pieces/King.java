package org.sherwoodhs.Chesscapades.pieces;

import org.sherwoodhs.Chesscapades.Game.Board;
import org.sherwoodhs.Chesscapades.Game.Tile;

import javax.swing.*;

public class King extends Piece {
    private boolean canCastle;

    public King(int color) {
        super(color);
        canCastle = true;
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            return(new ImageIcon("src/resources/bKing.png"));
        } else if(color == 1) {
            return(new ImageIcon("src/resources/wKing.png"));
        } else {
            return null;
        }
    }

    @Override
    public boolean isLegalMove(int x, int y, int newX, int newY, Board board, boolean forReal) {
        Tile destinationTile = board.getTile(board.getLocationFromCords(newX, newY));
        if(destinationTile.isOccupied())
        {
            if(destinationTile.getPiece().getColor() == getColor())
            {
                return false;
            }
        }
        int dx = Math.abs(newX - x);
        int dy = Math.abs(newY - y);
        if(dx <= 1 && dy <= 1)
        {
            return true;
        }
        Tile start = board.getTile(board.getLocationFromCords(x, y));
        if(start.isCastleable())
        {
            if (newY == y) {
                int direction;
                Tile tile;
                if (newX == 2)
                {
                    direction = -1;
                    tile = board.getTile(Board.getLocationFromCords(0, y));
                    Tile extraTile = board.getTile(Board.getLocationFromCords(1, y));
                    if(extraTile.getPiece() != null)
                    {
                        return false;
                    }
                }
                else if (newX == 6)
                {
                    direction = 1;
                    tile = board.getTile(Board.getLocationFromCords(7, y));
                }
                else
                {
                    return false;
                }
                int newRookDestination = Board.getLocationFromCords(newX - direction, newY);
                Tile newRookTile = board.getTile(newRookDestination);
                if (start.isPlayableMove(newRookDestination, board, false)) {
                    if (tile.isCastleable() && !isInCheck(board))
                    {
                        if(forReal)
                        {
                            newRookTile.setPiece(tile.getPiece());
                            tile.setPiece(null);
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void setCastleable(boolean b)
    {
        canCastle = b;
    }

    public boolean isInCheck(Board board)
    {
        int color = getColor();
        Tile[] enemyPieces = board.getOccupiedTilesOfColor(1 - color);
        Tile king = board.getKing(color);
        for(Tile tile: enemyPieces) {

            if (!(tile.getPiece() instanceof King))
            {
                for (Tile tile2 : tile.getLegalMoves(board))
                {
                    if (tile2 == king)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
