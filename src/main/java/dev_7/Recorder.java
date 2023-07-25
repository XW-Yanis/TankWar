package dev_7;

import java.io.*;
import java.util.Vector;

/**
 * @author Xiang Weng
 */
public class Recorder implements Serializable {
    private static int hitCount = 0;
    private static boolean leftGame = false;

    public static Vector<EnemyTank> getEnemyTanks() {
        return enemyTanks;
    }

    public static boolean hasLeftGame() {
        return leftGame;
    }

    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        Recorder.enemyTanks = enemyTanks;
    }

    private static Vector<EnemyTank> enemyTanks;

    public static int getHitCount() {
        return hitCount;
    }

    public static void setHitCount(int hit) {
        hitCount = hit;
    }

    private static BufferedReader br = null;
    private static BufferedWriter bw = null;
    private static final String PATH = "src\\record.txt";
    public static String getPath(){
        return PATH;
    }
    public static void increment() {
        hitCount++;
    }

    public static void save() {
        try {
            if (bw == null) bw = new BufferedWriter(new FileWriter(PATH));
            bw.write(hitCount + "\n");
            for (int i = 0; i < enemyTanks.size(); i++) {
                EnemyTank tank = enemyTanks.get(i);
                String temp = tank.getX() + " " + tank.getY() + " " + tank.getDirection();
                bw.write(temp + "\n");
                leftGame = true;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void load() {
        if (enemyTanks == null) enemyTanks = new Vector<>();
        try {

            br = new BufferedReader(new FileReader(PATH));
            hitCount = Integer.parseInt(br.readLine());
            String line;
            while ((line = br.readLine()) != null) {
                String[] s = line.split(" ");
                EnemyTank tank = new EnemyTank(Integer.parseInt(s[0]), Integer.parseInt(s[1]));
                tank.setDirection(Integer.parseInt(s[2]));
                enemyTanks.add(tank);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
