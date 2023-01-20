package org.sherwoodhs.Checkers;

import javax.swing.*;
import java.awt.*;

public class CheckerTile extends JButton {

    private Color color;

    private ImageIcon image;
    private Color pieceColor;
    public boolean hasKing;
    private int pieceSize;

    public CheckerTile(Color c, int pieceSize) {
        color = c;
        setBackground(color);
        setOpaque(true);
        setBorderPainted(false);

        this.pieceSize = pieceSize;
    }

    public Color getColor() {return color;}

    public Color getPieceColor() {
        return pieceColor;
    }

    public boolean hasPiece() {
        if (image != null) {
            return true;
        }
        return false;
    }

    public void removePiece() {
        image = null;
        pieceColor = null;
        hasKing = false;
        setIcon(null);
    }

    public void setPiece(Color color) {
        pieceColor = color;
        image = color == Color.BLACK ?
                new ImageIcon("black_piece.png") :
                new ImageIcon("red_piece.png");
        image = resizeImage(image);
        setIcon(image);
    }

    public void setKing(Color color) {
        hasKing = true;
        pieceColor = color;
        image = color == Color.BLACK ?
                new ImageIcon("black_king.png") :
                new ImageIcon("red_king.png");
        image = resizeImage(image);
        setIcon(image);
    }


    private ImageIcon resizeImage(ImageIcon img) {
        Image image = img.getImage();
        Image newImage = image.getScaledInstance(pieceSize, pieceSize, Image.SCALE_DEFAULT);
        return new ImageIcon(newImage);
    }
}
