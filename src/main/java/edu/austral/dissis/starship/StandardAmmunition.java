package edu.austral.dissis.starship;

import edu.austral.dissis.starship.base.vector.Vector2;

public class StandardAmmunition extends Ammunition {
    @Override
    public Vector2 getPosition() {
        return position;
    }

    @Override
    public Vector2 getDirection() {
        return direction;
    }

    public StandardAmmunition( Vector2 position, Vector2 direction) {
        this.position=position;
        this.position=position;
    }

    @Override
    public GameObject rotate(float angle) {
        return null;
    }

    @Override
    public GameObject moveForward(float speed) {

        return new StandardAmmunition(position.add(direction.multiply(speed)), direction);
    }

    @Override
    public GameObject moveBackwards(float speed) {
        return null;
    }
}
