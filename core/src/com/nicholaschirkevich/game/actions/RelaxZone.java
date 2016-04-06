package com.nicholaschirkevich.game.actions;

import com.nicholaschirkevich.game.interfaces.OnStartRelaxZone;
import com.nicholaschirkevich.game.model.Springboard;

import java.util.ArrayList;

/**
 * Created by Nikolas on 17.03.2016.
 */
public class RelaxZone {
    private static OnStartRelaxZone onStartRelaxZone;
    private static float time;
    private static float timerSpringBoard;
    private static float timeToRelaxZone = 50;
    private static float timeToRSpringBoard = 50;


    public static void resetTime() {
        time = 0;timerSpringBoard=0;
    }

    public static void update(float dt)
    {
        time+=dt;
        timerSpringBoard +=dt;
        if(time>timeToRelaxZone)
        {
            System.out.println("RelaxZone");
            onStartRelaxZone.onStartRelaxZone(true,dt);
        }
        if (time>timeToRelaxZone+5)
        {
            onStartRelaxZone.onStartRelaxZone(false,dt);
            time=0;
        }
        if(timerSpringBoard>timeToRSpringBoard)
        {
            onStartRelaxZone.onSetSpringBoar();
            timerSpringBoard=0;
        }
    }

    public OnStartRelaxZone getOnStartRelaxZone() {
        return onStartRelaxZone;
    }

    public static void setOnStartRelaxZone(OnStartRelaxZone onStartRelax) {
        onStartRelaxZone = onStartRelax;
    }
}
