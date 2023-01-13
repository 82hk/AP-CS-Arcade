package org.sherwoodhs.TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DebugTicTacToe implements ActionListener {

    JFrame window = new JFrame();
    JPanel grid = new JPanel();
    JPanel titleField = new JPanel();
    JLabel title = new JLabel();
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

    DebugTicTacToe() {

        bingo = false;
        currentPlayer = "X";
        otherPlayer = "O";

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Tic Tac Toe");
        window.setResizable(true);
        window.setVisible(true);
        window.setSize(new Dimension(600,700));
        window.setLayout(new BorderLayout());

        grid.setLayout(new GridLayout(3,3));
        //grid.setPreferredSize(new Dimension(600,600));

        title.setText(currentPlayer + "'s turn");
        title.setFont(new Font("Lato", Font.BOLD, 120));
        //titleField.setPreferredSize(new Dimension(600,100));
        titleField.add(title);

        window.add(titleField, BorderLayout.NORTH);
        window.getContentPane().add(grid);


        for (int i = 0; i < 9; i++) {
            gridSquare[i] = new JButton();
            gridSquare[i].setFont(new Font("Lato", Font.BOLD, 120));
            gridSquare[i].setBackground(Color.LIGHT_GRAY);
            gridSquare[i].setForeground(Color.black);
            gridSquare[i].addActionListener(this::actionPerformed);
            grid.add(gridSquare[i]);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Did thing.");
    }
}