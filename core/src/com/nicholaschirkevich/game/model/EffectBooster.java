package com.nicholaschirkevich.game.model;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
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
public class EffectBooster {

    private Animation effectAnimation;
    private Vector2 posEffectBoost1, posEffectBoost2;
    private Random rand;
    private Rectangle boundsRoad1, boundsRoad2;
    private static int posX = 0;
    private int size = 6;
    private boolean isAlfa = false;
    private boolean isStartAlfa = false;
    private float alfaTime = 0;
    private float alfa = 1;

    private ArrayList<Vector2> positionsEffectBoost = new ArrayList<Vector2>();
    private ArrayList<Rectangle> boundArrayList = new ArrayList<Rectangle>();

    public EffectBooster() {
        effectAnimation = AssetsManager.getAnimation(Constants.EFFECT_BOOST_ASSETS_ID);

        for (int i = 0; i < size; i++) {
            positionsEffectBoost.add(new Vector2(posX, i == 0 ? 20 : (positionsEffectBoost.get(i - 1).y + 146)));
            boundArrayList.add(new Rectangle(positionsEffectBoost.get(i).x, positionsEffectBoost.get(i).y, effectAnimation.getKeyFrames()[0].getRegionWidth(), effectAnimation.getKeyFrames()[0].getRegionHeight()));
        }
//        posEffectBoost1 = new Vector2(posX, 0);
//        posEffectBoost2 = new Vector2(posX, effectAnimation.getKeyFrames()[0].getRegionHeight());


//        boundsRoad1 = new Rectangle(posEffectBoost1.x, posEffectBoost1.y, effectAnimation.getKeyFrames()[0].getRegionWidth(), effectAnimation.getKeyFrames()[0].getRegionHeight());
//        boundsRoad2 = new Rectangle(posEffectBoost2.x, posEffectBoost2.y, effectAnimation.getKeyFrames()[0].getRegionWidth(), effectAnimation.getKeyFrames()[0].getRegionHeight());
//

    }


    public void update(Camera camera, float dt) {

        if (isStartAlfa) alfaTime += dt;
        if (alfaTime > 0.3) {
            if (isAlfa) {
                isAlfa = false;
                alfa = 1;
            } else {
                isAlfa = true;
                alfa = 0;
            }
            alfaTime = 0;
        }

        for (int i = 0; i < size; i++) {
            if (camera.position.y < positionsEffectBoost.get(i).y - effectAnimation.getKeyFrames()[0].getRegionHeight() * 2) {
                positionsEffectBoost.get(i).add(0, -effectAnimation.getKeyFrames()[0].getRegionHeight() * size);
            }
        }

//        if (camera.position.y + camera.viewportHeight < posEffectBoost1.y + effectAnimation.getKeyFrames()[0].getRegionHeight()) {
//            posEffectBoost1.add(0,- effectAnimation.getKeyFrames()[0].getRegionHeight() * 2);
//
//
//        }
//        if (camera.position.y + camera.viewportHeight < posEffectBoost2.y + effectAnimation.getKeyFrames()[0].getRegionHeight()) {
//            posEffectBoost2.add(0,- effectAnimation.getKeyFrames()[0].getRegionHeight() * 2);
//
//        }

        for (int i = 0; i < size; i++) {
            positionsEffectBoost.get(i).set(0, positionsEffectBoost.get(i).y + 100 * dt);
        }
//        posEffectBoost1.set(0, posEffectBoost1.y + (+GameManager.getCurrentSpeed()) * dt);
//        posEffectBoost2.set(0, posEffectBoost2.y + (+GameManager.getCurrentSpeed()) * dt);


    }


    public void draw(SpriteBatch sb) {
        Color color = sb.getColor();
        sb.setColor(color.r, color.g, color.b, alfa);
        for (int i = 0; i < size; i++) {


            sb.draw(effectAnimation.getKeyFrames()[0].getTexture(), positionsEffectBoost.get(i).x, positionsEffectBoost.get(i).y);
        }
        sb.setColor(color.r, color.g, color.b, 1f);
    }

    public TextureRegion getEffect1() {
        return effectAnimation.getKeyFrames()[0];
    }

    public TextureRegion getEffect2() {
        return effectAnimation.getKeyFrames()[0];
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


    public boolean isAlfa() {
        return isAlfa;
    }

    public void setIsAlfa(boolean isAlfa) {
        this.isAlfa = isAlfa;
    }

    public boolean isStartAlfa() {
        return isStartAlfa;
    }

    public void setIsStartAlfa(boolean isStartAlfa) {
        this.isStartAlfa = isStartAlfa;
    }
}
