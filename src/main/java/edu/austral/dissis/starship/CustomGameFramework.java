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
    private Starship starship1 = new Starship(vector(200, 200), vector(0, -1),50);
    private  StandardAmmunition standardAmmunition= new StandardAmmunition(vector(200, 200), vector(0, -1));
    private Asteroid asteroid= new Asteroid(vector(300,300),vector(0,-1),50);
    private Control control= new MyStarshipControl(starship1, new MySpaceShipControlConfiguration());
    private CollisionChecker collisionChecker;


    @Override
    public void setup(WindowSettings windowsSettings, ImageLoader imageLoader) {
        windowsSettings
            .setSize(500, 500);

        starshipDrawer = new StarshipDrawer(imageLoader.load("spaceship.png"));
       asteroidDrawer=new AsteroidDrawer(imageLoader.load("asteroid.png"));

       collisionChecker= new CollisionChecker(asList(
                starship1,asteroid
        ));
    }

    @Override
    public void draw(PGraphics graphics, float timeSinceLastDraw, Set<Integer> keySet) {
        starship1=(Starship)control.updateMovable(keySet);
        updateAsteroid();
        drawStarships(graphics);
        drawAsteroids(graphics);
        collisionChecker.checkCollisions();
    }





    private void drawStarships(PGraphics graphics) {
        starshipDrawer.draw(graphics,starship1);
    }
    private void drawAsteroids(PGraphics graphics) {
        asteroidDrawer.draw(graphics, asteroid);

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
