package org.sherwoodhs.CardsOfFate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Window {
    private static JFrame frame;

    private JPanel text;
    private JPanel pause;
    private JPanel superMain;

    private static JLabel words;
    private JButton button;

    public Window() {
        frame = new JFrame("Cards Of Fate");
        frame.getContentPane();
        frame.setDefaultLookAndFeelDecorated(true);
            text = new JPanel();
            pause = new JPanel();
            superMain = new JPanel();
            text.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
                words = new JLabel("Welcome to Cards of Fate!");
                Dimension size = words.getPreferredSize();
                words.setBounds(10, 20, size.width, size.height);
            pause.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
                button = new JButton("☰");
                button.setBounds(1130,10,60,60);
            text.setLayout(new BoxLayout(text, BoxLayout.Y_AXIS));
            pause.setLayout(new BoxLayout(text, BoxLayout.Y_AXIS));
                button.setAlignmentX(Component.RIGHT_ALIGNMENT);
            superMain.setLayout(new BoxLayout(text, BoxLayout.X_AXIS));
                words.setAlignmentX(Component.LEFT_ALIGNMENT);
            text.add(words);
            superMain.add(text);
            superMain.add(pause);
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
