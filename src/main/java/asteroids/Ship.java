package asteroids;

// velocity = change in distance / change in time

import javafx.event.Event;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.*;

public class Ship implements Moveable {
    private double speed;
    private double direction;
    private double acceleration;
    private Polygon shipShape;

    public Ship() {
        this.speed = 0;
        this.direction = 0;
        this.acceleration = 0;
        this.shipShape = drawTriangle();
    }

    public Polygon drawTriangle() {
        Polygon triangle = new Polygon();
        triangle.getPoints().addAll(new Double[] {
                0.0, 0.0,
                20.0, 10.0,
                10.0, 20.0
        });

        return triangle;
    }
    
    public Polygon getShipShape() {
        return shipShape;
    }
    
    @Override
    public void move(KeyEvent keyPressed) {
        if (keyPressed.getCode() == KeyCode.RIGHT) {
            shipShape.setTranslateX(shipShape.getTranslateX() + 2);
        }
        if (keyPressed.getCode() == KeyCode.LEFT) {
            shipShape.setTranslateX(shipShape.getTranslateX() - 2);
        }
        if (keyPressed.getCode() == KeyCode.DOWN) {
            shipShape.setTranslateY(shipShape.getTranslateY() + 2);
        }
        if (keyPressed.getCode() == KeyCode.UP) {
            shipShape.setTranslateY(shipShape.getTranslateY() - 2);
        }
    }
}
