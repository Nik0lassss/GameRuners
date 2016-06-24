package com.nicholaschirkevich.game.entity;

/**
 * Created by Nikolas on 23.06.2016.
 */
public class LeaderboardEntity {
    private String name,vk_id,fb_id,highscore,img_src;

    public LeaderboardEntity(String name, String vk_id, String fb_id, String highscore) {
        this.name = name;
        this.vk_id = vk_id;
        this.fb_id = fb_id;
        this.highscore = highscore;
    }

    public LeaderboardEntity(String name, String vk_id, String fb_id, String highscore, String img_src) {
        this.name = name;
        this.vk_id = vk_id;
        this.fb_id = fb_id;
        this.highscore = highscore;
        this.img_src = img_src;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVk_id() {
        return vk_id;
    }

    public void setVk_id(String vk_id) {
        this.vk_id = vk_id;
    }

    public String getFb_id() {
        return fb_id;
    }

    public void setFb_id(String fb_id) {
        this.fb_id = fb_id;
    }

    public String getHighscore() {
        return highscore;
    }

    public void setHighscore(String highscore) {
        this.highscore = highscore;
    }


    public String getImg_src() {
        return img_src;
    }

    public void setImg_src(String img_src) {
        this.img_src = img_src;
    }
}
