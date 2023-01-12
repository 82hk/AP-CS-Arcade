package org.sherwoodhs.Chesscapades.pieces;

import javax.swing.*;

public class Queen extends Piece {
    public Queen(int color) {
        super(color);
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            return(new ImageIcon("src/main/java/org/sherwoodhs/Chesscapades/resources/bQueen.png"));
        } else if(color == 1) {
            return(new ImageIcon("src/main/java/org/sherwoodhs/Chesscapades/resources/wQueen.png"));
        } else {
            return null;
        }
    }
}
