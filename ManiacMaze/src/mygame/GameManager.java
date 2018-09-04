/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;

/**
 *
 * @author Bob
 */
public class GameManager extends AbstractAppState {
    
    private final EntityManager      entityManager;
    private final CameraManager      cameraManager;
    private final SceneManager       sceneManager;
    private final InteractionManager interactionManager;
    private final Menu               menu;
    private final MenuControl        mc;
    private final GameControl        gc;
    private final ScoreManager       sm;
    private int   currentLevel;
    
    private boolean atMenu;
    
    public GameManager(SimpleApplication app) {
        cameraManager      = new CameraManager(app);
        sceneManager       = new SceneManager(app);
        interactionManager = new InteractionManager(app);
        entityManager      = new EntityManager(this, app.getAssetManager());
        menu               = new Menu(app);
        mc                 = new MenuControl(app, menu);
        gc                 = new GameControl(app);
        sm                 = new ScoreManager(app.getStateManager());
    }
    
    public void start() {
        atMenu = true;
        menu.showMenu();
        cameraManager.disable();
    }
    
    public void startLevel(String path) {
        
        String level = path.split("/Levels/Level")[1];
        level = level.replace(".j3o", "");
        currentLevel = Integer.valueOf(level);
        
        sceneManager.setScene(path);
        cameraManager.getCameraNode().setLocalTranslation(sceneManager.getScene().getChild("Start").getLocalTranslation());
        cameraManager.enable();
        atMenu = false;
    }
    
    public void finishLevel() {
        
        if (sm.getHighScore() < currentLevel) {
            sm.saveNewHighScore(currentLevel);
        }
        
        sceneManager.getScene().removeFromParent();        
        menu.showMenu();
        cameraManager.disable();
        atMenu = true;
    }
    
    public EntityManager getEntityManager() {
        return entityManager;
    }
    
    public CameraManager getCameraManager() {
        return cameraManager;
    }
    
    public SceneManager getSceneManager() {
        return sceneManager;
    }
    
    public ScoreManager getScoreManager() {
        return sm;
    }
    
    public InputControl getInputControl() {
        if (atMenu) return mc;
        else return gc;
    } 
    
    public int currentLevel() {
        return currentLevel;
    }
    
    @Override
    public void update(float tpf) {
        
        if (!atMenu) {
            interactionManager.update(tpf);
            entityManager.update(tpf);
        }
        else {
            menu.update(tpf);
        }
        
    }
    
}
