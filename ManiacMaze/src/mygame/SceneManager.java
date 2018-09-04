/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.scene.Node;

/**
 *
 * @author Bob
 */
public class SceneManager {
    
    private final SimpleApplication app;
    private Node scene;
    
    public SceneManager(SimpleApplication app) {
        this.app = app;
        scene    = new Node();
    }
    
    public void setScene(String path) {
        app.getRootNode().detachChild(scene);
        scene = (Node) app.getAssetManager().loadModel(path);
        app.getRootNode().attachChild(scene);
    }
    
    public Node getScene() {
        return scene;
    }
    
}
