package com.nicholaschirkevich.game.model;


import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.nicholaschirkevich.game.util.Constants;

import java.util.Random;

/**
 * Created by Nikolas on 13.02.2016.
 */
public class ThronsOnCarLeft extends ThronsOnCarBasicLeft {
    private Random rand;
    private static World world;

    // public static final short PASSER_CAR_FILTER_ENTITY = 0x1 << 1; // 0010 or 0x2 in hex
    public static final short THORN = 0x9;    // 0001
    ; // 0010 or 0x2 in hex
    private int defaultX;
    private int defaultY;
    public Body body;


    private float stateTime;


    public ThronsOnCarLeft(World world, int x, int y, int movement) {
        super(x, y, movement);
        defaultX = x;
        defaultY = y;


        if (isLeft)
            position.x = Constants.getCarPostitionXLeft(thronsOnCarLeft.getKeyFrames()[0].getRegionWidth());
        else
            position.x = Constants.getCarPostitionXRight(thronsOnCarLeft.getKeyFrames()[0].getRegionWidth());

        this.world = world;
        //sprite = new Sprite(carTexture);
        //sprite.setPosition(x, y);
        //passerCarAnimation = AssetsManager.getAnimation(animation_asset_id);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set((thronsOnCarLeft.getKeyFrames()[0].getRegionX() + thronsOnCarLeft.getKeyFrames()[0].getRegionWidth() / 2)
                ,
                (thronsOnCarLeft.getKeyFrames()[0].getRegionY() + thronsOnCarLeft.getKeyFrames()[0].getRegionHeight() / 2));


            body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(thronsOnCarLeft.getKeyFrames()[0].getRegionWidth() / 2, thronsOnCarLeft.getKeyFrames()[0].getRegionHeight()
                / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.1f;
        fixtureDef.restitution = 1f;


       // fixtureDef.filter.categoryBits = THORN;
        //fixtureDef.filter.maskBits = MyCar.MY_CAR_FILTER_ENTITY;
        //body.setTransform(x, y, 0);
        body.createFixture(fixtureDef);
        stateTime = 0f;

    }

    public void draw(SpriteBatch batch) {
        //batch.draw(getSpringboard(), position.x, position.y - 10);

    }

    @Override
    public void update(float dt, float x, float y) {

        position.set(x, y, 0);

        //sprite.setPosition(position.x, position.y);
        bounds.setPosition(x, y);
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


        //isLeft = rand.nextBoolean();
//        if (isLeft)
//            position.x = Constants.CAR_POS_X_LEFT - springboard.getKeyFrames()[0].getRegionHeight() / 2;
//        else
//            position.x = Constants.CAR_POS_X_RIGHT - springboard.getKeyFrames()[0].getRegionHeight() / 2;
        position.add(0, defaultY, 0);
        //sprite.setPosition(position.x, position.y);
        bounds.setPosition(position.x, position.y);
        body.setTransform(position.x, position.y, 0.0f);
    }

    @Override
    public void turn() {

    }

    public void dispose() {


    }


    public static void updateLadle(Camera camera, float dt) {


    }


    public float getStateTime() {
        return stateTime;
    }

    public Animation getboosOnCarLeft() {
        return thronsOnCarLeft;
    }
}
