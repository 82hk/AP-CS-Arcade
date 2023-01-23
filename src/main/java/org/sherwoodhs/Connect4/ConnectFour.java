package org.sherwoodhs.Connect4;

import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import static javax.swing.JFrame.*;

/*
Class is no longer a JFrame itself, but has a JFrame component
Class no longer contains main method
Program is run through separate main class by calling (initializing?) TestConnect4 class method
Added reset & exit buttons with methods
Button claiming is now disabled after a player wins, until the game is reset
Added boolean isWin for if player has won
Separated ActionListener code from Button Initializer (iterator) code.
ActionListener code can be found at bottom of class.
Divided up program into distinct methods
----------------------------------------------------------
NOTE: Buttons visually clear, but remain claimed even after pressing reset.
      Need to figure out button claiming logic.
      Also buttons move down *kind of*
      Need to do more testing to figure out how/why
 */

public class ConnectFour implements ActionListener {

    private JFrame frame;
    private JPanel panel;
    private JPanel title;
    private JButton resetButton;
    private JButton exitButton;
    private JButton[] button;
    private boolean isWin;
    private int column;
    private int k;
    private int[][] board; // 2D array
    private int currentPlayer;

    private void ConfigureGame() {

        frame = new JFrame();
        panel = new JPanel();
        title = new JPanel();
        resetButton = new JButton();
        exitButton = new JButton();
        button = new JButton[42];
        board = new int[6][7];
        currentPlayer = 1;
        isWin = false;

        resetButton.setBackground(Color.ORANGE);
        resetButton.setText("Reset");
        resetButton.addActionListener(this::actionPerformed);

        exitButton.setBackground(Color.RED);
        exitButton.setText("Exit");
        exitButton.addActionListener(this::actionPerformed);

        title.add(resetButton);
        title.add(exitButton);

        frame.setTitle("Connect 4"); // set window properties
        frame.setSize(800, 700);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());

        panel.setLayout(new GridLayout(6, 7));
        panel.setVisible(true);

        for (int i = 0; i < 42; i++) {
            button[i] = new JButton();
            button[i].addActionListener(this::actionPerformed);
            button[i].setVisible(true);
            //creates Jbutton Array
            // separated the ActionListener code (at bottom) from the button iterator
            panel.add(button[i]);
        }
        for (int i = 7; i < 42; i++) {
            button[i].setEnabled(false);
            // Makes all the buttons open to be clicked
        }

        frame.add(title, BorderLayout.NORTH);
        frame.add(panel);
    }

    private void ResetGame() {
        for (int i = 0; i < 42; i++) {
            button[i].setBackground(new JButton().getBackground());
            button[i].setEnabled(true);
        }
        for (int i = 7; i < 42; i++) {
            button[i].setEnabled(false);
        }
        for (int x = 5; x >= 0; x--) {
            for (int y = 6; y >= 0; y--) {
                board[x][y] = 0;
            }
        }
        isWin = false;
    }

    private void ExitGame() {
        frame.dispose();
    }

    private boolean CheckWin(int row, int column) {
        System.out.println("\n\nRow " + row);
        System.out.println("Column "+  column);
// check horizontal - WORKS
        //LEFT SIDE HORIZONTAL
        int count = 1; //variable to keep track of the number of pieces in a row
        for (int i = column - 1; i >= 0; i--) { //go through the columns to the left of the current column
            if (board[row][i] == currentPlayer) { //if the current piece is of the same player then increase count
                count++; //increment the count
            } else {
                break; //break if piece isnt the same as players
            }
        }
        //RIGHT SIDE HORIZONTAL
        for (int i = column + 1; i < 7; i++) { //go through the columns to the right of the current column
            if (board[row][i] == currentPlayer) { //if the current piece is of the same player then increase count
                count++; //increment the count
            } else {
                break; //break if piece isnt the same as players
            }
        }
        if (count >= 4) { // If its 4 in a row then the player wins
            return true;
        }

        // check vertical - WORKS NOW
        count = 0;
        for (int i = row; i <= 5; i++) { //go through the rows below current row
            if (board[i][column] == currentPlayer) { //if the current piece is of the same player then increase count
                count++; //increment the count
            } else {
                break; //break if piece isnt the same as players
            }
        }
        if (count >= 4) {
            return true;
        }

        // check diagonal (top-left to bottom-right) - DOES NOT WORK
        count = 1;
        for (int i = 1; i < 7; i++) { //go through the diagonal positions of the board
            int r = row + i; //the row of the diagonal position
            int c = column + i; //the column of the diagonal position
            if (r < 6 && c < 7 && board[r][c] == currentPlayer) { //if the current position is of the same player then increase count
                count++; //increment the count
            } else {
                break; //break if piece isnt the same as players
            }
        }

        count = 1;
        // check diagonal (top-right to bottom left)
        for (int i = 1; i < 7; i++) { //iterate through the diagonal positions of the board
            int r = row + i; //the row of the diagonal position
            int c = column - i; //the column of the diagonal position
            if (r < 6 && c >= 0 && board[r][c] == currentPlayer) { //if the current position is of the same player then increase count
                count++; //increment the count
            } else {
                break; //break if piece isnt the same as players
            }
        }

        count = 1;
        // check diagonal (bottom left to top-right)
        for (int i = 1; i < 7; i++) { //iterate through the diagonal positions of the board
            int r = row - i; //the row of the diagonal position
            int c = column + i; //the column of the diagonal position
            if (r < 6 && c >= 0 && board[r][c] == currentPlayer) { //if the current position is of the same player then increase count
                count++; //increment the count
            } else {
                break; //break if piece isnt the same as players
            }
        }

        count = 1;
        // check diagonal (bottom right to top-left)
        for (int i = 1; i < 7; i++) { //iterate through the diagonal positions of the board
            int r = row - i; //the row of the diagonal position
            int c = column - i; //the column of the diagonal position
            System.out.println("r " + r);
            System.out.println("c " + c);
            if (r < 6 && c >= 0 && board[r][c] == currentPlayer) { //if the current position is of the same player then increase count
                System.out.println("here");
                count++; //increment the count
            } else {
                break; //break if piece isnt the same as players
            }
        }

        if (count >= 4) {
            return true;
        }
        return false; // checkWin false if no win conditions met
    }

    private void ClaimSpot() {
    }

    private void UpdateBoard() {
        for (int row = 0; row < 5; row++) {
            for (int column = 0; column < 7; column++) {

                if (board[row + 1][column] == 0 && board[row][column] == currentPlayer) {
                    board[row + 1][column] = currentPlayer;
                    board[row][column] = 0;
                }

            }
        }
    }

    public ConnectFour() {
        ConfigureGame();
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == resetButton) {
            ResetGame(); //if pressed call the ResetGame method

        } else if (e.getSource() == exitButton) {
            ExitGame(); //if pressed call the ExitGame method

        } else if (isWin == false) {

            JButton b = (JButton) e.getSource();
            column = -1; //if game not over initialize the column as -1

            k = 0;
            for (int j = 0; j < 42; j++) { //go through the buttons on the board
                if (b == button[j]) { //if the button pressed is equal to the current button in the iteration
                    k = j; //set the value of k to the current button index
                    column = j % 7; //find the column of the button
                    break;
                }
            }

            for (int row = 0; row < 5; row++) { //go through the rows top to bottom
                if (board[row + 1][column] == 0) { //if the next row in the current column is empty increase k by 7 to mover to next column
                    k += 7;
                } else {
                    break; //if the next row is not empty break
                }
            }
            b = button[k]; // ERROR IS HAPPENING HERE. K TOO BIG.

            if (column != -1) {
                // Check if the column variable is not equal to -1.
                for (int row = 5; row >= 0; row--) {
                    // Iterate through the rows starting from the bottom row and going to the top
                    if (board[row][column] == 0) {
                        // Check if the space on the board array at the current row and column is empty
                        board[row][column] = currentPlayer;
                        // Assign the current player's value (1 or 2) to the current space on the board
                        b.setBackground(currentPlayer == 1 ? Color.RED : Color.YELLOW);
                        // Set the background color of the button that was clicked to match the current player's color
                        b.setEnabled(false);
                        // Disable the button that was clicked
                        if (CheckWin(row, column)) {
                            // Check if the current player has won the game
                            JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " wins!");
                            // If they win display a message dialog box saying they won
                            isWin = true;
                        } else {
                            currentPlayer = currentPlayer == 1 ? 2 : 1;
                        }
                        break;
                    }
                }
            }
        }

        //UpdateBoard();
        // below is purely for debugging purposes; prints out the 'board' 2D array
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println("");
        }
    }
}
