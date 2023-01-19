package org.sherwoodhs.CardsOfFate.Combos;

import org.sherwoodhs.CardsOfFate.Card.*;
public abstract class Combo{
    protected Card[] cost;
    protected String name;
    public Combo (Card[] cost) {
        this.cost = cost;
    }
    private void useCost(Deck deck){
        for(Card element: cost){
            deck.useCard(element);
        }
    }
    public String costDescription(){
        String string = "Cost: ";
        for(Card element: cost){
            string += element;
            if (element.equals(cost[cost.length() - 1])){
                string += ", ";
            } else{
                string += ".";
            }
        }
        return(string);
    }
    abstract void use();
    abstract String effectDescription();
    public String getName(){
        return(name);
    }
}