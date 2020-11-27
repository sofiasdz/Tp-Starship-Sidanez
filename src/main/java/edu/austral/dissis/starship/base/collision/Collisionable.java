package edu.austral.dissis.starship.base.collision;

import edu.austral.dissis.starship.Ammunition;
import edu.austral.dissis.starship.Asteroid;
import edu.austral.dissis.starship.Starship;

import java.awt.*;

public interface Collisionable<T extends Collisionable<T>> {
    Shape getShape();

    void collisionedWith(T collisionable);
    void collisionedWithStarship(Starship starship);
    void collisionedWithAsteroid(Asteroid asteroid);
    void collisionedWithAmmunition(Ammunition ammunition);
}
