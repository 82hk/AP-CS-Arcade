package org.sherwoodhs.Tetris;

import java.util.Random;

public class Piece {
    protected enum Tetromino {
        NONE,
        Z_MINO, S_MINO, I_MINO, T_MINO, O_MINO, L_MINO, ML_MINO
    }
    private Tetromino piece;
    private int[][] coords;
    public Piece() {
        coords = new int[4][2];
        setPiece(Tetromino.NONE);

    }
    private void setPiece(Tetromino piece) {
        int[][][] coords_table = new int[][][]{
                {{0, 0}, {0, 0}, {0, 0}, {0, 0}},
                {{0, -1}, {0, 0}, {-1, 0}, {-1, 1}},
                {{0, -1}, {0, 0}, {1, 0}, {1, 1}},
                {{0, -1}, {0, 0}, {0, 1}, {0, 2}},
                {{-1, 0}, {0, 0}, {1, 0}, {0, 1}},
                {{0, 0}, {1, 0}, {0, 1}, {1, 1}},
                {{-1, -1}, {0, -1}, {0, 0}, {0, 1}},
                {{1, -1}, {0, -1}, {0, 0}, {0, 1}}
        };
        for(int i = 0; i < 4; i++) {
            System.arraycopy(coords_table[piece.ordinal()], 0, coords, 0, 4);
        }
        this.piece = piece;
    }
}
