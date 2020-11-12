package edu.austral.dissis.starship;

public interface Movable {
    public GameObject rotate(float angle);
    public GameObject moveForward(float speed);
    public GameObject moveBackwards(float speed);

}
