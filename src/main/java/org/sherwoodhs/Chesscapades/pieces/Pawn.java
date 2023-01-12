package org.sherwoodhs.Chesscapades.pieces;

import javax.swing.*;

public class Pawn extends Piece {
    public Pawn(int color) {
        super(color);
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            return(new ImageIcon("src/main/java/org/sherwoodhs/Chesscapades/resources/bPawn.png"));
        } else if(color == 1) {
            return(new ImageIcon("src/main/java/org/sherwoodhs/Chesscapades/resources/wPawn.png"));
        } else {
            return null;
        }
    }
}
