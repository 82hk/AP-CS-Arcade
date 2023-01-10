package org.sherwoodhs.CardsOfFate.Entities;

import org.sherwoodhs.CardsOfFate.Cards.Card;

import java.util.*;

// Well, this is more aptly described as deck, hand, and discard 
public class Deck {
        private Stack<Card> deck;
        private ArrayList<Card> hand = new ArrayList<Card>();
        private ArrayList<Card> discard = new ArrayList<Card>();
        
        public Deck (Card[] deck) {
           // Puts Cards in a stack
            Stack<Card> stack = new Stack<>();
            for(int i = 0; i < deck.length; i++) {
                stack.push(deck[i]);
            }
            // Makes the stack of cards a deck
            this.deck = stack;

            shuffleDeck();
            initalDraw();
        }

        // Returns value of card on top of the deck
        public Card getNext () {
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
                addHandCard(deck.pop());
            }
        }

        // This is the inital draw of 5 cards everytime a game initiates.
        public void initalDraw() {
            drawCard(5);
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

        // Returns Hand
        public ArrayList<Card> getHand(){
            return(hand);
        }

        // Returns Deck
        public Stack<Card> getDeck(){
            return(deck);
        }

        public void discardCard(Card card){
            hand.remove(card);
            discard.add(card);
        }
        
    }

