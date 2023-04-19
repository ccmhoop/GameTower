package effects;

import main.GamePanel;
//import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class EffectAni extends EffectEntity {
    BufferedImage explosion;
    GamePanel gp;
    //KeyHandler keyH;
    public EffectAni (GamePanel gp){
        this.gp = gp;
        explosionAniSmall();
    }
    public void update(){

        explosionAniSmall();
    }

    public void explosionAniSmall(){
        spriteCounter++;
        try {
            if (spriteCounter>2){
                spriteNum++;
                explosion = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("//explosion/Explosion_" +spriteNum+".png")));
                spriteCounter=0;
            }else if (spriteNum>=10){
                spriteNum=0;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //-------------------Animations------------------------\\

    public void draw(Graphics2D g2){

        g2.drawImage(explosion,x,y,gp.tileSize, gp.tileSize,null );
    }
}
