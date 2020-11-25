package edu.austral.dissis.starship;

import processing.core.PConstants;

import java.util.Set;

public class MyStarshipControl extends Control {
    private StarshipInterface movable;
    private final ControlConfiguration controlConfiguration;

    public StarshipInterface updateMovable(Set<Integer> keySet) {


        if (keySet.contains(controlConfiguration.getMoveFowardKey())) {
            movable =(StarshipInterface) movable.moveForward(2);

        }

        if (keySet.contains(controlConfiguration.getMoveBackwardsKey())) {
            movable = (StarshipInterface) movable.moveBackwards(2);

        }

        if (keySet.contains(controlConfiguration.getRotateLeftKey())) {
            movable = (StarshipInterface) movable.rotate(-1 * PConstants.PI / 60);

        }

        if (keySet.contains(controlConfiguration.getRotateRightKey())) {
            movable = (StarshipInterface) movable.rotate(PConstants.PI / 60);

        }
        if (keySet.contains(controlConfiguration.getShootkey())) {
            movable = (StarshipInterface) movable.shoot();
            keySet.remove(controlConfiguration.getShootkey());

        }
        return movable;

}






    public MyStarshipControl(StarshipInterface starshipInterface, ControlConfiguration controlConfiguration) {
        this.movable = starshipInterface;
        this.controlConfiguration = controlConfiguration;
    }
}
