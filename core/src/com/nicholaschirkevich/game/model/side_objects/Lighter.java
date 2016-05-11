package com.nicholaschirkevich.game.model.side_objects;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.nicholaschirkevich.game.util.AssetsManager;
import com.nicholaschirkevich.game.util.Constants;
import com.nicholaschirkevich.game.util.GameManager;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Nikolas on 12.02.2016.
 */
public class Lighter {

    private Animation effectAnimationLighterLeft;
    private Animation effectAnimationLighterRight;
    private Vector2 posEffectBoost1, posEffectBoost2;
    private Random rand;
    private Rectangle boundsRoad1, boundsRoad2;
    private static int posX = -45;
    private static int posXLeft = 220;
    private int size = 20;


    private ArrayList<Vector2> positionsEffectLeft = new ArrayList<Vector2>();
    private ArrayList<Vector2> positionsEffectRight = new ArrayList<Vector2>();
    private ArrayList<Rectangle> boundArrayListLeft = new ArrayList<Rectangle>();
    private ArrayList<Rectangle> boundArrayListRight = new ArrayList<Rectangle>();

    public Lighter() {
        effectAnimationLighterLeft = AssetsManager.getAnimation(Constants.ROAD_1_LIGHTER_L_STATIC_ASSETS_ID);

        for (int i = 0; i < size; i++) {
            positionsEffectLeft.add(new Vector2(posX, i == 0 ? 20 : (positionsEffectLeft.get(i - 1).y +450+ effectAnimationLighterLeft.getKeyFrames()[0].getRegionHeight())));
            boundArrayListLeft.add(new Rectangle(positionsEffectLeft.get(i).x, positionsEffectLeft.get(i).y, effectAnimationLighterLeft.getKeyFrames()[0].getRegionWidth(), effectAnimationLighterLeft.getKeyFrames()[0].getRegionHeight()));
        }

       // effectAnimationLighterRight = AssetsManager.getAnimation(Constants.ROAD_1_LIGHTER_R_STATIC_ASSETS_ID);

//        for (int i = 0; i < size; i++) {
//            positionsEffectRight.add(new Vector2(posXLeft, i == 0 ? 20 : (positionsEffectRight.get(i - 1).y+ 450+effectAnimationLighterRight.getKeyFrames()[0].getRegionHeight())));
//            boundArrayListRight.add(new Rectangle(positionsEffectLeft.get(i).x, positionsEffectLeft.get(i).y, effectAnimationLighterRight.getKeyFrames()[0].getRegionWidth(), effectAnimationLighterRight.getKeyFrames()[0].getRegionHeight()));
//        }
//        posEffectBoost1 = new Vector2(posX, 0);
//        posEffectBoost2 = new Vector2(posX, effectAnimationLighterLeft.getKeyFrames()[0].getRegionHeight());


//        boundsRoad1 = new Rectangle(posEffectBoost1.x, posEffectBoost1.y, effectAnimationLighterLeft.getKeyFrames()[0].getRegionWidth(), effectAnimationLighterLeft.getKeyFrames()[0].getRegionHeight());
//        boundsRoad2 = new Rectangle(posEffectBoost2.x, posEffectBoost2.y, effectAnimationLighterLeft.getKeyFrames()[0].getRegionWidth(), effectAnimationLighterLeft.getKeyFrames()[0].getRegionHeight());
//

    }


    public void update(Camera camera, float dt) {

//        for (int i = 0; i < size; i++) {
//            if (camera.position.y  < positionsEffectLeft.get(i).y - effectAnimationLighterLeft.getKeyFrames()[0].getRegionHeight()*2) {
////                positionsEffectLeft.get(i).add(0, -effectAnimationLighterLeft.getKeyFrames()[0].getRegionHeight() * size);
//                positionsEffectLeft.get(i).add(0, -effectAnimationLighterLeft.getKeyFrames()[0].getRegionHeight() * size);
//            }
//        }

//        if (camera.position.y + camera.viewportHeight < posEffectBoost1.y + effectAnimationLighterLeft.getKeyFrames()[0].getRegionHeight()) {
//            posEffectBoost1.add(0,- effectAnimationLighterLeft.getKeyFrames()[0].getRegionHeight() * 2);
//
//
//        }
//        if (camera.position.y + camera.viewportHeight < posEffectBoost2.y + effectAnimationLighterLeft.getKeyFrames()[0].getRegionHeight()) {
//            posEffectBoost2.add(0,- effectAnimationLighterLeft.getKeyFrames()[0].getRegionHeight() * 2);
//
//        }

        for (int i = 0; i < size; i++) {
            positionsEffectLeft.get(i).add(0,  - GameManager.getCurrentSpeed() * dt);
        }
//        posEffectBoost1.set(0, posEffectBoost1.y + (+GameManager.getCurrentSpeed()) * dt);
//        posEffectBoost2.set(0, posEffectBoost2.y + (+GameManager.getCurrentSpeed()) * dt);


    }


    public void draw(SpriteBatch sb) {
        for (int i = 0; i < size; i++) {
            sb.draw(effectAnimationLighterLeft.getKeyFrames()[0].getTexture(), positionsEffectLeft.get(i).x, positionsEffectLeft.get(i).y);
        }
//        for (int i = 0; i < size; i++) {
//            sb.draw(effectAnimationLighterRight.getKeyFrames()[0].getTexture(), positionsEffectRight.get(i).x, positionsEffectRight.get(i).y);
//        }
    }

    public TextureRegion getEffect1() {
        return effectAnimationLighterLeft.getKeyFrames()[0];
    }

    public TextureRegion getEffect2() {
        return effectAnimationLighterLeft.getKeyFrames()[0];
    }

    public Vector2 getPosEffectBoost1() {
        return posEffectBoost1;
    }

    public Vector2 getPosEffectBoost2() {
        return posEffectBoost2;
    }

    public Rectangle getBoundsRoad1() {
        return boundsRoad1;
    }

    public Rectangle getBoundsRoad2() {
        return boundsRoad2;
    }


}
