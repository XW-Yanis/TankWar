package dev_5;

/**
 * @author Xiang Weng
 */
public class Explode {
    public int getX() {
        return x;
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

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    private int x,y,life = 9;
    private boolean isAlive = true;

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void lifeDown(){
        if (life > 0) life--;
        else isAlive = false;
    }
}
