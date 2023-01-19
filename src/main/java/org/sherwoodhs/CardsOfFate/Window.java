package org.sherwoodhs.CardsOfFate;

import org.sherwoodhs.CardsOfFate.Cards.Card;
import org.sherwoodhs.CardsOfFate.Entities.Enemy;
import org.sherwoodhs.CardsOfFate.Entities.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public class Window implements ActionListener, ItemListener{
    private Battle battle;
    private Player player = Player.getInstance();
    private static JFrame frame = new JFrame("Cards Of Fate");
    private static CardLayout crd =  new CardLayout();
    private static CardLayout crd2 = new CardLayout();
    //
    private static JPanel superPanel = new JPanel(crd);
    private JPanel text = new JPanel();
    private static JLabel words = new JLabel("Welcome to Cards of Fate!");
    //
    private JPanel battle = new JPanel();
    private static JLabel battleText = new JLabel("text");
    private static JLabel battleText2 = new JLabel("text");
    private static JLabel battleText3 = new JLabel("text");
    private static JLabel battleText4 = new JLabel("text");
    private static JLabel battleText5 = new JLabel("text");
    private static JLabel battleText6 = new JLabel("text");
    private static String[] choices = {"Use Card", "Use Combo", "End Turn"};
    private JComboBox battleChoices = new JComboBox(choices);
    private JComboBox options = new JComboBox(player.getHand2());
    private JButton use = new JButton("GO");

    Card c = player.getHand().get(0);
    private JLabel description = new JLabel(c.entry());
    private JLabel description2 = new JLabel(" ");

    //
    private JMenuBar menuBar = new JMenuBar();
    private JMenuItem menuItem1 = new JMenuItem("Quit?");
    private JMenu pauseMenu = new JMenu("⚙");

    private static Battle fight;

    private int box = 0;
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

                    battleChoices.setSelectedIndex(0);
                    battleChoices.addItemListener(this);
                    battleChoices.setEditable(false);
                    options.setSelectedIndex(0);
                    options.addItemListener(this);
                    options.setEditable(false);

                battle.setLayout(null);
                    battleText.setBounds(10,10,400,15);
                battle.add(battleText);
                    battleText2.setBounds(10,25,400,15);
                battle.add(battleText2);
                    battleText3.setBounds(10,40,400,15);
                battle.add(battleText3);
                    battleText4.setBounds(10,70,400,15);
                battle.add(battleText4);
                     battleText5.setBounds(10,85,400,15);
                battle.add(battleText5);
                    battleText6.setBounds(10,115,400,15);
                battle.add(battleText6);
                    battleChoices.setBounds(10,145,150,20);
                battle.add(battleChoices);
                    options.setBounds(180,145,150,20);
                battle.add(options);
                    use.setBounds(350, 145,60,20);
                    use.setFont(new Font("Arial", Font.BOLD,15));
                    use.setBackground(Color.RED);
                    use.addActionListener(this);
                battle.add(use);
                    description.setBounds(10,180,400,15);
                battle.add(description);
                    description2.setBounds(10,195,400,15);
                battle.add(description2);

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

        } else if (e.getSource() == use){
            String choice = (String) battleChoices.getSelectedItem();
            if (choice == "Use Card"){
                int a = options.getSelectedIndex();
                Card cards = player.getHand().get(a);
                player.useCard(cards);
            }
        }
    }
    public void itemStateChanged (ItemEvent e){
        String choice = (String) battleChoices.getSelectedItem();
        if (choice == "Use Card"){
            options.setVisible(true);
            int a = options.getSelectedIndex();
            Card cards = player.getHand().get(a);
            description.setText(cards.entry());
            description2.setText(" ");
        } else if (choice == "Use Combo"){
            options.setVisible(false);
        }
    }
    public static void setLabel(String string){
        words.setText(string);
    }
    public static void changeBattleText(String enemyName, int enemyHP, int enemyAtk, int enemyDfn, int playerHP, int playerAtk, int playerDfn, String otherText){
        battleText.setText("You are fighting a " + enemyName + ".");
        battleText2.setText("It has " + enemyHP + " hp remaining.");
        battleText3.setText("On it's next turn, it'll attack for " + enemyAtk + " and defend for " + enemyDfn + ".");
        battleText4.setText("You have " + playerHP + " hp remaining.");
        battleText5.setText("Next turn, you'll attack for " + playerAtk + " and defend for " + playerDfn + ".");
        battleText6.setText(otherText);
    }
    public static void changeCard(String string) {
        crd.show(superPanel, string);
    }
    public static void setBattle(Enemy enemy) {
        battle = new Battle(enemy);
        battle.endTurn();
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
