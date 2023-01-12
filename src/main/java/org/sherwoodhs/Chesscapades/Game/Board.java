package org.sherwoodhs.Chesscapades.Game;

import javax.swing.*;

public class Board extends JPanel {
    public Board() {
        super();
    }

    public Tile getTile(int i)
    {
        return (Tile) getComponent(i);
    }
}
