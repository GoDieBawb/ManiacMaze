/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.asset.AssetManager;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

/**
 *
 * @author Bob
 */
public class EntityManager {
    
    private final Player       player;
    private final AssetManager am;
    private final GameManager  gm;
    
    public EntityManager(GameManager gm, AssetManager am) {
        player     = new Player(gm.getCameraManager().getCameraNode());
        this.am    = am;
        this.gm    = gm;
    }
    
    public void update(float tpf) {
        
        Node entityNode = (Node) gm.getSceneManager().getScene().getChild("EntityNode");
        
        for (Spatial s : entityNode.getChildren()) {
            
            if (s.getUserData("Type").equals("Door")) {
                
                String color = (String) s.getUserData("Color");
                
                Node n = (Node) ((Node) s).getChild(0);
                
                if (n.getChild("Door") != null) {
                    s = n.getChild("Door");
                    s.setUserData("Color", color);
                    
                    s.setMaterial(am.loadMaterial("Materials/" + color + ".j3m"));
                    
                }
                
                else {
                    s.setUserData("Type", "Open");
                }
                
            }
            
            float dist = s.getWorldTranslation().distance(player.getLocation());
            
            if (dist < 1.5f) {
                
                String type = s.getUserData("Type");
                
                switch (type) {
                    case "Key":
                        {
                            String color = (String) s.getUserData("Color");
                            player.give(color);
                            s.removeFromParent();
                            break;
                        }
                    case "Door":
                        {
                            String color = (String) s.getUserData("Color");
                            if (player.has(color)) {
                                player.take(color);
                                s.removeFromParent();
                            }       break;
                        }
                    case "End":
                        gm.finishLevel();
                        break;
                    default:
                        break;
                }
                
            }
            
        }
        
    }
    
}
