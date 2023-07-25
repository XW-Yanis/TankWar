package dev_2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

/**
 * @author Xiang Weng
 */
public class MyPanel extends JPanel implements KeyListener {
    MyTank myTank;
    Vector<EnemyTank> enemyTanks = new Vector<>();
    int enemySize = 3;
    public MyPanel() {
        myTank = new MyTank(300, 200);
        myTank.setSpeed(7);
        // 初始化敌人坦克
        for (int i = 0; i < enemySize; i++) {
            enemyTanks.add(new EnemyTank(100*(i+1),1));
        }
    }

    @Override
    protected void paintBorder(Graphics g) {
        super.paintBorder(g);
        g.fillRect(0, 0, 800, 600);

        for (int i = 0; i < enemySize; i++) {
            enemyTanks.get(i).setDirection(2);
            drawTank(enemyTanks.get(i).getX(), enemyTanks.get(i).getY(), g, enemyTanks.get(i).getDirection(), 1);
        }
        drawTank(myTank.getX(), myTank.getY(), g, myTank.getDirection(), 0);

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

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // 如果 w
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                myTank.setDirection(0);
                myTank.moveUp();
                break;
            case KeyEvent.VK_D:
                myTank.setDirection(1);
                myTank.moveRight();
                break;
            case KeyEvent.VK_S:
                myTank.setDirection(2);
                myTank.moveDown();
                break;
            case KeyEvent.VK_A:
                myTank.setDirection(3);
                myTank.moveLeft();
                break;
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
