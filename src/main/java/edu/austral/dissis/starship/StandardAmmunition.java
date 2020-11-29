package edu.austral.dissis.starship;

import edu.austral.dissis.starship.base.collision.Collisionable;
import edu.austral.dissis.starship.base.vector.Vector2;
import processing.core.PConstants;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

public class StandardAmmunition extends Ammunition  {
    int size;
    private boolean isDestroyed;
    @Override
    public Vector2 getPosition() {
        return position;
    }

    @Override
    public Vector2 getDirection() {
        return direction;
    }

    @Override
    public int getSize() {
        return size;
    }

    public StandardAmmunition( Vector2 position, Vector2 direction, int size,int playerNumber) {
        this.position=position;
        this.direction=direction;
        this.size=size;
        isDestroyed=false;
        this.playerNumber=playerNumber;
    }
    public StandardAmmunition( Vector2 position, Vector2 direction, int size,int playerNumber,boolean isDestroyed) {
        this.position=position;
        this.direction=direction;
        this.size=size;
        this.playerNumber=playerNumber;
        this.isDestroyed=isDestroyed;
    }

    @Override
    public GameObject rotate(float angle) {
        return new StandardAmmunition(position,direction.rotate(angle),size,playerNumber,isDestroyed);
    }

    @Override
    public GameObject moveForward(float speed) {

        return new StandardAmmunition(position.add(direction.multiply(speed)), direction,size,playerNumber,isDestroyed);
    }

    @Override
    public GameObject moveBackwards(float speed) {
        return this;
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
        collisionable.collisionedWithAmmunition(this);

    }

    @Override
    public void collisionedWithStarship(Starship starship) {
        if(playerNumber!=starship.getPlayerNumber())isDestroyed=true;

    }

    @Override
    public void collisionedWithAsteroid(Asteroid asteroid) {
        System.out.println("ammun collided with asteroid");
        isDestroyed=true;
    }

    @Override
    public void collisionedWithAmmunition(Ammunition ammunition) {



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
        return 0;
    }

    @Override
    public int getPlayerNumber() {
        return playerNumber;
    }
}
