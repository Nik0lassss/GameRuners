package com.nicholaschirkevich.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
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
import com.nicholaschirkevich.game.filters.CarContactListener;
import com.nicholaschirkevich.game.filters.CarFilter;
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
import com.nicholaschirkevich.game.menu.MenuPause;
import com.nicholaschirkevich.game.menu.MenuSaveMe;
import com.nicholaschirkevich.game.menu.MenuTest;
import com.nicholaschirkevich.game.menu.PauseButton;
import com.nicholaschirkevich.game.menu.StartGameButton;
import com.nicholaschirkevich.game.model.boosters.BoostOnCarLeft;
import com.nicholaschirkevich.game.model.boosters.BoostOnCarRight;
import com.nicholaschirkevich.game.model.boosters.Booster;
import com.nicholaschirkevich.game.model.side_objects.Bushs;
import com.nicholaschirkevich.game.model.boosters.Coin;
import com.nicholaschirkevich.game.model.boosters.Dirt;
import com.nicholaschirkevich.game.model.boosters.DirtOnScreen;
import com.nicholaschirkevich.game.model.effects.EffectBooster;
import com.nicholaschirkevich.game.model.effects.EffectMode;
import com.nicholaschirkevich.game.model.boosters.FlySpringboard;
import com.nicholaschirkevich.game.model.boosters.Ladle;
import com.nicholaschirkevich.game.model.boosters.LadleOnCar;
import com.nicholaschirkevich.game.model.MyCar;
import com.nicholaschirkevich.game.model.PasserCar;
import com.nicholaschirkevich.game.model.side_objects.Road;
import com.nicholaschirkevich.game.model.side_objects.RoadHole;
import com.nicholaschirkevich.game.model.side_objects.RoadLighter;
import com.nicholaschirkevich.game.model.boosters.Skull;
import com.nicholaschirkevich.game.model.boosters.Springboard;
import com.nicholaschirkevich.game.model.boosters.ThronsOnCarLeft;
import com.nicholaschirkevich.game.model.boosters.ThronsOnCarRight;
import com.nicholaschirkevich.game.model.side_objects.TrafficLight;
import com.nicholaschirkevich.game.model.boosters.WingOnCarLeft;
import com.nicholaschirkevich.game.model.boosters.WingOnCarRight;
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

import java.util.ArrayList;

;


public class GameState extends State implements OnSetCollisionCars, ResumeFromPause, OnTrafficLightListener, OnCrushCarListener, OnGearUp, ResumeButtonListener, PauseButton.pauseButtonListener, PauseAfterCollision, UpdateCoinCount, OnStartRelaxZone, ListenerAddBoost, ListenerAddLadle, GenerateHoleAfterLadle, SetGodMode, ZoomCarListener, DirtListener {

    private GameState gameState;
    MyCar myCar;
    Road road;
    EffectBooster effectBooster;
    EffectMode effectMode;
    private Label labelCoinCount;
    private Label testCoinCount;
    private ImageButton imageButton;
    private Label labelAchives;
    private TrafficLight trafficLight;
    ArrayList<PasserCar> passerCars;
    ArrayList<Bushs> bushsArrayLeft, bushsArrayRight;
    ArrayList<RoadLighter> roadLighters;
    ArrayList<Coin> coins;
    ArrayList<Skull> skulls;
    ArrayList<Ladle> ladles;
    ArrayList<Booster> boosters;
    ArrayList<Springboard> springboards;
    MenuSaveMe menuSaveMe;

    SequenceAction sequenceAction = new SequenceAction();
    SequenceAction sequenceActionDangerousCount = new SequenceAction();
    SequenceAction sequenceActionCountCar = new SequenceAction();


    SequenceAction sequenceBonusCarCount = new SequenceAction();
    SequenceAction sequenceBonusText = new SequenceAction();

    SequenceAction sequenceLadleBonusText = new SequenceAction();
    SequenceAction sequenceLAdleBonus = new SequenceAction();

    SequenceAction sequenceFlyBonusCount = new SequenceAction();
    SequenceAction sequenceFlyeBonus = new SequenceAction();

    Label label = new Label("Dengerous", AssetsManager.getUiSkin());
    Label labelDangerousCount = new Label("+50",  AssetsManager.getUiSkin());
    Label labelCountCar = new Label("",  AssetsManager.getUiSkin());
    Label labelCars = new Label("",  AssetsManager.getUiSkin());

    Label labelFlyText = new Label("",  AssetsManager.getUiSkin());
    Label labelFlyCount = new Label("",  AssetsManager.getUiSkin());
    Label labelFlyBonus = new Label("",  AssetsManager.getUiSkin());

    Label boosterBonus = new Label("",  AssetsManager.getUiSkin());
    Label labelBonusText = new Label("",  AssetsManager.getUiSkin());
    Label ladleBonus = new Label("",  AssetsManager.getUiSkin());

    Label skullBonus = new Label("",  AssetsManager.getUiSkin());
    Label skullBonusText = new Label("",  AssetsManager.getUiSkin());
    Label skullBonusCountCar = new Label("",  AssetsManager.getUiSkin());
    private float skullAchives = 0;
    private float skullCarCount = 0;
    private float timeToCoin = 0;
    private float achives = 0;
    private float achivesBooster = 0;
    private float achivesFly = 0;
    private Texture textureCollisisonPoint;
    private boolean isBost = false;
    private boolean isDirt = false;
    private boolean isDirtUpdate = false;
    private boolean isLadle = false;

    private boolean isAfterPauseUpdate = false;
    private boolean isStartAfterPause = false;

    private ParticleEffect pf;
    private ParticleEffect pfl;
    private float springboardtime = 0;

    private float timer = 0;


    final float PIXELS_TO_METERS = 100f;
    World world;
    CarFilter carFilter;

    private Stage stage;

    private StartGameButton startGameButton;

    private TextButton pauseTextButton;
    private Image pauseButtonImageUp, pauseButtonImageDown;
    private Texture pauseButtonTextureUp, pauseButtonTextureDown;

    private Label afterPauseLabel;
    private float contactFlyCars = 1;
    private float playTimeAnimation = 0;
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
    private int carsCountTurn = 0;


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
        setUpTrafficLighter();
        carsTypes = GameManager.getCarsTypes();
        setUpMyCar(isNeedTutorial);
        road = new Road();
        effectBooster = new EffectBooster();
        effectMode = new EffectMode();
        passerCars = new ArrayList<PasserCar>();
        roadLighters = new ArrayList<RoadLighter>();
        coins = new ArrayList<Coin>();
        skulls = new ArrayList<Skull>();
        ladles = new ArrayList<Ladle>();
        boosters = new ArrayList<Booster>();
        springboards = new ArrayList<Springboard>();
        menuSaveMe =new MenuSaveMe(this, gsm,actionResolver);

        afterPauseLabel = new Label("",  AssetsManager.getUiSkin());

        afterPauseLabel.setBounds(GameRuners.WIDTH / 4 - (afterPauseLabel.getPrefWidth() + 10), GameRuners.HEIGHT / 4 - (afterPauseLabel.getPrefHeight() / 2), afterPauseLabel.getPrefWidth(), afterPauseLabel.getPrefHeight());
        stage.addActor(afterPauseLabel);

        setUpPasserCars();
        setUpBushs();
        setUpRoadLighter();
        setUpThornsLeftOnCar();
        setUpThornsRightOnCar();
        setUpImageCoinCount();
        setUpCoinCountLabel();
        setUpAchivesCountLabel();


        if (isFromGarage) startFromGarage();
        else stage.addActor(new MenuTest(this, gsm,actionResolver));
        textureCollisisonPoint = AssetsManager.getTextureRegion(Constants.CONTACT_POINT_ID).getTexture();
    }


    public void setUpWorld() {
        world = new World(new Vector2(0f, 0f), false);
        carFilter = new CarFilter(gsm, this, this, this, this, this, this, this, this);
        world.setContactListener(new CarContactListener(this));
        world.setContactFilter(carFilter);
        bushsArrayLeft = new ArrayList<Bushs>();
        bushsArrayRight = new ArrayList<Bushs>();
        GameManager.resetSpeed();
        GameManager.resetTime();
        RandomUtil.resetLasValue();
        Prize.resetTime();
        //bitmapFont = new BitmapFont();
    }

    public void setUpStage() {
        stage = new Stage(new StretchViewport(GameRuners.WIDTH / 2, GameRuners.HEIGHT / 2));
        Gdx.input.setInputProcessor(stage);
    }

    public void setUpCamera() {
        camera.setToOrtho(false, GameRuners.WIDTH / 2, GameRuners.HEIGHT / 2);
        camera.position.y = 455;
        camera.update();
    }

    public void setUpTrafficLighter() {
        trafficLight = new TrafficLight(41, 350);
        trafficLight.setOnTrafficLightListener(this);
    }


    public void setUpCoins() {
        //coins.add(new Coin(world, 90, (int) camera.viewportHeight + 30, 10));
    }


    public void pauseGame() {
        //if (!isPause) stage.addActor(new MenuGameOver(this, gsm));
        isPause = true;

    }

    public boolean isGamePause() {
        return isPause;
    }

    public void StartGame() {
        isPause = false;
        isGameStart = true;
        setPauseTextButton();
        //pauseButton.show();

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
        pf = new ParticleEffect();

        pf.load(Gdx.files.internal("booster_particle"), Gdx.files.internal(""));
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
        pfl = new ParticleEffect();

        pfl.load(Gdx.files.internal("booster_particle"), Gdx.files.internal(""));
        pfl.getEmitters().first().setPosition(boostOnCarRight.get(0).body.getPosition().x + pf.getBoundingBox().getWidth(), boostOnCarRight.get(0).body.getPosition().y);
        pfl.start();
    }


    public void setUpPasserCars() {
        passerCars.add(new PasserCar(world, 90, (int) camera.viewportHeight + 700, 10, RandomUtil.getRandomOtherCarType().getKey(), this));
        //System.out.println("camera.viewportHeight + 700" + camera.viewportHeight + 700);
    }



    public void setUpBushs() {
        for (int i = (int) camera.viewportHeight + (int) camera.position.y; i > 0; i -= 170) {
            bushsArrayLeft.add(new Bushs(world, 90, i, 10, false, Constants.ROAD_1_BUSH_1_STATIC_ASSETS_ID));
            bushsArrayRight.add(new Bushs(world, 90, i, 10, true, Constants.ROAD_1_BUSH_1_STATIC_ASSETS_ID));
        }

    }


    public void onAdClose()
    {
        menuSaveMe.onAdCLose();
    }

    public void updateBushs(ArrayList<Bushs> bushsArray, float dt, boolean isLeft) {
        if (camera.viewportHeight - bushsArray.get(bushsArray.size() - 1).getPosition().y > 10) {
            bushsArray.add(new Bushs(world, 90, (int) camera.viewportHeight + 150, 10, isLeft, Constants.ROAD_1_BUSH_1_STATIC_ASSETS_ID));

        }

        for (int i = 0; i < bushsArray.size(); i++) {

            if (bushsArray.get(i).getPosition().y < 0) {
                bushsArray.remove(i);
            }
            bushsArray.get(i).update(dt);
        }

    }

    public void updateCoins(ArrayList<Coin> coinsArray, float dt) {
        if (coinsArray.size() != 0 && camera.viewportHeight - coinsArray.get(coinsArray.size() - 1).getPosition().y > 10) {
            // coinsArray.add(new Coin(world, 90, (int) camera.viewportHeight + 150, 10));

        }

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
        if (skullArrayList.size() != 0 && camera.viewportHeight - skullArrayList.get(skullArrayList.size() - 1).getPosition().y > 10) {
            // coinsArray.add(new Coin(world, 90, (int) camera.viewportHeight + 150, 10));

        }

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

    public void updatSpringboards(ArrayList<Springboard> springboards, float dt) {
        if (springboards.size() != 0 && camera.viewportHeight - springboards.get(springboards.size() - 1).getPosition().y > 10) {
            // coinsArray.add(new Coin(world, 90, (int) camera.viewportHeight + 150, 10));

        }

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
        if (springboards.size() != 0 && camera.viewportHeight - springboards.get(springboards.size() - 1).getPosition().y > 10) {
            // coinsArray.add(new Coin(world, 90, (int) camera.viewportHeight + 150, 10));

        }

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
        if (dirts.size() != 0 && camera.viewportHeight - dirts.get(dirts.size() - 1).getPosition().y > 10) {
            // coinsArray.add(new Coin(world, 90, (int) camera.viewportHeight + 150, 10));

        }

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
        if (ladleArrayList.size() != 0 && camera.viewportHeight - ladleArrayList.get(ladleArrayList.size() - 1).getPosition().y > 10) {
            // coinsArray.add(new Coin(world, 90, (int) camera.viewportHeight + 150, 10));

        }

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
        if (boosterArrayList.size() != 0 && camera.viewportHeight - boosterArrayList.get(boosterArrayList.size() - 1).getPosition().y > 10) {
            // coinsArray.add(new Coin(world, 90, (int) camera.viewportHeight + 150, 10));

        }

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

    public void upateRoadLighters(ArrayList<RoadLighter> roadLighters, float dt) {
        if (roadLighters.get(roadLighters.size() - 1).getPosition().y < 400) {

            roadLighters.add(new RoadLighter(world, -45, (int) camera.viewportHeight + 400, 10, true, Constants.ROAD_1_LIGHTER_L_STATIC_ASSETS_ID));
            roadLighters.add(new RoadLighter(world, -45, (int) camera.viewportHeight + 400, 10, false, Constants.ROAD_1_LIGHTER_R_STATIC_ASSETS_ID));

        }
//        if (camera.viewportHeight - roadLighters.get(roadLighters.size() - 1).getPosition().y > 400) {
//
//            roadLighters.add(new RoadLighter(world, -45, (int) camera.viewportHeight + 200, 10, true, Constants.ROAD_1_LIGHTER_L_STATIC_ASSETS_ID));
//            roadLighters.add(new RoadLighter(world, -45, (int) camera.viewportHeight + 200, 10, false, Constants.ROAD_1_LIGHTER_R_STATIC_ASSETS_ID));
//
//        }
        for (int i = 0; i < roadLighters.size(); i++) {

            if (roadLighters.get(i).getPosition().y < -100) {
                roadLighters.remove(i);

            }

            roadLighters.get(i).update(dt);
        }
    }


    public void setUpRoadLighter() {

        roadLighters.add(new RoadLighter(world, -45, (int) camera.viewportHeight + 200, 10, true, Constants.ROAD_1_LIGHTER_L_STATIC_ASSETS_ID));
        roadLighters.add(new RoadLighter(world, -45, (int) camera.viewportHeight + 200, 10, false, Constants.ROAD_1_LIGHTER_R_STATIC_ASSETS_ID));

    }

    public void setUpImageCoinCount() {
        imageButton = new ImageButton(new Image(AssetsManager.getAnimation(Constants.COIN_ASSETS_ID).getKeyFrames()[0].getTexture()).getDrawable());
        //imageButton.setBounds(labelCoinCount.getX() + 50, labelCoinCount.getY() - 2, imageButton.getWidth(), imageButton.getHeight());
        imageButton.setBounds(GameRuners.WIDTH / 2 - 40, GameRuners.HEIGHT / 2 - 35, imageButton.getWidth(), imageButton.getHeight());
        stage.addActor(imageButton);
    }

    public void setUpCoinCountLabel() {
        Skin uiSkin = AssetsManager.getUiSkin();
        labelCoinCount = new Label(String.valueOf(GameManager.getCoinCounter()), uiSkin);
        labelCoinCount.setFontScale(0.60f, 0.60f);
        labelCoinCount.setBounds(imageButton.getX() - labelCoinCount.getPrefWidth() - 5, imageButton.getY() - 5, labelCoinCount.getWidth(), labelCoinCount.getHeight());
        stage.addActor(labelCoinCount);
    }

    public void setUpTestLabel() {
        testCoinCount = new Label(String.valueOf(GameManager.getCoinCounter()), AssetsManager.getUiSkin());
        testCoinCount.setBounds(GameRuners.WIDTH / 2 - 220, GameRuners.HEIGHT / 2 - 120, testCoinCount.getWidth(), testCoinCount.getHeight());
        testCoinCount.setText("0123456789 9876543210");
        stage.addActor(testCoinCount);
    }

    public void setUpAchivesCountLabel() {
        Skin uiSkin =AssetsManager.getUiSkin();
        labelAchives = new Label("", uiSkin);
        labelAchives.setFontScale(0.60f, 0.60f);
        labelAchives.setBounds(GameRuners.WIDTH / 2 - 160 - labelAchives.getPrefWidth() / 2, GameRuners.HEIGHT / 2 - 30, labelCoinCount.getWidth(), labelCoinCount.getHeight());
        stage.addActor(labelAchives);
    }

    public void setUpMyCar(boolean isNeedTutorial) {
        Car currentCur = GameManager.getCurrentCar();
        if (isNeedTutorial) {
            myCar = new MyCar(world, (int) Constants.CAR_POS_Y, 10, false, currentCur.getID());
            stage.addActor(myCar);
            myCar.moveToStartLine();
        } else {

            myCar = new MyCar(world, (int) 250, 10, false, currentCur.getID());
            myCar.setIsLeft(true);


            stage.addActor(myCar);
        }
    }

    @Override
    public void onCrashCar() {

    }

    public MyCar getMyCar() {
        return myCar;
    }

    @Override
    public void gearUp(int gear) {
        // stage.addActor(GearView.getView(gear));
    }


    @Override
    public void onStartTraffic() {
        StartGame();
        resetSpeed();
    }

    public void resetSpeed() {
        GameManager.setIsCollision(false);

    }


    @Override
    public void update(float dt) {
        handleInput();
        if (isStartTrafficLighter && isGameStart == false) trafficLight.update(dt);
        GameManager.setAchives(achives);

        if (GameManager.pauseGame) {


            pauseGame();
        }

        if (isMyCarCollision || isMyCarCollisionWithBlocks)
            playTimeAnimation += dt;

        if (!isGamePause()) {
            GameManager.updateGear(dt);
            achives = achives + ((GameManager.getCurrentSpeed() * dt) / 35);



            dangerousEvolution(passerCars, myCar);
            labelAchives.setText(String.valueOf((int) achives));
            labelAchives.setBounds(GameRuners.WIDTH / 2 - 160 - labelAchives.getPrefWidth() / 2, GameRuners.HEIGHT / 2 - 30, labelCoinCount.getWidth(), labelCoinCount.getHeight());

            world.step(1f / 60f, 6, 2);
            road.update(camera, dt);
            effectBooster.update(camera, dt);
            effectBooster.update(camera, dt);
            if (isBost) {
                setUpBoostLeftOnCar();
                setUpBoostRightOnCar();
                isBost = false;
                GameManager.setBoosterSpeed();


            }

            // System.out.println("isGameStart  "+isGameStart );
            if (isAutoTurn) {
                boostTime += dt;
                autoTurn(passerCars, myCar);
                carCountBooster(passerCars, myCar);
                pfl.update(dt);
                pfl.setPosition(boostOnCarRight.get(0).body.getPosition().x + boostOnCarRight.get(0).getboosOnCarLeft().getKeyFrames()[0].getRegionWidth() / 2, boostOnCarRight.get(0).body.getPosition().y - boostOnCarRight.get(0).getboosOnCarLeft().getKeyFrames()[0].getRegionHeight() - 10);

                pf.update(dt);
                pf.setPosition(boostOnCarLeft.get(0).body.getPosition().x + boostOnCarLeft.get(0).getboosOnCarLeft().getKeyFrames()[0].getRegionWidth() / 2, boostOnCarLeft.get(0).body.getPosition().y - boostOnCarLeft.get(0).getboosOnCarLeft().getKeyFrames()[0].getRegionHeight() - 10);
                //System.out.println(boostTime);

                if (boostTime > 3) {
                    effectBooster.setIsStartAlfa(true);
                }
                if (boostTime > 5) {
                    isAutoTurn = false;
                    world.destroyBody(boostOnCarLeft.get(0).body);
                    world.destroyBody(boostOnCarRight.get(0).body);
                    boostOnCarRight.get(0).remove();
                    boostOnCarLeft.get(0).remove();
                    boostOnCarLeft.clear();
                    boostOnCarRight.clear();
                    boostTime = 0;
                    carsCountTurn = 0;
                    GameManager.setLastSpeed();
                    labelCountCar.remove();
                    labelCars.remove();
                    sequenceBonusCarCount.addAction(Actions.delay(2f));
                    sequenceBonusCarCount.addAction(new Action() {
                        @Override
                        public boolean act(float delta) {
                            boosterBonus.setText("");
                            return true;
                        }
                    });
                    sequenceBonusText.addAction(Actions.delay(2f));
                    sequenceBonusText.addAction(new Action() {
                        @Override
                        public boolean act(float delta) {
                            labelCountCar.setText("");
                            return true;
                        }
                    });

                    labelCountCar.setText("");
                    labelCars.setText("");
                    labelFlyText.setText("");
                    labelFlyCount.setText("");
                    labelFlyBonus.setText("");
                    ladleBonus.setText("");
                    skullBonus.setText("");
                    skullBonusText.setText("");
                    skullBonusCountCar.setText("");
                    boosterBonus.addAction(sequenceBonusText);

                    boosterBonus.setColor(Color.ORANGE);
                    boosterBonus.setFontScale(0.7f, 0.7f);

                    boosterBonus.setPosition(GameRuners.WIDTH / 4 - 45, GameRuners.HEIGHT / 4 + 220);
                    boosterBonus.setText("Bonus ");
                    stage.addActor(boosterBonus);
                    labelCountCar.addAction(sequenceBonusCarCount);

                    labelCountCar.setFontScale(0.7f, 0.7f);

                    labelCountCar.setPosition(GameRuners.WIDTH / 4 - 25, GameRuners.HEIGHT / 4 + 180);
                    labelCountCar.setText(String.valueOf((int) achivesBooster));

                    stage.addActor(labelCountCar);
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
                isAfterPause = false;
            }
            if (isAfterPauseUpdate) {
                timeAfterPause += dt;
                if (timeAfterPause > 3) {
                    ((MyCarDataType) myCar.body.getUserData()).setIsAfterPause(false);
                    isAfterPauseUpdate = false;
                    timeAfterPause = 0;
                    myCar.setAlfa(1);
                } else {
                    if (timeAfterPause > 0 && timeAfterPause < 1) {
                        afterPauseLabel.setText("3");
                    }

                    if (timeAfterPause > 1 && timeAfterPause < 2) {
                        afterPauseLabel.setText("2");
                    }

                    if (timeAfterPause > 2 && timeAfterPause < 3) {
                        afterPauseLabel.setText("1");
                    }
                    if (timeAfterPause > 2.9 && timeAfterPause < 3) {
                        afterPauseLabel.setText("");
                    }
                    if (myCar.isLowAlfa()) {
                        if (myCar.getAlfa() > 0.04) {
                            myCar.setAlfa(myCar.getAlfa() - 0.05f);
                        } else myCar.setIsLowAlfa(false);
                    } else {
                        if (myCar.getAlfa() < 0.9) {
                            myCar.setAlfa(myCar.getAlfa() + 0.05f);
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
                if (dirTime > 3) {
                    for (int i = 0; i < dirtOnScreens.size(); i++) {
                        dirtOnScreens.get(i).setIsStartAlfa(true);
                    }
                }
                dirTime += dt;
                if (dirtOnScreens.get(0).getAlfa() <= 0.05) {
                    isDirtUpdate = false;
                    for (int i = 0; i < dirtOnScreens.size(); i++) {
                        dirtOnScreens.get(i).remove();
                        // world.destroyBody(dirtOnScreens.get(i).body);
                        dirtOnScreens.remove(i);
                    }

                    dirTime = 0;
                }
                for (DirtOnScreen dirtOnScreen : dirtOnScreens) {
                    dirtOnScreen.update(dt);
                }
            }

            udateBlows(passerCars, myCar);
            if (!isMyCarCollision && !isMyCarCollisionWithBlocks)
                PasserCar.updateCars(passerCars, camera, dt, this);
            updateBushs(bushsArrayLeft, dt, true);
            updateBushs(bushsArrayRight, dt, false);
            upateRoadLighters(roadLighters, dt);
            updateCoins(coins, dt);
            updatSkulls(skulls, dt);
            updatSpringboards(springboards, dt);
            updatFlySpringboards(flySpringBoards, dt);
            updatDirts(dirts, dt);
            springboardtime += dt;
            if (springboardtime > Constants.TIME_SPRINGBOARD) {
                springboards.add(new Springboard(world, 90, (int) (PasserCar.getPosYLastCar(passerCars) + 600), 10));
                springboardtime = 0;
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
                if (springJumpTime > 0.4) {
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
                if (springBoardTime > 3)
                    effectBooster.setIsStartAlfa(true);
                if (springBoardTime > 5) {

                    if (landing == null) {
                        for (PasserCar passerCar : passerCars) {
                            if (myCar.isLeft() == passerCar.getIsLeft()) {
                                if (myCar.getY() - passerCar.getY() < 500) {
                                    landing = new Landing(myCar, passerCar);
                                }
                            }
                            if (landing == null) {
                                isZoomCar = false;
                                isZoomCarUpdate = false;
                                springBoardTime = 0;
                                isZoomOut = true;
                                world.destroyBody(wingOnCarLeft.get(0).body);
                                world.destroyBody(wingOnCarRight.get(0).body);
                                wingOnCarLeft.get(0).remove();
                                wingOnCarRight.get(0).remove();
                                wingOnCarRight.remove(0);
                                wingOnCarLeft.remove(0);
                                labelFlyText.remove();
                                labelFlyCount.remove();
                                ((MyCarDataType) myCar.body.getUserData()).setIsFly(false);
                                sequenceFlyeBonus.reset();
                                sequenceFlyBonusCount.reset();
                                sequenceFlyeBonus.addAction(Actions.delay(2f));
                                sequenceFlyeBonus.addAction(new Action() {
                                    @Override
                                    public boolean act(float delta) {
                                        labelFlyBonus.setText("");
                                        return true;
                                    }
                                });
                                sequenceFlyBonusCount.addAction(Actions.delay(2f));
                                sequenceFlyBonusCount.addAction(new Action() {
                                    @Override
                                    public boolean act(float delta) {
                                        labelFlyCount.setText("");
                                        return true;
                                    }
                                });

                                labelFlyBonus.addAction(sequenceFlyeBonus);
                                //boosterBonus.setColor(0.3f, 0.3f, 1f, 1f);
                                labelFlyBonus.setColor(Color.ORANGE);
                                labelFlyBonus.setFontScale(0.7f, 0.7f);
                                //labelCoinCount.setBounds(imageButton.getX() - labelCoinCount.getPrefWidth() - 5, imageButton.getY() - 5, labelCoinCount.getWidth(), labelCoinCount.getHeight());

                                labelFlyBonus.setPosition(GameRuners.WIDTH / 4 - 45, GameRuners.HEIGHT / 4 + 220);
                                labelFlyBonus.setText("Bonus");
                                stage.addActor(labelFlyBonus);

                                labelFlyCount.addAction(sequenceFlyBonusCount);
                                //boosterBonus.setColor(0.3f, 0.3f, 1f, 1f);
                                labelFlyCount.setColor(Color.WHITE);
                                labelFlyCount.setFontScale(0.7f, 0.7f);
                                //labelCoinCount.setBounds(imageButton.getX() - labelCoinCount.getPrefWidth() - 5, imageButton.getY() - 5, labelCoinCount.getWidth(), labelCoinCount.getHeight());

                                labelFlyCount.setPosition(GameRuners.WIDTH / 4 - 25, GameRuners.HEIGHT / 4 + 180);
                                labelFlyCount.setText(String.valueOf((int) achivesFly));
                                stage.addActor(labelFlyCount);
                                contactFlyCars = 1;

                                achives += achivesFly;
                                // achivesFly = 0;
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

                            world.destroyBody(wingOnCarLeft.get(0).body);
                            world.destroyBody(wingOnCarRight.get(0).body);
                            wingOnCarLeft.get(0).remove();
                            wingOnCarRight.get(0).remove();
                            wingOnCarRight.remove(0);
                            wingOnCarLeft.remove(0);
                            ((MyCarDataType) myCar.body.getUserData()).setIsFly(false);
                            landing = null;

                            labelFlyText.remove();
                            labelFlyCount.remove();
                            sequenceFlyeBonus.reset();
                            sequenceFlyBonusCount.reset();
                            sequenceFlyeBonus.addAction(Actions.delay(2f));
                            sequenceFlyeBonus.addAction(new Action() {
                                @Override
                                public boolean act(float delta) {
                                    labelFlyBonus.setText("");
                                    return true;
                                }
                            });
                            sequenceFlyBonusCount.addAction(Actions.delay(2f));
                            sequenceFlyBonusCount.addAction(new Action() {
                                @Override
                                public boolean act(float delta) {
                                    labelFlyCount.setText("");
                                    return true;
                                }
                            });
                            labelFlyBonus.addAction(sequenceFlyeBonus);
                            //boosterBonus.setColor(0.3f, 0.3f, 1f, 1f);
                            labelFlyBonus.setColor(Color.ORANGE);
                            labelFlyBonus.setFontScale(0.7f, 0.7f);
                            //labelCoinCount.setBounds(imageButton.getX() - labelCoinCount.getPrefWidth() - 5, imageButton.getY() - 5, labelCoinCount.getWidth(), labelCoinCount.getHeight());

                            labelFlyBonus.setPosition(GameRuners.WIDTH / 4 - 45, GameRuners.HEIGHT / 4 + 220);
                            labelFlyBonus.setText("Bonus");
                            stage.addActor(labelFlyBonus);

                            labelFlyCount.addAction(sequenceFlyBonusCount);
                            //boosterBonus.setColor(0.3f, 0.3f, 1f, 1f);
                            labelFlyCount.setColor(Color.WHITE);
                            labelFlyCount.setFontScale(0.7f, 0.7f);
                            //labelCoinCount.setBounds(imageButton.getX() - labelCoinCount.getPrefWidth() - 5, imageButton.getY() - 5, labelCoinCount.getWidth(), labelCoinCount.getHeight());

                            labelFlyCount.setPosition(GameRuners.WIDTH / 4 - 25, GameRuners.HEIGHT / 4 + 180);
                            labelFlyCount.setText(String.valueOf((int) achivesFly));
                            stage.addActor(labelFlyCount);
                            contactFlyCars = 1;

                            achives += achivesFly;

                            effectBooster.setIsStartAlfa(false);
                            effectBooster.setAlfa(1);
                            GameManager.resetContactPoint();
                        }
                    }

                }

            }


            updatLadle(ladles, dt);
            updatBooster(boosters, dt);

            for (RoadHole roadHole : roadHoles) {
                roadHole.update(dt);
            }
            if (isMode) {
                setUpThornsLeftOnCar();
                setUpThornsRightOnCar();
                isMode = false;

            }
            if (isUpdateGodeMode) {
                thronsOnCarRight.get(0).update(dt, myCar.getX(), myCar.getY() + myCar.getCarTexture().getRegionHeight() / 2 + thronsOnCarRight.get(0).getBoostOnCar().getRegionHeight() / 2);
                thronsOnCarLeft.get(0).update(dt, myCar.getX(), myCar.getY() + myCar.getCarTexture().getRegionHeight() / 2 + thronsOnCarLeft.get(0).getBoostOnCar().getRegionHeight() / 2);
                godMedeTime += dt;

                if (godMedeTime > 3) {
                    effectMode.setIsStartAlfa(true);
                }
                if (godMedeTime > 5) {
                    world.destroyBody(thronsOnCarRight.get(0).body);
                    thronsOnCarRight.get(0).remove();
                    thronsOnCarRight.remove(0);
                    world.destroyBody(thronsOnCarLeft.get(0).body);
                    thronsOnCarLeft.get(0).remove();
                    thronsOnCarLeft.remove(0);
                    isUpdateGodeMode = false;
                    ((MyCarDataType) myCar.body.getUserData()).setIsGodMode(false);
                    godMedeTime = 0;
                    effectMode.setIsStartAlfa(false);
                    effectMode.setAlfa(1);
                    skullBonusCountCar.remove();
                    skullBonusText.remove();
                    achives += skullAchives;
                    skullCarCount = 0;
                    sequenceFlyeBonus.reset();
                    sequenceFlyBonusCount.reset();
                    sequenceFlyeBonus.addAction(Actions.delay(2f));

                    sequenceFlyeBonus.addAction(new Action() {
                        @Override
                        public boolean act(float delta) {
                            labelFlyBonus.setText("");
                            return true;
                        }
                    });
                    sequenceFlyBonusCount.addAction(Actions.delay(2f));
                    sequenceFlyBonusCount.addAction(new Action() {
                        @Override
                        public boolean act(float delta) {
                            labelFlyCount.setText("");
                            return true;
                        }
                    });
                    labelFlyBonus.addAction(sequenceFlyeBonus);
                    //boosterBonus.setColor(0.3f, 0.3f, 1f, 1f);
                    labelFlyBonus.setColor(Color.ORANGE);
                    labelFlyBonus.setFontScale(0.7f, 0.7f);
                    //labelCoinCount.setBounds(imageButton.getX() - labelCoinCount.getPrefWidth() - 5, imageButton.getY() - 5, labelCoinCount.getWidth(), labelCoinCount.getHeight());

                    labelFlyBonus.setPosition(GameRuners.WIDTH / 4 - 45, GameRuners.HEIGHT / 4 + 220);
                    labelFlyBonus.setText("Bonus");
                    stage.addActor(labelFlyBonus);

                    labelFlyCount.addAction(sequenceFlyBonusCount);
                    //boosterBonus.setColor(0.3f, 0.3f, 1f, 1f);
                    labelFlyCount.setColor(Color.WHITE);
                    labelFlyCount.setFontScale(0.7f, 0.7f);
                    //labelCoinCount.setBounds(imageButton.getX() - labelCoinCount.getPrefWidth() - 5, imageButton.getY() - 5, labelCoinCount.getWidth(), labelCoinCount.getHeight());

                    labelFlyCount.setPosition(GameRuners.WIDTH / 4 - 25, GameRuners.HEIGHT / 4 + 180);
                    labelFlyCount.setText(String.valueOf((int) skullAchives));
                    stage.addActor(labelFlyCount);

                    skullAchives = 0;


                }
                effectMode.update(camera, dt);
            }

            if (boostOnCarRight.size() != 0 && boostOnCarLeft.size() != 0) {
                boostOnCarLeft.get(0).update(dt, myCar.getX(), myCar.getY() + myCar.getCarTexture().getRegionHeight() / 2 + boostOnCarLeft.get(0).getBoostOnCar().getRegionHeight() / 2 - myCar.getCarTexture().getRegionHeight() / 2);
                boostOnCarRight.get(0).update(dt, myCar.getX(), boostOnCarLeft.get(0).getPosition().y);

            }

            if (wingOnCarRight.size() != 0 && wingOnCarLeft.size() != 0) {
                wingOnCarLeft.get(0).update(dt, myCar.getX(), myCar.getY() + myCar.getCarTexture().getRegionHeight() / 2 + wingOnCarLeft.get(0).getBoostOnCar().getRegionHeight() / 2 - myCar.getCarTexture().getRegionHeight() / 2);
                wingOnCarRight.get(0).update(dt, myCar.getX(), wingOnCarLeft.get(0).getPosition().y);

            }
            if (updateLadle)
                ladleOnCar.update(dt, myCar.getX(), myCar.getY() + myCar.getCarTexture().getRegionHeight() / 2 + ladleOnCar.getLadleOnCar().getRegionHeight() / 2);
            if (!updateLadle && ladleOnCar != null) {
                achives += 100;
                sequenceLAdleBonus.reset();
                sequenceLAdleBonus.addAction(Actions.delay(2f));
                sequenceLAdleBonus.addAction(new Action() {
                    @Override
                    public boolean act(float delta) {
                        ladleBonus.setText("");
                        return true;
                    }
                });
                ladleBonus.addAction(sequenceLAdleBonus);
                //boosterBonus.setColor(0.3f, 0.3f, 1f, 1f);
                ladleBonus.setColor(Color.ORANGE);
                ladleBonus.setFontScale(0.7f, 0.7f);
                //labelCoinCount.setBounds(imageButton.getX() - labelCoinCount.getPrefWidth() - 5, imageButton.getY() - 5, labelCoinCount.getWidth(), labelCoinCount.getHeight());

                ladleBonus.setPosition(GameRuners.WIDTH / 4 - 45, GameRuners.HEIGHT / 4 + 220);
                ladleBonus.setText("Bonus ");
                stage.addActor(ladleBonus);

                sequenceLadleBonusText.reset();
                sequenceLadleBonusText.addAction(Actions.delay(2f));
                sequenceLadleBonusText.addAction(new Action() {
                    @Override
                    public boolean act(float delta) {
                        labelBonusText.setText("");
                        return true;
                    }
                });
                labelBonusText.addAction(sequenceLadleBonusText);
                //boosterBonus.setColor(0.3f, 0.3f, 1f, 1f);
                labelBonusText.setFontScale(0.7f, 0.7f);
                //labelCoinCount.setBounds(imageButton.getX() - labelCoinCount.getPrefWidth() - 5, imageButton.getY() - 5, labelCoinCount.getWidth(), labelCoinCount.getHeight());

                labelBonusText.setPosition(GameRuners.WIDTH / 4 - 35, GameRuners.HEIGHT / 4 + 180);
                labelBonusText.setText("+100");
                stage.addActor(labelBonusText);

                ladleOnCar.remove();
                world.destroyBody(ladleOnCar.body);
                ladleOnCar = null;
            }

            Prize.updatePrize(world, camera, coins, skulls, ladles, boosters, springboards, flySpringBoards, dirts, dt, PasserCar.getPosYLastCar(passerCars), PasserCar.getIsLeftLastCar(passerCars));

            trafficLight.getPosition().set(41, trafficLight.getPosition().y + (-GameManager.getCurrentSpeed()) * dt);

            if (camera.position.y > 370) {
                camera.position.add(0, (myCar.body.getPosition().y - 440) * dt, 0);
                camera.update();
            }
        } else {

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

            GameManager.setCollisionSpeed(GameManager.getCurrentSpeed() <= 10 ? 0 : GameManager.getCurrentSpeed() - 15);


            if (timer > 1) {
                GameManager.pauseGame = true;
                // stage.addActor(new MenuGameOver(this, gsm));
                //stage.addActor(new MenuSaveMe(this, gsm,actionResolver));
                stage.addActor(menuSaveMe);
                System.out.println("Pause");
                timer = 0;
            }
        }
        if (isMyCarCollisionWithBlocks) {
            if (!GameManager.pauseGame) timer += dt;


            GameManager.setIsCollision(true);

            GameManager.setCollisionSpeed(50);


            if (timer > 1) {
                GameManager.pauseGame = true;
//                stage.addActor(new MenuSaveMe(this, gsm,actionResolver));
                stage.addActor(menuSaveMe);
                System.out.println("Pause");
                timer = 0;
            }
        }

    }

//    private void setUpResume() {
//
//        float x = Constants.RESUME_BTTN_X_VISIBLE, y = Constants.RESUME_BTTN_Y_VISIBLE, width = 70, height = 55;
//        resumeButton = new ResumeButton(x - (width / 2), y - (height / 2), width, height, this);
//        stage.addActor(resumeButton);
//
//    }

    private void autoTurn(ArrayList<PasserCar> passerCars, MyCar myCar) {
        for (PasserCar passerCar : passerCars) {

            if (passerCar.getIsLeft() == myCar.isLeft() && passerCar.getY() - myCar.getY() < 200 && passerCar.getY() - myCar.getY() > 0) {

                //labelCoinCount.setBounds(imageButton.getX() - labelCoinCount.getPrefWidth() - 5, imageButton.getY() - 5, labelCoinCount.getWidth(), labelCoinCount.getHeight());


                myCar.turn();

                // System.out.println("turn");
            }
        }
    }

    private void carCountBooster(ArrayList<PasserCar> passerCars, MyCar myCar) {
        for (PasserCar passerCar : passerCars) {
            if (!((PasserCarDataType) passerCar.body.getUserData()).isOvertaking()) {
                if (passerCar.getY() - myCar.getY() < 50 && passerCar.getY() - myCar.getY() > 0) {
                    ((PasserCarDataType) passerCar.body.getUserData()).setOvertaking(true);
                    carsCountTurn++;
                    achivesBooster += 100;
//                sequenceActionCountCar.addAction(Actions.delay(2f));
//                sequenceActionCountCar.addAction(Actions.removeActor());
                    labelCars.addAction(sequenceActionCountCar);
                    labelCars.setColor(1f, 0.2f, 0f, 1f);
                    labelCars.setFontScale(0.7f, 0.7f);
                    //labelCoinCount.setBounds(imageButton.getX() - labelCoinCount.getPrefWidth() - 5, imageButton.getY() - 5, labelCoinCount.getWidth(), labelCoinCount.getHeight());

                    labelCars.setPosition(GameRuners.WIDTH / 4 - 35, GameRuners.HEIGHT / 4 + 220);
                    labelCars.setText("Cars");
                    stage.addActor(labelCars);

                    labelCountCar.addAction(sequenceActionCountCar);
                    //labelCountCar.setColor(1f, 0.2f, 0f, 1f);
                    labelCountCar.setFontScale(0.7f, 0.7f);
                    //labelCoinCount.setBounds(imageButton.getX() - labelCoinCount.getPrefWidth() - 5, imageButton.getY() - 5, labelCoinCount.getWidth(), labelCoinCount.getHeight());

                    labelCountCar.setPosition(GameRuners.WIDTH / 4 - 5, GameRuners.HEIGHT / 4 + 180);
                    labelCountCar.setText(String.valueOf(carsCountTurn));
                    stage.addActor(labelCountCar);

                    // System.out.println("turn");
                }
            }
        }
    }

    private void dangerousEvolution(ArrayList<PasserCar> passerCars, MyCar myCar) {
        for (PasserCar passerCar : passerCars) {
            if (!((PasserCarDataType) passerCar.body.getUserData()).isDangerEvolution())
                if (!isAutoTurn && !isZoomCarUpdate && !isUpdateGodeMode && passerCar.getIsLeft() == !myCar.isLeft() && myCar.isTurnRun() && passerCar.getY() - myCar.getY() < 50 && passerCar.getY() - myCar.getY() > 0) {
                    achives += 50;
                    sequenceAction.addAction(Actions.delay(0.5f));
                    sequenceAction.addAction(new Action() {
                        @Override
                        public boolean act(float delta) {
                            label.setText("");
                            labelDangerousCount.setText("");
                            return true;
                        }
                    });
                    GameManager.addDangerousCount();
                    label.addAction(sequenceAction);
                    label.setColor(Color.ORANGE);
                    label.setFontScale(0.65f, 0.65f);
                    label.setText("Dangerous");


                    labelDangerousCount.setFontScale(0.65f, 0.65f);
                    labelDangerousCount.setText("+50");
//                    label = new Label("Dangerous +50", uiSkin);


                    labelCountCar.setText("");
                    labelCars.setText("");
                    labelFlyText.setText("");
                    labelFlyCount.setText("");
                    labelFlyBonus.setText("");
                    boosterBonus.setText("");
                    labelBonusText.setText("");
                    ladleBonus.setText("");
                    skullBonus.setText("");
                    skullBonusText.setText("");
                    skullBonusCountCar.setText("");
                    //labelCoinCount.setBounds(imageButton.getX() - labelCoinCount.getPrefWidth() - 5, imageButton.getY() - 5, labelCoinCount.getWidth(), labelCoinCount.getHeight());
                    label.setPosition(GameRuners.WIDTH / 4 - 70, GameRuners.HEIGHT / 4 + 200);
                    labelDangerousCount.setPosition(GameRuners.WIDTH / 4 - 30, GameRuners.HEIGHT / 4 + 160);
                    stage.addActor(label);
                    stage.addActor(labelDangerousCount);

                    //stage.addActor(new TurnBonusView(new Rectangle(GameRuners.WIDTH/4-(40/2), GameRuners.HEIGHT/4-(50/2), 40, 50), Constants.GEAR_1_ID));
                    ((PasserCarDataType) passerCar.body.getUserData()).setIsDangerEvolution(true);
                }
        }
    }

    private void udateBlows(ArrayList<PasserCar> passerCars, MyCar myCar) {
        for (PasserCar passerCar : passerCars) {

            PasserCarDataType passerCarDataType = (PasserCarDataType) passerCar.body.getUserData();
            if (passerCarDataType.isBlow()) {
                passerCar.body.applyForceToCenter(2f, 4f, true);
            }
        }
    }

    private void setPauseButton() {

//        float width = 24, height = 25;
//        pauseButton = new PauseButton(Constants.PAUSE_BTTN_X_INVISIBLE, Constants.PAUSE_BTTN_Y - (height / 2), width, height, this);
//        stage.addActor(pauseButton);

    }

    private void setPauseTextButton() {

        float width = 24, height = 25;
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();

        pauseButtonTextureUp = AssetsManager.getTextureRegion(Constants.PAUSE_BUTTON_UP_ID).getTexture();
        pauseButtonTextureDown = AssetsManager.getTextureRegion(Constants.PAUSE_BUTTON_PRESSED_ID).getTexture();
        pauseButtonImageDown = new Image(pauseButtonTextureDown);
        pauseButtonImageUp = new Image(pauseButtonTextureUp);
        textButtonStyle.down = pauseButtonImageDown.getDrawable();
        textButtonStyle.up = pauseButtonImageUp.getDrawable();
        textButtonStyle.font =  AssetsManager.getUiSkin().getFont("default-font");


        pauseTextButton = new TextButton("", textButtonStyle);
        pauseTextButton.addListener(new ClickListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                pauseGame();
                isAfterPause = true;

                stage.addActor(new MenuPause(gameState, gsm,actionResolver));
                hidePauseButton();

                return true;
            }
        });

        pauseTextButton.setBounds(Constants.PAUSE_BTTN_X_VISIBLE, Constants.PAUSE_BTTN_Y - (height / 2), width, height);
        stage.addActor(pauseTextButton);

    }

    public void hidePauseButton() {

        MoveToAction moveDown = new MoveToAction();
        moveDown.setPosition(Constants.PAUSE_BTTN_X_INVISIBLE, Constants.PAUSE_BTTN_Y);
        moveDown.setDuration(0.4f);
        pauseTextButton.addAction(moveDown);
    }

    public void showPauseButton() {
        MoveToAction moveDown = new MoveToAction();
        moveDown.setPosition(Constants.PAUSE_BTTN_X_VISIBLE, Constants.PAUSE_BTTN_Y);
        moveDown.setDuration(0.4f);
        pauseTextButton.addAction(moveDown);
    }
//    private void setGarageButton() {
//
//        float width = 43, height = 49;
//        garageButton = new GarageButton(Constants.GARAGE_BTTN_X_VISIBLE, Constants.GARAGE_BTTN_Y - (height / 2), width, height, gsm);
//        stage.addActor(garageButton);
//
//    }

    public void setUpStartButton() {
        float x = Constants.RESUME_BTTN_X_VISIBLE, y = Constants.RESUME_BTTN_Y_INVISIBLE - 190, width = 70, height = 55;
        startGameButton = new StartGameButton(x - (width / 2), y - (height / 2), width, height, gsm, actionResolver);
        stage.addActor(startGameButton);
    }


    @Override
    public void render(SpriteBatch sb) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sb.setProjectionMatrix(camera.combined);
        sb.begin();


        sb.draw(road.getRoad1(), road.getPosRoad1().x, road.getPosRoad1().y);
        sb.draw(road.getRoad2(), road.getPosRoad2().x, road.getPosRoad2().y);

        sb.draw(road.getStartLine().getTexture(), road.getStartLine().getPosition().x, road.getStartLine().getPosition().y);

//        sb.draw(effectBooster.getEffect1(),effectBooster.getPosEffectBoost1().x,effectBooster.getPosEffectBoost1().y);
//        sb.draw(effectBooster.getEffect2(),effectBooster.getPosEffectBoost2().x,effectBooster.getPosEffectBoost2().y);

        if (isAutoTurn || isZoomCarUpdate) {
            System.out.println(" effectBooster.draw(sb);  ");
            effectBooster.draw(sb);
        }
        if (isUpdateGodeMode)
            effectMode.draw(sb);

        for (Coin coin : coins) {
            coin.draw(sb);
            // sb.draw(coin.getCoinTexture(),coin.getPosition().x, coin.getPosition().y);

        }

        for (Skull buster : skulls) {
            buster.draw(sb);
            // sb.draw(coin.getCoinTexture(),coin.getPosition().x, coin.getPosition().y);

        }

        for (Springboard springboard : springboards) {
            springboard.draw(sb);
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
            // sb.draw(coin.getCoinTexture(),coin.getPosition().x, coin.getPosition().y);

        }

        for (Ladle ladle : ladles) {
            ladle.draw(sb);
            // sb.draw(coin.getCoinTexture(),coin.getPosition().x, coin.getPosition().y);

        }
        if (updateLadle) {
            sb.draw(ladleOnCar.getLadleOnCar(), ladleOnCar.body.getPosition().x, ladleOnCar.body.getPosition().y - ladleOnCar.getLadleOnCar().getRegionHeight() / 2);
        }


        if (passerCars.size() != 0) {

        }
        sb.setColor(sb.getColor().r, sb.getColor().g, sb.getColor().b, myCar.getAlfa());
        for (PasserCar car : passerCars) {

            sb.draw(car.getPasserCarAnimation().getKeyFrame(myCar.getStateTime(), true), car.body.getPosition().x, car.body.getPosition().y - car.getCarTexture().getRegionHeight() / 2, car.getOriginX() + car.getCarTexture().getRegionWidth() / 2, car.getOriginY() + car.getCarTexture().getRegionHeight() / 2, car.getCarTexture().getRegionWidth(), car.getCarTexture().getRegionHeight(), 1, 1, (float) Math.toDegrees(car.body.getAngle()));
            //  sb.draw(coins.get(0).getCoinTexture(),coins.get(0).getPosition().x,coins.get(0).getPosition().y);
            //     sb.draw(car.getPasserCarAnimation().getKeyFrame(myCar.getStateTime(), true), car.body.getPosition().x, car.body.getPosition().y);
        }


        if (isZoomCarUpdate || isJumpCarUpdate) {
            if (zoomMyCarX < 1.3 && zoomMyCarY < 1.3) {
                zoomMyCarX += 0.02f;
                zoomMyCarY += 0.02f;
            }
            sb.draw(myCar.getMyCarAnimation().getKeyFrame(myCar.getStateTime(), true), myCar.body.getPosition().x, myCar.body.getPosition().y - myCar.getCarTexture().getRegionHeight() / 2, myCar.getOriginX() + myCar.getCarTexture().getRegionWidth() / 2, myCar.getOriginY() + myCar.getCarTexture().getRegionHeight() / 2, myCar.getCarTexture().getRegionWidth(), myCar.getCarTexture().getRegionHeight(), zoomMyCarX, zoomMyCarY, (float) Math.toDegrees(myCar.body.getAngle()));

        } else if (isZoomOut) {
            if (zoomMyCarX > 1 && zoomMyCarY > 1) {
                zoomMyCarX -= 0.02f;
                zoomMyCarY -= 0.02f;
            } else isZoomOut = false;
            sb.draw(myCar.getMyCarAnimation().getKeyFrame(myCar.getStateTime(), true), myCar.body.getPosition().x, myCar.body.getPosition().y - myCar.getCarTexture().getRegionHeight() / 2, myCar.getOriginX() + myCar.getCarTexture().getRegionWidth() / 2 + 5, myCar.getOriginY() + myCar.getCarTexture().getRegionHeight() / 2 + 5, myCar.getCarTexture().getRegionWidth(), myCar.getCarTexture().getRegionHeight(), zoomMyCarX, zoomMyCarY, (float) Math.toDegrees(myCar.body.getAngle()));

        } else if (!isMyCarCollisionWithBlocks)
            sb.draw(myCar.getMyCarAnimation().getKeyFrame(myCar.getStateTime(), true), myCar.body.getPosition().x, myCar.body.getPosition().y - myCar.getCarTexture().getRegionHeight() / 2, myCar.getOriginX() + myCar.getCarTexture().getRegionWidth() / 2 + 5, myCar.getOriginY() + myCar.getCarTexture().getRegionHeight() / 2 + 5, myCar.getCarTexture().getRegionWidth(), myCar.getCarTexture().getRegionHeight(), 1, 1, (float) Math.toDegrees(myCar.body.getAngle()));
        //sb.draw(myCar.getMyCarAnimation().getKeyFrame(myCar.getStateTime(), true), myCar.body.getPosition().x, myCar.body.getPosition().y);
        sb.setColor(sb.getColor().r, sb.getColor().g, sb.getColor().b, 1);
        //sb.draw(myCar.getMyCarAnimation().getKeyFrame(myCar.getStateTime(), true), myCar.body.getPosition().x, myCar.body.getPosition().y);
        sb.draw(textureCollisisonPoint, myCar.body.getPosition().x * PIXELS_TO_METERS, myCar.body.getPosition().y * PIXELS_TO_METERS);

//        System.out.println("IsTurn " + myCar.isTurnRun());
        if (playTimeAnimation < 0.5 && isMyCarCollision) {
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


        if (GameManager.isCollisionWithCar()) {
            //collision.draw(sb);
            GameManager.setIsCollisionWithCar(false);
//            if (collision.isComplete())
//                collision.reset();
        }

        if (wingOnCarLeft.size() != 0 && wingOnCarRight.size() != 0 && isZoomCarUpdate) {
            sb.draw(wingOnCarLeft.get(0).getBoostOnCar(), wingOnCarLeft.get(0).body.getPosition().x - 7f, wingOnCarLeft.get(0).body.getPosition().y, wingOnCarLeft.get(0).getOriginX() + wingOnCarLeft.get(0).getBoostOnCar().getRegionWidth(), wingOnCarLeft.get(0).getOriginY() + wingOnCarLeft.get(0).getBoostOnCar().getRegionHeight(), wingOnCarLeft.get(0).getBoostOnCar().getRegionWidth(), wingOnCarLeft.get(0).getBoostOnCar().getRegionHeight(), 1, 1, myCar.body.getAngle());
            sb.draw(wingOnCarRight.get(0).getBoostOnCar(), wingOnCarRight.get(0).body.getPosition().x + 5f, wingOnCarRight.get(0).body.getPosition().y, wingOnCarRight.get(0).getOriginX() + wingOnCarRight.get(0).getBoostOnCar().getRegionWidth(), wingOnCarRight.get(0).getOriginY() + wingOnCarRight.get(0).getBoostOnCar().getRegionHeight(), wingOnCarRight.get(0).getBoostOnCar().getRegionWidth(), wingOnCarRight.get(0).getBoostOnCar().getRegionHeight(), 1, 1, myCar.body.getAngle());

        }


        if (boostOnCarRight.size() != 0 && boostOnCarLeft.size() != 0) {

//            sb.draw(boostOnCarLeft.get(0).getBoostOnCar(), boostOnCarLeft.get(0).body.getPosition().x - GameManager.getCurrentCar().getBrakeLines().getLeftLineStart() + GameManager.getCurrentCar().getLeftRocketPosition().getX() - boostOnCarLeft.get(0).getBoostOnCar().getRegionWidth(), boostOnCarLeft.get(0).body.getPosition().y - myCar.getCarTexture().getRegionHeight() / 2 - GameManager.getCurrentCar().getLeftRocketPosition().getY() - boostOnCarLeft.get(0).getBoostOnCar().getRegionHeight());
            sb.draw(boostOnCarLeft.get(0).getBoostOnCar(), boostOnCarLeft.get(0).body.getPosition().x, boostOnCarLeft.get(0).body.getPosition().y);
            sb.draw(boostOnCarRight.get(0).getBoostOnCar(), boostOnCarRight.get(0).body.getPosition().x, boostOnCarRight.get(0).body.getPosition().y);
            pf.draw(sb);
            if (pf.isComplete())
                pf.reset();
            pfl.draw(sb);
            if (pfl.isComplete())
                pfl.reset();
//            sb.draw(boostOnCarRight.get(0).getBoostOnCar(), boostOnCarRight.get(0).body.getPosition().x , boostOnCarLeft.get(0).body.getPosition().y - myCar.getCarTexture().getRegionHeight() / 2 - GameManager.getCurrentCar().getLeftRocketPosition().getY() - boostOnCarLeft.get(0).getBoostOnCar().getRegionHeight());
        }
        if (isUpdateGodeMode && thronsOnCarLeft.size() != 0 && thronsOnCarRight.size() != 0) {
            sb.draw(thronsOnCarLeft.get(0).getBoostOnCar(), thronsOnCarLeft.get(0).body.getPosition().x - GameManager.getCurrentCar().getBrakeLines().getLeftLineStart() + GameManager.getCurrentCar().getLeftRocketPosition().getX() - thronsOnCarLeft.get(0).getBoostOnCar().getRegionWidth(), thronsOnCarLeft.get(0).body.getPosition().y - myCar.getCarTexture().getRegionHeight() / 2 - GameManager.getCurrentCar().getLeftRocketPosition().getY() - thronsOnCarLeft.get(0).getBoostOnCar().getRegionHeight());
//            sb.draw(thronsOnCarRight.get(0).getBoostOnCar(), thronsOnCarRight.get(0).body.getPosition().x + GameManager.getCurrentCar().getRightRocketPosition().getX(), thronsOnCarRight.get(0).body.getPosition().y - myCar.getCarTexture().getRegionHeight() / 2 - GameManager.getCurrentCar().getLeftRocketPosition().getY() - thronsOnCarRight.get(0).getBoostOnCar().getRegionHeight());
            //sb.draw(thronsOnCarLeft.get(0).getBoostOnCar(), thronsOnCarLeft.get(0).body.getPosition().x - GameManager.getCurrentCar().getBrakeLines().getLeftLineStart() + GameManager.getCurrentCar().getLeftRocketPosition().getX() - thronsOnCarLeft.get(0).getBoostOnCar().getRegionWidth(), thronsOnCarLeft.get(0).body.getPosition().y - myCar.getCarTexture().getRegionHeight() / 2 - GameManager.getCurrentCar().getLeftRocketPosition().getY() - thronsOnCarLeft.get(0).getBoostOnCar().getRegionHeight());
            sb.draw(thronsOnCarRight.get(0).getBoostOnCar(), thronsOnCarRight.get(0).body.getPosition().x + GameManager.getCurrentCar().getRightRocketPosition().getX(), thronsOnCarRight.get(0).body.getPosition().y - myCar.getCarTexture().getRegionHeight() / 2 - GameManager.getCurrentCar().getLeftRocketPosition().getY() - thronsOnCarRight.get(0).getBoostOnCar().getRegionHeight());
        }
        for (Bushs bushs : bushsArrayLeft) {
            sb.draw(bushs.getBushTexture(), bushs.getPosition().x, bushs.getPosition().y);
        }
        for (Bushs bushs : bushsArrayRight) {
            sb.draw(bushs.getBushTexture(), bushs.getPosition().x, bushs.getPosition().y);
        }
        sb.draw(trafficLight.getTexture(), trafficLight.getPosition().x, trafficLight.getPosition().y);

        for (RoadLighter roadLighter : roadLighters) {
            sb.draw(roadLighter.getRoadLighterTexture(), roadLighter.getPosition().x, roadLighter.getPosition().y);

        }
        sb.draw(textureCollisisonPoint, -10, -10);


        // sb.draw(crashAnimation.getKeyFrame(playTimeAnimation, true), GameManager.getContactPointX() + 20, GameManager.getContactPointY() + 15);


        if (isDirtUpdate)
            for (DirtOnScreen dirtOnScreen : dirtOnScreens) {
                dirtOnScreen.draw(sb);
            }


        //bitmapFont.draw(sb, "FPS = " + String.valueOf(Gdx.graphics.getFramesPerSecond()), camera.viewportWidth - 100, camera.position.y - camera.viewportHeight / 2 + 20);
        stage.draw();
        sb.end();
    }

    @Override
    protected void handleInput() {

        if (Gdx.input.justTouched()) {
            if (!isGamePause() && !isJumpCarUpdate)
                if (!isAfterPause && !isAutoTurn && !isMyCarCollision && !isMyCarCollisionWithBlocks)
                    myCar.turn();
                else isAfterPause = false;
        }
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
        //garageButton.hide();

    }

    @Override
    public void onSaveMe() {
        for (int i = 0; i < passerCars.size() - 1; i++) {
            if (((PasserCarDataType) passerCars.get(i).body.getUserData()).isContact()) {
                passerCars.get(i).remove();
                world.destroyBody(passerCars.get(i).body);
                passerCars.remove(i);
            }
        }
        playTimeAnimation = 0;
        isPause = false;
        isMyCarCollision = false;

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
    }


    public void startFromGarage() {

        isStartTrafficLighter = true;
        // garageButton.hide();
        //pauseButton.show();
        //resumeButton.hide();
        //isFromGarage = false;
    }

    public void updateBushs() {

    }

    @Override
    public void onPauseButton() {
        pauseGame();
        isAfterPause = true;
        //pauseButton.hide();
        stage.addActor(new MenuPause(gameState, gsm,actionResolver));
        //resumeButton.show();
        //garageButton.show();
    }

    @Override
    public void pauseAfterCollision() {

    }

    @Override
    public void updateCoinCount(Integer countCoin) {
        labelCoinCount.setText(String.valueOf(countCoin));

        labelCoinCount.setBounds(imageButton.getX() - labelCoinCount.getPrefWidth() - 5, imageButton.getY() - 5, labelCoinCount.getWidth(), labelCoinCount.getHeight());
        // testCoinCount.setText( String.valueOf(countCoin));
    }

    @Override
    public void onStartRelaxZone(boolean isStart, float dt) {
        timeToCoin += dt;

        if (isStart == false)
            setUpPasserCars();
    }

    @Override
    public void onSetSpringBoar() {
        springboards.add(new Springboard(world, 90, 800, 10));
    }

    @Override
    public void onAddBoost() {
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

        //springboard = null;
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
        skullBonusText.setColor(Color.RED);
        skullBonusText.setFontScale(0.7f, 0.7f);
        //labelCoinCount.setBounds(imageButton.getX() - labelCoinCount.getPrefWidth() - 5, imageButton.getY() - 5, labelCoinCount.getWidth(), labelCoinCount.getHeight());

        skullBonusText.setPosition(GameRuners.WIDTH / 4 - 25, GameRuners.HEIGHT / 4 + 220);
        skullBonusText.setText("Car");
        stage.addActor(skullBonusText);

        skullBonusCountCar.setColor(Color.WHITE);
        skullBonusCountCar.setFontScale(0.7f, 0.7f);
        //labelCoinCount.setBounds(imageButton.getX() - labelCoinCount.getPrefWidth() - 5, imageButton.getY() - 5, labelCoinCount.getWidth(), labelCoinCount.getHeight());

        skullBonusCountCar.setPosition(GameRuners.WIDTH / 4 - 10, GameRuners.HEIGHT / 4 + 180);
        skullCarCount++;
        GameManager.addGodModeCount();
        skullBonusCountCar.setText(String.valueOf((int) skullCarCount));
        stage.addActor(skullBonusCountCar);

        skullAchives += 100;
    }

    @Override
    public void onCollisionWithPasserCar() {
        if (isUpdateGodeMode) {

            labelCountCar.setText("");
            labelCars.setText("");
            labelFlyText.setText("");
            labelFlyCount.setText("");
            labelFlyBonus.setText("");
            boosterBonus.setText("");
            labelBonusText.setText("");
            ladleBonus.setText("");


            skullBonusText.setColor(Color.RED);
            skullBonusText.setFontScale(0.7f, 0.7f);
            //labelCoinCount.setBounds(imageButton.getX() - labelCoinCount.getPrefWidth() - 5, imageButton.getY() - 5, labelCoinCount.getWidth(), labelCoinCount.getHeight());

            skullBonusText.setPosition(GameRuners.WIDTH / 4 - 25, GameRuners.HEIGHT / 4 + 220);
            skullBonusText.setText("Car");
            stage.addActor(skullBonusText);

            skullBonusCountCar.setColor(Color.WHITE);
            skullBonusCountCar.setFontScale(0.7f, 0.7f);
            //labelCoinCount.setBounds(imageButton.getX() - labelCoinCount.getPrefWidth() - 5, imageButton.getY() - 5, labelCoinCount.getWidth(), labelCoinCount.getHeight());

            skullBonusCountCar.setPosition(GameRuners.WIDTH / 4 - 10, GameRuners.HEIGHT / 4 + 180);
            skullCarCount++;
            GameManager.addGodModeCount();
            skullBonusCountCar.setText(String.valueOf((int) skullCarCount));
            stage.addActor(skullBonusCountCar);

            skullAchives += 100;
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
        isJumpCar = true;
        isJumpCarUpdate = true;
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
            Array<Action> actions = myCar.getActions();
            for (Action action : actions) {
                myCar.removeAction(action);
            }
            // world.setGravity(new Vector2(0,-200));
            for (PasserCar passerCar : passerCars) {
                if (((PasserCarDataType) passerCar.body.getUserData()).isContact()) {
                    if (passerCar.getIsLeft() && myCar.isTurnRun())
                        passerCar.body.setLinearVelocity(0, GameManager.getCurrentSpeed() * 3);
                    else if (!passerCar.getIsLeft() && myCar.isTurnRun()) {
                        passerCar.body.setLinearVelocity(0, GameManager.getCurrentSpeed() * 3);
                    }
                }
            }

            //System.out.println("myCar.isTurnRun() " + myCar.isTurnRun());
            isMyCarCollision = true;
            if (myCar.isLeft())
                myCar.body.setLinearVelocity(-800, GameManager.getCurrentSpeed() + 56);
            else if (!myCar.isLeft())
                myCar.body.setLinearVelocity(800, GameManager.getCurrentSpeed() + 56);
            //else myCar.body.setLinearVelocity(0, GameManager.getCurrentSpeed() + 56);

        }
    }

    @Override
    public void onFlyCollision() {


        labelCountCar.setText("");
        labelCars.setText("");
        boosterBonus.setText("");
        labelBonusText.setText("");
        ladleBonus.setText("");
        skullBonus.setText("");
        skullBonusText.setText("");
        skullBonusCountCar.setText("");

        achivesFly += 100;
        labelFlyText.setColor(Color.RED);
        labelFlyText.setFontScale(0.7f, 0.7f);

        labelFlyText.setPosition(GameRuners.WIDTH / 4 - 35, GameRuners.HEIGHT / 4 + 220);
        labelFlyText.setText("Cars");
        stage.addActor(labelFlyText);

        labelFlyCount.setColor(Color.WHITE);
        labelFlyCount.setFontScale(0.7f, 0.7f);
        labelFlyCount.setPosition(GameRuners.WIDTH / 4 - 5, GameRuners.HEIGHT / 4 + 180);
        labelFlyCount.setText(String.valueOf((int) contactFlyCars++));
        GameManager.addSpringBoardCount();
        stage.addActor(labelFlyCount);


    }

    @Override
    public void onBlockCollision() {
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

    @Override
    public void resumeFromPause() {

        StartGame();
        ((MyCarDataType) myCar.body.getUserData()).setIsAfterPause(true);
        isAfterPauseUpdate = true;
        isStartAfterPause = true;
    }
}