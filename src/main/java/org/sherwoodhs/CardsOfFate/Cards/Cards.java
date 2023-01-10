package org.sherwoodhs.CardsOfFate.Cards;

public abstract class Cards extends Card{
    protected int number;
    public Wands(int number, String type) {
        super("The " + number + " of " + type);
        ths.number = number; 
    }
}