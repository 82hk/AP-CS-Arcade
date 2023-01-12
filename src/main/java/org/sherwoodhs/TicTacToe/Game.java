// this is not my code, it's just to work out a framework from someone else - chris

package org.sherwoodhs.TicTacToe;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Game implements ActionListener {

    JFrame frame = new JFrame();
    //JPanel textPanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JLabel textField = new JLabel();
    JButton[] button = new JButton[9];
    int chance_flag = 0;
    Random random = new Random();
    boolean pl1_chance;

    JPanel textPanel = new JPanel();

    Game() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 900);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setTitle("Tic Tac Toe");
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        frame.setResizable(false);

        textField.setBackground(new Color(30, 192, 201));
        textField.setForeground(new Color(0, 0, 0));
        textField.setFont(new Font("Lato", Font.BOLD, 75));
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setText("Tic Tac Toe");
        textField.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.setBounds(0, 0, 800, 100);
        buttonPanel.setLayout(new GridLayout(3, 3));// setting layout of bt_pannel as gridlayout
        buttonPanel.setBackground(new Color(255, 0, 0, 255));
        for (int i = 0; i < 9; i++) {
            button[i] = new JButton();// creating object for each button element of array
            buttonPanel.add(button[i]);// adding each button to the pannel for buttons
            button[i].setFont(new Font("Lato", Font.BOLD, 120));
            button[i].setFocusable(false);
            button[i].addActionListener(this);
            //button[i].setText(String.valueOf(i));
            button[i].setSize(4,4);
        }

        textPanel.add(textField);
        frame.add(textPanel, BorderLayout.NORTH);
        frame.add(buttonPanel);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == button[i]) {
                if (pl1_chance) {
                    if (button[i].getText() == "") {
                        button[i].setForeground(new Color(255, 0, 0));
                        button[i].setText("X");
                        pl1_chance = false;
                        textField.setText("O turn");
                        chance_flag++;
                        button[i].setVisible(false);
                        //matchCheck();
                    }
                } else {
                    if (button[i].getText() == "") {
                        button[i].setForeground(new Color(0, 0, 255));
                        button[i].setText("O");
                        pl1_chance = true;
                        textField.setText("X turn");
                        chance_flag++;
                        button[i].setVisible(false);
                        //matchCheck();
                    }
                }
            }
        }
    }
}
