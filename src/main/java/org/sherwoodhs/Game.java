package org.sherwoodhs;

abstract public class Game {

    abstract protected void initializeGame();
    abstract protected void startGame();
    abstract protected void endGame();

    public final void play() {
        initializeGame();
        startGame();
        endGame();
    }
}