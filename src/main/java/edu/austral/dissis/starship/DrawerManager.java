package edu.austral.dissis.starship;

import edu.austral.dissis.starship.base.framework.ImageLoader;
import processing.core.PGraphics;

import java.util.List;

public class DrawerManager {
    private StarshipDrawer starshipDrawer;
    private AsteroidDrawer asteroidDrawer;
    private AmmunitionDrawer ammunitionDrawer;
    private ScoreDrawer scoreDrawer;
    private LifeDrawer lifeDrawer;
    private  BigAsteroidDrawer bigAsteroidDrawer;
    private SmallAsteroidDrawer smallAsteroidDrawer;
    private GameOverDrawer gameOverDrawer;

    public void setUp( ImageLoader imageLoader){
        starshipDrawer = new StarshipDrawer(imageLoader.load("spaceship.png"));
        asteroidDrawer = new AsteroidDrawer(imageLoader.load("asteroid.png"));
        ammunitionDrawer = new AmmunitionDrawer(imageLoader.load("bullet.png"));
        scoreDrawer = new ScoreDrawer(imageLoader.load("bullet.png"));
        lifeDrawer=new LifeDrawer(imageLoader.load("bullet.png"));
        bigAsteroidDrawer=new BigAsteroidDrawer(imageLoader.load("asteroid.png"));
        smallAsteroidDrawer=new SmallAsteroidDrawer(imageLoader.load("asteroid.png"));
        gameOverDrawer=new GameOverDrawer(imageLoader.load("asteroid.png"));
    }

    public void draw(PGraphics graphics, List<Starship> starships, List<Asteroid> asteroids,List<Ammunition> ammo,List<Score>scores,List<Life> lives,GameOverScreen gameOverScreen){
        DrawerComponent drawerComponent=new DrawerComponent(graphics,starshipDrawer);
        DrawerComponent drawerComponent1= new DrawerComponent(graphics,asteroidDrawer);
        DrawerComponent drawerComponent2=new DrawerComponent(graphics,ammunitionDrawer);
        DrawerComponent drawerComponent3= new DrawerComponent(graphics,scoreDrawer);
        DrawerComponent drawerComponent4=new DrawerComponent(graphics,lifeDrawer);
        DrawerComponent drawerComponent5=new DrawerComponent(graphics,bigAsteroidDrawer);
        DrawerComponent drawerComponent6=new DrawerComponent(graphics,smallAsteroidDrawer);
        DrawerComponent drawerComponent7=new DrawerComponent(graphics,gameOverDrawer);
        for (int i = 0; i <starships.size() ; i++) {
            drawerComponent.draw(starships.get(i));

        }
        for (int i = 0; i <asteroids.size() ; i++) {
            if(asteroids.get(i).size==100){
                drawerComponent5.draw(asteroids.get(i));
            }
            if(asteroids.get(i).size==10){
                drawerComponent6.draw(asteroids.get(i));
            }

            if (asteroids.get(i).size==50){
                drawerComponent1.draw(asteroids.get(i));
            }


        }
        for (int i = 0; i <scores.size() ; i++) {
            drawerComponent3.draw(scores.get(i));


        }
        for (int i = 0; i <ammo.size() ; i++) {
            drawerComponent2.draw(ammo.get(i));

        }
        for (int i = 0; i <lives.size() ; i++) {
            drawerComponent4.draw(lives.get(i));

        }
        if (gameOverScreen!=null){
            drawerComponent7.draw(gameOverScreen);

        }
    }
}
