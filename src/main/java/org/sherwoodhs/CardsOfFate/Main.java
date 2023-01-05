package CardsOfFate;

import CardsOfFate.cards.Card;
import CardsOfFate.cards.Test;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        startUp();
    }
    public static void startUp(){
        ArrayList<Card> startingDeck = new ArrayList<Card>();
        startingDeck.add(new Test(1));
        startingDeck.add(new Test(2));
        startingDeck.add(new Test(3));
        startingDeck.add(new Test(4));
        startingDeck.add(new Test(5));
        startingDeck.add(new Test(6));
        startingDeck.add(new Test(7));
        startingDeck.add(new Test(8));
        Deck player = new Deck(startingDeck);
        System.out.print(player.hand);

    }
}