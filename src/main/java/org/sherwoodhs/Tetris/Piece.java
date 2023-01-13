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
    public void setPiece(Tetromino piece) {
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
    private void setX(int index, int x) {
        coords[index][0] = x;
    }
    private void setY(int index, int y) {
        coords[index][1] = y;
    }
    public int getX(int index) {
        return coords[index][0];
    }
    public int getY(int index) {
        return coords[index][1];
    }
    public Tetromino getPiece() {
        return piece;
    }
    // change this to a better system in the future
    public void setRandomPiece() {
        Random r = new Random();
        int x = Math.abs(r.nextInt()) % 7 + 1;
        Tetromino[] values = Tetromino.values();
        setPiece(values[x]);
    }
    public int minY() {
        int y = coords[0][1];
        for (int i = 0; i < 4; i++) {
            y = Math.min(y, coords[i][1]);
        }
        return y;
    }
    // rotates the piece counter-clockwise if applicable
    Piece rotate() {
        if (piece == Tetromino.O_MINO) {
            return this;
        }
        Piece res = new Piece();
        res.piece = piece;
        for (int i = 0; i < 4; i++) {
            res.setX(i, -getY(i));
            res.setY(i, getX(i));
        }
        return res;
    }
}
