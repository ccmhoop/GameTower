package entity;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Entity {
    ArrayList<BufferedImage> facing_right_animation = new ArrayList<>();
    ArrayList<BufferedImage> facing_left_animation = new ArrayList<>();
    String player_action;
    public int player_position_Xaxis, player_position_Yaxis,player_speed = 2;
    private int timer, time_cycle, animation_timer, animation_cycle, file_cycle = 1;;
    public int animation_index;
    public boolean face_right, jump_activated;

    /*
     *Sets array : facingRightAnimation,facingLeftAnimation. Can cycle through different directories if modified
     *Example increment NumberDirectory in method "animationPath(*increment*, fileCycle, "Left|Right")" compensate fileCycle if needed
     */
    public void setAnimationArray() {
        while (file_cycle <= 48) {
            try {
                facing_left_animation.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(animationPngPath(1, file_cycle, "Left")))));
                facing_right_animation.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(animationPngPath(1, file_cycle, "Right")))));
                file_cycle++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //*Pathing method makes it quick to find animations in separate directories
    private String animationPngPath(int numberDirectory, int fileNumber, String directoryName) {
        return "/res/playerAnimation/" + numberDirectory + directoryName + "/" + directoryName + " " + "(" + fileNumber + ").png";
    }

    public int timeCycles(int cycleSpeed, int cycleAmount){
     timer++;
     if (timer==cycleSpeed) {
         time_cycle++;
     }
     if (timer>cycleSpeed){
         timer=0;
     }
     if (time_cycle >cycleAmount) {
         time_cycle =0;
         timer=0;
     }
        return time_cycle;
    }
    /*
     *Controls Animation Speed and cycles through array to get the requested animations
     *For more information read animationArrayPosition.txt in "/res/playerAnimation"
     */
    public void animationPngCycles(int speed, int indexStart, int indexEnd) {
        animation_timer++;
        if(animation_timer ==speed) {
            animation_index = animation_cycle + indexStart;
            animation_cycle++;
        }
        if (animation_index >indexEnd){
            animation_cycle =0;
            animation_index = indexStart;
        }
        if (animation_timer >speed) {
            animation_timer =0;
        }
    }

}

