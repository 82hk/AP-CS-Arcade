package org.sherwoodhs.CardsOfFate;

import org.sherwoodhs.CardsOfFate.Cards.*;
import org.sherwoodhs.CardsOfFate.Combos.*;
import java.util.ArrayList;

public class Combinations{
    private static Combinations combos = new Combinations();
    private ArrayList<Combo> avaliable = new ArrayList<Combo>();
    private Combinations () {
    }
    private void checkCombos(ArrayList<Card> hand){
        avaliable.clear();
        if (hand.contains(Fool.getInstance())){
            if(hand.contains(Fortune.getInstance())){
                avaliable.add(FoolFortune.getInstance());
            }
            for (int i = 0; i < hand.size(); i++) { //checks every card in hand for match
                String h =  hand.get(i).toString();
                if (h.equalsIgnoreCase("The 1 of Swords")){
                    avaliable.add(FoolSword.getInstance());
                }
            }
        }
    }
    public ArrayList<Combo> getCombos(ArrayList<Card> hand){
        checkCombos(hand);
        return (avaliable);
    }
    public Combo[] getCombos2(ArrayList<Card> hand){
        checkCombos(hand);
        Combo[] array = new Combo[avaliable.size()];
        for (int i = 0; i < avaliable.size(); i++) {
            array[i] = avaliable.get(i);
        }
        return(array);
    }
    public static Combinations getInstance(){
        return(combos);
    }
}