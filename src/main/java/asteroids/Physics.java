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

import java.time.Duration;


public class Physics {
    private int[] velocity;
    private int[] position;
    
    public Physics() {
        velocity = new int[2];
        
    }
    
//    public double calculateVelocity(Duration deltaTime) {
//        // v = u + a * t
//        
//        return velocity + acceleration * (deltaTime.toMillis() / 1000);
//    }
    
}
