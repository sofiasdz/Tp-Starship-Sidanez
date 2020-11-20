package edu.austral.dissis.starship;

import edu.austral.dissis.starship.base.vector.Vector2;

public interface Movable {
    //cambiar a movable
    public GameObject rotate(float angle);
    public GameObject moveForward(float speed);
    public GameObject moveBackwards(float speed);


}
