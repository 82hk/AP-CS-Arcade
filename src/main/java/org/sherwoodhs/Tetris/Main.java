package org.sherwoodhs.Tetris;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main extends JFrame {
    private JLabel status;
    private JLabel hold;
    public Main() {
        createUI();
    }
    public void createUI() {
        setTitle("Tetris");
        setSize(300, 600);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Main tetris = new Main();
            tetris.setVisible(true);
        });
    }
}