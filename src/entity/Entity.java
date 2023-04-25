package entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Entity {

    public int hx,hy,maxLife,pjSpeed,life,attack;
    public static Projectile projectile;
    public static boolean pjaLife = false;
    int width, height;
    static BufferedImage image;

    public String name;


    public Rectangle hitbox;

    public static void setImage(String a){
    try {
        image = ImageIO.read(Objects.requireNonNull(Entity.class.getResourceAsStream(a)));
    }catch (IOException e){
        e.printStackTrace();
    }
    }


    public void drawHitbox(Graphics2D g){
    g.setColor(Color.PINK);
   // g.drawRect(hx,hy, hitbox.width, hitbox.height);
    }



}
