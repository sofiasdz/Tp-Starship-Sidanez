package edu.austral.dissis.starship;

import edu.austral.dissis.starship.base.framework.ImageLoader;
import processing.core.PGraphics;

import java.util.List;

public interface DrawerManager {
    void draw(List<GameObject> gameObjects,PGraphics graphics);
    void setUp(ImageLoader imageLoader);
}
