package entity;

import gravity.Gravity;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Entity {

    public int playerPositionX,playerPositionY,playerSpeed=2;
    public static byte animationTimer = 0, animationloader;
    private int jumpCycle;
    public static boolean sideCheck,jumpActive;
    public static int animationIndex,animation=1;

    public String name;

    public static void main(String[] args) {
        setAniArray();
    }// I am using this to check if the new animation loader gets errors.
    public static void setAniArray(){
        int c = 1;
        int d =0;
        boolean loop = true;

        System.out.println(Player.idle.length);
        try {
            while (loop) {
                switch (animation) {
                    case (1) -> {
                        System.out.println("lcs "+animationIndex);
                        setAniArray(5);
                        Player.idle[animationIndex] = ImageIO.read(Objects.requireNonNull(Entity.class.getResourceAsStream("/res/PaladinWhite/PNG/PNG Sequences/Idle/0_Paladin_Idle_00" + animationIndex + ".png")));
                    }
                    case (2) -> {
                        setAniArray(5);
                        Player.idle[animationIndex] = ImageIO.read(Objects.requireNonNull(Entity.class.getResourceAsStream("/res/PaladinWhite/PNG/PNG Sequences/Idle/0_Paladin_Idle_00" + animationIndex + ".png")));
                    }
                    case (3) -> {
                        System.out.println("yo");
                        loop = false;
                        Player.runLeft[animationIndex] = ImageIO.read(Objects.requireNonNull(Entity.class.getResourceAsStream("/res/PaladinWhite/PNG/PNG Sequences/Idle/0_Paladin_Idle_00" + animationIndex + ".png")));
                    }
                            case (4) ->
                            Player.run[animationIndex] = ImageIO.read(Objects.requireNonNull(Entity.class.getResourceAsStream("/res/PaladinWhite/PNG/PNG Sequences/Idle/0_Paladin_Idle_00" + animationIndex + ".png")));
                    case (5) ->
                            Player.jump[animationIndex] = ImageIO.read(Objects.requireNonNull(Entity.class.getResourceAsStream("/res/PaladinWhite/PNG/PNG Sequences/Idle/0_Paladin_Idle_00" + animationIndex + ".png")));
                        }
                //c++;
                //d++;
            }
            }catch(IOException e){
                e.printStackTrace();
            }
        } //reworking for better animation loading
    public static void setAniArray(int a){
        //for (int i =0;i<=a;i++){
            animationIndex ++;
           // System.out.println(animationIndex);
       // }
        if (a == animationIndex){
            animationIndex=0;
            animation++;
        }
    } //reworking for better animation loading
    public void idleAni(int a,int b) {
        if (animationTimer == a) {
            animationloader++;
        } else if (animationloader >= b){
            animationloader = 0;
        }
        if (animationTimer>a){
            animationTimer=0;
        }
        animationTimer+=1;
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
    }//Hold spacebar to go through a platform tap spacebar to jump on top of a platform
}
