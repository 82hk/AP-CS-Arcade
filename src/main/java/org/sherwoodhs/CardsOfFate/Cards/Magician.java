package org.sherwoodhs.CardsOfFate.Cards;

import org.sherwoodhs.CardsOfFate.Entities.Deck;
import org.sherwoodhs.CardsOfFate.Battle;

public class Magician extends Card{
    public Magician(){
        super("The Magician");
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
