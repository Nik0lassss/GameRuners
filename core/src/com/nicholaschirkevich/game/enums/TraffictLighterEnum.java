package com.nicholaschirkevich.game.enums;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.nicholaschirkevich.game.util.AssetsManager;
import com.nicholaschirkevich.game.util.Constants;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Nikolas on 03.07.2016.
 */
public enum TraffictLighterEnum {
    TRAFFICT_LIGHTER_COUNTYSIDE(new ArrayList<Texture>(Arrays.asList(AssetsManager.getTextureRegion(Constants.START_LIGHT_RED_ID).getTexture(), (AssetsManager.getTextureRegion(Constants.START_LIGHT_1_ID).getTexture()), AssetsManager.getTextureRegion(Constants.START_LIGHT_2_ID).getTexture(), AssetsManager.getTextureRegion(Constants.START_LIGHT_3_ID).getTexture())), AssetsManager.getTextureRegion(Constants.START_LINE_ID).getTexture(),new Vector3(39, 350,0),new Vector3(67, 350,0), Constants.ROAD_1_START_LIGHTER),
    TRAFFICT_LIGHTER_MEXICO(new ArrayList<Texture>(Arrays.asList(AssetsManager.getTextureRegion(Constants.ROAD_2_START_BANNER_ID).getTexture())), AssetsManager.getTextureRegion(Constants.ROAD_2_START_LINE_ID).getTexture(),new Vector3(40, 350,0),new Vector3(67, 350,0), Constants.ROAD_2_START_LIGHTER),
    TRAFFICT_LIGHTER_SNOWLAND(new ArrayList<Texture>(Arrays.asList(AssetsManager.getTextureRegion(Constants.ROAD_3_START_LIGHTS_RED_GREEN_ID).getTexture(),AssetsManager.getTextureRegion(Constants.ROAD_3_START_LIGHTS_1_GREEN_ID).getTexture(),AssetsManager.getTextureRegion(Constants.ROAD_3_START_LIGHTS_2GREEN_GREEN_ID).getTexture(),AssetsManager.getTextureRegion(Constants.ROAD_3_START_LIGHTS_3GREEN_GREEN_ID).getTexture())), AssetsManager.getTextureRegion(Constants.ROAD_3_START_LINE_ID).getTexture(),new Vector3(40, 350,0),new Vector3(67, 350,0), Constants.ROAD_3_START_LIGHTER),
    TRAFFICT_LIGHTER_BEACH(new ArrayList<Texture>(Arrays.asList(AssetsManager.getTextureRegion(Constants.ROAD_5_START_BANNER_ID).getTexture())), AssetsManager.getTextureRegion(Constants.ROAD_5_START_LINE_ID).getTexture(),new Vector3(40, 350,0),new Vector3(67, 350,0), Constants.ROAD_5_START_LIGHTER),
    TRAFFICT_LIGHTER_CHINATOWN(new ArrayList<Texture>(Arrays.asList(AssetsManager.getTextureRegion(Constants.ROAD_7_START_GATE_ID).getTexture())), AssetsManager.getTextureRegion(Constants.START_LINE_ID).getTexture(),new Vector3(37, 350,0),new Vector3(67, 350,0), Constants.ROAD_7_START_LIGHTER),
    TRAFFICT_LIGHTER_CANYON(new ArrayList<Texture>(Arrays.asList(AssetsManager.getTextureRegion(Constants.ROAD_6_START_LINE_ID).getTexture())), AssetsManager.getTextureRegion(Constants.START_LINE_ID).getTexture(),new Vector3(37, 350,0),new Vector3(67, 350,0), Constants.ROAD_6_START_LIGHTER),;
    private ArrayList<Texture> textures;
    private  Vector3 position;

    public Vector3 getPositionStartLine() {
        return positionStartLine;
    }

    private Vector3 positionStartLine;

    public Texture getStart_line() {
        return start_line;
    }

    private Texture start_line;

    private String id;

    TraffictLighterEnum(ArrayList<Texture> textures, Texture start_line, Vector3 position, Vector3 positionStartLine, String id) {
        this.textures = textures;
        this.start_line = start_line;
        this.id = id;
        this.start_line = start_line;
        this.position = position.cpy();
        this.positionStartLine = positionStartLine.cpy();
    }

    public Texture getTexture(int state) {
        if (textures.size() - 1 < state) {
            if (!textures.isEmpty())
                return textures.get(textures.size() - 1);
        } else if (!textures.isEmpty()) {
            return textures.get(state);
        }
        return null;
    }

    public Vector3 getPosition() {
        return position;
    }
}
