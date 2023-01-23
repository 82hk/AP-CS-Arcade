package org.sherwoodhs.Checkers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CheckersBoard extends JPanel implements ActionListener {

    private int pieceSize;
    private ArrayList<CheckerTile> tiles;
    private final int[] blackStartingPos = {1, 3, 5, 7,
                                            8, 10, 12, 14,
                                            17, 19, 21, 23};

    private final int[] redStartingPos = {40, 42, 44, 46,
                                          49, 51, 53, 55,
                                          56, 58, 60, 62};

    private final int[] topEdgePositions = {1, 3, 5, 7};
    private final int[] bottomEdgePositions = {56, 58, 60, 62};

    private CheckerTile selectedTile;
    private Color currentTurn;
    public boolean isTurnOver;
    private CheckerTile moveNext;
    private ArrayList<CheckerTile> highlightedTiles;

    public boolean isOver;

    public CheckersBoard(int boardSize) {
        tiles = new ArrayList<>();
        final Checkers main = new Checkers();
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                initComponents(main, boardSize, currentTurn);
            }
        });
    }

    private Color switchColor(Color c) {
        if (c == Color.RED) {
            return Color.BLACK;
        }
        return Color.RED;
    }

    private void initComponents(Checkers main, int frameSize, Color currentTurn) {
        highlightedTiles = new ArrayList<>();
        currentTurn = Color.BLACK;
        pieceSize = frameSize/10;
        isTurnOver = false;
        isOver = false;
        this.currentTurn = currentTurn;

        setSize(frameSize, frameSize);
        setLayout(new GridLayout(8, 8));
        setVisible(true);

        Color c = Color.RED;
        for(int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tiles.add(new CheckerTile(c, pieceSize));
                c = switchColor(c);
            }
            c = switchColor(c);
        }

        for (CheckerTile tile: tiles) {
            tile.addActionListener(this);
            add(tile);
        }

        for (int i = 0; i < blackStartingPos.length; i++) {
            tiles.get(blackStartingPos[i]).setPiece(Color.BLACK);
            tiles.get(redStartingPos[i]).setPiece(Color.red);
        }
    }

    public void actionPerformed(ActionEvent e) {
        for (CheckerTile tile : highlightedTiles) {
            tile.setBackground(Color.BLACK);
        }

        if (selectedTile != null) {
            selectedTile.setBackground(Color.BLACK);
        }

        CheckerTile tile = (CheckerTile) e.getSource();
        if (tile.hasPiece() && tile.getPieceColor() == currentTurn)
            highlightJumpMoves(tile);

       if (tile.hasPiece()) {
           selectedTile = tile;
           selectedTile.setBackground(Color.lightGray);
        } else {
            moveTile(selectedTile, tile);
            selectedTile = null;
        }
    }

    public void moveTile(CheckerTile selectedTile, CheckerTile destinationTile) {
        if (moveNext != null) {
            selectedTile = moveNext;
            moveNext = null;
        }
        try {
            if (selectedTile.hasPiece() && selectedTile.getPieceColor() != currentTurn)
                return;
        } catch (Exception e) {
            return;
        }

        ArrayList<CheckerTile> currentColorPiecesToMove = new ArrayList<>();
        for (CheckerTile tile : tiles) {
            if (tile.getPieceColor() == currentTurn && hasJumpMove(tile)) {
                currentColorPiecesToMove.add(tile);
            }
        }

        if (!currentColorPiecesToMove.contains(selectedTile) && !currentColorPiecesToMove.isEmpty()) {
            return;
        }
        
        if (isOpenSpaceMove(selectedTile, destinationTile) ) {
            if (selectedTile.hasKing) {
                destinationTile.setKing(selectedTile.getPieceColor());
            } else {
                destinationTile.setPiece(selectedTile.getPieceColor());
            }
            selectedTile.removePiece();
        } else if (isJumpMove(selectedTile, destinationTile)) {
            performJumpMove(selectedTile, destinationTile);

            if (checkPromo(destinationTile))
                promote(destinationTile);

            if (hasJumpMove(destinationTile)) {
                moveNext = destinationTile;
                return;
            }
        } else {return;}

        if (checkPromo(destinationTile))
            promote(destinationTile);

        endTurn();
    }

    public boolean checkPromo(CheckerTile tile) {
        if (tile.getPieceColor() == Color.BLACK) {
            for (int pos : bottomEdgePositions) {
                if (tiles.indexOf(tile) == pos) {
                    return true;
                }
            }
        } else {
            for (int pos : topEdgePositions) {
                if (tiles.indexOf(tile) == pos) {
                    return true;
                }
            }
        }
        return false;
    }

    public void promote(CheckerTile tile) {
        Color pieceColor = tile.getPieceColor();
        tile.removePiece();
        tile.setKing(pieceColor);
    }

    public void endTurn() {
        isTurnOver = true;
        currentTurn = currentTurn == Color.BLACK ? Color.red : Color.BLACK;

        boolean hasBlackPiece = false;
        boolean hasRedPiece = false;
        for (CheckerTile tile : tiles) {
            if (tile.hasPiece() && tile.getPieceColor() == Color.BLACK) {
                hasBlackPiece = true;
            } else if (tile.hasPiece() && tile.getPieceColor() == Color.red) {
                hasRedPiece = true;
            }
        }
        if (!hasBlackPiece || !hasRedPiece) {
            isOver = true;
        }
    }

    public boolean hasJumpMove(CheckerTile tile) {
       if (tile.hasPiece()) {
            int[] offsets = getValidIndexOffsets(tile);
            Color oppositeColor = tile.getPieceColor() == Color.BLACK ? Color.RED : Color.BLACK;

            for (int offset : offsets) {
                try {
                    if (tiles.get(tiles.indexOf(tile) + offset).getPieceColor() == oppositeColor &&
                            !(tiles.get(tiles.indexOf(tile) + (offset * 2)).hasPiece()) &&
                            tiles.get(tiles.indexOf(tile) + (offset * 2)).getColor() == Color.BLACK) {
                        return true;
                    }
                } catch (Exception e) {}
            }
        }
       return false;
    }

    public boolean isJumpMove(CheckerTile selectedTile, CheckerTile destinationTile) {
        int selectedTileIndex = tiles.indexOf(selectedTile);
        Color selectedPieceColor = selectedTile.getPieceColor();
        Color opposingTeamColor = selectedPieceColor == Color.BLACK ? Color.RED : Color.BLACK;

        for (int offset : getValidIndexOffsets(selectedTile)) {
            try {
                if (destinationTile == tiles.get(selectedTileIndex + (2 * offset)) &&
                        tiles.get(selectedTileIndex + offset).hasPiece() &&
                        tiles.get(selectedTileIndex + offset).getPieceColor() == opposingTeamColor)
                    return true;
            } catch (Exception e) {}
        }
        return false;
    }

    public void performJumpMove(CheckerTile selectedTile, CheckerTile destinationTile) {
        int selectedTileIndex = tiles.indexOf(selectedTile);
        int destinationTileIndex = tiles.indexOf(destinationTile);
        Color selectedPieceColor = selectedTile.getPieceColor();

        if (selectedTile.hasKing) {
            destinationTile.setKing(selectedPieceColor);
        } else {
            destinationTile.setPiece(selectedPieceColor);
        }
        selectedTile.removePiece();
        tiles.get(selectedTileIndex + (destinationTileIndex - selectedTileIndex)/2).removePiece();
    }

    public boolean isOpenSpaceMove(CheckerTile selectedTile, CheckerTile destinationTile) {
        if (hasJumpMove(selectedTile)) {
            return false;
        }

        int selectedTileIndex = tiles.indexOf(selectedTile);
        for (int offset : getValidIndexOffsets(selectedTile)) {
            try {
                if (destinationTile == tiles.get(selectedTileIndex + offset))
                    return true;
            } catch (Exception e) {}
        }
        return false;
    }

    public void highlightJumpMoves(CheckerTile tile) {
        if (hasJumpMove(tile)) {
            for (int offset : getValidIndexOffsets(tile)) {
                try {
                    if (isJumpMove(tile, tiles.get(tiles.indexOf(tile) + (offset * 2))) &&
                            !tiles.get(tiles.indexOf(tile) + (offset * 2)).hasPiece()) {
                        tiles.get(tiles.indexOf(tile) + offset).setBackground(Color.green);
                        highlightedTiles.add(tiles.get(tiles.indexOf(tile) + offset));
                    }
                } catch (Exception e) {}
            }
        }
    }

    public int[] getValidIndexOffsets(CheckerTile tile) {
        int[] validIndexOffsets;

        if (tile.hasKing) {
            return new int[] {-7, -9, 7, 9};
        }

        if (tile.getPieceColor() == Color.BLACK) {
            validIndexOffsets = new int[] {7, 9};
        } else {
            validIndexOffsets = new int[] {-7, -9};
        }
        return validIndexOffsets;
    }
}
