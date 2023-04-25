package background.levels;

import background.BackgroundManager;
import background.Level;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class TileGen {

    GamePanel gp;

    boolean tilePlace=true,loaded=false;
    ArrayList<BufferedImage> levelOne = new ArrayList<>();
    public static Rectangle tiler;
    ArrayList<Integer> xAxis = new ArrayList<>();
    ArrayList<Integer> tilecCount = new ArrayList<>();
   ArrayList<Integer> yAxis = new ArrayList<>();
    int []lvlTiles = {25};


    ArrayList<Rectangle> collision = new ArrayList<>();

    public TileGen(GamePanel gp) {
        this.gp = gp;
        tiler = new Rectangle();
        tiler.y = 1015;
        tiler.width = 96;
        tiler.height = 96;
        //this.tilePlacer();
    }
/*
    private void tilePlacer(){
        int i =0;
        try {
            if(tilePlace) {
                xAxis.add(-48);
                do {
                //for (i = 0; i <tilecCount.size()-1; i++) {
                    tilecCount.add(i);
                    levelOne.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/FantasyCartoonVillage/PNG/Platformer/Ground_06.png"))));
                    levelOne.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/FantasyCartoonVillage/PNG/Platformer/Ground_02.png"))));
                    xAxis.add(xAxis.get(i)+48);
                    yAxis.add(984);
                    //tile = new Rectangle();
                    //tile.x = xAxis.get(i)+96;
                   // tile.y = 984;
                   // tile.width = 96;
                   // tile.height = 96;
                    i++;
                }while (i<900);
                tilePlace=false;
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void draw(Graphics2D g2){
        for (int i =0;i<25;i++) {
            g2.drawImage(levelOne.get(tilecCount.get(i)), xAxis.get(i), yAxis.get(i), 48, 48, null);
           // g2.drawRect(xAxis.get(i),yAxis.get(i),96,96);


        }
    }

 */

}
