package com.nicholaschirkevich.game.userdata;

/**
 * Created by Nikolas on 16.03.2016.
 */
public class CoinDataType extends UserData {
    private boolean isRecievedByMycar = false;
    public CoinDataType() {
        userDataType=UserDataType.COIN;
    }

    public boolean isRecievedByMycar() {
        return isRecievedByMycar;
    }

    public void setIsRecievedByMycar(boolean isRecievedByMycar) {
        this.isRecievedByMycar = isRecievedByMycar;
    }
}
