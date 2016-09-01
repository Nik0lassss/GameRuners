package com.nicholaschirkevich.game.model.side_objects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.nicholaschirkevich.game.states.GameState;
import com.nicholaschirkevich.game.util.AssetsManager;
import com.nicholaschirkevich.game.util.Constants;
import com.nicholaschirkevich.game.util.RandomUtil;

/**
 * Created by Nikolas on 12.02.2016.
 */
public abstract class Car extends Actor {


    public static final int INDEX = 0;
    public static final int X = 0;
    public static final int Y = 0;
    public static final int Z = 0;
    public static int speed = 100;
    public static int turnSpeed = 950;

    protected Vector3 position;
    protected Vector3 velosity;

    protected Animation carAnimation;
    protected Rectangle bounds;
    protected boolean isLeft;


    public Car(int x, int y, int movement, String key) {

        carAnimation = AssetsManager.getAnimation(key);
        position = new Vector3(x, y, Z);
        velosity = new Vector3(X, Y, Z);
        this.speed = movement;

        bounds = new Rectangle(x, y, carAnimation.getKeyFrames()[INDEX].getRegionWidth(), carAnimation.getKeyFrames()[INDEX].getRegionHeight());
    }

    public Car( int y, int movement,  String key) {

        carAnimation = AssetsManager.getAnimation(key);
        position = new Vector3(Constants.getCarPostitionXLeft(carAnimation.getKeyFrames()[INDEX].getRegionWidth()), y, Z);
        velosity = new Vector3(X, Y, Z);
        this.speed = movement;

        bounds = new Rectangle(Constants.getCarPostitionXLeft(carAnimation.getKeyFrames()[INDEX].getRegionWidth()), y, carAnimation.getKeyFrames()[INDEX].getRegionWidth(), carAnimation.getKeyFrames()[INDEX].getRegionHeight());

    }

    public void setIsLeft(boolean isLeft)
        {
            this.isLeft=isLeft;
        }



    public abstract void update(float dt);

    public abstract void turn();
    public abstract void turn(boolean left);

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getCarTexture() {
        return carAnimation.getKeyFrames()[INDEX];
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
