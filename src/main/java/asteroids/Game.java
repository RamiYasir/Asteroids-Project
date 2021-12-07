/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids;

import java.util.HashMap;
import java.util.Map;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

/**
 *
 * @author Rami.Yasir
 */
public class Game {

    private final Ship ship;
    private boolean isRunning;
    private Scene scene;
    private Map<KeyCode, Boolean> pressedKeys;
//  private ArrayList<Moveable> objects on screen.
//  or, more likely, physics should have a list of objects on screen and calculate their directions and speed.

    public Game() {
        this.ship = new Ship();
        this.pressedKeys = new HashMap<>();
        this.isRunning = true;
        this.scene = createScene();
    }

    private Scene createScene() {
        Pane pane = new Pane();
        pane.setPrefSize(600, 480);
        pane.getChildren().add(ship.getShipShape());
//        pane.getChildren().add(ship.getBoundsBox());
        Scene scene = new Scene(pane);
        
        scene.setOnKeyPressed(event -> {
            pressedKeys.put(event.getCode(), Boolean.TRUE);
        });

        scene.setOnKeyReleased(event -> {
            pressedKeys.put(event.getCode(), Boolean.FALSE);
        });
        
        new AnimationTimer() {
            long previousTimeStamp = 0;
            KeyCode keyCode = null;
            // keeping track of previousCode is a bug fix.
            KeyCode previousCode = null;
            
            @Override
            public void handle(long now) {
                long timeDifference = now - previousTimeStamp;
                
                for (KeyCode key : pressedKeys.keySet()) {
                    if (pressedKeys.get(key)) {
                        keyCode = key;
                    }
                }
                
                if (keyCode == null || pressedKeys.get(keyCode) == false || keyCode != previousCode) {
                    ship.slow(timeDifference);
                } else {
                    ship.move(keyCode, timeDifference);
                }
                previousTimeStamp = now;
                previousCode = keyCode;
            }
        }.start();

        
        return scene;
    }

    public Scene getScene() {
        return scene;
    }

    public boolean isRunning() {
        return isRunning;
    }
}
