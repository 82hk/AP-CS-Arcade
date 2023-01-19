package org.sherwoodhs.CardsOfFate;

import org.sherwoodhs.CardsOfFate.Entities.Enemy;
import org.sherwoodhs.CardsOfFate.Entities.Player;

import java.util.Random;

public class Battle{
    private static Player player = Player.getInstance();
    private static Enemy enemy;

    private Random r = new Random();

    // Atk / Dfn per turn
    private static int[] enemyMove = {0,0};
    private static int[] playerMove = {0,0};

    public boolean actioned = false;
    public boolean turned = false;

    public Battle (Enemy enemy){
        this.enemy = enemy;

    }
    public void endTurn(){
        int a = playerMove[0] - enemyMove[1];
        if (a > 0) {
            enemy.changeHealth(a);
        }
        a = enemyMove[0] - playerMove[1];
        if (a > 0) {
            player.changeHealth(a);
        }
        player.drawCard(1);
        start();
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

}

