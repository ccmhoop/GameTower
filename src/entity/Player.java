package entity;

import gravity.Gravity;
import inputs.KeyboardInputs;
import collision.CollisionChecker;
import main.GamePanel;
import java.awt.*;

public class Player extends Entity{

    GamePanel gp;
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

    private void setDefaultValues(){
        playerAction ="idle";
        playerPositionY = 740;
        playerPositionX = -30;
        faceRight =true;
    }

    private void jump(){

        switch (timeCycles(9,9)) {
            //windup\\
            case (3),(4)-> {Gravity.collision = false;playerPositionY -= 5;jumpHeight -= 5;}
            case (5), (6) -> {playerPositionY -= 2;jumpHeight -= 2;}
            case (7), (8) -> {playerPositionY -= 1;jumpHeight -= 1;}
            case (9)->{jumpActive=false;Gravity.collision=false;}
        }
    }

    private void requestPlayerAnimation(){
        if(!Gravity.collision){
            animationCycles(15,42,47);
        } else {
            switch (playerAction) {
                case ("idle") -> animationCycles(5, 0, 17);
                case ("run") -> animationCycles(5, 18, 29);
            }
       }
    }

    public void playerInput(){

        if (keyBoard.leftPressed){
            playerAction = "run";
            playerPositionX -= playerSpeed;
            faceRight =false;
        } else if (keyBoard.rightPressed) {
            playerAction = "run";
            playerPositionX += playerSpeed;
            faceRight = true;
        }  else{
            playerAction = "idle";
        }if (keyBoard.spaceBar) {
            if (Gravity.collision){
                jumpActive = true;
            }
        }
        if (jumpActive) {
            jump();
        }else{
            playerPositionY=gravity.gravity(playerPositionY);
        }
        collisionChecker.getPlayerPosition(playerPositionX,playerPositionY);
    }

    private void playerAnimationDrawer(Graphics2D g2){
        if (faceRight) {
            g2.drawImage(facingRightAnimation.get(animationIndex), playerPositionX, playerPositionY, 128, 128, null);
        } else {
            g2.drawImage(facingLeftAnimation.get(animationIndex), playerPositionX, playerPositionY, 128, 128, null);
        }
    }

    public void update(){
        playerInput();
        requestPlayerAnimation();
    }

    public void draw(Graphics2D g2){
        playerAnimationDrawer(g2);
    }

}
