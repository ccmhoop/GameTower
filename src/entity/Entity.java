package entity;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Entity {
    ArrayList<BufferedImage> facingRightAnimation = new ArrayList<>();
    ArrayList<BufferedImage> facingLeftAnimation = new ArrayList<>();
    public static int playerPositionX,playerPositionY,playerSpeed=2;
    public int animationIndex,jumpCycle;
    public int animationTimer = 0,animationCycle;
    public boolean sideCheck,jumpActive;
    private int fileCycle = 1;

    /*
     *Sets array : facingRightAnimation,facingLeftAnimation. Can cycle through different directories if modified--\\
     *Example increment NumberDirectory in animationPath(*increment*, fileCycle, "Left|Right") compensate fileCycle if needed--\\
     */
    public void setAnimationArray() {
        while (fileCycle <=30) {
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
    public static String animationPath(int numberDirectory,int fileNumber,String directoryName){
        return "/res/playerAnimation/"+numberDirectory+directoryName+"/"+directoryName+" "+"("+fileNumber+").png";
   }

   //--Controls Animation Speed and cycles through array to get the requested animations--\\
   //--For more information read animationArrayPosition.txt in "/res/playerAnimation"--\\
    public void animationLoader(int speed, int indexStart, int indexEnd) {
        if (animationTimer==speed) {
            animationCycle++;
            animationTimer=0;
        } else if (animationIndex==indexEnd){
            animationCycle=0;
        }
        animationIndex=animationCycle+indexStart;
        animationTimer+=1;
    }
}
