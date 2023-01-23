package org.sherwoodhs.TicTacToe;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe implements ActionListener {
    JFrame window = new JFrame();
    Border border = new LineBorder(Color.BLACK);
    JPanel grid = new JPanel();
    JButton[] gridSquare = new JButton[9]; // tic tic toe grid squares
    JPanel headerField = new JPanel();
    JTextField currentPlayerText = new JTextField();
    JPanel optionField = new JPanel();
    JButton playAgainButton = new JButton();
    JButton exitButton = new JButton();
    String currentPlayer, otherPlayer, temp;
    boolean bingo; // is win?
    int j; // for highlighting the winning buttons
    int squaresClaimed; // for ties
    final int[][] winComboList = new int[][] { // every possible winning combination of squares

            {0, 1, 2}, // 0
            {3, 4, 5}, // 1
            {6, 7, 8}, // 2

            {0, 3, 6}, // 3
            {1, 4, 7}, // 4
            {2, 5, 8}, // 5

            {0, 4, 8}, // 6
            {2, 4, 6}  // 7
    };

    private void DrawWindow() { // initial configuration. only run once per launch

        // get image icons for buttons
        ImageIcon hashIcon = new ImageIcon("src/main/java/org/sherwoodhs/TicTacToe/images/Octothorpe.png");
        ImageIcon exitIcon = new ImageIcon("src/main/java/org/sherwoodhs/TicTacToe/images/Exit Icon.png");
        ImageIcon replayIcon = new ImageIcon("src/main/java/org/sherwoodhs/TicTacToe/images/Replay Icon.png");

        // JComponent configuration
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
        grid.setBorder(border);

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


        for (int i = 0; i < 9; i++) { // create buttons
            gridSquare[i] = new JButton();
            gridSquare[i].setFont(new Font("Lato", Font.BOLD, 120));
            gridSquare[i].addActionListener(this::actionPerformed);
            gridSquare[i].setBackground(Color.BLACK);
            gridSquare[i].setText(""); // empty until claimed. squares are claimed when their text is changed to X or O
            grid.add(gridSquare[i]);
        }

        bingo = false;
        currentPlayer = "X"; // X is red
        otherPlayer = "O"; // O is blue

        UpdateTitle();

    }

    private void SmallPause() {
        try { Thread.sleep(10); } catch (InterruptedException ex) { throw new RuntimeException(ex); }
    }

    private void ClaimSquare(int i) {
        gridSquare[i].setText(currentPlayer); // update text

        if (currentPlayer == "O") { // update color
            gridSquare[i].setForeground(Color.CYAN);
        } else if (currentPlayer == "X") {
            gridSquare[i].setForeground(Color.PINK);
        }

        temp = currentPlayer; // switch players
        currentPlayer = otherPlayer;
        otherPlayer = temp;

        squaresClaimed++; // claim counter for tie checking
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
                    j = i;
                    gridSquare[winComboList[i][0]].setForeground(Color.BLUE);
                    gridSquare[winComboList[i][1]].setForeground(Color.BLUE);
                    gridSquare[winComboList[i][2]].setForeground(Color.BLUE);
                }
            }
        }
    }

    private void DeclareTie() { // disables buttons, custom title update
        System.out.println("It's a tie");
        for (int i = 0; i < 9; i++) { // disable buttons on win
            gridSquare[i].setEnabled(false);
        }
        currentPlayerText.setForeground(Color.WHITE);
        currentPlayerText.setText("Tie :(");
    }

    private void UpdateTitle() { // update whose turn it is
        if (bingo) { // if someone has won, declare it

            if (otherPlayer == "X") { // update title color to winner's color
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

            if (currentPlayer == "X") { // update title color to currentPlayer's color
                currentPlayerText.setForeground(Color.PINK);
            } else if (currentPlayer == "O") {
                currentPlayerText.setForeground(Color.CYAN);
            }

            currentPlayerText.setText(currentPlayer + " turn");

        }
        if (squaresClaimed == 9 && !bingo) {
            DeclareTie();
        }
    }

    private void ResetGame() { // reset everything
        bingo = false;
        currentPlayer = "X";
        otherPlayer = "O";
        squaresClaimed = 0;
        for (int i = 0; i < 9; i++) { // reset gridSquares
            gridSquare[i].setBackground(Color.BLACK);
            gridSquare[i].setText("");
            gridSquare[i].setEnabled(true);
        }
        currentPlayerText.setText(currentPlayer + " turn");
        UpdateTitle();

    }

    public TicTacToe() { // MAIN METHOD
        SwingUtilities.invokeLater(new Runnable() { public void run() { DrawWindow(); } } ); // thank you, Trenton
    }


    @Override
    public void actionPerformed(ActionEvent e) { // click

        SmallPause();

        if (e.getSource() == exitButton) { // if click exit button
            window.dispose();

        } else if (e.getSource() == playAgainButton) { // if click replay button
            ResetGame();

        } else if (!bingo) { // if click anywhere else (probably on grid square)

            for (int i = 0; i < 9; i++) { // iterate through gridSquares, checks if clicked & if unclaimed
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
