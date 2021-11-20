import javafx.beans.property.SimpleBooleanProperty;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.time.LocalTimer;
import javafx.util.Duration;
import java.awt.Point;
import javafx.scene.paint.Color;
import static com.almasb.fxgl.dsl.FXGL.*;
import java.lang.Math;
import java.util.Arrays;




public class LifeComponent extends Component{

    public SimpleBooleanProperty alive;
    Entity[][] map;
    static boolean[][] boolMap;
    Point pos;
    int speed;
    int i;
    int j;
    Entity cell;
    public static volatile int its = 0;
    public static SimpleBooleanProperty checking = new SimpleBooleanProperty(false);



    public LifeComponent(Entity cell, Entity[][] map, int speed, int i, int j){
        this.cell = cell;
        alive = new SimpleBooleanProperty(false);
        this.map = map;
        this.speed = speed;
        this.i = i;
        this.j = j;
    }

    @Override
    public void onUpdate(double tpf){
        if(checking.get()){
            its++;
            if(boolMap == null){
                boolMap = new boolean[map.length][map[0].length];
            }
            
            int neighbors = getNeighbourCount(this.i, this.j);

            boolean aliveNextTurn;

            if(cell.getComponent(LifeComponent.class).isAlive().get()){
                System.out.printf("A live cell was found at (%d, %d)\n",this.j,this.i);
                aliveNextTurn = (!(neighbors <= 1 || neighbors >= 4)); 
                System.out.printf("So this cell is alive : %b\n", aliveNextTurn);
            }else{
                System.out.printf("A dead cell was found at (%d, %d)\n", this.j, this.i);
                aliveNextTurn = (neighbors == 3);
                System.out.printf("So this cell is alive : %b\n",aliveNextTurn);
            }


            boolMap[this.i][this.j] = aliveNextTurn;

            if(its == Math.pow(CellApp.NUM_CELLS,2)){
                its = 0;
                checking.set(false);    
                for(int i = 0; i < CellApp.NUM_CELLS; i++){
                    for(int j = 0; j < CellApp.NUM_CELLS; j++){

                        map[i][j].getComponent(LifeComponent.class).isAlive().set(boolMap[i][j]); 
                        map[i][j].getViewComponent().setOpacity(map[i][j].getComponent(LifeComponent.class).isAlive().get() ? 1 : 0);

                    }
                }
                boolMap = null;
            }
        }
    
    }
    

    public int getNeighbourCount (int i, int j){

        int neighbors = 0;

        
        for(int y= i- 1; y <= i + 1; y++){
            for(int x = j - 1; x  <= j + 1; x++){
                if(y != i || j != x){
                    if(x < CellApp.NUM_CELLS && x >= 0 && y < CellApp.NUM_CELLS && y >= 0){
                        if(map[y][x].getComponent(LifeComponent.class).isAlive().get()){
                            neighbors++;
                        }
                    }
                }
            }
        }

                        
                        
        System.out.println(neighbors);
        return neighbors;
    }

    public SimpleBooleanProperty isAlive(){
        return this.alive;
    }

}

