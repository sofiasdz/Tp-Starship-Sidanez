package edu.austral.dissis.starship;
import edu.austral.dissis.starship.base.vector.Vector2;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;


    public class AmmunitionDrawer extends PGraphicsDrawer {
        private static final float IMAGE_SIZE = 20;
        public static final int SQUARE_SIZE = 20;

        private final PImage image;

        public AmmunitionDrawer(PImage image) {
            image.resize(20,20);
            this.image = image;
        }

         float getImageCenter() {
            return IMAGE_SIZE / -2f;
        }

        @Override
        public void draw(PGraphics graphics, GameObject ammunition) {
            final Vector2 position = ammunition.getPosition();
            final float angle = calculateRotation(ammunition);

            graphics.pushMatrix();

            graphics.translate(position.getX(), position.getY());
            graphics.rotate(angle);


            //graphics.fill(255, 0, 0);
            //graphics.rect(SQUARE_SIZE / -2f, SQUARE_SIZE / -2f, SQUARE_SIZE, SQUARE_SIZE);
            graphics.image(image, getImageCenter(), getImageCenter());


            graphics.popMatrix();

            //graphics.fill(0, 255, 0);
        }

        private float calculateRotation(Ammunition ammunition) {
            return ammunition.getDirection().rotate(PConstants.PI / 2).getAngle();
        }



    }

