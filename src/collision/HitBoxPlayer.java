package collision;

import main.GamePanel;

import java.awt.*;

public class HitBoxPlayer{

    GamePanel gp;
    CollisionChecker colCheck;
    int playerPosX,playerPosY;
    private final Rectangle playerHitBox;



    public HitBoxPlayer(GamePanel gp) {
        this.gp = gp;
        playerHitBox = new Rectangle();
        playerHitBox.x = playerPosX+15;
        playerHitBox.y = playerPosY+35;
        playerHitBox.width = 45;
        playerHitBox.height = 64;
    }

   public void playerHitBoxPos(){
       playerPosX = CollisionChecker.getPlayerPosX();
       playerPosY = CollisionChecker.getPlayerPosY();
       playerHitBox.x = playerPosX;
       playerHitBox.y = playerPosY;
   }

    public void update(){
        playerHitBoxPos();
        //System.out.println(playerPosX+" position "+ playerPosY);
        //System.out.println(playerHitBox.x+" hitbox "+playerHitBox.y);
    }

    public void playerHitBoxDraw(Graphics2D g2){
        g2.drawRect(playerPosX+15,playerPosY+35,45,64);
    }
}
