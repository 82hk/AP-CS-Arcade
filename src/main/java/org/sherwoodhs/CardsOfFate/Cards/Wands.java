package org.sherwoodhs.CardsOfFate.Cards;

import org.sherwoodhs.CardsOfFate.Entities.Deck;
import org.sherwoodhs.CardsOfFate.Battle;

public class Wands extends Cards{
    public Wands(int number) {
        super(number, "Wands");
    }
    public void effect(Deck deck, Battle battle){
        battle.changePlayerDfn(number);
    }
    public String entry(){
        return("Defends for " + number + ".");
    }
    public String used(){
        return("You will defend for " + number + ".");
    }
}