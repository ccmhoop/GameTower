package entity;

import gravity.Gravity;
import inputs.KeyboardInputs;
import collision.CollisionChecker;
import main.GamePanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


public class Player extends Entity{

    //All animations and key input is being reworked
    GamePanel gp;
    public static byte drawAni;
    static BufferedImage []idle = new BufferedImage[18];
    static BufferedImage []run = new BufferedImage[11];
    static BufferedImage []runLeft = new BufferedImage[11];
    static BufferedImage []idleLeft = new BufferedImage[18];
    static BufferedImage []jump = new BufferedImage[4];
    CollisionChecker collisionChecker = new CollisionChecker();
    KeyboardInputs keyBoard;

    Gravity gravity = new Gravity();


    public Player(GamePanel gp, KeyboardInputs keyH) {
        this.gp = gp;
        this.keyBoard = keyH;
        setDefaultValues();
        loadPlayerAnimation();;
    }

    private void loadPlayerAnimation(){
        for(int i=0;i<=17;i++){
            try{
                if(i>9){
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
    }//rework in progress

    public void setDefaultValues(){
        playerPositionY = 770;
        playerPositionX = -30;
        sideCheck=true;
    }

    public void update(){
        if (keyBoard.leftPressed){
            playerPositionX -= playerSpeed;
            drawAni=3;
            idleAni(5, 10);
            sideCheck=false;
        } else if (keyBoard.rightPressed) {
            playerPositionX += playerSpeed;
            drawAni = 1;
            idleAni(5, 10);
            sideCheck = true;
     }else{
            if (sideCheck) {
                drawAni = 2;
                idleAni(5, 17);
            }else {
                drawAni = 4;
                idleAni(5, 17);
            }
        }
      if (keyBoard.spaceBar) {
          if (Gravity.collision){
              jumpActive = true;
              Gravity.collision = false;
          }
      }
      if (jumpActive){
          jump();
      }else{
          playerPositionY=gravity.gravity(playerPositionY);
      }
      collisionChecker.getPlayerPosition(playerPositionX,playerPositionY);
    }//Reworking. Hold spacebar to go through a platform tap spacebar to jump on top of a platform

    public void draw(Graphics2D g2){
        switch (drawAni){
            case 1->g2.drawImage(run[aniloader], playerPositionX, playerPositionY, 128, 128, null);
            case 2->g2.drawImage(idle[aniloader], playerPositionX, playerPositionY, 128, 128, null);
            case 3->g2.drawImage(runLeft[aniloader], playerPositionX, playerPositionY, 128, 128, null);
            case 4->g2.drawImage(idleLeft[aniloader], playerPositionX, playerPositionY, 128, 128, null);
            case 5->g2.drawImage(jump[aniloader], playerPositionX, playerPositionY, 128, 128, null);
        }
    }//Reworking into method

}
