package org.sherwoodhs.Chesscapades.Game;

import org.sherwoodhs.Chesscapades.pieces.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChessGame extends JFrame implements MouseListener, MouseMotionListener, KeyListener {
    JLayeredPane layeredPane;
    Board chessBoard;
    Tile selectedTile;
    int turn;

    public ChessGame(){
        Dimension boardSize = new Dimension(1000, 1000);

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
            Tile tile = new Tile(i, new BorderLayout(), boardSize.height/8);
            chessBoard.add(tile);
        }

        setupPieces();
        turn = 1;

    }
    
    public void setupPieces() {
        //setup black pieces
        chessBoard.getTile(0).setPiece(new Rook(0));;
        chessBoard.getTile(7).setPiece(new Rook(0));;
        chessBoard.getTile(1).setPiece(new Knight(0));;
        chessBoard.getTile(6).setPiece(new Knight(0));;
        chessBoard.getTile(2).setPiece(new Bishop(0));;
        chessBoard.getTile(5).setPiece(new Bishop(0));;
        chessBoard.getTile(3).setPiece(new Queen(0));;
        chessBoard.getTile(4).setPiece(new King(0));;
        for (int i = 8; i < 16; i++) 
        {
            chessBoard.getTile(i).setPiece(new Pawn(0));
        }
        //setup white pieces
        chessBoard.getTile(56).setPiece(new Rook(1));
        chessBoard.getTile(63).setPiece(new Rook(1));
        chessBoard.getTile(57).setPiece(new Knight(1));
        chessBoard.getTile(62).setPiece(new Knight(1));
        chessBoard.getTile(58).setPiece(new Bishop(1));
        chessBoard.getTile(61).setPiece(new Bishop(1));
        chessBoard.getTile(59).setPiece(new Queen(1));
        chessBoard.getTile(60).setPiece(new King(1));
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
        if (e.getButton() == MouseEvent.BUTTON3) {
            turn = 1 - turn;
            return;
        }
        Tile tile = (Tile) chessBoard.getComponentAt(e.getX(), e.getY());
        Piece piece = tile.getPiece();
        if(piece != null)
        {
            if (piece.getColor() == turn) {
                if(selectedTile != null)
                {
                    selectedTile.setBackground(selectedTile.getColor());
                }
                selectedTile = tile;
                tile.setBackground(Color.green);
                return;
            }
        }
        if(selectedTile != null && selectedTile.getPiece().isMovable() && selectedTile.isLegalMove(tile.getLocationOnBoard(), chessBoard))
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


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}