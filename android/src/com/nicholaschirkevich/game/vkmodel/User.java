package com.nicholaschirkevich.game.vkmodel;

/**
 * Created by Nikolas on 08.06.2016.
 */
public class User {
    private final String name;
    private final int ID;

    public User(String name, int ID) {
        this.name = name;
        this.ID = ID;

    }


    public String getName() {
        return name;
    }


    public int getID() {
        return ID;
    }
}
