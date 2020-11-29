package edu.austral.dissis.starship;
import edu.austral.dissis.starship.base.vector.Vector2;
import processing.core.PGraphics;
import processing.core.PImage;

    public class AsteroidDrawer extends PGraphicsDrawer {
        private static final float IMAGE_SIZE = 90;
        public static final int SQUARE_SIZE = 50;

        private final PImage image;

        public AsteroidDrawer(PImage image) {
            image.resize(90,90);
            this.image=image;
        }

       float getImageCenter() {
            return IMAGE_SIZE/ -2f;
        }

        public void draw(PGraphics graphics,GameObject gameObject) {
            final Vector2 position= gameObject.getPosition();
            final float angle= super.calculateRotation(gameObject);
            graphics.pushMatrix();

            graphics.translate(position.getX(), position.getY());
            graphics.rotate(angle);


            graphics.fill(255, 0, 0);
            graphics.rect(SQUARE_SIZE/ -2f, SQUARE_SIZE / -2f, SQUARE_SIZE, SQUARE_SIZE);

            graphics.image(image, getImageCenter(), getImageCenter());


            graphics.popMatrix();

            graphics.fill(0, 255, 0);
        }

    }


