package main;

import background.Background;
import background.BackgroundManager;
import effects.EffectAni;
import entity.Entity;
import entity.Player;
import entity.Projectile;
import inputs.KeyboardInputs;
import inputs.MouseInputs;
import tower.Tower;
import tower.TowerEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class GamePanel extends JPanel implements Runnable {


    private BufferedImage img;
    private  BufferedImage[] levelSprite;

    private float xDelta=0, yDelta = 0;
    private float xDir=0.001f, yDir = 0.001f;
    final int originalTileSize =16; // 16x16 Tile
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; // 48x48 pixel Tile
    final int maxScreenCol = 40;
    final int maxScreenRow = 22;
    final int screenWidth = tileSize * maxScreenCol; //1920
    final int screenHeight = tileSize* maxScreenRow; //1056
    //private MouseInputs mouseInputs;
    KeyboardInputs keyH = new KeyboardInputs();
    Thread gameThread;
    BackgroundManager backgroundM = new BackgroundManager(this);
    Player player = new Player(this,keyH);
    Tower tower = new Tower(this);
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    Projectile projectile = new Projectile(this);
    EffectAni effectAni = new EffectAni(this);
    Entity entity;

    public GamePanel(){
        //importImg();
        this.addKeyListener(keyH);
      //  mouseInputs = new MouseInputs(this);
        //addKeyListener(new KeyboardInputs(this));
        //addMouseListener(new MouseInputs(this));
        //addMouseMotionListener(new MouseInputs(this));
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        //this.setPreferredSize(new Dimension(1920,1080));
        this.setBackground(Color.BLACK);
        //this.setDoubleBuffered(true);
        this.setFocusable(true);
        tower = new Tower(this);
        this.collisionChecker = new CollisionChecker(this);
        //player.setDefaultValues();
        player = new Player(this,keyH);
       player.loadLvlDate(backgroundM.getCurrentLevel().getLevelData());
        startGameThread();
    }
    public void updatePos(){
   // xDelta += xDir;
    //yDelta += yDir;
    }
    public void changeXdelta(float x){
    this.xDelta+=x;
   // repaint();
    }
    public void changeYdelta(float y){
    this.yDelta+=y;
    //repaint();
    }
    public void setRecPos(float x, float y){
    this.xDelta = x;
    this.yDelta = y;
   // repaint();
    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        int FPS_SET = 120;
        double drawInterval = 1000000000.0 / FPS_SET;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if (delta >= 1) {
                update();// 1 UPDATE : updates information such as characters position
                repaint();// 2 DRAW : draw the screen with the updated information
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000) {
               //System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }
    public void update(){
        backgroundM.update();
        //collisionChecker.update();
        player.update();
        projectile.update();

    }



    public void paintComponent(Graphics g){

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        backgroundM.draw(g2);
        tower.draw(g2);
        projectile.draw(g2);
        player.draw(g2);
        g2.dispose();

    }
}
