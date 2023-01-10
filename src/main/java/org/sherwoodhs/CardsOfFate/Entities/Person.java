package org.sherwoodhs.CardsOfFate.Entities;

import org.sherwoodhs.CardsOfFate.Cards.*;


public abstract class Person{
    protected String name;
    protected int health;
    protected int maxHealth;

    public Person(String name, int maxHealth) {
        this.name = name;
        this.maxHealth = maxHealth;
        health = maxHealth;
    }

    // Returns Entity Name
    public String getName(){
        return(name);
    }

    public void changeHealth(int change){
        health += change;
        if (health < 0){
            health = 0;
        } else if(health > maxHealth){

        }
    }

    public boolean isDead() {
        if (health == 0){
            return true;
        }
        return false;
    }
}