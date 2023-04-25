package inputs;

import entity.Player;
import collision.CollisionChecker;
import object.Cannonball;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {

    public boolean collisionOn =false;
    public static String direction;
    public CollisionChecker col;
    public static int xP;
    public static int yP;
    //public static int jumpTimeCounter,airTimeCounter;
    public int speed=3;

    public static int getyP() {
        return yP;
    }

    public static void setyP(int yP) {
        KeyboardInputs.yP = yP;
    }

    public int getxP() {
        return xP;
    }

    public static void setxP(int xP) {
        KeyboardInputs.xP = xP;
    }

    public static boolean upPressed,downPressed,leftPressed,rightPressed,nonPressed,spaceBar=false,ePressed,sideCheck;//jump=false;

    public void setDirection(String a){
        switch (KeyboardInputs.direction) {
            case "up" -> upPressed=false;
            case "down" -> downPressed=false;
            case "left" -> leftPressed=false;
            case "right" -> rightPressed=false;
        }
    }

    public static void checkSpeed(int a){
        if(a==1){
           rightPressed=false;
        }
        if (a==2){
            leftPressed=false;
        }
        if (a==3){
            downPressed=false;
        }
        if (a==4){
            upPressed=false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){
            upPressed = true;
        }
        else if(code == KeyEvent.VK_S){
            downPressed = true;
        }
        else if(code == KeyEvent.VK_A){
            leftPressed = true;
        }
        else if(code == KeyEvent.VK_D){
            rightPressed = true;
        } else if (code==KeyEvent.VK_SPACE) {
            spaceBar=true;
            CollisionChecker.jump=true;
            //if (CollisionChecker.collision&& !jump){
                //jump=true;
           // }
        }

        if (code == KeyEvent.VK_E){
            ePressed = true;
            Cannonball.canshot(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){
            upPressed = false;
            Player.aniloader=0;
        }
        else if(code == KeyEvent.VK_S){
            downPressed = false;
            Player.aniloader=0;
        }
        else if(code == KeyEvent.VK_A){
            leftPressed = false;
            sideCheck=false;
            Player.aniloader=0;
        }
        else if(code == KeyEvent.VK_D) {
            rightPressed = false;
            sideCheck=true;
            Player.aniloader = 0;
        }
        else if (code==KeyEvent.VK_SPACE) {
        spaceBar=false;
         }
        if (code == KeyEvent.VK_E){
            ePressed = false;
            Cannonball.canshot(false);
        }
       // if(!upPressed&&downPressed&&leftPressed&&rightPressed){
            //nonPressed=true;
        //}
    }

}
