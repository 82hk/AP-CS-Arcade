package org.sherwoodhs.CardsOfFate.Cards;

public abstract class Card {
    protected String name;

    public Card (String name) {
        this.name = name;
    }
    public String toString(){
        return(name);
    }
}