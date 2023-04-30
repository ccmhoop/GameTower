package gravity;

public class Gravity {
    public static boolean collision;
    public int gravity(int a){
     if (collision){
         return a;
     }else {
         return a+5;
     }
    }

}
