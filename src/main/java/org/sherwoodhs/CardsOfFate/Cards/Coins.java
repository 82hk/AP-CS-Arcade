package org.sherwoodhs.CardsOfFate.Cards;

import org.sherwoodhs.CardsOfFate.Entities.Deck;
import org.sherwoodhs.CardsOfFate.Battle;

public class Coins extends Cards{
    public Coins(int number) {
        super(number, "Coins");
    }
    public void effect(Deck deck, Battle battle){
    }
    public String entry(){
        return("Get something " + number +".");
    }
    public String used(){
        return(" ");
    }
}