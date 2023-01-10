package org.sherwoodhs.CardsOfFate;

public class Dialouge{
    public static Dialouge dialouge = new Dialouge();
    private Dialouge (){

    }
    public static Dialouge getInstance() {
        return(dialouge);
    }

    public String tutorialOne() {
        return("Welcome to the tutorial ");
    };
}