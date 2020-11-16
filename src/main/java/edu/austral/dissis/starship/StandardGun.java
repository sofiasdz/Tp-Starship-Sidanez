package edu.austral.dissis.starship;

import edu.austral.dissis.starship.base.vector.Vector2;

public class StandardGun  extends Weapon{
    @Override
    public Vector2 getPosition() {
       return position;
    }

    @Override
    public Vector2 getDirection() {
     return direction;
    }

    @Override
    public void shoot(Ammunition ammunition) {

    }
}
