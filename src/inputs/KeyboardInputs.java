package inputs;

import object.Cannonball;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {
    public static String direction;
    public boolean upPressed,downPressed,leftPressed,rightPressed,nonPressed,spaceBar=false,ePressed,sideCheck;
    public void setDirection(String a){
        switch (KeyboardInputs.direction) {
            case "up" -> upPressed=false;
            case "down" -> downPressed=false;
            case "left" -> leftPressed=false;
            case "right" -> rightPressed=false;
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
        }else if (code==KeyEvent.VK_SPACE) {
            spaceBar=true;
        }else {
            nonPressed=true;
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
            sideCheck=false;
        }
        else if(code == KeyEvent.VK_D) {
            rightPressed = false;
            sideCheck=true;
        }
        else if (code==KeyEvent.VK_SPACE) {
        spaceBar=false;
        }else{
            nonPressed = true;
        }
        if (code == KeyEvent.VK_E){
            ePressed = false;
            Cannonball.canshot(false);
        }
    }

}
