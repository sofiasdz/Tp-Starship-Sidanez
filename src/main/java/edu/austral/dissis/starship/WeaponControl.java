package edu.austral.dissis.starship;

import java.util.Set;

public class WeaponControl extends Control {
    Weapon weapon;
    ControlConfiguration controlConfiguration;


    public WeaponControl(Weapon weapon, ControlConfiguration controlConfiguration) {
        this.weapon = weapon;
        this.controlConfiguration = controlConfiguration;
    }

    @Override
    public Movable updateMovable(Set<Integer> keySet) {
      if(keySet.contains(controlConfiguration.getShootkey())){
          weapon.shoot();
      }
      return weapon.ammunition;
    }
}
