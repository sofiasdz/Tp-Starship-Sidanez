package edu.austral.dissis.starship;

import edu.austral.dissis.starship.base.vector.Vector2;

public interface GameObject {
    Vector2 getPosition();
    Vector2 getDirection();
    int getSize();
    void draw(DrawerManagerImpl drawerManagerImpl);




}
