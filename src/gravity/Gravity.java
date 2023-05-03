package gravity;

public class Gravity {
    public static boolean toggle_gravity;

    public int gravity(int a) {
            if (toggle_gravity){
                return a;
            }else {
                return a+5;
            }
        }

    }




