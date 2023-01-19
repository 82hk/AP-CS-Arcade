package org.sherwoodhs.TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe implements ActionListener {
    JFrame window = new JFrame();
    JPanel grid = new JPanel();
    JButton[] gridSquare = new JButton[9];
    JPanel headerField = new JPanel();
    JTextField currentPlayerText = new JTextField();
    JPanel optionField = new JPanel();
    JButton playAgainButton = new JButton();
    JButton exitButton = new JButton();
    String currentPlayer, otherPlayer, temp;
    boolean bingo;
    int j; // important. do not touch.
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

    private void DrawWindow(Main main) {

        ImageIcon hashIcon = new ImageIcon("src/main/java/org/sherwoodhs/TicTacToe/images/Octothorpe.png");
        ImageIcon exitIcon = new ImageIcon("src/main/java/org/sherwoodhs/TicTacToe/images/Exit Icon.png");
        ImageIcon replayIcon = new ImageIcon("src/main/java/org/sherwoodhs/TicTacToe/images/Replay Icon.png");

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Tic Tac Toe");
        window.setResizable(false);
        window.setVisible(true);
        window.setSize(new Dimension(600,700));
        window.setLayout(new BorderLayout());
        window.setBackground(Color.BLACK);

        grid.setLayout(new GridLayout(3,3));
        grid.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        grid.setBackground(Color.BLACK);
        grid.setOpaque(false);

        playAgainButton.setFont(new Font("Lato", Font.BOLD,30));
        playAgainButton.addActionListener(this::actionPerformed);
        playAgainButton.setBackground(Color.BLACK);
        playAgainButton.setForeground(Color.WHITE);
        playAgainButton.setBorderPainted(false);
        playAgainButton.setIcon(replayIcon);

        exitButton.setFont(new Font("Lato", Font.BOLD,30));
        exitButton.addActionListener(this::actionPerformed);
        exitButton.setBackground(Color.BLACK);
        exitButton.setForeground(Color.WHITE);
        exitButton.setBorderPainted(false);
        exitButton.setIcon(exitIcon);

        currentPlayerText.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        currentPlayerText.setFont(new Font("Lato", Font.BOLD, 80));
        currentPlayerText.setEditable(false);
        currentPlayerText.setBackground(Color.BLACK);
        currentPlayerText.setForeground(Color.PINK);
        currentPlayerText.setHorizontalAlignment(currentPlayerText.CENTER);

        headerField.setLayout(new BorderLayout());
        headerField.setBackground(Color.BLACK);

        optionField.setLayout(new BorderLayout());
        optionField.setBackground(Color.BLACK);
        optionField.add(playAgainButton, BorderLayout.WEST);
        optionField.add(exitButton, BorderLayout.EAST);

        headerField.add(optionField, BorderLayout.EAST);
        headerField.add(currentPlayerText, BorderLayout.CENTER);

        window.add(headerField, BorderLayout.NORTH);
        window.add(grid);


        for (int i = 0; i < 9; i++) {
            gridSquare[i] = new JButton();
            gridSquare[i].setFont(new Font("Lato", Font.BOLD, 120));
            gridSquare[i].addActionListener(this::actionPerformed);
            grid.add(gridSquare[i]);
        }

        bingo = false;
        currentPlayer = "X";
        otherPlayer = "O";

        for (int i = 0; i < 9; i++) {
            gridSquare[i].setBackground(Color.BLACK);
            gridSquare[i].setText("");
        }
        currentPlayerText.setText(currentPlayer + " turn");


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
            gridSquare[i].setForeground(Color.CYAN);
        } else if (otherPlayer == "X") {
            gridSquare[i].setForeground(Color.PINK);
        }
    }

    private void CheckForWin() {
        for (int i = 0; i < winComboList.length; i++) {
            if ( (gridSquare[winComboList[i][0]].getText() == otherPlayer) && (gridSquare[winComboList[i][1]].getText() == otherPlayer) && (gridSquare[winComboList[i][2]].getText() == otherPlayer) ) {
                bingo = true;
                if (otherPlayer == "X") {
                    j = i;
                    gridSquare[winComboList[i][0]].setForeground(Color.RED);
                    gridSquare[winComboList[i][1]].setForeground(Color.RED);
                    gridSquare[winComboList[i][2]].setForeground(Color.RED);
                } else if (otherPlayer == "O") {

                    gridSquare[winComboList[i][0]].setForeground(Color.BLUE);
                    gridSquare[winComboList[i][1]].setForeground(Color.BLUE);
                    gridSquare[winComboList[i][2]].setForeground(Color.BLUE);
                }
            }
        }
    }

    private void UpdateTitle() { // update whose turn it is
        if (bingo == true) { // if someone has won, declare it

            if (otherPlayer == "X") {
              currentPlayerText.setForeground(Color.RED);
            } else if (otherPlayer == "O") {
              currentPlayerText.setForeground(Color.BLUE);
            }
            currentPlayerText.setText(otherPlayer + " wins!");
            for (int i = 0; i < 9; i++) { // disable buttons on win
                gridSquare[i].setEnabled(false);
            }
            gridSquare[winComboList[j][0]].setEnabled(true);
            gridSquare[winComboList[j][1]].setEnabled(true);
            gridSquare[winComboList[j][2]].setEnabled(true);
        } else { // if no one has won, next player's turn. works because it comes after the check for win

            if (currentPlayer == "X") {
                currentPlayerText.setForeground(Color.PINK);
            } else if (currentPlayer == "O") {
                currentPlayerText.setForeground(Color.CYAN);
            }
            currentPlayerText.setText(currentPlayer + " turn");

        }
    }

    private void ResetGame() { // reset everything
        bingo = false;
        currentPlayer = "X";
        otherPlayer = "O";
        for (int i = 0; i < 9; i++) {
            gridSquare[i].setBackground(Color.BLACK);
            gridSquare[i].setText("");
            gridSquare[i].setEnabled(true);
        }
        currentPlayerText.setText(currentPlayer + " turn");
        UpdateTitle();

    }

    public TicTacToe() { // MAIN METHOD
        Main main = new Main();
        SwingUtilities.invokeLater(new Runnable() { public void run() { DrawWindow(main); } } );
    }


    @Override
    public void actionPerformed(ActionEvent e) { // click

        SmallPause();

        if (e.getSource() == exitButton) {
            System.exit(0);
        } else if (e.getSource() == playAgainButton) {
            ResetGame();
        } else if (bingo == false) {

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
