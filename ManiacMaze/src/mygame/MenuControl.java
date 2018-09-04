/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.collision.CollisionResults;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;

/**
 *
 * @author Bob
 */
public class MenuControl implements InputControl {
    
    private final SimpleApplication app;
    private final Menu              menu;
    
    public MenuControl(SimpleApplication app, Menu menu) {
        this.app  = app;
        this.menu = menu;
    }
    
    @Override
    public void onClick(Vector2f clickPosition) {
        CollisionResults results = new CollisionResults();
        Vector2f click2d = clickPosition;
        Vector3f click3d = app.getCamera().getWorldCoordinates(new Vector2f(click2d.x, click2d.y), 0f).clone();
        Vector3f dir = app.getCamera().getWorldCoordinates(new Vector2f(click2d.x, click2d.y), 1f).subtractLocal(click3d).normalizeLocal();
        // Aim the ray from the clicked spot forwards.
        Ray ray = new Ray(click3d, dir);
        // Collect intersections between ray and all nodes in results list.
        ((Node)app.getRootNode().getChild("MenuNode")).getChild("ButtonNode").collideWith(ray, results);
        // (Print the results so we see what is going on:)
        
        for (int i = 0; i < results.size(); i++) {
            
            // (For each “hit”, we know distance, impact point, geometry.)
            float    dist   = results.getCollision(i).getDistance();
            Vector3f pt     = results.getCollision(i).getContactPoint();
            String   target = results.getCollision(i).getGeometry().getName();
            
            menu.selectLevel(target);
            
        }
        
    }
    
}
