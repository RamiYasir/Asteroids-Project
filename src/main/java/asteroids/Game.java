/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids;

import java.util.HashMap;
import java.util.Map;
import javafx.animation.AnimationTimer;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

/**
 *
 * @author Rami.Yasir
 */
public class Game {
    private final UserInterface ui;
    private final Ship ship;
    private boolean isRunning;
    private Scene scene;
//    private Map<KeyCode, Boolean> pressedKeys;
//  private ArrayList<Moveable> objects on screen.
//  or, more likely, physics should have a list of objects on screen and calculate their directions and speed.

    public Game() {
        this.ui = new UserInterface();
        this.ship = new Ship();
//        this.pressedKeys = new HashMap<>();
        this.isRunning = true;
        this.scene = createScene();
    }

    private Scene createScene() {
        Pane pane = new Pane();
        pane.setPrefSize(600, 480);
        pane.getChildren().add(ship.getShipShape());
        Scene scene = new Scene(pane);

//        Bounds paneBounds = pane.sceneToLocal(pane.getBoundsInLocal());
        scene.setOnKeyPressed(event -> {
//            System.out.println(paneBounds);
            ui.add(event.getCode(), Boolean.TRUE);
        });

        scene.setOnKeyReleased(event -> {
            ui.add(event.getCode(), Boolean.FALSE);
        });

        new AnimationTimer() {
            long previousTimeStamp = 0;
            KeyCode keyCode = null;
            KeyCode previousCode = null;

            @Override
            public void handle(long now) {
                long timeDifference = now - previousTimeStamp;
                
                if (ui.determineDirectionFaced() == Directions.NONE) {
                    ship.slow(timeDifference);
                } else {
                    ship.move(timeDifference);
                    ship.rotate(ui.determineDirectionFaced());
                }

//                // 1 method
//                for (KeyCode key : ui.getPressedKeys().keySet()) {
//                    if (ui.getPressedKeys().get(key)) {
//                        keyCode = key;
//                    }
//                }
//
//                // 2 method
//                if (keyCode == null || ui.getPressedKeys().get(keyCode) == false || keyCode != previousCode) {
//                    ship.slow(timeDifference);
//                } else {
//                    ship.move(timeDifference);
//                    ship.rotate(keyCode);
//                }
                
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
