package collision;

import gravity.Gravity;
import static tile.TileManager.*;

public class CollisionChecker extends Collision {
    private static int player_collision_X, player_collision_Y;
    private int predict_collision_Y; //*used to calculate player Y position 10 steps ahead. Compensates for lag
    //*gets player Position to check for collision
    public void getPlayerPosition(int positionX,int positionY){
        player_collision_X = positionX+64; //*+64 compensates for image size (sets true location)
        player_collision_Y = positionY;
    }
    //*calculates player Y position 10 steps ahead. Compensates for lag. if condition are met load collisionCheck();
    private void collisionPrediction() {
        for (int i = 10; 0 < i; i--) {
            predict_collision_Y = player_collision_Y - i;
            if(tile_Yaxis_collision.contains(predict_collision_Y)) {
                collisionCheck();
            }
        }
    }
    //*checks for collision if predictCollisionY == collisionYaxis
    private void collisionCheck(){
        for (int index=335;0<index;index--) {
            if (tile_Yaxis_collision.get(index)== predict_collision_Y) {
                if (!tile_collision_boolean.get(index) && (player_collision_X > tile_left_collision.get(index) && player_collision_X < tile_right_collision.get(index))) {
                    Gravity.toggle_gravity = false;
                    break;
                } else if (tile_collision_boolean.get(index)&&(player_collision_X >=(tile_left_collision.get(index))&& player_collision_X <=(tile_right_collision.get(index)))) {
                    Gravity.toggle_gravity = true;
                    break;
                }
            }
        }
    }
    public void update(){
        collisionPrediction();
    }
}
