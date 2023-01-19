package org.sherwoodhs.CardsOfFate.Cards;

import org.sherwoodhs.CardsOfFate.Entities.Deck;
import org.sherwoodhs.CardsOfFate.Battle;

public class Goblets extends Cards{
    public Goblets(int number) {
        super(number, "Goblets");
    }
    public void effect(Deck deck, Battle battle){
        Player.getInstance().changeHealth(number);
    }
    public String entry(){
        return("Gain " + number + " hp.");
    }
    public String used(){
        return("You regained " + number + " hp.");
    }
}