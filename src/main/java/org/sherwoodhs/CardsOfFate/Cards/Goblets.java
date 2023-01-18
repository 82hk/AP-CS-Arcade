package org.sherwoodhs.CardsOfFate.Cards;

import org.sherwoodhs.CardsOfFate.Entities.Deck;

public class Goblets extends Cards{
    public Goblets(int number) {
        super(number, "Goblets");
    }
    public void effect(Deck deck){
    }
    public String entry(){
        return("Gain " + number + " hp.");
    }
}