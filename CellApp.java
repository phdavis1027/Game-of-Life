import static com.almasb.fxgl.dsl.FXGL.*;
import static com.almasb.fxgl.dsl.FXGL.getAppHeight;
import static com.almasb.fxgl.dsl.FXGL.getAppWidth;
import static com.almasb.fxgl.dsl.FXGL.getGameController;
import static com.almasb.fxgl.dsl.FXGL.onBtnDown;
import static com.almasb.fxgl.dsl.FXGL.onCollisionBegin;
import static com.almasb.fxgl.dsl.FXGL.onKey;
import static com.almasb.fxgl.dsl.FXGL.run;
import static com.almasb.fxgl.dsl.FXGL.showMessage;
import static com.almasb.fxgl.dsl.FXGL.spawn;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import javafx.geometry.Point2D;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import com.almasb.fxgl.time.TimerAction;


public class CellApp extends GameApplication{

    public static final int NUM_CELLS = 100;
    private static int CELL_RECT_SIZE = CellFactory.CELL_SIZE;
    private static int SPEED = 2;
    private Entity[][] map = new Entity[NUM_CELLS][NUM_CELLS];
    TimerAction go;

    public static void main(String[] args){
        launch(args);
    }


    @Override
    protected void initSettings(GameSettings settings){
        settings.setTitle("CellScape");
        settings.setWidth(NUM_CELLS*CELL_RECT_SIZE);
        settings.setHeight(NUM_CELLS*CELL_RECT_SIZE);
    }


    @Override
    protected void initGame(){
        getGameWorld().addEntityFactory(new CellFactory());

        for(int i = 0; i < NUM_CELLS; i++){
            for(int j = 0; j < NUM_CELLS; j++){
                    map[i][j] = spawn("cell", j*CellFactory.CELL_SIZE, i*CellFactory.CELL_SIZE);
                    map[i][j].addComponent(new LifeComponent(map[i][j], map, SPEED, i, j));
            }
        }
        
        go = getGameTimer().runAtInterval(()->{
            LifeComponent.checking.set(true);
        },Duration.seconds(2));

    }

    @Override
    protected void initInput(){
        Input input = getInput();

        onKey(KeyCode.E, ()->{
            if(go.isPaused()){
                go.resume();
            }else{
                go.pause();
            }
        });


    }
}
