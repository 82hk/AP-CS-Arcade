package org.sherwoodhs.CardsOfFate;


import org.sherwoodhs.Game;

import java.awt.image.BufferedImage;

public class Main implements Game {
    public static void main(String[] args) {
        startUp();
    }
    public static void startUp(){
        CardsOfFate c = new CardsOfFate();
        //c.startGame();
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public String getName() {
        return "Cards of Fate";
    }

    @Override
    public String getDescription() {
        return "A card rogue-like based off of tarot cards.";
    }

    @Override
    public BufferedImage getThumbnail() {
        return Game.super.getThumbnail();
    }
}