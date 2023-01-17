package com.ken.genericspaceshooter.main;
import com.joshuacrotts.standards.StandardGame;
import com.joshuacrotts.standards.StandardHandler;


public class SpaceInvader  extends StandardGame{
    //collisions
    private StandardHandler sih;
    //Objects in game
    private Player player;
    public SpaceInvader (int w, int h){
        super (w,h,"Space Invader");

        this.sih = new SpaceInvadersHandler();
        this.consoleFPS = false;
        this.player = new Player(300, 720, this);
        this.sih.addEntity(this.player);
        this.StartGame();

    }
    public void tick(){
        StandardHandler.Handler(sih);

    }


    public void render(){
        StandardHandler.Handler(sih);

    }

    public static void main(String[] args) {
        new SpaceInvader(800,800);
    }
}
