package org.sherwoodhs.Tetris;

import java.awt.EventQueue;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main extends JFrame {
    private JLabel status;
    public Main() {
        createUI();
    }
    public void createUI() {
        setTitle("Tetris");
        setSize(200, 400);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        status = new JLabel("Lines: 0");
        add(status, BorderLayout.SOUTH);
        Game game = new Game(this);
        add(game);
        game.start();
    }
    public JLabel getStatus() {
        return status;
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Main tetris = new Main();
            tetris.setVisible(true);
        });
    }
}
