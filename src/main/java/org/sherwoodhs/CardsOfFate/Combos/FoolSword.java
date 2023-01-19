package org.sherwoodhs.CardsOfFate.Combos;

import org.sherwoodhs.CardsOfFate.Card.Fool;
import org.sherwoodhs.CardsOfFate.Card.Swords;

import java.util.Random;

public class FoolSword{
    private FoolSword combo = new FoolSword(Fool.getInstance(),new Swords(1));
    private FoolSword(Card... cost){
        super(cost);
        name = "A Fool With a Sword.";
    }
    public FoolSword getInstance(){
        return(combo);
    }
    public void use(Deck deck, Battle battle){
        useCost(deck);
        Random r = new Random();
        int f = r.nextInt(4);
        if (f == 0){
            Player.getInstance.changeHealth(-1);
        } else {
            battle.changePlayerAtk(5);
        }
    }
    public String effectDescription(){
        return("Has a 25% chance of self-harm for 1 damage. Has a 75% chance of attacking for 5 damage.");
    }
}