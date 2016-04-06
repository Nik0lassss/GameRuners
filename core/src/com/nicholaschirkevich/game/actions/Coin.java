package com.nicholaschirkevich.game.actions;

/**
 * Created by Nikolas on 14.03.2016.
 */
public class Coin {

    private float timeDt = 0;
    private float coinTimeToGenerate = 12;

    public void updateCoins(float dt) {
        timeDt += dt;
        if (timeDt > coinTimeToGenerate) {
            timeDt = 0;
        }
    }

}
