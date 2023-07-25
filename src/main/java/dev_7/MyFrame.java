package dev_7;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

/**
 * @author Xiang Weng
 */
public class MyFrame extends JFrame {
    MyPanel p;

    public static void main(String[] args) {
        new MyFrame();
    }
    public MyFrame() {
        System.out.println("0: 下一把 \t1:继续造");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        p = new MyPanel(s);
        Thread thread = new Thread(p);
        thread.start();
        this.add(p);
        this.setSize(1000,750);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.addKeyListener(p);
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Recorder.save();
                System.exit(0);
            }
        });
    }
}
