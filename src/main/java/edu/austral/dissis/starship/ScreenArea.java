package edu.austral.dissis.starship;

import edu.austral.dissis.starship.base.vector.Vector2;

public class ScreenArea {
    int width;
    int height;

    public ScreenArea(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isInScreenArea(Vector2 position){
        float x=position.getX();
        float y=position.getY();
        if (x>width| x<0) return false;
        if (y>height|y<0) return false;
        return true;
    }

    public boolean isInCorner(Vector2 position){
        float x=position.getX();
        float y=position.getY();
        if (x>width && y<0 ){
            //corner arriba derecha

        }
        if(x>width && y>height){
            //corner abajo derecha
        }
        if (x<0 &&y<0){
            //corner arriba izquierda
        }
        if(x<0 && y>height){
            //abajo izquierda
        }
        return true;

    }
}
