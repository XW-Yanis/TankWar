package dev_5;

import java.util.Vector;

/**
 * @author Xiang Weng
 */
public class MyTank extends Tank {


    public Vector<Shot> getShots() {
        return shots;
    }

    public void setShots(Vector<Shot> shots) {
        this.shots = shots;
    }

    private Vector<Shot> shots;

    public MyTank(int x, int y) {
        super(x, y);
        shots = new Vector<>();
    }

    public void fire() {
        Shot shot = null;
        if (shots.size()==5) return;
        switch (getDirection()) {
            case 0:
                shot = new Shot(getX() + 20, getY(), 0);
                break;
            case 1:
                shot = new Shot(getX() + 60, getY() + 20, 1);
                break;
            case 2:
                shot = new Shot(getX() + 20, getY() + 60, 2);
                break;
            case 3:
                shot = new Shot(getX(), getY() + 20, 3);
                break;
        }
        shots.add(shot);
        new Thread(shot).start();
    }
}
