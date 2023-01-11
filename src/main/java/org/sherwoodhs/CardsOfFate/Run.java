package org.sherwoodhs.CardsOfFate;

public class Run {
    CardsOfFate game;
    private Dialouge dialouge = Dialouge.getInstance();

    public Run(CardsOfFate game){
        this.game = game;
        initiate();
    }

    private void initiate(){

    }
    private void tutorial(){
        dialouge.tutorialOne();
    }
}
