import java.util.Random;
import java.lang.Math;
import java.lang.String;
import java.util.Arrays;

public class MapGen{

    private int width;
    private int height;
    private int fillPercent;

    private long seed;
    private boolean useRandomSeed = true;

    public int[][] map;

    public static void main(String[] args){
        MapGen mapGen = new MapGen(100, 100, 25);
        mapGen.genMap();
        mapGen.smoothMap(5);
        System.out.println(Arrays.deepToString(mapGen.getMap()));
    }


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

                if(Math.ceil(rand.nextDouble()*100) <= fillPercent)
                    map[i][j] = 1;
            }
        }
    }


    public int[][] getMap(){
        return map.clone();
    }

    public void smoothMap(int iters){
        while(iters >= 0){
            int[][] nextMap = new int[height][width];

            for(int i = 0; i < height; ++i){
                for(int j = 0; j < width; ++j){
                    if(getNeighborCount(i,j) >= 4)
                        nextMap[i][j] = 1;
                    else
                        nextMap[i][j] = 0;
                }
            }
            this.map = nextMap;
            iters--;
        }
    }

    public int getNeighborCount(int y, int x){

        int neighbors = 0;

        for(int i = y - 1; i <= y + 1; ++i){
            for (int j = x - 1; j <= x + 1; ++j){
                if (i < width && i >=0 && j < height && j >= 0){
                    if(i != y || j != x){
                        neighbors += this.map[i][j];
                    }
                }else{
                    neighbors++;
                }  
            }
        }
        return neighbors;
    }


}
