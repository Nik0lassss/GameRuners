package com.nicholaschirkevich.game.userdata;

/**
 * Created by Nikolas on 16.03.2016.
 */
public class FlySpringBoardDataType extends UserData {
    private boolean isRecievedByMycar = false;
    public FlySpringBoardDataType() {
        userDataType=UserDataType.FLY_SPRING_BOARD;
    }

    public boolean isRecievedByMycar() {
        return isRecievedByMycar;
    }

    public void setIsRecievedByMycar(boolean isRecievedByMycar) {
        this.isRecievedByMycar = isRecievedByMycar;
    }
}
