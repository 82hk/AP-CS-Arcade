package org.sherwoodhs.CardsOfFate;

import org.sherwoodhs.CardsOfFate.Entities.Player;
import org.sherwoodhs.Game;

public class CardsOfFate implements Game {
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

    public void start() {
        window = new Window(this);
        player.resetPlayer();
        new Run();
    }
    public void stop() {
        window.closeFrame();
        // Return to main menu
    }
}