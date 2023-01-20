package org.sherwoodhs.Tetris;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main extends JFrame implements org.sherwoodhs.Game {
    private JLabel status;
    private JLabel hold;
    public Main() {
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
            Main tetris = new Main();
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
        return "The classic game of Tetris, in Java.";
    }

    @Override
    public BufferedImage getThumbnail() {
        final String path = "/tetris.png";
        return checkThumbnail(path);
    }
}