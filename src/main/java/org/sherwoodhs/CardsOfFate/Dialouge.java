package org.sherwoodhs.CardsOfFate;

public class Dialouge{
    public static Dialouge dialouge = new Dialouge();
    private Dialouge (){

    }
    public static Dialouge getInstance() {
        return(dialouge);
    }

    public String tutorialOne() {
        return("Welcome to the tutorial. At any time,enter \"menu\" or \"m\" to access the pause menu. Press enter or anything else to advance through text.");
    };
}