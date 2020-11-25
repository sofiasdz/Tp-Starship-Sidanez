package edu.austral.dissis.starship;

import processing.core.PConstants;

public class MySpaceShipControlConfiguration implements ControlConfiguration
{

    @Override
    public Object getMoveFowardKey() {
       return PConstants.UP;
    }

    @Override
    public Object getRotateLeftKey() {
        return PConstants.LEFT;

    }
    @Override
    public Object getRotateRightKey(){
        return  PConstants.RIGHT;
    }

    @Override
    public Object getMoveBackwardsKey() {
        return PConstants.DOWN;

    }

    @Override
    public Object getShootkey() {
     return PConstants.SHIFT;
    }
}
