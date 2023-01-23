package org.sherwoodhs.Tetris;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class TetrisGame extends JFrame implements org.sherwoodhs.Game {
    private JLabel status;
    private JLabel hold;
    public TetrisGame() {
        createUI();
    }
    public void createUI() {
        setTitle("Tetris");
        setSize(300, 600);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        status = new JLabel("Lines: 0");
        add(status, BorderLayout.SOUTH);
        hold = new JLabel("Hold: NONE");
        add(hold, BorderLayout.NORTH);
        Game game = new Game(this);
        add(game);
        game.start();
    }
    public JLabel getStatus() {
        return status;
    }
    public JLabel getHold() {
        return hold;
    }

    @Override
    public void start() {
        EventQueue.invokeLater(() -> {
            TetrisGame tetris = new TetrisGame();
            tetris.setVisible(true);
        });
    }

    @Override
    public void stop() {

    }

    @Override
    public String getName() {
        return "Tetris";
    }

    @Override
    public String getDescription() {
        return "<html>The classic game of Tetris, in Java.<br><br>By Hangil</html>";
    }

    @Override
    public BufferedImage getThumbnail() {
        final String path = "/tetris.png";
        return checkThumbnail(path);
    }
}
