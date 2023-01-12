package org.sherwoodhs.CardsOfFate;

import java.util.Scanner;
import org.sherwoodhs.CardsOfFate.Menu;
public class Dialouge{
    public static Dialouge dialouge = new Dialouge();
    Scanner in = new Scanner(System.in);
    Menu menu = new Menu();
    private Dialouge (){

    }
    public static Dialouge getInstance() {
        return(dialouge);
    }

    public void runText(int type){
        String text = "";
        switch(type){
            case 1: text = tutorialOne(); break;
            case 2: text = tutorialTwo(); break;
            case 3: text = tutorialThree(); break;
            default: text = "error"; break;
        }
        System.out.println(text + " ➤");
        prompt();
    }
    private void prompt(){
        String input = in.nextLine();
        switch (input){
            case "menu":
            case "m": menu.menu(); break;
            default: break;
        }
    }
    private String tutorialOne() {
        return("Welcome to the tutorial. At any time, enter \"menu\" or \"m\" to access the pause menu. Press enter or anything else to advance through text.");
    };
    private String tutorialTwo() {
        return("The pause menu contains the encyclopedia of all card effects. You can also quit the game from there.");
    }
    private String tutorialThree() {
        return("Now that you understand how to use the UI, let's get into what a battle looks like.");
    }
}