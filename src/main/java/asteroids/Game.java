/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

/**
 *
 * @author Rami.Yasir
 */
public class Game {
    private final Ship ship;
    private boolean isRunning;
    private Scene scene;
    
    public Game() {
        this.ship = new Ship();
        this.isRunning = true;
        this.scene = createScene();
    }
    
//    public void run() {
//        while (isRunning) {
//            scene.getOnKeyPressed();
//        }
//    }
//    
    private Scene createScene() {
        Pane pane = new Pane();
        pane.setPrefSize(600, 480);
        pane.getChildren().add(ship.getShipShape());
        Scene scene = new Scene(pane);
        scene.setOnKeyPressed(ship::move);
        return scene;
    }
    
    public Scene getScene() {
        return scene;
    }
    
    public boolean isRunning() {
        return isRunning;
    }
}
