package com.nicholaschirkevich.game.model;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.nicholaschirkevich.game.userdata.MyCarDataType;
import com.nicholaschirkevich.game.util.Constants;

/**
 * Created by Nikolas on 12.02.2016.
 */
public class MyCar extends com.nicholaschirkevich.game.model.side_objects.Car {
    public Body body;
    World world;
    private boolean isPlayAnimation = true;
    private boolean isTurnRun = false;
    private float angelt = 0;

    public void setAlfa(float alfa) {
        this.alfa = alfa;
    }

    private float alfa = 1;
    private boolean isLowAlfa = false;
    public boolean isBlow = false;
    MoveToAction moveToLeftAction;
    MoveToAction moveToRightAction;
    MoveToAction moveToRightActionTwoPart;
    MoveToAction moveToStartLineAction;
    MoveToAction moveToLeftActionTwoPart;
    SequenceAction sequenceAction;


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
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(position.x, position.y);
        setUpMoveToRightAction();
        setUpMoveToLeftAction();
        setUpMoveToStartLineAction();
        body = world.createBody(bodyDef);
        body.setLinearDamping(Constants.MY_CAR_LINEAR_DUMPING);
        body.setAngularDamping(Constants.MY_CAR_ANGULAR_DUMPING);
        PolygonShape shape = new PolygonShape();


        shape.setAsBox((carAnimation.getKeyFrames()[0].getRegionWidth() - 20) / 2 / Constants.PIXELS_TO_METERS, carAnimation.getKeyFrames()[0].getRegionHeight()
                / 2 / Constants.PIXELS_TO_METERS);


        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.0001f;
        fixtureDef.restitution = 0f;
        MyCarDataType myCarDataType = new MyCarDataType();
        myCarDataType.setBounds(bounds);
        body.setUserData(myCarDataType);
        bodyDef.bullet = true;

        fixtureDef.filter.categoryBits = Constants.MY_CAR_FILTER_ENTITY;
        fixtureDef.filter.maskBits = Constants.PASSER_CAR_FILTER_ENTITY;
        body.createFixture(fixtureDef);
        stateTime = 0f;
    }

    public MyCar(World world, int y, int movement, boolean ifLeft, String key) {
        super(y, movement, key);
        setBounds(position.x, y, carAnimation.getKeyFrames()[0].getRegionWidth() / Constants.PIXELS_TO_METERS, carAnimation.getKeyFrames()[0].getRegionHeight() / Constants.PIXELS_TO_METERS);
        this.world = world;
        isLeft = true;
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(position.x, position.y);

        setUpMoveToRightAction();
        setUpMoveToLeftAction();
        setUpMoveToStartLineAction();
        body = world.createBody(bodyDef);
        body.setLinearDamping(Constants.MY_CAR_LINEAR_DUMPING);
        body.setAngularDamping(Constants.MY_CAR_ANGULAR_DUMPING);
        PolygonShape shape = new PolygonShape();


        shape.setAsBox((carAnimation.getKeyFrames()[0].getRegionWidth() - 20) / 2 / Constants.PIXELS_TO_METERS, carAnimation.getKeyFrames()[0].getRegionHeight()
                / 2 / Constants.PIXELS_TO_METERS);


        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.001f;
        fixtureDef.restitution = 0f;


        fixtureDef.filter.categoryBits = Constants.MY_CAR_FILTER_ENTITY;
        fixtureDef.filter.maskBits = Constants.PASSER_CAR_FILTER_ENTITY;
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

//        if(getActions().size==1)
//            isTurnRun=false;
        if (getX() < Constants.getCarPostitionXLeft(carAnimation.getKeyFrames()[0].getRegionWidth() + 30) + 10 || getX() > Constants.getCarPostitionXRight(carAnimation.getKeyFrames()[0].getRegionWidth() - 30) - 10)
            isTurnRun = false;
//        if (isTurnRun() == true) System.out.println("IsTurn " + isTurnRun());

//        if (getX() < 270) setIsLeft(true);
//        else setIsLeft(false);
        bounds.setPosition(getX(), getY());
        body.setTransform(getX(), getY(), getRotation());

        if (myCarDataType.isBlow()) {
            angelt += 5;
            setRotation(angelt);
            removeAction(sequenceAction);


        }
        if (isPlayAnimation)
            stateTime += dt ;


    }


    public void updateAnimations(boolean isPause) {
        if (isPause && getActions().size == 0) isPlayAnimation = false;
        else isPlayAnimation = true;

    }

    public boolean isLeft() {
        return isLeft;
    }


    public void setUpMoveToStartLineAction() {
        moveToStartLineAction = new MoveToAction();
        moveToStartLineAction.setPosition(Constants.getCarPostitionXLeft(carAnimation.getKeyFrames()[0].getRegionWidth()), 250);
        moveToStartLineAction.setDuration(1.1f);
    }

    public void moveToStartLine() {

        addAction(moveToStartLineAction);
    }

    public void setLeft() {
        setX(Constants.getCarPostitionXLeft(carAnimation.getKeyFrames()[0].getRegionWidth()));
    }

    public void setRight() {
        setX(Constants.getCarPostitionXRight(carAnimation.getKeyFrames()[0].getRegionWidth()));
    }

    public void setUpMoveToRightAction() {
        sequenceAction = new SequenceAction();
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
        if (!isTurnRun()) {
            isTurnRun = true;
            if (isLeft) {
                setUpMoveToRightAction();
                addAction(sequenceAction);
                isLeft = false;
            } else {
                setUpMoveToLeftAction();
                addAction(sequenceAction);
                 isLeft = true;
            }

            bounds.setPosition(getX(), getY());
            body.setTransform(getX(), getY(), getRotation());
        }
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

    public float getAlfa() {
        return alfa;
    }

    public boolean isLowAlfa() {
        return isLowAlfa;
    }

    public void setIsLowAlfa(boolean isLowAlfa) {
        this.isLowAlfa = isLowAlfa;
    }
}
