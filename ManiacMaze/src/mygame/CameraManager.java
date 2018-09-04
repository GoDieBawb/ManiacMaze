/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
package mygame;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.collision.CollisionResults;
import com.jme3.input.ChaseCamera;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;

/**
*
* @author Bob
*/
public class CameraManager {

    private final SimpleApplication app;
    private final ChaseCamera       cam;
    private final Node              cameraNode;
  
    public CameraManager(Application app){
        this.app          = (SimpleApplication) app;
        cameraNode        = new Node();
        cam               = new ChaseCamera(this.app.getCamera(), cameraNode, app.getInputManager());
        setFrustrum();
        initCamera();
    }
  
    private void setFrustrum() {
        float scale = .5f;
        app.getCamera().setFrustumNear(app.getCamera().getFrustumNear()*scale);
        app.getCamera().setFrustumLeft(app.getCamera().getFrustumLeft()*scale);
        app.getCamera().setFrustumRight(app.getCamera().getFrustumRight()*scale);
        app.getCamera().setFrustumTop(app.getCamera().getFrustumTop()*scale);
        app.getCamera().setFrustumBottom(app.getCamera().getFrustumBottom()*scale);
    }
  
  //Creates camera
    private void initCamera() {
        app.getFlyByCamera().setEnabled(false);
        app.getRootNode().attachChild(cameraNode);
        cam.setMinDistance(0.5f);
        cam.setMaxDistance(.5f);
        cam.setDefaultDistance(.5f);
        cam.setDragToRotate(false);
        cam.setDownRotateOnCloseViewOnly(false);
        cam.setRotationSpeed(4f);
        cam.setDefaultVerticalRotation(3f);
        cam.setMaxVerticalRotation(4f);
        cam.setMinVerticalRotation(2f);
    }
  
    public boolean moveCheck(Vector3f moveDir, Node collisionNode) {

        Ray              ray     = new Ray(cameraNode.getLocalTranslation().multLocal(1,0,1).add(0,1,0), moveDir);
        CollisionResults results = new CollisionResults();
        collisionNode.collideWith(ray, results);

        for (int i = 0; i < results.size(); i++) {

            float dist = results.getCollision(i).getContactPoint().distance(cameraNode.getLocalTranslation());

            return results.getCollision(i).getContactPoint().distance(cameraNode.getLocalTranslation()) >= 1.1f;

        }

        return true;

    }  
  
    public void enable() {
        cam.setEnabled(true);
        app.getInputManager().setCursorVisible(false);
        cam.setDragToRotate(false);
    }
  
    public void disable() {
        cam.setEnabled(false);
        app.getInputManager().setCursorVisible(true);
    }
  
    public Node getCameraNode() {
        return cameraNode;
    }
  
    public void update(float tpf) {
        cam.setDefaultDistance(.6f);
        cam.setDefaultVerticalRotation(.145f);
    }
  
}