package edu.austral.dissis.starship;

import edu.austral.dissis.starship.base.vector.Vector2;

public class Life implements GameObject {
    int lives;
    Vector2 position;
    Vector2 direction;

    @Override
    public Vector2 getPosition() {
        return position;
    }

    @Override
    public Vector2 getDirection() {
        return direction;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public Life(Vector2 position, Vector2 direction, int lives) {
        this.position = position;
        this.direction = direction;
        this.lives=lives;
    }
}
