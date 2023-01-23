package org.sherwoodhs.CardsOfFate.Cards;

import org.sherwoodhs.CardsOfFate.Entities.Deck;
import org.sherwoodhs.CardsOfFate.Battle;

public class Coins extends Cards{
    public Coins(int number) {
        super(number, "Coins");
    }
    public void effect(Deck deck, Battle battle){
        battle.enemy.changeHealth(-number);
    }
    public String entry(){
        return("Pay some assassins to directly attack for " + number + ".");
    }
    public String used(){
        return("You directly attacked for " + number + ".");
    }
}