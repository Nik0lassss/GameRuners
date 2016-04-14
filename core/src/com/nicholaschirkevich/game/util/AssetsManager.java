/*
 * Copyright (c) 2014. William Mora
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nicholaschirkevich.game.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nicholaschirkevich.game.entity.Car;
import com.nicholaschirkevich.game.entity.CarsType;


import java.util.ArrayList;
import java.util.HashMap;

public class AssetsManager {

    private static HashMap<String, TextureRegion> texturesMap = new HashMap<String, TextureRegion>();
    private static HashMap<String, Animation> animationsMap = new HashMap<String, Animation>();

    private static ArrayList<TextureAtlas> carsTextureAtlasList = new ArrayList<TextureAtlas>();


    private static TextureAtlas carTextureAtlas;
    private static TextureAtlas menuBttnTextureAtlas;
    private static TextureAtlas menuGarageButtonTextureAtlas;
    private static TextureAtlas menuBackButtonTextureAtlas;
    private static TextureAtlas pauseBttnTextureAtlas;
    private static TextureAtlas other_car_1_1_texture_atlas;
    private static TextureAtlas other_car_1_2_texture_atlas;
    private static TextureAtlas other_car_1_3_texture_atlas;
    private static TextureAtlas other_car_2_1_texture_atlas;
    private static TextureAtlas other_car_2_2_texture_atlas;
    private static TextureAtlas other_car_2_3_texture_atlas;
    private static TextureAtlas other_car_3_1_texture_atlas;
    private static TextureAtlas other_car_3_3_texture_atlas;
    private static TextureAtlas other_car_4_1_texture_atlas;
    private static TextureAtlas other_car_4_2_texture_atlas;
    private static TextureAtlas other_car_4_3_texture_atlas;
    private static TextureAtlas other_car_5_1_texture_atlas;
    private static TextureAtlas other_car_5_2_texture_atlas;
    private static TextureAtlas other_car_5_3_texture_atlas;
    private static TextureAtlas coin_texture_atlas;
    private static TextureAtlas booster_r_texture_atlas;
    private static TextureAtlas throns_l_texture_atlas;
    private static TextureAtlas throns_r_texture_atlas;
    private static TextureAtlas booster_l_texture_atlas;
    private static TextureAtlas skull_on_road_texture_atlas;
    private static TextureAtlas ladle_on_road_texture_atlas;
    private static TextureAtlas coin_shadow_texture_atlas;
    private static TextureAtlas booster_on_road_texture_atlas;
    private static TextureAtlas ladle_on_car_texture_atlas;
    private static TextureAtlas road_texture_atlas;
    private static TextureAtlas road_1_bush_1_texture_atlas;
    private static TextureAtlas road_1_bush_2_texture_atlas;
    private static TextureAtlas road_1_tree_1_texture_atlas;
    private static TextureAtlas road_1_stump_1_texture_atlas;
    private static TextureAtlas road_1_lighter_l_rexture_atlas;
    private static TextureAtlas road_1_lighter_r_rexture_atlas;
    private static TextureAtlas insane_mode_alpha_left;
    private static TextureAtlas insane_mode_alpha_right;
    private static TextureAtlas road_block;
    private static TextureAtlas springboard;
    private static TextureAtlas dirt;
    private static TextureAtlas dirt_on_screen_1;
    private static TextureAtlas dirt_on_screen_2;
    private static TextureAtlas dirt_on_screen_3;
    private static TextureAtlas dirt_on_screen_4;
    private static TextureAtlas dirt_on_screen_5;
    private static TextureAtlas dirt_on_screen_6;
    private static TextureAtlas effect_boost_texture_atlas;
    private static TextureAtlas fly_springboard;
    private static TextureAtlas left_wing;
    private static TextureAtlas right_wing;
    private static TextureAtlas car_crash_animation;
    private static BitmapFont smallFont;
    private static BitmapFont smallestFont;
    private static BitmapFont largeFont;

    private AssetsManager() {

    }

    public static void loadCars() {
        ArrayList<CarsType> carsTypes = GameManager.getCarsTypes();
        for (int i = 0; i < carsTypes.size(); i++) {
            ArrayList<Car> cars = carsTypes.get(i).getCars();
            for (int z = 0; z < cars.size(); z++) {
                Car car = cars.get(z);
                TextureAtlas carAtlas = new TextureAtlas(car.getNormalTextures().getTextureName(0) + ".txt");
                animationsMap.put(car.getID(), createAnimation(carAtlas,
                        car.getNormalTextures().getTextureSources()));
                carsTextureAtlasList.add(carAtlas);
                texturesMap.put(car.getID(), new TextureRegion(new Texture(Gdx.files.internal(car.getNormalTextures().getTextureName(0) + "_without_shadow.png"))));
                texturesMap.put(car.getCarNameImage(), new TextureRegion(new Texture(Gdx.files.internal(car.getCarNameImage() + ".png"))));
            }
        }
    }

    public static void loadAssets() {

        //Road
        texturesMap.put(Constants.ROAD_ASSETS_ID,
                new TextureRegion(new Texture(Gdx.files.internal(Constants.ROAD_IMAGE_PATH))));

//        texturesMap.put(Constants.MY_CAR_STATIC_ASSETS_ID,
//                new TextureRegion(new Texture(Gdx.files.internal(Constants.MY_CAR_1_IMAGE_PATH))));
        //passer Car 1
        texturesMap.put(Constants.PASSER_CAR_1_ID,
                new TextureRegion(new Texture(Gdx.files.internal(Constants.PASSER_CAR_1_IMAGE_PATH))));
        //passer Car 1
        texturesMap.put(Constants.PASSER_CAR_2_ID,
                new TextureRegion(new Texture(Gdx.files.internal(Constants.PASSER_CAR_2_IMAGE_PATH))));

        //bushs
//        texturesMap.put(Constants.ROAD_1_BUSH_1_ID,
//                new TextureRegion(new Texture(Gdx.files.internal(Constants.ROAD_1_BUSH_1_IMAGE_PATH))));
//
//        texturesMap.put(Constants.ROAD_1_BUSH_2_ID,
//                new TextureRegion(new Texture(Gdx.files.internal(Constants.ROAD_1_BUSH_2_IMAGE_PATH))));
//
//        texturesMap.put(Constants.ROAD_1_TREE_1_ID,
//                new TextureRegion(new Texture(Gdx.files.internal(Constants.ROAD_1_TREE_1_IMAGE_PATH))));

        texturesMap.put(Constants.GEAR_1_ID,
                new TextureRegion(new Texture(Gdx.files.internal(Constants.GEAR_1_IMAGE_PATH))));

        texturesMap.put(Constants.GEAR_2_ID,
                new TextureRegion(new Texture(Gdx.files.internal(Constants.GEAR_2_IMAGE_PATH))));

        texturesMap.put(Constants.GEAR_3_ID,
                new TextureRegion(new Texture(Gdx.files.internal(Constants.GEAR_3_IMAGE_PATH))));

        texturesMap.put(Constants.GEAR_4_ID,
                new TextureRegion(new Texture(Gdx.files.internal(Constants.GEAR_4_IMAGE_PATH))));

        texturesMap.put(Constants.GEAR_5_ID,
                new TextureRegion(new Texture(Gdx.files.internal(Constants.GEAR_5_IMAGE_PATH))));

        texturesMap.put(Constants.GEAR_6_ID,
                new TextureRegion(new Texture(Gdx.files.internal(Constants.GEAR_6_IMAGE_PATH))));
//
//        texturesMap.put(Constants.X1_id,
//                new TextureRegion(new Texture(Gdx.files.internal(Constants.X1_IMAGE_PATH))));
        texturesMap.put(Constants.X2_id,
                new TextureRegion(new Texture(Gdx.files.internal(Constants.X2_IMAGE_PATH))));
        texturesMap.put(Constants.X3_id,
                new TextureRegion(new Texture(Gdx.files.internal(Constants.X3_IMAGE_PATH))));
        texturesMap.put(Constants.X4_id,
                new TextureRegion(new Texture(Gdx.files.internal(Constants.X4_IMAGE_PATH))));
        texturesMap.put(Constants.X5_id,
                new TextureRegion(new Texture(Gdx.files.internal(Constants.X5_IMAGE_PATH))));
        texturesMap.put(Constants.X6_id,
                new TextureRegion(new Texture(Gdx.files.internal(Constants.X6_IMAGE_PATH))));

        carTextureAtlas = new TextureAtlas(Constants.CAR_ATLAS_PATH);

        menuBttnTextureAtlas = new TextureAtlas(Constants.BTTN_RESUME_ATLAS_PATH);
        menuGarageButtonTextureAtlas = new TextureAtlas(Constants.GARAGE_BUTTON_ATLAS_PATH);
        menuBackButtonTextureAtlas = new TextureAtlas(Constants.BACK_BUTTON_ATLAS_PATH);

        ladle_on_car_texture_atlas = new TextureAtlas(Constants.LADLE_ON_CAR_ATLAS_PATH);

        pauseBttnTextureAtlas = new TextureAtlas(Constants.BTTN_PAUSE_ATLAS_PATH);
        coin_shadow_texture_atlas = new TextureAtlas(Constants.COIN_SHADOW_ATLAS_PATH);
        coin_texture_atlas = new TextureAtlas(Constants.COIN_ATLAS_PATH);
        skull_on_road_texture_atlas = new TextureAtlas(Constants.SKULL_ON_ROAD_ATLAS_PATH);
        ladle_on_road_texture_atlas = new TextureAtlas(Constants.LADLE_ON_ROAD_ATLAS_PATH);
        booster_on_road_texture_atlas = new TextureAtlas(Constants.BOOSTER_ON_ROAD_ATLAS_PATH);
        road_texture_atlas = new TextureAtlas(Constants.ROAR_ATLAS_PATH);
        road_1_bush_1_texture_atlas = new TextureAtlas(Constants.ROAR_1_BUSH_1_ATLAS_PATH);
        road_1_bush_2_texture_atlas = new TextureAtlas(Constants.ROAR_1_BUSH_2_ATLAS_PATH);
        road_1_tree_1_texture_atlas = new TextureAtlas(Constants.ROAR_1_TREE_2_ATLAS_PATH);
        road_1_stump_1_texture_atlas = new TextureAtlas(Constants.ROAR_1_STUMP_2_ATLAS_PATH);
        throns_l_texture_atlas = new TextureAtlas(Constants.LEFT_THRONS_ATLAS_PATH);
        throns_r_texture_atlas = new TextureAtlas(Constants.RIGHT_THRONS_ATLAS_PATH);
        booster_l_texture_atlas = new TextureAtlas(Constants.BOOSTER_L_ATLAS_PATH);
        booster_r_texture_atlas = new TextureAtlas(Constants.BOOSTER_R_ATLAS_PATH);
        effect_boost_texture_atlas = new TextureAtlas(Constants.EFFECT_BOOST_ATLAS_PATH);
        insane_mode_alpha_left = new TextureAtlas(Constants.INSANE_MODE_ALPHA_LEFT_ATLAS_PATH);
        insane_mode_alpha_right = new TextureAtlas(Constants.INSANE_MODE_ALPHA_RIGHT_ATLAS_PATH);

        road_1_lighter_l_rexture_atlas = new TextureAtlas(Constants.ROAR_1_LIGHTER_L_ATLAS_PATH);
        road_1_lighter_r_rexture_atlas = new TextureAtlas(Constants.ROAR_1_LIGHTER_R_ATLAS_PATH);

        car_crash_animation = new TextureAtlas(Constants.CRASH_ATLAS_PATH);
        other_car_1_1_texture_atlas = new TextureAtlas(Constants.OTHERCAR_1_1_ATLAS_PATH);
        other_car_1_2_texture_atlas = new TextureAtlas(Constants.OTHERCAR_1_2_ATLAS_PATH);
        other_car_1_3_texture_atlas = new TextureAtlas(Constants.OTHERCAR_1_3_ATLAS_PATH);

        other_car_2_1_texture_atlas = new TextureAtlas(Constants.OTHERCAR_2_1_ATLAS_PATH);
        other_car_2_2_texture_atlas = new TextureAtlas(Constants.OTHERCAR_2_2_ATLAS_PATH);
        other_car_2_3_texture_atlas = new TextureAtlas(Constants.OTHERCAR_2_3_ATLAS_PATH);
        other_car_3_1_texture_atlas = new TextureAtlas(Constants.OTHERCAR_3_1_ATLAS_PATH);
        other_car_3_3_texture_atlas = new TextureAtlas(Constants.OTHERCAR_3_3_ATLAS_PATH);
        other_car_4_1_texture_atlas = new TextureAtlas(Constants.OTHERCAR_4_1_ATLAS_PATH);
        other_car_4_2_texture_atlas = new TextureAtlas(Constants.OTHERCAR_4_2_ATLAS_PATH);
        other_car_4_3_texture_atlas = new TextureAtlas(Constants.OTHERCAR_4_3_ATLAS_PATH);
        other_car_5_1_texture_atlas = new TextureAtlas(Constants.OTHERCAR_5_1_ATLAS_PATH);
        other_car_5_2_texture_atlas = new TextureAtlas(Constants.OTHERCAR_5_2_ATLAS_PATH);
        other_car_5_3_texture_atlas = new TextureAtlas(Constants.OTHERCAR_5_3_ATLAS_PATH);

        left_wing = new TextureAtlas(Constants.LEFT_WING_ATLAS_PATH);
        right_wing = new TextureAtlas(Constants.RIGHT_WING_ATLAS_PATH);
        fly_springboard = new TextureAtlas(Constants.FLY_SPRINGBOARD_ATLAS_PATH);

        dirt = new TextureAtlas(Constants.DIRT_ATLAS_PATH);
        dirt_on_screen_1 = new TextureAtlas(Constants.DIRT_FOR_SREEN_1_ATLAS_PATH);
        dirt_on_screen_2 = new TextureAtlas(Constants.DIRT_FOR_SREEN_2_ATLAS_PATH);
        dirt_on_screen_3 = new TextureAtlas(Constants.DIRT_FOR_SREEN_3_ATLAS_PATH);
        dirt_on_screen_4 = new TextureAtlas(Constants.DIRT_FOR_SREEN_4_ATLAS_PATH);
        dirt_on_screen_5 = new TextureAtlas(Constants.DIRT_FOR_SREEN_5_ATLAS_PATH);
        dirt_on_screen_6 = new TextureAtlas(Constants.DIRT_FOR_SREEN_6_ATLAS_PATH);

        road_block = new TextureAtlas(Constants.ROAD_BLOCK_ATLAS_PATH);
        springboard = new TextureAtlas(Constants.SPRING_BOARD_ATLAS_PATH);

        animationsMap.put(Constants.LADLE_ON_CAR_ASSETS_ID, createAnimation(ladle_on_car_texture_atlas, Constants.LADLE_ON_CAR_REGION_NAMES));
        animationsMap.put(Constants.BOOSTER_ON_ROAD_ASSETS_ID, createAnimation(booster_on_road_texture_atlas, Constants.BOOSTER_ON_ROAD_REGION_NAMES));
        animationsMap.put(Constants.LADLE_ON_ROAD_ASSETS_ID, createAnimation(ladle_on_road_texture_atlas, Constants.LADLE_ON_ROAD_REGION_NAMES));

        animationsMap.put(Constants.MY_CAR_ASSETS_ID, createAnimation(carTextureAtlas,
                Constants.MY_CAR_REGION_NAMES));

        animationsMap.put(Constants.LEFT_THRONS_ASSETS_ID, createAnimation(throns_l_texture_atlas, Constants.LEFT_THRONS_REGION_NAMES));
        animationsMap.put(Constants.RIGHT_THRONS_ASSETS_ID, createAnimation(throns_r_texture_atlas, Constants.RIGHT_THRONS_REGION_NAMES));
        animationsMap.put(Constants.BOOST_L_ASSETS_ID, createAnimation(booster_l_texture_atlas, Constants.BOOSTER_L_REGION_NAMES));
        animationsMap.put(Constants.BOOST_R_ASSETS_ID, createAnimation(booster_r_texture_atlas, Constants.BOOSTER_R_REGION_NAMES));
        animationsMap.put(Constants.SKULL_ON_ROAD_ASSETS_ID, createAnimation(skull_on_road_texture_atlas, Constants.SKULL_ON_ROAD_REGION_NAMES));
        animationsMap.put(Constants.COIN_ASSETS_ID, createAnimation(coin_texture_atlas, Constants.COIN_REGION_NAMES));
        animationsMap.put(Constants.COIN_SHADOW_ASSETS_ID, createAnimation(coin_shadow_texture_atlas, Constants.COIN_SHADOW_REGION_NAMES));

        animationsMap.put(Constants.EFFECT_BOOST_ASSETS_ID, createAnimation(effect_boost_texture_atlas, Constants.EFFECT_BOOST_REGION_NAMES));
        animationsMap.put(Constants.INSANE_MODE_ALPHA_LEFT_ASSETS_ID, createAnimation(insane_mode_alpha_left, Constants.INSANE_MODE_ALPHA_LEFT_REGION_NAMES));
        animationsMap.put(Constants.INSANE_MODE_ALPHA_RIGHT_ASSETS_ID, createAnimation(insane_mode_alpha_right, Constants.INSANE_MODE_ALPHA_RIGHT_REGION_NAMES));

        animationsMap.put(Constants.OTHERCAR_1_1_ASSETS_ID, createAnimation(other_car_1_1_texture_atlas, Constants.OTHERCAR_1_1_REGION_NAMES));
        animationsMap.put(Constants.OTHERCAR_1_2_ASSETS_ID, createAnimation(other_car_1_2_texture_atlas, Constants.OTHERCAR_1_2_REGION_NAMES));
        animationsMap.put(Constants.OTHERCAR_1_3_ASSETS_ID, createAnimation(other_car_1_3_texture_atlas, Constants.OTHERCAR_1_3_REGION_NAMES));
        animationsMap.put(Constants.OTHERCAR_2_1_ASSETS_ID, createAnimation(other_car_2_1_texture_atlas, Constants.OTHERCAR_2_1_REGION_NAMES));
        animationsMap.put(Constants.OTHERCAR_2_2_ASSETS_ID, createAnimation(other_car_2_2_texture_atlas, Constants.OTHERCAR_2_2_REGION_NAMES));
        animationsMap.put(Constants.OTHERCAR_2_3_ASSETS_ID, createAnimation(other_car_2_3_texture_atlas, Constants.OTHERCAR_2_3_REGION_NAMES));
        animationsMap.put(Constants.OTHERCAR_3_1_ASSETS_ID, createAnimation(other_car_3_1_texture_atlas, Constants.OTHERCAR_3_1_REGION_NAMES));
        animationsMap.put(Constants.OTHERCAR_3_3_ASSETS_ID, createAnimation(other_car_3_3_texture_atlas, Constants.OTHERCAR_3_3_REGION_NAMES));
        animationsMap.put(Constants.OTHERCAR_4_1_ASSETS_ID, createAnimation(other_car_4_1_texture_atlas, Constants.OTHERCAR_4_1_REGION_NAMES));
        animationsMap.put(Constants.OTHERCAR_4_2_ASSETS_ID, createAnimation(other_car_4_2_texture_atlas, Constants.OTHERCAR_4_2_REGION_NAMES));
        animationsMap.put(Constants.OTHERCAR_4_3_ASSETS_ID, createAnimation(other_car_4_3_texture_atlas, Constants.OTHERCAR_4_3_REGION_NAMES));
        animationsMap.put(Constants.OTHERCAR_5_1_ASSETS_ID, createAnimation(other_car_5_1_texture_atlas, Constants.OTHERCAR_5_1_REGION_NAMES));
        animationsMap.put(Constants.OTHERCAR_5_2_ASSETS_ID, createAnimation(other_car_5_2_texture_atlas, Constants.OTHERCAR_5_2_REGION_NAMES));
        animationsMap.put(Constants.OTHERCAR_5_3_ASSETS_ID, createAnimation(other_car_5_3_texture_atlas, Constants.OTHERCAR_5_3_REGION_NAMES));
        animationsMap.put(Constants.ROAD_STATIC_ASSETS_ID, createAnimation(road_texture_atlas, Constants.ROAD_REGION_NAMES));

        animationsMap.put(Constants.ROAD_1_BUSH_1_STATIC_ASSETS_ID, createAnimation(road_1_bush_1_texture_atlas, Constants.ROAD_1_BUSH_1_REGION_NAMES));
        animationsMap.put(Constants.ROAD_1_BUSH_2_STATIC_ASSETS_ID, createAnimation(road_1_bush_2_texture_atlas, Constants.ROAD_1_BUSH_2_REGION_NAMES));
        animationsMap.put(Constants.ROAD_1_TREE_1_STATIC_ASSETS_ID, createAnimation(road_1_tree_1_texture_atlas, Constants.ROAD_1_TREE_1_REGION_NAMES));
        animationsMap.put(Constants.ROAD_1_STUMP_1_STATIC_ASSETS_ID, createAnimation(road_1_stump_1_texture_atlas, Constants.ROAD_1_STUMP_1_REGION_NAMES));

        animationsMap.put(Constants.ROAD_1_LIGHTER_L_STATIC_ASSETS_ID, createAnimation(road_1_lighter_l_rexture_atlas, Constants.ROAD_1_LIGHTER_L_REGION_NAMES));
        animationsMap.put(Constants.ROAD_1_LIGHTER_R_STATIC_ASSETS_ID, createAnimation(road_1_lighter_r_rexture_atlas, Constants.ROAD_1_LIGHTER_R_REGION_NAMES));

        animationsMap.put(Constants.SPRING_BOARD_ASSETS_ID, createAnimation(springboard, Constants.SPRING_BOARD_REGION_NAMES));
        animationsMap.put(Constants.ROAD_BLOCK_ASSETS_ID, createAnimation(road_block, Constants.ROAD_BLOCK_REGION_NAMES));
        animationsMap.put(Constants.DIRT_ON_SCREEN_1_ASSETS_ID, createAnimation(dirt_on_screen_1, Constants.DIRT_FOR_SCREEN_1_REGION_NAMES));
        animationsMap.put(Constants.DIRT_ON_SCREEN_2_ASSETS_ID, createAnimation(dirt_on_screen_2, Constants.DIRT_FOR_SCREEN_2_REGION_NAMES));
        animationsMap.put(Constants.DIRT_ON_SCREEN_3_ASSETS_ID, createAnimation(dirt_on_screen_3, Constants.DIRT_FOR_SCREEN_3_REGION_NAMES));
        animationsMap.put(Constants.DIRT_ON_SCREEN_4_ASSETS_ID, createAnimation(dirt_on_screen_4, Constants.DIRT_FOR_SCREEN_4_REGION_NAMES));
        animationsMap.put(Constants.DIRT_ON_SCREEN_5_ASSETS_ID, createAnimation(dirt_on_screen_5, Constants.DIRT_FOR_SCREEN_5_REGION_NAMES));
        animationsMap.put(Constants.DIRT_ON_SCREEN_6_ASSETS_ID, createAnimation(dirt_on_screen_6, Constants.DIRT_FOR_SCREEN_6_REGION_NAMES));
        animationsMap.put(Constants.DIRT_ASSETS_ID, createAnimation(dirt, Constants.DIRT_REGION_NAMES));
        animationsMap.put(Constants.FLY_SPRINGBOARD_ASSETS_ID, createAnimation(fly_springboard, Constants.FLY_SPRINGBOARD_REGION_NAMES));

        animationsMap.put(Constants.LEFT_WING_ASSETS_ID, createAnimation(left_wing, Constants.LEFT_WING_REGION_NAMES));
        animationsMap.put(Constants.RIGHT_WING_ASSETS_ID, createAnimation(right_wing, Constants.RIGHT_WING_REGION_NAMES));

        animationsMap.put(Constants.CRASH_ASSETS_ID, createAnimation(car_crash_animation, Constants.CRASH_REGION_NAMES));

    }

    public static TextureRegion getTextureRegion(String key) {
        return texturesMap.get(key);
    }

    public static Animation getAnimation(String key) {
        return animationsMap.get(key);
    }

    private static Animation createAnimation(TextureAtlas textureAtlas, String[] regionNames) {

        TextureRegion[] runningFrames = new TextureRegion[regionNames.length];

        for (int i = 0; i < regionNames.length; i++) {
            String path = regionNames[i];
            runningFrames[i] = textureAtlas.findRegion(path);
        }

        return new Animation(0.1f, runningFrames);

    }

    public static TextureAtlas getCarTextureAtlas() {
        return carTextureAtlas;
    }

    public static BitmapFont getSmallFont() {
        return smallFont;
    }

    public static BitmapFont getLargeFont() {
        return largeFont;
    }

    public static BitmapFont getSmallestFont() {
        return smallestFont;
    }

    public static void dispose() {
        carTextureAtlas.dispose();
        smallestFont.dispose();
        smallFont.dispose();
        largeFont.dispose();
        texturesMap.clear();
        animationsMap.clear();
    }

    public static TextureAtlas getBttnTextureAtlas() {
        return menuBttnTextureAtlas;
    }

    public static TextureAtlas getBackTextureAtlas() {
        return menuBackButtonTextureAtlas;
    }

    public static TextureAtlas getGarageBttnTextureAtlas() {
        return menuGarageButtonTextureAtlas;
    }
}
