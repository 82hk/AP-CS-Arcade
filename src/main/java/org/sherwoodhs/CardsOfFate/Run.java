package org.sherwoodhs.CardsOfFate;

import java.util.Scanner;

public class Run {
    CardsOfFate game;
    private static Dialouge dialouge = Dialouge.getInstance();

    public Run(CardsOfFate game){
        this.game = game;
    }

    public static void tutorial(){
        dialouge.runText(1);

    }
}
