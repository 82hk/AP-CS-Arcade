package org.sherwoodhs.CardsOfFate;

import org.sherwoodhs.CardsOfFate.Cards.*;
import org.sherwoodhs.CardsOfFate.Combos.*;
import java.util.ArrayList;

public class Combinations{
    private Combinations combos = new Combinations();
    private ArrayList<Combo> avaliable = new ArrayList<Combo>();
    private Combinations () {
    }
    private void checkCombos(ArrayList<Card> hand){
        avaliable.isEmpty();
        if (hand.contains(Fool.getInstance())){
            if(hand.contains(Fortune.getInstance())){
                avaliable.add(FoolFortune.getInstance());
            }
            if(hand.contains(new Swords(1))){
                avaliable.add(FoolSword.getInstance());
            }
        }
    }
    public ArrayList<Combo> getCombos(){
        return (avaliable);
    }
    public Combo[] getCombos2(){
        Card[] array = new Card[avaliable.size()];
        for (int i = 0; i < avaliable.size(); i++) {
            array[i] = avaliable.get(i);
        }
        return(array);
    }
    public Combinations getInstance(){
        return(combos);
    }
}