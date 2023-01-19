package org.sherwoodhs.Connect4;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Connect4 extends JFrame {
    private JPanel panel;
    private JButton[] button;
    private int[][] board;
    private int currentPlayer;

    public Connect4() {
        setTitle("Connect 4");
        setSize(700, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new GridLayout(6, 7));
        button = new JButton[42];
        board = new int[6][7];
        currentPlayer = 1;

        for (int i = 0; i < 42; i++) {
            button[i] = new JButton();
            button[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton b = (JButton) e.getSource();
                    int column = -1;
                    for (int j = 0; j < 42; j++) {
                        if (b == button[j]) {
                            column = j % 7;
                            break;
                        }
                    }
                    if (column != -1) {
                        for (int row = 5; row >= 0; row--) {
                            if (board[row][column] == 0) {
                                board[row][column] = currentPlayer;
                                b.setBackground(currentPlayer == 1 ? Color.RED : Color.YELLOW);
                                b.setEnabled(false);
                                if (checkWin(row, column)) {
                                    JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " wins!");
                                    System.exit(0);
                                } else {
                                    currentPlayer = currentPlayer == 1 ? 2 : 1;
                                }
                                break;
                            }
                        }
                    }
                }
            });
            panel.add(button[i]);
        }

        add(panel);
    }

    private boolean checkWin(int row, int column) {
        // check horizontal
        int count = 1;
        for (int i = column - 1; i >= 0; i--) {
            if (board[row][i] == currentPlayer) {
                count++;
            } else {
                break;
            }
        }
        for (int i = column + 1; i < 7; i++) {
            if (board[row][i] == currentPlayer) {
                count++;
            } else {
                break;
            }
        }
        if (count >= 4) {
            return true;
        }
        // check vertical
        count = 1;
        for (int i = row - 1; i >= 0; i--) {
            if (board[i][column] == currentPlayer) {
                count++;
            } else {
                break;
            }
        }
        if (count >= 4) {
            return true;
        }

        // check diagonal (top-left to bottom-right)
        count = 1;
        for (int i = 1; i < 7; i++) {
            int r = row + i;
            int c = column + i;
            if (r < 6 && c < 7 && board[r][c] == currentPlayer) {
                count++;
            } else {
                break;
            }
        }
        for (int i = 1; i < 7; i++) {
            int r = row - i;
            int c = column - i;
            if (r >= 0 && c >= 0 && board[r][c] == currentPlayer) {
                count++;
            } else {
                break;
            }
        }
        if (count >= 4) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Connect4 game = new Connect4();
        game.setVisible(true);
    }
}
