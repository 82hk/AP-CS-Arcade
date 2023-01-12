package org.sherwoodhs.CardsOfFate;


import org.sherwoodhs.CardsOfFate.Entities.Player;

public class Main {
    public static void main(String[] args) {
        startUp();
    }
    public static void startUp(){
        new Window();
        new Run(new CardsOfFate());
    }
}