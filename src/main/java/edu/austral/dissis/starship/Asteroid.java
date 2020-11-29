package edu.austral.dissis.starship;

import edu.austral.dissis.starship.base.collision.Collisionable;
import edu.austral.dissis.starship.base.vector.Vector2;
import processing.core.PConstants;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

public class Asteroid implements Movable,GameObject, Collisionable,Destroyable {

    private final Vector2 position;
    private final Vector2 direction;
     int  size;
     private boolean isDestroyed;
     int destroyedBy;

    public Asteroid(Vector2 position, Vector2 direction,int size,boolean isDestroyed,int destroyedBy) {
        this.position = position;
        this.direction = direction.asUnitary();
        this.size=size;
        this.isDestroyed=isDestroyed;
        this.destroyedBy=destroyedBy;
    }

    public Asteroid(Vector2 position, Vector2 direction,int size) {
        this.position = position;
        this.direction = direction.asUnitary();
        this.size=size;
        this.isDestroyed=false;
        this.destroyedBy=0;
    }

    public Asteroid rotate(float angle) {

        return  new Asteroid(position, direction.rotate(angle),size,isDestroyed,destroyedBy);
    }

    public Asteroid moveForward(float speed) {
        return  new Asteroid(position.add(direction.multiply(speed)), direction,size,isDestroyed,destroyedBy);
    }

    public Asteroid moveBackwards(float speed) {
        return  new Asteroid(position.subtract(direction.multiply(speed)), direction,size,isDestroyed,destroyedBy);
    }

    public Vector2 getPosition() { return position; }

    public Vector2 getDirection() { return direction; }

    @Override
    public int getSize() {
        return  size;
    }

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
        collisionable.collisionedWithAsteroid(this);


    }

    @Override
    public void collisionedWithStarship(Starship starship) {
        System.out.println("Asteroid collided with starship");
        destroyedBy=starship.getPlayerNumber();
        isDestroyed=true;





    }

    @Override
    public void collisionedWithAsteroid(Asteroid asteroid) {
        //isDestroyed=true;


    }

    @Override
    public void collisionedWithAmmunition(Ammunition ammunition) {
        System.out.println("Asteroid collided with ammunition");
        destroyedBy=ammunition.getPlayerNumber();
        isDestroyed=true;


    }


    public float calculateRotation() {
        return this.getDirection().rotate(PConstants.PI / 2).getAngle();
    }

    @Override
    public boolean getIsDestroyed() {
        return isDestroyed;
    }

    @Override
    public int destroyedBy() {
        return  destroyedBy;
    }
}
