package com.nicholaschirkevich.game.mappers;

import com.nicholaschirkevich.game.entity.LeaderboardEntity;

import org.json.JSONObject;

/**
 * Created by Nikolas on 23.06.2016.
 */
public class Mapper {
    public static LeaderboardEntity jsonToLeaderBoardEntity(JSONObject jsonObject) {
        final String name = jsonObject.optString("name", "");
        final String vk_id = jsonObject.optString("vk_id","");
        final String fb_id = jsonObject.optString("fb_id","");
        final String highscore = jsonObject.optString("highscore","");
        return new LeaderboardEntity(name,vk_id,fb_id,highscore);
    }
}
