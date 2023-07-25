package dev_5;

import java.util.Vector;

/**
 * @author Xiang Weng
 */
public class EnemyTank extends MyTank implements Runnable {


    private Vector<Shot> shots = new Vector<>();
    private boolean isAlive = true;

    public void setShots(Vector<Shot> shots) {
        this.shots = shots;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public Vector<Shot> getShots() {
        return shots;
    }

    public EnemyTank(int x, int y) {
        super(x, y);
    }

    //随机变换朝向
    public void randomDirection() {
        int direction = (int) (Math.random() * 4);
        this.setDirection(direction);
    }

    public void createShot() {
        Shot s = null;
        switch (getDirection()) {
            case 0:
                s = new Shot(getX() + 20, getY(), 0);
                break;
            case 1:
                s = new Shot(getX() + 60, getY() + 20, 1);
                break;
            case 2:
                s = new Shot(getX() + 20, getY() + 60, 2);
                break;
            case 3:
                s = new Shot(getX(), getY() + 20, 3);
                break;
        }
        shots.add(s);
        new Thread(s).start();
    }

    @Override
    public void run() {
        while (isAlive) {
            if (shots.size() <5) createShot();
            // 根据当前朝向移动
            switch (this.getDirection()) {
                case 0:
                    for (int i = 0; i < 30; i++) {
                        if (movable())
                            moveUp();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 1:
                    for (int i = 0; i < 30; i++) {
                        if (movable())
                            moveRight();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < 30; i++) {
                        if (movable())
                            moveDown();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < 30; i++) {
                        if (movable())
                            moveLeft();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
            }

            // 改变方向
            randomDirection();
        }
    }
}
