import javafx.beans.property.SimpleBooleanProperty;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.time.LocalTimer;
import javafx.util.Duration;
import java.awt.Point;
import javafx.scene.paint.Color;
import static com.almasb.fxgl.dsl.FXGL.*;





public class LifeComponent extends Component{

    SimpleBooleanProperty alive;
    Entity[][] map;
    Point pos;
    int speed;
    Entity cell;
    static int it = 0;
    public static SimpleBooleanProperty checking = new SimpleBooleanProperty(false);



    public LifeComponent(Entity cell, Entity[][] map, int speed, int x, int y){
        this.cell = cell;
        alive = new SimpleBooleanProperty(false);
        this.map = map;
        this.speed = speed;
        pos = new Point(x, y);
    }

    @Override
    public void onUpdate(double tpf){

        if(checking.get()){
            it++;
            int x = (int) pos.getX();
            int y = (int) pos.getY();
            int neighbors = getNeighbourCount(x, y);
            System.out.println(it);

            if(alive.get()){
                alive = new SimpleBooleanProperty(!(neighbors <= 1 || neighbors >= 4)); 
            }else{
                alive = new SimpleBooleanProperty(neighbors == 3);
            }

            cell.getViewComponent().setOpacity(alive.get() ? 1 : 0);   
        }
        if((int) pos.getX() == CellApp.NUM_CELLS -1 && (int) pos.getY() == CellApp.NUM_CELLS - 1){
            checking.set(false);    
        }
    }


    public int getNeighbourCount (int x, int y){

        int neighbors = 0;

        for(int i = y - 1; i <= y + 1; ++i){
            for (int j = x - 1; j <= x + 1; ++j){
                if (i < map.length && i >=0 && j < map.length && j >= 0){
                    
                    if(i != y || j != x){
                        
                        if (map[i][j].getComponent(LifeComponent.class).isAlive().get())
                            neighbors++;
                        
                    }
                } 
            }
        }
        return neighbors;
    }

    public SimpleBooleanProperty isAlive(){
        return this.alive;
    }

}

