package entity;

import gravity.Gravity;
import inputs.KeyboardInputs;
import collision.CollisionChecker;
import main.GamePanel;

import java.awt.*;

public class Player extends Entity {

    GamePanel gp;
    CollisionChecker collisionChecker = new CollisionChecker();
    KeyboardInputs keyBoard;
    Gravity gravity = new Gravity();

    /*
     * All animations and key input is being reworked
     * Separating Player related code into methods
     */
    public Player(GamePanel gp, KeyboardInputs keyH) {
        this.gp = gp;
        this.keyBoard = keyH;
        setDefaultValues();
        setAnimationArray();
    }

    private void setDefaultValues() {
        player_action = "idle";
        player_position_Yaxis = 740;
        player_position_Xaxis = -30;
        face_right = true;
    }

    private void jump() {
        if (jump_activated) {
            switch (timeCycles(9, 9)) {
                //windup\\
                case (3), (4) -> player_position_Yaxis -= 5;
                case (5), (6) -> player_position_Yaxis -= 2;
                case (7), (8) -> player_position_Yaxis -= 1;
                case (9) -> jump_activated = false;
            }
        }
    }

    private void requestPlayerAnimation() {
        if (!Gravity.toggle_gravity) {
            animationPngCycles(3, 42, 47);
        } else if (player_action.equals("jump")) {
            animationPngCycles(2, 30, 41);
        } else if (!jump_activated) {
            switch (player_action) {
                case ("idle") -> animationPngCycles(5, 0, 17);
                case ("run") -> animationPngCycles(5, 18, 29);
            }
        }
    }

    private void basicPlayerUpdates() {
        if (jump_activated) {
            jump();
        } else {
            player_position_Yaxis = gravity.gravity(player_position_Yaxis);
        }
        collisionChecker.getPlayerPosition(player_position_Xaxis, player_position_Yaxis);
    }

    private void playerInput() {
        if (keyBoard.leftPressed) {
            player_action = "run";
            player_position_Xaxis -= player_speed;
            face_right = false;
        } else if (keyBoard.rightPressed) {
            player_action = "run";
            player_position_Xaxis += player_speed;
            face_right = true;
        } else {
            player_action = "idle";
        }
        if (keyBoard.spaceBar) {
            if (Gravity.toggle_gravity) {
                player_action = "jump";
                jump_activated = true;
            }
        }

    }

    private void playerAnimationDrawer(Graphics2D g2) {
        if (face_right) {
            g2.drawImage(facing_right_animation.get(animation_index), player_position_Xaxis, player_position_Yaxis, 128, 128, null);
        } else {
            g2.drawImage(facing_left_animation.get(animation_index), player_position_Xaxis, player_position_Yaxis, 128, 128, null);
        }
    }

    public void update() {
        playerInput();
        basicPlayerUpdates();
        requestPlayerAnimation();

    }

    public void draw(Graphics2D g2) {
        playerAnimationDrawer(g2);
    }

}
