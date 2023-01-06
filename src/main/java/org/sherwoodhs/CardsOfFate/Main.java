package CardsOfFate;

import CardsOfFate.cards.Card;
import CardsOfFate.cards.Test;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        startUp();
    }
    public static void startUp(){
        Stack<Card> startingDeck = new Stack<Card>();
        for(int i = 0; i < 20; i++){
            startingDeck.push(new Test(i));
        }
        Deck player = new Deck(startingDeck);
        player.printHand();

    }
}