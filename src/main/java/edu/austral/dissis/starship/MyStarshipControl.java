package edu.austral.dissis.starship;

import processing.core.PConstants;

import java.util.Set;

public class MyStarshipControl extends Control {
    private Movable movable;
    private final ControlConfiguration controlConfiguration;

    public Movable updateMovable(Set<Integer> keySet) {


        if (keySet.contains(controlConfiguration.getMoveFowardKey())) {
            movable = (Movable) movable.moveForward(2);
            return movable;
        }

        if (keySet.contains(controlConfiguration.getMoveBackwardsKey())) {
            movable = (Movable) movable.moveBackwards(2);
            return movable;
        }

        if (keySet.contains(controlConfiguration.getRotateLeftKey())) {
            movable = (Movable) movable.rotate(-1 * PConstants.PI / 60);
            return movable;
        }

        if (keySet.contains(controlConfiguration.getRotateRightKey())) {
            movable = (Movable) movable.rotate(PConstants.PI / 60);
            return movable;
        }
        return movable;

}






    public MyStarshipControl(Movable movable, ControlConfiguration controlConfiguration) {
        this.movable = movable;
        this.controlConfiguration = controlConfiguration;
    }
}
