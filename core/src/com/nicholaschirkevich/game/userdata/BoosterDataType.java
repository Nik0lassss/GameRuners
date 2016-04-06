package com.nicholaschirkevich.game.userdata;

/**
 * Created by Nikolas on 16.03.2016.
 */
public class BoosterDataType extends UserData {
    private boolean isRecievedByMycar = false;
    public BoosterDataType() {
        userDataType=UserDataType.BOOSTER;
    }

    public boolean isRecievedByMycar() {
        return isRecievedByMycar;
    }

    public void setIsRecievedByMycar(boolean isRecievedByMycar) {
        this.isRecievedByMycar = isRecievedByMycar;
    }
}
