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
    private Pane currentPane;
    private Scene scene;
//    private Map<KeyCode, Boolean> pressedKeys;
//  private ArrayList<Moveable> objects on screen.
//  or, more likely, physics should have a list of objects on screen and calculate their directions and speed.

    public Game() {
        this.ui = new UserInterface();
        this.ship = new Ship();
        this.currentPane = new Pane();
//        this.pressedKeys = new HashMap<>();
        this.isRunning = true;
        this.scene = createScene();
    }

    private Scene createScene() {
        currentPane.setPrefSize(750, 600);
        currentPane.getChildren().add(ship.getShipShape());
        Scene scene = new Scene(currentPane);

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
//                System.out.println(ship.physics.getSpeed());

                System.out.println(ship.physics.getSpeed());
                if (detectShipCollisionWithBounds()) {
                    while (ship.physics.getSpeed() > 0.0) {
                        ship.rebound(timeDifference);
                    }
                }

                if (ui.determineDirectionFaced() == Directions.NONE) {
                    if (ship.physics.speedIsZero()) {
                        ship.physics.resetVariables();
                    } else {
                        ship.slow(timeDifference);
                    }
                } else {
                    ship.move(timeDifference);
                    ship.rotate(ui.determineDirectionFaced());
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

    private boolean detectShipCollisionWithBounds() {
        Bounds shipBounds = ship.getShipShape().getBoundsInParent();
        Bounds paneBounds = currentPane.getBoundsInLocal();
//        System.out.println(paneBounds);
//        System.out.println(shipBounds);

        if (shipBounds.getMaxX() > 750.0) {
            return true;
        }

        return false;
    }
}
