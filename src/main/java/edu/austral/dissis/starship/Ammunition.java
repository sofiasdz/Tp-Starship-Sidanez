package edu.austral.dissis.starship;

import edu.austral.dissis.starship.base.collision.Collisionable;
import edu.austral.dissis.starship.base.vector.Vector2;

public abstract class Ammunition  implements GameObject,Movable, Collisionable,Destroyable {
    private String name;
    Vector2 position;
    Vector2 direction;
    int playerNumber;
}
