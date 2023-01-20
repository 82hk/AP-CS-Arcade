package org.sherwoodhs.Chesscapades.Game;

import org.sherwoodhs.Chesscapades.pieces.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Tile extends JPanel {
    private Piece piece;
    private int location;
    static final Color tan = new Color(255, 248, 232);
    static final Color red = new Color(215, 122, 97);

    private final Color normalColor;
    private boolean castleable;
    int size;

    public Tile(int location, BorderLayout layout, int size)
    {
        super(layout);
        this.location = location;
        boolean isBlack;
        isBlack = location % 2 == 1;
        if (!((location / 8) % 2 == 0)) {
            isBlack = !isBlack;
        }
        if (!isBlack) {
            normalColor = tan;
        } else {
            normalColor = red;
        }
        setBackground(normalColor);
        this.size = size;
    }

    public boolean isOccupied()
    {
        return piece != null;
    }

    public Piece getPiece() {
        return piece;
    }

    public boolean isCastleable() {
        return castleable;
    }

    public void setCastleable(boolean b) {
        castleable = b;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        setCastleable(false);
        for(int i = 0; i < getComponentCount(); i++)
        {
            remove(i);
        }
        if(piece != null)
        {
            ImageIcon imageIcon = new ImageIcon(piece.getImageIcon().getImage().getScaledInstance(size * 7 / 8, size * 7 / 8, Image.SCALE_DEFAULT));
            JLabel image = new JLabel(imageIcon);
            add(image);
        }
        //check for pawn being on the top or bottom rows
        if (Board.getYFromLocation(getLocationOnBoard()) % 7 == 0 && getPiece() instanceof Pawn) {
            promPawn();
        }
        revalidate();
        repaint();
    }

    public void forceSetPiece(Piece piece)
    {
        this.piece = piece;
        setCastleable(false);
        for(int i = 0; i < getComponentCount(); i++)
        {
            remove(i);
        }
        if(piece != null)
        {
            ImageIcon imageIcon = new ImageIcon(piece.getImageIcon().getImage().getScaledInstance(size * 7 / 8, size * 7 / 8, Image.SCALE_DEFAULT));
            JLabel image = new JLabel(imageIcon);
            add(image);
        }
        revalidate();
        repaint();
    }

    public void quietlyUpdatePiece(Piece piece) {
        this.piece = piece;
    }

    public int getLocationOnBoard()
    {
        return location;
    }

    public Color getColor() {
        return normalColor;
    }

    public boolean isLegalMove(int location, Board board, boolean forReal) {
        if(getPiece() == null)
        {
            return false;
        }
        int x = getLocationOnBoard() % 8;
        int y = getLocationOnBoard() / 8;

        int newX = location % 8;
        int newY = location / 8;
        return getPiece().isLegalMove(x, y, newX, newY, board, forReal);
    }

    public boolean isPlayableMove(int location, Board board, boolean forReal) {
        if(!isLegalMove(location, board, forReal))
        {
            return false;
        }
        int color = getPiece().getColor();
        Tile[] enemyPieces = board.getOccupiedTilesOfColor(1 - color);
        Tile destination = board.getTile(location);
        Piece currentPiece = getPiece();
        Piece destinationCurrentPiece = destination.getPiece();
        destination.quietlyUpdatePiece(getPiece());
        quietlyUpdatePiece(null);
        Tile king = board.getKing(color);
        for(Tile tile: enemyPieces)
        {
            for(Tile tile2: tile.getLegalMoves(board))
            {
                if(tile2 == king)
                {
                    quietlyUpdatePiece(currentPiece);
                    destination.quietlyUpdatePiece(destinationCurrentPiece);
                    return false;
                }
            }
        }
        quietlyUpdatePiece(currentPiece);
        destination.quietlyUpdatePiece(destinationCurrentPiece);
        return true;
    }

    public Tile[] getPlayableMoves(Board board) {
        Tile[] allTiles = board.getTiles();
        ArrayList<Tile> goodTiles = new ArrayList();
        for (Tile tile:allTiles) {
            if(isPlayableMove(tile.getLocationOnBoard(), board, false))
            {
                goodTiles.add(tile);
            }
        }
        Tile[] arr = new Tile[goodTiles.size()];
        arr = goodTiles.toArray(arr);
        return arr;
    }

    public Tile[] getLegalMoves(Board board) {
        Tile[] allTiles = board.getTiles();
        ArrayList<Tile> goodTiles = new ArrayList();
        for (Tile tile:allTiles) {
            if(isLegalMove(tile.getLocationOnBoard(), board, false))
            {
                goodTiles.add(tile);
            }
        }
        Tile[] arr = new Tile[goodTiles.size()];
        arr = goodTiles.toArray(arr);
        return arr;
    }

    public void promPawn() {
        int input;

        AudioPlayer.play("src/main/java/org/sherwoodhs/Chesscapades/resources/audio/capture.wav");

        String[] possibilities = {"Queen", "Rook", "Knight", "Bishop"};
        JPanel jPanel = new JPanel(new GridBagLayout());
        JComboBox comboBox = new JComboBox(possibilities);
        input = JOptionPane.showConfirmDialog(null, comboBox, "Choose a piece to promote to: ", JOptionPane.DEFAULT_OPTION);
        jPanel.add(comboBox);

        if(input == JOptionPane.OK_OPTION) {
            String s = (String) comboBox.getSelectedItem();

            if (s == "Queen") {
                setPiece(new Queen(piece.getColor()));
            } else if (s == "Rook") {
                setPiece(new Rook(piece.getColor()));
            } else if (s == "Knight") {
                setPiece(new Knight(piece.getColor()));
            } else if (s == "Bishop") {
                setPiece(new Bishop(piece.getColor()));
            }
        }
    }
}
