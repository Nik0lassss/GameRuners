package com.nicholaschirkevich.game.model.side_objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.nicholaschirkevich.game.enums.BushType;
import com.nicholaschirkevich.game.util.AssetsManager;
import com.nicholaschirkevich.game.util.RandomUtil;

/**
 * Created by Nikolas on 12.02.2016.
 */
public abstract class BushsBasic {


    public static int speed = 100;
    public static int GRAVITY = -15;
    public static int turnSpeed = 500;
    protected int leftSide = -15;
    protected int rightSide = 287;
    protected Vector3 position;
    protected Vector3 velosity;
    protected Animation bushAnimation;
    protected Rectangle bounds;
    protected boolean isLeft;
    protected BushType bushType ;


    public BushsBasic(int x, int y, int movement,  String key) {
        bushType = RandomUtil.getRandomBushType();
        bushAnimation= AssetsManager.getAnimation(bushType.getKey());


        position = new Vector3(x, y, 0);
        velosity = new Vector3(0, 0, 0);
        this.speed = movement;

        bounds = new Rectangle(x, y, bushAnimation.getKeyFrames()[0].getRegionWidth(), bushAnimation.getKeyFrames()[0].getRegionHeight());
    }

    public abstract void update(float dt);

    public abstract void turn();

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getBushTexture() {
        return bushAnimation.getKeyFrames()[0];
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
