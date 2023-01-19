package org.sherwoodhs.CardsOfFate.Cards;

import org.sherwoodhs.CardsOfFate.Entities.Deck;
import org.sherwoodhs.CardsOfFate.Battle;

public abstract class Card {
    protected String name;

    public Card (String name) {
        this.name = name;
    }
    abstract void effect(Deck deck, Battle battle);
    public String toString(){
        return(name);
    }
    public abstract String entry();
    public abstract String used();
}