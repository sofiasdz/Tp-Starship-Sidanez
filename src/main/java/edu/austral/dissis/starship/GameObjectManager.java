package edu.austral.dissis.starship;

import edu.austral.dissis.starship.base.collision.Collisionable;
import processing.core.PConstants;
import processing.core.PGraphics;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static edu.austral.dissis.starship.base.vector.Vector2.vector;


public class GameObjectManager {
        private List<Asteroid> asteroids;
        private List<Starship> starships;
        private Score score;
        private List<Ammunition> ammo;
        private Weapon weapon;
        ControlConfiguration controlConfiguration;
        private Control control;
        private CollisionChecker collisionChecker;
        private Player player;


        public void setUp(){
            asteroids= new ArrayList<>();
            starships=new ArrayList<>();
            score = new Score(vector(800, 800), vector(0, -1), "khali", 0);
            ammo = new ArrayList<Ammunition>();
            weapon = new StandardWeapon(vector(200, 200), vector(0, -1), ammo);
            Starship starship1 = new Starship(vector(200, 200), vector(0, -1), 50, weapon);
            starships.add(starship1);
            Asteroid asteroid = new Asteroid(vector(300, 300), vector(0, -1), 50);
            Asteroid asteroid2 = new Asteroid(vector(600, 300), vector(0, -1), 50);
            asteroids.add(asteroid);
            asteroids.add(asteroid2);
            controlConfiguration = new MySpaceShipControlConfiguration();
            control = new MyStarshipControl(starship1, controlConfiguration);
            player = new Player("Khali", score);
            collisionChecker= new CollisionChecker();
        }


        public void handleUpdates( Set<Integer> keySet){
            for (int i = 0; i <starships.size(); i++) {
                starships.set(i,(Starship)control.updateMovable(keySet));
                weapon= starships.get(i).weapon;
                ammo=starships.get(i).weapon.ammo;

            }
            manageCollisions();
            updateAsteroid();
            updateAmmo();
            manageAmmo();
            manageAsteroids();

        }


    private void updateAsteroid(){
        for (int i = 0; i <asteroids.size(); i++) {
            asteroids.set(i,asteroids.get(i).rotate(PConstants.PI / 60));

        }


    }

    private void updateAmmo(){
        for (int i = 0; i <ammo.size() ; i++) {
            Ammunition ammunition=ammo.get(i);
            ammunition=(Ammunition)ammunition.moveForward(10);
            ammo.set(i,ammunition);


        }



    }

    private void manageAmmo() {
        for (int i = 0; i < ammo.size(); i++) {
            Ammunition ammunition = ammo.get(i);
            if (ammunition.getIsDestroyed()) {
                ammo.remove(ammunition);
                i = i - 1;


            }
        }
    }

        private void manageAsteroids(){
            int points=0;
            for (int i = 0; i <asteroids.size() ; i++) {
               Asteroid asteroid=asteroids.get(i);
                if(asteroid.getIsDestroyed()){
                  asteroids.remove(asteroid);
                  points=points+1;
                    System.out.println("DEAD ASTEROID");
                    i=i-1;
                }
            }
            score.setPoints(score.getPoints()+points);


    }



    public void  drawGameObjects(DrawerManager drawerManager, PGraphics graphics){
            drawerManager.draw(graphics,starships,asteroids,ammo,score);

    }

    public void manageCollisions(){
            List<Collisionable>collisionables=new ArrayList<>();
        for (int i = 0; i <starships.size() ; i++) {
            collisionables.add(starships.get(i));

        }
        for (int i = 0; i <asteroids.size() ; i++) {
            collisionables.add(asteroids.get(i));

        }
        for (int i = 0; i <ammo.size() ; i++) {
            collisionables.add(ammo.get(i));

        }
            collisionChecker.setCollisionables(collisionables);
        collisionChecker.checkCollisions();
    }






}
