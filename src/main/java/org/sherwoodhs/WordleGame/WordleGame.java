package org.sherwoodhs.WordleGame;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Arrays;
import java.io.*;
import java.nio.file.*;

public class WordleGame {

    private JFrame frame;
    private final JLayeredPane layerPanel = new JLayeredPane();
    private JPanel contentPane, letterPane;
    private final JTextField[] input = new JTextField[36];
    private final JTextField[] letterBoxes = new JTextField[26];
    private static final Dimension sz = Toolkit.getDefaultToolkit().getScreenSize();
    private static final KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
    private final GridBagConstraints c = new GridBagConstraints();
    private final GridBagConstraints d = new GridBagConstraints();
    private int guesses = 0;
    private int outerBound = 5;
    private static final Color yellow = new Color(200,182,83);
    private static final Color green = new Color(108,169,101);
    private final String word = getWord();
    private final String[] guess = new String[6];
    private static final String[][] key = {
            {"Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P"},
            {"A", "S", "D", "F", "G", "H", "J", "K", "L"},
            {"Z", "X", "C", "V", "B", "N", "M"},
    };

    public WordleGame() {
        createUI();
        createTextFields();
        changeEditableTextFieldsFalse();
        checkForInput();
    }

    public void createUI() {
        frame = new JFrame("Wordle");

        contentPane = new JPanel();
        JPanel titlePanel = new JPanel();
        letterPane = new JPanel();

        JLabel title = new JLabel("WORDLE");
        title.setFont(new Font("Serif", Font.BOLD, sz.width/20));
        title.setHorizontalAlignment(JLabel.CENTER);
        titlePanel.setLayout(new BorderLayout());
        titlePanel.add(title, BorderLayout.CENTER);

        layerPanel.setLayout(new GridBagLayout());
        c.fill = GridBagConstraints.HORIZONTAL;

        layerPanel.add(titlePanel, c);

        c.gridy = 1;
        c.ipady = 40;
        c.weightx = 1;
        layerPanel.add(contentPane, c);

        c.gridy = 2;
        c.ipady = 160;
        setLetterPane();
        layerPanel.add(letterPane, c);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
        frame.setContentPane(layerPanel);
        frame.setSize((sz.width/3)*2, (sz.height/6)*5);
        frame.setResizable(false);

        contentPane.setLayout(new GridLayout(6, 6, sz.width/200, sz.height/200));
        contentPane.setBorder(BorderFactory.createEmptyBorder(sz.height/50, sz.width/20, sz.height/20, sz.width/20));
    }
    public String getWord(){
        int random_int = (int)Math.floor(Math.random() * (450+1));
        try {
            return Files.readAllLines(Paths.get("RandomWords")).get(random_int);
        }
        catch(IOException e){
            System.out.println("Problem reading file.");
            System.err.println("IOException: " + e.getMessage());
        }
        return "";
    }
    public boolean checkWord(String[] g){
        File file = new File("6letters.txt");
        FileReader in;
        BufferedReader readFile;
        String word;

        StringBuilder strBuilder = new StringBuilder();
        for (String s : g) {
            strBuilder.append(s);
        }
        String guess = strBuilder.toString();

        try {
            in = new FileReader(file);
            readFile = new BufferedReader(in);

            while ((word = readFile.readLine()) != null) {
                if (word.equals(guess)) {
                    return true;
                }
            }
            readFile.close();
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist or could not be found.");
            System.err.println("FileNotFoundException: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Problem reading file.");
            System.err.println("IOException: " + e.getMessage());
        }
        return false;
    }
    public void createTextFields() {
        for (int i = 0; i < 36; i++) {
            input[i] = new JTextField();
            input[i].setFont(new Font("Verdana", Font.BOLD, sz.height/20));
            input[i].setHorizontalAlignment(JTextField.CENTER);
            contentPane.add(input[i]);
        }
        input[0].requestFocusInWindow();
    }

    public void setLetterPane() {

        JPanel letterPane1;
        d.anchor = GridBagConstraints.CENTER;
        d.weightx = 1;

        for (int i = 0; i < key.length; ++i) {
            letterPane1 = new JPanel(new GridBagLayout());
            d.gridy = 1;
            for (int j = 0; j < key[i].length; ++j) {
                char c = key[i][j].charAt(0);
                letterBoxes[(int)c-65] = new JTextField(key[i][j]);
                letterBoxes[(int)c-65].setColumns(4);
                letterBoxes[(int)c-65].setFont(new Font("Verdana", Font.BOLD, sz.height / 50));
                letterBoxes[(int)c-65].setHorizontalAlignment(JTextField.CENTER);
                letterBoxes[(int)c-65].setEditable(false);
                letterBoxes[(int)c-65].setBackground(new Color(212,214,218));
                letterPane1.add(letterBoxes[(int)c-65]);
            }
            letterPane.add(letterPane1, d);
        }
    }

    public void setLetterPaneColor(String s, Color c) {
        for (int i = 0; i < key.length; ++i) {
            for (int j = 0; j < key[i].length; ++j) {
                if (s.toUpperCase().equals(key[i][j])) {
                    letterBoxes[(int)key[i][j].charAt(0)-65].setBackground(c);
                    letterBoxes[(int)key[i][j].charAt(0)-65].setForeground(Color.white);
                }
            }
        }
    }
    public void setEditableTextFields(int guesses, String[] s) {
        switch (guesses) {
            case 1: changeEditableTextFieldsTrue(6, s); return;
            case 2: changeEditableTextFieldsTrue(12, s); return;
            case 3: changeEditableTextFieldsTrue(18, s); return;
            case 4: changeEditableTextFieldsTrue(24, s); return;
            case 5: changeEditableTextFieldsTrue(30, s); return;
            case 6: setWrongText(36, s);
        }
    }

    public void changeEditableTextFieldsFalse() {
        for (int i = 6; i < 36; i++) {
            input[i].setEditable(false);
        }
    }

    public void changeEditableTextFieldsTrue(int num, String[] s) {
        for (int i = num; i < num + 6; i++) {
            input[i].setEditable(true);
            manager.focusNextComponent();
        }
        setWrongText(num, s);
    }

    public void setWrongText(int num, String[] sa) {
        for (int i = num - 6; i < num; i++) {
            input[i].setEditable(false);
            input[i].setBackground(Color.darkGray);
            input[i].setForeground(Color.white);
        }
        for (String s: sa)
            setLetterPaneColor(s, Color.darkGray);
    }

    public void compareActualWord(int guesses, String[] s) {
        int match = 0;
        char[] phrase = word.toCharArray();
        for (int i = 0; i < 6; i++) {
            for(int j = 0; j < 6; j++) {
                if ((guess[i]).equals(String.valueOf(phrase[j]))) {
                    input[i+6*guesses].setBackground(yellow);
                    setLetterPaneColor(s[i], yellow);
                    guess[i] = "";
                    phrase[j] = Character.MIN_VALUE;
                }
            }
        }
        for (int i = 0; i < 6; i++) {
            if ((guess[i]).equals(String.valueOf(phrase[i]))) {
                input[i+6*guesses].setBackground(green);
                setLetterPaneColor(s[i], green);
                guess[i] = "";
                phrase[i] = Character.MIN_VALUE;
                match++;
            }
        }
        if (match == 6) {
            winScreen();
            changeEditableTextFieldsFalse();
        } else {
            if (guesses == 5) {
                loseScreen();
            }
        }
    }

    public int checkElementInArray() {
        int counter = 0;
        for (String s : guess)
            if (s != null)
                counter++;
        return counter;
    }

    public void winScreen() {
        createDialog("You won!");
    }

    public void loseScreen() {
        createDialog("You lost!");
    }

    public void createDialog(String s) {
        JPanel pane = new JPanel();
        JFrame f = new JFrame();

        JLabel l = new JLabel(s);
        l.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        l.setFont(new Font("Serif", Font.BOLD, sz.width/20));

        JLabel w = new JLabel("The word was " + "\"" + word + "\"");
        w.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        w.setFont(new Font("SansSerif", Font.BOLD, sz.width/30));
        JDialog d = new JDialog(f , s, true);
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

        JButton b = new JButton ("Play Again");
        b.setAlignmentX(JButton.CENTER_ALIGNMENT);

        b.addActionListener(e -> {
            new WordleGame();
            d.setVisible(false);
            frame.dispose();
        });
        b.setPreferredSize(new Dimension(800, 300));
        b.setFont(new Font("SansSerif", Font.BOLD, sz.width/50));

        JButton p = new JButton ("Close");
        p.setAlignmentX(JButton.CENTER_ALIGNMENT);

        p.addActionListener(e -> {
            d.setVisible(false);
            frame.dispose();
            System.exit(0);
        });
        p.setPreferredSize(new Dimension(800, 300));
        p.setFont(new Font("SansSerif", Font.BOLD, sz.width/50));


        pane.add(l);
        pane.add(w);
        pane.add(Box.createVerticalGlue());
        pane.add(Box.createVerticalGlue());
        pane.add(b);
        pane.add(p);
        pane.setBorder(BorderFactory.createEmptyBorder(200, 20, 200, 20));

        d.setSize(sz.width/2, (sz.height/4)*3);
        d.add(pane);
        d.setVisible(true);
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
                        if (input[finalI].getText().length() > 0) {
                            e.consume(); //prevents more than 1 letter being typed
                            if(input[outerBound].isFocusOwner()) {
                            } else {
                                manager.focusNextComponent(); //automatically moves to the next box
                                return;
                            }
                        }
                        if (Character.isLowerCase(keyChar)) {
                            e.setKeyChar(Character.toUpperCase(keyChar)); //makes text uppercase
                        }
                        if (checkElementInArray() < 6) {
                            guess[finalI-6*guesses] = String.valueOf((Character.toLowerCase(keyChar)));
                        }
                        if(input[outerBound].isFocusOwner()) {
                        } else {
                            manager.focusNextComponent(); //automatically moves to the next box
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
                            if (checkElementInArray() == 0) {
                            } else {
                                guess[finalI-6*guesses] = null;
                            }
                        }
                    } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        if (checkElementInArray() == 6) {
                            if (checkWord(guess)) {
                                setEditableTextFields(++guesses, guess);
                                outerBound+=6;
                                compareActualWord(guesses-1, guess);
                                Arrays.fill(guess, null);
                            }
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