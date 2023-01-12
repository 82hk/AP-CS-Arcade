package org.sherwoodhs.Chesscapades.pieces;


import javax.swing.*;

public class Rook extends Piece {
    public Rook(int color) {
        super(color);
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            return(new ImageIcon("src/main/java/org/sherwoodhs/Chesscapades/resources/bRook.png"));
        } else if(color == 1) {
            return(new ImageIcon("src/main/java/org/sherwoodhs/Chesscapades/resources/wRook.png"));
        } else {
            return null;
        }
    }



}
