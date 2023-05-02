package entity;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Entity {
    ArrayList<BufferedImage> facingRightAnimation = new ArrayList<>();
    ArrayList<BufferedImage> facingLeftAnimation = new ArrayList<>();
    String playerAction;
    public static int playerPositionX, playerPositionY, playerSpeed = 2;
    private int timer, timeCycle, animationTimer, animationCycle, fileCycle = 1;;
    public int jumpHeight, animationIndex;
    public boolean faceRight, jumpActive;

    /*
     *Sets array : facingRightAnimation,facingLeftAnimation. Can cycle through different directories if modified--\\
     *Example increment NumberDirectory in method "animationPath(*increment*, fileCycle, "Left|Right")" compensate fileCycle if needed--\\
     */
    public void setAnimationArray() {
        while (fileCycle <= 48) {
            try {
                facingLeftAnimation.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(animationPath(1, fileCycle, "Left")))));
                facingRightAnimation.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(animationPath(1, fileCycle, "Right")))));
                fileCycle++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //--Pathing method makes it quick to find animations in separate directories --\\
    private String animationPath(int numberDirectory, int fileNumber, String directoryName) {
        return "/res/playerAnimation/" + numberDirectory + directoryName + "/" + directoryName + " " + "(" + fileNumber + ").png";
    }

    public int timeCycles(int cycleSpeed, int cycleAmount){
     timer++;
     if (timer==cycleSpeed) {
         timeCycle++;
     }
     if (timer>cycleSpeed){
         timer=0;
     }
     if (timeCycle>cycleAmount) {
         timeCycle =0;
         timer=0;
     }
        return timeCycle;
    }
    //--Controls Animation Speed and cycles through array to get the requested animations--\\
    //--For more information read animationArrayPosition.txt in "/res/playerAnimation"--\\
    public void animationCycles(int speed, int indexStart, int indexEnd) {
        animationTimer++;
        if(animationTimer==speed) {
            animationIndex = animationCycle + indexStart;
            animationCycle++;
        }
        if (animationIndex>indexEnd){
            animationCycle=0;
            animationIndex = animationCycle + indexStart;
        }
        if (animationTimer>speed) {
            animationTimer=0;
        }
    }

}

