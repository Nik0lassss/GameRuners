package com.nicholaschirkevich.game.model.boosters;


import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.nicholaschirkevich.game.model.Prize;
import com.nicholaschirkevich.game.userdata.SkullOnRoadDataType;
import com.nicholaschirkevich.game.util.Constants;
import com.nicholaschirkevich.game.util.GameManager;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Nikolas on 13.02.2016.
 */
public class Skull extends Prize {
    private Random rand;
    private static World world;

    // public static final short PASSER_CAR_FILTER_ENTITY = 0x1 << 1; // 0010 or 0x2 in hex
    // 0001
    ; // 0010 or 0x2 in hex
    private int defaultX;
    private int defaultY;
    public Body body;


    private float stateTime;

    public Skull(World world, int x, int y, int movement, boolean left) {
        super(x, y, movement);
        defaultX = x;
        defaultY = y;
        rand = new Random();
        isLeft = left;
        // isLeft = rand.nextBoolean();
        if (isLeft)
            position.x = Constants.getCarPostitionXLeft(skullOnRoadAnimation.getKeyFrames()[0].getRegionWidth()+7);
        else
            position.x = Constants.getCarPostitionXRight(skullOnRoadAnimation.getKeyFrames()[0].getRegionWidth()+7);

        this.world = world;
        //sprite = new Sprite(carTexture);
        //sprite.setPosition(x, y);
        //passerCarAnimation = AssetsManager.getAnimation(animation_asset_id);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set((skullOnRoadAnimation.getKeyFrames()[0].getRegionX() + skullOnRoadAnimation.getKeyFrames()[0].getRegionWidth() / 2)
                ,
                (skullOnRoadAnimation.getKeyFrames()[0].getRegionY() + skullOnRoadAnimation.getKeyFrames()[0].getRegionHeight() / 2));

        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(skullOnRoadAnimation.getKeyFrames()[0].getRegionWidth() / 2, skullOnRoadAnimation.getKeyFrames()[0].getRegionHeight()
                / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.1f;
        fixtureDef.restitution = 0.5f;


        fixtureDef.filter.categoryBits = Constants.SKULL_MASK;
        fixtureDef.filter.maskBits = Constants.MY_CAR_FILTER_ENTITY;
        body.setUserData(new SkullOnRoadDataType());
        body.createFixture(fixtureDef);
        stateTime = 0f;

    }


//    public Skull(World world, int x, int y, int movement, boolean ifLeft) {
//        super(x, y, movement);
//        defaultX = x;
//        defaultY = y;
//
//        isLeft = ifLeft;
//        if (isLeft)
//            position.x = Constants.getCarPostitionXLeft(skullOnRoadAnimation.getKeyFrames()[0].getRegionWidth());
//        else
//            position.x = Constants.getCarPostitionXRight(skullOnRoadAnimation.getKeyFrames()[0].getRegionWidth());
//
//        this.world = world;
//        //sprite = new Sprite(carTexture);
//        //sprite.setPosition(x, y);
//        //passerCarAnimation = AssetsManager.getAnimation(animation_asset_id);
//
//        BodyDef bodyDef = new BodyDef();
//        bodyDef.type = BodyDef.BodyType.DynamicBody;
//        bodyDef.position.set((skullOnRoadAnimation.getKeyFrames()[0].getRegionX() + skullOnRoadAnimation.getKeyFrames()[0].getRegionWidth() / 2)
//                ,
//                (skullOnRoadAnimation.getKeyFrames()[0].getRegionY() + skullOnRoadAnimation.getKeyFrames()[0].getRegionHeight() / 2));
//
//        body = world.createBody(bodyDef);
//
//        PolygonShape shape = new PolygonShape();
//        shape.setAsBox(skullOnRoadAnimation.getKeyFrames()[0].getRegionWidth() / 2, skullOnRoadAnimation.getKeyFrames()[0].getRegionHeight()
//                / 2);
//
//        FixtureDef fixtureDef = new FixtureDef();
//        fixtureDef.shape = shape;
//        fixtureDef.density = 0.1f;
//        fixtureDef.restitution = 0.5f;
//
//
//        fixtureDef.filter.categoryBits = SKULL_MASK;
//        fixtureDef.filter.maskBits = MyCar.MY_CAR_FILTER_ENTITY;
//
//        body.createFixture(fixtureDef);
//        stateTime = 0f;
//
//    }

    public void draw(SpriteBatch batch) {
        //batch.draw(getCoinShadowTexture(), position.x, position.y - 10);
        batch.draw(getBusterTexture(), position.x, position.y);
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
            position.x = Constants.CAR_POS_X_LEFT - skullOnRoadAnimation.getKeyFrames()[0].getRegionHeight() / 2;
        else
            position.x = Constants.CAR_POS_X_RIGHT - skullOnRoadAnimation.getKeyFrames()[0].getRegionHeight() / 2;
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


    public static void updateCoins(ArrayList<Skull> busterses, Camera camera, float dt) {

//        if (camera.viewportHeight - busterses.get(busterses.size() - 1).getPosition().y > Constants.coinDistance) {
//
//            busterses.add(new Skull(world, 90, (int) camera.viewportHeight + 20, 10));
//
//        }
//
//        for (int i = 0; i < busterses.size(); i++) {
//
//            if (busterses.get(i).getPosition().y < 0) {
//                world.destroyBody(busterses.get(i).body);
//                busterses.remove(i);
//
//
//            }
//            busterses.get(i).update(dt);
//        }

    }


    public float getStateTime() {
        return stateTime;
    }

    public Animation getCoinAnimation() {
        return coinAnimation;
    }
}
