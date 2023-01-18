package org.sherwoodhs.CardsOfFate;

import org.sherwoodhs.CardsOfFate.Entities.Enemy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Window implements ActionListener{
    private static JFrame frame = new JFrame("Cards Of Fate");
    private static CardLayout crd =  new CardLayout();
    private static JPanel superPanel = new JPanel(crd);
    private JPanel text = new JPanel();
    private JPanel battle = new JPanel();
    private static JLabel words = new JLabel("Welcome to Cards of Fate!");
    private static JLabel battleText = new JLabel("text");
    private static JLabel battleText2 = new JLabel("text");
    private static JLabel battleText3 = new JLabel("text");
    private static JLabel battleText4 = new JLabel(" ");
    private static JLabel battleText5 = new JLabel("text");
    private static JLabel battleText6 = new JLabel("text");
    private static JLabel battleText7 = new JLabel(" ");
    private static JLabel battleText8 = new JLabel("text");


    private JMenuBar menuBar = new JMenuBar();
    private JMenuItem menuItem1 = new JMenuItem("Quit?");
    private JMenu pauseMenu = new JMenu("⚙");
    private JMenu enMenu = new JMenu("Encyclopedia");

    private static Battle fight;

    public Window() {
        //tutorial box
        int n = JOptionPane.showConfirmDialog(frame, "Do you want to skip the Tutorial?", " Starting Game", JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.NO_OPTION){
            Run.tutorial();
        } else{
            // Random new stuff
        }

        frame.getContentPane();
        frame.setDefaultLookAndFeelDecorated(true);

            text.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

                    pauseMenu.add(menuItem1);
                    menuItem1.addActionListener(this);
                    pauseMenu.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
                menuBar.add(pauseMenu);
                menuBar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

                text.setLayout(new BoxLayout(text, BoxLayout.Y_AXIS));
                text.setAlignmentX(Component.LEFT_ALIGNMENT);
                text.add(words);


                battle.setLayout(new BoxLayout(battle, BoxLayout.Y_AXIS));
                battle.setAlignmentX(Component.LEFT_ALIGNMENT);
                battle.add(battleText);
                battle.add(battleText2);
                battle.add(battleText3);
                battle.add(battleText4);
                battle.add(battleText5);
                battle.add(battleText6);
                battle.add(battleText7);
                battle.add(battleText8);

            superPanel.add("TEXT",text);
            superPanel.add("BATTLE",battle);
        frame.add(superPanel);
        frame.addKeyListener(new enterKey());
        frame.setJMenuBar(menuBar);

        frame.setSize(1200,800);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setFocusable(true);
    }
    public void actionPerformed (ActionEvent e){
        if (e.getSource() == menuItem1){

        }
    }
    public static void setLabel(String string){
        words.setText(string);
    }
    public static void changeBattleText(String enemyName, int enemyHP, int enemyAtk, int enemyDfn, int playerHP, int playerAtk, int playerDfn, String otherText){
        battleText.setText("You are fighting a " + enemyName + ".");
        battleText2.setText("It has " + enemyHP + " hp remaining.");
        battleText3.setText("On it's next turn, it'll attack for " + enemyAtk + " and defend for " + enemyDfn + ".");
        battleText5.setText("You have " + playerHP + " hp remaining.");
        battleText6.setText("Next turn, you'll attack for " + playerAtk + " and defend for " + playerDfn + ".");
        battleText8.setText(otherText);
    }
    public static void changeCard(String string) {
        crd.show(superPanel, string);
    }
    public static void setBattle(Enemy enemy) {
            changeCard("BATTLE");

    }
    class enterKey implements KeyListener{
        public void keyTyped(KeyEvent e){

        }
        public void keyPressed(KeyEvent e){

            if(e.getKeyCode() == KeyEvent.VK_ENTER){

                Dialouge.advanceText();
            }
        }
        public void keyReleased(KeyEvent e){

        }
    }
}
