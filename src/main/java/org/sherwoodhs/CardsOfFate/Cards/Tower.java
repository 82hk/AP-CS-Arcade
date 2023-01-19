package org.sherwoodhs.CardsOfFate.Cards;

import org.sherwoodhs.CardsOfFate.Entities.Deck;
import org.sherwoodhs.CardsOfFate.Battle;

public class Tower extends Card{
    public Tower() {
        super("The Tower");
    }
    public void effect(Deck deck, Battle battle){
    }
    public String entry(){
        return("");
    }
    public String used(){
        return(" ");
    }
}
