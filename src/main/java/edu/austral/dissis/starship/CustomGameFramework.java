package edu.austral.dissis.starship;
import edu.austral.dissis.starship.base.framework.GameFramework;
import edu.austral.dissis.starship.base.framework.ImageLoader;
import edu.austral.dissis.starship.base.framework.WindowSettings;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.event.KeyEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static edu.austral.dissis.starship.base.vector.Vector2.vector;
import static java.util.Arrays.asList;

public class CustomGameFramework implements GameFramework {

    private StarshipDrawer starshipDrawer;
    private AsteroidDrawer asteroidDrawer;
    private AmmunitionDrawer ammunitionDrawer;
        private List<Ammunition> ammo= new ArrayList<Ammunition>();
    private  Weapon weapon=new StandardWeapon(vector(200, 200), vector(0, -1),ammo);
    private Starship starship1 = new Starship(vector(200, 200), vector(0, -1),50,weapon);
    private Asteroid asteroid= new Asteroid(vector(300,300),vector(0,-1),50);
    ControlConfiguration controlConfiguration=new MySpaceShipControlConfiguration();
    private Control control= new MyStarshipControl(starship1, controlConfiguration);
    private CollisionChecker collisionChecker;


    @Override
    public void setup(WindowSettings windowsSettings, ImageLoader imageLoader) {
        windowsSettings
            .setSize(500, 500);

        starshipDrawer = new StarshipDrawer(imageLoader.load("spaceship.png"));
       asteroidDrawer=new AsteroidDrawer(imageLoader.load("asteroid.png"));
       ammunitionDrawer=new AmmunitionDrawer(imageLoader.load("bullet.png"));

       collisionChecker= new CollisionChecker(asList(
                starship1,asteroid
        ));
    }

    @Override
    public void draw(PGraphics graphics, float timeSinceLastDraw, Set<Integer> keySet) {
        starship1=(Starship)control.updateMovable(keySet);
        weapon= starship1.weapon;
        ammo=starship1.weapon.ammo;



        updateAsteroid();
        updateAmmo();
         DrawerComponent drawerComponent=new DrawerComponent(graphics,starshipDrawer);
         DrawerComponent drawerComponent1= new DrawerComponent(graphics,asteroidDrawer);
         DrawerComponent drawerComponent2=new DrawerComponent(graphics,ammunitionDrawer);
         drawerComponent.draw(starship1);
         drawerComponent1.draw(asteroid);
        for (int i = 0; i <ammo.size() ; i++) {
            drawerComponent2.draw(ammo.get(i));

        }
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
    private void updateAmmo(){
                for (int i = 0; i <ammo.size() ; i++) {
                    Ammunition ammunition=ammo.get(i);
                    ammunition=(Ammunition)ammunition.moveForward(10);
                    ammo.set(i,ammunition);

            }



    }
}
