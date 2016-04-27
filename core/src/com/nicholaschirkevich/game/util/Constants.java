package com.nicholaschirkevich.game.util;

import com.nicholaschirkevich.game.GameRuners;

/**
 * Created by Nikolas on 02.03.2016.
 */
public class Constants {


    public static String PREFERENCES_KEY = "SRGameKey";
    public static String PREFERENCES_KEY_CAR_ID = "PRefsCarId";
    public static String PREFERENCES_KEY_COIN_COUNT_ID = "PRefsCoinCount";

    public static final float CAR_POS_X_LEFT = 120;
    public static final float CAR_POS_X_RIGHT = 215;
    public static final float CAR_CENTER_X = 130;
    public static final float CAR_POS_Y = -90;
    public static final float CAR_TURN_SPEED = 0;
    public static final float CAR_SPEED = 0;
    public static final float PIXELS_TO_METERS = 1;
    public static final String ROAD_ASSETS_ID = "road";
    public static final String ROAD_IMAGE_PATH = "road_1_tile.png";
    public static final String CAR_ATLAS_PATH = "car_f1_1_1.txt";
    public static final String OTHERCAR_1_1_ATLAS_PATH = "other_car_1_1_animation.txt";
    public static final String OTHERCAR_1_2_ATLAS_PATH = "other_car_1_2_animation.txt";
    public static final String OTHERCAR_1_3_ATLAS_PATH = "other_car_1_3_animation.txt";
    public static final String OTHERCAR_2_1_ATLAS_PATH = "other_car_2_1_animation.txt";
    public static final String OTHERCAR_2_2_ATLAS_PATH = "other_car_2_2_animation.txt";
    public static final String OTHERCAR_2_3_ATLAS_PATH = "other_car_2_3_animation.txt";
    public static final String OTHERCAR_3_1_ATLAS_PATH = "other_car_3_1_animation.txt";
    public static final String OTHERCAR_3_3_ATLAS_PATH = "other_car_3_3_animation.txt";
    public static final String OTHERCAR_4_1_ATLAS_PATH = "other_car_4_1_animation.txt";
    public static final String OTHERCAR_4_2_ATLAS_PATH = "other_car_4_2_animation.txt";
    public static final String OTHERCAR_4_3_ATLAS_PATH = "other_car_4_3_animation.txt";
    public static final String OTHERCAR_5_1_ATLAS_PATH = "other_car_5_1_animation.txt";
    public static final String OTHERCAR_5_2_ATLAS_PATH = "other_car_5_2_animation.txt";
    public static final String OTHERCAR_5_3_ATLAS_PATH = "other_car_5_3_animation.txt";
    public static final String CRASH_ATLAS_PATH = "crash_animation.txt";
    public static final String COIN_ATLAS_PATH = "coin.txt";
    public static final String COIN_SHADOW_ATLAS_PATH = "coin_shadow.txt";
    public static final String SKULL_ON_ROAD_ATLAS_PATH = "skull_on_road.txt";
    public static final String LADLE_ON_ROAD_ATLAS_PATH = "ladle_on_road.txt";
    public static final String BOOSTER_ON_ROAD_ATLAS_PATH = "booster_on_road.txt";
    public static final String LADLE_ON_CAR_ATLAS_PATH = "ladle.txt";
    public static final String BOOSTER_R_ATLAS_PATH = "boost_r.txt";
    public static final String BOOSTER_L_ATLAS_PATH = "boost_l.txt";
    public static final String LEFT_THRONS_ATLAS_PATH = "left_throns.txt";
    public static final String RIGHT_THRONS_ATLAS_PATH = "right_throns.txt";
    public static final String SPRING_BOARD_ATLAS_PATH = "springboard.txt";
    public static final String ROAD_BLOCK_ATLAS_PATH = "road_block.txt";
    public static final String DIRT_ATLAS_PATH = "dirt.txt";
    public static final String DIRT_FOR_SREEN_1_ATLAS_PATH = "dirt_for_screen_1.txt";
    public static final String DIRT_FOR_SREEN_2_ATLAS_PATH = "dirt_for_screen_2.txt";
    public static final String DIRT_FOR_SREEN_3_ATLAS_PATH = "dirt_for_screen_3.txt";
    public static final String DIRT_FOR_SREEN_4_ATLAS_PATH = "dirt_for_screen_4.txt";
    public static final String DIRT_FOR_SREEN_5_ATLAS_PATH = "dirt_for_screen_5.txt";
    public static final String DIRT_FOR_SREEN_6_ATLAS_PATH = "dirt_for_screen_6.txt";


    public static final int MAX_GEAR = 5;


    public static final String ROAR_ATLAS_PATH = "road_animation.txt";
    public static final String INSANE_MODE_ALPHA_LEFT_ATLAS_PATH = "insane_mode_alpha_left.txt";
    public static final String INSANE_MODE_ALPHA_RIGHT_ATLAS_PATH = "insane_mode_alpha_right.txt";
    public static final String EFFECT_BOOST_ATLAS_PATH = "effect_1.txt";
    public static final String ROAR_1_BUSH_1_ATLAS_PATH = "road_1_bush_1_animation.txt";
    public static final String ROAR_1_BUSH_2_ATLAS_PATH = "road_1_bush_2_animation.txt";
    public static final String ROAR_1_TREE_2_ATLAS_PATH = "road_1_tree_1_animation.txt";
    public static final String ROAR_1_STUMP_2_ATLAS_PATH = "road_1_stump.txt";
    public static final String ROAR_1_LIGHTER_L_ATLAS_PATH = "road_1_lighter_l_animation.txt";
    public static final String ROAR_1_LIGHTER_R_ATLAS_PATH = "road_1_lighter_r_animation.txt";
    public static final String BTTN_RESUME_ATLAS_PATH = "bttn_resume.txt";
    public static final String GARAGE_BUTTON_ATLAS_PATH = "bttn_cars.txt";
    public static final String LEFT_WING_ATLAS_PATH = "left_wing.txt";
    public static final String RIGHT_WING_ATLAS_PATH = "right_wing.txt";
    public static final String BACK_BUTTON_ATLAS_PATH = "bttn_back.txt";
    public static final String FLY_SPRINGBOARD_ATLAS_PATH = "fly_springboard.atlas";
    public static final String BTTN_RESUME_REGION_NAME = "bttn_resume";

    public static final String BTTN_PAUSE_ATLAS_PATH = "bttn_pause.txt";
    public static final String[] GARAGE_BUTTON_REGION_NAMES = new String[]{"bttn_cars"};
    public static final String[] BACK_BUTTON_REGION_NAMES = new String[]{"bttn_back"};
    public static final String[] BTTN_PAUSE_REGION_NAMES = new String[]{"bttn_pause", "bttn_pause_prssd"};

    public static final String[] FLY_SPRINGBOARD_REGION_NAMES = new String[]{"bp_1", "bp_2", "bp_2", "bp_3", "bp_4", "bp_5", "bp_6", "bp_7", "bp_8", "bp_9", "bp_10", "bp_11", "bp_12", "bp_13"};
    public static final String[] MY_CAR_REGION_NAMES = new String[]{"car_f1_1_1", "car_f1_1_2", "car_f1_1_3", "car_f1_1_4"};
    public static final String[] OTHERCAR_1_1_REGION_NAMES = new String[]{"car_move_1"};
    public static final String[] OTHERCAR_1_2_REGION_NAMES = new String[]{"other_car_1_2"};
    public static final String[] OTHERCAR_1_3_REGION_NAMES = new String[]{"other_car_1_3"};
    public static final String[] OTHERCAR_2_1_REGION_NAMES = new String[]{"car_move_1"};
    public static final String[] OTHERCAR_2_2_REGION_NAMES = new String[]{"other_car_2_2"};
    public static final String[] OTHERCAR_2_3_REGION_NAMES = new String[]{"other_car_2_3"};
    public static final String[] OTHERCAR_3_1_REGION_NAMES = new String[]{"other_car_3_1"};
    public static final String[] OTHERCAR_3_3_REGION_NAMES = new String[]{"other_car_3_3"};
    public static final String[] OTHERCAR_4_1_REGION_NAMES = new String[]{"other_car_4_1"};
    public static final String[] OTHERCAR_4_2_REGION_NAMES = new String[]{"other_car_4_2"};
    public static final String[] OTHERCAR_4_3_REGION_NAMES = new String[]{"other_car_4_3"};
    public static final String[] OTHERCAR_5_1_REGION_NAMES = new String[]{"other_car_5_1"};
    public static final String[] OTHERCAR_5_2_REGION_NAMES = new String[]{"other_car_5_2"};
    public static final String[] OTHERCAR_5_3_REGION_NAMES = new String[]{"other_car_5_3"};
    public static final String[] CRASH_REGION_NAMES = new String[]{"clash_anim_left_right_1","clash_anim_left_right_2","clash_anim_left_right_3","clash_anim_left_right_4","clash_anim_left_right_5","clash_anim_left_right_6"};
    public static final String[] LEFT_WING_REGION_NAMES = new String[]{"left_wing"};
    public static final String[] RIGHT_WING_REGION_NAMES = new String[]{"right_wing"};
    public static final String[] EFFECT_BOOST_REGION_NAMES = new String[]{"effect_1"};
    public static final String[] INSANE_MODE_ALPHA_LEFT_REGION_NAMES = new String[]{"insane_mode_alpha_left"};
    public static final String[] INSANE_MODE_ALPHA_RIGHT_REGION_NAMES = new String[]{"insane_mode_alpha_right"};
    public static final String[] SPRING_BOARD_REGION_NAMES = new String[]{"springboard"};
    public static final String[] ROAD_BLOCK_REGION_NAMES = new String[]{"road_block"};
    public static final String[] DIRT_REGION_NAMES = new String[]{"dirt"};
    public static final String[] DIRT_FOR_SCREEN_1_REGION_NAMES = new String[]{"dirt_for_screen_1"};
    public static final String[] DIRT_FOR_SCREEN_2_REGION_NAMES = new String[]{"dirt_for_screen_2"};
    public static final String[] DIRT_FOR_SCREEN_3_REGION_NAMES = new String[]{"dirt_for_screen_3"};
    public static final String[] DIRT_FOR_SCREEN_4_REGION_NAMES = new String[]{"dirt_for_screen_4"};
    public static final String[] DIRT_FOR_SCREEN_5_REGION_NAMES = new String[]{"dirt_for_screen_5"};
    public static final String[] DIRT_FOR_SCREEN_6_REGION_NAMES = new String[]{"dirt_for_screen_6"};
    public static final String[] COIN_REGION_NAMES = new String[]{"coin"};
    public static final String[] SKULL_ON_ROAD_REGION_NAMES = new String[]{"skull_on_road"};
    public static final String[] BOOSTER_ON_ROAD_REGION_NAMES = new String[]{"booster_on_road"};
    public static final String[] LADLE_ON_ROAD_REGION_NAMES = new String[]{"ladle_on_road"};
    public static final String[] LADLE_ON_CAR_REGION_NAMES = new String[]{"ladle"};
    public static final String[] BOOSTER_R_REGION_NAMES = new String[]{"boost_r"};
    public static final String[] LEFT_THRONS_REGION_NAMES = new String[]{"left_throns"};
    public static final String[] RIGHT_THRONS_REGION_NAMES = new String[]{"right_throns"};
    public static final String[] BOOSTER_L_REGION_NAMES = new String[]{"boost_l"};
    public static final String[] COIN_SHADOW_REGION_NAMES = new String[]{"shadow"};
    public static final String[] ROAD_REGION_NAMES = new String[]{"road_move_1"};
    public static final String[] ROAD_1_BUSH_1_REGION_NAMES = new String[]{"road_1_bush_1_move_1"};
    public static final String[] ROAD_1_BUSH_2_REGION_NAMES = new String[]{"road_1_bush_2_move_1"};
    public static final String[] ROAD_1_TREE_1_REGION_NAMES = new String[]{"road_1_tree_1_move_1"};
    public static final String[] ROAD_1_STUMP_1_REGION_NAMES = new String[]{"road_1_stump"};
    public static final String[] ROAD_1_LIGHTER_L_REGION_NAMES = new String[]{"road_1_lighter_l_move_1"};
    public static final String[] ROAD_1_LIGHTER_R_REGION_NAMES = new String[]{"road_1_lighter_r_move_1"};

    public static final String MY_CAR_ASSETS_ID = "my_car";
    public static final String OTHERCAR_1_1_ASSETS_ID = "other_car_1_1";
    public static final String OTHERCAR_1_2_ASSETS_ID = "other_car_1_2";
    public static final String OTHERCAR_1_3_ASSETS_ID = "other_car_1_3";
    public static final String OTHERCAR_2_1_ASSETS_ID = "other_car_2_1";
    public static final String OTHERCAR_2_2_ASSETS_ID = "other_car_2_2";
    public static final String OTHERCAR_2_3_ASSETS_ID = "other_car_2_3";
    public static final String OTHERCAR_3_1_ASSETS_ID = "other_car_3_1";
    public static final String OTHERCAR_3_3_ASSETS_ID = "other_car_3_3";
    public static final String OTHERCAR_4_1_ASSETS_ID = "other_car_4_1";
    public static final String OTHERCAR_4_2_ASSETS_ID = "other_car_4_2";
    public static final String OTHERCAR_4_3_ASSETS_ID = "other_car_4_3";
    public static final String OTHERCAR_5_1_ASSETS_ID = "other_car_5_1";
    public static final String OTHERCAR_5_2_ASSETS_ID = "other_car_5_2";
    public static final String OTHERCAR_5_3_ASSETS_ID = "other_car_5_3";
    public static final String LEFT_WING_ASSETS_ID = "left_wing";
    public static final String RIGHT_WING_ASSETS_ID = "right_wing";
    public static final String INSANE_MODE_ALPHA_LEFT_ASSETS_ID = "insane_mode_alpha_left";
    public static final String INSANE_MODE_ALPHA_RIGHT_ASSETS_ID = "insane_mode_alpha_right";
    public static final String SPRING_BOARD_ASSETS_ID = "springboard";
    public static final String ROAD_BLOCK_ASSETS_ID = "road_block";
    public static final String DIRT_ASSETS_ID = "dirt";
    public static final String DIRT_ON_SCREEN_1_ASSETS_ID = "dirt_on_screen_1";
    public static final String DIRT_ON_SCREEN_2_ASSETS_ID = "dirt_on_screen_2";
    public static final String DIRT_ON_SCREEN_3_ASSETS_ID = "dirt_on_screen_3";
    public static final String DIRT_ON_SCREEN_4_ASSETS_ID = "dirt_on_screen_4";
    public static final String DIRT_ON_SCREEN_5_ASSETS_ID = "dirt_on_screen_5";
    public static final String DIRT_ON_SCREEN_6_ASSETS_ID = "dirt_on_screen_6";
    public static final String CRASH_ASSETS_ID = "crash_car_animation";
    public static final String EFFECT_BOOST_ASSETS_ID = "effect_1";
    public static final String COIN_ASSETS_ID = "coin";
    public static final String SKULL_ON_ROAD_ASSETS_ID = "skull_on_road";
    public static final String COIN_SHADOW_ASSETS_ID = "shadow";
    public static final String LADLE_ON_ROAD_ASSETS_ID = "ladle";
    public static final String LADLE_ON_CAR_ASSETS_ID = "ladle_on_car";
    public static final String BOOSTER_ON_ROAD_ASSETS_ID = "booster";
    public static final String BOOST_R_ASSETS_ID = "boost_l";
    public static final String BOOST_L_ASSETS_ID = "boost_r";
    public static final String RIGHT_THRONS_ASSETS_ID = "right_throns";
    public static final String LEFT_THRONS_ASSETS_ID = "left_throns";
    //public static final String MY_CAR_STATIC_ASSETS_ID = "my_car_static";
    public static final String ROAD_STATIC_ASSETS_ID = "road_static";
    public static final String ROAD_1_BUSH_1_STATIC_ASSETS_ID = "road_1_bush_1_static";
    public static final String ROAD_1_BUSH_2_STATIC_ASSETS_ID = "road_1_bush_2_static";
    public static final String ROAD_1_TREE_1_STATIC_ASSETS_ID = "road_1_tree_1_static";
    public static final String ROAD_1_STUMP_1_STATIC_ASSETS_ID = "road_1_stump_1_static";
    public static final String ROAD_1_LIGHTER_L_STATIC_ASSETS_ID = "road_1_lighter_l_static";
    public static final String ROAD_1_LIGHTER_R_STATIC_ASSETS_ID = "road_1_lighter_r_static";
    public static final String FLY_SPRINGBOARD_ASSETS_ID = "fly_springboard";

    public static final String PASSER_CAR_1_ID = "passer_car_1";
    public static final String PASSER_CAR_2_ID = "passer_car_2";
    //    public static final String ROAD_1_BUSH_1_ID = "road_1_bush_1";
//    public static final String ROAD_1_BUSH_2_ID = "road_1_bush_2";
//    public static final String ROAD_1_TREE_1_ID = "road_1_tree_1";
    public static final String GEAR_1_ID = "gear_1";
    public static final String GEAR_2_ID = "gear_2";
    public static final String GEAR_3_ID = "gear_3";
    public static final String GEAR_4_ID = "gear_4";
    public static final String GEAR_5_ID = "gear_5";
    public static final String GEAR_6_ID = "gear_6";

    public static final String X1_id = "x1";
    public static final String X2_id = "x2";
    public static final String X3_id = "x3";
    public static final String X4_id = "x4";
    public static final String X5_id = "x5";
    public static final String X6_id = "x6";


    //public static final String MY_CAR_1_IMAGE_PATH = "car_f1_1_1.png";
    public static final String PASSER_CAR_1_IMAGE_PATH = "other_car_1_1.png";
    public static final String PASSER_CAR_2_IMAGE_PATH = "other_car_2_1.png";
    public static final String ROAD_1_BUSH_1_IMAGE_PATH = "road_1_bush_1.png";
    public static final String ROAD_1_BUSH_2_IMAGE_PATH = "road_1_bush_2.png";
    public static final String ROAD_1_TREE_1_IMAGE_PATH = "road_1_tree_1.png";
    public static final String GEAR_1_IMAGE_PATH = "1_gear.png";
    public static final String GEAR_2_IMAGE_PATH = "2_gear.png";
    public static final String GEAR_3_IMAGE_PATH = "3_gear.png";
    public static final String GEAR_4_IMAGE_PATH = "4_gear.png";
    public static final String GEAR_5_IMAGE_PATH = "5_gear.png";
    public static final String GEAR_6_IMAGE_PATH = "6_gear.png";
    public static final String X1_IMAGE_PATH = "x1.png";
    public static final String X2_IMAGE_PATH = "x2.png";
    public static final String X3_IMAGE_PATH = "x3.png";
    public static final String X4_IMAGE_PATH = "x4.png";
    public static final String X5_IMAGE_PATH = "x5.png";
    public static final String X6_IMAGE_PATH = "x6.png";


    public static float getCarPostitionXRight(float width) {
        return CAR_POS_X_RIGHT - width / 2;
    }

    public static float getCarPostitionXLeft(float width) {
        return CAR_POS_X_LEFT - width / 2;
    }


    public static final float ROAD_HOLE_X = 165;
    public static final float ROAD_HOLE_Y = 260;

    public static final float PAUSE_BTTN_X_VISIBLE = GameRuners.WIDTH / 8 - 50;
    public static final float PAUSE_BTTN_X_INVISIBLE = -50;
    public static final float PAUSE_BTTN_Y = GameRuners.HEIGHT / 2 - 50;

    public static final float GARAGE_BTTN_X_VISIBLE = GameRuners.WIDTH / 8 - 50;
    public static final float GARAGE_BTTN_X_INVISIBLE = -50;
    public static final float GARAGE_BTTN_Y = GameRuners.HEIGHT / 2 - 500;

    public static final float SOUND_BTTN_X_VISIBLE = GameRuners.WIDTH / 4;
    public static final float SOUND_BTTN_Y_VISIBLE = GameRuners.HEIGHT / 2.8f;

    public static final float CONTROL_BTTN_X_VISIBLE = GameRuners.WIDTH / 4;
    public static final float CONTROL_BTTN_Y_VISIBLE = GameRuners.HEIGHT / 3.4f;

    public static final float BLOCK_BTTN_X_VISIBLE = GameRuners.WIDTH / 4;
    public static final float BLOCK_BTTN_Y_VISIBLE = GameRuners.HEIGHT / 4.2f;

    public static final float RESTORE_BTTN_X_VISIBLE = GameRuners.WIDTH / 4;
    public static final float RESTORE_BTTN_Y_VISIBLE = GameRuners.HEIGHT / 5.5f;

    public static final float SING_IN_VK_BTTN_X_VISIBLE = GameRuners.WIDTH / 4;
    public static final float SING_IN_VK_Y_VISIBLE = GameRuners.HEIGHT / 8f;

    public static final float SING_IN_FB_BTTN_X_VISIBLE = GameRuners.WIDTH / 4;
    public static final float SING_IN_FB_Y_VISIBLE = GameRuners.HEIGHT / 14f;

    public static final float BACK_BTTN_X_VISIBLE = GameRuners.WIDTH / 20f;
    public static final float BACK_BTTN_Y_VISIBLE = GameRuners.HEIGHT / 18f;

    public static final float RESUME_BTTN_X_VISIBLE = GameRuners.WIDTH / 4;
    public static final float RESUME_BTTN_X_INVISIBLE = GameRuners.WIDTH / 4;
    public static final float RESUME_BTTN_Y_VISIBLE = GameRuners.HEIGHT / 12f;
    public static final float RESUME_BTTN_Y_INVISIBLE = -200;

    public static final float PLAY_ONLINE_BTTN_X_VISIBLE = GameRuners.WIDTH / 4;
    public static final float PLAY_ONLINE_BTTN_Y_VISIBLE = GameRuners.HEIGHT / 7f;

    public static final float CAR_SHOP_BTTN_X_VISIBLE = GameRuners.WIDTH / 20f;
    public static final float CAR_SHOP_BTTN_Y_VISIBLE = GameRuners.HEIGHT / 18f;

    public static final float COIN_SHOP_BTTN_X_VISIBLE = GameRuners.WIDTH / 20f;
    public static final float COIN_SHOP_BTTN_Y_VISIBLE = GameRuners.HEIGHT / 9.5f;

    public static final float LEADERBOARDS_BTTN_X_VISIBLE = GameRuners.WIDTH /2.2f;
    public static final float LEADERBOARDS_BTTN_Y_VISIBLE = GameRuners.HEIGHT / 6.5f;

    public static final float LEADERBOARD_BTTN_X_VISIBLE = GameRuners.WIDTH /2.2f;
    public static final float LEADERBOARD_BTTN_Y_VISIBLE = GameRuners.HEIGHT / 9.5f;
    public static final float SETTING_BTTN_X_VISIBLE = GameRuners.WIDTH /2.2f;
    public static final float SETTING_BTTN_Y_VISIBLE = GameRuners.HEIGHT / 18f;

    public static final float PRIZE_BTTN_X_VISIBLE = GameRuners.WIDTH / 4;
    public static final float PRIZE_BTTN_Y_VISIBLE = GameRuners.HEIGHT / 5f;

    public static final float LOGO_POSITION_X= GameRuners.WIDTH / 28;
    public static final float LOGO_POSITION_Y = GameRuners.HEIGHT / 3.2f;


    public static final float GAME_OVER_LOGO_POSITION_X= GameRuners.WIDTH / 6.8f;
    public static final float GAME_OVER_LOGO_POSITION_Y = GameRuners.HEIGHT / 2.4f;

    public static final float SETTING_LOGO_POSITION_X= GameRuners.WIDTH / 20;
    public static final float SETTING_LOGO_POSITION_Y = GameRuners.HEIGHT /2.3f;

    public static final float SAVE_ME_BONUS_X= GameRuners.WIDTH / 3.35f;
    public static final float SAVE_ME_BONUS_Y = GameRuners.HEIGHT / 5.3f;

    public static final String GEAR_SHIFT_FILE_PATH = "gear_shift.xml";
    public static final String GEAR_SHIFT_FILE_SPEED_KEY = "speeds";
    public static final String GEAR_SHIFT_FILE_CAR_GEARS = "car_gears";
    public static final String GEAR_SHIFT_FILE_TIME_KEY = "times";
    public static final String GEAR_SHIFT_FILE_SPEED_VALUE_KEY = "speed";
    public static final String GEAR_SHIFT_FILE_TIME_VALUE_KEY = "time";

    public static float passerCarDistance = 200;
    public static float coinDistance = 20;


    public static final String CARS_TYPE_FILE_PATH = "cars.xml";
    public static final String CARS_TYPE_ROOT = "CarsTypes";
    public static final String CARS_TYPE = "CarsType";
    public static final String CARS_TYPE_NAME = "TypeName";
    public static final String CARS_TYPE_SPORT_CARS = "SportCars";
    public static final String CARS_TYPE_ORDER = "Order";
    public static final String CARS_TYPE_CARS = "Cars";
    public static final String CARS_TYPE_CAR = "Car";
    public static final String CARS_TYPE_TITLE_IMAGE = "TitleImage";
    public static final String CARS_TYPE_TITLE_IMAGE_NAME = "TitleImageName";
    public static final String CARS_TYPE_CAR_NAME = "Name";
    public static final String CARS_TYPE_CAR_NAME_TEXT = "CarNameText";
    public static final String CARS_TYPE_CAR_MAP_TYPE = "MapType";
    public static final String CARS_TYPE_CAR_SPEED = "Speed";
    public static final String CARS_TYPE_CAR_WEIGHT = "Weight";
    public static final String CARS_TYPE_CAR_CURVE_TYPE = "CurveType";
    public static final String CARS_TYPE_CAR_BRAKE_DURATION = "BrakeDuration";
    public static final String CARS_TYPE_CAR_RARE_STATUS = "RareStatus";
    public static final String CARS_TYPE_CAR_POSITION = "Position";
    public static final String CARS_TYPE_CAR_IS_FOR_REAL_MONEY = "isForRealMoney";
    public static final String CARS_TYPE_CAR_BRAKE_LINES = "BrakeLines";
    public static final String CARS_TYPE_CAR_POSSABILITY = "Possability";
    public static final String CARS_TYPE_CAR_BRAKE_LINES_LEFT_LINE_START = "leftLineStart";
    public static final String CARS_TYPE_CAR_BRAKE_LINES_RIGHT_LINE_START = "rightLineStart";
    public static final String CARS_TYPE_CAR_BRAKE_LINES_WEIGHT_OF_LINE = "weightOfLine";
    public static final String CARS_TYPE_CAR_BRAKE_LEFT_ROCKET_POSTITION = "LeftRocketPosition";
    public static final String CARS_TYPE_CAR_BRAKE_RIGHT_ROCKET_POSTITION = "RightRocketPosition";
    public static final String CARS_X = "X";
    public static final String CARS_Y = "Y";
    public static final String CARS_TYPE_CAR_ID = "ID";
    public static final String CARS_TYPE_CAR_STATUS = "Status";
    public static final String CARS_TYPE_CAR_PRICE = "Price";
    public static final String CARS_TYPE_CAR_NORMAL_TEXTURES = "NormalTextures";
    public static final String CARS_TYPE_CAR_BROKEN_TEXTURES = "BrokenTextures";
    public static final String CARS_TYPE_CAR_NAME_IMEGE = "CarNameImage";



    public static final float PASSER_CAR_LINEAR_DUMPING = 2.2f;
    public static final float PASSER_CAR_ANGULAR_DUMPING = 3f;
    public static final float MY_CAR_LINEAR_DUMPING = 2f;
    public static final float MY_CAR_ANGULAR_DUMPING = 3f;

    public static final float TIME_RELAX_ZONE_START=30;
    public static final float DURATION_RELAX_ZONE=2;
    public static final float TIME_SPRINGBOARD=31;

    public static final short MY_CAR_FILTER_ENTITY = 0x8;
    public static final short FLY_SPRING_BOARD_MASK = 0x14;
    public static final short SKULL_MASK = 0x4;
    public static final short BLOCK_MASK = 0x11;
    public static final short SPRING_BOARD_MASK = 0x10;
    public static final short PASSER_CAR_FILTER_ENTITY = 0x1 << 1; // 0010 or 0x2 in hex
    public static final short PHYSICS_ENTITY = 0x1;    // 0001
    public static final short WORLD_ENTITY = 0x1 << 1;
//Cars animation atlas path


}
