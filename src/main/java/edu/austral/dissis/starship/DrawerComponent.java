package edu.austral.dissis.starship;

import processing.core.PGraphics;
import processing.core.PImage;



public class DrawerComponent implements Drawer {
    PGraphics pGraphics;
    PGraphicsDrawer drawer;
    PImage image;

    @Override
    public void draw(GameObject gameObject) {

        drawer.draw(pGraphics,gameObject);
    }

    public DrawerComponent(PGraphics pGraphics, PGraphicsDrawer drawer) {
        this.pGraphics = pGraphics;
        this.drawer = drawer;
    }
}
