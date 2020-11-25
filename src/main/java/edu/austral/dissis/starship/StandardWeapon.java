package edu.austral.dissis.starship;

import edu.austral.dissis.starship.base.vector.Vector2;

public class StandardWeapon  extends Weapon{
    public StandardWeapon(Vector2 position, Vector2 direction, StandardAmmunition ammunition) {
        this.position = position;
        this.direction = direction;
        this.ammunition = ammunition;
    }

    @Override
    public Vector2 getPosition() {
        return position;
    }

    @Override
    public Vector2 getDirection() {
        return direction;
    }


    public void shoot() {
        for (int i = 0; i <10 ; i++) {

        ammunition=(StandardAmmunition)ammunition.moveForward(3);
        }
    }


}
