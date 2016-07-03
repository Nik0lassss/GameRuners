package com.nicholaschirkevich.game.enums;

import com.badlogic.gdx.math.Vector3;
import com.nicholaschirkevich.game.util.Constants;

/**
 * Created by Nikolas on 03.07.2016.
 */
public enum SideObjectType {
    ROAD_1_BUSH_1_ID(Constants.ROAD_1_BUSH_1_ID, 10, 10, new Vector3(20, 800, 0), new Vector3(20, 800, 0)),
    ROAD_1_BUSH_2_ID(Constants.ROAD_1_BUSH_2_ID, 10, 10, new Vector3(20, 800, 0), new Vector3(20, 800, 0)),
    ROAD_1_TILE_ID(Constants.ROAD_1_TILE_ID, 10, 10, new Vector3(20, 800, 0), new Vector3(20, 800, 0)),
    ROAD_1_LIGHTER_L_ID(Constants.ROAD_1_LIGHTER_L_ID, 10, 10, new Vector3(20, 800, 0), new Vector3(20, 800, 0)),
    ROAD_1_LIGHTER_R_ID(Constants.ROAD_1_LIGHTER_R_ID, 10, 10, new Vector3(20, 800, 0), new Vector3(20, 800, 0)),
    ROAD_1_STUMP_ID(Constants.ROAD_1_STUMP_ID, 10, 10, new Vector3(20, 800, 0), new Vector3(20, 800, 0)),
    ROAD_1_TREE_1_ID(Constants.ROAD_1_TREE_1_ID, 10, 10, new Vector3(20, 800, 0), new Vector3(20, 800, 0)),
    ROAD_2_BOARD_ID(Constants.ROAD_2_BOARD_ID, 10, 10, new Vector3(20, 800, 0), new Vector3(20, 800, 0)),
    ROAD_2_CACTUS_1_ID(Constants.ROAD_2_CACTUS_1_ID, 10, 10, new Vector3(20, 800, 0), new Vector3(20, 800, 0)),
    ROAD_2_CACTUS_2_ID(Constants.ROAD_2_CACTUS_2_ID, 10, 10, new Vector3(20, 800, 0), new Vector3(20, 800, 0)),
    ROAD_2_MAN_ID(Constants.ROAD_2_MAN_ID, 10, 10, new Vector3(20, 800, 0), new Vector3(20, 800, 0)),
    ROAD_2_STONE_ID(Constants.ROAD_2_STONE_ID, 10, 10, new Vector3(20, 800, 0), new Vector3(20, 800, 0)),
    ROAD_2_TIRES_ID(Constants.ROAD_2_TIRES_ID, 10, 10, new Vector3(20, 800, 0), new Vector3(20, 800, 0)),
    ROAD_3_TILE_ID(Constants.ROAD_3_TILE_ID, 10, 10, new Vector3(20, 800, 0), new Vector3(20, 800, 0)),
    ROAD_3_SNOW_1_ID(Constants.ROAD_3_SNOW_1_ID, 10, 10, new Vector3(20, 800, 0), new Vector3(20, 800, 0)),
    ROAD_3_SNOW_2_ID(Constants.ROAD_3_SNOW_2_ID, 10, 10, new Vector3(20, 800, 0), new Vector3(20, 800, 0)),
    ROAD_3_SNOWMAN_ID(Constants.ROAD_3_SNOWMAN_ID, 10, 10, new Vector3(20, 800, 0), new Vector3(20, 800, 0)),
    ROAD_3_TREE_1_ID(Constants.ROAD_3_TREE_1_ID, 10, 10, new Vector3(20, 800, 0), new Vector3(20, 800, 0)),
    ROAD_3_TREE_2_ID(Constants.ROAD_3_TREE_2_ID, 10, 10, new Vector3(20, 800, 0), new Vector3(20, 800, 0)),
    ROAD_3_TREE_3_ID(Constants.ROAD_3_TREE_3_ID, 10, 10, new Vector3(20, 800, 0), new Vector3(20, 800, 0)),;

    private String key;
    private int deltaX, deltaY;
    private Vector3 posLeft, posRight;
    private boolean isStaticObject;
    private int distance;

    public boolean isLeft() {
        return isLeft;
    }

    public void setIsLeft(boolean isLeft) {
        this.isLeft = isLeft;
    }

    private boolean isLeft = false;

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public boolean isStaticObject() {
        return isStaticObject;
    }

    public void setIsStaticObject(boolean isStaticObject) {
        this.isStaticObject = isStaticObject;
    }


    SideObjectType(String key, int deltaX, int deltaY, Vector3 posLeft, Vector3 posRight) {
        this.key = key;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.posLeft = posLeft;
        this.posRight = posRight;
    }


    public String getKey() {
        return key;
    }

    public int getDeltaX() {
        return deltaX;
    }

    public int getDeltaY() {
        return deltaY;
    }

    public Vector3 getPosLeft() {
        return posLeft;
    }

    public Vector3 getPosRight() {
        return posRight;
    }
}
