package tile;

import main.GamePanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    int tileLoadCounter;
    int[][] mapTileNum;

    public static ArrayList <Integer>  collisionYaxis = new ArrayList<>();
    public  static ArrayList <Integer> collisionRight = new ArrayList<>();
    public  static ArrayList <Integer> collisionLeft = new ArrayList<>();
    public  static ArrayList <Boolean> collisionBoolean = new ArrayList<>();

    /*
        * I tried to make a collision system that dint require calculations. This allows me to make maps quick.
          It's important to me that its only being checked if requirements are met instead of it being processed every
          screen draw or update.

        * I want to make in such away that in future updates I can add maps without breaking the code .

        * My concept is : if the player Y position is in collisionYaxis. (This checks if the Player Y position is on top of a tile);
          Check if collisionBool = true and Player X position is in between collisionLeft && collisionRight
          if true gravity is disabled

        * Short version if collisionBoolean = true use the de index number to check if playerX location is in between collisionLeft/Right

        * It is working so far
        * I need to refine the collisionLoader(); to make it more efficient for different maps

        *Jump mechanic : Hold space to go through a platform tap/release space to jump on top of a platform .
    */

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
        getTileImage();
        loadMap();
    }

    public void getTileImage() {
        try {
            //tile[0] = invisible;
            tile[0] = new Tile();

            tile[1] = new Tile();
            tile[1].tileLoader = (ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/FantasyCartoonVillage/PNG/Platformer/Ground_06.png"))));

            tile[2] = new Tile();
            tile[2].tileLoader = (ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/FantasyCartoonVillage/PNG/Platformer/Ground_02.png"))));

            tile[3]= new Tile();
            tile[3].tileLoader=(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/FantasyCartoonVillage/PNG/Building/Stone_Window_01.png"))));

            tile[4]= new Tile();
            tile[4].tileLoader=(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/FantasyCartoonVillage/PNG/Building/Door_01.png"))));

            tile[5]= new Tile();
            tile[5].tileLoader=(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/FantasyCartoonVillage/PNG/Building/Roof_A_02.png"))));

            tile[6]= new Tile();
            tile[6].tileLoader=(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/FantasyCartoonVillage/PNG/Building/Canopy_02.png"))));

        } catch (IOException e) {
            e.printStackTrace();
        }
    } //loads in images. tiles 80 by 80. this is going to be looped when all images are known; .
    public void loadMap(){
        try(InputStream is = getClass().getResourceAsStream("/res/maps/map1.txt")) {
           assert is != null;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;
            while (col < gp.maxScreenCol && row< gp.maxScreenRow){
                String line = br.readLine();
                while (col< gp.maxScreenCol){
                    String[] tileLoadNumb = line.split(" ");
                    int num = Integer.parseInt(tileLoadNumb[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxScreenCol){
                    col = 0;
                    row++;
                }
            }
            is.close();
            br.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    } //loads in data from txt files to set Tile Graphics and location
    public void collisionLoader(int a, int b,int c ,int d){
        if (tileLoadCounter < 336) {
            if (a == 2 || b == 6) {
                collisionBoolean.add(true);
            } else {
                collisionBoolean.add(false);
            }
            collisionYaxis.add(d - 105);
            collisionRight.add(c);
            collisionLeft.add(c - 80);
            tileLoadCounter++;
        }
    }//loads collision positions once per map
    public void mapDraw(Graphics2D g2){
        int col = 0,row =0,x=0,y=0;
        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
            int tileNum = mapTileNum[col][row];
            g2.drawImage(tile[tileNum].tileLoader, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;
            collisionLoader(tileNum, tileNum, x, y);
            if (col == gp.maxScreenCol) {
                col = 0;
                row++;
                y += gp.tileSize;
                x = 0;
            }
        }
    }//Draws Graphics. *uses collisionLoader();
    public void draw(Graphics2D g2) {
        mapDraw(g2);
    }
}






