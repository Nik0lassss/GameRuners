package com.nicholaschirkevich.game.entity;

/**
 * Created by Nikolas on 24.06.2016.
 */
public class VkUser {

    private String id,image_src,user_last_name, user_first_name,highscore;

    public VkUser(String id, String image_src) {
        this.id = id;
        this.image_src = image_src;

    }

    public VkUser(String id, String image_src, String user_last_name, String user_first_name) {
        this.id = id;
        this.image_src = image_src;
        this.user_last_name = user_last_name;
        this.user_first_name = user_first_name;
    }

    public VkUser(String id, String image_src, String user_last_name, String user_first_name, String highscore) {
        this.id = id;
        this.image_src = image_src;
        this.user_last_name = user_last_name;
        this.user_first_name = user_first_name;
        this.highscore = highscore;
    }

    public String getUser_last_name() {
        return user_last_name;
    }

    public void setUser_last_name(String user_last_name) {
        this.user_last_name = user_last_name;
    }





    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage_src() {
        return image_src;
    }

    public void setImage_src(String image_src) {
        this.image_src = image_src;
    }

    public String getUser_first_name() {
        return user_first_name;
    }

    public void setUser_first_name(String user_first_name) {
        this.user_first_name = user_first_name;
    }

    public String getHighscore() {
        return highscore;
    }

    public void setHighscore(String highscore) {
        this.highscore = highscore;
    }
}
