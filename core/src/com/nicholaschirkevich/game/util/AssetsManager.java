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
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.nicholaschirkevich.game.entity.Car;
import com.nicholaschirkevich.game.entity.CarsType;


import java.util.ArrayList;
import java.util.HashMap;

public class AssetsManager {

    private static HashMap<String, TextureRegion> texturesMap = new HashMap<String, TextureRegion>();
    private static HashMap<String, Animation> animationsMap = new HashMap<String, Animation>();

    public static Music getPlayMusic() {
        return playMusic;
    }
    public static void playSound(String soundID)
    {
        if(GameManager.isSoundEnable())
        {
            getSound(soundID).play();
        }
    }

    public static void playSoundLow(String soundID)
    {
        if(GameManager.isSoundEnable())
        {
            getSound(soundID).play(0.2f);
        }
    }

    private static Music playMusic;

    public static void playMusic()
    {
        if(GameManager.isSoundEnable()) playMusic.play();
    }

    public static void stopMusic()
    {
        if(GameManager.isSoundEnable()) playMusic.stop();
    }

    public static void pauseMusic()
    {
        if(GameManager.isSoundEnable()) playMusic.pause();
    }



    public static Sound getSound(String id) {
        return soundHashMap.get(id);
    }

    private static HashMap<String, Sound> soundHashMap = new HashMap<String, Sound>();

    private static ArrayList<TextureAtlas> carsTextureAtlasList = new ArrayList<TextureAtlas>();
    private static Skin uiSkin;
    private static ParticleEffect particleEffect;

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
    private static TextureAtlas gate_animation;
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

    public static ParticleEffect getParticleEffect() {
        return particleEffect;
    }

    public static void setParticleEffect(ParticleEffect particleEffect) {
        AssetsManager.particleEffect = particleEffect;
    }

    public static void loadSound()
    {
        soundHashMap.put(Constants.SOUND_START1,Gdx.audio.newSound(Gdx.files.internal(Constants.SOUND_START1_SOURCE)));
        soundHashMap.put(Constants.SOUND_START_2,Gdx.audio.newSound(Gdx.files.internal(Constants.SOUND_START_2_SOURCE)));
        soundHashMap.put(Constants.SOUND_BONUS,Gdx.audio.newSound(Gdx.files.internal(Constants.SOUND_BONUS_SOURCE)));
        soundHashMap.put(Constants.SOUND_BRAKE_LOOP,Gdx.audio.newSound(Gdx.files.internal(Constants.SOUND_BRAKE_LOOP_SOURCE)));
        soundHashMap.put(Constants.SOUND_CLICK,Gdx.audio.newSound(Gdx.files.internal(Constants.SOUND_CLICK_SOURCE)));
        soundHashMap.put(Constants.SOUND_CRASH_2,Gdx.audio.newSound(Gdx.files.internal(Constants.SOUND_CRASH_2_SOURCE)));
        soundHashMap.put(Constants.SOUND_GARAGE_CAR,Gdx.audio.newSound(Gdx.files.internal(Constants.SOUND_GARAGE_CAR_SOURCE)));
        soundHashMap.put(Constants.SOUND_GARAGE_OPEN,Gdx.audio.newSound(Gdx.files.internal(Constants.SOUND_GARAGE_OPEN_SOURCE)));
        soundHashMap.put(Constants.SOUND_GARAGE_WIN,Gdx.audio.newSound(Gdx.files.internal(Constants.SOUND_GARAGE_WIN_SOURCE)));
        soundHashMap.put(Constants.SOUND_GEAR,Gdx.audio.newSound(Gdx.files.internal(Constants.SOUND_GEAR_SOURCE)));
        soundHashMap.put(Constants.SOUND_HORN_1,Gdx.audio.newSound(Gdx.files.internal(Constants.SOUND_HORN_1_SOURCE)));
        soundHashMap.put(Constants.SOUND_HORN_2,Gdx.audio.newSound(Gdx.files.internal(Constants.SOUND_HORN_2_SOURCE)));
        soundHashMap.put(Constants.SOUND_HORN_3,Gdx.audio.newSound(Gdx.files.internal(Constants.SOUND_HORN_3_SOURCE)));
        soundHashMap.put(Constants.SOUND_JUMP,Gdx.audio.newSound(Gdx.files.internal(Constants.SOUND_JUMP_SOURCE)));
        soundHashMap.put(Constants.SOUND_MONEY_2,Gdx.audio.newSound(Gdx.files.internal(Constants.SOUND_MONEY_2_SOURCE)));
        soundHashMap.put(Constants.SOUND_NEW_RECORD1,Gdx.audio.newSound(Gdx.files.internal(Constants.SOUND_NEW_RECORD1_SOURCE)));
        soundHashMap.put(Constants.SOUND_NEW_RECORD2,Gdx.audio.newSound(Gdx.files.internal(Constants.SOUND_NEW_RECORD2_SOURCE)));
        soundHashMap.put(Constants.SOUND_ROCKET,Gdx.audio.newSound(Gdx.files.internal(Constants.SOUND_ROCKET_SOURCE)));

    }

    public static void loadAssets() {
        playMusic = Gdx.audio.newMusic(Gdx.files.internal(Constants.SOUND_SPEEDY_ROAD_LOOP_SOURCE));
        playMusic.setLooping(true);

        uiSkin = new Skin(Gdx.files.internal("uiskin_digit.json"));
        //Road
        texturesMap.put(Constants.ROAD_ASSETS_ID,
                new TextureRegion(new Texture(Gdx.files.internal(Constants.ROAD_IMAGE_PATH))));

//        texturesMap.put(Constants.MY_CAR_STATIC_ASSETS_ID,
//                new TextureRegion(new Texture(Gdx.files.internal(Constants.MY_CAR_1_IMAGE_PATH))));

        if (GameManager.getLocale().equals(Constants.RU_LOCALE)) {
            texturesMap.put(Constants.SETTINGS_ID, new TextureRegion(new Texture(Constants.SETTINGS_RESOURCE_RU)));
            texturesMap.put(Constants.TITLE_VEHICLES_ID, new TextureRegion(new Texture(Constants.TITLE_VEHICLES_RUS_RESOURCE)));
            texturesMap.put(Constants.COIN_SHOP_NAME_ID, new TextureRegion(new Texture(Constants.COIN_SHOP_NAME_RUS_RESOURCE)));
        } else {
            texturesMap.put(Constants.SETTINGS_ID, new TextureRegion(new Texture(Constants.SETTINGS_RESOURCE)));
            texturesMap.put(Constants.TITLE_VEHICLES_ID, new TextureRegion(new Texture(Constants.TITLE_VEHICLES_RESOURCE)));
            texturesMap.put(Constants.COIN_SHOP_NAME_ID, new TextureRegion(new Texture(Constants.COIN_SHOP_NAME_RESOURCE)));
        }
        //passer Car 1
        texturesMap.put(Constants.PASSER_CAR_1_ID,
                new TextureRegion(new Texture(Gdx.files.internal(Constants.PASSER_CAR_1_IMAGE_PATH))));

        texturesMap.put(Constants.COIN_SHOP_STANDART_ID, new TextureRegion(new Texture(Gdx.files.internal(Constants.COIN_SHOP_STANDART_RESOURCE))));
        texturesMap.put(Constants.COIN_SHOP_2_ID, new TextureRegion(new Texture(Gdx.files.internal(Constants.COIN_SHOP_2_RESOURCE))));
        texturesMap.put(Constants.COIN_SHOP_3_ID, new TextureRegion(new Texture(Gdx.files.internal(Constants.COIN_SHOP_3_RESOURCE))));
        texturesMap.put(Constants.COIN_SHOP_4_ID, new TextureRegion(new Texture(Gdx.files.internal(Constants.COIN_SHOP_4_RESOURCE))));
        texturesMap.put(Constants.MAIN_MENU_LOGO_ID, new TextureRegion(new Texture(Gdx.files.internal(Constants.MAIN_MENU_LOGO_PATH))));

        texturesMap.put(Constants.MAIN_MENU_COIN_SHOP_PRESSERD_ID, new TextureRegion(new Texture(Gdx.files.internal(Constants.MAIN_MENU_COIN_SHOP_PRESSED_RESOURCE))));
        texturesMap.put(Constants.MAIN_MENU_COIN_SHOP_UP_ID, new TextureRegion(new Texture(Gdx.files.internal(Constants.MAIN_MENU_COIN_SHOP_UP_RESOURCE))));
        texturesMap.put(Constants.MAIN_MENU_PLAY_BTTN_PRESSERD_ID, new TextureRegion(new Texture(Gdx.files.internal(Constants.MAIN_MENU_PLAY_BTTN_PRESSED_RESOURCE))));
        texturesMap.put(Constants.MAIN_MENU_PLAY_BTTN_UP_ID, new TextureRegion(new Texture(Gdx.files.internal(Constants.MAIN_MENU_PLAY_BTTN_UP_RESOURCE))));
        texturesMap.put(Constants.MAIN_MENU_MULTIPLAYER_PRESSERD_ID, new TextureRegion(new Texture(Gdx.files.internal(Constants.MAIN_MENU_MULTIPLAYER_PRESSED_RESOURCE))));
        texturesMap.put(Constants.MAIN_MENU_MULTIPLAYER_UP_ID, new TextureRegion(new Texture(Gdx.files.internal(Constants.MAIN_MENU_MULTIPLAYER_UP_RESOURCE))));
        texturesMap.put(Constants.MAIN_MENU_WIN_PRIZE_PRESSERD_ID, new TextureRegion(new Texture(Gdx.files.internal(Constants.MAIN_MENU_WIN_PRIZE_PRESSED_RESOURCE))));
        texturesMap.put(Constants.MAIN_MENU_WIN_PRIZE_UP_ID, new TextureRegion(new Texture(Gdx.files.internal(Constants.MAIN_MENU_WIN_PRIZE_UP_RESOURCE))));
        texturesMap.put(Constants.MAIN_MENU_CARS_PRESSERD_ID, new TextureRegion(new Texture(Gdx.files.internal(Constants.MAIN_MENU_CARS_PRESSED_RESOURCE))));
        texturesMap.put(Constants.MAIN_MENU_CARS_UP_ID, new TextureRegion(new Texture(Gdx.files.internal(Constants.MAIN_MENU_CARS_UP_RESOURCE))));
        texturesMap.put(Constants.MAIN_MENU_LEADERBOARDS_PRESSERD_ID, new TextureRegion(new Texture(Gdx.files.internal(Constants.MAIN_MENU_LEADERBOARDS_PRESSED_RESOURCE))));
        texturesMap.put(Constants.MAIN_MENU_LEADERBOARDS_UP_ID, new TextureRegion(new Texture(Gdx.files.internal(Constants.MAIN_MENU_LEADERBOARDS_UP_RESOURCE))));
        texturesMap.put(Constants.MAIN_MENU_LEADERBOARD_PRESSERD_ID, new TextureRegion(new Texture(Gdx.files.internal(Constants.MAIN_MENU_LEADERBOARD_PRESSED_RESOURCE))));
        texturesMap.put(Constants.MAIN_MENU_LEADERBOARD_UP_ID, new TextureRegion(new Texture(Gdx.files.internal(Constants.MAIN_MENU_LEADERBOARD_UP_RESOURCE))));
        texturesMap.put(Constants.MAIN_MENU_SET_PRESSERD_ID, new TextureRegion(new Texture(Gdx.files.internal(Constants.MAIN_MENU_SET_PRESSED_RESOURCE))));
        texturesMap.put(Constants.MAIN_MENU_SET_UP_ID, new TextureRegion(new Texture(Gdx.files.internal(Constants.MAIN_MENU_SET_UP_RESOURCE))));
        texturesMap.put(Constants.BTTN_CAR_SHOP_BUY_EMPTY_ID, new TextureRegion(new Texture(Gdx.files.internal(Constants.BTTN_CAR_SHOP_BUY_EMPTY_RESOURCE))));
        texturesMap.put(Constants.BTTN_CAR_SHOP_BUY_EMPTY_DIS_ID, new TextureRegion(new Texture(Gdx.files.internal(Constants.BTTN_CAR_SHOP_BUY_EMPTY_DIS_RESOURCE))));
        texturesMap.put(Constants.BTTN_CAR_SHOP_BUY_REAL_EMPTY_ID, new TextureRegion(new Texture(Gdx.files.internal(Constants.BTTN_CAR_SHOP_BUY_EMPTY_REAL_RESOURCE))));
        texturesMap.put(Constants.BTTN_CAR_SHOP_BUY_REAL_EMPTY_DIS_ID, new TextureRegion(new Texture(Gdx.files.internal(Constants.BTTN_CAR_SHOP_BUY_EMPTY_REAL_DIS_RESOURCE))));
        texturesMap.put(Constants.BTTN_CAR_SHOP_CHECKED_ID, new TextureRegion(new Texture(Gdx.files.internal(Constants.BTTN_CAR_SHOP_CHECKED_RESOURCE))));
        texturesMap.put(Constants.BTTN_NEXT_PRIZE_ID, new TextureRegion(new Texture(Gdx.files.internal(Constants.BTTN_NEXT_PRIZE_RESOURCE))));
        texturesMap.put(Constants.BTTN_NEXT_PRIZE_PRESSED_ID, new TextureRegion(new Texture(Gdx.files.internal(Constants.BTTN_NEXT_PRIZE_PRESSED_RESOURCE))));
        texturesMap.put(Constants.BTTN_GET_BONUS_ID, new TextureRegion(new Texture(Gdx.files.internal(Constants.BTTN_GET_BONUS_RESOURCE))));
        texturesMap.put(Constants.BTTN_GET_BONUS_PRESSED_ID, new TextureRegion(new Texture(Gdx.files.internal(Constants.BTTN_GET_BONUS_PRESSED_RESOURCE))));

        texturesMap.put(Constants.SPEED_TEXT_ID, new TextureRegion(new Texture(Constants.SPEED_TEXT_RESOURCE)));
        texturesMap.put(Constants.SPEED_BAR_ID, new TextureRegion(new Texture(Constants.SPEED_BAR_RESOURCE)));
        texturesMap.put(Constants.DELIMITER_ID, new TextureRegion(new Texture(Constants.DELIMITER_RESOURCE)));
        texturesMap.put(Constants.WEIGHT_TEXT_ID, new TextureRegion(new Texture(Constants.WEIGHT_TEXT_RESOURCE)));
        texturesMap.put(Constants.WEIGHT_BAR_ID, new TextureRegion(new Texture(Constants.WEIGHT_BAR_RESOURCE)));
        texturesMap.put(Constants.SLOT_VEHICLE_SELECTED_ID, new TextureRegion(new Texture(Constants.SLOT_VEHICLE_SELECTED_RESOURCE)));
        texturesMap.put(Constants.SLOT_VEHICLE_ID, new TextureRegion(new Texture(Constants.SLOT_VEHICLE_RESOURCE)));


        texturesMap.put(Constants.DISCOUNT_30_ID, new TextureRegion(new Texture(Constants.DISCOUNT_30_RESOURCE)));
        texturesMap.put(Constants.DISCOUNT_50_ID, new TextureRegion(new Texture(Constants.DISCOUNT_50_RESOURCE)));
        texturesMap.put(Constants.DISCOUNT_100_ID, new TextureRegion(new Texture(Constants.DISCOUNT_100_RESOURCE)));
        texturesMap.put(Constants.PAUSE_BACKGROUND_ID, new TextureRegion(new Texture(Constants.PAUSE_BACKGROUND_RESOURCE)));
        texturesMap.put(Constants.BTTN_RESUME_ID, new TextureRegion(new Texture(Constants.BTTN_RESUME_RESOURCE)));
        texturesMap.put(Constants.BTTN_RESUME_PRESSED_ID, new TextureRegion(new Texture(Constants.BTTN_RESUME_PRESSED_RESOURCE)));


        texturesMap.put(Constants.COIN_SHOP_NAME_ID, new TextureRegion(new Texture(Constants.COIN_SHOP_NAME_RESOURCE)));

        texturesMap.put(Constants.BACK_BUTTON_PRESSED_ID, new TextureRegion(new Texture(Constants.BACK_BUTTON_PRESSED_RESOURCE)));
        texturesMap.put(Constants.BACK_BUTTON_ID, new TextureRegion(new Texture(Constants.BACK_BUTTON_RESOURCE)));


        texturesMap.put(Constants.CAR_GARAGE_BTTN_GREEN_DOWN,new TextureRegion(new Texture(Constants.CAR_GARAGE_BTTN_GREEN_DOWN_SOURCE)));
        texturesMap.put(Constants.CAR_GARAGE_BTTN_GREEN_UP,new TextureRegion(new Texture(Constants.CAR_GARAGE_BTTN_GREEN_UP_SOURCE)));

        texturesMap.put(Constants.CNR_LINE_ID, new TextureRegion(new Texture(Constants.CNR_LINE_RESOURCE)));
        texturesMap.put(Constants.BACK_TILE_ID, new TextureRegion(new Texture(Constants.BACK_TILE_RESOURCE)));
        texturesMap.put(Constants.BUTTON_BUT_REAL_EMPTY_ID, new TextureRegion(new Texture(Constants.BUTTON_BUT_REAL_EMPTY_RESOURCE)));

        texturesMap.put(Constants.SOUND_OFF_ID, new TextureRegion(new Texture(Constants.SOUND_OFF_RESOURCE)));
        texturesMap.put(Constants.SOUND_ON_ID, new TextureRegion(new Texture(Constants.SOUND_ON_RESOURCE)));
        texturesMap.put(Constants.ICON_CONTROL_SETTING_ID, new TextureRegion(new Texture(Constants.ICON_CONTROL_SETTING_RESOURCE)));
        texturesMap.put(Constants.ICON_BLOCK_ADS_ID, new TextureRegion(new Texture(Constants.ICON_BLOCK_ADS_RESOURCE)));
        texturesMap.put(Constants.ICON_RETURN_P_ID, new TextureRegion(new Texture(Constants.ICON_RETURN_P_RESOURCE)));
        texturesMap.put(Constants.ICON_ICON_VK_ID, new TextureRegion(new Texture(Constants.ICON_ICON_VK_RESOURCE)));
        texturesMap.put(Constants.ICON_ICON_FB_ID, new TextureRegion(new Texture(Constants.ICON_ICON_FB_RESOURCE)));

        texturesMap.put(Constants.BTTN_BLUE_ID, new TextureRegion(new Texture(Constants.BTTN_BLUE_RESOURCE)));
        texturesMap.put(Constants.BTTN_BLUE_PRESSED_ID, new TextureRegion(new Texture(Constants.BTTN_BLUE_PRESSED_RESOURCE)));

        texturesMap.put(Constants.CONTACT_POINT_ID, new TextureRegion(new Texture(Constants.CONTACT_POINT_RESOURCE)));
        texturesMap.put(Constants.PAUSE_BUTTON_PRESSED_ID, new TextureRegion(new Texture(Constants.PAUSE_BUTTON_PRESSED_RESOURCE)));
        texturesMap.put(Constants.PAUSE_BUTTON_UP_ID, new TextureRegion(new Texture(Constants.PAUSE_BUTTON_UP_RESOURCE)));
        texturesMap.put(Constants.DANGER_ID, new TextureRegion(new Texture(Constants.DANGER_RESOURCE)));
        texturesMap.put(Constants.BOOSTER_ID, new TextureRegion(new Texture(Constants.BOOSTER_RESOURCE)));
        texturesMap.put(Constants.ROCKETS_ID, new TextureRegion(new Texture(Constants.ROCKETS_RESOURCE)));
        texturesMap.put(Constants.SKULL_ON_ROAD_ID, new TextureRegion(new Texture(Constants.SKULL_ON_ROAD_RESOURCE)));
        texturesMap.put(Constants.COIN_ICON_1_NAME_ID, new TextureRegion(new Texture(Constants.COIN_ICON_1_RESOURCE)));
        texturesMap.put(Constants.RESUME_BTTN_ID, new TextureRegion(new Texture(Constants.RESUME_BTTN_RESOURCE)));
        texturesMap.put(Constants.RESUME_BTTN_PRESSED_ID, new TextureRegion(new Texture(Constants.RESUME_BTTN_PRESSED_RESOURCE)));
        texturesMap.put(Constants.DESTRICTION_ID, new TextureRegion(new Texture(Constants.DESTRICTION_RESOURCE)));
        texturesMap.put(Constants.START_LINE_ID, new TextureRegion(new Texture(Constants.START_LINE_RESOURCE)));
        texturesMap.put(Constants.START_LIGHT_1_ID, new TextureRegion(new Texture(Constants.START_LIGHT_1_RESOURCE)));
        texturesMap.put(Constants.START_LIGHT_2_ID, new TextureRegion(new Texture(Constants.START_LIGHT_2_RESOURCE)));
        texturesMap.put(Constants.START_LIGHT_3_ID, new TextureRegion(new Texture(Constants.START_LIGHT_3_RESOURCE)));
        texturesMap.put(Constants.START_LIGHT_RED_ID, new TextureRegion(new Texture(Constants.START_LIGHT_RED_RESOURSE)));
        texturesMap.put(Constants.ROAD_HOLE_ID, new TextureRegion(new Texture(Constants.ROAD_HOLE_RESOURSE)));
        texturesMap.put(Constants.BTTN_REVIVAL_ID, new TextureRegion(new Texture(Constants.BTTN_REVIVAL_RESOURSE)));
        texturesMap.put(Constants.BTTN_REVIVAL_PRESSED_ID, new TextureRegion(new Texture(Constants.BTTN_REVIVAL_PRESSED_RESOURSE)));
        texturesMap.put(Constants.PROGRESS_BAR_ID, new TextureRegion(new Texture(Constants.PROGRESS_BAR_RESOURSE)));
        texturesMap.put(Constants.PROGRESS_BAR_FRAME_ID, new TextureRegion(new Texture(Constants.PROGRESS_BAR_FRAME_RESOURSE)));
        texturesMap.put(Constants.GARAGE_ID, new TextureRegion(new Texture(Constants.GARAGE_RESOURSE)));
        texturesMap.put(Constants.BTTN_EXIT_ID, new TextureRegion(new Texture(Constants.BTTN_EXIT_RESOURCE)));
        texturesMap.put(Constants.BTTN_EXIT_PRESSED_ID, new TextureRegion(new Texture(Constants.BTTN_EXIT_PRESSED_RESOURCE)));
        texturesMap.put(Constants.BTTN_VK_ID, new TextureRegion(new Texture(Constants.BTTN_VK_RESOURCE)));
        texturesMap.put(Constants.BTTN_VK_PRESSED_ID, new TextureRegion(new Texture(Constants.BTTN_VK_PRESSED_RESOURCE)));


        particleEffect = new ParticleEffect();
        particleEffect.load(Gdx.files.internal("booster_particle"), Gdx.files.internal(""));


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

        gate_animation = new TextureAtlas(Constants.GATE_ATLAS_PATH);

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
        animationsMap.put(Constants.GATE_ASSETS_ID, createAnimation(gate_animation, Constants.GATE_REGION_NAMES));

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

    public static Skin getUiSkin() {
        return uiSkin;
    }
}
