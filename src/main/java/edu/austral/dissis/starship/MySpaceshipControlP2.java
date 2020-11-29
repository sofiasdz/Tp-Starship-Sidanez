package edu.austral.dissis.starship;
import java.awt.event.KeyEvent;

public class MySpaceshipControlP2 implements ControlConfiguration
{

    @Override
    public Object getMoveFowardKey() {
        return KeyEvent.VK_W ;
    }

    @Override
    public Object getRotateLeftKey() {
        return KeyEvent.VK_A ;

    }
    @Override
    public Object getRotateRightKey(){
        return  KeyEvent.VK_D;
    }

    @Override
    public Object getMoveBackwardsKey() {
        return KeyEvent.VK_S;

    }

    @Override
    public Object getShootkey() {
        return KeyEvent.VK_Q;
    }
}
