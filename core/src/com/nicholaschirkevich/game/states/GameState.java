package com.nicholaschirkevich.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.nicholaschirkevich.game.GameRuners;
import com.nicholaschirkevich.game.actions.Landing;
import com.nicholaschirkevich.game.actions.Prize;
import com.nicholaschirkevich.game.admob.ActionResolver;
import com.nicholaschirkevich.game.entity.Car;
import com.nicholaschirkevich.game.entity.CarsType;
import com.nicholaschirkevich.game.enums.ToastLength;
import com.nicholaschirkevich.game.filters.CarContactListener;
import com.nicholaschirkevich.game.filters.CarFilter;
import com.nicholaschirkevich.game.interfaces.CarTurnInterface;
import com.nicholaschirkevich.game.interfaces.DirtListener;
import com.nicholaschirkevich.game.interfaces.GenerateHoleAfterLadle;
import com.nicholaschirkevich.game.interfaces.ListenerAddBoost;
import com.nicholaschirkevich.game.interfaces.ListenerAddLadle;
import com.nicholaschirkevich.game.interfaces.OnCrushCarListener;
import com.nicholaschirkevich.game.interfaces.OnGearUp;
import com.nicholaschirkevich.game.interfaces.OnSetCollisionCars;
import com.nicholaschirkevich.game.interfaces.OnStartRelaxZone;
import com.nicholaschirkevich.game.interfaces.OnTrafficLightListener;
import com.nicholaschirkevich.game.interfaces.PauseAfterCollision;
import com.nicholaschirkevich.game.interfaces.ResumeButtonListener;
import com.nicholaschirkevich.game.interfaces.ResumeFromPause;
import com.nicholaschirkevich.game.interfaces.SetGodMode;
import com.nicholaschirkevich.game.interfaces.UpdateCoinCount;
import com.nicholaschirkevich.game.interfaces.ZoomCarListener;
import com.nicholaschirkevich.game.menu.MenuGameOver;
import com.nicholaschirkevich.game.menu.MenuPause;
import com.nicholaschirkevich.game.menu.MenuSaveMe;
import com.nicholaschirkevich.game.menu.MainMenu;
import com.nicholaschirkevich.game.menu.PauseButton;
import com.nicholaschirkevich.game.model.MyCar;
import com.nicholaschirkevich.game.model.PasserCar;
import com.nicholaschirkevich.game.model.boosters.BoostOnCarLeft;
import com.nicholaschirkevich.game.model.boosters.BoostOnCarRight;
import com.nicholaschirkevich.game.model.boosters.Booster;
import com.nicholaschirkevich.game.model.boosters.Coin;
import com.nicholaschirkevich.game.model.boosters.Dirt;
import com.nicholaschirkevich.game.model.boosters.DirtOnScreen;
import com.nicholaschirkevich.game.model.boosters.FlySpringboard;
import com.nicholaschirkevich.game.model.boosters.Ladle;
import com.nicholaschirkevich.game.model.boosters.LadleOnCar;
import com.nicholaschirkevich.game.model.boosters.Skull;
import com.nicholaschirkevich.game.model.boosters.Springboard;
import com.nicholaschirkevich.game.model.boosters.ThronsOnCarLeft;
import com.nicholaschirkevich.game.model.boosters.ThronsOnCarRight;
import com.nicholaschirkevich.game.model.boosters.TutorialBlocks;
import com.nicholaschirkevich.game.model.boosters.TutorialSpringboard;
import com.nicholaschirkevich.game.model.boosters.WingOnCarLeft;
import com.nicholaschirkevich.game.model.boosters.WingOnCarRight;
import com.nicholaschirkevich.game.model.effects.EffectBooster;
import com.nicholaschirkevich.game.model.effects.EffectMode;
import com.nicholaschirkevich.game.model.side_objects.NewRoad;
import com.nicholaschirkevich.game.model.side_objects.RoadHole;
import com.nicholaschirkevich.game.unput.GestureListnener;
import com.nicholaschirkevich.game.userdata.BoosterDataType;
import com.nicholaschirkevich.game.userdata.CoinDataType;
import com.nicholaschirkevich.game.userdata.DirtDataType;
import com.nicholaschirkevich.game.userdata.FlySpringBoardDataType;
import com.nicholaschirkevich.game.userdata.LadleOnRoadDataType;
import com.nicholaschirkevich.game.userdata.MyCarDataType;
import com.nicholaschirkevich.game.userdata.PasserCarDataType;
import com.nicholaschirkevich.game.userdata.SkullOnRoadDataType;
import com.nicholaschirkevich.game.userdata.SpringBoardDataType;
import com.nicholaschirkevich.game.util.AssetsManager;
import com.nicholaschirkevich.game.util.Constants;
import com.nicholaschirkevich.game.util.GameManager;
import com.nicholaschirkevich.game.util.RandomUtil;
import com.nicholaschirkevich.game.util.ToastHelper;

import java.util.ArrayList;


public class GameState extends State implements OnSetCollisionCars, ResumeFromPause, OnTrafficLightListener, OnCrushCarListener, OnGearUp, ResumeButtonListener, PauseButton.pauseButtonListener, PauseAfterCollision, UpdateCoinCount, OnStartRelaxZone, ListenerAddBoost, ListenerAddLadle, GenerateHoleAfterLadle, SetGodMode, ZoomCarListener, DirtListener, CarTurnInterface {

    public static final int ACHIVE_COEFFICIENT = 35;
    public static final int LABEL_ACHIVE_PADDING_WIDTH = 160;
    public static final int LABEL_ACHIVE_PADDING_HEIGHT = 30;
    public static final float WORLD_STEP = 60f;
    public static final int VELOCITYI_TERATIONS = 6;
    public static final int POSITION_ITERATIONS = 2;
    public static final int TIME_TO_BOOST = 3;
    public static final int TIME_TO_END_BOOST_TIME = 5;
    public static final int INDEX = 0;
    public static final int INITIAL_BOOST_TIME = 0;
    public static final int DEFAULT_CAR_COUNT = 0;
    public static final int APPEND_REGION_HEIGHT = 10;
    public static final String AFTER_PAUSE_LABEL_THREE = "3";
    public static final String AFTER_PAUSE_LABEL_TWO = "2";
    public static final String AFTER_PAUSE_LABEL_ONE = "1";
    public static final double MIN_MY_CAR_ALFA = 0.04;
    public static final double MAX_MY_CAR_ALFA = 0.9;
    public static final float COEFFICIENT_ALFA = 0.05f;
    public static final int TIME_TO_DIR = 3;
    public static final int TIME_AFTER_PAUSE = 3;
    public static final int MIN_THREE_LABEL_VISIBLE_TIME = 0;
    public static final int MAX_THREE_LABEL_VISIBLE_TIME = 1;
    public static final int MIN_TWO_LABEL_VISIBLE_TIME = 1;
    public static final int MAX_TWO_LABEL_VISIBLE_TIME = 2;
    public static final String EMPTY_STRING_VALUE = "";
    public static final int MIN_ONE_LABEL_VISIBLE_TIME = 2;
    public static final int MAX_ONE_LABEL_VISIBLE_TIME = 3;
    public static final int ALFA = 1;
    public static final double MIN_SET_EMTY_VALUE_TIME = 2.9;
    public static final int MAX_SET_EMPTY_VELUE_TIME = 3;
    public static final int DENGEROUS_EVOLUTION_ACHIVES_COUNT = 50;
    public static final String DENGEROUS_EVOLUTION_LABEL_MESSAGE = "+50";
    public static final String DEFAULT_FONT = "default-font";
    public static final int SCULL_ACHIVE_COUNTS = 100;
    public static final int SCULL_ACHIVE_COLLISION_WITH_PASSER_CAR = 100;
    public static final int CURRENT_SPEED_APPEND_VAL = 100;
    public static final int VECTOR_X = 5;
    public static final int VECTOR_Y = 5;
    public static final int LINEAR_IMPULS_VALUE = 330;
    public static final int MY_CAR_COLLISION_LINEAR_IMPULS = 730;
    public static final int MY_CAR_COLIISION_APPEND_SPEED = 50;
    public static final int MY_CAR_VECTOR_Y = 25;
    public static final int ACHIVE_FLY_APPEND = 100;
    public static final int SPRINGBOARD_X = 90;
    public static final int SPRINGBOARD_Y = 800;
    public static final float WING_LEFT_APPEND_X = 7f;
    public static final float WING_RIGHT_APPEND_X = 5f;
    public static final int SWIPE_POSITION_RIGHT_X = 160;
    public static final int SWIPE_POSITION_RIGHT_Y = 160;
    public static final int TAP_POSITION_RIGHT_X = 180;
    public static final int TAP_POSITION_RIGHT_Y = 160;
    public static final float ROTATION_RIGHT = 0f;
    public static final float ROTATION_LEFT = 180f;
    public static final int SWIPE_POSITION_X_LEFT = 20;
    public static final int SWIPE_POSITION_Y_LEFT = 160;
    public static final int TAP_POSITION_X_LEFT = 80;
    public static final int TAP_POSITION_Y_LEFT = 160;
    public static final int SCALE_X = 1;
    public static final int SCALE_Y = 1;
    public static final double TIME_PLAY_ANIMATION = 0.5;
    public static final float ZOOM = 0.02f;
    public static final int ZOOM_MY_CAR_MIN = 1;
    public static final double ZOOM_MY_CAR_MAX = 1.3;
    public static final int ADD_COIN_COUNT = 179;
    public static final float LABEL_ACHIVE_FONT_SCALE_X = 0.60f;
    public static final float LABEL_ACHIVE_FONT_SCALE_Y = 0.60f;
    public static final int LABEL_ACHIVE_WIDTH_SHIFT = 160;
    public static final int LABEL_ACHIVE_HEIGHT_SHIFT = 30;
    public static final int MY_CAR_DEFAULT_Y = 250;
    public static final int MY_CAR_DEFAYLT_MOVEMENT = 10;
    public static final double CAR_MOVE_TIME = 0.1;
    public static final int CAMERA_POS_X_MOVE = 160;
    public static final int CAMERA_LEFT_MOVE = 155;
    public static final int CAMERA_RIGHT_MOVE = 165;
    public static final float CAMERA_SHIFT = 4f;
    public static final int ANGULAR_VELOCITY = 7;
    public static final double TIME_TO_SHOW_TUTORIAL_LEFT = 1.5;
    public static final double TIME_TO_SHOW_TUTORIAL_RIGHT = 1.5;
    public static final int SPRINGBOARD_DEFAULT_X = 90;
    public static final int BLOCK_DEFAULT_X = 90;
    public static final int APPEND_Y = 600;
    public static final int FIRST_START_PASSER_CAR_APPEND_Y = 700;
    public static final int DEFAULT_MOVEMENT = 10;
    public static final int TUTORIAL_CARS_TIME = 8;
    public static final int TUTORIAL_CARS_TIME_2 = 10;
    public static final int DEFAULT_PASSER_CAR_X = 90;
    public static final int TUTORIAL_SPRINGBOARD_TIME = 12;
    public static final int DEFAULT_SPRINGBOARD_X = 90;
    public static final int DEFAULT_SPRINGBOARD_Y = 900;
    public static final int SPRINGBOARD_MOVEMENT = 10;
    public static final double SPRING_JUMP_TIME = 0.4;
    public static final int SPRINGBOARD_START_TIME = 3;
    public static final int SPRINGBOARD_STOP_TIME = 5;
    public static final int PASSER_CAR_LANDING_Y = 500;
    public static final int FIRST_START_TUTORIAL_DURATION = 15;
    public static final int GODEMODE_START_TIME = 3;
    public static final int DEFAULT_SKULL_CAR_COUNT = 1;
    public static final int GODE_MODE_STOP_TIME = 5;
    public static final int LADEL_ACHIVES = 100;
    public static final String LADEL_ACHIVES_TEXT = "+100";
    public static final int MAX_CAMERA_POS = 370;
    public static final int CAMERA_SPEED = 440;
    public static final int MIN_SPEED = 10;
    public static final int LOW_SPEED = 7;
    public static final int MAX_DISTANCE = 100;
    public static final int COLLISION_SPEED = 50;
    public static final int MAX_SAVE_ME_DISTANCE = 100;
    public static final int MIN_LEFT_DANGEROUS_EVOLUTION = 95;
    public static final int MAX_LEFT_DANGEROUS_EVOLUTION = 180;
    public static final int ACHIVE_BOOSTER = 100;
    public static final int DISTANRE_BOOSTER_TURN = 50;
    public static final float FORCE_X = 2f;
    public static final float FORCE_Y = 4f;
    public static final int TUTORIAL_VLOCK_DEFULAT_Y = 1100;
    public static final int TUTORIAL_BLOCK_DEFULT_X = 90;
    public static final int DEFAULT_MOVE_TUTORIAL_BLOCK = 10;
    private GameState gameState;
    MyCar myCar;

    NewRoad roadNew;
    EffectBooster effectBooster;
    EffectMode effectMode;
    private Label labelCoinCount;

    private ImageButton imageButton;
    private Label labelAchives;
    private boolean collisionCameraMove = false;
    private boolean collisionCameraMoveLeft = false;
    private float cameraMoveTime = 0;

    private boolean isFirstStart = false;

    ArrayList<PasserCar> passerCars;


    ArrayList<Coin> coins;
    ArrayList<Skull> skulls;
    ArrayList<Ladle> ladles;
    ArrayList<Booster> boosters;
    ArrayList<Springboard> springboards;
    ArrayList<TutorialBlocks> tutorialBlocksesArrayList;
    ArrayList<TutorialSpringboard> tutorialSpringboardArrayList;
    MenuSaveMe menuSaveMe;
    public GestureListnener gestureListnener;


    private float skullAchives = 0;
    private float skullCarCount = 1;
    private float timeToCoin = 0;
    private float achives = 0;
    private float distacne = 0;
    private float achivesBooster = 0;
    private float achivesFly = 0;
    private Texture textureCollisisonPoint;
    private boolean isBost = false;
    private boolean isDirt = false;
    private boolean isDirtUpdate = false;
    private boolean isLadle = false;

    //////////////////save me
    private boolean isSavedMe = false;

    private boolean isAfterPauseUpdate = false;
    private boolean isStartAfterPause = false;

    private ParticleEffect pf;
    private ParticleEffect pfl;
    private float springboardtime = 0;


    private float tutorialSpringboardsTime = 0;
    private float tutorialBlocksTime = 0;
    private int tutorialBlocksCount = 0;

    private float tutorialCarsTime = 0;
    private float tutorialCarsTime2 = 0;

    private float timer = 0;


    final float PIXELS_TO_METERS = 100f;
    World world;
    CarFilter carFilter;

    private Stage stage;


    private TextButton pauseTextButton;
    private Image pauseButtonImageUp, pauseButtonImageDown;
    private Texture pauseButtonTextureUp, pauseButtonTextureDown;

    private float timeTutorialLeft = 0;
    private float timeTutorialRight = 0;
    private boolean showTutorialLeft = false;
    private boolean showTutorialRight = false;


    private float timeToTutorialStep1 = 3.5f;
    private boolean isShowStep1 = false;
    private float timeToTutorialStep2 = 6;
    private boolean isShowStep2 = false;
    private float timeToTutorialStep3 = 9;
    private boolean isShowStep3 = false;
    private float timeToTutorialStep4 = 11.5f;
    private boolean isShowStep4 = false;


    private Label afterPauseLabel;
    private float contactFlyCars = 1;
    private float playTimeAnimation = 0;
    private float tutorialControlAnimationTime = 0;
    private boolean isPause = true;
    private boolean isGameStart = false;
    private boolean isAfterPause = false;
    private boolean isStartTrafficLighter = false;
    private boolean isAutoTurn = false;
    private boolean updateLadle = false;
    private boolean isZoomCar = false;
    private boolean isZoomCarUpdate = false;
    private boolean isJumpCar = false;
    private boolean isJumpCarUpdate = false;
    private float boostTime = 0;
    private float zoomMyCarX = 1;
    private float zoomMyCarY = 1;
    private float godMedeTime = 0;
    private boolean isZoomOut = false;
    private float springBoardTime = 0;
    private float springJumpTime = 0;
    private float timeAfterPause = 0;
    private LadleOnCar ladleOnCar;
    private boolean isMode = false;
    private boolean isUpdateGodeMode = false;
    private float dirTime = 0;
    private boolean isMyCarCollision = false;
    private boolean isMyCarCollisionWithBlocks = false;
    private boolean isMySideCollision = false;
    private boolean isPasserSideCollision = false;
    private int carsCountTurn = 0;

    private Car currentCur;

    private ArrayList<BoostOnCarLeft> boostOnCarLeft = new ArrayList<BoostOnCarLeft>();
    private ArrayList<BoostOnCarRight> boostOnCarRight = new ArrayList<BoostOnCarRight>();

    private ArrayList<WingOnCarLeft> wingOnCarLeft = new ArrayList<WingOnCarLeft>();
    private ArrayList<WingOnCarRight> wingOnCarRight = new ArrayList<WingOnCarRight>();
    private ArrayList<RoadHole> roadHoles = new ArrayList<RoadHole>();
    private Landing landing;


    private ArrayList<ThronsOnCarLeft> thronsOnCarLeft = new ArrayList<ThronsOnCarLeft>();
    private ArrayList<ThronsOnCarRight> thronsOnCarRight = new ArrayList<ThronsOnCarRight>();
    private ArrayList<Dirt> dirts = new ArrayList<Dirt>();
    private ArrayList<FlySpringboard> flySpringBoards = new ArrayList<FlySpringboard>();
    ArrayList<DirtOnScreen> dirtOnScreens = new ArrayList<DirtOnScreen>();
    private Animation crashAnimation = AssetsManager.getAnimation(Constants.CRASH_ASSETS_ID);
    ArrayList<CarsType> carsTypes;
    private ActionResolver actionResolver;


    public GameState(GameStateManager gsm, boolean isNeedTutorial, boolean isFromGarage, ActionResolver actionResolver) {
        super(gsm);

        setUpWorld();
        setUpStage();
        gameState = this;
        this.actionResolver = actionResolver;
        GameManager.initial(world, stage);
        GameManager.resetSpeed();
        setUpCamera();
        isFirstStart = GameManager.isFirstStartApp();
        GameManager.setStopGeneratePasserCars(false);

        carsTypes = GameManager.getCarsTypes();
        setUpMyCar(isNeedTutorial);

        String mapType = currentCur.getMapType();

        roadNew = GameManager.getRoads().get(mapType);
        roadNew.setWorld(world);
        roadNew.setOnTrafficLightListener(this);
        effectBooster = new EffectBooster();
        effectMode = new EffectMode();
        passerCars = new ArrayList<PasserCar>();

        coins = new ArrayList<Coin>();
        skulls = new ArrayList<Skull>();
        ladles = new ArrayList<Ladle>();
        boosters = new ArrayList<Booster>();
        springboards = new ArrayList<Springboard>();
        tutorialBlocksesArrayList = new ArrayList<TutorialBlocks>();
        tutorialSpringboardArrayList = new ArrayList<TutorialSpringboard>();
        menuSaveMe = new MenuSaveMe(this, gsm, actionResolver);
        ToastHelper.setUpToastHelper(stage);


        afterPauseLabel = new Label(EMPTY_STRING_VALUE, AssetsManager.getUiSkin());

        afterPauseLabel.setBounds(GameRuners.WIDTH / 4 - (afterPauseLabel.getPrefWidth() + 10), GameRuners.HEIGHT / 4 - (afterPauseLabel.getPrefHeight() / 2), afterPauseLabel.getPrefWidth(), afterPauseLabel.getPrefHeight());
        stage.addActor(afterPauseLabel);

        if (!isFirstStart) setUpPasserCars();
        if (isFirstStart)
            tutorialBlocksesArrayList.add(new TutorialBlocks(world, TUTORIAL_BLOCK_DEFULT_X, TUTORIAL_VLOCK_DEFULAT_Y, DEFAULT_MOVE_TUTORIAL_BLOCK));

        setUpThornsLeftOnCar();
        setUpThornsRightOnCar();

        setUpAchivesCountLabel();

        GameManager.resetContactPoint();
        GameManager.resetTime();
        GameManager.resetSpeed();


        if (isFromGarage) startFromGarage();
        else stage.addActor(new MainMenu(this, gsm, actionResolver));
        textureCollisisonPoint = AssetsManager.getTextureRegion(Constants.CONTACT_POINT_ID).getTexture();
    }


    public void setUpWorld() {
        world = new World(new Vector2(0f, 0f), false);
        carFilter = new CarFilter(gsm, this, this, this, this, this, this, this, this);
        world.setContactListener(new CarContactListener(this));
        world.setContactFilter(carFilter);
//        bushsArrayLeft = new ArrayList<Bushs>();
//        bushsArrayRight = new ArrayList<Bushs>();
        GameManager.resetSpeed();
        GameManager.resetTime();
        RandomUtil.resetLasValue();
        Prize.resetTime();
        //bitmapFont = new BitmapFont();
    }

    public void setUpStage() {
        stage = new Stage(new StretchViewport(GameRuners.WIDTH / 2, GameRuners.HEIGHT / 2));
        gestureListnener = new GestureListnener(this);
        GameManager.setGestureListnener(gestureListnener);
        //Gdx.input.setInputProcessor(new GestureDetector(0.0f, 0.0f, 0.0f, 5f, new GestureListnener()));
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(new GestureDetector(0.0f, 0.0f, 0.0f, 5f, gestureListnener));
        inputMultiplexer.addProcessor(stage);
        Gdx.input.setInputProcessor(inputMultiplexer);
        //Gdx.input.setInputProcessor(stage);
    }

    public void setUpCamera() {
        camera.setToOrtho(false, GameRuners.WIDTH / 2, GameRuners.HEIGHT / 2);
        camera.position.y = 455;
        camera.update();
    }


    public void pauseGame() {


        isPause = true;
        AssetsManager.pauseMusic();

    }

    public boolean isGamePause() {
        return isPause;
    }

    public void StartGame() {
        isPause = false;
        isGameStart = true;
        setPauseTextButton();

        AssetsManager.playMusic();


    }


    public void setUpDirtsOnScreen() {
        for (int i = 0; i < 20; i++)
            dirtOnScreens.add(new DirtOnScreen(world, 10, 10, 10));
        // dirtOnScreens.add(new DirtOnScreen(world, 10, 10, 10));
    }

    public void setUpLadleOnCar() {
        ladleOnCar = new LadleOnCar(world, (int) myCar.getX(), (int) myCar.getY(), 100);
    }

    public void setUpBoostLeftOnCar() {

        boostOnCarLeft.add(new BoostOnCarLeft(world, (int) myCar.getX() + myCar.getCarTexture().getRegionWidth() / 2, (int) myCar.getY() + myCar.getCarTexture().getRegionHeight() / 2 - myCar.getCarTexture().getRegionHeight() / 2, 100));
//        pf = new ParticleEffect();
//
//        pf.load(Gdx.files.internal("booster_particle"), Gdx.files.internal(""));
        pf = AssetsManager.getParticleEffectLeft();
        pf.getEmitters().first().setPosition(boostOnCarLeft.get(0).body.getPosition().x + pf.getBoundingBox().getWidth(), boostOnCarLeft.get(0).body.getPosition().y);
        pf.start();

    }

    public void setUpWingOnCarLeft() {
        wingOnCarLeft.add(new WingOnCarLeft(world, (int) myCar.getX() + myCar.getCarTexture().getRegionWidth() / 2, (int) myCar.getY() + myCar.getCarTexture().getRegionHeight() / 2 - myCar.getCarTexture().getRegionHeight() / 2, 100));

    }


    public void setUpThornsRightOnCar() {

        thronsOnCarLeft.add(new ThronsOnCarLeft(world, (int) myCar.getX() + myCar.getCarTexture().getRegionWidth() / 2, (int) myCar.getY() + myCar.getCarTexture().getRegionHeight() / 2, 100));

    }


    public void setUpThornsLeftOnCar() {

        thronsOnCarRight.add(new ThronsOnCarRight(world, (int) myCar.getX() + myCar.getCarTexture().getRegionWidth() / 2, (int) myCar.getY() + myCar.getCarTexture().getRegionHeight() / 2, 100));

    }


    public void setUpWingOnCarRight() {
        wingOnCarRight.add(new WingOnCarRight(world, (int) myCar.getX() + myCar.getCarTexture().getRegionWidth() / 2, (int) myCar.getY() + myCar.getCarTexture().getRegionHeight() / 2 - myCar.getCarTexture().getRegionHeight() / 2, 100));

    }

    public void setUpBoostRightOnCar() {

        boostOnCarRight.add(new BoostOnCarRight(world, (int) myCar.getX() + myCar.getCarTexture().getRegionWidth() / 2, (int) myCar.getY() + myCar.getCarTexture().getRegionHeight() / 2 - myCar.getCarTexture().getRegionHeight() / 2, 100));
//        pfl = new ParticleEffect();
//
//        pfl.load(Gdx.files.internal("booster_particle"), Gdx.files.internal(""));
        pfl = AssetsManager.getParticleEffectRight();
        pfl.getEmitters().first().setPosition(boostOnCarRight.get(0).body.getPosition().x + pf.getBoundingBox().getWidth(), boostOnCarRight.get(0).body.getPosition().y);
        pfl.start();
    }


    public void setUpPasserCars() {
        passerCars.add(new PasserCar(world, 90, (int) camera.viewportHeight + 700, 10, RandomUtil.getRandomOtherCarType().getKey(), this));
        //System.out.println("camera.viewportHeight + 700" + camera.viewportHeight + 700);
    }


//    public void setUpBushs() {
//        for (int i = (int) camera.viewportHeight + (int) camera.position.y; i > 0; i -= 170) {
//            bushsArrayLeft.add(new Bushs(world, 90, i, 10, false, Constants.ROAD_1_BUSH_1_STATIC_ASSETS_ID));
//            bushsArrayRight.add(new Bushs(world, 90, i, 10, true, Constants.ROAD_1_BUSH_1_STATIC_ASSETS_ID));
//        }
//
//    }


    public void onAdClose() {
        menuSaveMe.onAdCLose();
    }

    public void onAdCloseAfterAddCoins() {
        GameManager.setDefaultSpeed();
        GameManager.pauseGame = false;
        GameManager.resetTime();
        GameManager.addCoin(ADD_COIN_COUNT);

    }


    public void updateCoins(ArrayList<Coin> coinsArray, float dt) {


        for (int i = 0; i < coinsArray.size(); i++) {
            CoinDataType coinDataType = (CoinDataType) coinsArray.get(i).body.getUserData();
            coinsArray.get(i).update(dt);
            if (coinDataType.isRecievedByMycar()) {
                world.destroyBody(coinsArray.get(i).body);
                coinsArray.get(i).remove();
                coinsArray.remove(i);
            } else if (coinsArray.get(i).getPosition().y < 0) {
                coinsArray.remove(i);
            }

        }

    }

    public void updatSkulls(ArrayList<Skull> skullArrayList, float dt) {


        for (int i = 0; i < skullArrayList.size(); i++) {
            SkullOnRoadDataType coinDataType = (SkullOnRoadDataType) skullArrayList.get(i).body.getUserData();
            skullArrayList.get(i).update(dt);
            if (coinDataType.isRecievedByMycar()) {
                world.destroyBody(skullArrayList.get(i).body);
                skullArrayList.get(i).remove();
                skullArrayList.remove(i);
            } else if (skullArrayList.get(i).getPosition().y < 0) {
                skullArrayList.remove(i);
            }

        }

    }

    public void updateTutorialBlocks(ArrayList<TutorialBlocks> tutorialBlocks, float dt) {


        for (int i = 0; i < tutorialBlocks.size(); i++) {

            tutorialBlocks.get(i).update(dt);
            if (tutorialBlocks.get(i).getPosition().y < -300) {
                tutorialBlocks.remove(i);
            }

        }
    }

    public void updateTutorialSpringoardBlocks(ArrayList<TutorialSpringboard> tutorialSpringboardArrayList, float dt) {

        for (int i = 0; i < tutorialSpringboardArrayList.size(); i++) {

            tutorialSpringboardArrayList.get(i).update(dt);
            if (tutorialSpringboardArrayList.get(i).getPosition().y < -300) {
                tutorialSpringboardArrayList.remove(i);
            }

        }
    }

    public void updatSpringboards(ArrayList<Springboard> springboards, float dt) {


        for (int i = 0; i < springboards.size(); i++) {
            SpringBoardDataType springboard = (SpringBoardDataType) springboards.get(i).body.getUserData();
            springboards.get(i).update(dt);
            if (springboard.isRecievedByMycar()) {
                world.destroyBody(springboards.get(i).body);
                springboards.get(i).remove();
                springboards.remove(i);
            } else if (springboards.get(i).getPosition().y < -300) {
                springboards.remove(i);
            }

        }

    }

    public void updatFlySpringboards(ArrayList<FlySpringboard> springboards, float dt) {


        for (int i = 0; i < springboards.size(); i++) {
            FlySpringBoardDataType springboard = (FlySpringBoardDataType) springboards.get(i).body.getUserData();
            springboards.get(i).update(dt);
            if (springboard.isRecievedByMycar()) {
                world.destroyBody(springboards.get(i).body);
                springboards.get(i).remove();
                springboards.remove(i);
            } else if (springboards.get(i).getPosition().y < -300) {
                springboards.remove(i);
            }

        }

    }

    public void updatDirts(ArrayList<Dirt> dirts, float dt) {


        for (int i = 0; i < dirts.size(); i++) {
            DirtDataType springboard = (DirtDataType) dirts.get(i).body.getUserData();
            dirts.get(i).update(dt);
            if (springboard.isRecievedByMycar()) {
                world.destroyBody(dirts.get(i).body);
                dirts.get(i).remove();
                dirts.remove(i);
            } else if (dirts.get(i).getPosition().y < -300) {
                dirts.remove(i);
            }

        }

    }

    public void updatLadle(ArrayList<Ladle> ladleArrayList, float dt) {


        for (int i = 0; i < ladleArrayList.size(); i++) {
            LadleOnRoadDataType coinDataType = (LadleOnRoadDataType) ladleArrayList.get(i).body.getUserData();
            ladleArrayList.get(i).update(dt);
            if (coinDataType.isRecievedByMycar()) {
                world.destroyBody(ladleArrayList.get(i).body);
                ladleArrayList.get(i).remove();
                ladleArrayList.remove(i);
            } else if (ladleArrayList.get(i).getPosition().y < 0) {
                ladleArrayList.remove(i);
            }

        }

    }

    public void updatBooster(ArrayList<Booster> boosterArrayList, float dt) {


        for (int i = 0; i < boosterArrayList.size(); i++) {
            BoosterDataType coinDataType = (BoosterDataType) boosterArrayList.get(i).body.getUserData();
            boosterArrayList.get(i).update(dt);
            if (coinDataType.isRecievedByMycar()) {
                world.destroyBody(boosterArrayList.get(i).body);
                boosterArrayList.get(i).remove();
                boosterArrayList.remove(i);
            } else if (boosterArrayList.get(i).getPosition().y < 0) {
                boosterArrayList.remove(i);
            }

        }

    }


    public void setUpImageCoinCount() {
        imageButton = new ImageButton(new Image(AssetsManager.getTextureRegion(Constants.COIN_ICON_1_NAME_ID)).getDrawable());
        //imageButton.setBounds(labelCoinCount.getX() + 50, labelCoinCount.getY() - 2, imageButton.getWidth(), imageButton.getHeight());
        imageButton.setBounds(GameRuners.WIDTH / 2 - 25, GameRuners.HEIGHT / 2 - 30, imageButton.getWidth(), imageButton.getHeight());
        imageButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                super.touchDown(event, x, y, pointer, button);
                // stage.addActor(new CoinShopState(null, gsm, actionResolver, (Group)stage));
                return true;
            }
        });
        stage.addActor(imageButton);
    }

    public void setUpCoinCountLabel() {
        labelCoinCount = new Label(String.valueOf(GameManager.getCoinCounter()), AssetsManager.getUiSkin());
        labelCoinCount.setFontScale(0.60f, 0.60f);
        labelCoinCount.setBounds(imageButton.getX() - labelCoinCount.getPrefWidth() - 5, imageButton.getY(), labelCoinCount.getWidth(), labelCoinCount.getHeight());
        stage.addActor(labelCoinCount);
    }


    public void setUpAchivesCountLabel() {
        Skin uiSkin = AssetsManager.getUiSkin();
        labelAchives = new Label(EMPTY_STRING_VALUE, uiSkin);
        labelAchives.setFontScale(LABEL_ACHIVE_FONT_SCALE_X, LABEL_ACHIVE_FONT_SCALE_Y);
        labelAchives.setBounds(GameRuners.WIDTH / 2 - LABEL_ACHIVE_WIDTH_SHIFT - labelAchives.getPrefWidth() / 2, GameRuners.HEIGHT / 2 - LABEL_ACHIVE_HEIGHT_SHIFT, labelAchives.getWidth(), labelAchives.getHeight());
        stage.addActor(labelAchives);
    }

    public void setUpMyCar(boolean isNeedTutorial) {
        currentCur = GameManager.getCurrentCar();
        if (isNeedTutorial) {
            myCar = new MyCar(world, (int) Constants.CAR_POS_Y, 10, false, currentCur.getID());
            stage.addActor(myCar);
            myCar.moveToStartLine();
        } else {

            myCar = new MyCar(world, MY_CAR_DEFAULT_Y, MY_CAR_DEFAYLT_MOVEMENT, false, currentCur.getID());
            myCar.setIsLeft(true);


            stage.addActor(myCar);
        }
    }

    @Override
    public void onCrashCar() {

    }


    @Override
    public void gearUp(int gear) {

    }


    @Override
    public void onStartTraffic() {
        GameManager.pauseGame = false;
        StartGame();
        resetSpeed();
        setUpImageCoinCount();
        setUpCoinCountLabel();
    }

    public void resetSpeed() {
        GameManager.setIsCollision(false);

    }


    @Override
    public void update(float dt) {
        handleInput();
        if (collisionCameraMove) {
            cameraMoveTime += dt;
            if (cameraMoveTime > CAR_MOVE_TIME) {
                collisionCameraMove = false;
                camera.position.x = CAMERA_POS_X_MOVE;
            }
        }
        if (collisionCameraMove) {
            if (collisionCameraMoveLeft) {
                if (camera.position.x > CAMERA_LEFT_MOVE) {
                    camera.position.x = camera.position.x - CAMERA_SHIFT;
                    camera.update();
                } else collisionCameraMoveLeft = false;
            } else if (!collisionCameraMoveLeft) {
                if (camera.position.x < CAMERA_RIGHT_MOVE) {
                    camera.position.x = camera.position.x + CAMERA_SHIFT;
                    camera.update();
                } else collisionCameraMoveLeft = true;
            }
        }


        for (PasserCar passerCar : passerCars) {
            if (((PasserCarDataType) passerCar.body.getUserData()).isContact()) {
                if (passerCar.body.getAngularVelocity() < -ANGULAR_VELOCITY) {
                    passerCar.body.setAngularVelocity(-ANGULAR_VELOCITY);
                }
                if (passerCar.body.getAngularVelocity() > ANGULAR_VELOCITY) {
                    passerCar.body.setAngularVelocity(ANGULAR_VELOCITY);
                }
            }
        }
        if (myCar.body.getAngularVelocity() < -ANGULAR_VELOCITY) {
            myCar.body.setAngularVelocity(-ANGULAR_VELOCITY);
        }
        if (myCar.body.getAngularVelocity() > ANGULAR_VELOCITY) {
            myCar.body.setAngularVelocity(ANGULAR_VELOCITY);
        }

        if (isStartTrafficLighter && isGameStart == false) roadNew.updateTrafficLighter(dt);
        GameManager.setAchives(achives);
        GameManager.setDistance(distacne);
        ToastHelper.onAct(dt);

        if (GameManager.pauseGame) {
            pauseGame();
        }
        if (GameManager.isAfterSaveMe) {
            isPause = false;
            StartGame();
            GameManager.setPauseDtTimer(true);
            ((MyCarDataType) myCar.body.getUserData()).setIsAfterPause(true);
            isAfterPauseUpdate = true;
            isStartAfterPause = true;
            GameManager.isAfterSaveMe = false;
/////////////////////////////////////////////////////////////crash
            myCar.setIsTurnRun(false);
            isGameStart = true;

            if (myCar.isLeft()) {
                myCar.setLeft();
            } else {
                myCar.setRight();
            }
            myCar.setIsTurnRun(false);
            isGameStart = true;
        }


        tutorialControlAnimationTime += dt;

        if (isMyCarCollision || isMyCarCollisionWithBlocks)
            playTimeAnimation += dt;

        if (!isGamePause()) {
            GameManager.updateGear(dt);
            if (!isFirstStart) {
                achives = achives + ((GameManager.getCurrentSpeed() * dt) / ACHIVE_COEFFICIENT);

                distacne = distacne + ((GameManager.getCurrentSpeed() * dt) / ACHIVE_COEFFICIENT);
            }
            dangerousEvolution(passerCars, myCar);
            labelAchives.setText(String.valueOf((int) achives));
            labelAchives.setBounds(GameRuners.WIDTH / 2 - LABEL_ACHIVE_PADDING_WIDTH - labelAchives.getPrefWidth() / 2, GameRuners.HEIGHT / 2 - LABEL_ACHIVE_PADDING_HEIGHT, labelCoinCount.getWidth(), labelCoinCount.getHeight());

            world.step(1f / WORLD_STEP, VELOCITYI_TERATIONS, POSITION_ITERATIONS);
            roadNew.update(camera, dt);

            effectBooster.update(camera, dt);
            effectBooster.update(camera, dt);
            if (isBost) {
                setUpBoostLeftOnCar();
                setUpBoostRightOnCar();
                isBost = false;
                GameManager.setBoosterSpeed();


            }


            if (isAutoTurn) {
                boostTime += dt;
                autoTurn(passerCars, myCar);
                carCountBooster(passerCars, myCar);
                pfl.update(dt);
                if (!boostOnCarRight.isEmpty())
                    pfl.setPosition(boostOnCarRight.get(INDEX).body.getPosition().x + boostOnCarRight.get(INDEX).getboosOnCarLeft().getKeyFrames()[INDEX].getRegionWidth() / 2, boostOnCarRight.get(0).body.getPosition().y - boostOnCarRight.get(0).getboosOnCarLeft().getKeyFrames()[0].getRegionHeight() - APPEND_REGION_HEIGHT);

                pf.update(dt);
                if (!boostOnCarLeft.isEmpty())
                    pf.setPosition(boostOnCarLeft.get(INDEX).body.getPosition().x + boostOnCarLeft.get(INDEX).getboosOnCarLeft().getKeyFrames()[INDEX].getRegionWidth() / 2, boostOnCarLeft.get(0).body.getPosition().y - boostOnCarLeft.get(0).getboosOnCarLeft().getKeyFrames()[0].getRegionHeight() - APPEND_REGION_HEIGHT);


                if (boostTime > TIME_TO_BOOST) {
                    effectBooster.setIsStartAlfa(true);
                }
                if (boostTime > TIME_TO_END_BOOST_TIME) {
                    isAutoTurn = false;
                    world.destroyBody(boostOnCarLeft.get(INDEX).body);
                    world.destroyBody(boostOnCarRight.get(INDEX).body);
                    boostOnCarRight.get(INDEX).remove();
                    boostOnCarLeft.get(INDEX).remove();
                    boostOnCarLeft.clear();
                    boostOnCarRight.clear();
                    boostTime = INITIAL_BOOST_TIME;
                    carsCountTurn = DEFAULT_CAR_COUNT;
                    GameManager.setLastSpeed();
                    ToastHelper.showUpMessage(GameManager.getStrings().get(Constants.MPGO_BONUS_COINS_TEXT), String.valueOf((int) achivesBooster), Color.ORANGE, Color.WHITE, ToastLength.TOAST_LONG);


                    achives += achivesBooster;
                    GameManager.addRocketCount();
                    achivesBooster = 0;
                    effectBooster.setAlfa(1);
                    effectBooster.setIsStartAlfa(false);

                }


            }
            if (isLadle) {
                setUpLadleOnCar();
                isLadle = false;
            }


            if (isAfterPause) {
                AssetsManager.playMusic();
                isAfterPause = false;
            }
            if (isAfterPauseUpdate) {
                timeAfterPause += dt;
                GameManager.setPauseDtTimer(true);
                ToastHelper.resetToasts();
                if (timeAfterPause > TIME_AFTER_PAUSE) {
                    ((MyCarDataType) myCar.body.getUserData()).setIsAfterPause(false);
                    isAfterPauseUpdate = false;
                    GameManager.setPauseDtTimer(false);
                    timeAfterPause = 0;
                    myCar.setAlfa(ALFA);
                } else {
                    if (timeAfterPause > MIN_THREE_LABEL_VISIBLE_TIME && timeAfterPause < MAX_THREE_LABEL_VISIBLE_TIME) {
                        afterPauseLabel.setText(AFTER_PAUSE_LABEL_THREE);
                    }

                    if (timeAfterPause > MIN_TWO_LABEL_VISIBLE_TIME && timeAfterPause < MAX_TWO_LABEL_VISIBLE_TIME) {
                        afterPauseLabel.setText(AFTER_PAUSE_LABEL_TWO);
                    }

                    if (timeAfterPause > MIN_ONE_LABEL_VISIBLE_TIME && timeAfterPause < MAX_ONE_LABEL_VISIBLE_TIME) {
                        afterPauseLabel.setText(AFTER_PAUSE_LABEL_ONE);
                    }
                    if (timeAfterPause > MIN_SET_EMTY_VALUE_TIME && timeAfterPause < MAX_SET_EMPTY_VELUE_TIME) {
                        afterPauseLabel.setText(EMPTY_STRING_VALUE);
                    }
                    if (myCar.isLowAlfa()) {
                        if (myCar.getAlfa() > MIN_MY_CAR_ALFA) {
                            myCar.setAlfa(myCar.getAlfa() - COEFFICIENT_ALFA);

                        } else myCar.setIsLowAlfa(false);
                    } else {
                        if (myCar.getAlfa() < MAX_MY_CAR_ALFA) {
                            myCar.setAlfa(myCar.getAlfa() + COEFFICIENT_ALFA);
                        } else myCar.setIsLowAlfa(true);
                    }
                }

            }

            if (isDirt) {
                setUpDirtsOnScreen();
                GameManager.setLow30Speed();
                isDirt = false;
            }
            if (isDirtUpdate) {
                if (dirTime > TIME_TO_DIR) {

                    for (int i = 0; i < dirtOnScreens.size(); i++) {
                        dirtOnScreens.get(i).setIsStartAlfa(true);
                    }
                }
                dirTime += dt;
                if (dirtOnScreens.get(0).getAlfa() <= COEFFICIENT_ALFA) {
                    isDirtUpdate = false;
                    for (int i = 0; i < dirtOnScreens.size(); i++) {
                        dirtOnScreens.get(i).remove();
                        dirtOnScreens.remove(i);
                    }

                    dirTime = 0;
                }
                for (DirtOnScreen dirtOnScreen : dirtOnScreens) {
                    dirtOnScreen.update(dt);
                }
            }

            udateBlows(passerCars);
//
            if (!isMyCarCollision && !isMyCarCollisionWithBlocks && !isFirstStart) {
                PasserCar.updateCars(passerCars, camera, dt, this);
            }

            if (isFirstStart) {
                for (PasserCar passerCar : passerCars)
                    passerCar.update(dt);
            }
            if (isPasserSideCollision) {
                for (int i = 0; i < passerCars.size(); i++) {

                    if ((((PasserCarDataType) passerCars.get(i).body.getUserData()).isSideObjectContact())) {
                        roadHoles.add(new RoadHole(passerCars.get(i).body.getPosition().x, passerCars.get(i).body.getPosition().y));
                        world.destroyBody(passerCars.get(i).body);
                        passerCars.get(i).remove();
                        passerCars.remove(i);

                        break;
                    }
                }
            }

//

            if (showTutorialLeft) {
                if (timeTutorialLeft < TIME_TO_SHOW_TUTORIAL_LEFT) {
                    timeTutorialLeft += dt;
                } else {
                    timeTutorialLeft = 0;
                    showTutorialLeft = false;
                    tutorialControlAnimationTime = 0;
                }
            }

            if (showTutorialRight) {
                if (timeTutorialRight < TIME_TO_SHOW_TUTORIAL_RIGHT) {
                    timeTutorialRight += dt;
                } else {
                    timeTutorialRight = 0;
                    showTutorialRight = false;
                    tutorialControlAnimationTime = 0;
                }
            }

            if (!isFirstStart) {
                updateCoins(coins, dt);
                updatSkulls(skulls, dt);
                updatSpringboards(springboards, dt);
                updatFlySpringboards(flySpringBoards, dt);
                updatDirts(dirts, dt);
            } else {
                updateTutorialSpringoardBlocks(tutorialSpringboardArrayList, dt);
                updateTutorialBlocks(tutorialBlocksesArrayList, dt);
            }
            if (!isFirstStart) springboardtime += dt;
            if (springboardtime > Constants.TIME_SPRINGBOARD) {
                springboards.add(new Springboard(world, SPRINGBOARD_DEFAULT_X, (int) (PasserCar.getPosYLastCar(passerCars) + APPEND_Y), 10));
                springboardtime = 0;
            }

            tutorialBlocksTime += dt;
            if (isFirstStart && tutorialBlocksTime > 7 && tutorialBlocksCount < 1) {
                tutorialBlocksesArrayList.add(new TutorialBlocks(world, BLOCK_DEFAULT_X,  (APPEND_Y), 10));
                tutorialBlocksTime = 0;
                tutorialBlocksCount++;
            }

            tutorialCarsTime += dt;
            tutorialCarsTime2 += dt;


            if (isFirstStart && tutorialCarsTime > TUTORIAL_CARS_TIME) {
                passerCars.add(new PasserCar(world, DEFAULT_PASSER_CAR_X, (int) camera.viewportHeight + FIRST_START_PASSER_CAR_APPEND_Y, DEFAULT_MOVEMENT, true, RandomUtil.getRandomOtherCarType().getKey(), this));
//
                tutorialCarsTime = 0;
            }

            if (isFirstStart && tutorialCarsTime2 > TUTORIAL_CARS_TIME_2) {
                passerCars.add(new PasserCar(world, DEFAULT_PASSER_CAR_X, (int) camera.viewportHeight + FIRST_START_PASSER_CAR_APPEND_Y, DEFAULT_MOVEMENT, false, RandomUtil.getRandomOtherCarType().getKey(), this));
                tutorialCarsTime2 = 0;
            }

            tutorialSpringboardsTime += dt;
            if (isFirstStart && tutorialSpringboardsTime > TUTORIAL_SPRINGBOARD_TIME) {
                tutorialSpringboardArrayList.add(new TutorialSpringboard(world, DEFAULT_SPRINGBOARD_X,  DEFAULT_SPRINGBOARD_Y, SPRINGBOARD_MOVEMENT));

                tutorialSpringboardsTime = 0;
            }


            if (GameManager.getAllTime() > Constants.TIME_RELAX_ZONE_START) {
                GameManager.setStopGeneratePasserCars(true);
            }
            if (GameManager.getAllTime() > Constants.TIME_RELAX_ZONE_START + Constants.DURATION_RELAX_ZONE) {
                GameManager.setStopGeneratePasserCars(false);
            }


            if (isJumpCar) {
                isJumpCar = false;
            }
            if (isJumpCarUpdate) {
                springJumpTime += dt;
                if (springJumpTime > SPRING_JUMP_TIME) {
                    isJumpCarUpdate = false;
                    isZoomOut = true;
                }
            }

            if (isZoomCar) {
                setUpWingOnCarLeft();
                setUpWingOnCarRight();
                isZoomCar = false;
            }
            if (isZoomCarUpdate) {

                springBoardTime += dt;
                if (springBoardTime > SPRINGBOARD_START_TIME)
                    effectBooster.setIsStartAlfa(true);
                if (springBoardTime > SPRINGBOARD_STOP_TIME) {

                    if (landing == null) {
                        for (PasserCar passerCar : passerCars) {
                            if (myCar.isLeft() == passerCar.getIsLeft()) {
                                if (myCar.getY() - passerCar.getY() < PASSER_CAR_LANDING_Y) {
                                    landing = new Landing(myCar, passerCar);
                                }
                            }
                            if (landing == null) {
                                isZoomCar = false;
                                isZoomCarUpdate = false;
                                springBoardTime = 0;
                                isZoomOut = true;
                                world.destroyBody(wingOnCarLeft.get(INDEX).body);
                                world.destroyBody(wingOnCarRight.get(INDEX).body);
                                wingOnCarLeft.get(INDEX).remove();
                                wingOnCarRight.get(INDEX).remove();
                                wingOnCarRight.remove(INDEX);
                                wingOnCarLeft.remove(INDEX);

                                ((MyCarDataType) myCar.body.getUserData()).setIsFly(false);

                                ToastHelper.showUpMessage(GameManager.getStrings().get(Constants.MPGO_BONUS_COINS_TEXT), String.valueOf((int) achivesFly), Color.ORANGE, Color.WHITE, ToastLength.TOAST_LONG);


//
                                contactFlyCars = 1;

                                achives += achivesFly;

                                effectBooster.setIsStartAlfa(false);
                                effectBooster.setAlfa(1);
                                GameManager.resetContactPoint();
                            }
                        }
                    } else {
                        if (landing.isLanding()) {
                            isZoomCar = false;
                            isZoomCarUpdate = false;
                            springBoardTime = 0;
                            isZoomOut = true;

                            world.destroyBody(wingOnCarLeft.get(INDEX).body);
                            world.destroyBody(wingOnCarRight.get(INDEX).body);
                            wingOnCarLeft.get(INDEX).remove();
                            wingOnCarRight.get(INDEX).remove();
                            wingOnCarRight.remove(INDEX);
                            wingOnCarLeft.remove(INDEX);
                            ((MyCarDataType) myCar.body.getUserData()).setIsFly(false);
                            landing = null;

                            ToastHelper.showUpMessage(GameManager.getStrings().get(Constants.MPGO_BONUS_COINS_TEXT), String.valueOf((int) achivesFly), Color.ORANGE, Color.WHITE, ToastLength.TOAST_LONG);
//
                            contactFlyCars = 1;

                            achives += achivesFly;

                            effectBooster.setIsStartAlfa(false);
                            effectBooster.setAlfa(1);
                            GameManager.resetContactPoint();
                        }
                    }

                }

            }

            if (isFirstStart) {
                if (GameManager.getAllTime() > FIRST_START_TUTORIAL_DURATION) isFirstStart = false;
            }


            if (isFirstStart) {
                if (GameManager.getAllTime() > timeToTutorialStep1 && !isShowStep1) {
                    showTutorialRight = true;
                    isShowStep1 = true;
                }
                if (GameManager.getAllTime() > timeToTutorialStep2 && !isShowStep2) {
                    showTutorialLeft = true;
                    isShowStep2 = true;
                }
                if (GameManager.getAllTime() > timeToTutorialStep3 && !isShowStep3) {
                    showTutorialRight = true;
                    isShowStep3 = true;
                }
                if (GameManager.getAllTime() > timeToTutorialStep4 && !isShowStep4) {
                    showTutorialLeft = true;
                    isShowStep4 = true;
                }
            }

            if (!isFirstStart) {
                updatLadle(ladles, dt);
                updatBooster(boosters, dt);
            }
            for (RoadHole roadHole : roadHoles) {
                roadHole.update(dt);
            }
            if (isMode) {
                setUpThornsLeftOnCar();
                setUpThornsRightOnCar();
                isMode = false;

            }
            if (isUpdateGodeMode) {
                if (!thronsOnCarRight.isEmpty() && !thronsOnCarLeft.isEmpty()) {
                    thronsOnCarRight.get(INDEX).update(dt, myCar.getX(), myCar.getY() + myCar.getCarTexture().getRegionHeight() / 2 + thronsOnCarRight.get(INDEX).getBoostOnCar().getRegionHeight() / 2);
                    thronsOnCarLeft.get(INDEX).update(dt, myCar.getX(), myCar.getY() + myCar.getCarTexture().getRegionHeight() / 2 + thronsOnCarLeft.get(INDEX).getBoostOnCar().getRegionHeight() / 2);
                    godMedeTime += dt;
                }
                if (godMedeTime > GODEMODE_START_TIME) {
                    effectMode.setIsStartAlfa(true);
                }
                if (godMedeTime > GODE_MODE_STOP_TIME) {
                    world.destroyBody(thronsOnCarRight.get(INDEX).body);
                    thronsOnCarRight.get(INDEX).remove();
                    thronsOnCarRight.remove(INDEX);
                    world.destroyBody(thronsOnCarLeft.get(INDEX).body);
                    thronsOnCarLeft.get(INDEX).remove();
                    thronsOnCarLeft.remove(INDEX);
                    isUpdateGodeMode = false;
                    ((MyCarDataType) myCar.body.getUserData()).setIsGodMode(false);
                    godMedeTime = 0;
                    effectMode.setIsStartAlfa(false);
                    effectMode.setAlfa(1);

                    achives += skullAchives;
                    skullCarCount = DEFAULT_SKULL_CAR_COUNT;
                    ToastHelper.showUpMessage(GameManager.getStrings().get(Constants.MPGO_BONUS_COINS_TEXT), String.valueOf((int) skullAchives), Color.ORANGE, Color.WHITE, ToastLength.TOAST_LONG);


                    skullAchives = 0;


                }
                effectMode.update(camera, dt);
            }

            if (boostOnCarRight.size() != 0 && boostOnCarLeft.size() != 0) {
                boostOnCarLeft.get(INDEX).update(dt, myCar.getX(), myCar.getY() + myCar.getCarTexture().getRegionHeight() / 2 + boostOnCarLeft.get(INDEX).getBoostOnCar().getRegionHeight() / 2 - myCar.getCarTexture().getRegionHeight() / 2);
                boostOnCarRight.get(INDEX).update(dt, myCar.getX(), boostOnCarLeft.get(INDEX).getPosition().y);

            }

            if (wingOnCarRight.size() != 0 && wingOnCarLeft.size() != 0) {
                wingOnCarLeft.get(INDEX).update(dt, myCar.getX(), myCar.getY() + myCar.getCarTexture().getRegionHeight() / 2 + wingOnCarLeft.get(INDEX).getBoostOnCar().getRegionHeight() / 2 - myCar.getCarTexture().getRegionHeight() / 2);
                wingOnCarRight.get(INDEX).update(dt, myCar.getX(), wingOnCarLeft.get(INDEX).getPosition().y);

            }
            if (updateLadle)
                ladleOnCar.update(dt, myCar.getX(), myCar.getY() + myCar.getCarTexture().getRegionHeight() / 2 + ladleOnCar.getLadleOnCar().getRegionHeight() / 2);
            if (!updateLadle && ladleOnCar != null) {
                achives += LADEL_ACHIVES;
                AssetsManager.playSound(Constants.SOUND_BONUS);
                ToastHelper.showUpMessage(GameManager.getStrings().get(Constants.MPGO_BONUS_COINS_TEXT), LADEL_ACHIVES_TEXT, Color.ORANGE, Color.WHITE, ToastLength.TOAST_LONG);
//

                ladleOnCar.remove();
                world.destroyBody(ladleOnCar.body);
                ladleOnCar = null;
            }

            if (!isAfterPause && !isFirstStart)
                Prize.updatePrize(world, camera, coins, skulls, ladles, boosters, springboards, flySpringBoards, dirts, dt, PasserCar.getPosYLastCar(passerCars), PasserCar.getIsLeftLastCar(passerCars));

            roadNew.getTrafficLight().getPosition().set(41, roadNew.getTrafficLight().getPosition().y + (-GameManager.getCurrentSpeed()) * dt);

            if (camera.position.y > MAX_CAMERA_POS) {
                camera.position.add(0, (myCar.body.getPosition().y - CAMERA_SPEED) * dt, 0);
                camera.update();
            }
        }

        myCar.updateAnimations(isGamePause());
        if (!GameManager.pauseGame && !isMyCarCollision && !isMyCarCollisionWithBlocks)
            myCar.update(dt);
        stage.act(Gdx.graphics.getDeltaTime());

        if (isMyCarCollision) {
            if (!GameManager.pauseGame) timer += dt;

            boostOnCarLeft.clear();
            boostOnCarRight.clear();
            thronsOnCarLeft.clear();
            thronsOnCarRight.clear();
            wingOnCarLeft.clear();
            wingOnCarRight.clear();


            GameManager.setIsCollision(true);

            GameManager.setCollisionSpeed(GameManager.getCurrentSpeed() <= MIN_SPEED ? 0 : GameManager.getCurrentSpeed() - LOW_SPEED);


            if (timer > 1) {
                GameManager.pauseGame = true;
                AssetsManager.stopMusic();

               // if (actionResolver != null && actionResolver.getAdmobStatus() && actionResolver.isIntertatlLoaded() && actionResolver.isSaveMeIntertitalLoad() && !isSavedMe && distacne > MAX_DISTANCE) {
                if (actionResolver != null && actionResolver.getAdmobStatus() && actionResolver.isIntertatlLoaded()  && !isSavedMe ) {
                    {
                        isMyCarCollision = false;
                        isMyCarCollisionWithBlocks = false;
                        stage.addActor(menuSaveMe);
                        isSavedMe = true;
                        ToastHelper.resetToasts();
                    }
                } else {

                    isMyCarCollision = false;
                    stage.addActor(new MenuGameOver(gsm, this, actionResolver));
                    labelAchives.setVisible(false);
                    labelCoinCount.setVisible(false);
                    imageButton.setVisible(false);
                    ToastHelper.resetToasts();
                }

                timer = 0;
            }
        }
        if (isMyCarCollisionWithBlocks) {
            if (!GameManager.pauseGame) timer += dt;


            GameManager.setIsCollision(true);

            GameManager.setCollisionSpeed(COLLISION_SPEED);


            if (timer > 1) {
                GameManager.pauseGame = true;
//
               // if (actionResolver.getAdmobStatus() && actionResolver.isIntertatlLoaded() && actionResolver.isSaveMeIntertitalLoad() && !isSavedMe && distacne > MAX_SAVE_ME_DISTANCE) {
                if (actionResolver != null && actionResolver.getAdmobStatus() && actionResolver.isIntertatlLoaded()  && !isSavedMe) {
                    isSavedMe = true;
                    stage.addActor(menuSaveMe);
                    ToastHelper.resetToasts();
                } else {
                    ToastHelper.resetToasts();
                    stage.addActor(new MenuGameOver(gsm, this, actionResolver));
                    labelAchives.setVisible(false);
                    labelCoinCount.setVisible(false);
                    imageButton.setVisible(false);

                }

                timer = 0;
            }
        }

    }


    private void autoTurn(ArrayList<PasserCar> passerCars, MyCar myCar) {
        for (PasserCar passerCar : passerCars) {

            if (passerCar.getIsLeft() == myCar.isLeft() && passerCar.getY() - myCar.getY() < 200 && passerCar.getY() - myCar.getY() > 0) {


                myCar.turn();


            }
        }
    }

    private void carCountBooster(ArrayList<PasserCar> passerCars, MyCar myCar) {
        for (PasserCar passerCar : passerCars) {
            if (!((PasserCarDataType) passerCar.body.getUserData()).isOvertaking()) {
                if (passerCar.getY() - myCar.getY() < DISTANRE_BOOSTER_TURN && passerCar.getY() - myCar.getY() > 0) {
                    ((PasserCarDataType) passerCar.body.getUserData()).setOvertaking(true);
                    carsCountTurn++;
                    AssetsManager.playSound(Constants.SOUND_BONUS);
                    achivesBooster += ACHIVE_BOOSTER;
//
                    ToastHelper.showUpMessage(GameManager.getStrings().get(Constants.GAME_CARS_COUNT_LBL), String.valueOf(carsCountTurn), Color.RED, Color.WHITE, ToastLength.TOAST_LONG);
//
                }
            }
        }
    }

    private void dangerousEvolution(ArrayList<PasserCar> passerCars, MyCar myCar) {
        for (PasserCar passerCar : passerCars) {

            if (!((PasserCarDataType) passerCar.body.getUserData()).isDangerEvolution())
                if (!isAfterPauseUpdate && !isAutoTurn && !isZoomCarUpdate && !isUpdateGodeMode && passerCar.getIsLeft() == !myCar.isLeft() && myCar.isTurnRun() && passerCar.getY() - myCar.getY() < 100 && passerCar.getY() - myCar.getY() > 0) {
                    if ((myCar.isLeft() && myCar.getX() < MIN_LEFT_DANGEROUS_EVOLUTION) || (!myCar.isLeft() && myCar.getX() > MAX_LEFT_DANGEROUS_EVOLUTION)) {
                        achives += DENGEROUS_EVOLUTION_ACHIVES_COUNT;
                        AssetsManager.playSound(Constants.SOUND_BONUS);
//

                        GameManager.addDangerousCount();
                        ToastHelper.showUpMessage(GameManager.getStrings().get(Constants.GAME_DANGEROUS_LBL), DENGEROUS_EVOLUTION_LABEL_MESSAGE, Color.ORANGE, Color.WHITE, ToastLength.TOAST_LONG);
//
                        ((PasserCarDataType) passerCar.body.getUserData()).setIsDangerEvolution(true);
                    }
                }
        }
    }

    private void udateBlows(ArrayList<PasserCar> passerCars) {
        for (PasserCar passerCar : passerCars) {

            PasserCarDataType passerCarDataType = (PasserCarDataType) passerCar.body.getUserData();
            if (passerCarDataType.isBlow()) {
                passerCar.body.applyForceToCenter(FORCE_X, FORCE_Y, true);
            }
        }
    }


    private void setPauseTextButton() {

        TextButton.TextButtonStyle textButtonStyleInvisible = new TextButton.TextButtonStyle();
        textButtonStyleInvisible.font = AssetsManager.getUiSkin().getFont(DEFAULT_FONT);
        TextButton invisibleClickButtonPause = new TextButton(EMPTY_STRING_VALUE, textButtonStyleInvisible);
        invisibleClickButtonPause.setBounds(Constants.PAUSE_BTTN_X_VISIBLE - 30, Constants.PAUSE_BTTN_Y - 30, 100, 100);
        invisibleClickButtonPause.addListener(new ClickListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                pauseGame();
                isAfterPause = true;

                stage.addActor(new MenuPause(gameState, gsm, actionResolver));
                hidePauseButton();

                return true;
            }
        });

        float width = 24, height = 25;
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();

        pauseButtonTextureUp = AssetsManager.getTextureRegion(Constants.PAUSE_BUTTON_UP_ID).getTexture();
        pauseButtonTextureDown = AssetsManager.getTextureRegion(Constants.PAUSE_BUTTON_PRESSED_ID).getTexture();
        pauseButtonImageDown = new Image(pauseButtonTextureDown);
        pauseButtonImageUp = new Image(pauseButtonTextureUp);
        textButtonStyle.down = pauseButtonImageDown.getDrawable();
        textButtonStyle.up = pauseButtonImageUp.getDrawable();
        textButtonStyle.font = AssetsManager.getUiSkin().getFont(DEFAULT_FONT);


        pauseTextButton = new TextButton(EMPTY_STRING_VALUE, textButtonStyle);
        pauseTextButton.addListener(new ClickListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                pauseGame();
                isAfterPause = true;

                stage.addActor(new MenuPause(gameState, gsm, actionResolver));
                hidePauseButton();

                return true;
            }
        });

        pauseTextButton.setBounds(Constants.PAUSE_BTTN_X_VISIBLE, Constants.PAUSE_BTTN_Y - (height / 2), width, height);
        stage.addActor(pauseTextButton);
        stage.addActor(invisibleClickButtonPause);

    }

    public void hidePauseButton() {


        pauseTextButton.setPosition(Constants.PAUSE_BTTN_X_INVISIBLE, Constants.PAUSE_BTTN_Y);

    }


    @Override
    public void render(SpriteBatch sb) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sb.setProjectionMatrix(camera.combined);
        sb.begin();

        roadNew.draw(sb);

//

        if (isAutoTurn || isZoomCarUpdate) {

            effectBooster.draw(sb);
        }
        if (isUpdateGodeMode)
            effectMode.draw(sb);

        for (Coin coin : coins) {
            coin.draw(sb);


        }

        for (Skull buster : skulls) {
            buster.draw(sb);

        }

        for (Springboard springboard : springboards) {
            springboard.draw(sb);
        }

        for (TutorialBlocks tutorialBlock : tutorialBlocksesArrayList) {
            tutorialBlock.draw(sb);
        }

        for (TutorialSpringboard tutorialSpringboard : tutorialSpringboardArrayList) {
            tutorialSpringboard.draw(sb);
        }

        for (FlySpringboard flySpringboard : flySpringBoards) {
            flySpringboard.draw(sb);
        }

        for (Dirt dirt : dirts) {
            dirt.draw(sb);
        }

        for (RoadHole roadHole : roadHoles) {
            sb.draw(roadHole.getTexture(), roadHole.getPosition().x, roadHole.getPosition().y);
        }

        for (Booster buster : boosters) {
            buster.draw(sb);

        }

        for (Ladle ladle : ladles) {
            ladle.draw(sb);


        }
        if (updateLadle) {
            sb.draw(ladleOnCar.getLadleOnCar(), ladleOnCar.body.getPosition().x, ladleOnCar.body.getPosition().y - ladleOnCar.getLadleOnCar().getRegionHeight() / 2);
        }


        sb.setColor(sb.getColor().r, sb.getColor().g, sb.getColor().b, myCar.getAlfa());
        for (PasserCar car : passerCars) {

            sb.draw(car.getPasserCarAnimation().getKeyFrame(myCar.getStateTime(), true), car.body.getPosition().x, car.body.getPosition().y - car.getCarTexture().getRegionHeight() / 2, car.getOriginX() + car.getCarTexture().getRegionWidth() / 2, car.getOriginY() + car.getCarTexture().getRegionHeight() / 2, car.getCarTexture().getRegionWidth(), car.getCarTexture().getRegionHeight(), 1, 1, (float) Math.toDegrees(car.body.getAngle()));

        }


        if (isZoomCarUpdate || isJumpCarUpdate) {
            if (zoomMyCarX < ZOOM_MY_CAR_MAX && zoomMyCarY < ZOOM_MY_CAR_MAX) {
                zoomMyCarX += ZOOM;
                zoomMyCarY += ZOOM;
            }
            sb.draw(myCar.getMyCarAnimation().getKeyFrame(myCar.getStateTime(), true), myCar.body.getPosition().x, myCar.body.getPosition().y - myCar.getCarTexture().getRegionHeight() / 2, myCar.getOriginX() + myCar.getCarTexture().getRegionWidth() / 2, myCar.getOriginY() + myCar.getCarTexture().getRegionHeight() / 2, myCar.getCarTexture().getRegionWidth(), myCar.getCarTexture().getRegionHeight(), zoomMyCarX, zoomMyCarY, (float) Math.toDegrees(myCar.body.getAngle()));

        } else if (isZoomOut) {
            if (zoomMyCarX > ZOOM_MY_CAR_MIN && zoomMyCarY > ZOOM_MY_CAR_MIN) {
                zoomMyCarX -= ZOOM;
                zoomMyCarY -= ZOOM;
            } else isZoomOut = false;
            sb.draw(myCar.getMyCarAnimation().getKeyFrame(myCar.getStateTime(), true), myCar.body.getPosition().x, myCar.body.getPosition().y - myCar.getCarTexture().getRegionHeight() / 2, myCar.getOriginX() + myCar.getCarTexture().getRegionWidth() / 2 + 5, myCar.getOriginY() + myCar.getCarTexture().getRegionHeight() / 2 + 5, myCar.getCarTexture().getRegionWidth(), myCar.getCarTexture().getRegionHeight(), zoomMyCarX, zoomMyCarY, (float) Math.toDegrees(myCar.body.getAngle()));

        } else if (!isMyCarCollisionWithBlocks && !isMySideCollision)
            sb.draw(myCar.getMyCarAnimation().getKeyFrame(myCar.getStateTime(), true), myCar.body.getPosition().x, myCar.body.getPosition().y - myCar.getCarTexture().getRegionHeight() / 2, myCar.getOriginX() + myCar.getCarTexture().getRegionWidth() / 2 + 5, myCar.getOriginY() + myCar.getCarTexture().getRegionHeight() / 2 + 5, myCar.getCarTexture().getRegionWidth(), myCar.getCarTexture().getRegionHeight(), 1, 1, (float) Math.toDegrees(myCar.body.getAngle()));

        sb.setColor(sb.getColor().r, sb.getColor().g, sb.getColor().b, ALFA);

        sb.draw(textureCollisisonPoint, myCar.body.getPosition().x * PIXELS_TO_METERS, myCar.body.getPosition().y * PIXELS_TO_METERS);

//
        if (playTimeAnimation < TIME_PLAY_ANIMATION && isMyCarCollision) {
            if (myCar.isTurnRun()) {
                if (myCar.isLeft()) {
                    sb.draw(crashAnimation.getKeyFrame(playTimeAnimation, true), GameManager.getContactPointX(), GameManager.getContactPointY() + 5);
                } else {
                    sb.draw(crashAnimation.getKeyFrame(playTimeAnimation, true), GameManager.getContactPointX() + 35, GameManager.getContactPointY() + 5);
                }
            } else {
                if (myCar.isLeft()) {
                    sb.draw(crashAnimation.getKeyFrame(playTimeAnimation, true), GameManager.getContactPointX(), GameManager.getContactPointY() + 5);
                } else {
                    sb.draw(crashAnimation.getKeyFrame(playTimeAnimation, true), GameManager.getContactPointX() - 10, GameManager.getContactPointY() + 5);
                }
            }
        }


        if (showTutorialLeft) {
            if (!GameManager.isTouchControl()) {
                TextureRegion tr = AssetsManager.getAnimation(Constants.SWIPE_ANIMATION_TUTORIAL_ID).getKeyFrame(tutorialControlAnimationTime, true);
                sb.draw(tr, SWIPE_POSITION_X_LEFT, SWIPE_POSITION_Y_LEFT, tr.getRegionWidth() / 2, tr.getRegionHeight() / 2, tr.getRegionWidth(), tr.getRegionHeight(), SCALE_X, SCALE_Y, ROTATION_LEFT);
            } else {
                TextureRegion tr = AssetsManager.getAnimation(Constants.TAP_ANIMATION_FOR_TUTORIAL_ID).getKeyFrame(tutorialControlAnimationTime, true);
                sb.draw(tr, TAP_POSITION_X_LEFT, TAP_POSITION_Y_LEFT, tr.getRegionWidth() / 2, tr.getRegionHeight() / 2, tr.getRegionWidth(), tr.getRegionHeight(), SCALE_X, SCALE_Y, ROTATION_LEFT);
            }

        }
        if (showTutorialRight) {
            if (!GameManager.isTouchControl()) {
                TextureRegion tr = AssetsManager.getAnimation(Constants.SWIPE_ANIMATION_TUTORIAL_ID).getKeyFrame(tutorialControlAnimationTime, true);
                sb.draw(tr, SWIPE_POSITION_RIGHT_X, SWIPE_POSITION_RIGHT_Y, tr.getRegionWidth() / 2, tr.getRegionHeight() / 2, tr.getRegionWidth(), tr.getRegionHeight(), SCALE_X, SCALE_Y, ROTATION_RIGHT);
            } else {
                TextureRegion tr = AssetsManager.getAnimation(Constants.TAP_ANIMATION_FOR_TUTORIAL_ID).getKeyFrame(tutorialControlAnimationTime, true);
                sb.draw(tr, TAP_POSITION_RIGHT_X, TAP_POSITION_RIGHT_Y, tr.getRegionWidth() / 2, tr.getRegionHeight() / 2, tr.getRegionWidth(), tr.getRegionHeight(), SCALE_X, SCALE_Y, ROTATION_RIGHT);
            }
        }

        if (GameManager.isCollisionWithCar()) {

            GameManager.setIsCollisionWithCar(false);

        }

        if (wingOnCarLeft.size() != 0 && wingOnCarRight.size() != 0 && isZoomCarUpdate) {
            sb.draw(wingOnCarLeft.get(INDEX).getBoostOnCar(), wingOnCarLeft.get(INDEX).body.getPosition().x - WING_LEFT_APPEND_X, wingOnCarLeft.get(INDEX).body.getPosition().y, wingOnCarLeft.get(INDEX).getOriginX() + wingOnCarLeft.get(INDEX).getBoostOnCar().getRegionWidth(), wingOnCarLeft.get(INDEX).getOriginY() + wingOnCarLeft.get(INDEX).getBoostOnCar().getRegionHeight(), wingOnCarLeft.get(INDEX).getBoostOnCar().getRegionWidth(), wingOnCarLeft.get(INDEX).getBoostOnCar().getRegionHeight(), 1, 1, myCar.body.getAngle());
            sb.draw(wingOnCarRight.get(INDEX).getBoostOnCar(), wingOnCarRight.get(INDEX).body.getPosition().x + WING_RIGHT_APPEND_X, wingOnCarRight.get(INDEX).body.getPosition().y, wingOnCarRight.get(INDEX).getOriginX() + wingOnCarRight.get(INDEX).getBoostOnCar().getRegionWidth(), wingOnCarRight.get(INDEX).getOriginY() + wingOnCarRight.get(INDEX).getBoostOnCar().getRegionHeight(), wingOnCarRight.get(INDEX).getBoostOnCar().getRegionWidth(), wingOnCarRight.get(INDEX).getBoostOnCar().getRegionHeight(), 1, 1, myCar.body.getAngle());

        }


        if (boostOnCarRight.size() != 0 && boostOnCarLeft.size() != 0) {

            sb.draw(boostOnCarLeft.get(INDEX).getBoostOnCar(), boostOnCarLeft.get(INDEX).body.getPosition().x, boostOnCarLeft.get(INDEX).body.getPosition().y);
            sb.draw(boostOnCarRight.get(INDEX).getBoostOnCar(), boostOnCarRight.get(INDEX).body.getPosition().x, boostOnCarRight.get(INDEX).body.getPosition().y);
            pf.draw(sb);
            if (pf.isComplete())
                pf.reset();
            pfl.draw(sb);
            if (pfl.isComplete())
                pfl.reset();
        }
        if (isUpdateGodeMode && thronsOnCarLeft.size() != 0 && thronsOnCarRight.size() != 0) {
            sb.draw(thronsOnCarLeft.get(INDEX).getBoostOnCar(), thronsOnCarLeft.get(INDEX).body.getPosition().x - GameManager.getCurrentCar().getBrakeLines().getLeftLineStart() + GameManager.getCurrentCar().getLeftRocketPosition().getX() - thronsOnCarLeft.get(INDEX).getBoostOnCar().getRegionWidth(), thronsOnCarLeft.get(INDEX).body.getPosition().y - myCar.getCarTexture().getRegionHeight() / 2 - GameManager.getCurrentCar().getLeftRocketPosition().getY() - thronsOnCarLeft.get(INDEX).getBoostOnCar().getRegionHeight());
            sb.draw(thronsOnCarRight.get(INDEX).getBoostOnCar(), thronsOnCarRight.get(INDEX).body.getPosition().x + GameManager.getCurrentCar().getRightRocketPosition().getX(), thronsOnCarRight.get(INDEX).body.getPosition().y - myCar.getCarTexture().getRegionHeight() / 2 - GameManager.getCurrentCar().getLeftRocketPosition().getY() - thronsOnCarRight.get(INDEX).getBoostOnCar().getRegionHeight());
        }

        roadNew.drawStaticObject(sb);

        sb.draw(textureCollisisonPoint, -10, -10);


        if (isDirtUpdate)
            for (DirtOnScreen dirtOnScreen : dirtOnScreens) {
                dirtOnScreen.draw(sb);
            }


        stage.draw();
        sb.end();
    }

    @Override
    protected void handleInput() {


    }

    @Override
    public void dispose() {


    }

    @Override
    public void pause() {


    }

    @Override
    public void resume() {


    }


    @Override
    public void resumeButtonOnResume() {

        if (isGameStart) StartGame();
        isStartTrafficLighter = true;

    }

    @Override
    public void onSaveMe() {

        playTimeAnimation = 0;

        isMyCarCollision = false;

        collisionCameraMove = false;
        cameraMoveTime = 0;
        GameManager.resetSpeed();
        GameManager.setIsCollisionWithCar(false);
        GameManager.setIsCollision(false);
        GameManager.resetContactPoint();
        if (myCar.isLeft()) {
            myCar.setLeft();
        } else {
            myCar.setRight();
        }
        myCar.setIsTurnRun(false);
        isGameStart = true;
        GameManager.pauseGame = false;
        GameManager.isAfterSaveMe = true;


    }


    public void startFromGarage() {

        isStartTrafficLighter = true;

    }

    @Override
    public void onPauseButton() {
        pauseGame();
        isAfterPause = true;

        stage.addActor(new MenuPause(gameState, gsm, actionResolver));

    }

    @Override
    public void pauseAfterCollision() {

    }

    @Override
    public void updateCoinCount(Integer countCoin) {
        labelCoinCount.setText(String.valueOf(countCoin));

        labelCoinCount.setBounds(imageButton.getX() - labelCoinCount.getPrefWidth() - 5, imageButton.getY() - 5, labelCoinCount.getWidth(), labelCoinCount.getHeight());

    }

    @Override
    public void onStartRelaxZone(boolean isStart, float dt) {
        timeToCoin += dt;

        if (!isStart && !isFirstStart)
            setUpPasserCars();
    }

    @Override
    public void onSetSpringBoar() {
        springboards.add(new Springboard(world, SPRINGBOARD_X, SPRINGBOARD_Y, SPRINGBOARD_MOVEMENT));
    }

    @Override
    public void onAddBoost() {
        AssetsManager.playSound(Constants.SOUND_ROCKET);
        isBost = true;
        isAutoTurn = true;
    }

    @Override
    public void onAddLadle() {
        if (!updateLadle) {
            isLadle = true;
            updateLadle = true;
        }
    }

    @Override
    public void removeLadle() {


        GameManager.addDestroyedCount();
        isLadle = false;
        updateLadle = false;
    }

    @Override
    public void generateHoleAfterLadle(float posX, float posY) {
        roadHoles.add(new RoadHole(posX, posY));
        ((MyCarDataType) myCar.body.getUserData()).setIsHaveLadle(false);
    }

    @Override
    public void addAchives() {

        ToastHelper.showUpMessage(GameManager.getStrings().get(Constants.GAME_CARS_COUNT_LBL), String.valueOf((int) skullCarCount), Color.RED, Color.WHITE, ToastLength.TOAST_LONG);
//
        skullCarCount++;
        GameManager.addGodModeCount();

        AssetsManager.playSound(Constants.SOUND_BONUS);
        skullAchives += SCULL_ACHIVE_COUNTS;
    }

    @Override
    public void onCollisionWithPasserCar() {
        if (isUpdateGodeMode) {

            ToastHelper.showUpMessage(GameManager.getStrings().get(Constants.GAME_CARS_COUNT_LBL), String.valueOf((int) skullCarCount), Color.RED, Color.WHITE, ToastLength.TOAST_LONG);

            skullCarCount++;

            skullAchives += SCULL_ACHIVE_COLLISION_WITH_PASSER_CAR;
        }
    }

    @Override
    public void setGodMode() {
        isMode = true;

        isUpdateGodeMode = true;
    }

    @Override
    public void onZoomCar() {
        achivesFly = 0;
        isZoomCar = true;
        isZoomCarUpdate = true;
        ((MyCarDataType) myCar.body.getUserData()).setIsFly(true);
    }

    @Override
    public void onZoomJump() {
        AssetsManager.playSound(Constants.SOUND_JUMP);
        isJumpCar = true;
        isJumpCarUpdate = true;
        if (isFirstStart) collisionCameraMove = true;
    }

    @Override
    public void onDirt() {
        if (!isDirtUpdate) {
            isDirt = true;
            isDirtUpdate = true;
        }
    }

    @Override
    public void onCollision() {
        if (!isMyCarCollision) {
            isFirstStart = false;
            collisionCameraMove = true;
            AssetsManager.playSound(Constants.SOUND_CRASH_2);
            Array<Action> actions = myCar.getActions();
            for (Action action : actions) {
                myCar.removeAction(action);
            }

            for (PasserCar passerCar : passerCars) {

                if (((PasserCarDataType) passerCar.body.getUserData()).isContact()) {

                    if (passerCar.getIsLeft() && myCar.isTurnRun()) {
                        passerCar.body.applyLinearImpulse(new Vector2(LINEAR_IMPULS_VALUE + GameManager.getCurrentSpeed(), GameManager.getCurrentSpeed() + CURRENT_SPEED_APPEND_VAL), new Vector2(VECTOR_X, VECTOR_Y), true);

                    } else if (!passerCar.getIsLeft() && myCar.isTurnRun()) {
                        passerCar.body.applyLinearImpulse(new Vector2(-LINEAR_IMPULS_VALUE + GameManager.getCurrentSpeed(), GameManager.getCurrentSpeed() + CURRENT_SPEED_APPEND_VAL), new Vector2(VECTOR_X, VECTOR_Y), true);


                    }
                }

            }


            isMyCarCollision = true;
            if (myCar.isLeft()) {
                myCar.body.applyLinearImpulse(new Vector2(MY_CAR_COLLISION_LINEAR_IMPULS + GameManager.getCurrentSpeed(), GameManager.getCurrentSpeed() + MY_CAR_COLIISION_APPEND_SPEED), new Vector2(VECTOR_X, VECTOR_Y), true);
            } else if (!myCar.isLeft()) {
                myCar.body.applyLinearImpulse(new Vector2(-MY_CAR_COLLISION_LINEAR_IMPULS + GameManager.getCurrentSpeed(), GameManager.getCurrentSpeed() + MY_CAR_COLIISION_APPEND_SPEED), new Vector2(VECTOR_X, MY_CAR_VECTOR_Y), true);
            }


        }
    }

    @Override
    public void onFlyCollision() {

        ToastHelper.showUpMessage(GameManager.getStrings().get(Constants.GAME_CARS_COUNT_LBL), String.valueOf((int) contactFlyCars++), Color.RED, Color.WHITE, ToastLength.TOAST_LONG);

        achivesFly += ACHIVE_FLY_APPEND;

        GameManager.addSpringBoardCount();


    }

    @Override
    public void onBlockCollision() {
        if (!isMyCarCollisionWithBlocks) {
            Array<Action> actions = myCar.getActions();
            for (Action action : actions) {
                myCar.removeAction(action);
            }

            for (PasserCar passerCar : passerCars) {
                if (((PasserCarDataType) passerCar.body.getUserData()).isContact()) {
                    if (passerCar.getIsLeft() && myCar.isTurnRun())
                        passerCar.body.setLinearVelocity(0, GameManager.getCurrentSpeed() * 3);
                    else if (!passerCar.getIsLeft() && myCar.isTurnRun()) {
                        passerCar.body.setLinearVelocity(0, GameManager.getCurrentSpeed() * 3);
                    }
                }
            }
            isMyCarCollisionWithBlocks = true;
            roadHoles.add(new RoadHole(myCar.body.getPosition().x, myCar.body.getPosition().y));

        }
    }

    @Override
    public void onPasserSideCollision() {
        if (!isPasserSideCollision) {
            isPasserSideCollision = true;
        }
    }

    @Override
    public void onMySideCollision() {
        if (!isMySideCollision) {
            Array<Action> actions = myCar.getActions();
            for (Action action : actions) {
                myCar.removeAction(action);
            }
            roadHoles.add(new RoadHole(myCar.body.getPosition().x, myCar.body.getPosition().y));
            isMySideCollision = true;
        }
    }


    @Override
    public void resumeFromPause() {

        StartGame();
        ((MyCarDataType) myCar.body.getUserData()).setIsAfterPause(true);
        isAfterPauseUpdate = true;
        isStartAfterPause = true;
        GameManager.setPauseDtTimer(true);
    }

    @Override
    public void turn() {
        if (!isGamePause() && !isJumpCarUpdate)
            if (!isAfterPause && !isAutoTurn && !isMyCarCollision && !isMyCarCollisionWithBlocks)
                myCar.turn();
            else isAfterPause = false;
    }

    @Override
    public void turn(boolean left) {
        if (!isGamePause() && !isJumpCarUpdate)
            if (!isAfterPause && !isAutoTurn && !isMyCarCollision && !isMyCarCollisionWithBlocks)
                myCar.turn(left);
            else isAfterPause = false;
    }
}