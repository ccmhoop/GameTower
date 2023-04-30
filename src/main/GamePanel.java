package main;

import background.Rework.BackgroundManager;
import collision.CollisionChecker;
import entity.Player;
import entity.rework.Projectile;
import inputs.KeyboardInputs;
import tile.TileManager;
import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    final int originalTileSize = 80;
    final int scale = 1;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 24;
    public final int maxScreenRow = 14;
    KeyboardInputs keyInput = new KeyboardInputs();
    Thread gameThread;
    BackgroundManager backgroundM = new BackgroundManager(this);
    TileManager tileManager = new TileManager(this);
    Player player;
    CollisionChecker collisionChecker = new CollisionChecker();
    Projectile projectile = new Projectile();

    public GamePanel(){
        this.addKeyListener(keyInput);
        this.setPreferredSize(new Dimension(1920,1080));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        player = new Player(this,keyInput);
        startGameThread();
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
        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if (delta >= 1) {
                update();// 1 UPDATE : updates information such as characters position
                repaint();// 2 DRAW : draw the screen with the updated information
                delta--;
            }
            if (timer >= 1000000000) {
                timer = 0;
            }
        }
    }
    public void update(){
        collisionChecker.update();
        backgroundM.update();
        player.update();
        projectile.update();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        backgroundM.draw(g2);
        tileManager.draw(g2);
        player.draw(g2);
        projectile.draw(g2);
        g2.dispose();
    }
}
