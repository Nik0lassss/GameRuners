package com.nicholaschirkevich.game.enums;

import com.nicholaschirkevich.game.util.Constants;


/**
 * Created by Nikolas on 02.03.2016.
 */
public enum BushType {

    ROAD_1_TREE_1(Constants.ROAD_1_TREE_1_STATIC_ASSETS_ID),
    ROAD_1_BUSH_1(Constants.ROAD_1_BUSH_1_STATIC_ASSETS_ID),
    ROAD_1_BUSH_2(Constants.ROAD_1_BUSH_2_STATIC_ASSETS_ID),
    ROAD_1_STUMP_2(Constants.ROAD_1_STUMP_1_STATIC_ASSETS_ID);




    private String key;


    BushType(String key) {
       this.key =key;
    }


    public String getKey() {
        return key;
    }
}
