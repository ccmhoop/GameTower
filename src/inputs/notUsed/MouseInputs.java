package inputs.notUsed;

import main.GamePanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInputs implements MouseListener, MouseMotionListener{
    private GamePanel gamePanel;
    public MouseInputs(GamePanel gamePanel){
    this.gamePanel=gamePanel;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("mouse");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("mouse");

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("mouse");

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("mouse 4");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("mouse 3");

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        System.out.println("mouse c");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        System.out.println("mouse d");
       // gamePanel.setRecPos(e.getX(),e.getY());

    }
}
