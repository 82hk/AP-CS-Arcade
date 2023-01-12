package org.sherwoodhs.CardsOfFate.Cards;

public class Fortune extends Card{
    public Fortune(){
        super("Wheel Of Fortune");
    }
    public void effect(Deck deck){
        deck.drawCard(2);
        //deck.discardCard(Fortune());
    }
}
