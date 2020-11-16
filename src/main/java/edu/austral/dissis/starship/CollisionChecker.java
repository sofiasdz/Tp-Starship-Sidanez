package edu.austral.dissis.starship;

import edu.austral.dissis.starship.base.collision.CollisionEngine;
import edu.austral.dissis.starship.base.collision.Collisionable;
import java.util.List;



public class CollisionChecker {
    private List<Collisionable> collisionables;
    private final CollisionEngine engine;


    public CollisionChecker(List<Collisionable> collisionables) {
        this.collisionables = collisionables;
        engine=new CollisionEngine();
    }

    public List<Collisionable> getCollisionables() {
        return collisionables;
    }

    public void setCollisionables(List<Collisionable> collisionables) {
        this.collisionables = collisionables;
    }

    public void checkCollisions() {
        engine.checkCollisions(collisionables);
    }
}
