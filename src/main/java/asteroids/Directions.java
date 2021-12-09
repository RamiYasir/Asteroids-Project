/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids;

/**
 *
 * @author Rami.Yasir
 */
public enum Directions {
    NORTH       (0.0),
    EAST        (90.0),
    SOUTH       (180.0),
    WEST        (270.0),
    NORTHEAST   (45.0),
    SOUTHEAST   (135.0),
    SOUTHWEST   (225.0),
    NORTHWEST   (315.0),
    NONE        (-1);
    
    private double angle;
    
    Directions(double angle) {
        this.angle = angle;
    }
    
    double getAngle() {
        return angle;
    }
    
    double add180() {
        double newAngle = angle;
        
        for (int count = 0; count < 180; count++) {
            if (newAngle == 360.0) {
                newAngle = 0;
            }
            newAngle++;
        }
        
        return newAngle;
    }
}
