package edu.austral.dissis.starship;

import edu.austral.dissis.starship.base.vector.Vector2;

public abstract class Weapon implements GameObject{
    private String name;
    Vector2 position;
    Vector2 direction;
    Ammunition ammunition;

    public abstract void shoot();



    }







