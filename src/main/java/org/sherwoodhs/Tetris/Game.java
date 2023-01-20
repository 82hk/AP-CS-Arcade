package org.sherwoodhs.Tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Game extends JPanel {
    private final int WIDTH = 10;
    private final int HEIGHT = 20;
    // ms per update
    private final int INTERVAL = 650;
    private Timer TIMER;
    private boolean isFalling = true;
    private boolean isPaused = false;
    private boolean isHeld = false;
    private int linesCleared = 0;
    private int cx = 0;
    private int cy = 0;
    private Color colors[] = {
            Color.BLACK, new Color(240, 50, 100),
            Color.GREEN, Color.CYAN,
            new Color(255, 55, 210), Color.YELLOW,
            new Color(50, 100, 240), new Color(240, 140, 0)
    };
    private JLabel status, hold;
    private Piece cpiece, hpiece;
    private Piece.Tetromino[] board;
    public static ArrayList<Piece.Tetromino> pieces = new ArrayList<Piece.Tetromino>();
    public Game(Main m) {
        setBackground(new Color(20, 20, 30));
        createBoard(m);
    }
    private void createBoard(Main m) {
        setFocusable(true);
        status = m.getStatus();
        hold = m.getHold();
        hold.setOpaque(true);
        hold.setForeground(Color.WHITE);
        hold.setBackground(new Color(20, 20, 30));
        addKeyListener(new Adapter());
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
    }
    private int getGridUnitWidth() {
        return (int) getSize().getWidth() / WIDTH;
    }
    private int getGridUnitHeight() {
        return (int) getSize().getHeight() / HEIGHT;
    }
    private void drawGridUnit(Graphics g, int x, int y, Piece.Tetromino piece) {
        Color color = colors[piece.ordinal()];
        g.setColor(color);
        g.fillRect(x + 1, y + 1, getGridUnitWidth() - 2, getGridUnitHeight() - 2);
    }
    private Piece.Tetromino getPieceAt(int x, int y) {
        return board[(y * WIDTH) + x];
    }
    public void start() {
        cpiece = new Piece();
        hpiece = new Piece();
        board = new Piece.Tetromino[WIDTH * HEIGHT];
        clearBoard();
        newPiece();
        TIMER = new Timer(INTERVAL, new Cycle());
        TIMER.start();
    }
    private void pause() {
        isPaused = !isPaused;
        if (isPaused) {
            status.setText("Paused");
        } else {
            status.setText(String.format("Lines: %d", linesCleared));
        }
        repaint();
    }
    private void update() {
        if (isPaused) {
            return;
        }
        if (!isFalling) {
            isFalling = true;
            newPiece();
        } else {
            softDropPiece();
        }
    }
    private void drawBoard(Graphics g) {
        Dimension size = getSize();
        int top = (int) size.getHeight() - HEIGHT * getGridUnitHeight();
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                Piece.Tetromino piece = getPieceAt(j, HEIGHT - i - 1);
                if (piece != Piece.Tetromino.NONE) {
                    drawGridUnit(g, j * getGridUnitWidth(), top + i * getGridUnitHeight(), piece);
                }
            }
        }
        if (cpiece.getPiece() != Piece.Tetromino.NONE) {
            for (int i = 0; i < 4; i++) {
                int x = cx + cpiece.getX(i);
                int y = cy - cpiece.getY(i);
                drawGridUnit(g, x * getGridUnitWidth(), top + (HEIGHT - y - 1) * getGridUnitHeight(), cpiece.getPiece());
            }
        }
    }
    private void clearBoard() {
        for (int i = 0; i < HEIGHT * WIDTH; i++) {
            board[i] = Piece.Tetromino.NONE;
        }
    }
    private void dropPiece() {
        for (int i = 0; i < 4; i++) {
            int x = cx + cpiece.getX(i);
            int y = cy - cpiece.getY(i);
            board[(y * WIDTH) + x] = cpiece.getPiece();
        }
        removeLines();
        isHeld = false;
        if (isFalling) {
            newPiece();
        }
    }
    private void newPiece() {
        cpiece.setRandomPiece();
        cx = WIDTH / 2 + 1;
        cy = HEIGHT - 1 + cpiece.minY();
        if (!move(cpiece, cx, cy)) {
            cpiece.setPiece(Piece.Tetromino.NONE);
            TIMER.stop();
            status.setText(String.format("Game over! Lines: %d", linesCleared));
        }
    }
    private void newPiece(Piece.Tetromino piece) {
        cpiece.setPiece(piece);
        cx = WIDTH / 2 + 1;
        cy = HEIGHT - 1 + cpiece.minY();
        if (!move(cpiece, cx, cy)) {
            cpiece.setPiece(Piece.Tetromino.NONE);
            TIMER.stop();
            status.setText(String.format("Game over! Lines: %d", linesCleared));
        }
    }
    private void holdPiece() {
        if (isHeld) {
            return;
        }
        if (hpiece.getPiece() == Piece.Tetromino.NONE) {
            hpiece.setPiece(cpiece.getPiece());
            newPiece();
        } else {
            Piece.Tetromino temp = hpiece.getPiece();
            hpiece.setPiece(cpiece.getPiece());
            newPiece(temp);
        }
        hold.setForeground(colors[hpiece.getPiece().ordinal()]);
        hold.setText("Hold: " + hpiece.getPiece());
        // only be able to hold a piece once per drop
        isHeld = true;
    }
    private void softDropPiece() {
        if (!move(cpiece, cx, cy - 1)) {
            dropPiece();
        }
    }
    private void hardDropPiece() {
        int y = cy;
        while (y > 0) {
            if (!move(cpiece, cx, y - 1)) {
                break;
            }
            y--;
        }
        dropPiece();
    }
    private boolean move(Piece piece, int nx, int ny) {
        for (int i = 0; i < 4; i++) {
            int x = nx + piece.getX(i);
            int y = ny - piece.getY(i);
            if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) {
                return false;
            }
            if (getPieceAt(x, y) != Piece.Tetromino.NONE) {
                return false;
            }
        }
        cpiece = piece;
        cx = nx;
        cy = ny;
        repaint();
        return true;
    }
    private void removeLines() {
        int completeLines = 0;
        for (int i = HEIGHT - 1; i >= 0; i--) {
            boolean isComplete = true;
            for (int j = 0; j < WIDTH; j++) {
                if (getPieceAt(j, i) == Piece.Tetromino.NONE) {
                    isComplete = false;
                    break;
                }
            }
            if (isComplete) {
                completeLines++;
                for (int k = i; k < HEIGHT - 1; k++) {
                    for (int j = 0; j < WIDTH; j++) {
                        board[(k * WIDTH) + j] = getPieceAt(j, k + 1);
                    }
                }
            }
        }
        if (completeLines > 0) {
            linesCleared += completeLines;
            status.setText(String.format("Lines: %d", linesCleared));
            isFalling = false;
            cpiece.setPiece(Piece.Tetromino.NONE);
        }
    }
    private class Adapter extends KeyAdapter {
        private boolean isHeld = false;
        private boolean hasRotated = false, hasDropped = false, hasPaused = false;
        @Override
        public void keyPressed(KeyEvent e) {
            if (cpiece.getPiece() == Piece.Tetromino.NONE) {
                return;
            }
            if (isPaused && e.getKeyCode() != KeyEvent.VK_P) {
                return;
            }
            isHeld = true;
            // introduce ability to hold key in the future
            switch (e.getKeyCode()) {
                case KeyEvent.VK_P:
                    if (!hasPaused)
                        pause();
                    hasPaused = true;
                    break;
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_A:
                    if (isHeld)
                        move(cpiece, cx - 1, cy);
                    break;
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_D:
                    if (isHeld)
                        move(cpiece, cx + 1, cy);
                    break;
                case KeyEvent.VK_UP:
                case KeyEvent.VK_W:
                    if (!hasRotated)
                        move(cpiece.rotate(), cx, cy);
                    hasRotated = true;
                    break;
                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_S:
                    if (isHeld)
                        softDropPiece();
                    break;
                case KeyEvent.VK_C:
                    holdPiece(); break;
                case KeyEvent.VK_SPACE:
                    if (!hasDropped)
                        hardDropPiece();
                    hasDropped = true;
                    break;
            }
        }
        @Override
        public void keyReleased(KeyEvent e) {
            isHeld = false;
            hasRotated = false;
            hasPaused = false;
            hasDropped = false;
        }
    }
    private void paintCycle() {
        update();
        repaint();
    }
    private class Cycle implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            paintCycle();
        }
    }
}
