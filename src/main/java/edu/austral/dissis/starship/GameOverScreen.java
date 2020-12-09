package edu.austral.dissis.starship;

import edu.austral.dissis.starship.base.vector.Vector2;

public class GameOverScreen implements GameObject {
    String winnerNickname;
    int points;
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

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public void draw(DrawerManagerImpl drawerManagerImpl) {
        drawerManagerImpl.draw(this);
    }

    public String getWinnerNickname() {
        return winnerNickname;
    }

    public int getPoints() {
        return points;
    }

    public GameOverScreen(String winnerNickname, int points, Vector2 position, Vector2 direction) {
        this.winnerNickname = winnerNickname;
        this.points = points;
        this.position = position;
        this.direction = direction;
    }
}
