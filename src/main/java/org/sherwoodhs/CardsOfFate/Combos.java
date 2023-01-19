package org.sherwoodhs.CardsOfFate;

import org.sherwoodhs.CardsOfFate.Cards.*;
import java.util.ArrayList;

public class Combos{
    private Combos combos = new Combos();
    private Combos () {
    }
    public void checkCombos(ArrayList<Card> hand){
        if (hand.contains(Fool.getInstance())){
            
        }
    }
    public Combos getInstance(){
        return(combos);
    }
}