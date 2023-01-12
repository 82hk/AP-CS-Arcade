package org.sherwoodhs.WordleGame.src.com.shs.Wordlegame;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Random;
import java.io.*;
import java.nio.file.*;

public class WordleGame {

    JFrame frame;
    JLabel title;
    JPanel layerPanel, contentPane, titlePanel;
    JTextField[] input = new JTextField[36];
    Dimension sz = Toolkit.getDefaultToolkit().getScreenSize();
    KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
    GridBagConstraints c = new GridBagConstraints();

    int guesses = 0;
    int outerBound = 5;
    String guess = "";

    public WordleGame() {
        frame = new JFrame("Wordle");

        layerPanel = new JPanel();
        contentPane = new JPanel();
        titlePanel = new JPanel();

        addTitle();

        layerPanel.setLayout(new GridBagLayout());
        c.fill = GridBagConstraints.HORIZONTAL;

        layerPanel.add(titlePanel, c);

        c.gridy = 1;
        c.ipady = 40;
        c.weightx = 1;
        layerPanel.add(contentPane, c);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
        frame.setContentPane(layerPanel);
        frame.setSize(sz.width/2, (sz.height/4)*3);
        frame.setResizable(false);

        contentPane.setLayout(new GridLayout(6, 6, sz.width/200, sz.height/200));
        contentPane.setBorder(BorderFactory.createEmptyBorder(sz.height/50, sz.width/20, sz.height/20, sz.width/20));

        createTextFields();
        addTextFields();
        input[0].requestFocusInWindow();
        changeEditableTextFieldsFalse();
        checkForInput();

    }
    public String GetWord(){
        String a;
        Random rand = new Random();
        int random_int = (int)Math.floor(Math.random() * (7637+1));
        File file = new File("6letters.txt");
        try {
            String line = Files.readAllLines(Paths.get("6letters.txt")).get(random_int);
            return line;
        }
        catch(IOException e){
            a = e.getMessage();
        }
        return "";
    }

    public void addTitle() {
        //titlePanel.setBackground(Color.BLUE);
        title = new JLabel("WORDLE");
        title.setFont(new Font("Serif", Font.BOLD, sz.width/20));
        //title.setForeground(Color.green);
        title.setHorizontalAlignment(JLabel.CENTER);
        titlePanel.setLayout(new BorderLayout());
        titlePanel.add(title, BorderLayout.CENTER);
    }

    public void createTextFields() {
        for (int i = 0; i < 36; i++) {
            input[i] = new JTextField();
            input[i].setFont(new Font("Verdana", Font.BOLD, sz.height/20));
            input[i].setHorizontalAlignment(JTextField.CENTER);
        }
    }

    public void addTextFields() {
        for (int i = 0; i < 36; i++) {
            contentPane.add(input[i]);
        }
    }

    public void setEditableTextFields(int guesses) {
        switch (guesses) {
            case 1: changeEditableTextFieldsTrue(6); return;
            case 2: changeEditableTextFieldsTrue(12); return;
            case 3: changeEditableTextFieldsTrue(18); return;
            case 4: changeEditableTextFieldsTrue(24); return;
            case 5: changeEditableTextFieldsTrue(30); return;
            //case 5: loseScreen();
        }
    }

    public void changeEditableTextFieldsFalse() {
        for (int i = 6; i < 36; i++) {
            input[i].setEditable(false);
        }
    }

    public void changeEditableTextFieldsTrue(int num) {
        for (int i = num; i < num + 6; i++) {
            input[i].setEditable(true);
            manager.focusNextComponent();
        }
        for (int i = num - 6; i < num; i++) {
            input[i].setEditable(false);
        }
    }

    public void checkForInput() {
        for (int i = 0; i < 36; i++) {
            int finalI = i;
            input[i].addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    char keyChar = e.getKeyChar();
                    if(!(Character.isLetter(keyChar))){
                        e.consume(); //only allows letters to be typed
                    } else {
                        if (Character.isLowerCase(keyChar)) {
                            if (input[finalI].getText().length() > 0) {
                                e.consume(); //prevents more than 1 letter being typed
                            }
                            e.setKeyChar(Character.toUpperCase(keyChar));//makes letter uppercase
                            if (guess.length() < 6) {
                                guess += keyChar;
                            }
                            if(input[outerBound].isFocusOwner()) {
                            } else {
                                manager.focusNextComponent(); //automatically moves to the next box
                            }
                        }
                    }
                }
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                        if(input[guesses*6].isFocusOwner()) {
                        } else {
                            if (input[finalI].getText().isEmpty()) {
                                manager.focusPreviousComponent();
                            }
                            if (guess.length() == 0) {
                            } else {
                                guess = guess.substring(0, guess.length() - 1);
                            }
                        }
                    } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        if (guess.length() == 6) {
                            setEditableTextFields(++guesses);
                            outerBound+=6;
                        }
                    }
                }
                @Override
                public void keyReleased(KeyEvent e) {
                }
            });
        }
    }
}
