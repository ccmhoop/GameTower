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
    private int tileLoadCounter;
    int[][] mapTileNum;

    public static ArrayList <Integer>  collisionYaxis = new ArrayList<>();
    public  static ArrayList <Integer> collisionRight = new ArrayList<>();
    public  static ArrayList <Integer> collisionLeft = new ArrayList<>();
    public  static ArrayList <Boolean> collisionBoolean = new ArrayList<>();

    /*
        * I tried to make a collision system that dint require calculations. This allows me to make maps quick.
          It's important to me that its only being checked if requirements are met instead of it being processed every
          screen draw or update.

        * I want to make it in such away that in future updates I can add maps without breaking the code.

        * My concept is : if the player Y position is in collisionYaxis. (This checks if the Player Y position is on top of a tile);
          Check if collisionBool = true for the same tile && Player X position is in between collisionLeft && collisionRight
          If(true) gravity is disabled

        * Short version if collisionBoolean = true use the de index number to check if the player location matches the same index

        * It is working so far
        * I need to refine the collisionLoader(); to make it more efficient for different maps

        *Jump mechanic : Hold space to jump through a platform tap/release space to jump on top of a platform .
    */

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
        getTileImage();
        loadMap();
        mapCollisionLocation();
    }
    private void getTileImage() {
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
    }

    //--loads in data from txt files to set Tile Graphics and location--\\
    private void loadMap(){
        try(InputStream is = getClass().getResourceAsStream("/res/maps/map1.txt")) {
           assert is != null;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0,row = 0;
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
            is.close();br.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //--Boolean method that contains all tile types that got collision
    private boolean tileSetCollision(int tileCollision){
        switch (tileCollision){
            case 2,6-> {
                return true;
            }
        }
        return false;
    }

    //--Sets collision locations in array--\\
    private void setMapCollision(int collisionTiles, int tileYLocation,int tileXLocation){
        if (tileLoadCounter < 336) {
            if (tileSetCollision(collisionTiles)) {
                collisionBoolean.add(true);
            } else {
                collisionBoolean.add(false);
            }
            collisionYaxis.add(tileYLocation - 105);
            collisionRight.add(tileXLocation);
            collisionLeft.add(tileXLocation - 80);
            tileLoadCounter++;
        }
    }

    //--loads in collision on start location--\\
    private void mapCollisionLocation (){
        int col = 0,row =0,x=0,y=0;
        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
            int tileNum = mapTileNum[col][row];
            col++;
            x += gp.tileSize;
            setMapCollision(tileNum,y,x);
            if (col == gp.maxScreenCol) {
                col = 0;
                row++;
                y += gp.tileSize;
                x = 0;
            }
        }
    }

    private void mapDraw(Graphics2D g2){
        int col = 0,row =0,x=0,y=0;
        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
            int tileNum = mapTileNum[col][row];
            g2.drawImage(tile[tileNum].tileLoader, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;
            if (col == gp.maxScreenCol) {
                col = 0;
                row++;
                y += gp.tileSize;
                x = 0;
            }
        }
    }
    public void draw(Graphics2D g2) {
        mapDraw(g2);
    }
}






