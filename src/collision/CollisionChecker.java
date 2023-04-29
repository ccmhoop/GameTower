package collision;


import gravity.Gravity;
import java.awt.*;
import static tile.TileManager.*;

public class CollisionChecker {
    static int playerPosX;
    static int playerPosY;
    private int predictCollisionY; //calculates player Y position 10 steps ahead. Compensates for lag;
    public void getPlayerPosition(int a,int b){
        playerPosX = a+64; // +64 compensates for image size (sets true location)
        playerPosY = b;
    }
    private void collisionCheck(){
        for (int p =335; 0 < p; p--) {
            if (collisionYaxis.get(p) ==  predictCollisionY) {
                if (!collisionBoolean.get(p) && (playerPosX > collisionLeft.get(p) && playerPosX < collisionRight.get(p))) {
                    Gravity.collision = false;
                    break;
                } else if (collisionBoolean.get(p) && (playerPosX >= (collisionLeft.get(p)) && playerPosX <= (collisionRight.get(p)))) {
                    Gravity.collision = true;
                    break;
                }
            }
        }
    }//checks for collision if predictCollisionY == collisionYaxis
    private void collisionPrediction() {
        for (int i = 10; 0 < i; i--) {
            predictCollisionY = playerPosY - i;
            if(collisionYaxis.contains(predictCollisionY)) {
                collisionCheck();
            }
        }
    }//calculates player Y position 10 steps ahead. Compensates for lag;
    public void update(){
        collisionPrediction();
    }
    public void draw(Graphics2D g2){
    }
}
