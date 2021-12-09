/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.input.KeyCode;

/**
 *
 * @author Rami.Yasir
 */
public class UserInterface {

    private HashMap<KeyCode, Boolean> pressedKeys;
//    private HashMap<KeyCode, Boolean> pressedArrowKeys;

    public UserInterface() {
        this.pressedKeys = new HashMap<>();
//        this.pressedArrowKeys = new HashMap<>();
    }

    public void add(KeyCode keyCode, Boolean value) {
        pressedKeys.put(keyCode, value);
//        addArrowKeys();
    }

    public HashMap<KeyCode, Boolean> getPressedKeys() {
        return pressedKeys;
    }
    
//    private void addArrowKeys() {
//        // don't like this. maybe scrap it.
//        for (KeyCode key : pressedKeys.keySet()) {
//            if (key == KeyCode.LEFT || key == KeyCode.RIGHT || key == KeyCode.UP || key == KeyCode.DOWN) {
//                pressedArrowKeys.put(key, pressedKeys.get(key));
//            }
//        }
//        System.out.println(pressedArrowKeys);
//    }
    
    public Directions determineDirectionFaced() {
        if (pressedKeys.getOrDefault(KeyCode.LEFT, Boolean.FALSE) && pressedKeys.getOrDefault(KeyCode.UP, Boolean.FALSE)) {
            System.out.println("NORTHWEST");
            return Directions.NORTHWEST;
        }
        
        if (pressedKeys.getOrDefault(KeyCode.LEFT, Boolean.FALSE) && pressedKeys.getOrDefault(KeyCode.DOWN, Boolean.FALSE)) {
            System.out.println("SOUTHWEST");
            return Directions.SOUTHWEST;
        }
        
        if (pressedKeys.getOrDefault(KeyCode.RIGHT, Boolean.FALSE) && pressedKeys.getOrDefault(KeyCode.UP, Boolean.FALSE)) {
            System.out.println("NORTHEAST");
            return Directions.NORTHEAST;
        }
        
        if (pressedKeys.getOrDefault(KeyCode.RIGHT, Boolean.FALSE) && pressedKeys.getOrDefault(KeyCode.DOWN, Boolean.FALSE)) {
            System.out.println("SOUTHEAST");
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
