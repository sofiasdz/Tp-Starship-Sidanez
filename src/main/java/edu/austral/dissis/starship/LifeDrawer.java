package edu.austral.dissis.starship;

import processing.core.PFont;
import processing.core.PGraphics;
import processing.core.PImage;

public class LifeDrawer extends PGraphicsDrawer {
    private static final float IMAGE_SIZE = 200;
    //public static final int SQUARE_SIZE = 50;

    private final PImage image;

    public LifeDrawer(PImage image) {
        this.image = image;
    }


    public float getImageCenter() {
        return IMAGE_SIZE / -2f;
    }


    public void draw(PGraphics graphics, GameObject gameObject) {
        Life life= (Life)gameObject;
        PFont f;
        PFont.loadFonts();
        f= graphics.parent.createFont("Arial",16,true);
        graphics.textFont(f,36);
        graphics.fill(225);
        System.out.println("here");
        System.out.println(life.getLives());
        graphics.text("Lives Remaining: "+life.getLives(),(int)gameObject.getPosition().getX(),(int)gameObject.getPosition().getY());
    }
}
