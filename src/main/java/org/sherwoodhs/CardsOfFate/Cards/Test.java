package org.sherwoodhs.CardsOfFate.Cards;

import org.sherwoodhs.CardsOfFate.Entities.Deck;
import org.sherwoodhs.CardsOfFate.Battle;

public class Test extends Card{
    public Test(int number) {
        super("Test" + number);
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
