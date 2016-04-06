package com.nicholaschirkevich.game.model;


import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.nicholaschirkevich.game.userdata.CoinDataType;
import com.nicholaschirkevich.game.util.Constants;
import com.nicholaschirkevich.game.util.GameManager;
import com.nicholaschirkevich.game.util.RandomUtil;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Nikolas on 13.02.2016.
 */
public class Coin extends Prize {
    private Random rand;
    private static World world;

    // public static final short PASSER_CAR_FILTER_ENTITY = 0x1 << 1; // 0010 or 0x2 in hex
    public static final short COIN_MASK = 0x3;    // 0001
    ; // 0010 or 0x2 in hex
    private int defaultX;
    private int defaultY;
    public Body body;


    private float stateTime;

    public Coin(World world, int x, int y, int movement) {
        super(x, y, movement);
        defaultX = x;
        defaultY = y;
        rand = new Random();
        isLeft = rand.nextBoolean();
        if (isLeft)
            position.x = Constants.getCarPostitionXLeft(coinAnimation.getKeyFrames()[0].getRegionWidth());
        else
            position.x = Constants.getCarPostitionXRight(coinAnimation.getKeyFrames()[0].getRegionWidth());

        this.world = world;
        //sprite = new Sprite(carTexture);
        //sprite.setPosition(x, y);
        //passerCarAnimation = AssetsManager.getAnimation(animation_asset_id);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set((coinAnimation.getKeyFrames()[0].getRegionX() + coinAnimation.getKeyFrames()[0].getRegionWidth() / 2)
                ,
                (coinAnimation.getKeyFrames()[0].getRegionY() + coinAnimation.getKeyFrames()[0].getRegionHeight() / 2));

        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(coinAnimation.getKeyFrames()[0].getRegionWidth() / 2, coinAnimation.getKeyFrames()[0].getRegionHeight()
                / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.1f;
        fixtureDef.restitution = 0.5f;


        fixtureDef.filter.categoryBits = COIN_MASK;
        fixtureDef.filter.maskBits = MyCar.MY_CAR_FILTER_ENTITY;
        body.setUserData(new CoinDataType());
        body.createFixture(fixtureDef);
        stateTime = 0f;

    }


    public Coin(World world, int x, int y, int movement, boolean ifLeft) {
        super(x, y, movement);
        defaultX = x;
        defaultY = y;

        isLeft = ifLeft;
        if (isLeft)
            position.x = Constants.getCarPostitionXLeft(coinAnimation.getKeyFrames()[0].getRegionWidth());
        else
            position.x = Constants.getCarPostitionXRight(coinAnimation.getKeyFrames()[0].getRegionWidth());

        this.world = world;
        //sprite = new Sprite(carTexture);
        //sprite.setPosition(x, y);
        //passerCarAnimation = AssetsManager.getAnimation(animation_asset_id);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set((coinAnimation.getKeyFrames()[0].getRegionX() + coinAnimation.getKeyFrames()[0].getRegionWidth() / 2)
                ,
                (coinAnimation.getKeyFrames()[0].getRegionY() + coinAnimation.getKeyFrames()[0].getRegionHeight() / 2));

        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(coinAnimation.getKeyFrames()[0].getRegionWidth() / 2, coinAnimation.getKeyFrames()[0].getRegionHeight()
                / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.1f;
        fixtureDef.restitution = 0.5f;


        fixtureDef.filter.categoryBits = COIN_MASK;
        fixtureDef.filter.maskBits = MyCar.MY_CAR_FILTER_ENTITY;
        body.setUserData(new CoinDataType());
        body.createFixture(fixtureDef);
        stateTime = 0f;

    }


    public boolean isLeft()
    {
        return isLeft;
    }
    public void draw(SpriteBatch batch) {
        batch.draw(getCoinShadowTexture(), position.x, position.y - 10);
        batch.draw(getCoinTexture(), position.x, position.y);
    }

    @Override
    public void update(float dt) {

       position.add(0, (-GameManager.getCurrentSpeed()) * dt, 0);

        //sprite.setPosition(position.x, position.y);
        bounds.setPosition(position.x, position.y);
        body.setTransform(position.x, position.y, 0.0f);
        setPosition(position.x, position.y);
        stateTime += dt;
    }

    public void setDefaultPosition(int x) {
        position.set(0, x, 0);

        //sprite.setPosition(position.x, position.y);
        bounds.setPosition(position.x, position.y);
        body.setTransform(position.x, position.y, 0.0f);

    }


    public void setPosition() {

        int randomY = rand.nextInt((70 - 0) + 1) + 0;
        isLeft = rand.nextBoolean();
        if (isLeft)
            position.x = Constants.CAR_POS_X_LEFT - coinAnimation.getKeyFrames()[0].getRegionHeight() / 2;
        else
            position.x = Constants.CAR_POS_X_RIGHT - coinAnimation.getKeyFrames()[0].getRegionHeight() / 2;
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


    public static void updateCoins(ArrayList<Coin> coins, Camera camera, float dt) {

        if (camera.viewportHeight - coins.get(coins.size() - 1).getPosition().y > Constants.coinDistance) {
//            boolean isBigCar = rand.nextBoolean();
//            if (!isBigCar)
            coins.add(new Coin(world, 90, (int) camera.viewportHeight + 20, 10));
//            else
//                passerCars.add(new PasserCar(world, 90, (int) camera.viewportHeight + 200, 10, true, Constants.OTHERCAR_2_1_ASSETS_ID));
//
        }

        for (int i = 0; i < coins.size(); i++) {

            if (coins.get(i).getPosition().y < 0) {
                world.destroyBody(coins.get(i).body);
                coins.remove(i);


            }
            coins.get(i).update(dt);
        }

    }


    public float getStateTime() {
        return stateTime;
    }

    public Animation getCoinAnimation() {
        return coinAnimation;
    }
}
