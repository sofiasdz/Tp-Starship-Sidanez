package edu.austral.dissis.starship;

import edu.austral.dissis.starship.base.collision.Collisionable;
import processing.core.PGraphics;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static edu.austral.dissis.starship.base.vector.Vector2.vector;


public class GameObjectManager {
    private List<Asteroid> asteroids;
    private List<Starship> starships;
    private List<Player> players;
    private List<Control> controls;
    private List<Ammunition> ammo;
    private CollisionChecker collisionChecker;
    private Player winner;
    private GameOverScreen gameOverScreen;
    private AsteroidManager asteroidManager;


    public void setUp(int numberOfPlayers, List<String> playersNames,ScreenArea screenArea) {
        starships = new ArrayList<>();
        players = new ArrayList<>();
        controls = new ArrayList<>();
        asteroidManager=new AsteroidManager(screenArea,8);
        for (int i = 0; i < numberOfPlayers; i++) {
            createPlayer(i + 1, playersNames.get(i));
        }
        collisionChecker = new CollisionChecker();
        //spawnAsteroids();
        asteroids=asteroidManager.spawnAsteroids();

    }

    private void createPlayer(int number, String nickname) {
        Score score = new Score(vector(300 + ((number - 1) * 500), 700), vector(0, -1), nickname, 0);
        Life life = new Life(vector(300 + 240 + ((number - 1) * 500), 700), vector(0, -1), 3);
        List<Ammunition> ammo = new ArrayList<Ammunition>();
        Weapon weapon = new StandardWeapon(vector(200, 200 * number), vector(0, -1), ammo, number);
        Starship starship = new Starship(vector(200, 200 * number), vector(0, -1), 50, weapon, number);
        starships.add(starship);
        ControlConfiguration controlConfiguration;
        if (number == 2) {
            controlConfiguration = new MySpaceshipControlP2();
        } else {
            controlConfiguration = new MySpaceShipControlConfiguration();
        }
        Control control = new MyStarshipControl(starship, controlConfiguration);
        Player player = new Player(nickname, score, life);
        controls.add(control);
        players.add(player);

    }

 /*   private void spawnAsteroids() {
        Asteroid asteroid = new Asteroid(vector(300, 300), vector(0, 1), 100);
        Asteroid asteroid2 = new Asteroid(vector(600, 300), vector(0, -1), 50);
        Asteroid asteroid3 = new Asteroid(vector(900, 300), vector(0, -1), 100);
        Asteroid asteroid4 = new Asteroid(vector(600, 600), vector(0, 1), 50);
        Asteroid asteroid5 = new Asteroid(vector(1000, 100), vector(0, -1), 10);
        Asteroid asteroid6 = new Asteroid(vector(100, 100), vector(0, -1), 10);

        asteroids.add(asteroid);
        asteroids.add(asteroid2);
        asteroids.add(asteroid3);
        asteroids.add(asteroid4);
        asteroids.add(asteroid5);
        asteroids.add(asteroid6);

    }*/


    public void handleUpdates(Set<Integer> keySet) {
        for (int i = 0; i < starships.size(); i++) {
            starships.set(i, (Starship) controls.get(i).updateMovable(keySet));

        }
        manageCollisions();
        //updateAsteroid();
        asteroidManager.updateAsteroids(asteroids);
        updateAmmo();
        manageAmmo();
        manageAsteroids();
        manageStarships();

    }


    private void updateAmmo() {
        for (int i = 0; i < starships.size(); i++) {
            List<Ammunition> starshipAmmo = starships.get(i).weapon.ammo;
            for (int j = 0; j < starshipAmmo.size(); j++) {
                Ammunition ammunition = starshipAmmo.get(j);
                ammunition = (Ammunition) ammunition.moveForward(10);
                starshipAmmo.set(j, ammunition);
            }
        }


    }


    private void manageAmmo() {
        getAmmo();
        for (int i = 0; i < starships.size(); i++) {
            List<Ammunition> starshipAmmo = starships.get(i).weapon.ammo;
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

    private void manageAsteroids() {
        for (int i = 0; i < asteroids.size(); i++) {
            Asteroid asteroid = asteroids.get(i);
            if (asteroid.getIsDestroyed() && asteroid.destroyedBy != -1) {
                int destroyedBy = asteroid.destroyedBy;
                Score score = players.get(destroyedBy - 1).score;
                System.out.println(destroyedBy);
                score.setPoints(score.getPoints() + 1);
                System.out.println("DEAD ASTEROID");
               asteroids.set(i,asteroidManager.spawnAsteroid());
            }
        }


    }

    private void manageStarships() {
        for (int i = 0; i < starships.size(); i++) {
            Starship starship = starships.get(i);
            if (starship.getIsDestroyed()) {
                if (starship.destroyedBy != -1) {
                    int destroyedBy = starship.destroyedBy;
                    Score score = players.get(destroyedBy - 1).score;
                    score.setPoints(score.getPoints() + 5);
                }
                Life life = players.get(i).getLife();
                if (life.getLives() - 1 < 0) {
                    starships.remove(starship);
                    checkForWinner();

                } else {
                    players.get(i).getLife().setLives(life.getLives() - 1);
                    List<Ammunition> ammo = new ArrayList<Ammunition>();
                    Weapon weapon = new StandardWeapon(vector(200, 200 * starship.playerNumber), vector(0, -1), ammo, starship.getPlayerNumber());
                    Starship starship2 = new Starship(vector(200, 200 * starship.playerNumber), vector(0, -1), 50, weapon, starship.playerNumber);
                    starships.set(i, starship2);

                    controls.set(i, new MyStarshipControl(starship2, getControlConfiguration(starship.playerNumber)));
                    System.out.println(starship2.getIsDestroyed());
                    System.out.println("DEAD SPACESHIP");
                }

            }
        }


    }

    private ControlConfiguration getControlConfiguration(int playerNumber) {
        ControlConfiguration controlConfiguration;
        if (playerNumber == 2) {
            controlConfiguration = new MySpaceshipControlP2();

        } else {
            controlConfiguration = new MySpaceShipControlConfiguration();
        }

        return controlConfiguration;
    }


    public void drawGameObjects(DrawerManagerImpl drawerManagerImpl, PGraphics graphics) {
        getAmmo();
        List<GameObject> list = new ArrayList<>();
        list.addAll(starships);
        list.addAll(asteroids);
        list.addAll(ammo);
        list.addAll(getPlayersScores());
        list.addAll(getPlayersLives());
        list.add(gameOverScreen);
        drawerManagerImpl.draw(list, graphics);

    }

    public void manageCollisions() {
        getAmmo();
        List<Collisionable> collisionables = new ArrayList<>();
        for (int i = 0; i < starships.size(); i++) {
            collisionables.add(starships.get(i));

        }
        for (int i = 0; i < asteroids.size(); i++) {
            collisionables.add(asteroids.get(i));

        }
        for (int i = 0; i < ammo.size(); i++) {
            collisionables.add(ammo.get(i));

        }
        collisionChecker.setCollisionables(collisionables);
        collisionChecker.checkCollisions();
        checkForWinner();
    }


    public void getAmmo() {
        ammo = new ArrayList<>();
        for (int i = 0; i < starships.size(); i++) {
            for (int j = 0; j < starships.get(i).weapon.ammo.size(); j++) {

                ammo.add(starships.get(i).weapon.ammo.get(j));
            }

        }
    }

    public List<Score> getPlayersScores() {
        List<Score> scores = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            scores.add(players.get(i).getScore());

        }
        return scores;
    }

    public List<Life> getPlayersLives() {
        List<Life> lives = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            lives.add(players.get(i).getLife());

        }
        return lives;
    }


    public void setGameOverScreen() {
        gameOverScreen = new GameOverScreen(winner.getNickname(), winner.getScore().getPoints(), vector(300, 300), vector(0, -1));
        asteroids = new ArrayList<>();
        starships = new ArrayList<>();
        players = new ArrayList<>();
        ammo = new ArrayList<>();

    }

    public void checkForWinner() {
        if (starships.size() == 0) {
            winner = getHighestScore();
            setGameOverScreen();
        }
    }



    Player getHighestScore(){
        Player player=players.get(0);
        for (int i = 0; i <players.size() ; i++) {
            if (player.score.getPoints()<players.get(i).score.getPoints()) {
                player = players.get(i);
            }



        }
        return player;
    }
}
