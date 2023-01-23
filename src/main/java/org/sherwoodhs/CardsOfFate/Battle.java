package org.sherwoodhs.CardsOfFate;

import org.sherwoodhs.CardsOfFate.Entities.Enemy;
import org.sherwoodhs.CardsOfFate.Entities.Player;

import java.util.Random;

public class Battle{
    private static Player player = Player.getInstance();
    public static Enemy enemy;

    // Atk / Dfn per turn
    private static int[] enemyMove = {0,0};
    private static int[] playerMove = {0,0};

    public Battle (Enemy enemy){
        this.enemy = enemy;
    }
    public void endTurn(){
        int a = playerMove[0] - enemyMove[1];
        if (a > 0) {
            enemy.changeHealth(-1 * a);
        }
        a = enemyMove[0] - playerMove[1];
        if (a > 0) {
            player.changeHealth(-1 * a);
        }
        player.drawCard(1);
        playerMove[0] = 0;
        playerMove[1] = 0;
        start();
        checkDead();
    }
    public void start(){
        enemyMove();
        updateText("It's your turn");
    }
    public static void updateText(String text) {
        Window.changeBattleText(enemy.getName(), enemy.getHp(), enemyMove[0],enemyMove[1],player.getHp(),playerMove[0],playerMove[1],text);
    }
    
    private void enemyMove() {
        // Enemy moveset
           enemyMove[0] = enemy.getAtk();
           enemyMove[1] = enemy.getDfn();
    }

    public void changePlayerAtk(int change){
        playerMove[0] += change;
    }
    public void changePlayerDfn(int change){
        playerMove[1] += change;
    }

    public static void checkDead(){
        if (player.getHp() == 0){
            Window.setLoss(enemy);
        } if (enemy.getHp() == 0){
            Window.setVictory(enemy);
        }
    }
}

