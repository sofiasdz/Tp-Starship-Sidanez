package edu.austral.dissis.starship;
import edu.austral.dissis.starship.base.vector.Vector2;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;


    public class AmmunitionDrawer {
        private static final float IMAGE_SIZE = 5;
        public static final int SQUARE_SIZE = 20;

        private final PImage image;

        public AmmunitionDrawer(PImage image) {
            this.image = image;
        }

        private float getImageCenter() {
            return IMAGE_SIZE / -2f;
        }

        public void draw(PGraphics graphics, Ammunition ammunition) {
            final Vector2 position = ammunition.getPosition();
            final float angle = calculateRotation(ammunition);

            graphics.pushMatrix();

            graphics.translate(position.getX(), position.getY());
            graphics.rotate(angle);


            graphics.fill(255, 0, 0);
            graphics.rect(SQUARE_SIZE / -2f, SQUARE_SIZE / -2f, SQUARE_SIZE, SQUARE_SIZE);
            graphics.image(image, getImageCenter(), getImageCenter());


            graphics.popMatrix();

            graphics.fill(0, 255, 0);
        }

        private float calculateRotation(Ammunition ammunition) {
            return ammunition.getDirection().rotate(PConstants.PI / 2).getAngle();
        }

        public SquareCollisionable getCollisionable(Ammunition ammunition) {
            return new SquareCollisionable(
                    SQUARE_SIZE,
                    calculateRotation(ammunition),
                    ammunition.getPosition()
            );
        }

    }

