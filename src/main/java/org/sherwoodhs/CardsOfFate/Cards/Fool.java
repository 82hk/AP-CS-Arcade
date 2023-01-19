package org.sherwoodhs.CardsOfFate.Cards;

import org.sherwoodhs.CardsOfFate.Entities.Deck;
import org.sherwoodhs.CardsOfFate.Battle;

public class Fool extends Card{
    private Fool fool = new Fool();
    private Fool(){
        super("The Fool");
    }
    public void effect(Deck deck, Battle battle){
    }
    public String entry(){
        return("You. Useless by itself.");
    }
    public Fool getInstance(){
        return(fool);
    }
    public String used(){
        return("You wasted a card.");
    }
}
