package Game;

import pieces.King;

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
}
