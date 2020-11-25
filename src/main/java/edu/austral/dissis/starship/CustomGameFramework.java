package edu.austral.dissis.starship;
import edu.austral.dissis.starship.base.framework.GameFramework;
import edu.austral.dissis.starship.base.framework.ImageLoader;
import edu.austral.dissis.starship.base.framework.WindowSettings;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.event.KeyEvent;

import java.util.Set;

import static edu.austral.dissis.starship.base.vector.Vector2.vector;
import static java.util.Arrays.asList;

public class CustomGameFramework implements GameFramework {

    private StarshipDrawer starshipDrawer;
    private AsteroidDrawer asteroidDrawer;
    private AmmunitionDrawer ammunitionDrawer;
    private StandardAmmunition ammunition=new StandardAmmunition(vector(200, 200), vector(0, -1),10);
    private  Weapon weapon=new StandardWeapon(vector(200, 200), vector(0, -1),ammunition);
    private Starship starship1 = new Starship(vector(200, 200), vector(0, -1),50,weapon);
    private Asteroid asteroid= new Asteroid(vector(300,300),vector(0,-1),50);
    ControlConfiguration controlConfiguration=new MySpaceShipControlConfiguration();
    private Control control= new MyStarshipControl(starship1, controlConfiguration);
    private Control control2=new WeaponControl(weapon,controlConfiguration);
    private CollisionChecker collisionChecker;


    @Override
    public void setup(WindowSettings windowsSettings, ImageLoader imageLoader) {
        windowsSettings
            .setSize(500, 500);

        starshipDrawer = new StarshipDrawer(imageLoader.load("spaceship.png"));
       asteroidDrawer=new AsteroidDrawer(imageLoader.load("asteroid.png"));
       ammunitionDrawer=new AmmunitionDrawer(imageLoader.load("bullet.png"));

       collisionChecker= new CollisionChecker(asList(
                starship1,asteroid,ammunition
        ));
    }

    @Override
    public void draw(PGraphics graphics, float timeSinceLastDraw, Set<Integer> keySet) {

        ammunition=(StandardAmmunition) control2.updateMovable(keySet);
       weapon=new StandardWeapon(weapon.position, weapon.direction, ammunition);
       starship1=new Starship(starship1.getPosition(),starship1.getDirection(),50,weapon);
        starship1=(Starship)control.updateMovable(keySet);



        updateAsteroid();
         DrawerComponent drawerComponent=new DrawerComponent(graphics,starshipDrawer);
         DrawerComponent drawerComponent1= new DrawerComponent(graphics,asteroidDrawer);
         DrawerComponent drawerComponent2=new DrawerComponent(graphics,ammunitionDrawer);
         drawerComponent.draw(starship1);
         drawerComponent1.draw(asteroid);
         drawerComponent2.draw(ammunition);
        collisionChecker.checkCollisions();
    }








    private void updateAsteroid(){
            asteroid=asteroid.rotate(PConstants.PI / 60);

    }

    @Override
    public void keyPressed(KeyEvent event) {

    }

    @Override
    public void keyReleased(KeyEvent event) {

    }
}
