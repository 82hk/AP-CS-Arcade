package org.sherwoodhs.CardsOfFate;

public class Encyclopedia {
    public static Encyclopedia encyclopedia = new Encyclopedia();
    private Encyclopedia (){

    }
    public static Encyclopedia getInstance() {
        return(encyclopedia);
    }
    public void openEncyclopedia() {
        //Provides a list of all cards and their effects
    }
}
