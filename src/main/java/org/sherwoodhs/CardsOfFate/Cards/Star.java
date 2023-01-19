package org.sherwoodhs.CardsOfFate.Cards;

import org.sherwoodhs.CardsOfFate.Entities.Deck;
import org.sherwoodhs.CardsOfFate.Battle;

public class Star extends Card{
    public Star() {
        super("The Star");
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
