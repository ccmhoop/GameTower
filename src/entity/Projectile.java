package entity;

import inputs.KeyboardInputs;
import main.GamePanel;
import object.Cannonball;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;

public class Projectile extends Entity {

    public static int pjX, pjY, pjSpeed,pjCounter;
    public int pjAttack;
    public static int pjCount;

    GamePanel gp;
    public static ArrayList<BufferedImage> pjImage = new ArrayList<>();

    public static Integer []pjType = {0};
    public static ArrayList<Integer> pjLife = new ArrayList<>();
    public static ArrayList<String> pjName = new ArrayList<>();
    public static ArrayList<Integer> ppjX = new ArrayList<>();
    public static ArrayList<Integer> ppjY = new ArrayList<>();


    Entity user;

    public Projectile(GamePanel gp) {

    }
    public static int pjSpeed(int a){
      // for ()
        return a;
    }
    public static void pjArrayTrim(int a){
        pjLife.remove(a);
        ppjX.remove(a);
        ppjY.remove(a);
       pjName.remove(a);
        pjImage.remove(a);
       //pjLife.trimToSize();
       // ppjY.trimToSize();
      // ppjX.trimToSize();
       // pjImage.trimToSize();
      // pjName.trimToSize();
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

    public static void setProjectile(int worldX, int worldY, String direction) {

    }


    public void update() {
        //Cannonball.canSpeed();
       // Cannonball.cannonballPj();
        Cannonball.canReload();


    }

    public static void canDraw(Graphics2D g,int a,int b,int c,int e){

        /*
        for (int i = 0; i <pjImage.size(); i++) {
            g.drawImage(pjImage.get(a), b, c, 48, 48, null);
        }
         */
        for (int i = 0; i <e; i++) {
            g.drawImage(pjImage.get(a), b, c, 25, 25, null);
        }
    }

    public void draw(Graphics2D g) {


            try {
            Cannonball.cannonballPj(g);

               //for (int i = 0; i <pjName.size(); i++) {
                   // g.drawImage(pjImage.get(i), ppjX.get(i), ppjY.get(i), 48, 48, null);
              // }
            } catch (IndexOutOfBoundsException e) {

            }

        }
    }

