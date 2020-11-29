package edu.austral.dissis.starship;

import edu.austral.dissis.starship.base.vector.Vector2;

import java.util.List;

public class StandardWeapon  extends Weapon implements Identifiable {
    int playerNumber;
    public StandardWeapon(Vector2 position, Vector2 direction, List<Ammunition> ammo,int playerNumber) {
        this.position = position;
        this.direction = direction;
        this.ammo = ammo;
        this.playerNumber=playerNumber;
    }

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


    public void shoot() {
        Ammunition ammunition = new StandardAmmunition(position, direction, 20,playerNumber);
        ammo.add(ammunition);

    }



    @Override
    public GameObject rotate(float angle) {
         return new StandardWeapon(position,direction.rotate(angle),ammo,playerNumber);
    }

    @Override
    public GameObject moveForward(float speed) {
       return new StandardWeapon(position.add(direction.multiply(speed)),direction,ammo,playerNumber);
    }

    @Override
    public GameObject moveBackwards(float speed) {
         return new StandardWeapon(position.subtract(direction.multiply(speed)), direction,ammo,playerNumber);
    }

    @Override
    public int getPlayerNumber() {
        return playerNumber;
    }
}
