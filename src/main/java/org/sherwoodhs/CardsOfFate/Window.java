package org.sherwoodhs.CardsOfFate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Window {
    private static JFrame frame;

    private JPanel text;
    private static JLabel words;
    public Window() {
        frame = new JFrame("Cards Of Fate");
        frame.getContentPane();
        frame.setDefaultLookAndFeelDecorated(true);
            text = new JPanel();
            text.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
                words = new JLabel("Welcome to Cards of Fate!");
                Dimension size = words.getPreferredSize();
                words.setBounds(10, 20, size.width, size.height);
            text.setLayout(new BoxLayout(text, BoxLayout.Y_AXIS));
                words.setAlignmentX(Component.LEFT_ALIGNMENT);
            text.add(words);
        frame.add(text);

        frame.setSize(1200,800);
        frame.setResizable(false);
        frame.setVisible(true);
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
