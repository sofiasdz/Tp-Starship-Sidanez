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
         ammunition=(StandardAmmunition)ammunition.moveForward(30);


    }

    public void setAmmunition(Ammunition ammunition){
        this.ammunition=ammunition;
    }
}
