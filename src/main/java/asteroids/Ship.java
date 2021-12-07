package asteroids;

// velocity = change in distance / change in time

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javafx.event.Event;
import javafx.geometry.Bounds;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.*;

public class Ship implements Moveable {
    private double[] position;
    private double speed;
    private double direction;
    private double acceleration;
//    private Polygon bounds;
    private Polygon shipShape;

    public Ship() {
        this.speed = 0;
        this.direction = 0;
        this.acceleration = 0;
//        this.position = new double[] {
//            0.0, 0.0,
//            20.0, 10.0,
//            10.0, 20.0
//        };
        this.shipShape = drawTriangle();
//        this.bounds = drawBounds();
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
    
//    public Polygon drawBounds() {
//        Bounds bounds = shipShape.localToParent(shipShape.getBoundsInLocal());
//        Polygon boundsBox = new Polygon();
//        boundsBox.getPoints().addAll(new Double[] {
//            bounds.getMaxX(), bounds.getMinY(),
//            bounds.getMinX(), bounds.getMinY(),
//            bounds.getMinX(), bounds.getMaxY(),
//            bounds.getMaxX(), bounds.getMaxY()
//        });
//        
//        return boundsBox;
//    }
    
    public Polygon drawBounds(Bounds bounds) {
        Polygon boundsBox = new Polygon();
        boundsBox.getPoints().addAll(new Double[] {
            bounds.getMinX(), bounds.getMinY(),
            bounds.getMaxX(), bounds.getMinY(),
            bounds.getMinX(), bounds.getMaxY(),
            bounds.getMaxX(), bounds.getMaxY()
        });
        
        return boundsBox;
    }
    
//    public Polygon getBoundsBox() {
//        return bounds;
//    }
    
    public Polygon getShipShape() {
        return shipShape;
    }
    
    @Override
    public void move(KeyEvent keyPressed) {
        if (keyPressed.getCode() == KeyCode.RIGHT) {
            moveRight();
        }
        if (keyPressed.getCode() == KeyCode.LEFT) {
            shipShape.setTranslateX(shipShape.getTranslateX() - 0.2);
            System.out.println(shipShape.getPoints());
        }
        if (keyPressed.getCode() == KeyCode.DOWN) {
            shipShape.setTranslateY(shipShape.getTranslateY() + 0.2);
            System.out.println(shipShape.getPoints());
        }
        if (keyPressed.getCode() == KeyCode.UP) {
            shipShape.setTranslateY(shipShape.getTranslateY() - 0.2);
            System.out.println(shipShape.getPoints());
        }
    }
    
    public void moveRight() {
        double previousPositionX = shipShape.getTranslateX();
        double previousPositionY = shipShape.getTranslateY();
        shipShape.setTranslateX(shipShape.getTranslateX() + 0.2);
        System.out.println(shipShape.getTranslateX());
        double[] velocity = new double[2];
        velocity[0] = shipShape.getTranslateX()- previousPositionX;
        velocity[1] = shipShape.getTranslateY() - previousPositionY;
        System.out.println(Arrays.toString(velocity));
        
        //I can use this to get the coordinates. 
        Bounds boundsInScene = shipShape.localToParent(shipShape.getBoundsInLocal());
        drawBounds(boundsInScene);
    }
    
    public void calculateVelocity() {
        double previousPositionX = shipShape.getTranslateX();
        double previousPositionY = shipShape.getTranslateY();
    }
    
    public void setPosition() {
        
    }
    
    public double[] addToPoints(double number) {
        double[] newPoints = new double[6];
        
        for (int i = 0; i < position.length; i++) {
            newPoints[i] = position[i] + number;
        }
        
        return newPoints;
    }
}
