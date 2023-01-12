package org.sherwoodhs.Chesscapades.pieces;

import javax.swing.*;

public class Knight extends Piece {
    public Knight(int color) {
        super(color);
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            return(new ImageIcon("src/main/java/org/sherwoodhs/Chesscapades/resources/bKnight.png"));
        } else if(color == 1) {
            return(new ImageIcon("src/main/java/org/sherwoodhs/Chesscapades/resources/wKnight.png"));
        } else {
            return null;
        }
    }
}
