package com.nicholaschirkevich.game.util;

import com.badlogic.gdx.physics.box2d.Body;
import com.nicholaschirkevich.game.userdata.UserData;
import com.nicholaschirkevich.game.userdata.UserDataType;

/**
 * Created by Nikolas on 16.03.2016.
 */
public class BodyUtils {
    public static boolean bodyIsCoin(Body body) {
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getUserDataType() == UserDataType.COIN;
    }

    public static boolean bodyIsBooster(Body body) {
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getUserDataType() == UserDataType.BOOSTER;
    }
    public static boolean bodyIsLadle(Body body) {
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getUserDataType() == UserDataType.LADLE_ON_ROAD;
    }

    public static boolean bodyIsSkull(Body body) {
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getUserDataType() == UserDataType.SKULL_ON_ROAD;
    }
    public static boolean bodyIsMyCar(Body body) {
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getUserDataType() == UserDataType.MY_CAR;
    }
    public static boolean bodyIsPasserCar(Body body) {
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getUserDataType() == UserDataType.PASSER_CAR;
    }
}
