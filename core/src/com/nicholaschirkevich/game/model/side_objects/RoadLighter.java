package com.nicholaschirkevich.game.model.side_objects;


import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.nicholaschirkevich.game.util.GameManager;

import java.util.Random;

/**
 * Created by Nikolas on 13.02.2016.
 */
public class RoadLighter extends BasicRoadLighter {
    private Random rand;
    World world;




    private int defaultX;
    private int defaultY;
    public Body body;

    public RoadLighter(World world, int x, int y, int movement, boolean isLeft, String key) {
        super(x, y, movement, key);
        defaultX = x;
        defaultY = y;
        if (isLeft) position.x = leftSide;
        else position.x = rightSide;
        rand = new Random();
        isLeft = rand.nextBoolean();




    }

    @Override
    public void update(float dt) {
        position.add(0, -(GameManager.getCurrentSpeed()) * dt, 0);
       // sprite.setPosition(position.x, position.y);
        bounds.setPosition(position.x, position.y);


    }

    public void setPosition() {

        //int randomY = rand.nextInt((50 - 0) + 1) + 0;
        isLeft = rand.nextBoolean();

        position.add(0, defaultY + 200, 0);
       // sprite.setPosition(position.x, position.y);
        bounds.setPosition(position.x, position.y);

    }

    @Override
    public void turn() {

    }

    public void dispose() {


    }
}
