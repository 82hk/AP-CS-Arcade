package org.sherwoodhs.CardsOfFate.Cards;

import org.sherwoodhs.CardsOfFate.Entities.Deck;

public class Fortune extends Card{
    private static Fortune fortune = new Fortune();
    private Fortune(){
        super("Wheel Of Fortune");
    }
    public void effect(Deck deck){
        deck.drawCard(2);
        deck.discardCard(this);
    }
    public String entry(){
        return("");
    }
    public Fortune getInstance(){
        return (fortune);
    }
}
