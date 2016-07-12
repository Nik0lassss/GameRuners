package com.nicholaschirkevich.game.model.side_objects;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.nicholaschirkevich.game.GameRuners;
import com.nicholaschirkevich.game.enums.SideObjectType;
import com.nicholaschirkevich.game.enums.TraffictLighterEnum;
import com.nicholaschirkevich.game.interfaces.OnTrafficLightListener;
import com.nicholaschirkevich.game.util.Constants;
import com.nicholaschirkevich.game.util.GameManager;
import com.nicholaschirkevich.game.util.RandomUtil;

import java.util.ArrayList;

/**
 * Created by Nikolas on 03.07.2016.
 */
public class NewRoad {

    private String id;
    private ArrayList<SideObjectType> sideObjectRightTypeArrayList = new ArrayList<SideObjectType>();
    private ArrayList<SideObjectType> sideObjectLeftTypeArrayList = new ArrayList<SideObjectType>();
    private ArrayList<SideObject> sideObjectRightArrayList = new ArrayList<SideObject>();
    private ArrayList<SideObject> sideObjectLeftArrayList = new ArrayList<SideObject>();
    private ArrayList<SideObjectType> staticSideRightObjectTypeArrayList = new ArrayList<SideObjectType>();
    private ArrayList<SideObjectType> staticSideLeftObjectTypeArrayList = new ArrayList<SideObjectType>();
    private ArrayList<SideObject> staticRightSideObjectArrayList = new ArrayList<SideObject>();
    private ArrayList<SideObject> staticLeftSideObjectArrayList = new ArrayList<SideObject>();
    private Texture roadTexture;
    private Vector2 posRoad1, posRoad2;
    private TrafficLight trafficLight;
    private static int posX = -15;
    private Vector3 posStartLine, trafficLighterPosition;
    private float leftTopPos = 500, rightTopPos = 500;
    private World world;

    public void setWorld(World world) {
        this.world = world;
        generateRoadSide();
    }


    public void setOnTrafficLightListener(OnTrafficLightListener onTrafficLightListener) {
        trafficLight.setOnTrafficLightListener(onTrafficLightListener);
    }

    private OnTrafficLightListener onTrafficLightListener;

    public TrafficLight getTrafficLight() {
        return trafficLight;
    }


    public NewRoad(Texture roadTexture, ArrayList<SideObjectType> sideObjectRightTypeArrayList, ArrayList<SideObjectType> sideObjectLeftTypeArrayList, ArrayList<SideObjectType> staticSideRightObjectTypeArrayList, ArrayList<SideObjectType> staticSideLeftObjectTypeArrayList, TraffictLighterEnum traffictLighterEnum) {


        this.roadTexture = roadTexture;
        posRoad1 = new Vector2(posX, -300);
        posRoad2 = new Vector2(posX, roadTexture.getHeight() - 300);
        this.sideObjectRightTypeArrayList.addAll(sideObjectRightTypeArrayList);
        this.sideObjectLeftTypeArrayList.addAll(sideObjectLeftTypeArrayList);
        this.staticSideRightObjectTypeArrayList = staticSideRightObjectTypeArrayList;
        this.staticSideLeftObjectTypeArrayList = staticSideLeftObjectTypeArrayList;
        trafficLight = new TrafficLight(41, 350, traffictLighterEnum);
        posStartLine = trafficLight.getTraffictLighterEnum().getPositionStartLine().cpy();
        trafficLighterPosition = trafficLight.getTraffictLighterEnum().getPosition().cpy();
        initialRoad();


    }

    public void generateRoadSide() {
        BodyDef bodyDefSideLeft = new BodyDef();
        bodyDefSideLeft.type = BodyDef.BodyType.DynamicBody;
        bodyDefSideLeft.position.set(0, GameRuners.HEIGHT / 2);

        Body leftSideBody = world.createBody(bodyDefSideLeft);
        PolygonShape leftShape = new PolygonShape();
        leftShape.setAsBox(20, GameRuners.HEIGHT / 2);


        FixtureDef leftFixtureDef = new FixtureDef();
        leftFixtureDef.shape = leftShape;
        leftFixtureDef.filter.categoryBits = Constants.ROAD_SIDE_LEFT;
        leftFixtureDef.filter.maskBits = Constants.WORLD_ENTITY;
        leftFixtureDef.density = 0;
        leftSideBody.createFixture(leftFixtureDef);
    }

    public void initialRoad() {
        if (!sideObjectLeftTypeArrayList.isEmpty()) {
            SideObjectType sideObjectType = sideObjectLeftTypeArrayList.get(RandomUtil.getRand(0, sideObjectLeftTypeArrayList.size() - 1));
            SideObject sideObject = new SideObject(sideObjectType, true, leftTopPos + sideObjectType.getDistance());

            while (leftTopPos < 1200) {

                sideObjectLeftArrayList.add(sideObject);
                leftTopPos += sideObject.getHeight();
                SideObjectType sideObjectType1 = sideObjectLeftTypeArrayList.get(RandomUtil.getRand(0, sideObjectLeftTypeArrayList.size() - 1));
                leftTopPos += sideObjectType1.getDistance();
                sideObject = new SideObject(sideObjectType1, true, leftTopPos);

            }
        }
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<SideObject> getSideObjectRightArrayList() {
        return sideObjectRightArrayList;
    }

    public void setSideObjectRightArrayList(ArrayList<SideObject> sideObjectRightArrayList) {
        this.sideObjectRightArrayList = sideObjectRightArrayList;
    }

    public void draw(SpriteBatch sb) {
        sb.draw(roadTexture, posRoad1.x, posRoad1.y);
        sb.draw(roadTexture, posRoad2.x, posRoad2.y);
        sb.draw(trafficLight.getTraffictLighterEnum().getStart_line(), posStartLine.x, posStartLine.y);


    }

    public void drawStaticObject(SpriteBatch sb) {


        for (int i = staticRightSideObjectArrayList.size() - 1; i >= 0; i--) {
            staticRightSideObjectArrayList.get(i).draw(sb);
        }

        for (int i = staticLeftSideObjectArrayList.size() - 1; i >= 0; i--) {
            staticLeftSideObjectArrayList.get(i).draw(sb);
        }
        for (int i = sideObjectRightArrayList.size() - 1; i >= 0; i--) {
            sideObjectRightArrayList.get(i).draw(sb);
//            System.out.println("draw right" + sideObjectRightArrayList.get(i).getPosition().x + " " + sideObjectRightArrayList.get(i).getPosition().y);
//            System.out.println("------------------");
        }
        for (int i = sideObjectLeftArrayList.size() - 1; i >= 0; i--) {
            sideObjectLeftArrayList.get(i).draw(sb);
//            System.out.println("draw left" + sideObjectLeftArrayList.get(i).getPosition().x + " " + sideObjectLeftArrayList.get(i).getPosition().y);
//            System.out.println("------------------");
        }

        sb.draw(trafficLight.getTexture(), trafficLighterPosition.x, trafficLighterPosition.y);

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

                SideObject sideObjectLeft = new SideObject(sideObject, true);
                sideObjectLeft.setIsLeft(true);
                sideObjectLeft.setDistance(200);
                staticLeftSideObjectArrayList.add(sideObjectLeft);

            }
        } else {
            ArrayList<SideObject> sideleftobjects = new ArrayList<SideObject>();
            for (int i = staticLeftSideObjectArrayList.size() - 1; i >= 0; i--) {
                if (!sideleftobjects.contains(staticLeftSideObjectArrayList.get(i))) {
                    sideleftobjects.add(staticLeftSideObjectArrayList.get(i));
                }
            }
            for (SideObject sideObjectLastLeft : sideleftobjects) {
                //SideObject sideObjectLast = staticLeftSideObjectArrayList.get(staticLeftSideObjectArrayList.size() - 1);
                if (camera.viewportHeight - sideObjectLastLeft.getPosition().y > sideObjectLastLeft.getDistance()) {
                    SideObject sideObjectLeft;
                    if (sideObjectLastLeft.getType().isAddToTop() && !sideObjectLeftArrayList.isEmpty()) {

                        sideObjectLeft = new SideObject(sideObjectLastLeft.getType(), sideObjectLastLeft.isLeft(), leftTopPos);
                    } else
                        sideObjectLeft = new SideObject(sideObjectLastLeft.getType(), sideObjectLastLeft.isLeft());
                    sideObjectLeft.setIsLeft(true);
                    sideObjectLeft.setDistance(sideObjectLastLeft.getDistance());
                    for (SideObject sideObject1 : sideObjectLeftArrayList) {
                        if (Intersector.overlaps(sideObject1.getShape(), sideObjectLeft.getShape()) && sideObject1.getType().isMovible()) {

                            sideObject1.setPosition(new Vector3(sideObjectLeft.getPosition().x - sideObject1.getWidth() - 10, sideObject1.getPosition().y, 0));

                        }

                    }

                    staticLeftSideObjectArrayList.add(sideObjectLeft);
                }
            }
            for (int i = 0; i < staticLeftSideObjectArrayList.size(); i++) {
                if (staticLeftSideObjectArrayList.get(i).getPosition().y < -2000) {
                    staticLeftSideObjectArrayList.remove(i);
                }
                staticLeftSideObjectArrayList.get(i).update(camera, dt);
            }

        }
        if (staticRightSideObjectArrayList.isEmpty()) {
            for (SideObjectType sideObject : staticSideRightObjectTypeArrayList) {

                SideObject sideObjectRight = new SideObject(sideObject, false);
                sideObjectRight.setIsLeft(false);
                sideObjectRight.setDistance(200);

                staticRightSideObjectArrayList.add(sideObjectRight);

            }
        } else {

            ArrayList<SideObject> siderightobjects = new ArrayList<SideObject>();
            for (int i = staticRightSideObjectArrayList.size() - 1; i >= 0; i--) {
                if (!siderightobjects.contains(staticRightSideObjectArrayList.get(i))) {
                    siderightobjects.add(staticRightSideObjectArrayList.get(i));
                }
            }

            for (SideObject sideObject : siderightobjects) {
                if (camera.viewportHeight - sideObject.getPosition().y > sideObject.getDistance()) {
                    SideObject sideObjectLeft = new SideObject(sideObject.getType(), sideObject.isLeft());
                    sideObjectLeft.setIsLeft(true);
                    sideObjectLeft.setDistance(sideObject.getDistance());
                    for (SideObject sideObject1 : staticRightSideObjectArrayList) {
                        if (Intersector.overlaps(sideObject1.getShape(), sideObjectLeft.getShape()) && sideObject1.getType().isMovible()) {

                            sideObject1.setPosition(new Vector3(sideObjectLeft.getPosition().x + sideObject1.getWidth() + 10, sideObject1.getPosition().y, 0));

                        }

                    }
                    staticRightSideObjectArrayList.add(sideObjectLeft);
                }
            }
            for (int i = 0; i < staticRightSideObjectArrayList.size(); i++) {
                if (staticRightSideObjectArrayList.get(i).getPosition().y < -2000) {
                    staticRightSideObjectArrayList.remove(i);
                }
                staticRightSideObjectArrayList.get(i).update(camera, dt);
            }
        }


        if (sideObjectLeftArrayList.isEmpty()) {
            //SideObject sideObject = new SideObject(sideObjectLeftTypeArrayList.get(RandomUtil.getRand(0, sideObjectLeftTypeArrayList.size() - 1)),true);
            if (!sideObjectLeftTypeArrayList.isEmpty())
                sideObjectLeftArrayList.add(new SideObject(sideObjectLeftTypeArrayList.get(RandomUtil.getRand(0, sideObjectLeftTypeArrayList.size() - 1)), true));
        } else if (GameRuners.HEIGHT - sideObjectLeftArrayList.get(sideObjectLeftArrayList.size() - 1).getPosition().y + sideObjectLeftArrayList.get(sideObjectLeftArrayList.size() - 1).getHeight() > 560) {

            leftTopPos = sideObjectLeftArrayList.get(sideObjectLeftArrayList.size() - 1).getPosition().y + sideObjectLeftArrayList.get(sideObjectLeftArrayList.size() - 1).getHeight();
            SideObjectType sideObjectType = sideObjectLeftTypeArrayList.get(RandomUtil.getRand(0, sideObjectLeftTypeArrayList.size() - 1));
            //        SideObject sideObject = new SideObject(sideObjectLeftTypeArrayList.get(RandomUtil.getRand(0, sideObjectLeftTypeArrayList.size() - 1)), true, leftTopPos);
            SideObject sideObject = new SideObject(sideObjectType, true, leftTopPos + sideObjectType.getDistance());
            ;
            if (!sideObjectType.isMovible()) {
                if (leftTopPos > rightTopPos) {
                    SideObjectType sideObjectType1 = sideObjectLeftTypeArrayList.get(RandomUtil.getRand(0, sideObjectLeftTypeArrayList.size() - 1));
                    sideObject = new SideObject(sideObjectType1, true, leftTopPos + sideObjectType1.getDistance());
                    leftTopPos += sideObject.getHeight();
                } else {
                    SideObjectType sideObjectType1 = sideObjectLeftTypeArrayList.get(RandomUtil.getRand(0, sideObjectLeftTypeArrayList.size() - 1));
                    sideObject = new SideObject(sideObjectType1, true, rightTopPos + sideObjectType1.getDistance());
                    rightTopPos += sideObject.getHeight();
                }
            }
            for (SideObject sideObject1 : staticLeftSideObjectArrayList) {
                if (Intersector.overlaps(sideObject1.getShape(), sideObject.getShape()) && sideObject.getType().isMovible()) {

                    sideObject.setPosition(new Vector3(sideObject1.getPosition().x - sideObject.getWidth() - 10, sideObject.getPosition().y, 0));
//
                }
            }

            sideObjectLeftArrayList.add(sideObject);

        }
        for (int i = 0; i < sideObjectLeftArrayList.size(); i++) {

            if (sideObjectLeftArrayList.get(i).getPosition().y < -200) {
                sideObjectLeftArrayList.remove(i);
            }
            sideObjectLeftArrayList.get(i).update(camera, dt);
        }


        if (sideObjectRightArrayList.isEmpty()) {
            //SideObject sideObject = new SideObject(sideObjectLeftTypeArrayList.get(RandomUtil.getRand(0, sideObjectLeftTypeArrayList.size() - 1)),true);
            if (!sideObjectRightTypeArrayList.isEmpty())
                sideObjectRightArrayList.add(new SideObject(sideObjectRightTypeArrayList.get(RandomUtil.getRand(0, sideObjectRightTypeArrayList.size() - 1)), true));
        } else if (GameRuners.HEIGHT - sideObjectRightArrayList.get(sideObjectRightArrayList.size() - 1).getPosition().y + sideObjectRightArrayList.get(sideObjectRightArrayList.size() - 1).getHeight() > 560) {
            // SideObject sideObject = new SideObject(sideObjectLeftTypeArrayList.get(RandomUtil.getRand(0, sideObjectLeftTypeArrayList.size() - 1)),true);
            rightTopPos = sideObjectRightArrayList.get(sideObjectRightArrayList.size() - 1).getPosition().y + sideObjectRightArrayList.get(sideObjectRightArrayList.size() - 1).getHeight();
            SideObjectType sideObjectType = sideObjectRightTypeArrayList.get(RandomUtil.getRand(0, sideObjectRightTypeArrayList.size() - 1));
            SideObject sideObject = new SideObject(sideObjectType, true, rightTopPos + sideObjectType.getDistance());

            for (SideObject sideObject1 : staticRightSideObjectArrayList) {
                if (Intersector.overlaps(sideObject1.getShape(), sideObject.getShape()) && sideObject.getType().isMovible()) {

                    sideObject.setPosition(new Vector3(sideObject1.getPosition().x + sideObject1.getWidth() + 10, sideObject.getPosition().y, 0));
//
                }
            }
            SideObjectType sideObjectType1 = sideObjectRightTypeArrayList.get(RandomUtil.getRand(0, sideObjectRightTypeArrayList.size() - 1));
            //sideObjectRightArrayList.add(new SideObject(sideObjectRightTypeArrayList.get(RandomUtil.getRand(0, sideObjectRightTypeArrayList.size() - 1)), false, sideObjectRightArrayList.get(sideObjectRightArrayList.size() - 1).getPosition().y + sideObjectRightArrayList.get(sideObjectRightArrayList.size() - 1).getHeight()));
            sideObjectRightArrayList.add(new SideObject(sideObjectType1, false, rightTopPos + sideObjectType1.getDistance()));
        }
        for (int i = 0; i < sideObjectRightArrayList.size(); i++) {

            if (sideObjectRightArrayList.get(i).getPosition().y < -200) {
                sideObjectRightArrayList.remove(i);
            }
            sideObjectRightArrayList.get(i).update(camera, dt);
        }

//

        posStartLine.add(0, (-GameManager.getCurrentSpeed()) * dt, 0);
        trafficLighterPosition.add(0, (-GameManager.getCurrentSpeed()) * dt, 0);

        ///////////teaffict lighter


    }

    public void updateTrafficLighter(float dt) {
        trafficLight.update(dt);
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
