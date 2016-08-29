package com.nicholaschirkevich.game.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Json;
import com.nicholaschirkevich.game.GameRuners;
import com.nicholaschirkevich.game.entity.Car;
import com.nicholaschirkevich.game.entity.CarsType;
import com.nicholaschirkevich.game.entity.GearShift;
import com.nicholaschirkevich.game.entity.VkUser;
import com.nicholaschirkevich.game.enums.SideObjectType;
import com.nicholaschirkevich.game.enums.TraffictLighterEnum;
import com.nicholaschirkevich.game.helper.XmlHelper;
import com.nicholaschirkevich.game.model.GearView;
import com.nicholaschirkevich.game.model.side_objects.NewRoad;
import com.nicholaschirkevich.game.model.side_objects.SideObject;
import com.nicholaschirkevich.game.unput.GestureListnener;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Nikolas on 11.03.2016.
 */
public class GameManager {


    private static ArrayList<GearShift> gearShifts = new ArrayList<GearShift>();
    private static ArrayList<CarsType> carsTypes = new ArrayList<CarsType>();
    private static HashMap<String, String> strings = new HashMap<String, String>();

    public static HashMap<String, NewRoad> getRoads() {
        return roads;
    }

    private static HashMap<String, NewRoad> roads = new HashMap<String, NewRoad>();
    private static Car currentCar = new Car();
    public static boolean pauseGame = false;
    private static Date lastFreeCarPrize;
    private static VkUser vkUser;

    private static boolean isFirstStartApp;

    private static int CarShopSize;

    public static int getCarShopSize() {
        return CarShopSize;
    }

    public static void setCarShopSize(int carShopSize) {
        CarShopSize = carShopSize;
    }


    public static GestureListnener getGestureListnener() {
        return gestureListnener;
    }

    public static void setGestureListnener(GestureListnener gestureListnener) {
        GameManager.gestureListnener = gestureListnener;
    }

    private static GestureListnener gestureListnener;

    public static VkUser getVkUser() {
        return vkUser;
    }

    public static void setVkUser(VkUser vkUser) {
        preferences.putString(Constants.PREFERENCES_VK_HIGH_SCORE, vkUser.getHighscore());
        preferences.putString(Constants.PREFERENCES_VK_USER_ID, vkUser.getId());
        preferences.putString(Constants.PREFERENCES_VK_IMG_SOURCE, vkUser.getImage_src());
        preferences.putString(Constants.PREFERENCES_VK_FIRST_NAME, vkUser.getUser_first_name());
        preferences.putString(Constants.PREFERENCES_VK_LAST_NAME, vkUser.getUser_last_name());
        preferences.flush();
        GameManager.vkUser = vkUser;
    }


    public static boolean isAfterSaveMe() {
        return isAfterSaveMe;
    }

    public static void setIsAfterSaveMe(boolean isAfterSaveMe) {
        GameManager.isAfterSaveMe = isAfterSaveMe;
    }

    public static boolean isAfterSaveMe = false;
    private static float contactPointX = 0, contactPointY = 0;
    //private static float boosterSpeed =0;
    private static float lastSpeed = 0;
    private static float achives = 0;

    public static float getDistance() {
        return distance;
    }

    public static void setDistance(float distance) {
        GameManager.distance = distance;
    }

    private static float distance = 0;
    private static String locale = "";

    public static boolean isVkLogin() {
        return isVkLogin;
    }

    public static void setIsVkLogin(boolean isLogin) {
        isVkLogin = isLogin;
    }

    private static boolean isVkLogin = false;
    private static int dangerousCount = 0;
    private static int rocketCount = 0;
    private static int destroyedCount = 0;
    private static int springBoardCount = 0;
    private static int godModeCount = 0;

    public static int getCoinCount() {
        return coinCount;
    }

    public static void setGameCoin(int coinCount) {
        GameManager.coinCount = coinCount;
    }

    private static int coinCount = 0;
    private static boolean isSoundEnable;

    public static boolean isSoundEnable() {
        return isSoundEnable;
    }

    public static void setIsSoundEnable(boolean isSoundEnable) {
        preferences.putBoolean(Constants.PREFERENCES_SOUND_SETTING_ID, isSoundEnable);
        preferences.flush();
        GameManager.isSoundEnable = isSoundEnable;
    }


    public static List<String> getMyCars() {
        return myCars;
    }

    private static List<String> myCars;
    static Json json = new Json();

    public static void addDangerousCount() {
        dangerousCount += 50;
    }


    public static void addRocketCount() {
        rocketCount += 100;
    }

    public static void addDestroyedCount() {
        destroyedCount += 100;
    }

    public static void addSpringBoardCount() {
        springBoardCount += 100;
    }

    public static void addGodModeCount() {
        godModeCount += 100;
    }

    public static void resetCountBusters() {
        dangerousCount = 0;
        rocketCount = 0;
        destroyedCount = 0;
        springBoardCount = 0;
        godModeCount = 0;
        coinCount = 0;
    }

    public static float getBestAchives() {
        return bestAchives;
    }

    private static float bestAchives = 0;
    private static boolean isCollision = false;

    public static void setCurrentSpeed(float currentSpeed) {
        CurrentSpeed = currentSpeed;
    }

    public static void setcurrentSpeed(float currentspeed) {
        currentSpeed = currentspeed;
    }

    private static String currentCarID = "SP000";
    private static Integer coinCounter;
    private static Preferences preferences;

    public static float getCurrentSpeed() {
        return CurrentSpeed;
    }

    private static float CurrentSpeed = 0;


    public static boolean isStopGeneratePasserCars() {
        return stopGeneratePasserCars;
    }

    public static void setStopGeneratePasserCars(boolean stopGeneratePasserCars) {
        GameManager.stopGeneratePasserCars = stopGeneratePasserCars;
    }

    private static boolean stopGeneratePasserCars = false;

    private static int gear = 0;
    private static GearShift gearShift;
    private static float toSpeed = 0;
    private static float currentSpeed = 0;

    public static boolean isPauseDtTimer() {
        return pauseDtTimer;
    }

    public static void setPauseDtTimer(boolean pauseDtTimer) {
        GameManager.pauseDtTimer = pauseDtTimer;
    }

    private static boolean pauseDtTimer = false;
    private static float dtTime = 0;
    private static float dtTimeAhive = 0;
    private static boolean isCollisionWithCar = false;

    public static boolean isTouchControl() {
        return isTouchControl;
    }

    public static void setIsTouchControl(boolean isTouchControl) {
        preferences.putBoolean(Constants.PREFERENCES_CONTROL_ID, isTouchControl);
        preferences.flush();
        GameManager.isTouchControl = isTouchControl;
    }

    private static boolean isTouchControl;

    private static Label label;
    private static Label timeLabel;
    private static Label stageChild;
    private static Label worldChild;

    private static World worldGameManager;
    private static Stage stageGameManager;

    public static float getAllTime() {
        return allTime;
    }

    public static void setAllTime(float allTime) {
        GameManager.allTime = allTime;
    }

    private static float allTime = 0;

    public static float getCollisionSpeed() {
        return collisionSpeed;
    }

    public static void setCollisionSpeed(float collisionSpeed) {
        GameManager.collisionSpeed = collisionSpeed;
    }

    private static float collisionSpeed = 0;

    public static void loadLocale() {
        locale = java.util.Locale.getDefault().toString();
    }

    public static String getLocale() {
        return locale;
    }

    public static void addCar(String id) {
        if (!myCars.contains(id)) {
            myCars.add(id);
            preferences.putString(Constants.PREFERENCES_KEY_CARS, json.toJson(myCars).toString());
            preferences.flush();
        }
    }

    public static void addCar(Car car) {
        if (!myCars.contains(car.getID())) {
            myCars.add(car.getID());
            preferences.putString(Constants.PREFERENCES_KEY_CARS, json.toJson(myCars).toString());
            preferences.flush();
        }
    }

    public static void loadData() {
        loadLocale();
        gearShifts = XmlHelper.getShifts();
        carsTypes = XmlHelper.getCars();
        for (CarsType carsType : carsTypes) {
            setCarShopSize(getCarShopSize() + carsType.getCars().size());
        }
        if (locale.equals(Constants.RU_LOCALE)) {
            strings = XmlHelper.getStringsRus();
        } else {
            strings = XmlHelper.getStringsEn();
        }

        preferences = Gdx.app.getPreferences(Constants.PREFERENCES_KEY);
        loadPreferences();
        gearShift = GameManager.getGearShifts().get(GameManager.getCurrentCar().getCurveType() - 1);

//       String test =  preferences.getString(Constants.PREFERENCES_KEY_CARS);
//        preferences.putString("test",Constants.PREFERENCES_KEY_CARS);
//        preferences.flush();
        myCars = json.fromJson(ArrayList.class, preferences.getString(Constants.PREFERENCES_KEY_CARS));
        if (myCars == null) {
            myCars = new ArrayList<String>();
            myCars.add("SP000");
            preferences.putString(Constants.PREFERENCES_KEY_CARS, json.toJson(myCars).toString());
            preferences.flush();
        }


    }

    public static void initial(World world, Stage stage) {
        worldGameManager = world;
        stageGameManager = stage;
        setUpLabetDebug();
        setUpTimeLabetDebug();
        setUpWorldChildLabetDebug();
        setUpStageChildLabetDebug();
        setUpRoadsHashMap();
    }

    public static void setUpRoadsHashMap() {
        ArrayList<SideObjectType> sideObjectsLeftRoad1ArrayListType = new ArrayList<SideObjectType>();
        sideObjectsLeftRoad1ArrayListType.add(SideObjectType.ROAD_1_BUSH_1_ID_LEFT);
        sideObjectsLeftRoad1ArrayListType.add(SideObjectType.ROAD_1_BUSH_2_ID_LEFT);

        sideObjectsLeftRoad1ArrayListType.add(SideObjectType.ROAD_1_TREE_1_ID_LEFT);
        sideObjectsLeftRoad1ArrayListType.add(SideObjectType.ROAD_1_STUMP_ID_LEFT);
        sideObjectsLeftRoad1ArrayListType.add(SideObjectType.ROAD_1_BUSH_1_ID_LEFT_AND_STUMP_LEFT);
        sideObjectsLeftRoad1ArrayListType.add(SideObjectType.ROAD_1_TREE_1_ID_LEFT_AND_BUSH);
        sideObjectsLeftRoad1ArrayListType.add(SideObjectType.ROAD_1_TREE_1_ID_LEFT_AND_TREE);


        ArrayList<SideObjectType> sideObjectsRightRoad1ArrayListType = new ArrayList<SideObjectType>();
        sideObjectsRightRoad1ArrayListType.add(SideObjectType.ROAD_1_BUSH_1_ID_RIGHT);
        sideObjectsRightRoad1ArrayListType.add(SideObjectType.ROAD_1_BUSH_2_ID_RIGHT);

        sideObjectsRightRoad1ArrayListType.add(SideObjectType.ROAD_1_TREE_1_ID_RIGHT);
        sideObjectsRightRoad1ArrayListType.add(SideObjectType.ROAD_1_STUMP_ID_RIGHT);

        sideObjectsRightRoad1ArrayListType.add(SideObjectType.ROAD_1_BUSH_1_ID_RIGHT_AND_STUMP_RIGHT);
        sideObjectsRightRoad1ArrayListType.add(SideObjectType.ROAD_1_TREE_1_ID_RIGHT_AND_BUSH);

        ArrayList<SideObjectType> staticSideRightObjectsArrayList = new ArrayList<SideObjectType>();
        SideObjectType sideObjectLighterLType = SideObjectType.ROAD_1_LIGHTER_L_ID;
        sideObjectLighterLType.setDistance(300);
        sideObjectLighterLType.setIsStaticObject(true);
        sideObjectLighterLType.setIsLeft(true);
        staticSideRightObjectsArrayList.add(sideObjectLighterLType);

        ArrayList<SideObjectType> staticSideLeftObjectsArrayList = new ArrayList<SideObjectType>();
        SideObjectType sideObjectLighterRType = SideObjectType.ROAD_1_LIGHTER_R_ID;
        sideObjectLighterRType.setIsStaticObject(true);
        sideObjectLighterRType.setDistance(300);
        sideObjectLighterRType.setIsLeft(false);
        staticSideLeftObjectsArrayList.add(sideObjectLighterRType);

        NewRoad road1 = new NewRoad(AssetsManager.getTextureRegion(Constants.ROAD_1_TILE_ID).getTexture(), AssetsManager.getTextureRegion(Constants.ROAD_1_TILE_BACK_ID).getTexture(), sideObjectsRightRoad1ArrayListType, sideObjectsLeftRoad1ArrayListType, staticSideRightObjectsArrayList, staticSideLeftObjectsArrayList, TraffictLighterEnum.TRAFFICT_LIGHTER_COUNTYSIDE, false);
        roads.put("TrackTypeCountrySide", road1);

        ArrayList<SideObjectType> sideObjectsRoad2RightArrayListType = new ArrayList<SideObjectType>();
        sideObjectsRoad2RightArrayListType.add(SideObjectType.ROAD_2_BOARD_RIGHT_ID);
        sideObjectsRoad2RightArrayListType.add(SideObjectType.ROAD_2_CACTUS_RIGHT_1_ID);
        sideObjectsRoad2RightArrayListType.add(SideObjectType.ROAD_2_CACTUS_RIGHT_2_ID);
        sideObjectsRoad2RightArrayListType.add(SideObjectType.ROAD_2_MAN_RIGHT_ID);
        sideObjectsRoad2RightArrayListType.add(SideObjectType.ROAD_2_STONE_RIGHT_ID);
        sideObjectsRoad2RightArrayListType.add(SideObjectType.ROAD_2_TIRES_RIGHT_ID);

        ArrayList<SideObjectType> sideObjectsRoad2LeftArrayListType = new ArrayList<SideObjectType>();
        sideObjectsRoad2RightArrayListType.add(SideObjectType.ROAD_2_BOARD_LEFT_ID);
        sideObjectsRoad2RightArrayListType.add(SideObjectType.ROAD_2_CACTUS_LEFT_1_ID);
        sideObjectsRoad2RightArrayListType.add(SideObjectType.ROAD_2_CACTUS_LEFT_2_ID);
        sideObjectsRoad2RightArrayListType.add(SideObjectType.ROAD_2_MAN_LEFT_ID);
        sideObjectsRoad2RightArrayListType.add(SideObjectType.ROAD_2_STONE_LEFT_ID);
        sideObjectsRoad2RightArrayListType.add(SideObjectType.ROAD_2_TIRES_LEFT_ID);

        ArrayList<SideObjectType> staticSide2RightObjectsArrayList = new ArrayList<SideObjectType>();


        ArrayList<SideObjectType> staticSide2LeftObjectsArrayList = new ArrayList<SideObjectType>();


        NewRoad road2 = new NewRoad(AssetsManager.getTextureRegion(Constants.ROAD_2_TILE__ID).getTexture(), AssetsManager.getTextureRegion(Constants.ROAD_2_TILE_BACK_ID).getTexture(), sideObjectsRoad2RightArrayListType, sideObjectsRoad2LeftArrayListType, staticSide2RightObjectsArrayList, staticSide2LeftObjectsArrayList, TraffictLighterEnum.TRAFFICT_LIGHTER_MEXICO, false);
        roads.put("TrackTypeMexico", road2);


        ArrayList<SideObjectType> sideObjectsRightRoad3ArrayListType = new ArrayList<SideObjectType>();
        sideObjectsRightRoad3ArrayListType.add(SideObjectType.ROAD_3_SNOW_1_ID_RIGHT);
        sideObjectsRightRoad3ArrayListType.add(SideObjectType.ROAD_3_SNOW_2_ID_RIGHT);
        sideObjectsRightRoad3ArrayListType.add(SideObjectType.ROAD_3_SNOWMAN_ID_RIGHT);
        sideObjectsRightRoad3ArrayListType.add(SideObjectType.ROAD_3_TREE_1_ID_RIGHT);
        sideObjectsRightRoad3ArrayListType.add(SideObjectType.ROAD_3_TREE_2_ID_RIGHT);
        sideObjectsRightRoad3ArrayListType.add(SideObjectType.ROAD_3_TREE_3_ID_RIGHT);


        ArrayList<SideObjectType> sideObjectsLeftRoad3ArrayListType = new ArrayList<SideObjectType>();
        sideObjectsLeftRoad3ArrayListType.add(SideObjectType.ROAD_3_SNOW_1_ID_LEFT);
        sideObjectsLeftRoad3ArrayListType.add(SideObjectType.ROAD_3_SNOW_2_ID_LEFT);
        sideObjectsLeftRoad3ArrayListType.add(SideObjectType.ROAD_3_SNOWMAN_ID_LEFT);
        sideObjectsLeftRoad3ArrayListType.add(SideObjectType.ROAD_3_TREE_1_ID_LEFT);
        sideObjectsLeftRoad3ArrayListType.add(SideObjectType.ROAD_3_TREE_2_ID_LEFT);
        sideObjectsLeftRoad3ArrayListType.add(SideObjectType.ROAD_3_TREE_3_ID_LEFT);

        ArrayList<SideObjectType> staticSide3RightObjectsArrayList = new ArrayList<SideObjectType>();


        ArrayList<SideObjectType> staticSide3LeftObjectsArrayList = new ArrayList<SideObjectType>();


        NewRoad road3 = new NewRoad(AssetsManager.getTextureRegion(Constants.ROAD_3_TILE_ID).getTexture(), AssetsManager.getTextureRegion(Constants.ROAD_3_TILE_BACK_ID).getTexture(), sideObjectsRightRoad3ArrayListType, sideObjectsLeftRoad3ArrayListType, staticSide3RightObjectsArrayList, staticSide3LeftObjectsArrayList, TraffictLighterEnum.TRAFFICT_LIGHTER_SNOWLAND, false);
        roads.put("TrackTypeSnowLand", road3);

        ArrayList<SideObjectType> sideObjectsRoad4RightArrayListType = new ArrayList<SideObjectType>();
        sideObjectsRoad4RightArrayListType.add(SideObjectType.ROAD_4_BIGBOARD_DARK_RIGHT_ID);
        sideObjectsRoad4RightArrayListType.add(SideObjectType.ROAD_4_FLOWERS_RIGHT_ID);
        sideObjectsRoad4RightArrayListType.add(SideObjectType.ROAD_4_PHONE_RIGHT_ID);
        sideObjectsRoad4RightArrayListType.add(SideObjectType.ROAD_4_BILLBOARD_RIGHT_ID);
        sideObjectsRoad4RightArrayListType.add(SideObjectType.ROAD_4_BUILDING_1_RIGHT_ID);
        sideObjectsRoad4RightArrayListType.add(SideObjectType.ROAD_4_BUILDING_2_RIGHT_ID);
        sideObjectsRoad4RightArrayListType.add(SideObjectType.ROAD_4_BILLBOARD_DARK_RIGHT_ID);

//
        ArrayList<SideObjectType> sideObjectsRoad4LeftArrayListType = new ArrayList<SideObjectType>();
        sideObjectsRoad4LeftArrayListType.add(SideObjectType.ROAD_4_BIGBOARD_DARK_LEFT_ID);
        sideObjectsRoad4LeftArrayListType.add(SideObjectType.ROAD_4_PHONE_LEFT_ID);
        SideObjectType sideObjectBuldingLeft = SideObjectType.ROAD_4_BUILDING_1_LEFT_ID;
        sideObjectBuldingLeft.setIsFlipX(true);
        sideObjectsRoad4LeftArrayListType.add(sideObjectBuldingLeft);
        SideObjectType sideObjectBulding2Left = SideObjectType.ROAD_4_BUILDING_2_LEFT_ID;
        sideObjectBulding2Left.setIsFlipX(true);
        sideObjectsRoad4LeftArrayListType.add(sideObjectBulding2Left);
        sideObjectsRoad4LeftArrayListType.add(SideObjectType.ROAD_4_BILLBOARD_LEFT_ID);
        sideObjectsRoad4LeftArrayListType.add(SideObjectType.ROAD_4_FLOWERS_LEFT_ID);
        sideObjectsRoad4LeftArrayListType.add(SideObjectType.ROAD_4_BILLBOARD_DARK_LEFT_ID);

        ArrayList<SideObjectType> staticSide4RightObjectsArrayList = new ArrayList<SideObjectType>();


        ArrayList<SideObjectType> staticSide4LeftObjectsArrayList = new ArrayList<SideObjectType>();

        NewRoad road4 = new NewRoad(AssetsManager.getTextureRegion(Constants.ROAD_4_TILE_ID).getTexture(), AssetsManager.getTextureRegion(Constants.ROAD_4_TILE_BACK_ID).getTexture(), sideObjectsRoad4RightArrayListType, sideObjectsRoad4LeftArrayListType, staticSide4RightObjectsArrayList, staticSide4LeftObjectsArrayList, TraffictLighterEnum.TRAFFICT_LIGHTER_CITY, true);
        roads.put("TrackTypeCity", road4);


        ArrayList<SideObjectType> sideObjectsRoad5RightArrayListType = new ArrayList<SideObjectType>();
        sideObjectsRoad5RightArrayListType.add(SideObjectType.ROAD_5_LIFEBUOY_ID_RIGHT);
        sideObjectsRoad5RightArrayListType.add(SideObjectType.ROAD_5_PALM_ID_RIGHT);
        sideObjectsRoad5RightArrayListType.add(SideObjectType.ROAD_5_SHELL_ID_RIGHT);

        ArrayList<SideObjectType> sideObjectsRoad5LeftArrayListType = new ArrayList<SideObjectType>();
        sideObjectsRoad5LeftArrayListType.add(SideObjectType.ROAD_5_LIFEBUOY_ID_LEFT);
        sideObjectsRoad5LeftArrayListType.add(SideObjectType.ROAD_5_PALM_ID_LEFT);
        sideObjectsRoad5LeftArrayListType.add(SideObjectType.ROAD_5_SHELL_ID_LEFT);

        ArrayList<SideObjectType> staticSide5RightObjectsArrayList = new ArrayList<SideObjectType>();


        ArrayList<SideObjectType> staticSide5LeftObjectsArrayList = new ArrayList<SideObjectType>();

        NewRoad road5 = new NewRoad(AssetsManager.getTextureRegion(Constants.ROAD_5_TILE_ID).getTexture(), AssetsManager.getTextureRegion(Constants.ROAD_5_TILE_BACK_ID).getTexture(), sideObjectsRoad5RightArrayListType, sideObjectsRoad5LeftArrayListType, staticSide5RightObjectsArrayList, staticSide5LeftObjectsArrayList, TraffictLighterEnum.TRAFFICT_LIGHTER_BEACH, false);
        roads.put("TrackTypeBeach", road5);

        ArrayList<SideObjectType> sideObjectsRoad6RightArrayListType = new ArrayList<SideObjectType>();
        sideObjectsRoad6RightArrayListType.add(SideObjectType.ROAD_6_ROCK_1_LEFT_ID);
        sideObjectsRoad6RightArrayListType.add(SideObjectType.ROAD_6_ROCK_2_LEFT_ID);
        sideObjectsRoad6RightArrayListType.add(SideObjectType.ROAD_6_TREE_1_LEFT_ID);
        sideObjectsRoad6RightArrayListType.add(SideObjectType.ROAD_6_TREE_2_LEFT_ID);
        sideObjectsRoad6RightArrayListType.add(SideObjectType.ROAD_6_TREE_3_LEFT_ID);

        ArrayList<SideObjectType> sideObjectsRoad6LeftArrayListType = new ArrayList<SideObjectType>();
        sideObjectsRoad6LeftArrayListType.add(SideObjectType.ROAD_6_ROCK_1_RIGHT_ID);
        sideObjectsRoad6LeftArrayListType.add(SideObjectType.ROAD_6_ROCK_2_RIGHT_ID);
        sideObjectsRoad6LeftArrayListType.add(SideObjectType.ROAD_6_TREE_1_RIGHT_ID);
        sideObjectsRoad6LeftArrayListType.add(SideObjectType.ROAD_6_TREE_2_RIGHT_ID);
        sideObjectsRoad6LeftArrayListType.add(SideObjectType.ROAD_6_TREE_3_RIGHT_ID);

        ArrayList<SideObjectType> staticSide6RightObjectsArrayList = new ArrayList<SideObjectType>();


        ArrayList<SideObjectType> staticSide6LeftObjectsArrayList = new ArrayList<SideObjectType>();

        NewRoad road6 = new NewRoad(AssetsManager.getTextureRegion(Constants.ROAD_6_TILE_ID).getTexture(), AssetsManager.getTextureRegion(Constants.ROAD_6_TILE_BACK_ID).getTexture(), sideObjectsRoad6RightArrayListType, sideObjectsRoad6LeftArrayListType, staticSide6RightObjectsArrayList, staticSide6LeftObjectsArrayList, TraffictLighterEnum.TRAFFICT_LIGHTER_CANYON, false);
        roads.put("TrackTypeCanyon", road6);

        ArrayList<SideObjectType> sideObjectsRightRoad7ArrayListType = new ArrayList<SideObjectType>();
        sideObjectsRightRoad7ArrayListType.add(SideObjectType.ROAD_7_BENCH_BANNER_RIGHT);
        sideObjectsRightRoad7ArrayListType.add(SideObjectType.ROAD_7_BANNER_RIGHT_ID);
        sideObjectsRightRoad7ArrayListType.add(SideObjectType.ROAD_7_HOUSE_RIGHT_ID);
        sideObjectsRightRoad7ArrayListType.add(SideObjectType.ROAD_7_BENCH_RIGHT_ID);

        ArrayList<SideObjectType> sideObjectsLeftRoad7ArrayListType = new ArrayList<SideObjectType>();
        SideObjectType sideOnjectTypeRoad7LanterHouse = SideObjectType.ROAD_7_LANTERNS_HOUSE_LEFT_AND_RIGHT;
        sideOnjectTypeRoad7LanterHouse.setMovible(false);
        sideObjectsLeftRoad7ArrayListType.add(sideOnjectTypeRoad7LanterHouse);
        sideObjectsLeftRoad7ArrayListType.add(SideObjectType.ROAD_7_BANNER_LEFT_ID);
        sideObjectsLeftRoad7ArrayListType.add(SideObjectType.ROAD_7_HOUSE_LEFT_ID);
        sideObjectsLeftRoad7ArrayListType.add(SideObjectType.ROAD_7_BENCH_LEFT_ID);
        sideObjectsLeftRoad7ArrayListType.add(SideObjectType.ROAD_7_BENCH_BANNER_LEFT);
//
        ArrayList<SideObjectType> staticSide7RightObjectsArrayList = new ArrayList<SideObjectType>();

        SideObjectType sideObject7right = SideObjectType.ROAD_7_LAMP_RIGHT_ID;
        sideObject7right.setIsLeft(false);
        sideObject7right.setDistance(1000);
        staticSide7RightObjectsArrayList.add(sideObject7right);


        ArrayList<SideObjectType> staticSide7LeftObjectsArrayList = new ArrayList<SideObjectType>();
        SideObjectType sideObject7left = SideObjectType.ROAD_7_LAMP_LEFT_ID;
        sideObject7left.setIsLeft(true);
        sideObject7left.setDistance(1000);
        staticSide7LeftObjectsArrayList.add(sideObject7left);


        NewRoad road7 = new NewRoad(AssetsManager.getTextureRegion(Constants.ROAD_7_TILE_ID).getTexture(), AssetsManager.getTextureRegion(Constants.ROAD_7_TILE_BACK_ID).getTexture(), sideObjectsRightRoad7ArrayListType, sideObjectsLeftRoad7ArrayListType, staticSide7RightObjectsArrayList, staticSide7LeftObjectsArrayList, TraffictLighterEnum.TRAFFICT_LIGHTER_CHINATOWN, false);
        roads.put("TrackTypeChinaTown", road7);
        //road1.setSideObjectTypeArrayList(sideObjectsRoad1ArrayListType);


    }

    public static void resetSpeed() {
        gear = 0;
        isCollision = false;
    }


    public static void resetDtTime() {
        dtTime = 0;
    }

    public static void resetTime() {
        resetCountBusters();
        allTime = 0;
        dtTime = 0;
        dtTimeAhive = 0;
        contactPointX = 0;
        contactPointY = 0;
    }

    public static void resetContactPoint() {
        contactPointX = 0;
        contactPointY = 0;
    }

    public static Car getRandomCarForGarage() {
        ArrayList<Car> cars = new ArrayList<Car>();

        ArrayList<Car> possabilityArrayList = RandomUtil.generateRandPossabilityCarHashMap(carsTypes, getMyCars());

//        for (int i = 0; i < carsTypes.size(); i++) {
//            CarsType carsType = carsTypes.get(i);
//            for (int z = 0; z < carsType.getCars().size(); z++) {
//                Car car = carsType.getCars().get(z);
//                if (!getMyCars().contains(car.getID()))
//                    cars.add(car);
//            }
//        }
        //return cars.get(RandomUtil.getRand(0, cars.size()-1));
        return possabilityArrayList.get(RandomUtil.getRand(0, possabilityArrayList.size() - 1));
    }

    public static int getDangerousCount() {
        return dangerousCount;
    }

    public static int getRocketCount() {
        return rocketCount;
    }

    public static int getDestroyedCount() {
        return destroyedCount;
    }

    public static int getSpringBoardCount() {
        return springBoardCount;
    }

    public static int getGodModeCount() {
        return godModeCount;
    }

    public static HashMap<String, String> getStrings() {
        return strings;
    }

    public static boolean isFirstStartApp() {
        boolean isFirstStart = preferences.getBoolean(Constants.PREFERENCES_IS_FIRST_START,true);
        if(isFirstStart) preferences.putBoolean(Constants.PREFERENCES_IS_FIRST_START, false);
        preferences.flush();
        return isFirstStart;
    }

    public static void setIsFirstStartApp(boolean isFirstStartApp) {
        GameManager.isFirstStartApp = isFirstStartApp;
    }

    public void resetAchives() {
        achives = 0;
    }

    private static void loadPreferences() {
        String loadId = preferences.getString(Constants.PREFERENCES_KEY_CAR_ID);
        if (loadId.equals("")) {
            preferences.putString(Constants.PREFERENCES_KEY_CAR_ID, currentCarID);
            preferences.flush();
        } else currentCarID = loadId;
//        if (preferences.getBoolean(Constants.PREFERENCES_IS_FIRST_START, true)) {
//            isFirstStartApp = true;
//            preferences.putBoolean(Constants.PREFERENCES_IS_FIRST_START, false);
//        } else isFirstStartApp = false;

        coinCounter = preferences.getInteger(Constants.PREFERENCES_KEY_COIN_COUNT_ID, 0);
        bestAchives = preferences.getInteger(Constants.PREFERENCES_KEY_ACHIVES_COUNT_ID, 0);
        isTouchControl = preferences.getBoolean(Constants.PREFERENCES_CONTROL_ID, true);
        isSoundEnable = preferences.getBoolean(Constants.PREFERENCES_SOUND_SETTING_ID);
        Long lastCarUpdateTimePrize = preferences.getLong(Constants.PREFERENCES_LAST_CAR_PRIZE_TIME_MILLIS, 0);
        if (lastCarUpdateTimePrize.equals(0l)) {
            lastFreeCarPrize = new Date();

            preferences.putLong(Constants.PREFERENCES_LAST_CAR_PRIZE_TIME_MILLIS, lastFreeCarPrize.getTime());
            preferences.flush();
        } else {
            lastFreeCarPrize = new Date(lastCarUpdateTimePrize);

        }

        vkUser = new VkUser();
        vkUser.setHighscore(preferences.getString(Constants.PREFERENCES_VK_HIGH_SCORE));
        vkUser.setId(preferences.getString(Constants.PREFERENCES_VK_USER_ID));
        vkUser.setUser_first_name(preferences.getString(Constants.PREFERENCES_VK_FIRST_NAME));
        vkUser.setUser_last_name(preferences.getString(Constants.PREFERENCES_VK_LAST_NAME));
        vkUser.setImage_src(preferences.getString(Constants.PREFERENCES_VK_IMG_SOURCE));

    }

    public static ArrayList<GearShift> getGearShifts() {
        return gearShifts;
    }

    public static boolean isNeedFreeCarPrize() {
        if ((new Date()).after(new Date(lastFreeCarPrize.getTime() + TimeUnit.HOURS.toMillis(4)))) {
            return true;
        } else return false;
    }

    public static String timeToNextFreePrize() {
        Long currentTime = new Date().getTime();

        lastFreeCarPrize.getTime();
        Date date = new Date(currentTime - lastFreeCarPrize.getTime());
        return "";
    }

    public static void setNewFreeCarPrizeDate() {
        lastFreeCarPrize = new Date();
        preferences.putLong(Constants.PREFERENCES_LAST_CAR_PRIZE_TIME_MILLIS, (lastFreeCarPrize.getTime()));
        preferences.flush();
    }

    public static ArrayList<CarsType> getCarsTypes() {
        return carsTypes;
    }

    public static Car getCurrentCar() {
        for (int i = 0; i < carsTypes.size(); i++) {
            ArrayList<Car> carArrayList = carsTypes.get(i).getCars();
            for (int n = 0; n < carArrayList.size(); n++) {
                Car car = carArrayList.get(n);
                if (car.getID().equals(currentCarID)) return car;
            }
        }

        return null;
    }

    public static void setCurrentCarID(String id) {
        currentCarID = id;
        preferences.putString(Constants.PREFERENCES_KEY_CAR_ID, currentCarID);
        preferences.flush();
    }

    public static void setCountCoint(Integer countCoint) {
        coinCounter = countCoint;
        preferences.putInteger(Constants.PREFERENCES_KEY_COIN_COUNT_ID, coinCounter);
        preferences.flush();
    }

    public static void setBestAchives() {
        if (achives > bestAchives) {
            bestAchives = achives;
            preferences.putInteger(Constants.PREFERENCES_KEY_ACHIVES_COUNT_ID, (int) bestAchives);
            preferences.flush();

        }
    }

    public static void updateGear(float dt) {
//        System.out.println("gearShift.getSpeeds().get(gear) " + gearShift.getSpeeds().get(gear));
//        System.out.println("currentSpeed " + currentSpeed);
//        System.out.println("CurrentSpeed " + CurrentSpeed);
//        System.out.println("dtTime " + dtTime);
//        System.out.println("dtTimeAhive " + dtTimeAhive);
//        System.out.println("allTime " + allTime);
//        System.out.println("isCollision " + isCollision);
//        System.out.println("toSpeed " + toSpeed);
//        System.out.println("-----------------------------");

        if (!isPauseDtTimer()) {
            dtTime += dt;
            dtTimeAhive += dt;
        }

        allTime += dt;
        toSpeed = gearShift.getSpeeds().get(gear);
        // System.out.println("isCollision " + isCollision);
        if (gear == 0) currentSpeed = toSpeed;
        if (currentSpeed < toSpeed) currentSpeed = currentSpeed + 1f;
        if (isCollision) currentSpeed = collisionSpeed;
        GameManager.setCurrentSpeed(currentSpeed);
        label.setText(String.valueOf(currentSpeed));
        timeLabel.setText(String.valueOf(allTime));
        stageChild.setText("stageChild " + String.valueOf(stageGameManager.getActors().size));
        worldChild.setText("worldChild " + String.valueOf(worldGameManager.getBodyCount()));
        // System.out.println("Gear "+gear);
        if (dtTime > gearShift.getTimes().get(gear)) {

            if (gear + 1 < gearShift.getTimes().size()) {
                gear++;
                GearView.getView(gear);
                //stageGameManager.addActor(GearView.getView(gear));
            }

            GameManager.setCurrentSpeed(currentSpeed);
            dtTime = 0;
        }
        if (dtTimeAhive > gearShift.getTimes().get(gear) + 1) {

            if (gear + 1 < gearShift.getTimes().size()) {
//                Actor actor = AchiveView.getView(gear);
//                if (actor != null)
//                    stageGameManager.addActor(AchiveView.getView(gear));
            }

            GameManager.setCurrentSpeed(currentSpeed);
            dtTimeAhive = 0;
        }
    }

    public static void setUpLabetDebug() {

        label = new Label("0", AssetsManager.getUiSkin());
        label.setFontScale(0.5f, 0.5f);
        label.setBounds(GameRuners.WIDTH / 2 - 60, GameRuners.HEIGHT / 2 - 60, label.getWidth(), label.getHeight());
        stageGameManager.addActor(label);
    }

    public static void setUpTimeLabetDebug() {

        timeLabel = new Label("0", AssetsManager.getUiSkin());
        timeLabel.setFontScale(0.5f, 0.5f);
        timeLabel.setBounds(GameRuners.WIDTH / 2 - 60, GameRuners.HEIGHT / 2 - 80, label.getWidth(), label.getHeight());
        stageGameManager.addActor(timeLabel);
    }

    public static void setDefaultSpeed() {
        isCollision = false;
//        gear=0;
        currentSpeed = gearShift.getSpeeds().get(gear);
        CurrentSpeed = gearShift.getSpeeds().get(gear);
        toSpeed = gearShift.getSpeeds().get(gear);

    }

    public static void setUpWorldChildLabetDebug() {

        worldChild = new Label("0", AssetsManager.getUiSkin());
        worldChild.setFontScale(0.35f, 0.35f);
        worldChild.setBounds(GameRuners.WIDTH / 2 - 110, GameRuners.HEIGHT / 2 - 100, label.getWidth(), label.getHeight());
        stageGameManager.addActor(worldChild);
    }

    public static void setUpStageChildLabetDebug() {

        stageChild = new Label("0", AssetsManager.getUiSkin());
        stageChild.setFontScale(0.35f, 0.35f);
        stageChild.setBounds(GameRuners.WIDTH / 2 - 110, GameRuners.HEIGHT / 2 - 120, label.getWidth(), label.getHeight());
        stageGameManager.addActor(stageChild);
    }


    public static Integer getCoinCounter() {
        return coinCounter;
    }

    public static void buyCar(float price) {
        coinCounter -= (int) price;
        setCountCoint(coinCounter);
    }

    public static Integer addCoint() {
        coinCounter++;
        setCountCoint(coinCounter);
        return coinCounter;
    }

    public static Integer addCoin(int count) {
        coinCounter += count;
        setCountCoint(coinCounter);
        return coinCounter;
    }


    public static float getContactPointX() {
        return contactPointX;
    }

    public static void setContactPointX(float contactPointX) {
        if (GameManager.isCollision() && GameManager.getContactPointX() == 0)
            GameManager.contactPointX = contactPointX;
    }

    public static float getContactPointY() {
        return contactPointY;
    }

    public static void setContactPointY(float contactPointY) {
        if (GameManager.isCollision() && GameManager.getContactPointY() == 0)
            GameManager.contactPointY = contactPointY;
    }


    public static void setBoosterSpeed() {
        setLastSpeed(getCurrentSpeed());
        currentSpeed = getGearShifts().get(getCurrentCar().getCurveType() - 1).getSpeeds().get(Constants.MAX_GEAR);
    }

    public static void setLow30Speed() {
        setLastSpeed(getCurrentSpeed());

        if (gear != 1) currentSpeed -= currentSpeed * 0.03f;
    }

    public static float getLastSpeed() {
        return lastSpeed;
    }

    public static void setLastSpeed() {
        currentSpeed = lastSpeed;
    }

    public static void setLastSpeed(float lastSpeed) {
        GameManager.lastSpeed = lastSpeed;
    }

    public static boolean isCollisionWithCar() {
        return isCollisionWithCar;
    }

    public static void setIsCollisionWithCar(boolean isCollisionWithCar) {
        GameManager.isCollisionWithCar = isCollisionWithCar;
    }

    public static int getGear() {
        return gear;
    }


    public static boolean isCollision() {
        return isCollision;
    }

    public static void setIsCollision(boolean isCollision) {
        GameManager.isCollision = isCollision;
        System.out.println("GameManager.isCollision " + GameManager.isCollision);
    }

    public static float getAchives() {
        return achives;
    }

    public static void setAchives(float achives) {
        GameManager.achives = achives;
    }
}
