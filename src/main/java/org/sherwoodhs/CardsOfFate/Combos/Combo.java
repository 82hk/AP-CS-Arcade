package org.sherwoodhs.CardsOfFate.Combos;

import org.sherwoodhs.CardsOfFate.Battle;
import org.sherwoodhs.CardsOfFate.Cards.*;
import org.sherwoodhs.CardsOfFate.Entities.Deck;

public abstract class Combo{
    protected Card[] cost;
    protected String name;
    public Combo (Card[] cost) {
        this.cost = cost;
    }
    protected void useCost(Deck deck, Battle battle){
        for(Card element: cost){
            deck.useCard(element, battle);
        }
    }
    public String costDescription(){
        String string = "Cost: ";
        for(Card element: cost){
            string += element;
            if (element.equals(cost[cost.length - 1])){
                string += ", ";
            } else{
                string += ".";
            }
        }
        return(string);
    }
    public void use(Deck deck, Battle battle){}
    public String effectDescription(){
        return ("");
    }
    public String getName(){
        return(name);
    }
    public String toString() {
        return(name);
    }
}