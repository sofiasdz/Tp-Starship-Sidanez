package edu.austral.dissis.starship;

import edu.austral.dissis.starship.base.vector.Vector2;

import java.util.List;

public class StandardWeapon  extends Weapon {
    public StandardWeapon(Vector2 position, Vector2 direction, List<Ammunition> ammo) {
        this.position = position;
        this.direction = direction;
        this.ammo = ammo;
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
        Ammunition ammunition = new StandardAmmunition(position, direction, 20);
        ammo.add(ammunition);

    }



    @Override
    public GameObject rotate(float angle) {
         return new StandardWeapon(position,direction.rotate(angle),ammo);
    }

    @Override
    public GameObject moveForward(float speed) {
       return new StandardWeapon(position.add(direction.multiply(speed)),direction,ammo);
    }

    @Override
    public GameObject moveBackwards(float speed) {
         return new StandardWeapon(position.subtract(direction.multiply(speed)), direction,ammo);
    }
}
