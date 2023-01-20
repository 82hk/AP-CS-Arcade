package org.sherwoodhs.CardsOfFate;

import org.sherwoodhs.CardsOfFate.Cards.Emperor;
import org.sherwoodhs.CardsOfFate.Entities.Dummy;
import org.sherwoodhs.CardsOfFate.Entities.Enemy;

public class Dialouge{
    public static boolean paused = false;
    public static Dialouge dialouge = new Dialouge();
    private static int num = 0;
    private Dialouge (){

    }
    public static Dialouge getInstance() {
        return(dialouge);
    }

    public static void runText(int type){
        paused = false;
        num = type;
        String text = "";
        switch(type){
            case 1: text = tutorialOne(); break;
            case 2: text = tutorialTwo(); break;
            case 3: text = tutorialThree(); break;
            case 4: text = tutorialFour(); break;
            default: text = "error"; break;
        }
        Window.setLabel(text + " ➤");
    }
    public static void advanceText(){
            num++;
            runText(num);

    }
    private static String tutorialOne() {
        return("Welcome to the tutorial. At any time, use the top-right gear to access the options menu. Press enter or anything else to advance through text.");
        num = 1;
    };
    private static String tutorialTwo() {
        return("The options menu contains the encyclopedia of all card effects. You can also quit the game from there.");
    }
    private static String tutorialThree() {
        return("Now that you understand how to use the UI, let's get into what a battle looks like.");
    }
    private static String tutorialFour() {
        Enemy enemy = new Dummy();
        Window.setBattle(enemy);

        return("");
    }
}