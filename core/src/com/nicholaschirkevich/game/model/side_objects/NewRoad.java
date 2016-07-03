package com.nicholaschirkevich.game.model.side_objects;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.nicholaschirkevich.game.enums.SideObjectType;
import com.nicholaschirkevich.game.util.GameManager;
import com.nicholaschirkevich.game.util.RandomUtil;

import java.util.ArrayList;

/**
 * Created by Nikolas on 03.07.2016.
 */
public class NewRoad {

    private String id;
    private ArrayList<SideObjectType> sideObjectTypeArrayList = new ArrayList<SideObjectType>();
    private ArrayList<SideObject> sideObjectArrayList = new ArrayList<SideObject>();
    private ArrayList<SideObjectType> staticSideRightObjectTypeArrayList = new ArrayList<SideObjectType>();
    private ArrayList<SideObjectType> staticSideLeftObjectTypeArrayList = new ArrayList<SideObjectType>();
    private ArrayList<SideObject> staticRightSideObjectArrayList = new ArrayList<SideObject>();
    private ArrayList<SideObject> staticLeftSideObjectArrayList = new ArrayList<SideObject>();
    private Texture roadTexture;
    private Vector2 posRoad1, posRoad2;

    private static int posX = -15;


    public NewRoad(Texture roadTexture, ArrayList<SideObjectType> sideObjectTypeArrayList, ArrayList<SideObjectType> staticSideRightObjectTypeArrayList, ArrayList<SideObjectType> staticSideLeftObjectTypeArrayList) {

        this.roadTexture = roadTexture;
        posRoad1 = new Vector2(posX, -300);
        posRoad2 = new Vector2(posX, roadTexture.getHeight() - 300);
        this.sideObjectTypeArrayList.addAll(sideObjectTypeArrayList);
        this.staticSideRightObjectTypeArrayList = staticSideRightObjectTypeArrayList;
        this.staticSideLeftObjectTypeArrayList = staticSideLeftObjectTypeArrayList;

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


    }

    public void drawStaticObject(SpriteBatch sb) {
        for (SideObject sideObject : sideObjectArrayList) {
            sideObject.draw(sb);
        }
        for (SideObject sideObject : staticRightSideObjectArrayList) {
            sideObject.draw(sb);
        }
        for (SideObject sideObject : staticLeftSideObjectArrayList) {
            sideObject.draw(sb);
        }
    }

    public void update(Camera camera, float dt) {


        if (camera.position.y - (camera.viewportHeight / 2) > posRoad1.y + roadTexture.getHeight()) {
            posRoad1.add(0, roadTexture.getHeight() * 2);


        }
        if (camera.position.y - (camera.viewportHeight / 2) > posRoad2.y + roadTexture.getHeight()) {
            posRoad2.add(0, roadTexture.getHeight() * 2);

        }


        posRoad1.set(-15, posRoad1.y + (-GameManager.getCurrentSpeed()) * dt);
        posRoad2.set(-15, posRoad2.y + (-GameManager.getCurrentSpeed()) * dt);
        if (staticLeftSideObjectArrayList.isEmpty()) {
            for (SideObjectType sideObject : staticSideLeftObjectTypeArrayList) {

                SideObject sideObjectLeft = new SideObject(sideObject);
                sideObjectLeft.setIsLeft(true);
                sideObjectLeft.setDistance(200);
                staticLeftSideObjectArrayList.add(sideObjectLeft);

            }
        } else {

            SideObject sideObjectLast = staticLeftSideObjectArrayList.get(staticLeftSideObjectArrayList.size() - 1);
            if (camera.viewportHeight - sideObjectLast.getPosition().y > sideObjectLast.getDistance()) {
                SideObject sideObjectLeft = new SideObject(sideObjectLast.getType(), sideObjectLast.isLeft());
                sideObjectLeft.setIsLeft(true);
                sideObjectLeft.setDistance(sideObjectLast.getDistance());
                staticLeftSideObjectArrayList.add(sideObjectLeft);
            }
            for (int i = 0; i < staticLeftSideObjectArrayList.size(); i++) {
                if (staticLeftSideObjectArrayList.get(i).getPosition().y < -20) {
                    staticLeftSideObjectArrayList.remove(i);
                }
                staticLeftSideObjectArrayList.get(i).update(camera, dt);
            }
        }

        if (staticRightSideObjectArrayList.isEmpty()) {
            for (SideObjectType sideObject : staticSideRightObjectTypeArrayList) {

                SideObject sideObjectRight = new SideObject(sideObject);
                sideObjectRight.setIsLeft(false);
                sideObjectRight.setDistance(200);

                staticRightSideObjectArrayList.add(sideObjectRight);

            }
        } else {

            SideObject sideObjectLast = staticRightSideObjectArrayList.get(staticRightSideObjectArrayList.size() - 1);
            if (camera.viewportHeight - sideObjectLast.getPosition().y > sideObjectLast.getDistance()) {
                SideObject sideObjectLeft = new SideObject(sideObjectLast.getType(), sideObjectLast.isLeft());
                sideObjectLeft.setIsLeft(true);
                sideObjectLeft.setDistance(sideObjectLast.getDistance());
                staticRightSideObjectArrayList.add(sideObjectLeft);
            }
            for (int i = 0; i < staticRightSideObjectArrayList.size(); i++) {
                if (staticRightSideObjectArrayList.get(i).getPosition().y < -20) {
                    staticRightSideObjectArrayList.remove(i);
                }
                staticRightSideObjectArrayList.get(i).update(camera, dt);
            }
        }

        if (sideObjectArrayList.isEmpty()) {
            sideObjectArrayList.add(new SideObject(sideObjectTypeArrayList.get(RandomUtil.getRand(0, sideObjectTypeArrayList.size() - 1))));
        } else if (camera.viewportHeight - sideObjectArrayList.get(sideObjectArrayList.size() - 1).getPosition().y > 10) {
            sideObjectArrayList.add(new SideObject(sideObjectTypeArrayList.get(RandomUtil.getRand(0, sideObjectTypeArrayList.size() - 1))));
        }
        for (int i = 0; i < sideObjectArrayList.size(); i++) {

            if (sideObjectArrayList.get(i).getPosition().y < -20) {
                sideObjectArrayList.remove(i);
            }
            sideObjectArrayList.get(i).update(camera, dt);
        }


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

    public ArrayList<SideObjectType> getSideObjectTypeArrayList() {
        return sideObjectTypeArrayList;
    }

    public void setSideObjectTypeArrayList(ArrayList<SideObjectType> sideObjectTypeArrayList) {
        this.sideObjectTypeArrayList = sideObjectTypeArrayList;
    }
}
