package dev_7;

import java.util.Vector;

/**
 * @author Xiang Weng
 */

@SuppressWarnings({"All"})
public class EnemyTank extends MyTank implements Runnable {


    private Vector<Shot> shots = new Vector<>();
    private Vector<EnemyTank> tanks = new Vector<>();

    public Vector<EnemyTank> getTanks() {
        return tanks;
    }

    public void setTanks(Vector<EnemyTank> tanks) {
        this.tanks = tanks;
    }

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

    /**
     * @param tank 被撞的坦克
     * @return 看碰撞是否发生了
     */
    public boolean collision() {
        switch (this.getDirection()) {
            case 0:
                for (int i = 0; i < tanks.size(); i++) {
                    EnemyTank tank = tanks.get(i);
                    if (tank != this) {
                        if (tank.getDirection() == 0 || tank.getDirection() == 2) {
                            if (this.getX() > tank.getX()
                                    && this.getX() < tank.getX() + 40
                                    && this.getY() > tank.getY()
                                    && this.getY() < tank.getY() + 60) {
                                return true;
                            }
                            if (this.getX() + 40 > tank.getX()
                                    && this.getX() + 40 < tank.getX() + 40
                                    && this.getY() > tank.getY()
                                    && this.getY() < tank.getY() + 60) {
                                return true;
                            }
                        } else if (tank.getDirection() == 1 || tank.getDirection() == 3) {
                            if (this.getX() > tank.getX()
                                    && this.getX() < tank.getX() + 60
                                    && this.getY() > tank.getY()
                                    && this.getY() < tank.getY() + 40) {
                                return true;
                            }
                            if (this.getX() + 40 > tank.getX()
                                    && this.getX() + 40 < tank.getX() + 60
                                    && this.getY() > tank.getY()
                                    && this.getY() < tank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;

            case 1:
                for (int i = 0; i < tanks.size(); i++) {
                    EnemyTank tank = tanks.get(i);
                    if (tank != this) {
                        if (tank.getDirection() == 0 || tank.getDirection() == 2) {
                            if (this.getX() + 60 > tank.getX()
                                    && this.getX() + 60 < tank.getX() + 40
                                    && this.getY() > tank.getY()
                                    && this.getY() < tank.getY() + 60) {
                                return true;
                            }
                            if (this.getX() + 60 > tank.getX()
                                    && this.getX() + 60 < tank.getX() + 40
                                    && this.getY() + 40 > tank.getY()
                                    && this.getY() + 40 < tank.getY() + 60) {
                                return true;
                            } else if (tank.getDirection() == 1 || tank.getDirection() == 3) {
                                if (this.getX() + 60 > tank.getX()
                                        && this.getX() + 60 < tank.getX() + 60
                                        && this.getY() > tank.getY()
                                        && this.getY() < tank.getY() + 40) {
                                    return true;
                                }
                                if (this.getX() + 60 > tank.getX()
                                        && this.getX() + 60 < tank.getX() + 60
                                        && this.getY() + 40 > tank.getY()
                                        && this.getY() + 40 < tank.getY() + 40) {
                                    return true;
                                }
                            }
                        }
                    }
                }
                break;

            case 2:
                for (int i = 0; i < tanks.size(); i++) {
                    EnemyTank tank = tanks.get(i);
                    if (tank != this) {
                        if (tank.getDirection() == 0 || tank.getDirection() == 2) {
                            if (this.getX() > tank.getX()
                                    && this.getX() < tank.getX() + 40
                                    && this.getY() + 60 > tank.getY()
                                    && this.getY() + 60 < tank.getY() + 60) {
                                return true;
                            }
                            if (this.getX() + 40 > tank.getX()
                                    && this.getX() + 40 < tank.getX() + 40
                                    && this.getY() + 60 > tank.getY()
                                    && this.getY() + 60 < tank.getY() + 60) {
                                return true;
                            }
                        }
                    } else if (tank.getDirection() == 1 || tank.getDirection() == 3) {
                        if (this.getX() > tank.getX()
                                && this.getX() < tank.getX() + 60
                                && this.getY() + 60 > tank.getY()
                                && this.getY() + 60 < tank.getY() + 40) {
                            return true;
                        }
                        if (this.getX() + 40 > tank.getX()
                                && this.getX() + 40 < tank.getX() + 60
                                && this.getY() + 60 > tank.getY()
                                && this.getY() + 60 < tank.getY() + 40) {
                            return true;
                        }
                    }
                }
                break;

            case 3:
                for (int i = 0; i < tanks.size(); i++) {
                    EnemyTank tank = tanks.get(i);
                    if (tank != this) {
                        if (tank.getDirection() == 0 || tank.getDirection() == 2) {
                            if (this.getX() > tank.getX()
                                    && this.getX() < tank.getX() + 40
                                    && this.getY() > tank.getY()
                                    && this.getY() < tank.getY() + 60) {
                                return true;
                            }
                            if (this.getX() > tank.getX()
                                    && this.getX() < tank.getX() + 40
                                    && this.getY() + 40 > tank.getY()
                                    && this.getY() + 40 < tank.getY() + 60) {
                                return true;
                            }
                        } else if (tank.getDirection() == 1 || tank.getDirection() == 3) {
                            if (this.getX() > tank.getX()
                                    && this.getX() < tank.getX() + 60
                                    && this.getY() > tank.getY()
                                    && this.getY() < tank.getY() + 40) {
                                return true;
                            }
                            if (this.getX() > tank.getX()
                                    && this.getX() < tank.getX() + 60
                                    && this.getY() + 40 > tank.getY()
                                    && this.getY() + 40 < tank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
        }
        return false;
    }

    @Override
    public void run() {
        while (isAlive) {
            if (shots.size() < 5) createShot();
            // 根据当前朝向移动
            switch (this.getDirection()) {
                case 0:
                    for (int i = 0; i < 30; i++) {
                        if (movable() && !collision()) moveUp();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 1:
                    for (int i = 0; i < 30; i++) {
                        if (movable() && !collision()) moveRight();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < 30; i++) {
                        if (movable() && !collision()) moveDown();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < 30; i++) {
                        if (movable() && !collision()) moveLeft();
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
