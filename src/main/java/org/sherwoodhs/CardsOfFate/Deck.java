package CardsOfFate;

import CardsOfFate.cards.Card;

import java.util.*;

// Well, this is more aptly described as deck and hand
public class Deck {
        private Stack<Card> deck;
        private ArrayList<Card> hand = new ArrayList<Card>();
        public Deck (Stack<Card> deck) {
            this.deck = deck;
            shuffleDeck();
            drawCard(5);
        }

        // Returns value of card on top of the deck
        private Card getNext () {
            return(deck.peek());
        }

        // Adds a card to the deck
        public void addDeckCard (Card card) {
            deck.push(card);
        }

        // Adds a card to the hand
        public void addHandCard (Card card) {
            hand.add(card);
        }

        // Adds the top card from the deck to the hand n# of times
        public void drawCard (int n) {
            for(int i = 0; i < n; i++){
                addHandCard(deck.pop(););
            }
        }

        // Shuffles the deck
        public void shuffleDeck() {
            Collections.shuffle(deck);
        }
/*
        // Removes one instance of a specific card from deck
        public void searchCard(Card card) {
            for(int i = 0; i < deck.size(); i++) {
                if (deck.get(i).equals(card)) {
                    deck.remove(i);
                    break;
                }
            }
            shuffleDeck();
        }
*/

        // Prints Hand
        public void printHand(){
            System.out.println(hand);
        }
    }

