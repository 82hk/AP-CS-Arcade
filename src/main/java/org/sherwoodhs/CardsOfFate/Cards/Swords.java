package org.sherwoodhs.CardsOfFate.Cards;

import org.sherwoodhs.CardsOfFate.Entities.Deck;
import org.sherwoodhs.CardsOfFate.Battle;

public class Swords extends Cards{
    public Swords(int number) {
        super(number, "Swords");
    }
    public void effect(Deck deck, Battle battle){
        battle.changePlayerAtk(number);
    }
    public String entry(){
        return("Attacks for " + number + ".");
    }
    public String used(){
        return("You will attack for " + number + ".");
    }
}