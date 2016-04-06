package com.nicholaschirkevich.game.entity;

import java.util.ArrayList;

/**
 * Created by Nikolas on 11.03.2016.
 */
public class TextureSource {
    private ArrayList<String> textureSources;

    public TextureSource() {
        textureSources = new ArrayList<String>();
    }

    public void add(String string) {
        textureSources.add(string);
    }

    public String getTextureName(int i) {
        return textureSources.get(i);
    }

    public String[] getTextureSources() {
        //String [] texture = new String[textureSources.size()];
        return textureSources.toArray(new String[textureSources.size()]);

    }
}
