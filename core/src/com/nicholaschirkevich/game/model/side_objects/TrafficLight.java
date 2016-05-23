package com.nicholaschirkevich.game.model.side_objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.nicholaschirkevich.game.interfaces.OnTrafficLightListener;
import com.nicholaschirkevich.game.util.AssetsManager;
import com.nicholaschirkevich.game.util.Constants;

import java.util.TimerTask;

/**
 * Created by Nikolas on 25.02.2016.
 */
public class TrafficLight {
    private Texture texture, textureGreen1, textureGreen2, textureGreen3, textureRed;
    private Vector2 position;
    private Rectangle bounds;
    private boolean isWork = false;
    private final int red = 0, green1 = 1, green2 = 2, green3 = 3;
    private float time =0;
    //private Timer timer;
    private int currentState = red;
    OnTrafficLightListener onTrafficLightListener;
    ChangeTrafficState changeTrafficState;

    public TrafficLight(int x, int y) {
        textureGreen1 = AssetsManager.getTextureRegion(Constants.START_LIGHT_1_ID).getTexture();
        textureGreen2 = AssetsManager.getTextureRegion(Constants.START_LIGHT_2_ID).getTexture();
        textureGreen3 =  AssetsManager.getTextureRegion(Constants.START_LIGHT_3_ID).getTexture();
        textureRed =  AssetsManager.getTextureRegion(Constants.START_LIGHT_RED_ID).getTexture();
        texture = textureRed;
        position = new Vector2(x, y);
        bounds = new Rectangle(x, y, texture.getWidth(), texture.getHeight());

    }


    public void stopTrafficLight() {
        if (changeTrafficState != null)
            changeTrafficState.cancel();

//        if (timer != null)
//            timer.cancel();


//        currentState = 0;


    }

    public void startTrafficLight() {
        isWork = true;
        TrafficLightTask();
    }

    public void update(float dt)
    {
        time+=dt;
       if(time>0.5 && currentState ==0)
       {
           texture = textureRed;
           currentState++;
           return;
       }
         else if(time>1 && currentState ==1)
       {
           texture = textureGreen1;
           currentState++;
           return;
       } else if(time>1.5 && currentState ==2)
       {
           texture = textureGreen2;
           currentState++;
           return;
       }else if(time>2 && currentState ==3)
       {
           texture = textureGreen3;
           currentState++;
           onTrafficLightListener.onStartTraffic();
           isWork = false;
           currentState=0;
           time=0;
           return;
       }

        //System.out.println("TafficLighterTime "+time);
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

    public Rectangle getBounds() {
        return bounds;
    }

    public void TrafficLightTask() {
       // timer = new Timer();
       // changeTrafficState = new ChangeTrafficState();
       // timer.schedule(changeTrafficState, 0, // initial delay
               // 1 * 1000); // subsequent rate
    }

    public boolean isWork() {
        return isWork;
    }

    class ChangeTrafficState extends TimerTask {


        public void run() {
            switch (currentState) {
                case 0:
                    texture = textureRed;
                    currentState++;
                    return;
                case 1:
                    texture = textureGreen1;
                    currentState++;
                    return;
                case 2:
                    texture = textureGreen2;
                    currentState++;
                    return;
                case 3:
                    texture = textureGreen3;
                    currentState++;
                    onTrafficLightListener.onStartTraffic();
                    this.cancel();
                    isWork = false;
                    return;
            }

        }
    }

    public void setState(int state) {
        switch (state) {
            case green1:
                texture = textureGreen1;
                break;
            case green2:
                texture = textureGreen2;
                break;
            case green3:
                texture = textureGreen3;
                break;
            case red:
                texture = textureRed;
                break;
        }

    }

}
