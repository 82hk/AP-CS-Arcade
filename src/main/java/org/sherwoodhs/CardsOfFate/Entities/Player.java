package org.sherwoodhs.CardsOfFate.Entities;

import org.sherwoodhs.CardsOfFate.Cards.*;

public class Player extends Deck {
    private static Player player = new Player("Player", Fool.getInstance(), Fortune.getInstance(), new Swords(1), new Swords(2), new Wands(1), new Wands(2), new Coins(1), new Coins(2), new Goblets(1), new Goblets(2));
    private Player (String name, Card... deck){
        super (name,1, deck);
    }
    public static Player getInstance () {
        return (player);
    }
}