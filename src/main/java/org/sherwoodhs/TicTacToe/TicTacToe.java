package org.sherwoodhs.TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe implements ActionListener {

    JFrame window = new JFrame();
    JPanel grid, titleField = new JPanel();
    JLabel title = new JLabel();
    JButton playAgain = new JButton();
    JButton[] gridSquare = new JButton[9];
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

    protected void DrawWindow() {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Tic Tac Toe");
        window.setResizable(false);
        window.setVisible(true);
        window.setSize(new Dimension(600,700));
        window.setLayout(new BorderLayout());

        grid.setLayout(new GridLayout(3,3));

        playAgain.setFont(new Font("Lato", Font.BOLD,10));
        playAgain.setText("Play again");

        title.setText(currentPlayer + "'s turn");
        title.setFont(new Font("Lato", Font.BOLD, 80));

        titleField.setLayout(new GridLayout(1,2));
        titleField.add(playAgain, BorderLayout.WEST);
        titleField.add(title);

        window.add(titleField, BorderLayout.NORTH);
        window.add(grid);


        for (int i = 0; i < 9; i++) { // create tic-tac-toe grid buttons
            gridSquare[i] = new JButton();
            gridSquare[i].setFont(new Font("Lato", Font.BOLD, 120));
            gridSquare[i].setBackground(Color.LIGHT_GRAY);
            gridSquare[i].setForeground(Color.black);
            gridSquare[i].addActionListener(this::actionPerformed);
            grid.add(gridSquare[i]);
        }
    }

    protected void SmallPause() {
        try { Thread.sleep(10); } catch (InterruptedException ex) { throw new RuntimeException(ex); }
    }

    protected void ClaimSquare(int i) {
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

    protected void CheckForWin() {
        for (int i = 0; i < winComboList.length; i++) {
            if ( (gridSquare[winComboList[i][0]].getText() == otherPlayer) && (gridSquare[winComboList[i][1]].getText() == otherPlayer) && (gridSquare[winComboList[i][2]].getText() == otherPlayer) ) {
                bingo = true;
            }
        }
    }

    protected void UpdateTitle() {
        if (bingo == true) {
            title.setText(otherPlayer + " wins!");
        } else {
            title.setText(currentPlayer + "'s turn");
        }
    }

    protected void ResetGrid() {
        bingo = false;
        currentPlayer = "X";
        otherPlayer = "O";
        for (int i = 0; i < 9; i++) {
            gridSquare[i].setBackground(Color.LIGHT_GRAY);
            gridSquare[i].setForeground(Color.black);
            gridSquare[i].setText("");
        }
    }

    protected void IntitializeGame() {
        DrawWindow();

    }

    public TicTacToe() {
        DrawWindow();
        ResetGrid();

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        SmallPause();

        if (e.getSource() == playAgain) {
            System.out.println("here!");
        }
        if (bingo == false && e.getSource() != playAgain) {

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