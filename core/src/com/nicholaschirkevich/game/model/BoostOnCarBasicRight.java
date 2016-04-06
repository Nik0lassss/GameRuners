package com.nicholaschirkevich.game.model;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.nicholaschirkevich.game.util.AssetsManager;
import com.nicholaschirkevich.game.util.Constants;
import com.nicholaschirkevich.game.util.GameManager;
import com.nicholaschirkevich.game.util.RandomUtil;

/**
 * Created by Nikolas on 12.02.2016.
 */
public abstract class BoostOnCarBasicRight extends Actor {


    public static int speed = 100;
    public static int turnSpeed = 950;

    protected Vector3 position;
    protected Vector3 velosity;
    protected Vector3 acceleration;
    //protected TextureRegion carTexture;

    protected Animation boosOnCarRight;


    protected Rectangle bounds;
    protected boolean isLeft;


    public BoostOnCarBasicRight(int x, int y, int movement) {
        //carTexture = AssetsManager.getTextureRegion(key);
        boosOnCarRight = AssetsManager.getAnimation(Constants.BOOST_R_ASSETS_ID);
        x = x + GameManager.getCurrentCar().getRightRocketPosition().getX();
        y -=  boosOnCarRight.getKeyFrames()[0].getRegionHeight() / 2 - GameManager.getCurrentCar().getLeftRocketPosition().getY() - boosOnCarRight.getKeyFrames()[0].getRegionHeight();

        position = new Vector3(x, y, 0);
        velosity = new Vector3(0, 0, 0);
        this.speed = movement;

        bounds = new Rectangle(x, y, boosOnCarRight.getKeyFrames()[0].getRegionWidth(), boosOnCarRight.getKeyFrames()[0].getRegionHeight());
    }

    public BoostOnCarBasicRight(int y, int movement, String key) {
        //carTexture = AssetsManager.getTextureRegion(key);
        boosOnCarRight = AssetsManager.getAnimation(key);
        position = new Vector3(Constants.getCarPostitionXLeft(boosOnCarRight.getKeyFrames()[0].getRegionWidth()), y, 0);
        velosity = new Vector3(0, 0, 0);
        this.speed = movement;

        bounds = new Rectangle(Constants.getCarPostitionXLeft(boosOnCarRight.getKeyFrames()[0].getRegionWidth()), y, boosOnCarRight.getKeyFrames()[0].getRegionWidth(), boosOnCarRight.getKeyFrames()[0].getRegionHeight());
        //setBounds(Constants.getCarPostitionXLeft(carTexture.getRegionWidth()), y, carTexture.getRegionWidth(), carTexture.getRegionHeight());
    }

    public void setIsLeft(boolean isLeft) {
        this.isLeft = isLeft;
    }

    public static Vector2 getRandomInitialPosition() {
        if (RandomUtil.getRandomBoolean())
            return new Vector2(Constants.CAR_POS_X_LEFT, Constants.CAR_POS_Y);
        else return new Vector2(Constants.CAR_POS_X_RIGHT, Constants.CAR_POS_Y);
    }

    protected float getRandomXPosition() {
        if (RandomUtil.getRandomBoolean()) {
            isLeft = true;
            return Constants.CAR_POS_X_LEFT;
        } else {
            isLeft = false;
            return Constants.CAR_POS_X_RIGHT;
        }
    }

    public static boolean getRandomPosition() {
        return RandomUtil.getRandomBoolean();
    }

    public abstract void update(float dt, float x, float y);

    public abstract void turn();

    public Vector3 getPosition() {
        return position;
    }


    public Rectangle getBounds() {
        return bounds;
    }

    public TextureRegion getBoostOnCar() {
        return boosOnCarRight.getKeyFrames()[0];
    }
}
