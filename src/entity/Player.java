package entity;

import gravity.Gravity;
import inputs.KeyboardInputs;
import collision.CollisionChecker;
import main.GamePanel;
import java.awt.*;

public class Player extends Entity{

    GamePanel gp;
    private byte drawAnimation;
    CollisionChecker collisionChecker = new CollisionChecker();
    KeyboardInputs keyBoard;
    Gravity gravity = new Gravity();

    /*
        * All animations and key input is being reworked
        * Separating Player related code into methods
     */
    public Player(GamePanel gp, KeyboardInputs keyH) {
        this.gp = gp;
        this.keyBoard = keyH;
        setDefaultValues();
        setAnimationArray();
    }
    public void setDefaultValues(){
        playerPositionY = 740;
        playerPositionX = -30;
        sideCheck=true;
    }
    public void jump() {
        if (jumpActive && !Gravity.collision) {
            jumpCycle += 1;
            if (jumpCycle < 30) {
                playerPositionY -= 5;
            } else if (jumpCycle > 30 && jumpCycle < 60) {
                playerPositionY -= 2;
            } else if (jumpCycle > 60 && jumpCycle < 90) {
                playerPositionY += 2;
            } else if (jumpCycle > 90 && jumpCycle < 120) {
                playerPositionY += 5;
            } else if (jumpCycle == 120) {
                jumpActive = false;
                jumpCycle = 0;
            }
        }if (Gravity.collision){
            jumpActive=false;
            jumpCycle=0;
        }
    }
    public void playerInput(){
        if (keyBoard.leftPressed){
            playerPositionX -= playerSpeed;
            drawAnimation=3;
            animationLoader(5,18,29);
            sideCheck=false;
        } else if (keyBoard.rightPressed) {
            playerPositionX += playerSpeed;
            drawAnimation = 1;
            animationLoader(5,18,29);
            sideCheck = true;
        }else{
            if (sideCheck) {
                drawAnimation = 2;
            }else {
                drawAnimation = 4;
            }
            animationLoader(5,0,17);
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
        if (animationIndex>29){
            animationIndex =0;
            animationCycle=0;
        }

    }
    public void update(){
        playerInput();
    }
    public void draw(Graphics2D g2){
        switch (drawAnimation){
            case 1, 2 ->g2.drawImage(facingRightAnimation.get(animationIndex), playerPositionX, playerPositionY, 128, 128, null);
            case 3, 4 ->g2.drawImage(facingLeftAnimation.get(animationIndex), playerPositionX, playerPositionY, 128, 128, null);
        }
    }

}
