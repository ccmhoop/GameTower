package background;

import background.levels.TileGen;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class BackgroundManager extends BackGroundEntity {
    GamePanel gp;
    int glideUp =1,glideDown=-1,bSpeed;
    int [] levelSpriteind = new int[11];
    public static BufferedImage[] background= new BufferedImage[15];
    int index;
    CloudAni cloudAni;
    //int cloudSwi;
    Random rnd = new Random();
    Level levelOne = new Level(levelSpriteind);
    public void cloudSpeed(){
        speed++;
        if (speed==30){
            for (int i =0;i<5;i++) {
               cS[i] = rnd.nextInt(1,2);
            }
            speed=0;
        }
    }
    public void cloudFade(){
        if(cX[0]>=2100){
            cX[0] = rnd.nextInt(-1920,-400);
            cIm[0] = rnd.nextInt(1,5);
        }
        if(cX[1]>=2100){
            cX[1] = rnd.nextInt(-1920,-400);
            cIm[1] = rnd.nextInt(1,5);
        }
        if(cX[2]>=2100){
            cX[2] = rnd.nextInt(-1920,-400);
            cIm[2] = rnd.nextInt(1,5);
        }
        if(cX[3]>=2100){
            cX[3] = rnd.nextInt(-1920,-400);
            cIm[3] = rnd.nextInt(1,5);
        }
        if(cX[4]>=2100){
            cX[4] = rnd.nextInt(-1920,-400);
            cIm[4] = rnd.nextInt(1,5);
        }
    }
    public void cloudBounce(){
        bounceS++;
        cloudB++;
        bSpeed++;
        if (bounceS>100){
            for (int i =0;i<3;i++) {
                cY[i] = rnd.nextInt(-2,2);
            }
            bounceS=0;
        }
        if (cloudB>60) {
            if (bSpeed>13) {
                glideUp--;
                glideDown++;
                cY[3] = glideUp;
                cY[4] = glideDown;
                bSpeed=0;
            }
        }
        else if (cloudB<60) {
            if (bSpeed>13) {
                glideUp++;
                glideDown--;
                cY[3] = glideUp;
                cY[4] = glideDown;
                bSpeed=0;
            }
        }
        if(cloudB>120){
            cloudB = 0;
            bSpeed=0;
        }
    }

    public BackgroundManager(GamePanel gp){
        this.gp = gp;
        getBackGroundImage();
        cloudAni = new CloudAni(gp);
       // levelOne = new Level(levelOne.getLevelData());
        System.out.println(Arrays.toString(levelOne.lvlData));
    }
    public void getBackGroundImage(){

        try {
            background[0]= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/background/1_game_background/layers/1.png")));
            background[1]= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/background/1_game_background/layers/4.png")));
            background[2] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/background/1_game_background/layers/3.png")));
            background[3] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/background/1_game_background/layers/5.png")));
            background[13] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/background/1_game_background/layers/6.png")));
            background[12] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/FantasyCartoonVillage/PNG/Building/Wide_Door_04.png")));
            background[14] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/background/1_game_background/layers/2.png")));
          // background[4] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/background/m7/2.png")));
           // background[5]= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/background/m1/4.png")));
            background[4] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/background/Mountain_BG_04/Layers/Mount_BG.png")));
            background[11]= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/background/Mountain_BG_04/Layers/Decor_01.png")));
            background[5]= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/background/Mountain_BG_04/Layers/Decor_02.png")));
            background[6] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/background/m3/5.png")));
            background[7] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/background/Mountain_BG_04/Layers/Ground.png")));
            background[8] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/FantasyCartoonVillage/PNG/Building/Wall_C_02.png")));
            //background[9] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/toren/torenPas.png")));
            background[10] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/Canon/can.png")));

        }catch(IOException e){
            e.printStackTrace();
            }
        for (int i=0;i<=10;i++){
            System.out.println(i);
            levelSpriteind[i]=i;
        }
    }
    public void update(){
        cloudSpeed();
        cloudBounce();
        cloudFade();
    }

    // array maken voor Y wolk locatie;
    public void draw(Graphics2D g2){
        g2.drawImage(background[0],0,0,1920,1056,null);
        g2.drawImage(background[14],0,0,1920,900,null);
        g2.drawImage(background[1],0,-390,1920,1080,null);
        g2.drawImage(background[13],0,-400,1920,1080,null);
        cloudAni.draw(g2,cloudAni.cloudForG(cIm[0]),cX[0] = cX[0]+cS[0],50+cY[0]);
        g2.drawImage(background[4],0,0,1920,900,null);
        g2.drawImage(background[11],0,0,1920,1080,null);
        g2.drawImage(background[5],0,50,1920,1056,null);
        //g2.drawImage(background[6],cX[3]-500,-310+cY[3],2500,1300,null);
        //g2.drawImage(background[8],200,-60,700,1200,null);
        g2.drawImage(background[10],450,380,100,50,null);
        g2.drawImage(background[9],320,300,360,520,null);
        //g2.drawImage(background[8],175,400,700,500,null);
        //g2.drawImage(background[6],cX[4]+3,-150+cY[4],3000,1200,null);
        cloudAni.draw(g2,cloudAni.cloudForG(cIm[1]),cX[1] = cX[1]+cS[1],100+cY[1]);
        g2.drawImage(background[7],0,0,1920,1080,null);
       // g2.drawImage(background[8],128*6,580,256,256,null);
        g2.drawImage(background[8],128*10,780,256,256,null);
        g2.drawImage(background[8],128*6,780,256,256,null);

        g2.drawImage(background[8],128*8,780,256,256,null);
        g2.drawImage(background[12],(128*8)+80,900,96,128,null);


    }

    public Level getCurrentLevel() {
        return levelOne;
    }
}
