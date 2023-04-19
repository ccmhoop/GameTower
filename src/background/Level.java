package background;

public class Level {
    public int[]lvlData;
    public Level(int[]lvlData){
        this.lvlData =lvlData;
    }
    public int getSpriteIndex(int x){
        return lvlData[x];
    }

    public void setLevelData(int x){
        lvlData[x] = x;
    }

    public int[] getLevelData(){
        return lvlData;
    }
}
