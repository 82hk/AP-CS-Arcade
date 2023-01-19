package org.sherwoodhs.CardsOfFate.Cards;

import org.sherwoodhs.CardsOfFate.Entities.Deck;
import org.sherwoodhs.CardsOfFate.Battle;

public class Chariot extends Card{
    private static Chariot chariot = new Chariot();
    private Chariot() {
        super("The Chariot");
    }
    public void effect(Deck deck, Battle battle){
    }
    public String entry(){
        return(" ");
    }
    public Chariot getInstance() {
        return chariot;
    }
    public String used(){
        return(" ");
    }
}
