import java.util.Random;
import java.lang.String;

public class MapGen{

    private int width;
    private int height;
    private int fillPercent;

    private long seed;
    private boolean useRandomSeed = true;



    int[][] map;

    public MapGen(int width, int height, int fillPercent){
        this.height = height;
        this.width = width;
        this.fillPercent = fillPercent;
        map = new int[width][height];
    }

    void genMap(){
        if(useRandomSeed){
            seed = (long) System.currentTimeMillis();      
        }

        Random rand = new Random(seed);

        for (int i = 0; i < height; ++i){
            for (int j = 0; j < height; ++j){



            }
        }
    }


}