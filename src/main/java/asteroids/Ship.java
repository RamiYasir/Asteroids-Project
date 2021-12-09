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
        if (physics.getSpeed() > 0) {
            physics.decreaseSpeed();
        } else {
            physics.resetVariables();
        }
        shipShape.setTranslateX(shipShape.getTranslateX() + physics.calculateDistanceInXCoordinate(timeDifference));
        shipShape.setTranslateY(shipShape.getTranslateY() - physics.calculateDistanceInYCoordinate(timeDifference));
    }

    @Override
    public void move(long timeDifference) {
//        System.out.println(physics.calculateDistanceInYCoordinate(timeDifference));
        physics.increaseSpeed();
        shipShape.setTranslateX(shipShape.getTranslateX() + physics.calculateDistanceInXCoordinate(timeDifference));
        shipShape.setTranslateY(shipShape.getTranslateY() - physics.calculateDistanceInYCoordinate(timeDifference));
    }

    public void detectCollisionWithBounds() {
        Bounds shipBounds = shipShape.getBoundsInParent();
        Bounds paneBounds = shipShape.getParent().getBoundsInLocal();
        System.out.println(paneBounds);
        System.out.println(shipBounds);
    }

    public void rotate(Directions directionFaced) {
        System.out.println("directionfaced: " + " " + directionFaced + ", " + directionFaced.getAngle());
        System.out.println("physics.directionInDegrees: " + physics.getDirectionInDegrees());
        System.out.println("directionFaced + 180: " + directionFaced.add180());

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
        } 
        else {
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

//    public void rotate(Directions directionFaced) {
//        System.out.println(shipShape.getRotate() + " " + physics.getDirectionInDegrees());
//
//        if (directionFaced == Directions.EAST) {
//            if (physics.getDirectionInDegrees() < directionFaced.getAngle() || physics.getDirectionInDegrees() > directionFaced.add180()) {
//                shipShape.setRotate(physics.getDirectionInDegrees() + 5);
//            } else if (physics.getDirectionInDegrees() == directionFaced.getAngle()) {
//                return;
//            } else {
//                shipShape.setRotate(physics.getDirectionInDegrees() - 5);
//            }
//            physics.setDirectionInDegrees(shipShape.getRotate());
//        }
//
//        if (directionFaced == Directions.WEST) {
//            if (physics.getDirectionInDegrees() < 270.0 && physics.getDirectionInDegrees() > 90.0) {
//                shipShape.setRotate(physics.getDirectionInDegrees() + 5);
//            } else if (physics.getDirectionInDegrees() == 270.0) {
//                return;
//            } else {
//                shipShape.setRotate(physics.getDirectionInDegrees() - 5);
//            }
//            physics.setDirectionInDegrees(shipShape.getRotate());
//        }
//
//        if (directionFaced == Directions.SOUTH) {
//            if (physics.getDirectionInDegrees() > 0.0 && physics.getDirectionInDegrees() < 180.0) {
//                shipShape.setRotate(physics.getDirectionInDegrees()+ 5);
//            } else if (physics.getDirectionInDegrees() == 180.0) {
//                return;
//            } else {
//                shipShape.setRotate(physics.getDirectionInDegrees()- 5);
//            }
//            physics.setDirectionInDegrees(shipShape.getRotate());
//        }
//
//        if (directionFaced == Directions.NORTH) {
//            if (physics.getDirectionInDegrees() > 180.0 && physics.getDirectionInDegrees() < 360.0) {
//                shipShape.setRotate(physics.getDirectionInDegrees() + 5);
//            } else if (physics.getDirectionInDegrees() == 0.0 || physics.getDirectionInDegrees() == 360.0) {
//                return;
//            } else {
//                shipShape.setRotate(physics.getDirectionInDegrees()- 5);
//            }
//            physics.setDirectionInDegrees(shipShape.getRotate());
//        }
//
//        if (directionFaced == Directions.NORTHEAST) {
//            if (physics.getDirectionInDegrees() >= 225.0 || physics.getDirectionInDegrees() < 45.0) {
//                System.out.println(shipShape.getRotate());
//                shipShape.setRotate(shipShape.getRotate() + 5);
//            } else if (physics.getDirectionInDegrees() == 45.0) {
//                return;
//            } else {
//                System.out.println(shipShape.getRotate());
//                shipShape.setRotate(shipShape.getRotate() - 5);
//            }
//            physics.setDirectionInDegrees(shipShape.getRotate());
//        }
//
//        if (directionFaced == Directions.NORTHWEST) {
//            if (physics.getDirectionInDegrees() > 315.0 || physics.getDirectionInDegrees() < 135.0) {
//                shipShape.setRotate(physics.getDirectionInDegrees() - 5);
//            } else if (physics.getDirectionInDegrees() == 315.0) {
//                return;
//            } else {
//                shipShape.setRotate(physics.getDirectionInDegrees() + 5);
//            }
//            physics.setDirectionInDegrees(shipShape.getRotate());
//        }
//        
//        if (directionFaced == Directions.SOUTHEAST) {
//            if (physics.getDirectionInDegrees() > 135.0 && physics.getDirectionInDegrees() < 315.0) {
//                shipShape.setRotate(physics.getDirectionInDegrees() - 5);
//            } else if (physics.getDirectionInDegrees() == 135.0) {
//                return;
//            } else {
//                shipShape.setRotate(physics.getDirectionInDegrees() + 5);
//            }
//            physics.setDirectionInDegrees(shipShape.getRotate());
//        }
//        
//        if (directionFaced == Directions.SOUTHWEST) {
//            if (physics.getDirectionInDegrees() > 225.0 && physics.getDirectionInDegrees() < 45.0) {
//                shipShape.setRotate(physics.getDirectionInDegrees() - 5);
//            } else if (physics.getDirectionInDegrees() == 225.0) {
//                return;
//            } else {
//                shipShape.setRotate(physics.getDirectionInDegrees() + 5);
//            }
//            physics.setDirectionInDegrees(shipShape.getRotate());
//        }
//    }
}
