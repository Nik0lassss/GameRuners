package com.nicholaschirkevich.game.model.side_objects;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.nicholaschirkevich.game.util.GameManager;

import java.util.ArrayList;

/**
 * Created by Nikolas on 03.07.2016.
 */
public class NewRoad {

    private String id;
    private ArrayList<SideObject> sideObjectArrayList = new ArrayList<SideObject>();
    private Texture roadTexture;
    private Vector2 posRoad1, posRoad2;

    private static int posX = -15;


    public NewRoad(Texture roadTexture) {

        this.roadTexture = roadTexture;
        posRoad1 = new Vector2(posX, -300);
        posRoad2 = new Vector2(posX, roadTexture.getHeight() - 300);

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<SideObject> getSideObjectArrayList() {
        return sideObjectArrayList;
    }

    public void setSideObjectArrayList(ArrayList<SideObject> sideObjectArrayList) {
        this.sideObjectArrayList = sideObjectArrayList;
    }

    public void draw(SpriteBatch sb) {
        sb.draw(roadTexture, posRoad1.x, posRoad1.y);
        sb.draw(roadTexture, posRoad2.x, posRoad2.y);
        for (SideObject sideObject : sideObjectArrayList) {
            sideObject.draw(sb);
        }
    }

    public void update(Camera camera, float dt) {


        if (camera.position.y - (camera.viewportHeight / 2) > posRoad1.y +  roadTexture.getHeight()) {
            posRoad1.add(0, roadTexture.getHeight() * 2);


        }
        if (camera.position.y - (camera.viewportHeight / 2) > posRoad2.y +  roadTexture.getHeight()) {
            posRoad2.add(0,  roadTexture.getHeight() * 2);

        }

//

        posRoad1.set(-15, posRoad1.y + (-GameManager.getCurrentSpeed() ) * dt);
        posRoad2.set(-15, posRoad2.y + (-GameManager.getCurrentSpeed()) * dt);


    }

    public Texture getRoadTexture() {

        return roadTexture;
    }

    public void setRoadTexture(Texture roadTexture) {
        this.roadTexture = roadTexture;
    }


    public Vector2 getPosRoad1() {
        return posRoad1;
    }

    public void setPosRoad1(Vector2 posRoad1) {
        this.posRoad1 = posRoad1;
    }

    public Vector2 getPosRoad2() {
        return posRoad2;
    }

    public void setPosRoad2(Vector2 posRoad2) {
        this.posRoad2 = posRoad2;
    }

    public static int getPosX() {
        return posX;
    }

    public static void setPosX(int posX) {
        NewRoad.posX = posX;
    }
}
