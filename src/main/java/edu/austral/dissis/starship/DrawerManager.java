package edu.austral.dissis.starship;

import edu.austral.dissis.starship.base.framework.ImageLoader;
import processing.core.PGraphics;

import java.util.List;

public class DrawerManager {
    private StarshipDrawer starshipDrawer;
    private AsteroidDrawer asteroidDrawer;
    private AmmunitionDrawer ammunitionDrawer;
    private ScoreDrawer scoreDrawer;

    public void setUp( ImageLoader imageLoader){
        starshipDrawer = new StarshipDrawer(imageLoader.load("spaceship.png"));
        asteroidDrawer = new AsteroidDrawer(imageLoader.load("asteroid.png"));
        ammunitionDrawer = new AmmunitionDrawer(imageLoader.load("bullet.png"));
        scoreDrawer = new ScoreDrawer(imageLoader.load("bullet.png"));
    }

    public void draw(PGraphics graphics, List<Starship> starships, List<Asteroid> asteroids,List<Ammunition> ammo,Score score){
        DrawerComponent drawerComponent=new DrawerComponent(graphics,starshipDrawer);
        DrawerComponent drawerComponent1= new DrawerComponent(graphics,asteroidDrawer);
        DrawerComponent drawerComponent2=new DrawerComponent(graphics,ammunitionDrawer);
        DrawerComponent drawerComponent3= new DrawerComponent(graphics,scoreDrawer);
        for (int i = 0; i <starships.size() ; i++) {
            drawerComponent.draw(starships.get(i));

        }
        for (int i = 0; i <asteroids.size() ; i++) {
            drawerComponent1.draw(asteroids.get(i));


        }
        drawerComponent3.draw(score);
        for (int i = 0; i <ammo.size() ; i++) {
            drawerComponent2.draw(ammo.get(i));

        }
    }
}
