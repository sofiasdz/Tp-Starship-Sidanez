package edu.austral.dissis.starship;

import edu.austral.dissis.starship.base.collision.Collisionable;
import edu.austral.dissis.starship.base.vector.Vector2;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;

import java.awt.geom.Rectangle2D;

import static edu.austral.dissis.starship.base.vector.Vector2.vector;

    public class AsteroidDrawer  {
        private static final float IMAGE_SIZE = 100;
        public static final int SQUARE_SIZE = 20;

        private final PImage image;

        public AsteroidDrawer(PImage image) {
            this.image = image;
        }

        private float getImageCenter() {
            return IMAGE_SIZE / -2f;
        }

        public void draw(PGraphics graphics, Asteroid asteroid) {
            final Vector2 position = asteroid.getPosition();
            final float angle = calculateRotation(asteroid);

            graphics.pushMatrix();

            graphics.translate(position.getX(), position.getY());
            graphics.rotate(angle);


            graphics.fill(255, 0, 0);
           graphics.rect(SQUARE_SIZE / -2f, SQUARE_SIZE / -2f, SQUARE_SIZE, SQUARE_SIZE);
           graphics.image(image, getImageCenter(), getImageCenter());


            graphics.popMatrix();

            graphics.fill(0, 255, 0);
        }

        private float calculateRotation(Asteroid asteroid) {
            return asteroid.getDirection().rotate(PConstants.PI / 2).getAngle();
        }

        public SquareCollisionable getCollisionable(Asteroid asteroid) {
            return new SquareCollisionable(
                    SQUARE_SIZE,
                    calculateRotation(asteroid),
                    asteroid.getPosition()
            );
        }
    }


