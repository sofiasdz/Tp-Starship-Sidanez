package edu.austral.dissis.starship;

import processing.core.PConstants;

import java.util.Set;

public  abstract class Control {
    private Movable movable;
    private ControlConfiguration controlConfiguration;

   public  abstract Movable updateMovable(Set<Integer> keySet);
}
