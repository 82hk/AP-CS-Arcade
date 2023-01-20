package org.sherwoodhs.Chesscapades.Game;

import org.sherwoodhs.Chesscapades.pieces.*;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Board extends JPanel {
    public Board() {
        super();
    }

    public Tile getTile(int i)
    {
        return (Tile) getComponent(i);
    }

    public static int getXFromLocation(int i)
    {
        return i % 8;
    }

    public static int getYFromLocation(int i)
    {
        return i / 8;
    }

    public static int getLocationFromCords(int x, int y)
    {
        return y * 8 + x;
    }



    public Tile[] getTiles()
    {
        Component[] components = getComponents();
        List<Tile> tiles = new ArrayList();
        for(Component component:components)
        {
            if (component instanceof Tile)
            {
                tiles.add((Tile) component);
            }
        }
        Tile[] arr = new Tile[tiles.size()];
        arr = tiles.toArray(arr);
        return arr;
    }

    public Tile[] getOccupiedTiles()
    {
        Tile[] tiles = getTiles();
        List<Tile> occupiedTiles = new ArrayList<>();
        for(int i = 0; i < tiles.length; i++)
        {
            Tile tile = tiles[i];
            if (tile.isOccupied())
            {
                occupiedTiles.add(tile);
            }
        }
        Tile[] arr = new Tile[occupiedTiles.size()];
        arr = occupiedTiles.toArray(arr);
        return arr;
    }

    public Tile[] getOccupiedTilesOfColor(int color)
    {
        Tile[] occupiedTiles = getOccupiedTiles();
        List<Tile> goodTiles = new ArrayList<>();
        for(int i = 0; i < occupiedTiles.length; i++)
        {
            Tile tile = occupiedTiles[i];
            if (tile.getPiece().getColor() == color)
            {
                goodTiles.add(tile);
            }
        }
        Tile[] arr = new Tile[goodTiles.size()];
        arr = goodTiles.toArray(arr);
        return arr;
    }

    public Tile getKing(int color)
    {
        for (Tile tile:getOccupiedTilesOfColor(color))
        {
            if (tile.getPiece() instanceof King)
            {
                return tile;
            }
        }
        throw new IllegalStateException("Board is missing a king!");
    }

    public String computeFen(int turn)
    {
        StringBuilder fen = new StringBuilder();
        int empty = 0;
        int passantLoc = 0;
        for (int i = 0; i < 64; i++)
        {
            Piece piece = getTile(i).getPiece();
            if (i%8 == 0 && i > 0)
            {
                if (empty > 0)
                {
                    fen.append(empty);
                    empty = 0;
                }
                fen.append('/');
            }
            if (piece == null)
            {
                empty++;
            }
            else
            {
                if (empty > 0)
                {
                    fen.append(empty);
                    empty = 0;
                }
                if (piece instanceof King)
                {
                    if (piece.getColor() == 1)
                    {
                        fen.append('K');
                    }
                    else
                    {
                        fen.append('k');
                    }
                }
                else if (piece instanceof Queen)
                {
                    if (piece.getColor() == 1)
                    {
                        fen.append('Q');
                    }
                    else
                    {
                        fen.append('q');
                    }
                }
                else if (piece instanceof Bishop)
                {
                    if (piece.getColor() == 1)
                    {
                        fen.append('B');
                    }
                    else
                    {
                        fen.append('b');
                    }
                }
                else if (piece instanceof Knight)
                {
                    if (piece.getColor() == 1)
                    {
                        fen.append('N');
                    }
                    else
                    {
                        fen.append('n');
                    }
                }
                else if (piece instanceof Rook)
                {
                    if (piece.getColor() == 1)
                    {
                        fen.append('R');
                    }
                    else
                    {
                        fen.append('r');
                    }
                }
                else if (piece instanceof Pawn)
                {
                    Pawn pawn = (Pawn) piece;
                    if (piece.moved2 == 1)
                    {
                        int y = Board.getYFromLocation(i) - pawn.getForwardDirection();
                        int location = getLocationFromCords(Board.getXFromLocation(i), y);
                        passantLoc = location;
                    }
                    if (piece.getColor() == 1)
                    {
                        fen.append('P');
                    }
                    else
                    {
                        fen.append('p');
                    }
                }
            }
        }
        if (turn == 0)
        {
            fen.append(" b ");
        }
        else
        {
            fen.append(" w ");
        }
        if (getKing(1).isCastleable())
        {
            if(getTile(63).isCastleable())
            {
                fen.append("K");
            }
            if(getTile(56).isCastleable())
            {
                fen.append("Q");
            }
        }
        if (getKing(0).isCastleable())
        {
            if(getTile(7).isCastleable())
            {
                fen.append("k");
            }
            if(getTile(0).isCastleable())
            {
                fen.append("q");
            }
        }
        if (fen.charAt(fen.length() - 1) == ' ')
        {
            fen.append('-');
        }
        if (passantLoc != 0)
        {
            fen.append(' ' + convertLocationToCode(passantLoc));
        }
        return fen.toString();
    }
    public static String convertLocationToCode(int location)
    {
        int x = getXFromLocation(location);
        int y = getYFromLocation(location);
        Integer codeN = 8 - y;
        char codeA;
        switch (x)
        {
            case 7:
                codeA = 'h';
                break;
            case 6:
                codeA = 'g';
                break;
            case 5:
                codeA = 'f';
                break;
            case 4:
                codeA = 'e';
                break;
            case 3:
                codeA = 'd';
                break;
            case 2:
                codeA = 'c';
                break;
            case 1:
                codeA = 'b';
                break;
            case 0:
                codeA = 'a';
                break;
            default:
                throw new IllegalArgumentException();
        }
        return (codeN.toString() + codeA);
    }

    public static int convertCodeToLocation(String code)
    {
        char codeA = code.charAt(0);
        int codeN = Integer.parseInt(Character.toString(code.charAt(1)));
        int y = 8 - codeN;
        int x;
        switch (codeA)
        {
            case 'h':
                x = 7;
                break;
            case 'g':
                x = 6;
                break;
            case 'f':
                x = 5;
                break;
            case 'e':
                x = 4;
                break;
            case 'd':
                x = 3;
                break;
            case 'c':
                x = 2;
                break;
            case 'b':
                x = 1;
                break;
            case 'a':
                x = 0;
                break;
            default:
                throw new IllegalArgumentException();
        }
        return getLocationFromCords(x, y);
    }

}
