package edu.austral.dissis.starship;

import processing.core.PFont;
import processing.core.PGraphics;
import processing.core.PImage;

public class GameOverDrawer extends PGraphicsDrawer {
    private static final float IMAGE_SIZE = 200;
    //public static final int SQUARE_SIZE = 50;

    private final PImage image;

    public GameOverDrawer(PImage image) {
        this.image = image;
    }


    public float getImageCenter() {
        return IMAGE_SIZE / -2f;
    }


    public void draw(PGraphics graphics, GameObject gameObject) {
        GameOverScreen gameOverScreen= (GameOverScreen) gameObject;
        PFont f;
        PFont.loadFonts();
        f= graphics.parent.createFont("Arial",16,true);
        graphics.textFont(f,46);
        graphics.fill(225);
        graphics.text("GAME OVER",(int)gameObject.getPosition().getX()+200,(int)gameObject.getPosition().getY());
        graphics.text(" The WINNER  is "+gameOverScreen.getWinnerNickname()+", with the SCORE of "+gameOverScreen.getPoints() ,(int)gameObject.getPosition().getX()-100,(int)gameObject.getPosition().getY()+100);
    }
}
