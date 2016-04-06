package com.nicholaschirkevich.game.userdata;

/**
 * Created by Nikolas on 16.03.2016.
 */
public class SkullOnRoadDataType extends UserData {
    private boolean isRecievedByMycar = false;
    public SkullOnRoadDataType() {
        userDataType=UserDataType.SKULL_ON_ROAD;
    }

    public boolean isRecievedByMycar() {
        return isRecievedByMycar;
    }

    public void setIsRecievedByMycar(boolean isRecievedByMycar) {
        this.isRecievedByMycar = isRecievedByMycar;
    }
}
