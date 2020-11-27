package edu.austral.dissis.starship;
import edu.austral.dissis.starship.base.framework.GameFramework;
import edu.austral.dissis.starship.base.framework.ImageLoader;
import edu.austral.dissis.starship.base.framework.WindowSettings;
import processing.core.PGraphics;
import processing.event.KeyEvent;


import java.util.Set;


public class CustomGameFramework implements GameFramework {

 GameObjectManager gameObjectManager;
 DrawerManager drawerManager;


    @Override
    public void setup(WindowSettings windowsSettings, ImageLoader imageLoader) {
        gameObjectManager=new GameObjectManager();
        drawerManager=new DrawerManager();
        gameObjectManager.setUp();
        drawerManager.setUp(imageLoader);
        windowsSettings
                .setSize(1280, 720);

    }

    @Override
    public void draw(PGraphics graphics, float timeSinceLastDraw, Set<Integer> keySet) {
    gameObjectManager.handleUpdates(keySet);
    gameObjectManager.drawGameObjects(drawerManager,graphics);

    }









    @Override
    public void keyPressed(KeyEvent event) {

    }

    @Override
    public void keyReleased(KeyEvent event) {

    }

}
