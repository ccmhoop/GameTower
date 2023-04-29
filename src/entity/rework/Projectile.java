package entity.rework;

import entity.Entity;
import inputs.KeyboardInputs;
import main.GamePanel;
import object.Cannonball;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;;

public class Projectile extends Entity {

    public static int pjX, pjY, pjSpeed,pjCounter;
    public int pjAttack;
    public static ArrayList<BufferedImage> pjImage = new ArrayList<>();
    public static ArrayList<Integer> pjLife = new ArrayList<>();
    public static ArrayList<String> pjName = new ArrayList<>();
    public static ArrayList<Integer> ppjX = new ArrayList<>();
    public static ArrayList<Integer> ppjY = new ArrayList<>();

    public static void pjArrayTrim(int a){
        pjLife.remove(a);
        ppjX.remove(a);
        ppjY.remove(a);
        pjName.remove(a);
        pjImage.remove(a);
    }

    public static void getPjPosition() {
        switch (KeyboardInputs.direction) {
            case "up" -> pjY -= pjSpeed;
            case "down" -> pjY += pjSpeed;
            case "left" -> pjX -= pjSpeed;
            case "right" -> pjX += pjSpeed;
        }
    }
    public static void playerPosition(int a, int b) {
        pjX = a;
        pjY = b;
    }
    public void update() {
        Cannonball.canReload();
    }

    public static void canDraw(Graphics2D g,int a,int b,int c,int e){
        for (int i = 0; i <e; i++) {
            g.drawImage(pjImage.get(a), b, c, 25, 25, null);
        }
    }
    public void draw(Graphics2D g) {
            try {
            Cannonball.cannonballPj(g);
            } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            }

        }
    }

