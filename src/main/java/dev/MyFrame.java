package dev;

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
        this.add(p);
        this.setSize(800,600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
