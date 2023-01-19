package org.sherwoodhs.SpaceInvaders;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class SpaceInvadersKinda extends JFrame {
    private JPanel panel;
    private int x = 400; // initial x-coordinate of the blue square
    private int y = 550; // initial y-coordinate of the blue square
    private int size = 50; // size of the blue square and the red squares
    private int[] enemyX; // array to store the x-coordinates of the enemy squares
    private int enemyY; // y-coordinate of the enemy squares
    private int enemyDx = 10; // x-direction of the enemy squares
    private int numberOfEnemies = 5; // number of enemy squares
    private int projectileX; // x-coordinate of the projectile
    private int projectileY; // y-coordinate of the projectile
    private boolean projectileFired; // flag to check if the projectile has been fired
    private Timer moveTimer; // timer to move the enemies
    private Timer projectileTimer; // timer to move the projectile
    private Timer enemyProjectileTimer; // timer to fire the enemy projectiles
    private int[] enemyProjectileX; // array to store the x-coordinates of the enemy projectiles
    private int[] enemyProjectileY; // array to store the y-coordinates of the enemy projectiles
    private boolean[] enemyProjectileFired; // array to store flags to check if the enemy projectiles have been fired
    private Random random; // random number generator

    public SpaceInvadersKinda() {
        setTitle("Space Invaders");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLUE);
                g.fillRect(x, y, size, size);
                g.setColor(Color.RED);
                for (int i = 0; i < numberOfEnemies; i++) {
                    if (enemyX[i] != -1) { // check if the enemy is still alive
                        g.fillRect(enemyX[i], enemyY, size, size);
                    }
                }
                if (projectileFired) { // check if the projectile has been fired
                    g.setColor(Color.YELLOW);
                    g.fillRect(projectileX, projectileY, size / 4, size / 4);
                }
            }
        };
        //panel.setBackground(Color.BLACK);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_A) {
                    x -= 10;
                }
                if (e.getKeyCode() == KeyEvent.VK_D) {
                    x += 10;
                }
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    if (!projectileFired) { // check if the projectile has not been fired
                        projectileX = x + size / 2; // set the initial x-coordinate of the projectile
                        projectileY = y; // set the initial y-coordinate of the projectile
                        projectileFired = true; // set the flag to indicate that the projectile has been fired
                        projectileTimer.start(); // start the timer to move the projectile
                    }
                }
                panel.repaint();
            }
        });

        panel.setFocusable(true);
        panel.requestFocusInWindow();

        // initialize enemy positions and direction
        enemyX = new int[numberOfEnemies];
        enemyY = 50;
        for (int i = 0; i < numberOfEnemies; i++) {
            enemyX[i] = 50 + i * 100;
        }

        // create timer to move the enemies
        moveTimer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < numberOfEnemies; i++) {
                    enemyX[i] += enemyDx;
                }
                if (enemyX[numberOfEnemies - 1] + size >= 800) { // if the last enemy hits the right side
                    enemyDx = -10; // change direction to left
                    enemyY += size; // move down
                } else if (enemyX[0] <= 0) { // if the first enemy hits the left side
                    enemyDx = 10; // change direction to right
                    enemyY += size; // move down
                }
                panel.repaint();
            }
        });
        moveTimer.start();

        // create timer to move the projectile
        projectileTimer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                projectileY -= 10;
                for (int i = 0; i < numberOfEnemies; i++) {
                    if (projectileX >= enemyX[i] && projectileX <= enemyX[i] + size && projectileY >= enemyY && projectileY <= enemyY + size) {
                        enemyX[i] = -1;
                        projectileFired = false;
                        projectileTimer.stop();
                    }
                }
                if (projectileY < 0) {
                    projectileFired = false;
                    projectileTimer.stop();
                }
                panel.repaint();
            }
        });

        enemyProjectileX = new int[numberOfEnemies];
        enemyProjectileY = new int[numberOfEnemies];
        enemyProjectileFired = new boolean[numberOfEnemies];
        random = new Random();


        enemyProjectileTimer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int randomEnemy = random.nextInt(numberOfEnemies);
                if (!enemyProjectileFired[randomEnemy] && enemyX[randomEnemy] != -1) {
                    enemyProjectileX[randomEnemy] = enemyX[randomEnemy] + size / 2;
                    enemyProjectileY[randomEnemy] = enemyY + size;
                    enemyProjectileFired[randomEnemy] = true;
                }
            }
        });
        enemyProjectileTimer.start();


        Timer moveEnemyProjectileTimer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < numberOfEnemies; i++) {
                    if (enemyProjectileFired[i]) {
                        enemyProjectileY[i] += 5;
                        if (enemyProjectileX[i] >= x && enemyProjectileX[i] <= x + size && enemyProjectileY[i] >= y && enemyProjectileY[i] <= y + size) {

                            JOptionPane.showMessageDialog(null, "Game Over!");
                            System.exit(0);
                        } else if (enemyProjectileY[i] > 600) {
                            enemyProjectileFired[i] = false;
                        }
                    }
                }
                panel.repaint();
            }
        });
        moveEnemyProjectileTimer.start();

        add(panel);
    }

    public static void main(String[] args) {
        SpaceInvadersKinda invaders = new SpaceInvadersKinda();
        invaders.setVisible(true);
    }
}
