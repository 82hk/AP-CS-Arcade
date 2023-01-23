package org.sherwoodhs.CardsOfFate;

import org.sherwoodhs.CardsOfFate.Entities.Player;
import org.sherwoodhs.Game;

public class CardsOfFateGame implements Game {
    private Window window;
    private Player player = Player.getInstance();
    public void CardsOfFate(){
    }
    public String getName() {
        return ("Cards Of Fate (Version Alpha Demo)");
    }
    public String getDescription() {
        return ("<html>A Card Rougelike game based off of Tarot Cards.<br><br> Made by Derek ;) </html>");
    }

    public void start() {
        window = new Window();
        player.resetPlayer();
        new Run();
    }
    public void stop() {
        window.closeFrame();
        // Return to main menu
    }
}