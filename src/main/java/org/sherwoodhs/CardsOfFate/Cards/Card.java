package org.sherwoodhs.CardsOfFate.Cards;

import org.sherwoodhs.CardsOfFate.Entities.Deck;

public abstract class Card {
    protected String name;

    public Card (String name) {
        this.name = name;
    }
    abstract void effect(Deck deck);
    public String toString(){
        return(name);
    }
    public abstract String entry();
}