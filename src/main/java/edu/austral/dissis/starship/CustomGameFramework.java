package edu.austral.dissis.starship;
import edu.austral.dissis.starship.base.framework.GameFramework;
import edu.austral.dissis.starship.base.framework.ImageLoader;
import edu.austral.dissis.starship.base.framework.WindowSettings;
import processing.core.PGraphics;
import processing.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.JFrame;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class CustomGameFramework implements GameFramework {

 GameObjectManager gameObjectManager;
 DrawerManager drawerManager;
 ScreenArea screenArea;


    @Override
    public void setup(WindowSettings windowsSettings, ImageLoader imageLoader) {

        String number= JOptionPane.showInputDialog("Please input number of players: ");
        int numberOfPlayers = Integer.parseInt(number);
        List<String> playerNames=new ArrayList<>();
        for (int i = 0; i <numberOfPlayers ; i++) {
            String name= JOptionPane.showInputDialog("Please input player number "+(i+1)+" nickname");
            playerNames.add(name);

        }
        windowsSettings
                .setSize(1280, 720);
        screenArea=new ScreenArea(1280,720);
        gameObjectManager=new GameObjectManager();
        drawerManager=new DrawerManager();
        gameObjectManager.setUp(numberOfPlayers,playerNames,screenArea);
        drawerManager.setUp(imageLoader);

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
