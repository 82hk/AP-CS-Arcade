package org.sherwoodhs.CardsOfFate.Cards;

import org.sherwoodhs.CardsOfFate.Entities.Deck;

public class Fortune extends Card{
    public Fortune(){
        super("Wheel Of Fortune");
    }
    public void effect(Deck deck){
        deck.drawCard(2);
        //deck.discardCard(new Fortune());
    }
}
