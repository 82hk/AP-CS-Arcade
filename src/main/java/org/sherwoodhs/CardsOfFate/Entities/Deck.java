package org.sherwoodhs.CardsOfFate.Entities;

import org.sherwoodhs.CardsOfFate.Battle;
import org.sherwoodhs.CardsOfFate.Cards.Card;

import java.util.*;

import static org.sherwoodhs.CardsOfFate.Battle.updateText;

// Well, this is more aptly described as deck, hand, and discard 
public abstract class Deck extends Person{
        protected static Stack<Card> deck;
        protected static ArrayList<Card> hand = new ArrayList<Card>();
        protected static ArrayList<Card> discard = new ArrayList<Card>();
        
        public Deck (String name, int hp, Card[] deck) {
            super(name,hp);
           // Puts Cards in a stack
            Stack<Card> stack = new Stack<>();
            for(int i = 0; i < deck.length; i++) {
                stack.push(deck[i]);
            }
            // Makes the stack of cards a deck
            this.deck = stack;
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
        public static void drawCard (int n) {
            for(int i = 0; i < n; i++){
                if (!deck.isEmpty()) {
                    hand.add(deck.pop());
                    restock();
                }
            }
        }
        // Starts a Battle. Shuffles everything back in deck and draws new hand.
        public void startBattle(){
            int a = hand.size();
            for(int i = 0; i < a; i++){
                discardCard(hand.get(0));
            }
            restock();
            shuffleDeck();
            initalDraw();
        }
        // This is the inital draw of 5 cards everytime a game initiates.
        public void initalDraw() {
            drawCard(3);
        }

        // Shuffles the deck
        public static void shuffleDeck() {
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

        //Returns Hand in Array
        public Card[] getHand2(){
            Card[] array = new Card[hand.size()];
            for (int i = 0; i < hand.size(); i++) {
                array[i] = hand.get(i);
            }
            return (array);
        }

        // Returns Deck
        public Stack<Card> getDeck(){
            return(deck);
        }

        //Returns Discard Pile
        public ArrayList<Card> getDiscard() {
            return(discard);
        }
        public Card[] getDiscard2() {
            Card[] array = new Card[discard.size()];
            for (int i = 0; i < discard.size(); i++) {
                array[i] = discard.get(i);
            }
            return (array);
        }

        //Moves a specific Card from hand to discard
        public static void discardCard(Card card){
            for (int i = 0; i < hand.size(); i++) {
                if (hand.get(i).toString().equalsIgnoreCase(card.toString())){
                    hand.remove(i);
                }
            }
            discard.add(card);
        }

        public void useCard(Card card, Battle battle){
            card.effect(this, battle);
            discardCard(card);
            updateText(card.used());
        }

        //Moves a specific card from discard to hand
        public void revive(Card card){
            discard.remove(card);
            addHandCard(card);
        }

        // Moves cards in discard to deck and shuffles deck
        private static void restock(){
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

