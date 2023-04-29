package background;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class CloudAni extends BackGroundEntity {
    GamePanel gp;
    public BufferedImage cloudSmall;

    public BufferedImage cloudForG(int a){
        try {
            cloudSmall = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/clouds/Clouds_white/Shape2/cloud_shape2_" +a+".png")));
        }catch (IOException e){
            e.printStackTrace();
        }
        return cloudSmall;
    }

    public CloudAni (GamePanel gp){
        this.gp = gp;
    }
    public void draw(Graphics2D g2, BufferedImage cloudSmall, int x, int y){
            g2.drawImage(cloudSmall, x, y, 200, 65, null);
    }
}
