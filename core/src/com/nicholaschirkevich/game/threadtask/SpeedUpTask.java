package com.nicholaschirkevich.game.threadtask;

import com.nicholaschirkevich.game.interfaces.OnGearUp;
import com.nicholaschirkevich.game.util.Constants;

import java.util.TimerTask;

/**
 * Created by Nikolas on 03.03.2016.
 */
public class SpeedUpTask extends TimerTask {
    OnGearUp onGearUpInterfcae;

    public SpeedUpTask(OnGearUp onGearUpInterface) {
        this.onGearUpInterfcae = onGearUpInterface;
    }

    @Override
    public void run() {
//        Constants.GEAR++;
//        Constants.TO_SPEED = Constants.SPEED + 97;
//        if (null != onGearUpInterfcae) onGearUpInterfcae.gearUp(Constants.GEAR);
//        if(Constants.GEAR==6) this.cancel();
//        Timestamp timestamp = new Timestamp(new Date().getTime());
    }
}
