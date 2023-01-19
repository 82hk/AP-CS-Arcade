
package org.sherwoodhs.CardsOfFate;

import org.sherwoodhs.CardsOfFate.Cards.Card;
import org.sherwoodhs.CardsOfFate.Entities.Enemy;
import org.sherwoodhs.CardsOfFate.Entities.Player;

import java.util.Random;

public class Battle{
    private Player player = Player.getInstance();
    private Encyclopedia encyclopedia = Encyclopedia.getInstance();
    private Enemy enemy;

    private Random r = new Random();

    // Atk / Dfn per turn
    private int[] enemyMove = {0,0};
    private int[] playerMove = {0,0};

    public boolean actioned = false;
    public boolean turned = false;

    public Battle (Enemy enemy){
        this.enemy = enemy;

    }
    public void endTurn(){
        enemy.changeHealth(playerMove[0] - enemyMove[1]);
        player.changeHealth(enemyMove[0] - playerMove[1]);
        enemyMove();
        player.drawCard();
        updateText("It's your turn.");
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

