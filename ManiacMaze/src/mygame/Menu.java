
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Quad;
import com.jme3.texture.Texture;
import java.util.ArrayList;

/**
 *
 * @author Bob
 */
public class Menu {
    
    private final SimpleApplication     app;
    private final Node                  menuNode;
    private final ArrayList<Geometry>   buttons;
    
    public Menu(SimpleApplication app) {
        this.app = app;
        menuNode = new Node("MenuNode");
        buttons  = new ArrayList<>();
        createMenu();
    }
    
    private void createMenu() {
        
        Quad title = new Quad(10, 10); // replace the definition of Vertex and Textures Coordinates plus indexes
        Geometry g = new Geometry("Title", title); // using Quad object
        Material m = new Material(app.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        Texture  t = app.getAssetManager().loadTexture("Textures/Title.png");
        m.setTexture("ColorMap", t);
        m.setFloat("AlphaDiscardThreshold", 0.1f);
        g.setMaterial(m);
        g.setLocalTranslation(-5, -3.5f, -10);
        menuNode.attachChild(g);
        
        float quadWidth  = 1.5f;
        float quadHeight = 1.5f;
        
        float y = 0;
        float x = (quadWidth*-2.5f) - (quadWidth*1.25f)/2;
        
        Node buttonNode = new Node("ButtonNode");
        menuNode.attachChild(buttonNode);
        
        for (int i = 0; i < 10; i++) {
            
            Quad quad = new Quad(quadHeight,quadWidth);
            Geometry geo = new Geometry(String.valueOf(i+1), quad);
            Material mat = new Material(app.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");

            Texture text = app.getAssetManager().loadTexture("Textures/Buttons/" + String.valueOf(i+1) + ".png");
            mat.setTexture("ColorMap", text);
            mat.setFloat("AlphaDiscardThreshold", 0.1f);
            geo.setMaterial(mat);
            buttons.add(geo);
            geo.setLocalTranslation(x,y,-10);
            
            if (i == 4) {
                y = 0-quad.getHeight()*1.25f;
                x = (quadWidth*-2.5f) - (quadWidth*1.25f)/2;
            }
            
            else {
                x += quad.getWidth()*1.25f;
            }
            
        }    
        
    }
    
    public void showMenu() {
        
        Spatial s = app.getAssetManager().loadModel("Scenes/Pieces/Room1.j3o");
        s.setName("Room");
        s.setLocalTranslation(0, 0, -15);
        s.rotate(90,0,0);
        s.scale(1.25f);
        menuNode.attachChild(s);
        
        int highscore = app.getStateManager().getState(GameManager.class).getScoreManager().getHighScore();
        
        for (int i = 0; i < 10; i++) {
            
           Node bn = (Node) menuNode.getChild("ButtonNode");
           bn.attachChild(buttons.get(i));
           
           if (i == highscore) break;
           
        }
        
        app.getCamera().setLocation(new Vector3f(0,0,0));
        app.getCamera().lookAtDirection(new Vector3f(0,0,-1), new Vector3f(0,1,0));
        app.getRootNode().attachChild(menuNode);
        
    }
    
    public void selectLevel(String level) {
        menuNode.detachChildNamed("Room");
        app.getStateManager().getState(GameManager.class).startLevel("Scenes/Levels/Level" + level + ".j3o");
        app.getRootNode().detachChild(menuNode);
    }
    
    public void update(float tpf) {
        if ( menuNode.getChild("Room") != null)
        menuNode.getChild("Room").rotate(0,1*tpf,0);
    }
    
}
