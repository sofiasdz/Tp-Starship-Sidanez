package edu.austral.dissis.starship;

import processing.core.PConstants;
import processing.core.PGraphics;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static edu.austral.dissis.starship.base.vector.Vector2.vector;
import static java.util.Arrays.asList;

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
            asteroids.add(asteroid);
            controlConfiguration = new MySpaceShipControlConfiguration();
            control = new MyStarshipControl(starship1, controlConfiguration);
            player = new Player("Khali", score);
            collisionChecker= new CollisionChecker(asList(
                    starship1,asteroid
            ));
        }


        public void handleUpdates( Set<Integer> keySet){
            for (int i = 0; i <starships.size(); i++) {
                starships.set(i,(Starship)control.updateMovable(keySet));
                weapon= starships.get(i).weapon;
                ammo=starships.get(i).weapon.ammo;

            }
            collisionChecker.checkCollisions();
            updateAsteroid();
            updateAmmo();
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

    public void  drawGameObjects(DrawerManager drawerManager, PGraphics graphics){
            drawerManager.Draw(graphics,starships,asteroids,ammo,score);

    }

   /* public void getPoints(){
            int points=0;
        for (int i = 0; i < asteroids.size() ; i++) {


        }
    }*/

}
