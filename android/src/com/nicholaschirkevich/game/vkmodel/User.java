package com.nicholaschirkevich.game.vkmodel;

/**
 * Created by Nikolas on 08.06.2016.
 */
public class User {
    private final String name;
    private final int ID;
    private final String photo_100;
    public String getPhoto_100() {
        return photo_100;
    }



    public User(String name, int ID, String photo_100) {
        this.name = name;
        this.ID = ID;

        this.photo_100 = photo_100;
    }


    public String getName() {
        return name;
    }


    public int getID() {
        return ID;
    }
}
