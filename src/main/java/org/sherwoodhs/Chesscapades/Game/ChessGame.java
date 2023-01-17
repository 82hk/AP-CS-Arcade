package org.sherwoodhs.Chesscapades.Game;

import org.sherwoodhs.Chesscapades.pieces.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class ChessGame extends JFrame implements MouseListener, MouseMotionListener {
    JLayeredPane layeredPane;
    Board chessBoard;
    Tile selectedTile;
    int turn;

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
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Tile[] tiles = chessBoard.getTiles();
        for(Tile rTile:tiles)
        {
            rTile.setBackground(rTile.getColor());
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            turn = 1 - turn;
            selectedTile = null;
            return;
        }
        Tile tile = (Tile) chessBoard.getComponentAt(e.getX(), e.getY());
        if (e.getButton() == MouseEvent.BUTTON2) {
            selectedTile.setPiece(null);
            return;
        }
        Piece piece = tile.getPiece();
        if(piece != null)
        {
            if (piece.getColor() == turn) {
                selectedTile = tile;
                tile.setBackground(Color.green);
                Tile[] legalMoves = tile.getPlayableMoves(chessBoard);
                for (Tile legalTile: legalMoves)
                {
                    legalTile.setBackground(Color.BLUE);
                }
                return;
            }
        }
        int location = tile.getLocationOnBoard();
        if(selectedTile != null && selectedTile.isPlayableMove(location, chessBoard, true))
        {
            tile.setPiece(selectedTile.getPiece());
            selectedTile.setPiece(null);
            selectedTile.setBackground(selectedTile.getColor());
            selectedTile = null;
            turn = 1 - turn;
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