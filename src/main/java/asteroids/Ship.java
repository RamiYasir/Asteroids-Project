package asteroids;

// velocity = change in distance / change in time
import javafx.scene.input.KeyCode;
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


    public void slow(long timeDifference) {
        if (physics.getSpeed() > 0) {
            physics.decreaseSpeed();
        } else {
            physics.resetVariables();
        }
        shipShape.setTranslateX(shipShape.getTranslateX() + physics.calculateDistanceInXCoordinate(timeDifference));
        shipShape.setTranslateY(shipShape.getTranslateY() - physics.calculateDistanceInYCoordinate(timeDifference));
    }
    
    @Override
    public void move(KeyCode keyPressed, long timeDifference) {
        System.out.println(physics.calculateDistanceInYCoordinate(timeDifference));
        physics.increaseSpeed();
        shipShape.setTranslateX(shipShape.getTranslateX() + physics.calculateDistanceInXCoordinate(timeDifference));
        shipShape.setTranslateY(shipShape.getTranslateY() - physics.calculateDistanceInYCoordinate(timeDifference));
        rotate(keyPressed);
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
}
