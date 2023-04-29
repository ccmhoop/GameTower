package object;

import entity.rework.Projectile;
import collision.CollisionChecker;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Cannonball extends Projectile {

    static int canbX;
    static int canbY;
    static int canCount=0;
    static int reload,life;
    public static ArrayList<Integer> aCan = new ArrayList<>();
    /*
     * Proof of concept.
     * I tried to make an automatic firing function.
     * It is working but needs to be improved and optimized.
     */
    public Cannonball() {
        name = "canBal";
        pjSpeed = 6;
        pjAttack = 2;
    }
    public static void canshot(boolean a) {
        if (a && reload == 0) {
            try {
                aCan.add(canCount);
                pjName.add("canB" + canCount);
                pjCounter++;
                System.out.println(aCan.size()+"  "+pjName.size());
                ppjX.add(550);
                ppjY.add(370);
                pjImage.add(ImageIO.read(Objects.requireNonNull(Cannonball.class.getResourceAsStream("/res/projectile/cannonball.png"))));
                pjLife.add(1);
                canCount++;
                reload = 1;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void cannonballPj(Graphics2D g) {
        int j = aCan.size();
        if(j>0) {
            for (int i = 0; i <j; i++) {
                try {
                    if (pjName.contains("canB" + aCan.get(i))) {
                        int a = pjName.indexOf("canB" + aCan.get(i));
                        life = pjLife.get(a);
                        canbX = ppjX.get(a);
                        canbY = ppjY.get(a);
                        //CollisionChecker.projectileCollision(canbX, canbY);
                       if (life>120) {
                            pjArrayTrim(a);
                            aCan.remove(a);
                            System.out.println(pjName.size() + " " + aCan.size()+" "+ppjY.size()+" "+pjImage.size());
                           j=aCan.size()-1;
                           pjCounter--;
                            System.out.println(pjCounter);
                        } else {
                            Projectile.canDraw(g, a, canbX, canbY,j);
                            ppjX.set(a, canbX + 20);
                            ppjY.set(a, canbY -5);
                            pjLife.set(a,life+1);
                        }
                    }
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                }
            }
        }else if(pjCounter ==0){
            ppjY.clear();
            ppjX.clear();
            pjLife.clear();
            pjImage.clear();
            aCan.clear();
            canCount =0;
        }
    }
    public static void canReload() {
        if (reload > 0) {
            reload++;
            if (reload > 60) {
                reload = 0;
            }
        } else {
            reload = 0;
        }
    }
}


