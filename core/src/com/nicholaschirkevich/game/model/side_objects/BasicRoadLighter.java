package com.nicholaschirkevich.game.model.side_objects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.nicholaschirkevich.game.util.AssetsManager;

/**
 * Created by Nikolas on 12.02.2016.
 */
public abstract class BasicRoadLighter {


    public static int speed = 100, leftSide = -45, rightSide = 220;
    public static int GRAVITY = -15;
    public static int turnSpeed = 500;
    protected Vector3 position;
    protected Vector3 velosity;
    protected Animation roadLighterAnimation;
    protected Rectangle bounds;
    protected boolean isLeft;


    public BasicRoadLighter(int x, int y, int movement, String key) {

//        }
        roadLighterAnimation = AssetsManager.getAnimation(key);
        position = new Vector3(x, y, 0);
        velosity = new Vector3(0, 0, 0);
        this.speed = movement;

        bounds = new Rectangle(x, y, roadLighterAnimation.getKeyFrames()[0].getRegionWidth(), roadLighterAnimation.getKeyFrames()[0].getRegionHeight());
    }

    public abstract void update(float dt);

    public abstract void turn();

    public Vector3 getPosition() {
        return position;
    }


    public Rectangle getBounds() {
        return bounds;
    }

    public TextureRegion getRoadLighterTexture() {
        return roadLighterAnimation.getKeyFrames()[0];
    }
}
