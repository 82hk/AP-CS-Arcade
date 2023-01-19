package org.sherwoodhs.CardsOfFate;

import java.util.Scanner;

public class Run {
    private static Dialouge dialouge = Dialouge.getInstance();

    public Run(){
    }

    public static void tutorial(){
        dialouge.runText(1);

    }
}
