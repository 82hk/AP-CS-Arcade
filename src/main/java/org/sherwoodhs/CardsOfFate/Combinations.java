package org.sherwoodhs.CardsOfFate;

import org.sherwoodhs.CardsOfFate.Cards.*;
import java.util.ArrayList;

public class Combinations{
    private Combinations combos = new Combinations();
    private Combinations () {
    }
    public void checkCombos(ArrayList<Card> hand){
        if (hand.contains(Fool.getInstance())){
            
        }
    }
    public Combinations getInstance(){
        return(combos);
    }
}