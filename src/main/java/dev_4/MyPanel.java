package dev_4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.format.TextStyle;
import java.util.Vector;

/**
 * @author Xiang Weng
 */
public class MyPanel extends JPanel implements KeyListener,
        Runnable {
    MyTank myTank;
    Vector<EnemyTank> enemyTanks = new Vector<>();
    Vector<Explode> explodes = new Vector<>();
    Image image1;
    Image image2;
    Image image3;
    int enemySize = 3;

    public MyPanel() {
        myTank = new MyTank(300, 200);
        myTank.setSpeed(1);
        // 初始化敌人坦克
        for (int i = 0; i < enemySize; i++) {
            EnemyTank tank = new EnemyTank(100 * (i + 1), 1);
            tank.setDirection(2);
            new Thread(tank).start();
            enemyTanks.add(tank);
            Shot shot = new Shot(tank.getX() + 20, tank.getY() + 60, tank.getDirection());
            tank.getShots().add(shot);
            new Thread(shot).start();
        }
        image1 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/bomb_1.gif"));
        image2 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/bomb_2.gif"));
        image3 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/bomb_3.gif"));
    }

    @Override
    protected void paintBorder(Graphics g) {
        super.paintBorder(g);
        // 游戏背景的大小
        g.fillRect(0, 0, 800, 600);
        // 绘制敌方坦克
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank tank = enemyTanks.get(i);
            if (tank.isAlive()) {
                drawTank(tank.getX(), tank.getY(), g, tank.getDirection(), 1);
                // 绘制敌方子弹
                for (int j = 0; j < tank.getShots().size(); j++) {
                    Shot shot = tank.getShots().get(j);
                    if (shot.isAlive()) {
                        g.draw3DRect(shot.getX(), shot.getY(), 2, 2, false);
                    } else tank.getShots().remove(shot);
                }
            } else enemyTanks.remove(tank);
        }
        // 绘制自己坦克
        drawTank(myTank.getX(), myTank.getY(), g, myTank.getDirection(), 0);
        //绘制我方子弹
        if (myTank.getShot() != null && myTank.getShot().isAlive()) {
            g.draw3DRect(myTank.getShot().getX(), myTank.getShot().getY(), 2, 2, false);
        }
        //绘制爆炸（如果有的话）
        for (int i = 0; i < explodes.size(); i++) {
            Explode explode = explodes.get(i);
            if (explode.getLife() > 6) {
                g.drawImage(image1, explode.getX(), explode.getY(), 60, 60, this);
            } else if (explode.getLife() > 3) {
                g.drawImage(image2, explode.getX(), explode.getY(), 60, 60, this);
            } else g.drawImage(image3, explode.getX(), explode.getY(), 60, 60, this);
            explode.lifeDown();
            if (!explode.isAlive()) explodes.remove(explode);
        }

    }

    /**
     * @param x         坦克左上角的横坐标
     * @param y         左上角纵坐标
     * @param g         画笔
     * @param direction 面朝的方向
     * @param type      坦克类型，玩家坦克？敌方坦克?
     */
    public void drawTank(int x, int y, Graphics g, int direction, int type) {
        // 根据不同类型坦克设置颜色
        switch (type) {
            case 0:
                //玩家坦克
                g.setColor(Color.cyan);
                break;
            case 1:
                //敌方坦克
                g.setColor(Color.yellow);
                break;
        }
        //根据方向来绘制坦克
        /*
        0 北
        1 东
        2 南
        3 西
         */
        switch (direction) {
            //朝北
            case 0:
                g.fill3DRect(x, y, 10, 60, false);//左边履带
                g.fill3DRect(x + 30, y, 10, 60, false); //右边履带
                g.fill3DRect(x + 10, y + 10, 20, 40, false);// 坦克主体
                g.fillOval(x + 10, y + 20, 20, 20); // 坦克盖子
                g.drawLine(x + 20, y + 30, x + 20, y); // 炮管
                break;
            case 1:
                g.fill3DRect(x, y, 60, 10, false);//上边履带
                g.fill3DRect(x, y + 30, 60, 10, false); //下边履带
                g.fill3DRect(x + 10, y + 10, 40, 20, false);// 坦克主体
                g.fillOval(x + 20, y + 10, 20, 20); // 坦克盖子
                g.drawLine(x + 30, y + 20, x + 60, y + 20); // 炮管
                break;
            case 2:
                g.fill3DRect(x, y, 10, 60, false);//左边履带
                g.fill3DRect(x + 30, y, 10, 60, false); //右边履带
                g.fill3DRect(x + 10, y + 10, 20, 40, false);// 坦克主体
                g.fillOval(x + 10, y + 20, 20, 20); // 坦克盖子
                g.drawLine(x + 20, y + 30, x + 20, y + 60); // 炮管
                break;
            case 3:
                g.fill3DRect(x, y, 60, 10, false);//上边履带
                g.fill3DRect(x, y + 30, 60, 10, false); //下边履带
                g.fill3DRect(x + 10, y + 10, 40, 20, false);// 坦克主体
                g.fillOval(x + 20, y + 10, 20, 20); // 坦克盖子
                g.drawLine(x + 30, y + 20, x, y + 20); // 炮管
                break;
            default:
                break;
        }
    }

    public void isHit(Shot s, EnemyTank tank) {
        switch (tank.getDirection()) {
            case 0:
            case 2:
                if (s.getX() > tank.getX() && s.getX() < tank.getX() + 40
                        && s.getY() > tank.getY() && s.getY() < tank.getY() + 60) {
                    s.setAlive(false);
                    tank.setAlive(false);
                    explodes.add(new Explode(tank.getX(), tank.getY()));
                }
                break;
            case 1:
            case 3:
                if (s.getX() > tank.getX() && s.getX() < tank.getX() + 60
                        && s.getY() > tank.getY() && s.getY() < tank.getY() + 40) {
                    s.setAlive(false);
                    tank.setAlive(false);
                    explodes.add(new Explode(tank.getX(), tank.getY()));
                }
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // 如果 w
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                myTank.setDirection(0);
                if (myTank.movable())
                    myTank.moveUp();
                break;
            case KeyEvent.VK_D:
                myTank.setDirection(1);
                if (myTank.movable())
                    myTank.moveRight();
                break;
            case KeyEvent.VK_S:
                myTank.setDirection(2);
                if (myTank.movable())
                    myTank.moveDown();
                break;
            case KeyEvent.VK_A:
                myTank.setDirection(3);
                if (myTank.movable())
                    myTank.moveLeft();
                break;
            case KeyEvent.VK_J:
                if (myTank.getShot() == null || !myTank.getShot().isAlive())
                    myTank.fire();
                break;
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while (true) {
            if (myTank.getShot() != null && myTank.getShot().isAlive()) {
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank tank = enemyTanks.get(i);
                    isHit(myTank.getShot(), tank);
                }
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            repaint();
        }
    }
}
