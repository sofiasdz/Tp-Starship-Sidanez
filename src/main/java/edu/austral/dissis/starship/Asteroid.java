package edu.austral.dissis.starship;

import edu.austral.dissis.starship.base.collision.Collisionable;
import edu.austral.dissis.starship.base.vector.Vector2;
import processing.core.PConstants;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

public class Asteroid implements Movable,GameObject, Collisionable {

    private final Vector2 position;
    private final Vector2 direction;
     int  size;

    public Asteroid(Vector2 position, Vector2 direction,int size) {
        this.position = position;
        this.direction = direction.asUnitary();
        this.size=size;
    }

    public Asteroid rotate(float angle) {

        return  new Asteroid(position, direction.rotate(angle),size);
    }

    public Asteroid moveForward(float speed) {
        return  new Asteroid(position.add(direction.multiply(speed)), direction,size);
    }

    public Asteroid moveBackwards(float speed) {
        return  new Asteroid(position.subtract(direction.multiply(speed)), direction,size);
    }

    public Vector2 getPosition() { return position; }

    public Vector2 getDirection() { return direction; }

    @Override
    public Shape getShape() {
        final Rectangle2D baseSquare = new Rectangle2D.Float(size / -2, size / -2, size, size);
        final Path2D.Float path = new Path2D.Float();
        path.append(baseSquare, false);

        final AffineTransform transform = new AffineTransform();
        transform.translate(position.getX(), position.getY());
        transform.rotate(calculateRotation());

        path.transform(transform);

        return path;
    }

    @Override
    public void collisionedWith(Collisionable collisionable) {
        System.out.println("Collisioned with " + collisionable);

    }


    public float calculateRotation() {
        return this.getDirection().rotate(PConstants.PI / 2).getAngle();
    }
}
