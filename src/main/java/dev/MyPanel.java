package dev;

import javax.swing.*;
import java.awt.*;

/**
 * @author Xiang Weng
 */
public class MyPanel extends JPanel  {
    MyTank myTank;
    public MyPanel(){
        myTank=new MyTank(100,100);
    }

    @Override
    protected void paintBorder(Graphics g) {
        super.paintBorder(g);
        g.fillRect(0,0,800,600);
        drawTank(myTank.getX(), myTank.getY(), g, 0,0);
    }

    /**
     *
     * @param x 坦克左上角的横坐标
     * @param y 左上角纵坐标
     * @param g 画笔
     * @param direction 面朝的方向
     * @param type 坦克类型，玩家坦克？敌方坦克?
     */
    public void drawTank(int x, int y, Graphics g, int direction, int type){
        // 根据不同类型坦克设置颜色
        switch (type){
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
        switch (direction){
            //朝北
            case 0:
                g.fill3DRect(x, y,10,60,false);//左边履带
                g.fill3DRect(x+30,y,10,60,false); //右边履带
                g.fill3DRect(x+10,y+10,20,40,false);// 坦克主体
                g.fillOval(x+10,y+20,20,20); // 坦克盖子
                g.drawLine(x+20,y+30, x+20, y); // 炮管
                break;

            default:
                System.out.println("...");
                break;
        }
    }
}
