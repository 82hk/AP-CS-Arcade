package org.sherwoodhs.CardsOfFate.Entities;

import org.sherwoodhs.CardsOfFate.Cards.Card;

import java.util.*;

// Well, this is more aptly described as deck, hand, and discard 
public abstract class Deck extends Person{
        private Stack<Card> deck;
        private ArrayList<Card> hand = new ArrayList<Card>();
        private ArrayList<Card> discard = new ArrayList<Card>();
        
        public Deck (String name, int hp, Card[] deck) {
            super(name,hp);
           // Puts Cards in a stack
            Stack<Card> stack = new Stack<>();
            for(int i = 0; i < deck.length; i++) {
                stack.push(deck[i]);
            }
            // Makes the stack of cards a deck
            this.deck = stack;

            shuffleDeck();
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
                restock();
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

        //Returns Discard Pile
        public ArrayList<Card> getDiscard() {
            return(discard);
        }

        //Moves a specific Card from hand to discard
        public void discardCard(Card card){
            hand.remove(card);
            discard.add(card);
        }

        //Moves a specific card from discard to hand
        public void revive(Card card){
            discard.remove(card);
            addHandCard(card);
        }

        // Moves cards in discard to deck and shuffles deck
        private void restock(){
            if (deck.size() == 0) {
                int a = discard.size();
                for (int i = 0; i < a; i++) {
                    Card card = discard.get(0);
                    discard.remove(0);
                    deck.push(card);
                }
                shuffleDeck();
            }
        }
    }
