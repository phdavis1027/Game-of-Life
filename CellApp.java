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
import javafx.util.Duration;
import javafx.geometry.Point2D;

public class CellApp extends GameApplication{

    private static int NUM_CELLS = 100;
    private static int CELL_RECT_SIZE = CellFactory.CELL_SIZE + 1;
    private Entity[][] map = new Entity[NUM_CELLS][NUM_CELLS];


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
    }

    @Override
    protected void initInput(){
        Input input = getInput();

        
        onBtnDown(MouseButton.PRIMARY, ()->{
            double mouseX = input.getMouseXWorld();
            double mouseY = input.getMouseYWorld();
            spawn("cell",mouseX,mouseY);
        });

    }
}
