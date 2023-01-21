package org.sherwoodhs.CardsOfFate;

import org.sherwoodhs.CardsOfFate.Cards.Card;
import org.sherwoodhs.CardsOfFate.Combos.Combo;
import org.sherwoodhs.CardsOfFate.Entities.Enemy;
import org.sherwoodhs.CardsOfFate.Entities.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window implements ActionListener{
    private static Battle battle;

    private static int currentCard = 0;
    private static Player player;
    private static Combinations combinations = Combinations.getInstance();
    private static JFrame frame = new JFrame("Cards Of Fate");
    private static CardLayout crd =  new CardLayout();
    private static JPanel superPanel = new JPanel(crd);
    //
    private JPanel text = new JPanel();
    private static JLabel words = new JLabel("You've reached an area with no new content.");
    //
    private static JPanel battles = new JPanel();
    private static JLabel battleText = new JLabel("text");
    private static JLabel battleText2 = new JLabel("text");
    private static JLabel battleText3 = new JLabel("text");
    private static JLabel battleText4 = new JLabel("text");
    private static JLabel battleText5 = new JLabel("text");
    private static JLabel battleText6 = new JLabel("text");
    private static String[] choices = {"Use Card", "Use Combo", "End Turn"};
    private JComboBox battleChoices = new JComboBox(choices);

    private static JComboBox options;

    private static JComboBox combos;

    private JButton use = new JButton("GO");
    private static DefaultListModel listModel = new DefaultListModel();

    private static JList discard = new JList(listModel);
    private JLabel discardTitle = new JLabel("Discard Pile");
    private static JLabel description;
    private static JLabel description2 = new JLabel(" ");

    //
    private JPanel victory = new JPanel();
    private static JLabel vicLabel = new JLabel("");
    //
    private JPanel loss = new JPanel();
    private static JLabel lossLabel = new JLabel("You loss. Fool.");
    private JButton tryAgain = new JButton("Try Again?");
    //
    private JMenuBar menuBar = new JMenuBar();
    private JMenuItem menuItem1 = new JMenuItem("Quit?");
    private JMenu pauseMenu = new JMenu("⚙");
    public Window() {
        player = Player.getInstance();
        options = new JComboBox(player.getHand2());
        updateDiscards();
        description = new JLabel(" ");
        //description = new JLabel(player.getHand().get(0).entry());
        combos = new JComboBox(combinations.getCombos2(player.getHand()));

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
        menuBar.setBackground(new Color(213, 203, 203));
        menuBar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        text.setLayout(new BoxLayout(text, BoxLayout.Y_AXIS));
        text.setAlignmentX(Component.LEFT_ALIGNMENT);
        text.add(words);

        battleChoices.setSelectedIndex(0);
        battleChoices.addActionListener(this);
        battleChoices.setEditable(false);
        options.addActionListener(this);
        options.setEditable(false);

        battles.setLayout(null);
        battleText.setBounds(10,10,400,15);
        battles.add(battleText);
        battleText2.setBounds(10,25,400,15);
        battles.add(battleText2);
        battleText3.setBounds(10,40,400,15);
        battles.add(battleText3);
        battleText4.setBounds(10,70,400,15);
        battles.add(battleText4);
        battleText5.setBounds(10,85,400,15);
        battles.add(battleText5);
        battleText6.setBounds(10,115,400,15);
        battles.add(battleText6);
        battleChoices.setBounds(10,145,150,20);
        battles.add(battleChoices);
        options.setBounds(180,145,150,20);
        battles.add(options);
        combos.setBounds(180,145,150,20);
        combos.setVisible(false);
        battles.add(combos);
        use.setBounds(350, 145,60,20);
        use.setFont(new Font("Arial", Font.BOLD,15));
        use.setBackground(new Color(224, 135, 135));
        use.addActionListener(this);
        battles.add(use);
        description.setBounds(10,180,400,15);
        battles.add(description);
        description2.setBounds(10,195,400,15);
        battles.add(description2);
        discardTitle.setBounds(430,10,110,20);
        battles.add(discardTitle);
        discard.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        discard.setLayoutOrientation(JList.VERTICAL);
        discard.setBounds(430,40,110,300);
        discard.setVisibleRowCount(-1);
        battles.add(discard);
        //JScrollPane listScroller = new JScrollPane(discard);
        //listScroller.setPreferredSize(new Dimension(250, 80));

        victory.add(vicLabel);
        lossLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        loss.add(lossLabel);
        tryAgain.setAlignmentX(Component.CENTER_ALIGNMENT);
        tryAgain.addActionListener(this);
        loss.add(tryAgain);
        loss.setLayout(new BoxLayout(loss, BoxLayout.Y_AXIS));

        superPanel.add("TEXT",text);
        superPanel.add("BATTLE",battles);
        superPanel.add("VICTORY", victory);
        superPanel.add("LOSS", loss);
        changeCard("TEXT");
        currentCard = 0;

        Color back = new Color(220, 212, 191);
        text.setBackground(back);
        battles.setBackground(back);
        victory.setBackground(back);
        loss.setBackground(back);
        frame.add(superPanel);
        frame.addKeyListener(new enterKey());
        frame.setJMenuBar(menuBar);

        frame.setSize(1200,800);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setFocusable(true);
        updateOptions();
    }
    public void actionPerformed (ActionEvent e){
        if (e.getSource() == menuItem1){
            // Quit Game
        } else if (e.getSource() == use){
            String choice = (String) battleChoices.getSelectedItem();
            if (choice == "Use Card"){
                int a = options.getSelectedIndex();
                if (a != -1) {
                    Card cards = player.getHand().get(a);
                    player.useCard(cards, battle);
                    updateCombos();
                    updateOptions();
                    updateDiscards();
                }

            } else if(choice == "Use Combo"){
                int a = combos.getSelectedIndex();
                if (a!=-1){
                    Combo c = combinations.getCombos(player.getHand()).get(a);
                    c.use(player,battle);
                    updateOptions();
                    updateCombos();
                    updateDiscards();
                }

            }else if (choice == "End Turn"){
                battle.endTurn();
                battleChoices.setSelectedIndex(0);
                updateCombos();
                updateOptions();
                updateDiscards();
            }
            battle.checkDead();
        } else if (e.getSource() == tryAgain){
            //game.endGame();
            //game.startGame(); // temp
        } else if (e.getSource() == battleChoices){
            String choice = (String) battleChoices.getSelectedItem();
            if (choice == "Use Card"){
                updateOptions();

            } else if (choice == "Use Combo"){
                updateCombos();

            } else if (choice == "End Turn"){
                combos.setVisible(false);
                options.setVisible(false);
                description.setText("");
                description2.setText("");
            }
        } else if (e.getSource() == options){
            upOptions();
        } else if (e.getSource() == combos){
            upCombos();
        }
    }
    private static void upOptions(){
        if(options.getItemCount() != 0) {
            int a = options.getSelectedIndex();
            Card cards = player.getHand().get(a);
            description.setText(cards.entry());
            description2.setText(" ");
            options.setVisible(true);
        } else {
            description.setText("You have no cards to use.");
            description2.setText(" ");
            options.setVisible(false);
        }
    }
    private static void upCombos(){
        if (combos.getItemCount() != 0){
            int a = combos.getSelectedIndex();
            Combo c = combinations.getCombos(player.getHand()).get(a);
            description.setText(c.costDescription());
            description2.setText(c.effectDescription());
            combos.setVisible(true);
        } else {
            description.setText("There are no combos to use.");
            description2.setText(" ");
            combos.setVisible(false);
        }
    }
    private static void updateOptions(){
        combos.setVisible(false);
        options.removeAllItems();
        Card[] a = player.getHand2();
        for (Card element : a) {
            options.addItem(element);
        }
        upOptions();
    }
    private static void updateCombos(){
        options.setVisible(false);
        combos.removeAllItems();
        Combo[] a = combinations.getCombos2(player.getHand());
        for (Combo element : a) {
            combos.addItem(element);
        }
        upCombos();
    }
    private static void updateDiscards(){
        listModel.removeAllElements();
        Card[] a = player.getDiscard2();
        String[] b = new String[a.length];
        for (int i = 0; i < a.length; i++) {
            listModel.addElement(a[i].toString());
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
        battle.start();
        player.startBattle();
        updateCombos();
        updateOptions();
        changeCard("BATTLE");
        currentCard = 1;
    }
    public static void setVictory(Enemy enemy){
        changeCard("VICTORY");
        vicLabel.setText("You beat a " + enemy.getName() + ". Good job.");
        currentCard = 2;
    }
    public static void setLoss(Enemy enemy){
        changeCard("LOSS");
        lossLabel.setText("It's a very sad day. You lost to a " + enemy.getName() + ".");
        currentCard = 3;
    }
    public static void setText(int a){
        changeCard("TEXT");
        Dialouge.runText(a);
    }
    class enterKey implements KeyListener{
        public void keyTyped(KeyEvent e){

        }
        public void keyPressed(KeyEvent e){

            if(e.getKeyCode() == KeyEvent.VK_ENTER && currentCard == 0){

                Dialouge.advanceText();
            }
        }
        public void keyReleased(KeyEvent e){

        }
    }
    public void closeFrame() {
        frame.dispose();
    }
}