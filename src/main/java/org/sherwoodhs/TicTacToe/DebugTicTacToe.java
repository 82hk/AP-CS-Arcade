package org.sherwoodhs.TicTacToe;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DebugTicTacToe implements ActionListener {

    JFrame window = new JFrame();


    JPanel grid = new JPanel();
    JButton[] gridSquare = new JButton[9];


    JPanel headerField = new JPanel();
    JLabel currentPlayerText = new JLabel();


    JPanel optionField = new JPanel();
    JButton playAgainButton = new JButton();
    JButton exitButton = new JButton();
    String currentPlayer, otherPlayer, temp;
    boolean bingo;
    final int[][] winComboList = new int[][] {

            {0, 1, 2}, // 0
            {3, 4, 5}, // 1
            {6, 7, 8}, // 2

            {0, 3, 6}, // 3
            {1, 4, 7}, // 4
            {2, 5, 8}, // 5

            {0, 4, 8}, // 6
            {2, 4, 6}  // 7
    };

    private void DrawWindow() throws IOException {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Tic Tac Toe");
        window.setResizable(true);
        window.setVisible(true);
        window.setSize(new Dimension(600,700));
        window.setLayout(new BorderLayout());

        BufferedImage Octothorpe = ImageIO.read(new File("src/main/java/org/sherwoodhs/TicTacToe/resources/Octothorpe.png"));
        JButton button = new JButton();
        try {
            Image img = ImageIO.read(getClass().getResource("src/main/java/org/sherwoodhs/TicTacToe/resources/Octothorpe.png"));
            button.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        //grid.add(picLabel);

        playAgainButton.setFont(new Font("Lato", Font.BOLD,30));
        playAgainButton.setText("Play again");

        exitButton.setFont(new Font("Lato", Font.BOLD,30));
        exitButton.setText("Exit");

        currentPlayerText.setFont(new Font("Lato", Font.BOLD, 80));

        headerField.setLayout(new BorderLayout());

        optionField.setLayout(new BorderLayout());
        optionField.add(playAgainButton, BorderLayout.NORTH);
        optionField.add(exitButton, BorderLayout.SOUTH);

        headerField.add(optionField);
        headerField.add(currentPlayerText, BorderLayout.CENTER);

        window.add(optionField);


    }

    private void SmallPause() {
        try { Thread.sleep(10); } catch (InterruptedException ex) { throw new RuntimeException(ex); }
    }

    private void ClaimSquare(int i) {
        gridSquare[i].setText(currentPlayer);
        temp = currentPlayer;
        currentPlayer = otherPlayer;
        otherPlayer = temp;

        if (otherPlayer == "O") {
            gridSquare[i].setBackground(Color.CYAN);
            gridSquare[i].setForeground(Color.BLUE);
        } else {
            gridSquare[i].setBackground(Color.PINK);
            gridSquare[i].setForeground(Color.RED);
        }
    }

    private void CheckForWin() {
        for (int i = 0; i < winComboList.length; i++) {
            if ( (gridSquare[winComboList[i][0]].getText() == otherPlayer) && (gridSquare[winComboList[i][1]].getText() == otherPlayer) && (gridSquare[winComboList[i][2]].getText() == otherPlayer) ) {
                bingo = true;
            }
        }
    }

    private void UpdateTitle() {
        if (bingo == true) {
            currentPlayerText.setText(otherPlayer + " wins!");
        } else {
            currentPlayerText.setText(currentPlayer + "'s turn");
        }
    }

    private void ResetGrid() {
        bingo = false;
        currentPlayer = "X";
        otherPlayer = "O";
        for (int i = 0; i < 9; i++) {
            gridSquare[i].setBackground(Color.LIGHT_GRAY);
            gridSquare[i].setForeground(Color.black);
            gridSquare[i].setText("");
        }
        currentPlayerText.setText(currentPlayer + "'s turn");

    }

    public DebugTicTacToe() throws IOException { // MAIN METHOD
        DrawWindow();
        ResetGrid();
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        SmallPause();

        if (bingo == false && e.getSource() != playAgainButton) {

            for (int i = 0; i < 9; i++) {
                if (e.getSource() == gridSquare[i] && gridSquare[i].getText() != "X" && gridSquare[i].getText() != "O") {
                    SmallPause();
                    ClaimSquare(i);
                }
            }

            CheckForWin();
            SmallPause();
            UpdateTitle();

        }
    }
}