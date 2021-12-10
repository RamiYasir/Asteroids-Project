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

    public Game() {
        this.ui = new UserInterface();
        this.ship = new Ship();
        this.currentPane = new Pane();
        this.isRunning = true;
        this.scene = createScene();
    }

    private Scene createScene() {
        currentPane.setPrefSize(750, 600);
        currentPane.getChildren().add(ship.getShipShape());
        Scene scene = new Scene(currentPane);

        scene.setOnKeyPressed(event -> {
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

                if (detectShipCollisionWithBounds() && ship.physics.speedIsPositive()) {
                    System.out.println("ship.rebound() called");
                    ship.reboundBackwards(timeDifference);
                    System.out.println(ship.physics.getSpeed());
                }
                
                if (detectShipCollisionWithBounds() && ship.physics.speedIsNegative()) {
                    ship.reboundForwards(timeDifference);
                }

                if (ui.determineDirectionFaced() != Directions.NONE) {
                    System.out.println("ship.move() called");
                    ship.move(timeDifference);
                    ship.rotate(ui.determineDirectionFaced());
                } else if (ui.determineDirectionFaced() == Directions.NONE && !ship.physics.speedIsZero()) {
                    System.out.println("ship.slow(timeDifference) called");
                    ship.slow(timeDifference);
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

    
    // Maybe create another polygon that serves as the playing field bounds.
    // Don't think it needs to be added to the scene, just used for calculations. 
    private boolean detectShipCollisionWithBounds() {
        Bounds shipBounds = ship.getShipShape().getBoundsInParent();

        if (shipBounds.getMaxX() > 750.0) {
            return true;
        }

        if (shipBounds.getMaxY() > 600.0) {
            return true;
        }
        
        if (shipBounds.getMinX() < 0.0) {
            return true;
        }
        
        if (shipBounds.getMinY() < 0.0) {
            return true;
        }

        return false;
    }
}
