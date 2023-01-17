package org.sherwoodhs.TicTacToe;

import javax.swing.*;

public class DebugTicTacToe{

    JFrame window = new JFrame();

    private void initComponents(Main main){
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Sample");
        window.setVisible(true);
        window.setSize(1000,500);

        ImageIcon icon = new ImageIcon("src/main/java/org/sherwoodhs/TicTacToe/Octothorpe.png");
        JButton button = new JButton();
        button.setIcon(icon);
        button.setOpaque(true);
        button.setBorderPainted(false);
        window.add(button);
    }

    public DebugTicTacToe() {
        final Main main = new Main();
        SwingUtilities.invokeLater(new Runnable() {
           public void run() {
               initComponents(main);

           }
        } );
    }
}