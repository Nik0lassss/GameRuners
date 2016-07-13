package com.nicholaschirkevich.game.enums;

import com.badlogic.gdx.math.Vector3;
import com.nicholaschirkevich.game.util.Constants;
import com.nicholaschirkevich.game.util.RandomUtil;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Nikolas on 03.07.2016.
 */
public enum SideObjectType {
    ROAD_1_BUSH_1_ID_LEFT(Constants.ROAD_1_BUSH_1_LEFT_ID, 20, 20, new Vector3(-10, 1200, 0), new Vector3(-10, 1200, 0),160),
    ROAD_1_BUSH_2_ID_LEFT(Constants.ROAD_1_BUSH_2_LEFT_ID, 20, 20, new Vector3(-10, 1200, 0), new Vector3(-10, 1200, 0),160),
    ROAD_1_BUSH_1_ID_RIGHT(Constants.ROAD_1_BUSH_1_RIGHT_ID, 20, 20, new Vector3(270, 1200, 0), new Vector3(270, 1200, 0),160),
    ROAD_1_BUSH_2_ID_RIGHT(Constants.ROAD_1_BUSH_2_RIGHT_ID, 20, 20, new Vector3(270, 1200, 0), new Vector3(270, 1200, 0),160),
    ROAD_1_TILE_ID(Constants.ROAD_1_TILE_ID, 10, 10, new Vector3(20, 800, 0), new Vector3(20, 800, 0),160),
    ROAD_1_LIGHTER_L_ID(Constants.ROAD_1_LIGHTER_L_ID, 0, 0, new Vector3(-30, 1200, 0), new Vector3(-30, 1200, 0),160),
    ROAD_1_LIGHTER_R_ID(Constants.ROAD_1_LIGHTER_R_ID, 0, 0, new Vector3(210, 1200, 0), new Vector3(210, 1200, 0),160),
    ROAD_1_STUMP_ID_LEFT(Constants.ROAD_1_STUMP_LEFT_ID, 20, 20, new Vector3(-10, 1200, 0), new Vector3(-10, 1200, 0),160),
    ROAD_1_STUMP_ID_RIGHT(Constants.ROAD_1_STUMP_RIGHT_ID, 20, 20, new Vector3(270, 1200, 0), new Vector3(270, 1200, 0),160),
    ROAD_1_TREE_1_ID_LEFT(Constants.ROAD_1_TREE_1_LEFT_ID, 20, 20, new Vector3(-10, 1200, 0), new Vector3(-10, 1200, 0),160),
    ROAD_1_TREE_1_ID_RIGHT(Constants.ROAD_1_TREE_1_RIGHT_ID, 20, 20, new Vector3(270, 1200, 0), new Vector3(270, 1200, 0),160),


    ROAD_2_BOARD_LEFT_ID(Constants.ROAD_2_BOARD_LEFT_ID, 10, 10, new Vector3(0, 1200, 0), new Vector3(0, 1200, 0),60),
    ROAD_2_BOARD_RIGHT_ID(Constants.ROAD_2_BOARD_RIGHT_ID, 10, 10, new Vector3(0, 1200, 0), new Vector3(0, 1200, 0),60),
    ROAD_2_CACTUS_LEFT_1_ID(Constants.ROAD_2_CACTUS_LEFT_1_ID, 10, 10, new Vector3(0, 1200, 0), new Vector3(0, 1200, 0),60),
    ROAD_2_CACTUS_RIGHT_1_ID(Constants.ROAD_2_CACTUS_RIGHT_1_ID, 10, 10, new Vector3(260, 1200, 0), new Vector3(260, 1200, 0),60),
    ROAD_2_CACTUS_LEFT_2_ID(Constants.ROAD_2_CACTUS_LEFT_2_ID, 10, 10, new Vector3(0, 1200, 0), new Vector3(0, 1200, 0),60),
    ROAD_2_CACTUS_RIGHT_2_ID(Constants.ROAD_2_CACTUS_RIGHT_2_ID, 10, 10, new Vector3(260, 1200, 0), new Vector3(260, 1200, 0),60),
    ROAD_2_MAN_LEFT_ID(Constants.ROAD_2_MAN_LEFT_ID, 10, 10, new Vector3(0, 1200, 0), new Vector3(0, 1200, 0),60),
    ROAD_2_MAN_RIGHT_ID(Constants.ROAD_2_MAN_RIGHT_ID, 10, 10, new Vector3(260, 1200, 0), new Vector3(260, 1200, 0),60),
    ROAD_2_STONE_LEFT_ID(Constants.ROAD_2_STONE_LEFT_ID, 10, 10, new Vector3(0, 1200, 0), new Vector3(0, 1200, 0),60),
    ROAD_2_STONE_RIGHT_ID(Constants.ROAD_2_STONE_RIGHT_ID, 10, 10, new Vector3(260, 1200, 0), new Vector3(260, 1200, 0),60),
    ROAD_2_TIRES_LEFT_ID(Constants.ROAD_2_TIRES_LEFT_ID, 10, 10, new Vector3(0, 1200, 0), new Vector3(0, 1200, 0),60),
    ROAD_2_TIRES_RIGHT_ID(Constants.ROAD_2_TIRES_RIGHT_ID, 10, 10, new Vector3(260, 1200, 0), new Vector3(260, 1200, 0),60),


    ROAD_3_TILE_ID(Constants.ROAD_3_TILE_ID, 10, 10, new Vector3(20, 1200, 0), new Vector3(20, 1200, 0),60),
    ROAD_3_SNOW_1_ID_LEFT(Constants.ROAD_3_SNOW_1_ID, 10, 10, new Vector3(0, 1200, 0), new Vector3(0, 1200, 0),160),
    ROAD_3_SNOW_2_ID_LEFT(Constants.ROAD_3_SNOW_2_ID, 10, 10, new Vector3(0, 1200, 0), new Vector3(0, 1200, 0),160),
    ROAD_3_SNOWMAN_ID_LEFT(Constants.ROAD_3_SNOWMAN_ID, 10, 10, new Vector3(0, 1200, 0), new Vector3(0, 1200, 0),160),
    ROAD_3_TREE_1_ID_LEFT(Constants.ROAD_3_TREE_1_ID, 10, 10, new Vector3(0, 1200, 0), new Vector3(0, 1200, 0),160),
    ROAD_3_TREE_2_ID_LEFT(Constants.ROAD_3_TREE_2_ID, 10, 10, new Vector3(0, 1200, 0), new Vector3(0, 1200, 0),160),
    ROAD_3_TREE_3_ID_LEFT(Constants.ROAD_3_TREE_3_ID, 10, 10, new Vector3(0, 1200, 0), new Vector3(0, 1200, 0),160),

    ROAD_3_SNOW_1_ID_RIGHT(Constants.ROAD_3_SNOW_1_ID, 10, 10, new Vector3(260, 1200, 0), new Vector3(260, 1200, 0),160),
    ROAD_3_SNOW_2_ID_RIGHT(Constants.ROAD_3_SNOW_2_ID, 10, 10, new Vector3(260, 1200, 0), new Vector3(260, 1200, 0),160),
    ROAD_3_SNOWMAN_ID_RIGHT(Constants.ROAD_3_SNOWMAN_ID, 10, 10, new Vector3(260, 1200, 0), new Vector3(260, 1200, 0),160),
    ROAD_3_TREE_1_ID_RIGHT(Constants.ROAD_3_TREE_1_ID, 10, 10, new Vector3(260, 1200, 0), new Vector3(260, 1200, 0),160),
    ROAD_3_TREE_2_ID_RIGHT(Constants.ROAD_3_TREE_2_ID, 10, 10, new Vector3(260, 1200, 0), new Vector3(260, 1200, 0),160),
    ROAD_3_TREE_3_ID_RIGHT(Constants.ROAD_3_TREE_3_ID, 10, 10, new Vector3(260, 1200, 0), new Vector3(260, 1200, 0),160),

    ROAD_5_LIFEBUOY_ID_LEFT(Constants.ROAD_5_LIFEBUOY_LEFT_ID, 10, 10, new Vector3(0, 1200, 0), new Vector3(0, 1200, 0),160),
    ROAD_5_PALM_ID_LEFT(Constants.ROAD_5_PALM_LEFT_ID, 10, 10, new Vector3(0, 1200, 0), new Vector3(0, 1200, 0),160),
    ROAD_5_SHELL_ID_LEFT(Constants.ROAD_5_SHELL_LEFT_ID, 10, 10, new Vector3(0, 800, 0), new Vector3(0, 1200, 0),160),
    ROAD_5_LIFEBUOY_ID_RIGHT(Constants.ROAD_5_LIFEBUOY_RIGHT_ID, 10, 10, new Vector3(260, 1200, 0), new Vector3(260, 1200, 0),160),
    ROAD_5_PALM_ID_RIGHT(Constants.ROAD_5_PALM_RIGHT_ID, 5, 5, new Vector3(240, 1200, 0), new Vector3(240, 1200, 0),160),
    ROAD_5_SHELL_ID_RIGHT(Constants.ROAD_5_SHELL_RIGHT_ID, 10, 10, new Vector3(260, 1200, 0), new Vector3(260, 1200, 0),160),




    ROAD_6_ROCK_1_LEFT_ID(Constants.ROAD_6_ROCK_1_LEFT_ID, 10, 10, new Vector3(-150, 1200, 0), new Vector3(-150, 1200, 0),160),
    ROAD_6_ROCK_1_RIGHT_ID(Constants.ROAD_6_ROCK_1_RIGHT_ID, 10, 10, new Vector3(260, 1200, 0), new Vector3(260, 1200, 0),160),
    ROAD_6_ROCK_2_LEFT_ID(Constants.ROAD_6_ROCK_2_LEFT_ID, 10, 10, new Vector3(0, 1200, 0), new Vector3(0, 1200, 0),160),
    ROAD_6_ROCK_2_RIGHT_ID(Constants.ROAD_6_ROCK_2_RIGHT_ID, 10, 10, new Vector3(260, 1200, 0), new Vector3(260, 1200, 0),160),
    ROAD_6_TREE_1_LEFT_ID(Constants.ROAD_6_TREE_1_LEFT_ID, 10, 10, new Vector3(0, 1200, 0), new Vector3(0, 1200, 0),160),
    ROAD_6_TREE_1_RIGHT_ID(Constants.ROAD_6_TREE_1_RIGHT_ID, 10, 10, new Vector3(260, 1200, 0), new Vector3(260, 1200, 0),160),
    ROAD_6_TREE_2_LEFT_ID(Constants.ROAD_6_TREE_2_LEFT_ID, 10, 10, new Vector3(0, 1200, 0), new Vector3(0, 1200, 0),160),
    ROAD_6_TREE_2_RIGHT_ID(Constants.ROAD_6_TREE_2_RIGHT_ID, 10, 10, new Vector3(260, 1200, 0), new Vector3(260, 1200, 0),160),
    ROAD_6_TREE_3_LEFT_ID(Constants.ROAD_6_TREE_3_LEFT_ID, 10, 10, new Vector3(0, 1200, 0), new Vector3(0, 1200, 0),160),
    ROAD_6_TREE_3_RIGHT_ID(Constants.ROAD_6_TREE_3_RIGHT_ID, 10, 10, new Vector3(260, 1200, 0), new Vector3(260, 1200, 0),160),





    ROAD_7_BANNER_LEFT_ID(Constants.ROAD_7_BANNER_LEFT_ID, 10, 10, new Vector3(0, 1200, 0), new Vector3(0, 1200, 0),60),
    ROAD_7_BANNER_RIGHT_ID(Constants.ROAD_7_BANNER_RIGHT_ID, 10, 10, new Vector3(270, 1200, 0), new Vector3(270, 1200, 0),60),
    ROAD_7_BENCH_LEFT_ID(Constants.ROAD_7_BENCH_LEFT_ID, 10, 10, new Vector3(0, 1200, 0), new Vector3(0, 800, 0),60),
    ROAD_7_BENCH_RIGHT_ID(Constants.ROAD_7_BENCH_RIGHT_ID, 10, 10, new Vector3(270, 1200, 0), new Vector3(270, 1200, 0),60),
    ROAD_7_HOUSE_LEFT_ID(Constants.ROAD_7_HOUSE_LEFT_ID, 10, 10, new Vector3(-160, 1200, 0), new Vector3(-160, 1200, 0),60),
    ROAD_7_HOUSE_RIGHT_ID(Constants.ROAD_7_HOUSE_RIGHT_ID, 10, 10, new Vector3(280, 1200, 0), new Vector3(280, 1200, 0),60),
    ROAD_7_LANTERNS_ID(Constants.ROAD_7_LANTERNS_ID, 10, 10, new Vector3(15, 1200, 0), new Vector3(0, 1200, 0),60),
    ROAD_7_LAMP_LEFT_ID(Constants.ROAD_7_LAMP_LEFT_ID, 0,0, new Vector3(40, 1200, 0), new Vector3(40, 1200, 0),60),
    ROAD_7_LAMP_RIGHT_ID(Constants.ROAD_7_LAMP_RIGHT_ID, 0, 0, new Vector3(240, 1200, 0), new Vector3(240, 1200, 0),60),
    ROAD_7_BANNER_LEFT_FOR_APPEND_1_ID(Constants.ROAD_7_BANNER_LEFT_ID, 0, 0,-20,20),
    ROAD_7_BENCH_BANNER_LEFT(Constants.ROAD_7_BENCH_LEFT_ID,5,5, new Vector3(0, 1200, 0), new Vector3(20, 1200, 0),new ArrayList<SideObjectType>(Arrays.asList(SideObjectType.ROAD_7_BANNER_LEFT_FOR_APPEND_1_ID)),70),
    ROAD_7_BANNER_RIGHT_FOR_APPEND_1_ID(Constants.ROAD_7_BANNER_RIGHT_ID, 0, 0,50,20),
    ROAD_7_BENCH_BANNER_RIGHT(Constants.ROAD_7_BENCH_RIGHT_ID,5,5, new Vector3(280, 1200, 0), new Vector3(280, 1200, 0),new ArrayList<SideObjectType>(Arrays.asList(SideObjectType.ROAD_7_BANNER_RIGHT_FOR_APPEND_1_ID)),70),
    ROAD_7_LANTERNS_HOUSE_LEFT_ID(Constants.ROAD_7_LANTERNS_ID, 10, 10, 170,80),
    ROAD_7_HOUSE_RIGHT_TO_LANTERS_ID(Constants.ROAD_7_HOUSE_RIGHT_ID, 10, 10, 420,0),
    ROAD_7_LANTERNS_HOUSE_LEFT_AND_RIGHT(Constants.ROAD_7_HOUSE_LEFT_ID,5,5,new Vector3(-160,1200,0),new Vector3(-160,1200,0),new ArrayList<SideObjectType>(Arrays.asList(ROAD_7_LANTERNS_HOUSE_LEFT_ID,ROAD_7_HOUSE_RIGHT_TO_LANTERS_ID)),70);



    private String key;
    private int deltaX, deltaY;
    private Vector3 posLeft, posRight;
    private boolean isStaticObject;
    private boolean addToTop = false;


    private float distance  = 50;

    public boolean isMovible() {
        return movible;
    }
    public void setDistance(float distance) {
        this.distance = distance;
    }

    public void setMovible(boolean movible) {
        this.movible = movible;
    }

    private boolean movible = true;



    public boolean isAddToTop() {
        return addToTop;
    }

    public void setAddToTop(boolean addToTop) {
        this.addToTop = addToTop;
    }


    public float getAppendX() {
        return appendX;
    }

    public void setAppendX(float appendX) {
        this.appendX = appendX;
    }

    public float getAppendY() {
        return appendY;
    }

    public void setAppendY(float appendY) {
        this.appendY = appendY;
    }

    private float appendX,appendY;


    private ArrayList<SideObjectType> appendSideTypeObject = new ArrayList<SideObjectType>();

    public boolean isLeft() {
        return isLeft;
    }

    public void setIsLeft(boolean isLeft) {
        this.isLeft = isLeft;
    }

    private boolean isLeft = false;

    public float getDistance() {
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
    SideObjectType(String key, int deltaX, int deltaY,float appendX, float appendY) {
        this.key = key;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.appendX = appendX;
        this.appendY = appendY;

    }

    SideObjectType(String key, int deltaX, int deltaY, Vector3 posLeft, Vector3 posRight, float distance) {
        this.distance = distance;
        this.key = key;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.posLeft = new Vector3(posLeft.x+RandomUtil.getRand(0,deltaX),posLeft.y+RandomUtil.getRand(0,deltaY),0);
        this.posRight = new Vector3(posRight.x+RandomUtil.getRand(0,deltaX),posRight.y+RandomUtil.getRand(0,deltaY),0);
    }
    SideObjectType(String key, int deltaX, int deltaY, Vector3 posLeft, Vector3 posRight,ArrayList<SideObjectType> appendSideTypeObjectArray,float distance) {
        this.distance = distance;
        this.key = key;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.appendX = appendX;
        this.appendY = appendY;
        this.posLeft = new Vector3(posLeft.x+RandomUtil.getRand(0,deltaX),posLeft.y+RandomUtil.getRand(0,deltaY),0);
        this.posRight = new Vector3(posRight.x+RandomUtil.getRand(0,deltaX),posRight.y+RandomUtil.getRand(0,deltaY),0);
        this.appendSideTypeObject = appendSideTypeObjectArray;
    }
    public void appendObject(SideObjectType object) {
        appendSideTypeObject.add(object);
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

    public ArrayList<SideObjectType> getAppendSideTypeObject() {
        return appendSideTypeObject;
    }

    public void setPosLeft(Vector3 posLeft) {
        this.posLeft = posLeft;
    }

    public void setPosRight(Vector3 posRight) {
        this.posRight = posRight;
    }
}
