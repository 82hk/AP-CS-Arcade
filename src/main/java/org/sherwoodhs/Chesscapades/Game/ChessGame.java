package org.sherwoodhs.Chesscapades.Game;

import org.sherwoodhs.Chesscapades.pieces.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class ChessGame extends JFrame implements MouseListener, MouseMotionListener {
    JLayeredPane layeredPane;
    Board chessBoard;
    Tile selectedTile;
    int turn;

    ArrayList<String> fens;

    public ChessGame(int size){
        Dimension boardSize = new Dimension(size, size);

        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(boardSize);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);

        chessBoard = new Board();
        layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
        chessBoard.setLayout(new GridLayout(8, 8));
        chessBoard.setPreferredSize(boardSize);
        chessBoard.setBounds(0,0, boardSize.width, boardSize.height);

        for (int i = 0; i < 64; i++) {
            Tile tile = new Tile(i, new BorderLayout(), size/8);
            chessBoard.add(tile);
        }

        setupPieces();
        turn = 1;

        fens = new ArrayList<String>();
        fens.add(chessBoard.computeFen(turn));
    }

    public void setupPieces() {
        //setup black pieces
        chessBoard.getTile(0).setPiece(new Rook(0));
        chessBoard.getTile(7).setPiece(new Rook(0));
        chessBoard.getTile(0).setCastleable(true);
        chessBoard.getTile(7).setCastleable(true);
        chessBoard.getTile(1).setPiece(new Knight(0));
        chessBoard.getTile(6).setPiece(new Knight(0));
        chessBoard.getTile(2).setPiece(new Bishop(0));
        chessBoard.getTile(5).setPiece(new Bishop(0));
        chessBoard.getTile(3).setPiece(new Queen(0));
        chessBoard.getTile(4).setPiece(new King(0));
        chessBoard.getTile(4).setCastleable(true);
        for (int i = 8; i < 16; i++) 
        {
            chessBoard.getTile(i).setPiece(new Pawn(0));
        }
        //setup white pieces
        chessBoard.getTile(56).setPiece(new Rook(1));
        chessBoard.getTile(63).setPiece(new Rook(1));
        chessBoard.getTile(56).setCastleable(true);
        chessBoard.getTile(63).setCastleable(true);
        chessBoard.getTile(57).setPiece(new Knight(1));
        chessBoard.getTile(62).setPiece(new Knight(1));
        chessBoard.getTile(58).setPiece(new Bishop(1));
        chessBoard.getTile(61).setPiece(new Bishop(1));
        chessBoard.getTile(59).setPiece(new Queen(1));
        chessBoard.getTile(60).setPiece(new King(1));
        chessBoard.getTile(60).setCastleable(true);
        for (int i = 48; i < 56; i++)
        {
            Tile tile = (Tile) chessBoard.getComponent(i);
            tile.setPiece(new Pawn(1));
        }
        System.out.println(chessBoard.computeFen(1));
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Tile[] tiles = chessBoard.getTiles();
        for (Tile rTile : tiles) {
            rTile.setBackground(rTile.getColor());
        }
        Tile tile = (Tile) chessBoard.getComponentAt(e.getX(), e.getY());
        Piece piece = tile.getPiece();
        if (piece != null) {
            if (piece.getColor() == turn) {
                selectedTile = tile;
                tile.setBackground(Color.green);
                Tile[] legalMoves = tile.getPlayableMoves(chessBoard);
                for (Tile legalTile : legalMoves) {
                    legalTile.setBackground(Color.BLUE);
                }
                return;
            }
        }
        int location = tile.getLocationOnBoard();
        if (selectedTile != null && selectedTile.isPlayableMove(location, chessBoard, true)) {
            //process move
            tile.setPiece(selectedTile.getPiece());
            selectedTile.setPiece(null);
            selectedTile.setBackground(selectedTile.getColor());
            selectedTile = null;
            turn = 1 - turn;

            //compute fen
            String fen = chessBoard.computeFen(turn);
            fens.add(fen);


            Tile[] enemyTiles = chessBoard.getOccupiedTilesOfColor(turn);

            boolean canMove = false;
            for (Tile enemyTile : enemyTiles) {
                if (enemyTile.getPlayableMoves(chessBoard).length > 0)
                {
                    canMove = true;
                    break;
                }
            }

            King king = (King) chessBoard.getKing(turn).getPiece();
            if (!canMove)
            {
                if (king.isInCheck(chessBoard)) {
                    checkmate();
                }
                else
                {
                    stalemate();
                }
            }

            //check for three move repetition
            int priorOccurrences = 0;
            for (String oldFen: fens)
            {
                if (fen.equals(oldFen))
                {
                    priorOccurrences++;
                }
            }
            if (priorOccurrences >= 3)
            {
                stalemate();
            }

        }
    }

    void checkmate()
    {
        int option;
        String buttons[] = {"Replay", "Quit"};
        if (turn == 1) {
            option = JOptionPane.showOptionDialog(null, "White wins! Play again or quit?", "Checkmate", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, buttons, "default");
        } else {
            option = JOptionPane.showOptionDialog(null, "Black wins! Play again or quit?", "Checkmate", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, buttons, "default");
        }

        if (option == 0) {
            for (int i = 0; i < 64; i++) {
                chessBoard.getTile(i).setPiece(null);
            }
            setupPieces();
        } else {
            System.exit(0);
        }
    }

    void stalemate()
    {
        int option;
        String buttons[] = {"Replay", "Quit"};
        option = JOptionPane.showOptionDialog(null, "Stalemate! Play again or quit?", "Stalemate", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, buttons, "default");

        if (option == 0) {
            for (int i = 0; i < 64; i++) {
                chessBoard.getTile(i).setPiece(null);
            }
            setupPieces();
        } else {
            System.exit(0);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }


}