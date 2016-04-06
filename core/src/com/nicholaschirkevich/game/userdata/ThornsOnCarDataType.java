package com.nicholaschirkevich.game.userdata;

/**
 * Created by Nikolas on 16.03.2016.
 */
public class ThornsOnCarDataType extends UserData {
    private boolean isCollision = false;
    public ThornsOnCarDataType() {
        userDataType=UserDataType.THORN_ON_CAR;
    }

    public boolean isRecievedByMycar() {
        return isCollision;
    }

    public void setIsRecievedByMycar(boolean isCollision) {
        this.isCollision = isCollision;
    }
}
