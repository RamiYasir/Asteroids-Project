/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids;

import java.util.HashMap;
import javafx.scene.input.KeyCode;

/**
 *
 * @author Rami.Yasir
 */
public class UserInterface {

    private HashMap<KeyCode, Boolean> pressedKeys;

    public UserInterface() {
        this.pressedKeys = new HashMap<>();
    }

    public void add(KeyCode keyCode, Boolean value) {
        pressedKeys.put(keyCode, value);
    }

    public HashMap<KeyCode, Boolean> getPressedKeys() {
        return pressedKeys;
    }
    
    public Directions determineDirectionFaced() {
        if (pressedKeys.getOrDefault(KeyCode.LEFT, Boolean.FALSE) && pressedKeys.getOrDefault(KeyCode.UP, Boolean.FALSE)) {
            return Directions.NORTHWEST;
        }
        
        if (pressedKeys.getOrDefault(KeyCode.LEFT, Boolean.FALSE) && pressedKeys.getOrDefault(KeyCode.DOWN, Boolean.FALSE)) {
            return Directions.SOUTHWEST;
        }
        
        if (pressedKeys.getOrDefault(KeyCode.RIGHT, Boolean.FALSE) && pressedKeys.getOrDefault(KeyCode.UP, Boolean.FALSE)) {
            return Directions.NORTHEAST;
        }
        
        if (pressedKeys.getOrDefault(KeyCode.RIGHT, Boolean.FALSE) && pressedKeys.getOrDefault(KeyCode.DOWN, Boolean.FALSE)) {
            return Directions.SOUTHEAST;
        }
        
        if (pressedKeys.getOrDefault(KeyCode.LEFT, Boolean.FALSE)) {
            return Directions.WEST;
        }
        
        if (pressedKeys.getOrDefault(KeyCode.UP, Boolean.FALSE)) {
            return Directions.NORTH;
        }
        
        if (pressedKeys.getOrDefault(KeyCode.RIGHT, Boolean.FALSE)) {
            return Directions.EAST;
        }
        
        if (pressedKeys.getOrDefault(KeyCode.DOWN, Boolean.FALSE)) {
            return Directions.SOUTH;
        }
        
        return Directions.NONE;
    }
}
