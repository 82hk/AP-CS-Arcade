package com.ken.genericspaceshooter.main;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.joshuacrotts.standards.StandardGameObject;
import com.joshuacrotts.standards.StandardHandler;
import com.joshuacrotts.standards.StandardID;

public class SpaceInvadersHandler extends StandardHandler {

    public SpaceInvadersHandler() {
        this.entities = new ArrayList<StandardGameObject>();
    }

    @Override
    public void tick() {
        for (int i = 0; i < this.entities.size(); i++) {
            this.entities.get(i).tick();
        }
    }

    @Override
    public void render(Graphics2D g2) {
        for (int i = 0; i < this.entities.size(); i++) {
            this.entities.get(i).render(g2);
        }
    }
}


