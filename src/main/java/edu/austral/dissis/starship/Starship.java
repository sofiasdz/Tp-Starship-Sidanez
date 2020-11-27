package edu.austral.dissis.starship;

import edu.austral.dissis.starship.base.collision.Collisionable;
import edu.austral.dissis.starship.base.vector.Vector2;
import processing.core.PConstants;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

public class Starship  extends StarshipInterface implements Collisionable,Destroyable{
    private final Vector2 position;
    private final Vector2 direction;
    Weapon weapon;
    int size;
    int life;
    boolean isDestroyed;

    public Starship(Vector2 position, Vector2 direction,int size,Weapon weapon) {
        this.position = position;
        this.direction = direction.asUnitary();
        this.size=size;
        this.weapon=weapon;
        this.life=0;

    }

    public Starship rotate(float angle) {
        return new Starship(position, direction.rotate(angle),size,(Weapon)weapon.rotate(angle));
    }

    public Starship moveForward(float speed) {
        return new Starship(position.add(direction.multiply(speed)), direction, size, (Weapon)weapon.moveForward(speed));
    }

    public Starship moveBackwards(float speed) {
        return new Starship(position.subtract(direction.multiply(speed)), direction, size,(Weapon) weapon.moveBackwards(speed));

    }
    public Vector2 getPosition() { return position; }

    public Vector2 getDirection() { return direction; }


    public float calculateRotation() {
        return this.getDirection().rotate(PConstants.PI / 2).getAngle();
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
        collisionable.collisionedWithStarship(this);

    }

    @Override
    public void collisionedWithStarship(Starship starship) {
        this.life=life-1;
    }

    @Override
    public void collisionedWithAsteroid(Asteroid asteroid) {
        this.life=life-1;

    }

    @Override
    public void collisionedWithAmmunition(Ammunition ammunition) {
        this.life=life-1;

    }


    @Override
    public Starship shoot() {
         weapon.shoot();
        return new Starship(position,direction,size,weapon);





    }


    @Override
    public boolean getIsDestroyed() {
        return isDestroyed;
    }
}
