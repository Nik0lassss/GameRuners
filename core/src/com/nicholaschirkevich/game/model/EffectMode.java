package com.nicholaschirkevich.game.model;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.nicholaschirkevich.game.GameRuners;
import com.nicholaschirkevich.game.states.GameState;
import com.nicholaschirkevich.game.util.AssetsManager;
import com.nicholaschirkevich.game.util.Constants;
import com.nicholaschirkevich.game.util.GameManager;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Nikolas on 12.02.2016.
 */
public class EffectMode {

    private Animation effectAnimationModeLeft;
    private Animation effectAnimationModeRight;
    private Vector2 posEffectBoost1, posEffectBoost2;
    private Random rand;
    private Rectangle boundsRoad1, boundsRoad2;
    private static int posX = 0;
    private static int posXLeft = 200;
    private int size = 20;
    private boolean isAlfa = false;
    private boolean isStartAlfa = false;
    private float alfaTime = 0;
    private float alfa = 1;

    private ArrayList<Vector2> positionsEffectLeft = new ArrayList<Vector2>();
    private ArrayList<Vector2> positionsEffectRight = new ArrayList<Vector2>();
    private ArrayList<Rectangle> boundArrayListLeft = new ArrayList<Rectangle>();
    private ArrayList<Rectangle> boundArrayListRight = new ArrayList<Rectangle>();

    public EffectMode() {
        effectAnimationModeLeft = AssetsManager.getAnimation(Constants.INSANE_MODE_ALPHA_LEFT_ASSETS_ID);

        for (int i = 0; i < size; i++) {
            positionsEffectLeft.add(new Vector2(posX, i == 0 ? 20 : (positionsEffectLeft.get(i - 1).y + effectAnimationModeLeft.getKeyFrames()[0].getRegionHeight())));
            boundArrayListLeft.add(new Rectangle(positionsEffectLeft.get(i).x, positionsEffectLeft.get(i).y, effectAnimationModeLeft.getKeyFrames()[0].getRegionWidth(), effectAnimationModeLeft.getKeyFrames()[0].getRegionHeight()));
        }

        effectAnimationModeRight = AssetsManager.getAnimation(Constants.INSANE_MODE_ALPHA_RIGHT_ASSETS_ID);

        for (int i = 0; i < size; i++) {
            positionsEffectRight.add(new Vector2(posXLeft, i == 0 ? 20 : (positionsEffectRight.get(i - 1).y+ effectAnimationModeRight.getKeyFrames()[0].getRegionHeight())));
            boundArrayListRight.add(new Rectangle(positionsEffectLeft.get(i).x, positionsEffectLeft.get(i).y, effectAnimationModeRight.getKeyFrames()[0].getRegionWidth(), effectAnimationModeRight.getKeyFrames()[0].getRegionHeight()));
        }
//        posEffectBoost1 = new Vector2(posX, 0);
//        posEffectBoost2 = new Vector2(posX, effectAnimationModeLeft.getKeyFrames()[0].getRegionHeight());


//        boundsRoad1 = new Rectangle(posEffectBoost1.x, posEffectBoost1.y, effectAnimationModeLeft.getKeyFrames()[0].getRegionWidth(), effectAnimationModeLeft.getKeyFrames()[0].getRegionHeight());
//        boundsRoad2 = new Rectangle(posEffectBoost2.x, posEffectBoost2.y, effectAnimationModeLeft.getKeyFrames()[0].getRegionWidth(), effectAnimationModeLeft.getKeyFrames()[0].getRegionHeight());
//

    }


    public void update(Camera camera, float dt) {
        if (isStartAlfa) alfaTime += dt;
        if (alfaTime > 0.7) {
            if (isAlfa) {
                isAlfa = false;
                alfa = 1;
            } else {
                isAlfa = true;
                alfa = 0;
            }
            alfaTime = 0;
        }
//        System.out.println("alfa"+alfa);
//        for (int i = 0; i < size; i++) {
//            if (camera.position.y  < positionsEffectLeft.get(i).y - effectAnimationModeLeft.getKeyFrames()[0].getRegionHeight()*2) {
//                positionsEffectLeft.get(i).add(0, -effectAnimationModeLeft.getKeyFrames()[0].getRegionHeight() * size);
//            }
//        }

//        if (camera.position.y + camera.viewportHeight < posEffectBoost1.y + effectAnimationModeLeft.getKeyFrames()[0].getRegionHeight()) {
//            posEffectBoost1.add(0,- effectAnimationModeLeft.getKeyFrames()[0].getRegionHeight() * 2);
//
//
//        }
//        if (camera.position.y + camera.viewportHeight < posEffectBoost2.y + effectAnimationModeLeft.getKeyFrames()[0].getRegionHeight()) {
//            posEffectBoost2.add(0,- effectAnimationModeLeft.getKeyFrames()[0].getRegionHeight() * 2);
//
//        }

//        for (int i = 0; i < size; i++) {
//            positionsEffectLeft.get(i).set(0, positionsEffectLeft.get(i).y + 100 * dt);
//        }
//        posEffectBoost1.set(0, posEffectBoost1.y + (+GameManager.getCurrentSpeed()) * dt);
//        posEffectBoost2.set(0, posEffectBoost2.y + (+GameManager.getCurrentSpeed()) * dt);


    }


    public void draw(SpriteBatch sb) {
        Color color = sb.getColor();
        sb.setColor(color.r, color.g, color.b, alfa);

        for (int i = 0; i < size; i++) {

            sb.draw(effectAnimationModeLeft.getKeyFrames()[0].getTexture(), positionsEffectLeft.get(i).x, positionsEffectLeft.get(i).y);
        }
        for (int i = 0; i < size; i++) {
            sb.draw(effectAnimationModeRight.getKeyFrames()[0].getTexture(), positionsEffectRight.get(i).x, positionsEffectRight.get(i).y);
        }
        sb.setColor(color.r, color.g, color.b, 1f);
    }

    public TextureRegion getEffect1() {
        return effectAnimationModeLeft.getKeyFrames()[0];
    }

    public TextureRegion getEffect2() {
        return effectAnimationModeLeft.getKeyFrames()[0];
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


    public boolean isStartAlfa() {
        return isStartAlfa;
    }

    public void setIsStartAlfa(boolean isStartAlfa) {
        this.isStartAlfa = isStartAlfa;
    }

    public float getAlfa() {
        return alfa;
    }

    public void setAlfa(float alfa) {
        this.alfa = alfa;
    }
}
