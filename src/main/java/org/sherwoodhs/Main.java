package org.sherwoodhs;

import java.awt.*;

import static org.sherwoodhs.Menu.MENU;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            MENU.initialize();
        });
    }

}