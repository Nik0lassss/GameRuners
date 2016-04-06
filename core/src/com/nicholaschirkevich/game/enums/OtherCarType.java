package com.nicholaschirkevich.game.enums;

import com.nicholaschirkevich.game.util.Constants;


/**
 * Created by Nikolas on 02.03.2016.
 */
public enum OtherCarType {

    OTHER_CAR_1_1(Constants.OTHERCAR_1_1_ASSETS_ID),
    OTHER_CAR_1_2(Constants.OTHERCAR_1_2_ASSETS_ID),
    OTHER_CAR_1_3(Constants.OTHERCAR_1_3_ASSETS_ID),
    OTHER_CAR_2_1(Constants.OTHERCAR_2_1_ASSETS_ID),
    OTHER_CAR_2_2(Constants.OTHERCAR_2_2_ASSETS_ID),
    OTHER_CAR_2_3(Constants.OTHERCAR_2_3_ASSETS_ID),
    OTHER_CAR_3_1(Constants.OTHERCAR_3_1_ASSETS_ID),
    OTHER_CAR_3_3(Constants.OTHERCAR_3_3_ASSETS_ID),
    OTHER_CAR_4_1(Constants.OTHERCAR_4_1_ASSETS_ID),
    OTHER_CAR_4_2(Constants.OTHERCAR_4_2_ASSETS_ID),
    OTHER_CAR_4_3(Constants.OTHERCAR_4_3_ASSETS_ID),
    OTHER_CAR_5_1(Constants.OTHERCAR_5_1_ASSETS_ID),
    OTHER_CAR_5_2(Constants.OTHERCAR_5_2_ASSETS_ID),
    OTHER_CAR_5_3(Constants.OTHERCAR_5_3_ASSETS_ID);





    private String key;


    OtherCarType(String key) {
       this.key =key;
    }


    public String getKey() {
        return key;
    }
}
