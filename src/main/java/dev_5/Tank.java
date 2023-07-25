package dev_5;

/**
 * @author Xiang Weng
 */
public class Tank {

    /*
            0 ↑
            1 →
            2 ↓
            3 ←
         */
    private int x, y, direction, speed = 1;
    private boolean isAlive = true;

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public int getDirection() {
        return direction;
    }

    public void moveUp() {
        y -= speed;
    }

    public void moveRight() {
        x += speed;
    }

    public void moveDown() {
        y += speed;
    }

    public void moveLeft() {
        x -= speed;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }


    public int getX() {
        return x;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean movable() {
        switch (this.direction) {
            case 0:
                return this.y > 0;
            case 1:
                return this.x + 60 < 800;
            case 2:
                return this.y + 60 < 600;
            case 3:
                return this.x > 0;
            default:
                return false;
        }
    }
}
