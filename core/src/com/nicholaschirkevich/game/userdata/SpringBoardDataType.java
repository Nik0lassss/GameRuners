package com.nicholaschirkevich.game.userdata;

/**
 * Created by Nikolas on 16.03.2016.
 */
public class SpringBoardDataType extends UserData {
    private boolean isRecievedByMycar = false;

    public SpringBoardDataType() {
        userDataType = UserDataType.SPRING_ON_ROAD;
    }

    public boolean isRecievedByMycar() {
        return isRecievedByMycar;
    }

    public void setIsRecievedByMycar(boolean isRecievedByMycar) {
        this.isRecievedByMycar = isRecievedByMycar;
    }
}
