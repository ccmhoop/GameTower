package tower;

import entity.Player;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tower extends TowerEntity{
    GamePanel gp;
    public static int towerHp=100;
    public Tower (GamePanel gp){
        this.gp = gp;
        if (towerHp>0) {
            towerHitbox = new Rectangle();
            towerHitbox.x = 430;
            towerHitbox.y = 368;
            towerHitbox.width = 130;
            towerHitbox.height = 260;
        }
    }

    public void update(){

    }
    public void draw(Graphics2D g2){

        g2.drawRect(430,428,130,260);
    }
}
