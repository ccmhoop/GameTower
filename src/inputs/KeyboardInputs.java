package inputs;

import entity.Entity;
import main.CollisionChecker;
import object.Cannonball;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {

    public static String direction;
    public CollisionChecker col;
    public int xP,yP;
    public int speed=2;

    public static boolean upPressed,downPressed,leftPressed,rightPressed,ePressed;

    public static void setSpeed(){

    }

    public static void checkSpeed(int a){
        if(a==1){
           rightPressed=false;
            System.out.println("da");
        }
        if (a==2){
            System.out.println("Daddy");
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
        }
        else if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        else if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        else if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
        if (code == KeyEvent.VK_E){
            ePressed = false;
            Cannonball.canshot(false);
        }
    }

}
