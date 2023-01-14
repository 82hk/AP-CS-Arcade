
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
        updateText(" ");
        //battle();

    }
    public void battle(){
        while(!player.isDead() && !enemy.isDead()) {
            enemyMove();
            // Player move
            player.drawCard(3);

            while (!turned) {
                while (!actioned) { //Wait for One action to be made
                    Thread.yield();
                }
                actioned = false;
            } // Wait for turn to be completed
            turned = false;
        } // Wait until One side is dead
    }
    private void updateText(String text) {
        Window.changeBattleText(enemy.getName(), enemy.getHp(), enemyMove[0],enemyMove[1],player.getHp(),playerMove[0],playerMove[1],text);
        System.out.println(2);
    }
    
    private void enemyMove() {
        // Enemy moveset
        int a = r.nextInt();
        if (a == 0){ //Attacks
           enemyMove[0] = enemy.getAtk();
        } else { //Defends
            enemyMove[1] = enemy.getDfn();
        }
    }

    private void checkDiscard(){
        System.out.println("Discard Pile:\n" + player.getDiscard());
    }
    private void checkCombos(){

    }
    private void checkHand(){
        
    }
    private void useCard(Card card){

    }
}

