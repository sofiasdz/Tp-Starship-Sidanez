package edu.austral.dissis.starship;
import edu.austral.dissis.starship.base.vector.Vector2;
import processing.core.PConstants;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;


import java.util.List;

import static edu.austral.dissis.starship.base.vector.Vector2.vector;

public class AsteroidManager {
    ScreenArea screenArea;
    int initialAsteroidNumber;

    public AsteroidManager(ScreenArea screenArea, int initialAsteroidNumber) {
        this.screenArea = screenArea;
        this.initialAsteroidNumber = initialAsteroidNumber;
    }

    public List<Asteroid> spawnAsteroids(){
        List<Asteroid> asteroids=new ArrayList<>();
        for (int i = 0; i <initialAsteroidNumber ; i++) {
            int asteroidSize = ThreadLocalRandom.current().nextInt(0, 2 + 1);
            if (asteroidSize==0) asteroidSize=10;
            if(asteroidSize==1) asteroidSize=50;
            else asteroidSize=100;
           Vector2 position=generateRandomPosition();
           Vector2 direction=generateRandomDirection(position);

            Asteroid asteroid = new Asteroid(position, direction, asteroidSize);
            asteroids.add(asteroid);

        }
        return asteroids;

    }

    public Asteroid spawnAsteroid(){

            int asteroidSize = ThreadLocalRandom.current().nextInt(0, 2 + 1);
            if (asteroidSize==0) asteroidSize=10;
            if(asteroidSize==1) asteroidSize=50;
            else asteroidSize=100;
            Vector2 position=generateRandomPosition();
            Vector2 direction=generateRandomDirection(position);

            return new Asteroid(position, direction, asteroidSize);

    }


    private Vector2 generateRandomDirection(Vector2 position) {
        int x=0;
        int y=0;
        int asteroidDirection = ThreadLocalRandom.current().nextInt(0, 3 + 1);
        if (asteroidDirection == 0) {
            y = 1;
        }
        if (asteroidDirection == 1) {
            y = -1;
        }
        if (asteroidDirection == 2) {
            x = 1;
        }
        if (asteroidDirection == 3) {
            x = -1;
        }
        return vector(x,y);



    }

    private Vector2 generateRandomPosition() {
        int x=0;
        int y=0;
        int asteroidPosition = ThreadLocalRandom.current().nextInt(0, 3 + 1);
        if (asteroidPosition == 0) {
            x = 0;
            y = screenArea.getHeight()-10;
        }
        if (asteroidPosition == 1) {
            x = screenArea.getWidth()-10;
            y = screenArea.getHeight()-10;
        }
        if (asteroidPosition == 2) {
            x = 0;
            y = 0;
        }
        if (asteroidPosition == 3) {
            x = 0;
            y = screenArea.getWidth()-10;
        }
        return vector(x,y);
    }


    public void updateAsteroids(List<Asteroid> asteroids){
        for (int i = 0; i <asteroids.size() ; i++) {
            Asteroid asteroid=asteroids.get(i);
            manageCornerCollision(asteroid,asteroids);
            if (!screenArea.isInScreenArea(asteroid.getPosition())){
                asteroid=asteroid.rotate(PConstants.PI / 60+ 1);
            }

            asteroid = (Asteroid) asteroid.moveForward(10);
            asteroids.set(i, asteroid);


        }
    }



     private void manageCornerCollision(Asteroid asteroid,List<Asteroid> asteroids){
         float x=asteroid.getPosition().getX();
         float y=asteroid.getPosition().getY();
         if (x>screenArea.width && y<0 ){
             //corner arriba derecha
            asteroid.setPosition(vector(x,y+50));

         }
         if(x>screenArea.width && y>screenArea.height){
             asteroid.setPosition(vector(x,y-50));
         }
         if (x<0 && y<0){
             asteroid.setPosition(vector(x,y+50));
         }
         if(x<0 && y>screenArea.height){
             asteroid.setPosition(vector(x,y-50));
         }
}




}
