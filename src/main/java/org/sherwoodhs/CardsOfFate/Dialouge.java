package org.sherwoodhs.CardsOfFate;

import java.util.Scanner;
import org.sherwoodhs.CardsOfFate.Menu;
public class Dialouge{
    public static Dialouge dialouge = new Dialouge();
    Scanner in = new Scanner(System.in);
    Menu menu = new Menu();
    static int num = 0;
    private Dialouge (){

    }
    public static Dialouge getInstance() {
        return(dialouge);
    }

    public static void runText(int type){
        num = type;
        String text = "";
        switch(type){
            case 1: text = tutorialOne(); break;
            case 2: text = tutorialTwo(); break;
            case 3: text = tutorialThree(); break;
            default: text = "error"; break;
        }
        Window.setLabel(text + " ➤");
    }
    public static void advanceText(){
        num++;
        runText(num);
    }
    private static String tutorialOne() {
        return("Welcome to the tutorial. At any time, use the top-right button to access the pause menu. Press enter or anything else to advance through text.");
    };
    private static String tutorialTwo() {
        return("The pause menu contains the encyclopedia of all card effects. You can also quit the game from there.");
    }
    private static String tutorialThree() {
        return("Now that you understand how to use the UI, let's get into what a battle looks like.");
    }
}