package org.sherwoodhs.CardsOfFate.Entities;

import org.sherwoodhs.CardsOfFate.Cards.*;

import java.util.Stack;

public class Player extends Deck {
    private static Card[] decks = {Fool.getInstance(), Fortune.getInstance(), new Swords(1), new Swords(2), new Wands(1), new Wands(2), new Coins(1), new Coins(2), new Goblets(1), new Goblets(2)};
    private static Player player = new Player("Player", decks);
    private Player (String name, Card[] deck){
        super (name,1, deck);
    }
    public static Player getInstance () {
        return (player);
    }
    public void resetPlayer(){
        deck.clear();
        discard.clear();
        hand.clear();
        Stack<Card> stack = new Stack<>();
        for(int i = 0; i < decks.length; i++) {
            stack.push(decks[i]);
        }
        // Makes the stack of cards a deck
        deck = stack;
        health = 1;

        shuffleDeck();
        initalDraw();
    }
}