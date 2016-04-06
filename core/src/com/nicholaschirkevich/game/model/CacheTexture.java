package com.nicholaschirkevich.game.model;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

/**
 * Created by Nikolas on 24.02.2016.
 */
public class CacheTexture {
    public static HashMap<String,Texture> textureHashMap = new HashMap<String, Texture>();

    public  static Texture getTexture(String key)
    {
        return  textureHashMap.get(key);
    }

    public static void setTexture(Texture texture,String key)
    {
        textureHashMap.put(key,texture);
    }
}
