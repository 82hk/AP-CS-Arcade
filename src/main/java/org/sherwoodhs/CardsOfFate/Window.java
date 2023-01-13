package org.sherwoodhs.CardsOfFate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Window {
    private static JFrame frame;

    private JPanel text;
    private static JLabel words;
    private JButton button;
    private JMenuBar menuBar = new JMenuBar();
    private MenuItem menuItem;
    private JMenu pauseMenu;

    public Window() {
        frame = new JFrame("Cards Of Fate");
        frame.getContentPane();
        frame.setDefaultLookAndFeelDecorated(true);
            text = new JPanel();
            pauseMenu = new JMenu("Access");
            text.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
                words = new JLabel("Welcome to Cards of Fate!");
                Dimension size = words.getPreferredSize();
                words.setBounds(10, 20, 900, size.height);

/*
                    menuItem = new MenuItem("Encyclopedia");
                    pauseMenu.add(menuItem);
                    menuItem = new MenuItem("Quit?");
                    pauseMenu.add(menuItem);

 */
                menuBar.add(pauseMenu);
                button = new JButton("X");
                button.setBounds(1140,10,30,30);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                    }
                });

        frame.add(words);
        frame.add(button);
        frame.add(text);
        frame.addKeyListener(new enterKey());
        frame.setJMenuBar(menuBar);

        frame.setSize(1200,800);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setFocusable(true);
    }
    public static void setLabel(String string){
        words.setText(string);
        frame.repaint();
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
