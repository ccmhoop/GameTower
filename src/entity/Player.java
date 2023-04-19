package entity;

import inputs.KeyboardInputs;
import main.CollisionChecker;
import main.GamePanel;
import object.Cannonball;
import tower.TowerEntity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


public class Player extends KeyboardInputs{

    GamePanel gp;
    BufferedImage img,idl;
    boolean sideCheck;
    KeyboardInputs keyH;
    public static byte aniTimer = 0, aniloader = 0,drawAni;


    private int []lvlData;

    BufferedImage []idle = new BufferedImage[18];
    BufferedImage []run = new BufferedImage[11];
    BufferedImage []runLeft = new BufferedImage[11];
    BufferedImage []idleLeft = new BufferedImage[18];

    Entity entity = new Entity();
    CollisionChecker check;
    TowerEntity tower = new TowerEntity();


    public Player(GamePanel gp, KeyboardInputs keyH) {
        this.gp = gp;
        this.keyH = keyH;
        this.check = new CollisionChecker(gp);
        setDefaultValues();
       // getPlayerImage();
        loadPlayerAnimation();
        //entity.getXY(xP,yP);
       // entity.initHitbox();

    }
    private void loadPlayerAnimation(){
        for(int i=0;i<=17;i++){
            try{
                if(i>9){
                    System.out.println(i);
                    idle[i] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/PaladinWhite/PNG/PNG Sequences/Idle/0_Paladin_Idle_0"+i+".png")));

                }else {
                    idle[i] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/PaladinWhite/PNG/PNG Sequences/Idle/0_Paladin_Idle_00" + i + ".png")));
                }
                if(i<=10){
                    run[i] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/PaladinWhite/PNG/PNG Sequences/Running/0_Paladin_Running_"+i+".png")));
                }
                if(i<=10){
                    runLeft[i] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/PaladinWhite/PNG/PNG Sequences/RunLeft/0_Paladin_Running_"+i+".png")));
                }
                idleLeft[i] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/PaladinWhite/PNG/PNG Sequences/IdleLeft/0_Paladin_Idle_"+i+".png")));
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private void idleAni(int a,int b) {
            if (aniTimer == a) {
                {
                    aniloader++;
                }
            } else if (aniloader >= b) {
                aniloader = 0;
            }if (aniTimer>a){
                aniTimer=0;
        }
            aniTimer+=1;
    }

    public void setDefaultValues(){
        Projectile projectile = new Projectile(gp);
        sideCheck=true;
        keyH.xP=300;
        keyH.yP=550;

        //keyH.speed = 2;
    direction = "down";
    }

    public int setPlayerX(){
      return xP;
    }
    public int setPlayerY(){
        return yP;
    }

    public void loadLvlDate(int[] lvlData){
        this.lvlData = lvlData;
    }

    public void getPlayerImage(){
        try {
            this.img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/images/head.png")));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void update(){
        if(upPressed){
            direction = "up";
            keyH.yP -= keyH.speed;
        }
        else if (downPressed){
            direction = "down";
            keyH.yP += keyH.speed;
        }
        else if (leftPressed){
            direction = "left";
            keyH.xP -= keyH.speed;
            drawAni=3;
            idleAni(5, 10);
            sideCheck=false;
        }
        else if (rightPressed){
            direction = "right";
            keyH.xP += keyH.speed;
            drawAni=1;
            idleAni(5, 10);
            sideCheck=true;

        }else{
            System.out.println(aniloader);
           nonPressed = false;
            if (sideCheck) {
                drawAni = 2;
                idleAni(5, 17);
            } else {
                drawAni = 4;
                idleAni(5, 17);
            }

        }
        if(ePressed){

           // gp.projectileList.add(Entity.projectile);
        }
        //Projectile.playerPosition(keyH.xP, keyH.yP);
        //Projectile.setProjectile(keyH.xP, keyH.yP,direction);
       // System.out.println(xP+" "+yP);
        CollisionChecker.position(keyH.xP, keyH.yP);
    }


    public void draw(Graphics2D g2){

        //BufferedImage image = null,head = null,body,feet = null;
        switch (drawAni){
            case 1->g2.drawImage(run[aniloader],keyH.xP,keyH.yP, 96, 96, null);
            case 2->g2.drawImage(idle[aniloader],keyH.xP,keyH.yP, 96, 96, null);
            case 3->g2.drawImage(runLeft[aniloader],keyH.xP,keyH.yP, 96, 96, null);
            case 4->g2.drawImage(idleLeft[aniloader],keyH.xP,keyH.yP, 96, 96, null);
        }

       // entity.drawHitbox(g2);

    }

}
