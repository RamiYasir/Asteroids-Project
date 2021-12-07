package asteroids;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;


// need to put an AnimationTimer somewhere to handle the framerate.
// maybe pass it into the physics object of each moveable object. 

public class AsteroidsApplication extends Application {

    @Override
    public void start(Stage window) {
        Game game = new Game();
        window.setScene(game.getScene());
        window.show();
    }

    public static void main(String[] args) {
        launch(AsteroidsApplication.class);
    }

    public static int partsCompleted() {
        // State how many parts you have completed using the return value of this method
        return 0;
    }

}
