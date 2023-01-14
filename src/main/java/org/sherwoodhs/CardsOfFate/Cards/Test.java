package org.sherwoodhs.CardsOfFate.Cards;

import org.sherwoodhs.CardsOfFate.Entities.Deck;

public class Test extends Card{
    public Test(int number) {
        super("Test" + number);
    }
    public void effect(Deck deck){
    }
    public String entry(){
        return("");
    }
}
