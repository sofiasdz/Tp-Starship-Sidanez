package edu.austral.dissis.starship;

import processing.core.PConstants;
import processing.core.PGraphics;

public abstract class PGraphicsDrawer {
     abstract float getImageCenter();
     abstract void draw(PGraphics graphics, GameObject gameObject);
    public  float calculateRotation(GameObject gameObject) {
        return gameObject.getDirection().rotate(PConstants.PI / 2).getAngle();
    }

}
