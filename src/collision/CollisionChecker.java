package collision;

import background.BackgroundManager;
import entity.Entity;
import entity.Player;
import inputs.KeyboardInputs;
import main.GamePanel;
import tile.TileManager;
import tower.Tower;
import tower.TowerEntity;

import java.awt.*;

public class CollisionChecker {
    GamePanel gp;
    static  int health = 100;
    static int a,xPp,yPp;
    static int playerPosX;
    static int playerPosY;


    KeyboardInputs keyH = new KeyboardInputs();
    Entity ent = new Entity();
    HitBoxPlayer hitBoxPlayer;
    TowerEntity te;
    Player play;

    public static int jumpTimeCounter,airTimeCounter,gravCount,graveCompansate,gravTimer;

    static boolean gravity = true;
    public static boolean jump =false;

    public static boolean collision=false;

    public CollisionChecker(GamePanel gp){
        this.te = new TowerEntity();
        this.gp = gp;
        hitBoxPlayer = new HitBoxPlayer(gp);

    }

    public static void setGraveCompansate(int graveCompansate) {
        CollisionChecker.graveCompansate = graveCompansate;
    }

    public static void jump(int a) {
        System.out.println(airTimeCounter);
            if (a<=40&&jump&&collision){
                jumpTimeCounter += 1;
                if (jumpTimeCounter>=1) {
                    playerPosY -= 5 ;
                    //playerPosX +=1;
                   // KeyboardInputs.setxP(playerPosX);
                    KeyboardInputs.setyP(playerPosY);
                    jumpTimeCounter = 0;
                }
            } else if (a>55) {
                jumpTimeCounter = 0;
                airTimeCounter = 0;
                jump=false;
                gravity = true;
                System.out.println(jump);

            }
    }

        public static void position(int a, int b){
       // xPp=a;
      // yPp=b;
        }


    public static void graveCompansate() {
        if (gravCount==2){
            for (int i =10;0<i;i--){
                graveCompansate = playerPosY-i;
                if (TileManager.tileColCheckY.contains(graveCompansate)){
                    checkTile(graveCompansate);
                    setGraveCompansate(graveCompansate);
                } else if (TileManager.tileColCheckY.contains(graveCompansate)&&collision) {
                    setGraveCompansate(graveCompansate);
                    gravity=false;

                }
            }
        }

    }

    public void playerPosition(){

        if (KeyboardInputs.leftPressed) {
            playerPosX = KeyboardInputs.xP+25;
            playerPosY = KeyboardInputs.yP;
        } else if (KeyboardInputs.rightPressed) {
            playerPosX = KeyboardInputs.xP+30;
            playerPosY = KeyboardInputs.yP;
        } else if (!KeyboardInputs.nonPressed) {
            if(KeyboardInputs.sideCheck) {
                playerPosX = KeyboardInputs.xP+25;
            }
            else {
                playerPosX = KeyboardInputs.xP+30;
            }
            playerPosY = KeyboardInputs.yP;
        }
    }

    public static void gravity(){
        //System.out.println(gravity);
        if (gravity) {
            gravCount+=1;
            playerPosY+=5;
            if(gravCount==2){
                graveCompansate();
                gravCount=0;
            }
            //System.out.println(gravCount);
            KeyboardInputs.setyP(playerPosY);// System.out.println(playerPosY);
        }else if(jump){
            airTimeCounter++;
            jump(airTimeCounter);
        }

    }

    public static int getPlayerPosX() {
        return playerPosX;
    }

    public static int getPlayerPosY() {
        return playerPosY;
    }


    public static void checkTile(int a) {
        int d;
        int op;
        if (TileManager.tileColCheckY.contains(a)) {
            System.out.println(TileManager.tileColBool.get(96));
            d = TileManager.tileColCheckY.indexOf(a);
            int e = playerPosY;
            int o = TileManager.tileColCheckY.get(d);
            int xRight = TileManager.tileColCheckX.get(d);
            int xLeft = TileManager.tileColCheckXLeft.get(d);
            //System.out.println(xLeft);
            //System.out.println(TileManager.tileColBool.get(d)+" "+TileManager.tileColBool.get(130));
            try {
                if ((o == a || e >= o) && TileManager.tileColBool.get(d)) {
                    //System.out.println(o + " " + playerPosY + " " + gravCount);
                    collision = true;
                    gravity = false;
                } else {
                    collision = false;
                    gravity = true;
                }
            } catch (IndexOutOfBoundsException f) {
                f.printStackTrace();
            }
        }
    }

    //}




    public static boolean checkTower(){
       // a=0;
       // xPp-=2;
        if ((xPp>=375 && xPp<=560) && (yPp>=390 && yPp<=685)&&health>0) {

            if  ((xPp == 380))  {
               // a = 1;
               // KeyboardInputs.checkSpeed(a);
                //KeyboardInputs.setDirection("right");
            }
            if ((xPp ==560)) {
               // a = 2;
               // KeyboardInputs.checkSpeed(a);
                //KeyboardInputs.setDirection("left");
            }
            if ((yPp==390)&&(xPp >= 380 && xPp <= 560)) {
              //  a = 3;
                //KeyboardInputs.checkSpeed(a);
                //KeyboardInputs.setDirection("down");
            }
            if ((yPp==675)&&(xPp >= 380 && xPp <= 560)) {
               // a = 4;
                //KeyboardInputs.checkSpeed(a);
            }
            // TowerEntity.towerCol=true;
            //health -= 11;
            Tower.towerHp=health;
            if (health <0){
                BackgroundManager.background[9]=null;
                return true;
            }
            return true;
        }
        return false;
    }

   /*
    public void tilesCol() {

        if (playerPosY == 1054) {
            KeyboardInputs.upPressed = false;
            KeyboardInputs.downPressed= false;
            collision=true;
        }
        else if(playerPosY<941) {
            if (KeyboardInputs.jump){

            }else {
                collision=false;
                playerPosY += 2;
               KeyboardInputs.setyP(playerPosY);
            }
        }


    }

    */
    public void update(){
        //playerPosition();
        //checkTile();
       // jump();
        gravity();
        //checkTile();

        playerPosition();

        //tilesCol();

        //System.out.println(playerPosX+" position "+ playerPosY);
    }

    public void draw(Graphics2D g2){
        hitBoxPlayer.playerHitBoxDraw(g2);
        hitBoxPlayer.update();
    }


}
