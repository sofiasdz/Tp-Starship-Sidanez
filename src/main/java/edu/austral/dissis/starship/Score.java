package edu.austral.dissis.starship;

import edu.austral.dissis.starship.base.vector.Vector2;

public class Score  implements  GameObject{
    Vector2 position;
    Vector2 direction;
    String playerNick;
    int points;

    @Override
    public Vector2 getPosition() {
        return position;
    }

    @Override
    public Vector2 getDirection() {
        return direction;
    }

    public Score(Vector2 position, Vector2 direction, String playerNick, int points) {
        this.position = position;
        this.direction = direction;
        this.playerNick = playerNick;
        this.points = points;
    }

    public String getPlayerNick() {
        return playerNick;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
