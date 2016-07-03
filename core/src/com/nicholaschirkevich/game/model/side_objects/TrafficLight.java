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
    private Texture texture;
    private Vector2 position;
    //private Rectangle bounds;
    private boolean isWork = false;
    private final int red = 0, green1 = 1, green2 = 2, green3 = 3;
    private float time =0;
    private int currentState = red;
    OnTrafficLightListener onTrafficLightListener;

    public TraffictLighterEnum getTraffictLighterEnum() {
        return traffictLighterEnum;
    }

    private TraffictLighterEnum traffictLighterEnum;


    public TrafficLight(int x, int y, TraffictLighterEnum traffictLighterEnum) {
//        textureGreen1 = AssetsManager.getTextureRegion(Constants.START_LIGHT_1_ID).getTexture();
//        textureGreen2 = AssetsManager.getTextureRegion(Constants.START_LIGHT_2_ID).getTexture();
//        textureGreen3 =  AssetsManager.getTextureRegion(Constants.START_LIGHT_3_ID).getTexture();
//        textureRed =  AssetsManager.getTextureRegion(Constants.START_LIGHT_RED_ID).getTexture();
        texture = traffictLighterEnum.getTexture(currentState);
        position = new Vector2(x, y);
//        bounds = new Rectangle(x, y, texture.getWidth(), texture.getHeight());

        this.traffictLighterEnum = traffictLighterEnum;
    }





    public void update(float dt)
    {
        time+=dt;
        if(time>0.8 && currentState ==1)
        {
            AssetsManager.playSoundLow(Constants.SOUND_START1);
        } else if(time>1.3 && currentState==2)
        {
            AssetsManager.playSoundLow(Constants.SOUND_START1);
        }else if(time>1.8 && currentState ==3)
        {
            AssetsManager.playSoundLow(Constants.SOUND_START_2);
        }else if(time>2.4)
        {
            //AssetsManager.playSound(Constants.SOUND_START_2);
        }

       if(time>0.5 && currentState ==0)
       {
           texture = traffictLighterEnum.getTexture(currentState);
           currentState++;

           return;
       }
         else if(time>1 && currentState ==1)
       {
           texture = traffictLighterEnum.getTexture(currentState);
           currentState++;

           return;
       } else if(time>1.5 && currentState ==2)
       {
           texture = traffictLighterEnum.getTexture(currentState);
           currentState++;

           return;
       }else if(time>2 && currentState ==3)
       {
           texture = traffictLighterEnum.getTexture(currentState);
           currentState++;
           //AssetsManager.playSound(Constants.SOUND_START_2);
           onTrafficLightListener.onStartTraffic();
           isWork = false;
           currentState=0;
           time=0;
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
