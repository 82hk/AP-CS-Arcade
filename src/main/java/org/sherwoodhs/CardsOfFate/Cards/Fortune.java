package org.sherwoodhs.CardsOfFate.Cards;

import org.sherwoodhs.CardsOfFate.Entities.Deck;
import org.sherwoodhs.CardsOfFate.Battle;

public class Fortune extends Card{
    private static Fortune fortune = new Fortune();
    private Fortune(){
        super("Wheel Of Fortune");
    }
    public void effect(Deck deck, Battle battle){
        deck.drawCard(2);
    }
    public String entry(){
        return("Allows you to draw 2 cards when used.");
    }
    public static Fortune getInstance(){
        return (fortune);
    }
    public String used(){
        return("You drew 2 cards.");
    }
}
