package org.sherwoodhs.CardsOfFate;


public class Main {
    public static void main(String[] args) {
        startUp();
    }
    public static void startUp(){
        new Run(new CardsOfFate());
        new Window();
    }
}