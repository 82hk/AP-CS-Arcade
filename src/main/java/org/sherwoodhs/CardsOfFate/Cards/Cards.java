package org.sherwoodhs.CardsOfFate.Cards;

public abstract class Cards extends Card{
    protected int number;
    public Cards(int number, String type) {
        super("The " + number + " of " + type);
        this.number = number;
    }
}