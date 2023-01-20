package org.sherwoodhs.CardsOfFate;

import org.sherwoodhs.CardsOfFate.Entities.Player;
import org.sherwoodhs.Game;

public class CardsOfFate extends Game {
    private Window window;
    private Player player = Player.getInstance();
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
        player.startBattle();
        new Run();
        window = new Window(this);
        player.resetPlayer();

    }
    public void endGame() {
        window.closeFrame();
        // Return to main menu
    }
}