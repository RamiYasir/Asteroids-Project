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

    public Physics physics;
    private double direction;
    private double acceleration;
    private Polygon shipShape;

    public Ship() {
        this.direction = 0;
        this.acceleration = 0;
        this.shipShape = drawTriangle();
        this.physics = new Physics();
    }

    public Polygon drawTriangle() {
        Polygon triangle = new Polygon();
        triangle.getPoints().addAll(new Double[]{
            10.0, 0.0,
            0.0, 20.0,
            20.0, 20.0
        });

        return triangle;
    }

    public Polygon getShipShape() {
        return shipShape;
    }
//
//    @Override
//    public void slow(long timeDifference) {
//        if (lastKeyPressed == KeyCode.RIGHT) {
//            physics.decreaseSpeed();
//            moveRight(timeDifference);
//        }
//        if (lastKeyPressed == KeyCode.LEFT) {
//            physics.decreaseSpeed();
//            moveLeft(timeDifference);
//        }
//    }

    @Override
    public void move(KeyCode keyPressed, long timeDifference) {
        if (keyPressed == KeyCode.RIGHT) {
            physics.increaseSpeed();
            moveRight(timeDifference);
            rotate(keyPressed);
        }
        if (keyPressed == KeyCode.LEFT) {
            physics.increaseSpeed();
            moveLeft(timeDifference);
            rotate(keyPressed);
        }
        if (keyPressed == KeyCode.DOWN) {
            physics.increaseSpeed();
            moveDown(timeDifference);
            rotate(keyPressed);
        }
        if (keyPressed == KeyCode.UP) {
            physics.increaseSpeed();
            moveUp(timeDifference);
            rotate(keyPressed);
        }
    }

    private void moveLeft(long timeDifference) {
        shipShape.setTranslateX(shipShape.getTranslateX() - physics.calculateDistanceInPixels(timeDifference));
    }

    private void moveRight(long timeDifference) {
        shipShape.setTranslateX(shipShape.getTranslateX() + physics.calculateDistanceInPixels(timeDifference));
    }

    private void moveDown(long timeDifference) {
        shipShape.setTranslateY(shipShape.getTranslateY() + physics.calculateDistanceInPixels(timeDifference));
    }

    private void moveUp(long timeDifference) {
        shipShape.setTranslateY(shipShape.getTranslateY() - physics.calculateDistanceInPixels(timeDifference));
    }

    private void rotate(KeyCode keyPressed) {
        System.out.println(shipShape.getRotate() + " " + physics.getDirectionInDegrees());

        if (keyPressed == KeyCode.RIGHT) {
            if (physics.getDirectionInDegrees() > 270.0 || physics.getDirectionInDegrees() < 90.0) {
                shipShape.setRotate(shipShape.getRotate() + 5);
            } else if (physics.getDirectionInDegrees() == 90.0) {
                return;
            } else {
                shipShape.setRotate(shipShape.getRotate() - 5);
            }
            physics.setDirectionInDegrees(shipShape.getRotate());
        }

        if (keyPressed == KeyCode.LEFT) {
            if (physics.getDirectionInDegrees() < 270.0 && physics.getDirectionInDegrees() > 90.0) {
                shipShape.setRotate(shipShape.getRotate() + 5);
            } else if (physics.getDirectionInDegrees() == 270.0) {
                return;
            } else {
                shipShape.setRotate(shipShape.getRotate() - 5);
            }
            physics.setDirectionInDegrees(shipShape.getRotate());
        }

        if (keyPressed == KeyCode.DOWN) {
            if (physics.getDirectionInDegrees() > 0.0 && physics.getDirectionInDegrees() < 180.0) {
                shipShape.setRotate(shipShape.getRotate() + 5);
            } else if (physics.getDirectionInDegrees() == 180.0) {
                return;
            } else {
                shipShape.setRotate(shipShape.getRotate() - 5);
            }
            physics.setDirectionInDegrees(shipShape.getRotate());
        }

        if (keyPressed == KeyCode.UP) {
            if (physics.getDirectionInDegrees() > 180.0 && physics.getDirectionInDegrees() < 360.0) {
                shipShape.setRotate(shipShape.getRotate() + 5);
            } else if (physics.getDirectionInDegrees() == 0.0) {
                return;
            } else {
                shipShape.setRotate(shipShape.getRotate() - 5);
            }
            physics.setDirectionInDegrees(shipShape.getRotate());
        } 
    }

//    public void moveRight() {
//        double previousPositionX = shipShape.getTranslateX();
//        double previousPositionY = shipShape.getTranslateY();
//        shipShape.setTranslateX(shipShape.getTranslateX() + 0.2);
//        System.out.println(shipShape.getTranslateX());
//        double[] velocity = new double[2];
//        velocity[0] = shipShape.getTranslateX()- previousPositionX;
//        velocity[1] = shipShape.getTranslateY() - previousPositionY;
//        System.out.println(Arrays.toString(velocity));
//        
//        I can use this to get the coordinates. 
//        Bounds boundsInScene = shipShape.localToParent(shipShape.getBoundsInLocal());
//        drawBounds(boundsInScene);
//    }
//    public void calculateVelocity() {
//        double previousPositionX = shipShape.getTranslateX();
//        double previousPositionY = shipShape.getTranslateY();
//    }
//    public void setPosition() {
//        
//    }
//    public double[] addToPoints(double number) {
//        double[] newPoints = new double[6];
//        
//        for (int i = 0; i < position.length; i++) {
//            newPoints[i] = position[i] + number;
//        }
//        
//        return newPoints;
//    }
}
