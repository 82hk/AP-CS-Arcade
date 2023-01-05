package CardsOfFate;

import CardsOfFate.cards.Card;

import java.util.*;

public class Deck {
        private ArrayList<Card> deck;
        public ArrayList<Card> hand = new ArrayList<Card>();
        public Deck (ArrayList<Card> deck) {
            this.deck = deck;
            shuffleDeck();
            for (int i = 0; i < 5; i++) {
                drawCard();
            }
        }

        // Gets card on top of the deck
        private Card getNext () {
            return(deck.get(deck.size()-1));
        }

        // Adds a card to the deck
        public void addCard (Card card) {
            deck.add(card);
        }

        //Adds the top card from the deck to the hand
        public void drawCard () {
            Card a = getNext();
            deck.remove(deck.size()-1);
            hand.add(a);
        }
        public void shuffleDeck() {
            Collections.shuffle(deck);
        }
        public void searchCard(Card card) {
            for(int i = 0; i < deck.size(); i++) {
                if (deck.get(i).equals(card)) {
                    deck.remove(i);
                    break;
                }
            }
            shuffleDeck();
        }
    }

