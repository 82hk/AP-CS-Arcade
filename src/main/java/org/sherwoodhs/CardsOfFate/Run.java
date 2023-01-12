package org.sherwoodhs.CardsOfFate;

public class Run {
    CardsOfFate game;
    Scanner in = new Scanner(System.in);
    private Dialouge dialouge = Dialouge.getInstance();
    Menu menu = new Menu();

    public Run(CardsOfFate game){
        this.game = game;
        initiate();
    }

    private void initiate(){

    }
    private void tutorial(){
        for(int i = 1; i >= 3; i++){
            dialouge.runText(1);
        }
    }
}
