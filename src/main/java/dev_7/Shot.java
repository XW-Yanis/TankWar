package dev_7;

/**
 * @author Xiang Weng
 */
public class Shot implements Runnable {
    /*
          0 ↑
          1 →
          2 ↓
          3 ←
       */
    private int x, y, direction, speed = 15;

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    private boolean isAlive = true;

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

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

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Shot(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
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

    @Override
    public void run() {
        while (isAlive) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            switch (direction) {
                case 0:
                    moveUp();
                    break;
                case 1:
                    moveRight();
                    break;
                case 2:
                    moveDown();
                    break;
                case 3:
                    moveLeft();
                    break;
            }
//            System.out.println(x + " : " + y);
            //如果抵达边界
            if (!(x >= 0 && x <= 800 && y >= 0 && y <= 600)) {
                isAlive = false;
                break;
            }
        }
//        System.out.println("子弹线程退出了...");
    }
}
