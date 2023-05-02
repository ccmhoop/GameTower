package collision;

import gravity.Gravity;
import java.awt.*;
import static tile.TileManager.*;

public class CollisionChecker {
    private static int playerPosX,playerPosY;
    private int predictCollisionY; //--used to calculate player Y position 10 steps ahead. Compensates for lag;--\\

    public void getPlayerPosition(int positionX,int positionY){
        playerPosX = positionX+64; //-- +64 compensates for image size (sets true location)--\\
        playerPosY = positionY;
    }

    //--calculates player Y position 10 steps ahead. Compensates for lag. if condition are met load collisionCheck();--\\
    private void collisionPrediction() {
        for (int i = 10; 0 < i; i--) {
            predictCollisionY = playerPosY - i;
            if(collisionYaxis.contains(predictCollisionY)) {
                collisionCheck();
            }
        }
    }
    //--checks for collision if predictCollisionY == collisionYaxis--\\
    private void collisionCheck(){
        for (int index=335;0<index;index--) {
            if (collisionYaxis.get(index)==predictCollisionY) {
                if (!collisionBoolean.get(index) && (playerPosX>collisionLeft.get(index) && playerPosX < collisionRight.get(index))) {
                    Gravity.collision = false;
                    break;
                } else if (collisionBoolean.get(index)&&(playerPosX>=(collisionLeft.get(index))&&playerPosX<=(collisionRight.get(index)))) {
                    Gravity.collision = true;
                    break;
                }
            }
        }
    }

    public void update(){
        collisionPrediction();
    }

}
