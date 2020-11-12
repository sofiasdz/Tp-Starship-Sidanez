package edu.austral.dissis.starship;

import edu.austral.dissis.starship.base.vector.Vector2;

public class Asteroid implements Movable,GameObject {

    private final Vector2 position;
    private final Vector2 direction;

    public Asteroid(Vector2 position, Vector2 direction) {
        this.position = position;
        this.direction = direction.asUnitary();
    }

    public Asteroid rotate(float angle) {

        return  new Asteroid(position, direction.rotate(angle));
    }

    public Asteroid moveForward(float speed) {
        return  new Asteroid(position.add(direction.multiply(speed)), direction);
    }

    public Asteroid moveBackwards(float speed) {
        return  new Asteroid(position.subtract(direction.multiply(speed)), direction);
    }

    public Vector2 getPosition() { return position; }

    public Vector2 getDirection() { return direction; }

}
