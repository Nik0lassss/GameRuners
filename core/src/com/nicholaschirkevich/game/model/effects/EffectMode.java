package com.nicholaschirkevich.game.model.effects;

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

    private static int posX = 0;
    private static int posXLeft = 200;
    private int size = 20;
    private boolean isAlfa = false;
    private boolean isStartAlfa = false;
    private float alfaTime = 0;
    private float alfa = 1;



    public EffectMode() {
        effectAnimationModeLeft = AssetsManager.getAnimation(Constants.INSANE_MODE_ALPHA_LEFT_ASSETS_ID);



        effectAnimationModeRight = AssetsManager.getAnimation(Constants.INSANE_MODE_ALPHA_RIGHT_ASSETS_ID);



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


    }


    public void draw(SpriteBatch sb) {
        Color color = sb.getColor();
        sb.setColor(color.r, color.g, color.b, alfa);



            sb.draw(effectAnimationModeLeft.getKeyFrames()[0].getTexture(),posX, 0,effectAnimationModeLeft.getKeyFrames()[0].getTexture().getWidth(),GameRuners.HEIGHT);

            sb.draw(effectAnimationModeRight.getKeyFrames()[0].getTexture(), posXLeft,0, effectAnimationModeRight.getKeyFrames()[0].getTexture().getWidth(),GameRuners.HEIGHT);

        sb.setColor(color.r, color.g, color.b, 1f);
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
