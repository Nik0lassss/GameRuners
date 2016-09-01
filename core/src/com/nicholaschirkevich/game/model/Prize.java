package com.nicholaschirkevich.game.model;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.nicholaschirkevich.game.util.AssetsManager;
import com.nicholaschirkevich.game.util.Constants;
import com.nicholaschirkevich.game.util.RandomUtil;

/**
 * Created by Nikolas on 12.02.2016.
 */
public abstract class Prize extends Actor {


    public static final int INDEX = 0;
    public static int speed = 100;
    public static int turnSpeed = 950;

    protected Vector3 position;
    protected Vector3 velosity;
    protected Vector3 acceleration;
    //protected TextureRegion carTexture;
    protected Animation coinAnimation;
    protected Animation skullOnRoadAnimation;
    protected Animation ladleOnRoadAnimation;
    protected Animation shadowCoinAnimation;
    protected Animation springboardAnimation;
    protected Animation flySpringboardAnimation;
    protected Animation boosterOnRoad;
    protected Animation block;
    protected Animation dirt;
    protected Animation dirt_on_screen_1;
    protected Animation dirt_on_screen_2;
    protected Animation dirt_on_screen_3;
    protected Animation dirt_on_screen_4;
    protected Animation dirt_on_screen_5;
    protected Animation dirt_on_screen_6;

    protected Rectangle bounds;
    protected boolean isLeft;


    public Prize(int x, int y, int movement) {
        //carTexture = AssetsManager.getTextureRegion(key);
        coinAnimation = AssetsManager.getAnimation(Constants.COIN_ASSETS_ID);
        shadowCoinAnimation = AssetsManager.getAnimation(Constants.COIN_SHADOW_ASSETS_ID);
        skullOnRoadAnimation = AssetsManager.getAnimation(Constants.SKULL_ON_ROAD_ASSETS_ID);
        ladleOnRoadAnimation = AssetsManager.getAnimation(Constants.LADLE_ON_ROAD_ASSETS_ID);
        boosterOnRoad = AssetsManager.getAnimation(Constants.BOOSTER_ON_ROAD_ASSETS_ID);
        springboardAnimation = AssetsManager.getAnimation(Constants.SPRING_BOARD_ASSETS_ID);
        block = AssetsManager.getAnimation(Constants.ROAD_BLOCK_ASSETS_ID);
        dirt = AssetsManager.getAnimation(Constants.DIRT_ASSETS_ID);
        flySpringboardAnimation = AssetsManager.getAnimation(Constants.FLY_SPRINGBOARD_ASSETS_ID);
        dirt_on_screen_1 = AssetsManager.getAnimation(Constants.DIRT_ON_SCREEN_1_ASSETS_ID);
        dirt_on_screen_2 = AssetsManager.getAnimation(Constants.DIRT_ON_SCREEN_2_ASSETS_ID);
        dirt_on_screen_3 = AssetsManager.getAnimation(Constants.DIRT_ON_SCREEN_3_ASSETS_ID);
        dirt_on_screen_4 = AssetsManager.getAnimation(Constants.DIRT_ON_SCREEN_4_ASSETS_ID);
        dirt_on_screen_5 = AssetsManager.getAnimation(Constants.DIRT_ON_SCREEN_5_ASSETS_ID);
        dirt_on_screen_6 = AssetsManager.getAnimation(Constants.DIRT_ON_SCREEN_6_ASSETS_ID);

        position = new Vector3(x, y, 0);
        velosity = new Vector3(0, 0, 0);
        this.speed = movement;

        bounds = new Rectangle(x, y, coinAnimation.getKeyFrames()[0].getRegionWidth(), coinAnimation.getKeyFrames()[0].getRegionHeight());
    }

    public Prize(int y, int movement, String key) {
        //carTexture = AssetsManager.getTextureRegion(key);
        coinAnimation = AssetsManager.getAnimation(key);
        position = new Vector3(Constants.getCarPostitionXLeft(coinAnimation.getKeyFrames()[0].getRegionWidth()), y, 0);
        velosity = new Vector3(0, 0, 0);
        this.speed = movement;

        bounds = new Rectangle(Constants.getCarPostitionXLeft(coinAnimation.getKeyFrames()[0].getRegionWidth()), y, coinAnimation.getKeyFrames()[0].getRegionWidth(), coinAnimation.getKeyFrames()[0].getRegionHeight());
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

    public abstract void update(float dt);

    public abstract void turn();

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getCoinTexture() {
        return coinAnimation.getKeyFrames()[INDEX];
    }

    public TextureRegion getCoinShadowTexture() {
        return shadowCoinAnimation.getKeyFrames()[INDEX];
    }

    public TextureRegion getBusterTexture() {
        return skullOnRoadAnimation.getKeyFrames()[INDEX];
    }

    public TextureRegion getLadleTexture() {
        return ladleOnRoadAnimation.getKeyFrames()[INDEX];
    }

    public TextureRegion getBoosterOnRoadTexture() {
        return boosterOnRoad.getKeyFrames()[INDEX];
    }
    public TextureRegion getSpringBoardTexture() {return  springboardAnimation.getKeyFrames()[INDEX];};

    public TextureRegion getBlockTexture()
    {
        return block.getKeyFrames()[INDEX];
    }
    public TextureRegion getDirtTexture()
    {
        return dirt.getKeyFrames()[INDEX];
    }
    public TextureRegion getDirtOnScreen_1_Texture()
    {
        return dirt_on_screen_1.getKeyFrames()[INDEX];
    }
    public TextureRegion getDirtOnScreen_2_Texture()
    {
        return dirt_on_screen_2.getKeyFrames()[INDEX];
    }
    public TextureRegion getDirtOnScreen_3_Texture()
    {
        return dirt_on_screen_3.getKeyFrames()[INDEX];
    }
    public TextureRegion getDirtOnScreen_4_Texture()
    {
        return dirt_on_screen_4.getKeyFrames()[INDEX];
    }
    public TextureRegion getDirtOnScreen_5_Texture()
    {
        return dirt_on_screen_5.getKeyFrames()[INDEX];
    }

    public Animation getFlySpringboardAnimation()
    {
        return flySpringboardAnimation;
    }



    public Rectangle getBounds() {
        return bounds;
    }
}
