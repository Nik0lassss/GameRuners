package com.nicholaschirkevich.game.actions;

import com.nicholaschirkevich.game.model.MyCar;
import com.nicholaschirkevich.game.model.PasserCar;

/**
 * Created by Nikolas on 04.04.2016.
 */
public class Landing {

    private MyCar myCar;
    private PasserCar passerCar;

    public Landing( MyCar myCar, PasserCar passerCar) {
        this.myCar = myCar;
        this.passerCar = passerCar;
    }
    public boolean isLanding()
    {

        if(myCar.getY()>passerCar.getY()+passerCar.getCarTexture().getRegionHeight()+5)
        {
            return true;
        }
        return false;
    }
}
