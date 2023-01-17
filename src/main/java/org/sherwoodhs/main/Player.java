package com.ken.genericspaceshooter.main;



import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

import com.andrewmatzureff.input.Movement;
import com.joshuacrotts.standards.StandardDraw;
import com.joshuacrotts.standards.StandardGameObject;
import com.joshuacrotts.standards.StandardID;
import com.joshuacrotts.standards.StdOps;

public class Player extends StandardGameObject{
    //Handles Collisions
    private SpaceInvader si;



    private Movement left, right;
    //Handles Movement
    public Player(double x,double y, SpaceInvader si){
        super(x,y,StandardID.Player);

        this.si = si;
        this.currentSprite = StdOps.loadImage("Resources/player.png");
        this.left = new Movement(this, null, -2, 0);
        this.right = new Movement(this, null, 0, 2);
        //Binds left and right movement to keys
        this.left.bind(si.keyboard, KeyEvent.VK_A);
        this.right.bind(si.keyboard, KeyEvent.VK_D);

        }
        public void tick(){

        }
        public void render(Graphics2D g2){

        g2.drawImage(this.currentSprite, (int) x, (int) y, null);

        }
    }

