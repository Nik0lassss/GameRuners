package com.nicholaschirkevich.game.model;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.nicholaschirkevich.game.GameRuners;
import com.nicholaschirkevich.game.actions.RelaxZone;
import com.nicholaschirkevich.game.enums.CollisionPasserCarType;
import com.nicholaschirkevich.game.interfaces.GenerateHoleAfterLadle;
import com.nicholaschirkevich.game.states.GameState;
import com.nicholaschirkevich.game.userdata.LadleOnRoadDataType;
import com.nicholaschirkevich.game.userdata.PasserCarDataType;
import com.nicholaschirkevich.game.util.Constants;
import com.nicholaschirkevich.game.util.GameManager;
import com.nicholaschirkevich.game.util.RandomUtil;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Nikolas on 13.02.2016.
 */
public class PasserCar extends Car {
    private Random rand;
    private static World world;
    //private Animation passerCarAnimation;
    private float angelt = 0;
    private float angeltCrashLadle = 0;
    //public Sprite sprite;
    public static final short PASSER_CAR_FILTER_ENTITY = 0x1 << 1; // 0010 or 0x2 in hex
    public final short PHYSICS_ENTITY = 0x1;    // 0001
    public final short WORLD_ENTITY = 0x1 << 1; // 0010 or 0x2 in hex
    private int defaultX;
    private int defaultY;
    public Body body;
    private float sideCollisionTime = 0;
    private static boolean isBlocks = false;
    private static int bloksCount = 0;
    private static float bocksTime = 6;
    private static boolean bloksIsLeftStart = false;
    private static float timeBloks = 0;
    private static boolean isFromGarage = false;
    private static float timer = 0;
    private GenerateHoleAfterLadle generateHoleAfterLadleInterface;
    private float stateTime;

    public PasserCar(World world, int x, int y, int movement, String textureSrc, GenerateHoleAfterLadle generateHoleAfterLadle) {
        super(x, y, movement, textureSrc);
        defaultX = x;
        defaultY = y;
        rand = new Random();
        generateHoleAfterLadleInterface = generateHoleAfterLadle;
        isLeft = rand.nextBoolean();
        if (isLeft)
            position.x = Constants.getCarPostitionXLeft(carAnimation.getKeyFrames()[0].getRegionWidth());
        else
            position.x = Constants.getCarPostitionXRight(carAnimation.getKeyFrames()[0].getRegionWidth());

        this.world = world;
        //sprite = new Sprite(carTexture);
        //sprite.setPosition(x, y);
        //passerCarAnimation = AssetsManager.getAnimation(animation_asset_id);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set((carAnimation.getKeyFrames()[0].getRegionX() + carAnimation.getKeyFrames()[0].getRegionWidth() / 2) / Constants.PIXELS_TO_METERS
                ,
                ((carAnimation.getKeyFrames()[0].getRegionY() + carAnimation.getKeyFrames()[0].getRegionHeight() / 2) - 500) / Constants.PIXELS_TO_METERS);

        body = world.createBody(bodyDef);
        body.setLinearDamping(Constants.PASSER_CAR_LINEAR_DUMPING);
        body.setAngularDamping(Constants.PASSER_CAR_ANGULAR_DUMPING);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(carAnimation.getKeyFrames()[0].getRegionWidth() / 2 / Constants.PIXELS_TO_METERS, carAnimation.getKeyFrames()[0].getRegionHeight()
                / 2 / Constants.PIXELS_TO_METERS);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.0001f;
        fixtureDef.restitution = 0f;
        //fixtureDef.friction=0.8f;


        fixtureDef.filter.categoryBits = WORLD_ENTITY;
        fixtureDef.filter.maskBits = MyCar.MY_CAR_FILTER_ENTITY;
//        fixtureDef.filter.maskBits = PHYSICS_ENTITY;
        PasserCarDataType passerCarDataType = new PasserCarDataType();
        passerCarDataType.setBounds(bounds);
        body.setUserData(passerCarDataType);
        body.createFixture(fixtureDef);
        stateTime = 0f;

    }


    public PasserCar(World world, int x, int y, int movement, boolean ifLeft, String textureSrc, GenerateHoleAfterLadle generateHoleAfterLadle) {
        super(x, y, movement, textureSrc);
        defaultX = x;
        defaultY = y;

        generateHoleAfterLadleInterface = generateHoleAfterLadle;
        isLeft = ifLeft;
        if (isLeft)
            position.x = Constants.getCarPostitionXLeft(carAnimation.getKeyFrames()[0].getRegionWidth());
        else
            position.x = Constants.getCarPostitionXRight(carAnimation.getKeyFrames()[0].getRegionWidth());

        this.world = world;
        //sprite = new Sprite(carTexture);
        //sprite.setPosition(x, y);
        //passerCarAnimation = AssetsManager.getAnimation(animation_asset_id);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set((carAnimation.getKeyFrames()[0].getRegionX() + carAnimation.getKeyFrames()[0].getRegionWidth() / 2) / Constants.PIXELS_TO_METERS
                ,
                (carAnimation.getKeyFrames()[0].getRegionY() + carAnimation.getKeyFrames()[0].getRegionHeight() / 2) / Constants.PIXELS_TO_METERS);

        body = world.createBody(bodyDef);
        body.setLinearDamping(Constants.PASSER_CAR_LINEAR_DUMPING);
        body.setAngularDamping(Constants.PASSER_CAR_ANGULAR_DUMPING);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(carAnimation.getKeyFrames()[0].getRegionWidth() / 2 / Constants.PIXELS_TO_METERS, carAnimation.getKeyFrames()[0].getRegionHeight()
                / 2 / Constants.PIXELS_TO_METERS);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.0001f;
        fixtureDef.restitution = 0f;
        //fixtureDef.friction=0.8f;

//        setHeight(carAnimation.getKeyFrames()[0].getRegionHeight());
//        setWidth(carAnimation.getKeyFrames()[0].getRegionWidth());
        fixtureDef.filter.categoryBits = WORLD_ENTITY;
        fixtureDef.filter.maskBits = MyCar.MY_CAR_FILTER_ENTITY;
//        fixtureDef.filter.maskBits = MyCar.MY_CAR_FILTER_ENTITY | LadleOnCar.LADLE_MASK;
        //fixtureDef.filter.maskBits = PHYSICS_ENTITY | LadleOnCar.LADLE_MASK;
        PasserCarDataType passerCarDataType = new PasserCarDataType();
        passerCarDataType.setBounds(bounds);
        body.setUserData(passerCarDataType);
        body.createFixture(fixtureDef);
        stateTime = 0f;

    }

    public static boolean isBlocks() {
        return isBlocks;
    }

    public boolean getIsLeft() {
        return isLeft;
    }

    @Override
    public void update(float dt) {

        PasserCarDataType passerCarDataType = (PasserCarDataType) body.getUserData();

        if (passerCarDataType.getCollisionPasserCarType().equals(passerCarDataType.getCollisionPasserCarType().SIDE_COLLISION)) {
            sideCollisionTime += dt;
            if (!isLeft && position.x > 240) {
                passerCarDataType.setIsAfterHoleCollision(true);
            }
            if (isLeft && position.x < 10) {
                passerCarDataType.setIsAfterHoleCollision(true);
            }
//            if (sideCollisionTime > 0.1) {
//
//
//                passerCarDataType.setIsAfterHoleCollision(true);
//                sideCollisionTime = 0;
//            }
            if (isLeft) {
                //angeltCrashLadle += 2.5;
                position.add(-(880) * dt, -10 * dt, 0);
            } else {
                //angeltCrashLadle -= 2.5;
                position.add((880) * dt, -10 * dt, 0);
            }


            //sprite.setPosition(position.x, position.y);
            bounds.setPosition(position.x, position.y);
            body.setTransform(position.x, position.y, angeltCrashLadle);
            System.out.println(sideCollisionTime);
        }


        if (passerCarDataType.isGodMode()) {
            Rectangle intersaction = new Rectangle();
//            if (passerCarDataType != null && !passerCarDataType.isContact()) {
//                Intersector.intersectRectangles(passerCarDataType.getBounds(), passerCarDataType.getMyCarBounds(), intersaction);
//                if (intersaction.x > passerCarDataType.getMyCarBounds().x) {
//                    System.out.println("Intersects with right side");
//                    passerCarDataType.setCollisionPasserCarType(CollisionPasserCarType.SIDE_COLLISION);
//                    passerCarDataType.setIsContact(true);
//                    System.out.println("Sude collision");
////                if (isLeft) {
////                    //angeltCrashLadle += 2.5;
////                    position.add(-(GameManager.getCurrentSpeed() - 200) * dt, -10 * dt, 0);
////                } else {
////                    //angeltCrashLadle -= 2.5;
////                    position.add((GameManager.getCurrentSpeed() - 200) * dt, -10 * dt, 0);
////                }
//
//
//                    //sprite.setPosition(position.x, position.y);
//                    bounds.setPosition(position.x, position.y);
//                    body.setTransform(position.x, position.y, angeltCrashLadle);
//                } else if (intersaction.x + intersaction.width < passerCarDataType.getMyCarBounds().x + passerCarDataType.getMyCarBounds().width && !passerCarDataType.isContact()) {
//                    System.out.println("Intersects with left side    ");
//                    passerCarDataType.setCollisionPasserCarType(CollisionPasserCarType.SIDE_COLLISION);
//                    passerCarDataType.setIsContact(true);
//                    System.out.println("Sude collision");
////                if (isLeft) {
////                    //angeltCrashLadle += 2.5;
////                    position.add(-(GameManager.getCurrentSpeed() - 200) * dt, -10 * dt, 0);
////                } else {
////                    //angeltCrashLadle -= 2.5;
////                    position.add((GameManager.getCurrentSpeed() - 200) * dt, -10 * dt, 0);
////                }
//
//
//                    //sprite.setPosition(position.x, position.y);
//                    bounds.setPosition(position.x, position.y);
//                    body.setTransform(position.x, position.y, angeltCrashLadle);
//                } else
//                    //Intersects with right side
//                    if (intersaction.y > passerCarDataType.getMyCarBounds().y && !passerCarDataType.isContact()) {
//                        System.out.println("Intersects with top side   ");
//                        passerCarDataType.setCollisionPasserCarType(CollisionPasserCarType.COUNTER_COLLISION);
//                        position.set(position.x, position.y, 0);
//                        System.out.println("Counter collision");
//                        passerCarDataType.setIsContact(true);
//                        bounds.setPosition(position.x, position.y);
//                        body.setTransform(position.x, position.y, 0.0f);
//                        setPosition(position.x, position.y);
//                    } else
//                        //Intersects with top side
//
//                        //Intersects with left side
//                        if (intersaction.y + intersaction.height < passerCarDataType.getMyCarBounds().y + passerCarDataType.getMyCarBounds().height) {
//                            System.out.println("Intersects with with bottom side     ");
//                        }
//            }
            //( (passerCarDataType.getX()>85 || passerCarDataType.getX()<65) || (passerCarDataType.getX()>185 || passerCarDataType.getX()<170))
//            if (((passerCarDataType.getX() < 85 && passerCarDataType.getX() > 115 && isLeft) || (passerCarDataType.getX() < 230 && passerCarDataType.getX() > 205 && !isLeft))) {
//                passerCarDataType.setCollisionPasserCarType(CollisionPasserCarType.SIDE_COLLISION);
//
//                System.out.println("Sude collision");
////
//                bounds.setPosition(position.x, position.y);
//                body.setTransform(position.x, position.y, angeltCrashLadle);
//

            if (!passerCarDataType.isBefore()) {
                passerCarDataType.setCollisionPasserCarType(CollisionPasserCarType.SIDE_COLLISION);
//
//                System.out.println("Sude collision");
////
//                bounds.setPosition(position.x, position.y);
//                body.setTransform(position.x, position.y, angeltCrashLadle);
            } else {
                passerCarDataType.setCollisionPasserCarType(CollisionPasserCarType.COUNTER_COLLISION);
                position.set(position.x, position.y, 0);
                System.out.println("Counter collision");

                bounds.setPosition(position.x, position.y);
                body.setTransform(position.x, position.y, 0.0f);
                setPosition(position.x, position.y);
            }
            //passerCarDataType.setIsGodMode(false);
            //stateTime += dt;
            //stateTime += dt;
        } else if (passerCarDataType.isCollisionThrons()) {
//            if (isLeft) {
//                position.set(-70*dt, position.y, 0);
//
//                //sprite.setPosition(position.x, position.y);
//                bounds.setPosition(position.x, position.y);
//                body.setTransform(position.x, position.y, 0.0f);
//                setPosition(position.x, position.y);
//                stateTime += dt;
//            }
//            else {
//                position.set(70*dt, position.y, 0);
//
//                //sprite.setPosition(position.x, position.y);
//                bounds.setPosition(position.x, position.y);
//                body.setTransform(position.x, position.y, 0.0f);
//                setPosition(position.x, position.y);
//                stateTime += dt;
//            }
            System.out.println("passerCarDataType.isCollisionThrons()");
//            body.setTransform(0, 0, 20f);
//            position.add(-100 * dt, 0, 0);
//            //body.applyForce(1000, 1000, 100, 100, true);
//            body.setLinearVelocity(100, 100);

        } else if (!passerCarDataType.isBlow() && !passerCarDataType.isLadleCollision()) {
            position.add(0, (-GameManager.getCurrentSpeed() + 56) * dt, 0);

            //sprite.setPosition(position.x, position.y);
            bounds.setPosition(position.x, position.y);
            body.setTransform(position.x, position.y, 0.0f);
            setPosition(position.x, position.y);
            stateTime += dt;
        } else if (passerCarDataType.isBlow()) {

//            Vector2 fv1 = new Vector2(-GameManager.getContactPointX(), -GameManager.getContactPointY());
//
//            if (isLeft) angelt += 2;
//            else angelt -= 4;
//            world.setGravity(new Vector2(0f, -20f));
//            if (isLeft)
//                body.applyLinearImpulse(500000, 500000f, -GameManager.getContactPointX(), -GameManager.getContactPointY(), true);
//            else
//                body.applyLinearImpulse(-500000, 500000f, -GameManager.getContactPointX(), -GameManager.getContactPointY(), true);
//
//
//            body.setTransform(body.getPosition().x, body.getPosition().y, angelt);
//
//            bounds.setPosition(body.getPosition().x, body.getPosition().y);
//
//            setPosition(body.getPosition().x, body.getPosition().y);
//            stateTime += dt;
            timer += dt;
//
//            GameManager.setCurrentSpeed(GameManager.getGearShifts().get(0).getSpeeds().get(0));


            if (timer > 0.4) {
                GameManager.pauseGame = true;
                System.out.println("Pause");
                timer = 0;
            }

        } else if (passerCarDataType.isLadleCollision()) {


//            world.destroyBody(body);
//            remove();

//            position.set(position.x, position.y, 0);
//            //position.add(100 * dt, (-GameManager.getCurrentSpeed() + 56) * dt, 0);
//            if (isLeft) {
//                //angeltCrashLadle += 2.5;
//                position.add(-(GameManager.getCurrentSpeed() - 200) * dt, -10 * dt, 0);
//            } else {
//                //angeltCrashLadle -= 2.5;
//                position.add((GameManager.getCurrentSpeed() - 200) * dt, -10 * dt, 0);
//            }
//            //sprite.setPosition(position.x, position.y);
//            bounds.setPosition(position.x, position.y);
//            body.setTransform(position.x, position.y, angeltCrashLadle);
//            setPosition(position.x, position.y);
            stateTime += dt;
            //stateTime += dt;
//            if (isLeft)  body.applyLinearImpulse(-50,0, -GameManager.getContactPointX(), -GameManager.getContactPointY(), true);
//            else  body.applyLinearImpulse(50, 0, -GameManager.getContactPointX(), -GameManager.getContactPointY(), true);
//
//            //body.applyLinearImpulse(GameManager.getContactPointX(),GameManager.getContactPointY(),position.x,position.y,false);
//            body.setTransform(body.getPosition().x, body.getPosition().y, angeltCrashLadle);
//            //setRotation(angelt);
//            //body.setTransform(0,0,20f);
//            // position.add(-100*dt, 0, 0);
//            //body.applyForce(1000, 1000, 100, 100, true);
//            //body.setLinearVelocity(100,100);
//            //sprite.setPosition(position.x, position.y);
//            bounds.setPosition(body.getPosition().x, body.getPosition().y);
//            //body.setTransform(body.getPosition().x, body.getPosition().y, 0.0f);
//            setPosition(body.getPosition().x, body.getPosition().y);
        }

    }

    public void setDefaultPosition(int x) {
        position.set(0, x, 0);

        //sprite.setPosition(position.x, position.y);
        bounds.setPosition(position.x, position.y);
        body.setTransform(position.x, position.y, 0.0f);

    }

    public static Float getPosYLastCar(ArrayList<PasserCar> passerCars) {
        return passerCars.size() != 0 ? passerCars.get(passerCars.size() - 1).getPosition().y : 200;
    }

    public static boolean getIsLeftLastCar(ArrayList<PasserCar> passerCars) {
        return passerCars.size() != 0 ? passerCars.get(passerCars.size() - 1).getIsLeft() : false;
    }

    public static void updateCars(boolean isRelaxZone, ArrayList<PasserCar> passerCars, Camera camera, float dt, GenerateHoleAfterLadle generateHoleAfterLadle) {

        if (isBlocks == false)
            PasserCar.updatePasserCars(isRelaxZone, passerCars, camera, dt, generateHoleAfterLadle);
        else {
            PasserCar.generatePasserCarsBloks(isRelaxZone, passerCars, camera, dt, generateHoleAfterLadle);
        }
        PasserCar.generateBlocks(dt);

    }

    public void setPosition() {

        int randomY = rand.nextInt((70 - 0) + 1) + 0;
        isLeft = rand.nextBoolean();
        if (isLeft)
            position.x = Constants.CAR_POS_X_LEFT - carAnimation.getKeyFrames()[0].getRegionHeight() / 2;
        else
            position.x = Constants.CAR_POS_X_RIGHT - carAnimation.getKeyFrames()[0].getRegionHeight() / 2;
        position.add(0, defaultY + randomY + 600, 0);
        //sprite.setPosition(position.x, position.y);
        bounds.setPosition(position.x, position.y);
        body.setTransform(position.x, position.y, 0.0f);
    }

    @Override
    public void turn() {

    }

    public void dispose() {


    }

    public static void generateBlocksCount() {
        bloksCount = RandomUtil.getRandBocksCount();
        //System.out.println(bloksCount);
    }

    public static void generateBlocks(float dt) {

        timeBloks += dt;
        if (timeBloks > bocksTime) {
            isBlocks = true;
            timeBloks = 0;
            bloksIsLeftStart = RandomUtil.getRandomBoolean();
            generateBlocksCount();
            bocksTime = 12;
        }
    }

    public static void generatePasserCarsBloks(boolean isRelaxZone, ArrayList<PasserCar> passerCars, Camera camera, float dt, GenerateHoleAfterLadle generateHoleAfterLadle) {


        if (bloksCount > 0) {
            //System.out.println(bloksCount);
            if (passerCars.size() != 0 && camera.viewportHeight - passerCars.get(passerCars.size() - 1).getPosition().y > 100) {
//                boolean isBigCar = rand.nextBoolean();
//                if (!isBigCar)
                // passerCars.add(new PasserCar(world, 90, (int) camera.viewportHeight + 200, 10, true, Constants.OTHERCAR_1_1_ASSETS_ID));
                // else

                if (!isRelaxZone) {
                    passerCars.add(new PasserCar(world, 90, (int) camera.viewportHeight + 170, 10, bloksIsLeftStart, RandomUtil.getRandomOtherCarType().getKey(), generateHoleAfterLadle));
                    if (bloksIsLeftStart) bloksIsLeftStart = false;
                    else bloksIsLeftStart = true;
                    //System.out.println("Blocks");
                    bloksCount--;
                }

            }

        } else isBlocks = false;

        for (int i = 0; i < passerCars.size(); i++) {

            if (passerCars.get(i).getPosition().y < 0) {
                if (passerCars.get(i).body != null)
                    world.destroyBody(passerCars.get(i).body);
                passerCars.remove(i);

            }
            if (((PasserCarDataType) passerCars.get(i).body.getUserData()).isLadleCollision()) {
                passerCars.get(i).generateHoleAfterLadleInterface.generateHoleAfterLadle(passerCars.get(i).body.getPosition().x, passerCars.get(i).body.getPosition().y);
                world.destroyBody(passerCars.get(i).body);
                passerCars.get(i).remove();
                passerCars.remove(i);
                break;
            }
            if (((PasserCarDataType) passerCars.get(i).body.getUserData()).isCollisionWhithPasser()) {
                passerCars.get(i).generateHoleAfterLadleInterface.generateHoleAfterLadle(passerCars.get(i).body.getPosition().x, passerCars.get(i).body.getPosition().y);
                world.destroyBody(passerCars.get(i).body);
                passerCars.get(i).remove();
                passerCars.remove(i);
                break;

            }
            if ((((PasserCarDataType) passerCars.get(i).body.getUserData()).getCollisionPasserCarType().equals(CollisionPasserCarType.SIDE_COLLISION)) && ((PasserCarDataType) passerCars.get(i).body.getUserData()).isAfterHoleCollision()) {
                passerCars.get(i).generateHoleAfterLadleInterface.generateHoleAfterLadle(passerCars.get(i).body.getPosition().x, passerCars.get(i).body.getPosition().y);
                world.destroyBody(passerCars.get(i).body);
                passerCars.get(i).remove();
                passerCars.remove(i);
                break;
            }

            passerCars.get(i).update(dt);
        }

    }


    public static void updatePasserCars(boolean isRelaxZone, ArrayList<PasserCar> passerCars, Camera camera, float dt, GenerateHoleAfterLadle generateHoleAfterLadle) {

        if (passerCars.size() != 0 && camera.viewportHeight - passerCars.get(passerCars.size() - 1).getPosition().y > Constants.passerCarDistance) {
//            boolean isBigCar = rand.nextBoolean();
//            if (!isBigCar)
            if (!isRelaxZone)
                passerCars.add(new PasserCar(world, 90, (int) camera.viewportHeight + 200, 10, RandomUtil.getRandomOtherCarType().getKey(), generateHoleAfterLadle));
//            else
//                passerCars.add(new PasserCar(world, 90, (int) camera.viewportHeight + 200, 10, true, Constants.OTHERCAR_2_1_ASSETS_ID));
//
        }
        if (!isRelaxZone && passerCars.size() == 0)
            passerCars.add(new PasserCar(world, 90, (int) camera.viewportHeight + 200, 10, RandomUtil.getRandomOtherCarType().getKey(), generateHoleAfterLadle));

        for (int i = 0; i < passerCars.size(); i++) {

            if (((PasserCarDataType) passerCars.get(i).body.getUserData()).isLadleCollision()) {
                passerCars.get(i).generateHoleAfterLadleInterface.generateHoleAfterLadle(passerCars.get(i).body.getPosition().x, passerCars.get(i).body.getPosition().y);
                world.destroyBody(passerCars.get(i).body);
                passerCars.get(i).remove();
                passerCars.remove(i);
                break;
            }
            if (((PasserCarDataType) passerCars.get(i).body.getUserData()).isCollisionWhithPasser()) {
                passerCars.get(i).generateHoleAfterLadleInterface.generateHoleAfterLadle(passerCars.get(i).body.getPosition().x, passerCars.get(i).body.getPosition().y);
                world.destroyBody(passerCars.get(i).body);
                passerCars.get(i).remove();
                passerCars.remove(i);
                generateHoleAfterLadle.onCollisionWithPasserCar();
                break;
            }
            if ((((PasserCarDataType) passerCars.get(i).body.getUserData()).getCollisionPasserCarType().equals(CollisionPasserCarType.SIDE_COLLISION)) && ((PasserCarDataType) passerCars.get(i).body.getUserData()).isAfterHoleCollision()) {
                passerCars.get(i).generateHoleAfterLadleInterface.generateHoleAfterLadle(passerCars.get(i).body.getPosition().x, passerCars.get(i).body.getPosition().y);
                world.destroyBody(passerCars.get(i).body);
                passerCars.get(i).remove();
                passerCars.remove(i);
                generateHoleAfterLadle.addAchives();
                break;
            }

//            if(((PasserCarDataType) passerCars.get(i).body.getUserData()).isBlow())
//            {
//
//            }
            if (passerCars.get(i).getPosition().y < 0) {
                world.destroyBody(passerCars.get(i).body);
                passerCars.remove(i);
            }

            if (passerCars.size() != 0) passerCars.get(i).update(dt);
        }

    }


    public float getStateTime() {
        return stateTime;
    }

    public Animation getPasserCarAnimation() {
        return carAnimation;
    }
}
