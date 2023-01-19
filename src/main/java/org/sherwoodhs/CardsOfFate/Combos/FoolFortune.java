package org.sherwoodhs.CardsOfFate.Combos;

import org.sherwoodhs.CardsOfFate.Card.Fool;
import org.sherwoodhs.CardsOfFate.Card.Fortune;

import java.util.Random;

public class FoolFortune{
    private FoolFortune combo = new FoolFortune(Fool.getInstance(),Fortune.getInstance());
    private FoolFortune(Card... cost){
        super(cost);
        name = "A Fool's Gamble.";
    }
    public FoolFortune getInstance(){
        return(combo);
    }
    public void use(Deck deck, Battle battle){
        useCost(deck);
        Random r = new Random();
        int f = r.nextInt(3);
        int z = deck.getHand.size();
        if (f == 0 && z >= 1){
            int s = r.nextInt(z);
            deck.discardCard(deck.getHand.get(s));
        } else {
            deck.drawCard(3);
        }
    }
    public String effectDescription(){
        return("Has a 33% chance of discarding 1 card from hand. Has a 66% chance of drawing 3 cards.");
    }
}