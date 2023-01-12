package org.sherwoodhs.Chesscapades.pieces;

import javax.swing.*;

public class Bishop extends Piece {
    public Bishop(int color) {
        super(color);
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            return(new ImageIcon("src/main/java/org/sherwoodhs/Chesscapades/resources/bBishop.png"));
        } else if(color == 1) {
            return(new ImageIcon("src/main/java/org/sherwoodhs/Chesscapades/resources/wBishop.png"));
        } else {
            return null;
        }
    }
}
