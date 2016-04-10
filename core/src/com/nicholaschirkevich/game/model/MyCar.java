package com.nicholaschirkevich.game.model;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.nicholaschirkevich.game.states.GameState;
import com.nicholaschirkevich.game.userdata.LadleOnRoadDataType;
import com.nicholaschirkevich.game.userdata.MyCarDataType;
import com.nicholaschirkevich.game.util.AssetsManager;
import com.nicholaschirkevich.game.util.Constants;
import com.nicholaschirkevich.game.util.GameManager;

/**
 * Created by Nikolas on 12.02.2016.
 */
public class MyCar extends Car {
    public Body body;
    World world;
    //private Animation coinAnimation;
    private boolean isPlayAnimation = true;
    private boolean isTurnRun = false;
    //public Sprite sprite;
    private float angelt = 0;
    public boolean isBlow = false;
    MoveToAction moveToLeftAction;
    MoveToAction moveToRightAction;
    MoveToAction moveToRightActionTwoPart;
    MoveToAction moveToStartLineAction;
    MoveToAction moveToLeftActionTwoPart;
    SequenceAction sequenceAction;
    public static final short MY_CAR_FILTER_ENTITY = 0x8;    // 0001


    private float stateTime;

    public MyCar(int x, int y, int movement, boolean ifLeft, String key) {
        super(x, y, movement, key);
        isLeft = true;

    }


    public MyCar(World world, int x, int y, int movement, boolean ifLeft, String key) {
        super(y, movement, key);
        setBounds(x, y, carAnimation.getKeyFrames()[0].getRegionWidth(), carAnimation.getKeyFrames()[0].getRegionHeight());
        this.world = world;
        isLeft = true;
        //coinAnimation = AssetsManager.getAnimation(Constants.MY_CAR_ASSETS_ID);
        //sprite = new Sprite(coinAnimation);
        //sprite.setPosition(x, y);
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(position.x, position.y);
        setUpMoveToRightAction();
        setUpMoveToLeftAction();
        setUpMoveToStartLineAction();
        body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();


        shape.setAsBox((carAnimation.getKeyFrames()[0].getRegionWidth() -20)/ 2 / Constants.PIXELS_TO_METERS, carAnimation.getKeyFrames()[0].getRegionHeight()
                / 2 / Constants.PIXELS_TO_METERS);


        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.1f;
        fixtureDef.restitution = 1f;
        MyCarDataType myCarDataType = new MyCarDataType();
        myCarDataType.setBounds(bounds);
        body.setUserData(myCarDataType);
        bodyDef.bullet = true;

        fixtureDef.filter.categoryBits = MyCar.MY_CAR_FILTER_ENTITY;
        //fixtureDef.filter.maskBits = MyCar.MY_CAR_FILTER_ENTITY;
        fixtureDef.filter.maskBits = PasserCar.PASSER_CAR_FILTER_ENTITY;
        body.createFixture(fixtureDef);
        stateTime = 0f;
    }

    public MyCar(World world, int y, int movement, boolean ifLeft, String key) {
        super(y, movement, key);
        setBounds(position.x, y, carAnimation.getKeyFrames()[0].getRegionWidth() / Constants.PIXELS_TO_METERS, carAnimation.getKeyFrames()[0].getRegionHeight() / Constants.PIXELS_TO_METERS);
        this.world = world;
        isLeft = true;
        //coinAnimation = AssetsManager.getAnimation(Constants.MY_CAR_ASSETS_ID);
        //sprite = new Sprite(coinAnimation);
        //sprite.setPosition(x, y);
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(position.x, position.y);

        setUpMoveToRightAction();
        setUpMoveToLeftAction();
        setUpMoveToStartLineAction();
        body = world.createBody(bodyDef);
        // body.setUserData(new MyCarDataType());
        PolygonShape shape = new PolygonShape();


        shape.setAsBox((carAnimation.getKeyFrames()[0].getRegionWidth()-20) / 2 / Constants.PIXELS_TO_METERS, carAnimation.getKeyFrames()[0].getRegionHeight()
                / 2 / Constants.PIXELS_TO_METERS);


        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.9f;
        fixtureDef.restitution = 1f;


        fixtureDef.filter.categoryBits = MyCar.MY_CAR_FILTER_ENTITY;
        fixtureDef.filter.maskBits = PasserCar.PASSER_CAR_FILTER_ENTITY;
//        fixtureDef.filter.maskBits = PasserCar.PASSER_CAR_FILTER_ENTITY|LadleOnCar.LADLE_MASK;
        MyCarDataType myCarDataType = new MyCarDataType();
        myCarDataType.setBounds(bounds);
        body.setUserData(myCarDataType);
        body.createFixture(fixtureDef);
        stateTime = 0f;
    }


    @Override
    public void update(float dt) {
        MyCarDataType myCarDataType = (MyCarDataType) body.getUserData();

        if (getActions().size == 1) isTurnRun = false;
        //sprite.setPosition(getX(), getY());

//        if (isLeft && position.x > leftSide) {
//            if(myCarDataType.isBlow()) {
//                MoveToAction moveToAction = new MoveToAction();
//
//                //sprite.setPosition(position.x, position.y);
//            }
        bounds.setPosition(getX(), getY());
        body.setTransform(getX(), getY(), getRotation());
////            velosity.scl(1 / dt);
//        } else if (!isLeft && position.x < rightSide) {
//            if(!isBlow()) {
//                velosity.set(turnSpeed, 0, 0);
//                velosity.scl(dt);
//                position.add(velosity.x, 0, 0);
//                sprite.setPosition(position.x, position.y);
//            }
////            velosity.scl(1 / dt);
//        }
        if (myCarDataType.isBlow()) {
            angelt += 5;
            setRotation(angelt);
            removeAction(sequenceAction);


        }
        if (isPlayAnimation)
            stateTime += dt;

        //body.setTransform(sprite.getX(), sprite.getY(), 0.0f);


    }


    public void updateAnimations(boolean isPause) {
        if (isPause && getActions().size == 0) isPlayAnimation = false;
        else isPlayAnimation = true;

    }

    public boolean isLeft() {
        return isLeft;
    }

    public void moveToStartLine(float dt, float startLinePosition, float speedMoveToStartLine) {
        if (position.y < 280) {
            velosity.set(0, 300, 0);
            velosity.scl(dt);
            position.add(0, velosity.y, 0);
            //sprite.setPosition(position.x, position.y);
        }
    }

//    public void moveToStartLine(float dt) {
//        if (position.y < 280) {
//            velosity.set(0, 300, 0);
//            velosity.scl(dt);
//            position.add(0, velosity.y, 0);
//            sprite.setPosition(position.x, position.y);
//            //sprite.setRotation(100);
//        }
//    }


    public void setUpMoveToStartLineAction() {
        moveToStartLineAction = new MoveToAction();
        moveToStartLineAction.setPosition(Constants.getCarPostitionXLeft(carAnimation.getKeyFrames()[0].getRegionWidth()), 250);
        moveToStartLineAction.setDuration(1.1f);
    }

    public void moveToStartLine() {

        addAction(moveToStartLineAction);
    }

    public void setUpMoveToRightAction() {
        sequenceAction = new SequenceAction();
        //sequenceAction.addAction(Actions.delay(1.5f));
//        sequenceAction.addAction(Actions.removeActor());
        moveToRightAction = new MoveToAction();
        moveToRightActionTwoPart = new MoveToAction();
        moveToRightAction.setPosition(Constants.getCarPostitionXRight(carAnimation.getKeyFrames()[0].getRegionWidth() - 20), 250);
        moveToRightAction.setDuration(0.1f);
        moveToRightActionTwoPart.setPosition(Constants.getCarPostitionXRight(carAnimation.getKeyFrames()[0].getRegionWidth()), 250);
        moveToRightActionTwoPart.setDuration(0.1f);
        sequenceAction.addAction(moveToRightAction);
        sequenceAction.addAction(moveToRightActionTwoPart);


    }

    public void setUpMoveToLeftAction() {
        sequenceAction = new SequenceAction();
        moveToLeftAction = new MoveToAction();
        moveToLeftActionTwoPart = new MoveToAction();
        moveToLeftAction.setPosition(Constants.getCarPostitionXLeft(carAnimation.getKeyFrames()[0].getRegionWidth() + 20), 250);
        moveToLeftAction.setDuration(0.1f);
        moveToLeftActionTwoPart.setPosition(Constants.getCarPostitionXLeft(carAnimation.getKeyFrames()[0].getRegionWidth()), 250);
        moveToLeftActionTwoPart.setDuration(0.1f);
        sequenceAction.addAction(moveToLeftAction);
        sequenceAction.addAction(moveToLeftActionTwoPart);
    }


    @Override
    public void turn() {
        if (!isTurnRun) {
            isTurnRun = true;
            if (isLeft) {
                //sequenceAction.reset();
                setUpMoveToRightAction();
                addAction(sequenceAction);

                isLeft = false;
            } else {
                setUpMoveToLeftAction();
                //moveToLeftAction.reset();
                addAction(sequenceAction);
                isLeft = true;
            }
            //sprite.setPosition(getX(), getY());
            bounds.setPosition(getX(), getY());
            body.setTransform(getX(), getY(), getRotation());
        }
    }

    public void blow_sideways(float dt) {
        velosity.set(20, 180, 0);
        velosity.scl(dt);
        position.add(velosity.x, velosity.y, 0);

//        sprite.setPosition(position.x, position.y);

//        sprite.setRotation(sprite.getRotation() + 7);
        bounds.setPosition(position.x, position.y);
        body.setTransform(getX(), getY(), getRotation());
    }

    public boolean isBlow() {
        return isBlow;
    }

    public void setIsBlow(boolean isBlow) {
        this.isBlow = isBlow;
    }

    public Animation getMyCarAnimation() {
        return carAnimation;
    }

    public float getStateTime() {
        return stateTime;
    }

    public boolean isTurnRun() {
        return isTurnRun;
    }

    public void setIsTurnRun(boolean isTurnRun) {
        this.isTurnRun = isTurnRun;
    }
}
