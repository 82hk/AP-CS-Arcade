package org.sherwoodhs.CardsOfFate.Entities;

import org.sherwoodhs.CardsOfFate.cards.Card;
import org.sherwoodhs.CardsOfFate.cards.Test;

public class Player extends Person {
    private static Player player = new Player("Player", new Test(1), new Test(2), new Test(3), new Test(4), new Test(5), new Test (6), new Test(7),new Test(8));
    private Player (String name, Card... deck){
        super (name,deck);

    }
    public static Player getInstance () {
        return (player);
    }
}