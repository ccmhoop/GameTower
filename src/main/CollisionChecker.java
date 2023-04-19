package main;

import background.BackgroundManager;
import entity.Entity;
import entity.Player;
import inputs.KeyboardInputs;
import tower.Tower;
import tower.TowerEntity;

public class CollisionChecker {
    GamePanel gp;
    static  int health = 100;
    static int a,xPp,yPp;




    KeyboardInputs keyH = new KeyboardInputs();
    Entity ent = new Entity();
    TowerEntity te = new TowerEntity();
    Player play;

    public CollisionChecker(GamePanel gp){
        this.te = new TowerEntity();
        this.gp = gp;
    }
        public static void position(int a, int b){
        xPp=a;
        yPp=b;
        }

    public static boolean checkTower(){
        a=0;
        xPp-=2;
        if ((xPp>=375 && xPp<=560) && (yPp>=390 && yPp<=685)) {

            if  ((xPp == 380))  {
                a = 1;
                KeyboardInputs.checkSpeed(a);
            }
            if ((xPp ==560)) {
                a = 2;
                KeyboardInputs.checkSpeed(a);
            }
            if ((yPp==390)&&(xPp >= 380 && xPp <= 560)) {
                a = 3;
                KeyboardInputs.checkSpeed(a);
            }
            if ((yPp==685)&&(xPp>= 380 && xPp <= 560)) {
                a = 4;
                KeyboardInputs.checkSpeed(a);
            }
            TowerEntity.towerCol=true;
            health -= 11;
            Tower.towerHp=health;
            if (health <0){
                BackgroundManager.background[9]=null;
                return true;
            }
            return true;
        }
        return false;
    }


    public void update(){
        checkTower();
    }


}
