package org.sherwoodhs;

import com.google.common.reflect.ClassPath;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Menu extends JFrame {
    public static final Menu MENU = new Menu();
    private static final int SCREEN_WIDTH = 1080;
    private static final int SCREEN_HEIGHT = 720;
    private static int NUM_GAMES;
    ArrayList<Class> main = new ArrayList<>();
    GameGrid gameGrid = new GameGrid();
    GamePartition gamePartition = new GamePartition();
    private Menu() {
        setTitle("AP CS Arcade");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setResizable(false);
        setLocationRelativeTo(null);
    }
    public void initialize() {
        Container cp = getContentPane();
        ArrayList<Class> packages = new ArrayList<>();
        try {
            packages = getPackages();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // grab just the Main classes in every org.sherwoodhs package
        for (int i = 0; i < packages.size(); i++) {
            Class c = packages.get(i);
            String[] parts = c.getName().split("\\.");
            if (parts.length == 4 && parts[3].equals("Main")) {
                main.add(c);
            }
        }
        NUM_GAMES = main.size();
        gameGrid.setPreferredSize(new Dimension(640, 720));
        gamePartition.setPreferredSize(new Dimension(440, 720));
        for (int i = 0; i < NUM_GAMES; i++) {
            Class c = main.get(i);
            try {
                Method getName = c.getMethod("getName");
                Method getThumbnail = c.getMethod("getThumbnail");
                Object o = c.newInstance();
                String name = (String) getName.invoke(o);
                System.out.println(name);
                BufferedImage thumbnail = (BufferedImage) getThumbnail.invoke(o);
                gameGrid.add(gameCard(name, thumbnail));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        cp.add(gameGrid, BorderLayout.WEST);
        cp.add(gamePartition, BorderLayout.EAST);
        setVisible(true);
    }
    private class GameGrid extends JPanel {
        public GameGrid() {
            setLayout(new GridLayout(3, 2));
        }
    }
    private class GamePartition extends JPanel {
        public GamePartition() {
            setLayout(new GridBagLayout());
            add(new JLabel("Please select a game to begin."));
        }
        public void adjustContents(String name, BufferedImage thumbnail, String description) {
            removeAll();
            revalidate();
            setBorder(new EmptyBorder(20, 40, 20, 40));
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            Box box[] = new Box[4];
            for (int i = 0; i < 4; i++) {
                box[i] = Box.createVerticalBox();
                add(box[i]);
            }
            JLabel nameLabel = new JLabel("<html><font size='+2'>" + name + "</font></html>");
            nameLabel.setBorder(new EmptyBorder(0, 0, 15, 0));
            JLabel thumbnailLabel = new JLabel(new ImageIcon(thumbnail));
            JLabel descriptionLabel = new JLabel(description);
            descriptionLabel.setBorder(new EmptyBorder(10, 0, 10, 0));
            box[0].add(nameLabel);
            box[1].add(thumbnailLabel);
            box[2].add(descriptionLabel);
            JButton start = new JButton("Start");
            start.addActionListener(e -> {
                boolean isStart = true;
                if (start.getText().equals("Start")) {
                    start.setText("Stop");
                } else {
                    isStart = false;
                    start.setText("Start");
                }
                // grab necessary class
                Class a = null;
                for (Class c : main) {
                    String[] parts = c.getName().split("\\.");
                    String name_wdspaces = name.replaceAll("\\s", "");
                    if (parts[2].equalsIgnoreCase(name_wdspaces)) {
                        a = c;
                        break;
                    }
                }
                if (isStart) {
                    try {
                        Method startGame = a.getMethod("start");
                        Object o = a.newInstance();
                        startGame.invoke(o);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    try {
                        Method stopGame = a.getMethod("stop");
                        Object o = a.newInstance();
                        stopGame.invoke(o);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
            box[3].setBorder(new EmptyBorder(25, 0, 0, 0));
            box[3].add(start);
            repaint();
        }
    }
    public JButton gameCard(String name, BufferedImage thumbnail) {
        Icon icon = new ImageIcon();
        try {
            icon = new ImageIcon(thumbnail.getScaledInstance(170, 120, Image.SCALE_DEFAULT));
        } catch (Exception e) {
            e.printStackTrace();
        }
        JButton b = new JButton(name, icon);
        b.setHorizontalTextPosition(JButton.CENTER);
        b.setVerticalTextPosition(JButton.BOTTOM);
        b.setContentAreaFilled(false);
        b.setFocusPainted(false);
        b.addActionListener(e -> {
            // grab necessary class
            Class a = null;
            for (Class c : main) {
                String[] parts = c.getName().split("\\.");
                String name_wdspaces = name.replaceAll("\\s", "");
                if (parts[2].equalsIgnoreCase(name_wdspaces)) {
                    a = c;
                    break;
                }
            }
            // grab description in class
            try {
                Method getDescription = a.getMethod("getDescription");
                Object o = a.newInstance();
                String description = (String) getDescription.invoke(o);
                gamePartition.adjustContents(name, thumbnail, description);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        return b;
    }
    private ArrayList<Class> getPackages() throws IOException {

        ArrayList<Class> classes = new ArrayList<Class>();
        final ClassLoader loader = Thread.currentThread().getContextClassLoader();
        for (final ClassPath.ClassInfo info : ClassPath.from(loader).getTopLevelClasses()) {
            if (info.getName().startsWith("org.sherwoodhs.")) {
                final Class<?> c = info.load();
                classes.add(c);
            }
        }
        return classes;
    }
}