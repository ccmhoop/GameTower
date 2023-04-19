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
    BufferedImage img;
    KeyboardInputs keyH;

    private int []lvlData;
    Entity entity = new Entity();
    CollisionChecker check;
    TowerEntity tower = new TowerEntity();


    public Player(GamePanel gp, KeyboardInputs keyH) {
        this.gp = gp;
        this.keyH = keyH;
        this.check = new CollisionChecker(gp);
        setDefaultValues();
        getPlayerImage();
        //entity.getXY(xP,yP);
       // entity.initHitbox();

    }

    public void setDefaultValues(){
        Projectile projectile = new Projectile(gp);

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
        }
        else if (rightPressed){
            direction = "right";
            keyH.xP += keyH.speed;

        }
        if(ePressed){

           // gp.projectileList.add(Entity.projectile);
        }
        Projectile.playerPosition(keyH.xP, keyH.yP);
        Projectile.setProjectile(keyH.xP, keyH.yP,direction);
       // System.out.println(xP+" "+yP);
        CollisionChecker.position(keyH.xP, keyH.yP);
    }


    public void draw(Graphics2D g2){

        //BufferedImage image = null,head = null,body,feet = null;
        getPlayerImage();
         g2.drawImage(img,keyH.xP,keyH.yP, 48, 48, null);

        //g2.drawImage(img,keyH.xP,keyH.yP, (0-48|0+48), gp.tileSize, null);
       // g2.drawImage(head,(int)x,(int)y, gp.tileSize, gp.tileSize, null);
       // entity.drawHitbox(g2);

    }

}
