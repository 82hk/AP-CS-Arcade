package org.sherwoodhs.Chesscapades.pieces;

import javax.swing.*;

public class King extends Piece {
    public King(int color) {
        super(color);
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            return(new ImageIcon("src/main/java/org/sherwoodhs/Chesscapades/resources/bKing.png"));
        } else if(color == 1) {
            return(new ImageIcon("src/main/java/org/sherwoodhs/Chesscapades/resources/wKing.png"));
        } else {
            return null;
        }
    }
}
