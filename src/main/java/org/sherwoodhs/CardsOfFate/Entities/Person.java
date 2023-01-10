package org.sherwoodhs.CardsOfFate.Entities;

import org.sherwoodhs.CardsOfFate.Deck;
import org.sherwoodhs.CardsOfFate.Cards.*;

import java.util.ArrayList;
import java.util.Stack;

public abstract class Person extends Deck{
    protected String name;
    protected Deck deck;

    public Person(String name, Card[] deck) {
        super(deck);
        this.name = name; 
    }

    // Returns Entity Name
    public String getName(){
        return(name);
    }

    // Prints Hand
    public void printHand(){
        System.out.println(getHand());
    }
}