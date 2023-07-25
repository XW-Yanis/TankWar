package dev_4;

import javax.swing.*;

/**
 * @author Xiang Weng
 */
public class MyFrame extends JFrame {
    MyPanel p;

    public static void main(String[] args) {
        new MyFrame();
    }
    public MyFrame() {
        p = new MyPanel();
        Thread thread = new Thread(p);
        thread.start();
        this.add(p);
        this.setSize(800,600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.addKeyListener(p);
        this.setVisible(true);

    }
}
