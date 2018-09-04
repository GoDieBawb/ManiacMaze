/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import java.util.ArrayList;

/**
 *
 * @author Bob
 */
public class Player {
    
    private final Node               cameraNode;
    private final ArrayList<String>  inventory;
    private int   level;
    
    public Player(Node cameraNode) {
        this.cameraNode = cameraNode;
        inventory       = new ArrayList<>();
    }
    
    public boolean has(String item) {
        return inventory.contains(item);
    }
    
    public void give(String item) {
        inventory.add(item);
    }
    
    public void take(String item) {
        inventory.remove(item);
    }
    
    public void clearInventory() {
        inventory.clear();
    }
    
    public Vector3f getLocation() {
        return cameraNode.getLocalTranslation();
    }
    
    public int getLevel() {
        return level;
    }
    
    public void advanceLevel() {
        level++;
    }
    
}
