package org.sherwoodhs.CardsOfFate;

import org.sherwoodhs.CardsOfFate.Entities.Player;
import org.sherwoodhs.Game;

public class CardsOfFate extends Game {
    private Player player = Player.getInstance();
    private Dialouge dialouge = Dialouge.getInstance();
    public CardsOfFate(){

    }
    public String getName() {
        return ("Cards Of Fate");
    }
    public String getDescription() {
        return ("A Card Rougelike game based off of Tarot Cards.");
    }
    public void initializeGame(){
    }
    public void startGame() {
        
    }
    public void endGame() {

    }
}