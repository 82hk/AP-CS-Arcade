package org.sherwoodhs;

import org.sherwoodhs.CardsOfFate.CardsOfFateGame;
import org.sherwoodhs.Chesscapades.ChessGame;
import org.sherwoodhs.Connect4.ConnectFourGame;
import org.sherwoodhs.Tetris.TetrisGame;
import org.sherwoodhs.TicTacToe.TicTacToeGame;
import org.sherwoodhs.Wordle.WordleGame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Menu extends JFrame {
    public static final Menu MENU = new Menu();
    private static final int SCREEN_WIDTH = 1080;
    private static final int SCREEN_HEIGHT = 720;
    private static int NUM_GAMES;
    ArrayList<Class> main = new ArrayList<>();
    GameGrid gameGrid = new GameGrid();
    GamePartition gamePartition = new GamePartition();
    private Menu() {
        setTitle("AP CS Arcade");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setResizable(false);
        setLocationRelativeTo(null);
    }
    public void initialize() {
        Container cp = getContentPane();

        ArrayList<Game> games = new ArrayList<>();

        games.add(new CardsOfFateGame());
        games.add(new ChessGame());
        games.add(new ConnectFourGame());
        games.add(new TetrisGame());
        games.add(new TicTacToeGame());
        games.add(new WordleGame());

        NUM_GAMES = games.size();
        gameGrid.setPreferredSize(new Dimension(640, 720));
        gamePartition.setPreferredSize(new Dimension(440, 720));
        for (int i = 0; i < NUM_GAMES; i++) {
            gameGrid.add(gameCard(games.get(i)));
        }
        cp.add(gameGrid, BorderLayout.WEST);
        cp.add(gamePartition, BorderLayout.EAST);
        setVisible(true);
    }
    private class GameGrid extends JPanel {
        public GameGrid() {
            setLayout(new GridLayout(3, 2));
        }
    }
    private class GamePartition extends JPanel {
        public GamePartition() {
            setLayout(new GridBagLayout());
            add(new JLabel("Please select a game to begin."));
        }
        public void adjustContents(Game game) {
            String name = game.getName();
            BufferedImage thumbnail = game.getThumbnail();
            String description = game.getDescription();
            removeAll();
            revalidate();
            setBorder(new EmptyBorder(20, 40, 20, 40));
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            Box box[] = new Box[4];
            for (int i = 0; i < 4; i++) {
                box[i] = Box.createVerticalBox();
                add(box[i]);
            }
            JLabel nameLabel = new JLabel("<html><font size='+2'>" + name + "</font></html>");
            nameLabel.setBorder(new EmptyBorder(0, 0, 15, 0));
            JLabel thumbnailLabel = new JLabel(new ImageIcon(thumbnail));
            JLabel descriptionLabel = new JLabel(description);
            descriptionLabel.setBorder(new EmptyBorder(10, 0, 10, 0));
            box[0].add(nameLabel);
            box[1].add(thumbnailLabel);
            box[2].add(descriptionLabel);
            JButton start = new JButton("Start");
            start.addActionListener(e -> {
                game.start();
            });
            box[3].setBorder(new EmptyBorder(25, 0, 0, 0));
            box[3].add(start);
            repaint();
        }
    }
    public JButton gameCard(Game game) {
        BufferedImage thumbnail = game.getThumbnail();
        String name = game.getName();
        Icon icon = new ImageIcon();
        try {
            icon = new ImageIcon(thumbnail.getScaledInstance(170, 120, Image.SCALE_DEFAULT));
        } catch (Exception e) {
            e.printStackTrace();
        }
        JButton b = new JButton(name, icon);
        b.setHorizontalTextPosition(JButton.CENTER);
        b.setVerticalTextPosition(JButton.BOTTOM);
        b.setContentAreaFilled(false);
        b.setFocusPainted(false);
        b.addActionListener(e -> {
            // grab necessary class
            Class a = null;
            for (Class c : main) {
                String[] parts = c.getName().split("\\.");
                String name_wdspaces = name.replaceAll("\\s", "");
                if (parts[2].equalsIgnoreCase(name_wdspaces)) {
                    a = c;
                    break;
                }
            }
            // grab description in class
            try {
                String description = game.getDescription();
                gamePartition.adjustContents(game);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        return b;
    }
}