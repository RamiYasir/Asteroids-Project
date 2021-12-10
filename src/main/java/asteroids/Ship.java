package asteroids;

// velocity = change in distance / change in time
import javafx.geometry.Bounds;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.*;

public class Ship implements Moveable {

    public Physics physics;
    private Polygon shipShape;
//    private Bounds shipBounds;
//    private Bounds paneBounds;

    public Ship() {
        this.shipShape = drawTriangle();
        this.physics = new Physics();
//        this.shipBounds = new Bounds();
//        this.paneBounds = new Bounds();
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

    @Override
    public void slow(long timeDifference) {
        if (physics.speedIsPositive()) {
            physics.decreaseSpeedToZero();
        }

        if (physics.speedIsNegative()) {
            physics.increaseSpeedToZero();
        }

        shipShape.setTranslateX(shipShape.getTranslateX() + physics.calculateDistanceInXCoordinate(timeDifference));
        shipShape.setTranslateY(shipShape.getTranslateY() - physics.calculateDistanceInYCoordinate(timeDifference));
    }

    @Override
    public void move(long timeDifference) {
        physics.increaseSpeed();
        shipShape.setTranslateX(shipShape.getTranslateX() + physics.calculateDistanceInXCoordinate(timeDifference));
        shipShape.setTranslateY(shipShape.getTranslateY() - physics.calculateDistanceInYCoordinate(timeDifference));
    }

    public void reboundBackwards(long timeDifference) {
        physics.setSpeed(physics.getSpeed() - (physics.getSpeed() * 2));
        physics.increaseSpeedToZero();

        shipShape.setTranslateX(shipShape.getTranslateX() + physics.calculateDistanceInXCoordinate(timeDifference));
        shipShape.setTranslateY(shipShape.getTranslateY() - physics.calculateDistanceInYCoordinate(timeDifference));
    }
    
    public void reboundForwards(long timeDifference) {
        physics.setSpeed(physics.getSpeed() + (physics.getSpeed() * 2));
        physics.decreaseSpeedToZero();
        
        shipShape.setTranslateX(shipShape.getTranslateX() + physics.calculateDistanceInXCoordinate(timeDifference));
        shipShape.setTranslateY(shipShape.getTranslateY() - physics.calculateDistanceInYCoordinate(timeDifference));
    }

    public void rotate(Directions directionFaced) {
//        System.out.println("directionfaced: " + " " + directionFaced + ", " + directionFaced.getAngle());
//        System.out.println("physics.directionInDegrees: " + physics.getDirectionInDegrees());
//        System.out.println("directionFaced + 180: " + directionFaced.add180());

        if (directionFaced == Directions.NONE) {
            return;
        }

        if (directionFaced.getAngle() > 180.0) {
            if (physics.getDirectionInDegrees() > directionFaced.getAngle() || physics.getDirectionInDegrees() < directionFaced.add180()) {
                shipShape.setRotate(physics.getDirectionInDegrees() - 5);
            } else if (physics.getDirectionInDegrees() == directionFaced.getAngle()) {
                return;
            } else {
                shipShape.setRotate(physics.getDirectionInDegrees() + 5);
            }
            physics.setDirectionInDegrees(shipShape.getRotate());
        } else {
            if (physics.getDirectionInDegrees() < directionFaced.getAngle() || physics.getDirectionInDegrees() > directionFaced.add180()) {
                shipShape.setRotate(physics.getDirectionInDegrees() + 5);
            } else if (physics.getDirectionInDegrees() == directionFaced.getAngle()) {
                return;
            } else {
                shipShape.setRotate(physics.getDirectionInDegrees() - 5);
            }
            physics.setDirectionInDegrees(shipShape.getRotate());
        }
    }
}
