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
        private List<Player>  players;
        private List<Control> controls;
        private List<Ammunition> ammo;
        private CollisionChecker collisionChecker;



        public void setUp(){
            asteroids=new ArrayList<>();
            starships=new ArrayList<>();
            players=new ArrayList<>();
            controls=new ArrayList<>();
           createPlayer(1,"khali");
           createPlayer(2,"sofi");
            collisionChecker= new CollisionChecker();
            spawnAsteroids();
        }

        private void createPlayer(int number,String nickname){
            Score score = new Score(vector(800, 800*number), vector(0, -1), nickname, 0);
             List<Ammunition> ammo = new ArrayList<Ammunition>();
             Weapon weapon = new StandardWeapon(vector(200, 200), vector(0, -1), ammo,number);
            Starship starship = new Starship(vector(200, 200*number), vector(0, -1), 50, weapon,number);
            starships.add(starship);
            ControlConfiguration controlConfiguration = new MySpaceShipControlConfiguration();
             Control control = new MyStarshipControl(starship, controlConfiguration);
             Player player = new Player("Khali", score);
             controls.add(control);
             players.add(player);

        }

        private void spawnAsteroids(){
            Asteroid asteroid = new Asteroid(vector(300, 300), vector(0, -1), 50);
            Asteroid asteroid2 = new Asteroid(vector(600, 300), vector(0, -1), 50);
            Asteroid asteroid3 = new Asteroid(vector(900, 300), vector(0, -1), 50);
            Asteroid asteroid4 = new Asteroid(vector(600, 600), vector(0, -1), 50);

            asteroids.add(asteroid);
            asteroids.add(asteroid2);
            asteroids.add(asteroid3);
            asteroids.add(asteroid4);

        }





        public void handleUpdates( Set<Integer> keySet){
            for (int i = 0; i <starships.size(); i++) {
                starships.set(i,(Starship)controls.get(i).updateMovable(keySet));

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
        for (int i = 0; i <starships.size() ; i++) {
            List<Ammunition> starshipAmmo=starships.get(i).weapon.ammo;
            for (int j = 0; j < starshipAmmo.size(); j++) {
                Ammunition ammunition = starshipAmmo.get(j);
                ammunition = (Ammunition) ammunition.moveForward(10);
                starshipAmmo.set(j, ammunition);
            }
        }



    }

    private void manageAmmo() {
         getAmmo();
        for (int i = 0; i <starships.size() ; i++) {
            List<Ammunition> starshipAmmo=starships.get(i).weapon.ammo;
            for (int j = 0; j < starshipAmmo.size(); j++) {
                Ammunition ammunition = starshipAmmo.get(j);
                if (ammunition.getIsDestroyed()) {
                    ammo.remove(ammunition);
                    starships.get(i).weapon.ammo.remove(ammunition);
                    j = j - 1;


                }

            }
        }

    }

        private void manageAsteroids(){
            for (int i = 0; i <asteroids.size() ; i++) {
               Asteroid asteroid=asteroids.get(i);
                if(asteroid.getIsDestroyed()){
                    int destroyedBy= asteroid.destroyedBy;
                    asteroids.remove(asteroid);
                    Score score=players.get(destroyedBy).score;
                    score.setPoints(score.getPoints()+1);
                    System.out.println("DEAD ASTEROID");
                    i=i-1;
                }
            }



    }



    public void  drawGameObjects(DrawerManager drawerManager, PGraphics graphics){
            getAmmo();
            drawerManager.draw(graphics,starships,asteroids,ammo,getPlayerScores());

    }

    public void manageCollisions(){
            getAmmo();
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


    public void getAmmo(){
            ammo=new ArrayList<>();
        for (int i = 0; i <starships.size() ; i++) {
            for (int j = 0; j <starships.get(i).weapon.ammo.size() ; j++) {

                ammo.add(starships.get(i).weapon.ammo.get(j));
            }

        }
    }

    public List<Score> getPlayerScores(){
            List<Score> scores=new ArrayList<>();
        for (int i = 0; i <players.size(); i++) {
            scores.add(players.get(i).score);

        }
        return scores;
    }






}
