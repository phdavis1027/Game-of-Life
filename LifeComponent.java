import javafx.beans.property.SimpleBooleanProperty;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.time.LocalTimer;
import javafx.util.Duration;
import java.awt.Point;
import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.paint.Color;




public class LifeComponent extends Component{

    SimpleBooleanProperty alive;
    Entity[][] map;
    Point pos;
    int speed;
    Entity cell;

    public static final LocalTimer lifeTimer = FXGL.newLocalTimer();


    public LifeComponent(Entity cell, Entity[][] map, int speed, int x, int y){
        this.cell = cell;
        alive = new SimpleBooleanProperty(false);
        this.map = map;
        this.speed = speed;
        pos = new Point(x, y);
    }

    @Override
    public void onUpdate(double tpf){

        if(lifeTimer.elapsed(Duration.seconds(speed))){
            int x = (int) pos.getX();
            int y = (int) pos.getY();
            int neighbors = getNeighbourCount(x, y);
            System.out.println(neighbors);

            if(alive.get()){
                alive = new SimpleBooleanProperty(!(neighbors <= 1 || neighbors >= 4)); 
            }else{
                alive = new SimpleBooleanProperty(neighbors == 3);
            }

            cell.getViewComponent().setOpacity(alive.get() ? 1 : 0);       
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

