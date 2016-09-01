package com.nicholaschirkevich.game.model.side_objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.nicholaschirkevich.game.enums.TraffictLighterEnum;
import com.nicholaschirkevich.game.interfaces.OnTrafficLightListener;
import com.nicholaschirkevich.game.util.AssetsManager;
import com.nicholaschirkevich.game.util.Constants;

import java.util.TimerTask;

/**
 * Created by Nikolas on 25.02.2016.
 */
public class TrafficLight {
    public static final double TIME_STATE_1 = 0.8;
    public static final double TIME_STATE_2 = 1.3;
    public static final double TIME_STATE_3 = 1.8;
    public static final double TIME_STATE = 0.5;
    public static final int CURRENT_STATE_1 = 1;
    public static final int CURRENT_STATE_2 = 2;
    public static final int CURRENT_STATE_3 = 3;
    public static final int CURRENT_STATE = 0;
    public static final int STATE_1 = 1;
    public static final double STATE_2 = 1.5;
    public static final int STATE_3 = 2;
    private Texture texture;
    private Vector2 position;

    private boolean isWork = false;
    private final int red = 0;
    private float time = 0;
    private int currentState = red;
    OnTrafficLightListener onTrafficLightListener;

    public TraffictLighterEnum getTraffictLighterEnum() {
        return traffictLighterEnum;
    }

    private TraffictLighterEnum traffictLighterEnum;


    public TrafficLight(int x, int y, TraffictLighterEnum traffictLighterEnum) {
//
        texture = traffictLighterEnum.getTexture(currentState);
        position = new Vector2(x, y);
//

        this.traffictLighterEnum = traffictLighterEnum;
    }


    public void update(float dt) {
        time += dt;
        if (time > TIME_STATE_1 && currentState == CURRENT_STATE_1) {
            AssetsManager.playSoundLow(Constants.SOUND_START1);
        } else if (time > TIME_STATE_2 && currentState == CURRENT_STATE_2) {
            AssetsManager.playSoundLow(Constants.SOUND_START1);
        } else if (time > TIME_STATE_3 && currentState == CURRENT_STATE_3) {
            AssetsManager.playSoundLow(Constants.SOUND_START_2);
        }

        if (time > TIME_STATE && currentState == CURRENT_STATE) {
            texture = traffictLighterEnum.getTexture(currentState);
            currentState++;
            return;
        } else if (time > STATE_1 && currentState == CURRENT_STATE_1) {
            texture = traffictLighterEnum.getTexture(currentState);
            currentState++;

            return;
        } else if (time > STATE_2 && currentState == CURRENT_STATE_2) {
            texture = traffictLighterEnum.getTexture(currentState);
            currentState++;

            return;
        } else if (time > STATE_3 && currentState == CURRENT_STATE_3) {
            texture = traffictLighterEnum.getTexture(currentState);
            currentState++;

            onTrafficLightListener.onStartTraffic();
            isWork = false;
            currentState = 0;
            time = 0;
            return;
        }

    }

    public void setOnTrafficLightListener(OnTrafficLightListener onTrafficLightListener) {
        this.onTrafficLightListener = onTrafficLightListener;
    }

    public Texture getTexture() {
        return texture;
    }

    public Vector2 getPosition() {
        return position;
    }


}
