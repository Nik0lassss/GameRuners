package com.nicholaschirkevich.game.model.side_objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.nicholaschirkevich.game.GameRuners;
import com.nicholaschirkevich.game.enums.RoadTypeEnum;
import com.nicholaschirkevich.game.enums.SideObjectType;
import com.nicholaschirkevich.game.enums.TraffictLighterEnum;
import com.nicholaschirkevich.game.interfaces.OnTrafficLightListener;
import com.nicholaschirkevich.game.util.AssetsManager;
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
    //private Texture roadTexture;
    private ArrayList<RoadTypeEnum> roads = new ArrayList<RoadTypeEnum>();
    private Texture back_tile;
    private TrafficLight trafficLight;

    public boolean isWithCrossroad() {
        return withCrossroad;
    }

    public void setWithCrossroad(boolean withCrossroad) {
        this.withCrossroad = withCrossroad;
    }

    private boolean withCrossroad = false;
    private static int posX;
    private Vector3 posStartLine, trafficLighterPosition;
    private float leftTopPos = 500, rightTopPos = 500;
    private World world;
    private int size = 10;
//
//    private ArrayList<Vector2> positionsRoads = new ArrayList<Vector2>();
//    private ArrayList<Rectangle> boundRoads = new ArrayList<Rectangle>();


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


    public NewRoad(Texture roadTexture, Texture back_tile, ArrayList<SideObjectType> sideObjectRightTypeArrayList, ArrayList<SideObjectType> sideObjectLeftTypeArrayList, ArrayList<SideObjectType> staticSideRightObjectTypeArrayList, ArrayList<SideObjectType> staticSideLeftObjectTypeArrayList, TraffictLighterEnum traffictLighterEnum, boolean withCrossroad) {

        this.withCrossroad = withCrossroad;
        //this.roadTexture = roadTexture;
        this.back_tile = back_tile;
        //this.roadTexture = AssetsManager.getTextureRegion(Constants.ROAD_5_TILE_ID).getTexture();
        roadTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        //posX= 7;
        posX = GameRuners.WIDTH / 4 - roadTexture.getWidth() / 2;
        for (int i = 0; i < size; i++) {
            if (withCrossroad) {
                if (i + 1 == size) {
                    Texture crossroadTexture = AssetsManager.getTextureRegion(Constants.ROAD_4_TILE_CROSSROAD_ID).getTexture();
                    RoadTypeEnum roadTypeEnum = new RoadTypeEnum(false, crossroadTexture);
                    roadTypeEnum.setIsCrossroad(true);
                    roadTypeEnum.setPosRoad(new Vector2(posX, i == 0 ? 20 : (roads.get(i - 1).getPosRoad().y + crossroadTexture.getHeight())));
                    roadTypeEnum.setBounds(new Rectangle(roadTypeEnum.getPosRoad().x, roadTypeEnum.getPosRoad().y, crossroadTexture.getWidth(), crossroadTexture.getHeight()));
                    roads.add(roadTypeEnum);
                } else {
                    RoadTypeEnum roadTypeEnum = new RoadTypeEnum(false, roadTexture);
                    roadTypeEnum.setPosRoad(new Vector2(posX, i == 0 ? 20 : (roads.get(i - 1).getPosRoad().y + roadTexture.getHeight())));
                    roadTypeEnum.setBounds(new Rectangle(roadTypeEnum.getPosRoad().x, roadTypeEnum.getPosRoad().y, roadTexture.getWidth(), roadTexture.getHeight()));
                    roads.add(roadTypeEnum);


                }
            } else {
                RoadTypeEnum roadTypeEnum = new RoadTypeEnum(false, roadTexture);
                roadTypeEnum.setPosRoad(new Vector2(posX, i == 0 ? 20 : (roads.get(i - 1).getPosRoad().y + roadTexture.getHeight())));
                roadTypeEnum.setBounds(new Rectangle(roadTypeEnum.getPosRoad().x, roadTypeEnum.getPosRoad().y, roadTexture.getWidth(), roadTexture.getHeight()));
                roads.add(roadTypeEnum);
            }
//            positionsRoads.add(new Vector2(posX, i == 0 ? 20 : (positionsRoads.get(i - 1).y + 124)));
//            boundRoads.add(new Rectangle(positionsRoads.get(i).x, positionsRoads.get(i).y, roadTexture.getWidth(),roadTexture.getHeight()));
        }

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

        BodyDef bodyDefSideRight = new BodyDef();
        bodyDefSideRight.type = BodyDef.BodyType.DynamicBody;
        bodyDefSideRight.position.set(280, GameRuners.HEIGHT / 2);

        Body rightSideBody = world.createBody(bodyDefSideRight);
        PolygonShape rightShape = new PolygonShape();
        rightShape.setAsBox(20, GameRuners.HEIGHT / 2);


        FixtureDef rightFixtureDef = new FixtureDef();
        rightFixtureDef.shape = rightShape;
        rightFixtureDef.filter.categoryBits = Constants.ROAD_SIDE_LEFT;
        rightFixtureDef.filter.maskBits = Constants.WORLD_ENTITY;
        rightFixtureDef.density = 0;
        rightSideBody.createFixture(rightFixtureDef);
    }

    public void initialRoad() {
        if (!sideObjectLeftTypeArrayList.isEmpty()) {
            SideObjectType sideObjectType = sideObjectLeftTypeArrayList.get(RandomUtil.getRand(0, sideObjectLeftTypeArrayList.size() - 1));
            SideObject sideObject = new SideObject(sideObjectType, true, leftTopPos + sideObjectType.getDistance());

            while (leftTopPos < 1200) {

                sideObjectLeftArrayList.add(sideObject);
                leftTopPos += sideObject.getHeight();
                SideObjectType sideObjectType1 = sideObjectLeftTypeArrayList.get(RandomUtil.getRand(0, sideObjectLeftTypeArrayList.size() - 1));
                leftTopPos += sideObjectType1.getDistance() + RandomUtil.getRand(0, sideObjectType1.getDeltaY());
                sideObject = new SideObject(sideObjectType1, true, leftTopPos);

            }
        }
        if (!sideObjectRightTypeArrayList.isEmpty()) {
            SideObjectType sideObjectType = sideObjectRightTypeArrayList.get(RandomUtil.getRand(0, sideObjectRightTypeArrayList.size() - 1));
            SideObject sideObject = new SideObject(sideObjectType, true, rightTopPos + sideObjectType.getDistance());

            while (rightTopPos < 1200) {

                sideObjectRightArrayList.add(sideObject);
                rightTopPos += sideObject.getHeight();
                SideObjectType sideObjectType1 = sideObjectRightTypeArrayList.get(RandomUtil.getRand(0, sideObjectRightTypeArrayList.size() - 1));
                rightTopPos += sideObjectType1.getDistance() + RandomUtil.getRand(0, sideObjectType1.getDeltaY());
                sideObject = new SideObject(sideObjectType1, true, rightTopPos);

            }
        }
    }

    public void initialRoadForGarage(Rectangle bounsGarage) {
        leftTopPos=30;
        rightTopPos=30;
        sideObjectRightArrayList.clear();
        sideObjectLeftArrayList.clear();
        if (!sideObjectLeftTypeArrayList.isEmpty()) {
            SideObjectType sideObjectType = sideObjectLeftTypeArrayList.get(RandomUtil.getRand(0, sideObjectLeftTypeArrayList.size() - 1));
            SideObject sideObject = new SideObject(sideObjectType, true, leftTopPos + sideObjectType.getDistance());


            //leftTopPos+=sideObject.getHeight();

            while (leftTopPos < 1200) {
                if(Intersector.overlaps(sideObject.getShape(),bounsGarage))
                {
                    sideObject.setPosition(new Vector3(bounsGarage.x-sideObject.getWidth(),sideObject.getPosition().y,0));
                }
                sideObjectLeftArrayList.add(sideObject);
                leftTopPos += sideObject.getHeight();
                SideObjectType sideObjectType1 = sideObjectLeftTypeArrayList.get(RandomUtil.getRand(0, sideObjectLeftTypeArrayList.size() - 1));
                leftTopPos += sideObjectType1.getDistance() + RandomUtil.getRand(0, sideObjectType1.getDeltaY());
                sideObject = new SideObject(sideObjectType1, true, leftTopPos);

            }
        }
        if (!sideObjectRightTypeArrayList.isEmpty()) {
            SideObjectType sideObjectType = sideObjectRightTypeArrayList.get(RandomUtil.getRand(0, sideObjectRightTypeArrayList.size() - 1));
            SideObject sideObject = new SideObject(sideObjectType, true, rightTopPos + sideObjectType.getDistance());

            //rightTopPos+=sideObject.getHeight();

            while (rightTopPos < 1200) {
                if(Intersector.overlaps(sideObject.getShape(),bounsGarage))
                {
                    sideObject.setPosition(new Vector3(bounsGarage.x+bounsGarage.getWidth(),sideObject.getPosition().y,0));
                }
                sideObjectRightArrayList.add(sideObject);
                rightTopPos += sideObject.getHeight();
                SideObjectType sideObjectType1 = sideObjectRightTypeArrayList.get(RandomUtil.getRand(0, sideObjectRightTypeArrayList.size() - 1));
                rightTopPos += sideObjectType1.getDistance() + RandomUtil.getRand(0, sideObjectType1.getDeltaY());
                sideObject = new SideObject(sideObjectType1, true, rightTopPos);

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
//        sb.draw(roadTexture, posRoad1.x, posRoad1.y);
//        sb.draw(roadTexture, posRoad2.x, posRoad2.y);
        sb.draw(back_tile, 0, 0, GameRuners.WIDTH, GameRuners.HEIGHT);
        for (int i = 0; i < size; i++) {

            roads.get(i).draw(sb);
            //sb.draw(roadTexture, positionsRoads.get(i).x, positionsRoads.get(i).y);
        }
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
        if (trafficLight.getTexture() != null)
            sb.draw(trafficLight.getTexture(), trafficLighterPosition.x, trafficLighterPosition.y);

    }

    public void drawSideObject(SpriteBatch sb) {



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
//        if (trafficLight.getTexture() != null)
//            sb.draw(trafficLight.getTexture(), trafficLighterPosition.x, trafficLighterPosition.y);

    }

    public void update(Camera camera, float dt) {


//


        for (int i = 0; i < size; i++) {
            roads.get(i).update(dt);
        }


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

                        System.out.println("sideObject1.getShape()"+sideObject1.getShape());

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

                }


            }
            SideObjectType sideObjectType1 = sideObjectRightTypeArrayList.get(RandomUtil.getRand(0, sideObjectRightTypeArrayList.size() - 1));
            SideObject sideObject1 =new SideObject(sideObjectType1, false, rightTopPos + sideObjectType1.getDistance());

            //sideObjectRightArrayList.add(new SideObject(sideObjectRightTypeArrayList.get(RandomUtil.getRand(0, sideObjectRightTypeArrayList.size() - 1)), false, sideObjectRightArrayList.get(sideObjectRightArrayList.size() - 1).getPosition().y + sideObjectRightArrayList.get(sideObjectRightArrayList.size() - 1).getHeight()));
            sideObjectRightArrayList.add(sideObject1);
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


        for(RoadTypeEnum road:roads)
        {
            if(road.isCrossroad()) {
                for(SideObject sideObject:sideObjectRightArrayList) {
                    if (Intersector.overlaps(sideObject.getShape(), road.getBounds())) {
                        sideObject.setPosition(new Vector3(1300, sideObject.getPosition().y, 0));
                    }
                }
                for (SideObject sideObject : sideObjectLeftArrayList) {
                    if (Intersector.overlaps(sideObject.getShape(), road.getBounds())) {
                        sideObject.setPosition(new Vector3(-1300, sideObject.getPosition().y, 0));
                    }
                }
            }
        }
//
//        for(SideObject sideObject:sideObjectRightArrayList)
//        {
//            if(Intersector.overlaps(sideObject.getShape(), roadTypeEnum.getBounds()))
//            {
//                sideObject.setPosition(new Vector3(300,0,0));
//            }
//        }
//        for(SideObject sideObject:sideObjectLeftArrayList)
//        {
//            if(Intersector.overlaps(sideObject.getShape(), roadTypeEnum.getBounds()))
//            {
//                sideObject.setPosition(new Vector3(-300,0,0));
//            }
//        }

    }

    public void updateTrafficLighter(float dt) {
        trafficLight.update(dt);
    }

//    public Texture getRoadTexture() {
//
//        return roadTexture;
//    }
//
//    public void setRoadTexture(Texture roadTexture) {
//        this.roadTexture = roadTexture;
//    }


    public static int getPosX() {
        return posX;
    }

    public static void setPosX(int posX) {
        NewRoad.posX = posX;
    }


}
