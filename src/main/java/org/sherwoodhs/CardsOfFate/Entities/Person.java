package org.sherwoodhs.CardsOfFate.Entities;

import org.sherwoodhs.CardsOfFate.Deck;
import org.sherwoodhs.CardsOfFate.cards.*;

import java.util.ArrayList;
import java.util.Stack;

public abstract class Person {
    protected String name = "";
    protected Deck deck;

    public Person(String name, Card[] deck) {
        this.name = name;

        // Puts Cards in a stack
        Stack<Card> stack = new Stack<>();
        for(int i = 0; i < deck.length; i++) {
            stack.push(deck[i]);
        }
        // Makes the stack of cards a deck
        this.deck = new Deck(stack);

    }

    // Returns Entity Name
    public String getName(){
        return(name);
    }

    // Gets the Deck
    private Stack<Card> getDeck(){
        return(deck.getDeck());
    }

    // Gets the Hand
    private ArrayList<Card> getHand(){
        return(deck.getHand());
    }

    // Prints Hand
    public void printHand(){
        System.out.println(deck.getHand());
    }
}