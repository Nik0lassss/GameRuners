package com.nicholaschirkevich.game.model;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

/**
 * Created by Nikolas on 24.02.2016.
 */
public class CacheCar {

    public static Texture MyCarTexture = null;
    public static HashMap<String, Car> textureHashMap = new HashMap<String, Car>();
    public static Texture OpponentCarTexture = null;


    public static Car getCar(String key) {
        return textureHashMap.get(key);
    }

    public static void setCar(Car car, String key) {
        textureHashMap.put(key, car);
    }

    public static Texture getMyCarTexture() {
        return MyCarTexture;
    }

    public static void setMyCarTexture(Texture myCarTexture) {
        MyCarTexture = myCarTexture;
    }

    public static HashMap<String, Car> getTextureHashMap() {
        return textureHashMap;
    }

    public static void setTextureHashMap(HashMap<String, Car> textureHashMap) {
        CacheCar.textureHashMap = textureHashMap;
    }

    public static Texture getOpponentCarTexture() {
        return OpponentCarTexture;
    }

    public static void setOpponentCarTexture(Texture opponentCarTexture) {
        OpponentCarTexture = opponentCarTexture;
    }
}
