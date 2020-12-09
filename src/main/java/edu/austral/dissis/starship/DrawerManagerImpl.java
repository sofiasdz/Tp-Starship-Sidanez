package edu.austral.dissis.starship;

import edu.austral.dissis.starship.base.framework.ImageLoader;
import processing.core.PGraphics;

import java.util.List;

public class DrawerManagerImpl implements DrawerManager {
    private PGraphics pGraphics;
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
    public void draw(List<GameObject> gameObjects, PGraphics graphics){
        this.pGraphics=graphics;
        for (int i = 0; i <gameObjects.size() ; i++) {
            if(gameObjects.get(i)!=null) {
                gameObjects.get(i).draw(this);
            }

        }
    }

     public void draw(Asteroid asteroid){
         if(asteroid.size==100){
             bigAsteroidDrawer.draw(pGraphics,asteroid);
         }
         if(asteroid.size==10){
             smallAsteroidDrawer.draw(pGraphics,asteroid);
         }

         if (asteroid.size==50){
             asteroidDrawer.draw(pGraphics,asteroid);
         }
     }

    public void draw(Ammunition ammunition){
       ammunitionDrawer.draw(pGraphics,ammunition);
    }

    public void draw(Score score){
        scoreDrawer.draw(pGraphics,score);
    }
    public void draw(Starship starship){
        starshipDrawer.draw(pGraphics,starship);
    }
    public void draw(Life life){
        lifeDrawer.draw(pGraphics,life);
    }
    public void draw(GameOverScreen gameOverScreen){
      gameOverDrawer.draw(pGraphics,gameOverScreen);
    }




}
