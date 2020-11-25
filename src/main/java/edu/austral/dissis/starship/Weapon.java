package edu.austral.dissis.starship;

import edu.austral.dissis.starship.base.vector.Vector2;

import java.util.List;

public abstract class Weapon implements GameObject, Movable{
    private String name;
    Vector2 position;
    Vector2 direction;
    List<Ammunition> ammo;

    public abstract void shoot();

    }







