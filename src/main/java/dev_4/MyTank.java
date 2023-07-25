package dev_4;

/**
 * @author Xiang Weng
 */
public class MyTank extends Tank {
    public Shot getShot() {
        return shot;
    }

    public void setShot(Shot shot) {
        this.shot = shot;
    }

    private Shot shot;

    public MyTank(int x, int y) {
        super(x, y);
    }

    public void fire() {
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
        new Thread(shot).start();
    }
}
