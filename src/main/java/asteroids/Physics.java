/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// velocity = change in distance / change in time
// acceleration = delta velocity / delta time
// Right so lets say I have an animation timer, which is started in the game and 
// passed as a parameter to this physics object. 
package asteroids;

public class Physics {

    private double speed;
    private double directionInDegrees;

    public Physics() {
        this.speed = 0;
        this.directionInDegrees = 0;
    }
    
    public void increaseSpeed() {
        if (speed >= 1.0) {
            speed = 1.0;
            return;
        }
        speed += 0.01;
    }
    
    public void decreaseSpeed() {
        if (speed <= 0) {
            speed = 0.0;
            return;
        }
        speed -= 0.02;
    }
    
    public double getSpeed() {
        return speed;
    }
    
    public double calculateDistanceInXCoordinate(long timeDifference) {
        // soh cah toa. soh? sin(theta) = opposite / hypotenuse
        // opposite = sin(theta) * hypotenuse
        
        double degreeInRadians = Math.toRadians(directionInDegrees);
        double distanceOfX = Math.sin(degreeInRadians) * calculateDistanceInPixels(timeDifference);
        return distanceOfX;
    }

    public double calculateDistanceInYCoordinate(long timeDifference) {
        //soh cah toa. cah? cos(theta) = adjacent / hypotenuse
        //adjacent = cos(theta) * hypotenuse

        double degreeInRadians = Math.toRadians(directionInDegrees);
        double distanceOfY = Math.cos(degreeInRadians) * calculateDistanceInPixels(timeDifference);
        return distanceOfY;
    }
    
    public double calculateDistanceInPixels(long timeDifference) {
        double distance = this.speed * (timeDifference / 1000000);
//        System.out.println("distance: " + distance);
//        System.out.println("speed: " + speed);
        return distance;
    }
    
    public void resetVariables() {
        this.speed = 0;
    }
    
    public void setDirectionInDegrees(double newDirection) {
        if (newDirection / 360 > 1) {
            directionInDegrees = 0 + (newDirection % 360);
        } else if (newDirection / 360 < 0) {
            directionInDegrees = 360 + (newDirection % 360);
        }else  {
            directionInDegrees = newDirection;
        }
    }
    
    public double getDirectionInDegrees() {
        return directionInDegrees;
    }
}
