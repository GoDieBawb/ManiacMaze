/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.math.Vector2f;

/**
 *
 * @author Bob
 */
public class GameControl implements InputControl {
    
    private final SimpleApplication app;
    
    public GameControl(SimpleApplication app) {
        this.app = app;
    }
        
    
    @Override
    public void onClick(Vector2f clickSpot) {
    }
    
}
