package org.sherwoodhs.CardsOfFate;

import java.util.Scanner;

public class Run {
    CardsOfFate game;
    private Dialouge dialouge = Dialouge.getInstance();

    public Run(CardsOfFate game){
        this.game = game;
        initiate();
    }

    private void initiate(){
        tutorial();
    }
    private void tutorial(){
            dialouge.runText(1);

    }
}
