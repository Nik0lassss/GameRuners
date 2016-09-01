package com.nicholaschirkevich.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.nicholaschirkevich.game.GameRuners;
import com.nicholaschirkevich.game.admob.ActionResolver;
import com.nicholaschirkevich.game.entity.Car;
import com.nicholaschirkevich.game.menu.BackButton;
import com.nicholaschirkevich.game.menu.StartGameGarageButton;
import com.nicholaschirkevich.game.model.side_objects.Bushs;
import com.nicholaschirkevich.game.model.side_objects.NewRoad;
import com.nicholaschirkevich.game.util.AssetsManager;
import com.nicholaschirkevich.game.util.Constants;
import com.nicholaschirkevich.game.util.GameManager;

import java.util.ArrayList;


/**
 * Created by Nikolas on 10.03.2016.
 */
public class CarGarageState extends State {
    public static final float RED = 0.098f;
    public static final float GREEN = 0.655f;
    public static final float BLUE = 0.976f;
    public static final int ALPHA = 0;
    public static final int GARAGE_ANIMATION_Y = 415;
    public static final int GARAGE_ANIMATION_X = 65;
    OrthographicCamera camera;
    Car prizeCar = GameManager.getRandomCarForGarage();
      Texture prizeCarTexture = AssetsManager.getTextureRegion(prizeCar.getID()).getTexture();
    TextureRegion textureRegion = new TextureRegion(prizeCarTexture);
    Stage stage;
    Texture cnr_line;
    Image image;
    SequenceAction sequence;
    TextButton resumeButton;
    private   ImageButton imageButton;
    private  Label labelCoinCount;
    Image resumeButtonUpImage, resumeButtonDownImage, getPrizeButtonImageUp, getPrizeButtonImageDown;

    private Animation garageAnimation;
    ArrayList<Bushs> bushsArrayLeft, bushsArrayRight;
    BackButton backButton;
    Texture garageTexture;
    Image garage, myCar;
    StartGameGarageButton startGameGarageButton;

    private State thisState;
    private float platTime = 0;
    private float posX = 85, posY = 460;
    private int heightTexture = prizeCarTexture.getHeight();
    private int fullHeight = prizeCarTexture.getHeight();
    NewRoad road;
    //Road road;
    private ActionResolver actionResolver;
    private TextButton getPrizeButton;
    private boolean isPlayAnimation = false;
    private boolean isFree;


    public CarGarageState(GameStateManager gsm, ActionResolver actionResolver, boolean isFree) {
        super(gsm);
        this.actionResolver = actionResolver;
        bushsArrayLeft = new ArrayList<Bushs>();
        bushsArrayRight = new ArrayList<Bushs>();
        this.isFree = isFree;

        thisState = this;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        stage = new Stage(new StretchViewport(GameRuners.WIDTH / 2, GameRuners.HEIGHT / 2));
        cnr_line = AssetsManager.getTextureRegion(Constants.CNR_LINE_ID).getTexture();
        garageTexture = AssetsManager.getTextureRegion(Constants.GARAGE_ID).getTexture();
        garage = new Image(garageTexture);
        garage.setScale(0.4f, 0.4f);
        garage.setBounds(20, 320, garage.getPrefWidth(), garage.getPrefHeight());
        road = GameManager.getRoads().get(GameManager.getCurrentCar().getMapType());

        resumeButtonUpImage = new Image(AssetsManager.getTextureRegion(Constants.MAIN_MENU_PLAY_BTTN_UP_ID));
        resumeButtonDownImage = new Image(AssetsManager.getTextureRegion(Constants.MAIN_MENU_PLAY_BTTN_PRESSERD_ID));
        getPrizeButtonImageUp = new Image(AssetsManager.getTextureRegion(Constants.CAR_GARAGE_BTTN_GREEN_UP));
        getPrizeButtonImageDown = new Image(AssetsManager.getTextureRegion(Constants.CAR_GARAGE_BTTN_GREEN_DOWN));


        sequence = new SequenceAction();
        textureRegion.setRegion(0, (int) heightTexture, textureRegion.getRegionWidth(), textureRegion.getRegionHeight() - heightTexture);

        myCar = new Image(textureRegion);
        myCar.setBounds(20, 320, textureRegion.getRegionWidth(), textureRegion.getRegionHeight());

        image = new Image(cnr_line);
        garageAnimation = AssetsManager.getAnimation(Constants.GATE_ASSETS_ID);
        image.setBounds(0, GameRuners.HEIGHT / 2 - 80, GameRuners.WIDTH / 2, 80);


//
        road.initialRoadForGarage(new Rectangle(garage.getX(), garage.getY(), garage.getWidth(), garage.getHeight()));

        setUpGetPrize();

        setUpBackButton();
        setUpStartButton();

        stage.addActor(garage);


        Gdx.input.setInputProcessor(stage);
    }

    public void setUpBackButton() {

        float width = 43, height = 49;
        backButton = new BackButton(Constants.GARAGE_BTTN_X_VISIBLE, Constants.GARAGE_BTTN_Y - (height / 2), width, height, gsm, actionResolver);
        backButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                gsm.set(new GameState(gsm, false, false, actionResolver));
                return true;
            }
        });
        stage.addActor(backButton);
    }

    public void setUpStartButton() {
        float x = Constants.RESUME_BTTN_X_VISIBLE, y = Constants.RESUME_BTTN_Y_VISIBLE - 190, width = 70, height = 55;
        startGameGarageButton = new StartGameGarageButton(x - (width / 2), y - (height / 2), width, height, gsm, actionResolver);
        stage.addActor(startGameGarageButton);
    }



    public void setUpGetPrize() {
        float x = Constants.GET_PRIZE_BTTN_X_VISIBLE, y = Constants.GET_PRIZE_Y_VISIBLE;
        final Image coinImage = new Image(AssetsManager.getAnimation(Constants.COIN_ASSETS_ID).getKeyFrames()[0]);
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.down = getPrizeButtonImageDown.getDrawable();
        textButtonStyle.up = getPrizeButtonImageUp.getDrawable();
        textButtonStyle.font = AssetsManager.getUiSkin().getFont("default-font");
        if (isFree) {
            getPrizeButton = new TextButton(GameManager.getStrings().get(Constants.GARAGE_FREE_LBL), textButtonStyle);
            getPrizeButton.getLabel().setFontScale(0.40f, 0.40f);
        } else {

            getPrizeButton = new TextButton("300", textButtonStyle);
            getPrizeButton.getLabel().setAlignment(Align.left);
            getPrizeButton.getLabelCell().padLeft(30f);
            getPrizeButton.getLabel().setFontScale(0.6f, 0.6f);

            coinImage.setBounds(x + 75, y + 20, coinImage.getWidth() - 3, coinImage.getHeight() - 7);

        }

        getPrizeButton.setBounds(x, y, 120, 55);
        int coinCount = 300 - GameManager.getCoinCounter();
        if (coinCount <= 0 || isFree)
            getPrizeButton.addListener(new ClickListener() {

                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    AssetsManager.playSound(Constants.SOUND_CLICK);
                    isPlayAnimation = true;
                    GameManager.addCar(prizeCar);
                    getPrizeButton.remove();
                    coinImage.remove();
                    if (!isFree) GameManager.setCountCoint(GameManager.getCoinCounter() - 300);
                    return super.touchDown(event, x, y, pointer, button);
                }
            });
        stage.addActor(getPrizeButton);
        if (!isFree) stage.addActor(coinImage);
    }

    public void animation(float dt) {
        platTime += dt;
        if (platTime > 1) {
            if (posY > 300) {
                if (posY < 400)
                    posY -= 3;
                else
                    posY -= 3;

                if (heightTexture >= 0) heightTexture -= 3;
                textureRegion = new TextureRegion(prizeCarTexture);

                textureRegion.setRegion(0, (int) heightTexture, textureRegion.getRegionWidth(), textureRegion.getRegionHeight() - heightTexture);
            } else {
                setUpResume();
            }
        }
    }


    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

        if (isPlayAnimation) animation(dt);

    }

    private void setUpResume() {

        float x = Constants.RESUME_BTTN_X_VISIBLE, y = Constants.RESUME_BTTN_Y_VISIBLE, width = 70, height = 55;
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.down = resumeButtonDownImage.getDrawable();
        textButtonStyle.up = resumeButtonUpImage.getDrawable();
        textButtonStyle.font = AssetsManager.getUiSkin().getFont("default-font");


        resumeButton = new TextButton(GameManager.getStrings().get(Constants.MP_PLAY_BTN), textButtonStyle);
        resumeButton.getLabel().setFontScale(0.45f, 0.45f);
        resumeButton.getLabelCell().padLeft(45f);


        resumeButton.setBounds(x - resumeButtonUpImage.getWidth() / 2, y - resumeButtonUpImage.getHeight() / 2, resumeButtonUpImage.getWidth(), resumeButtonUpImage.getHeight());

        resumeButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                AssetsManager.playSound(Constants.SOUND_CLICK);
                sequence.addAction(Actions.delay(0.3f));
                sequence.addAction(new Action() {
                    @Override
                    public boolean act(float delta) {
                        GameManager.setCurrentCarID(prizeCar.getID());
                        gsm.push(new GameState(gsm, false, true, actionResolver));
                        return true;
                    }
                });
                sequence.addAction(Actions.removeActor());
                resumeButton.addAction(sequence);

                return true;
            }
        });

        stage.addActor(resumeButton);

    }


    @Override
    public void render(SpriteBatch sb) {
//
        Gdx.gl.glClearColor(RED, GREEN, BLUE, ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());

        sb.begin();

        road.draw(sb);
        road.drawSideObject(sb);
//
        for (Bushs bushs : bushsArrayRight) {
            sb.draw(bushs.getBushTexture(), bushs.getPosition().x, bushs.getPosition().y);
        }
        for (Bushs bushs : bushsArrayLeft) {
            sb.draw(bushs.getBushTexture(), bushs.getPosition().x, bushs.getPosition().y);
        }
        sb.end();
        stage.draw();
        sb.begin();
        sb.draw(garageAnimation.getKeyFrame(platTime, false), GARAGE_ANIMATION_X, GARAGE_ANIMATION_Y);
        sb.draw(textureRegion, posX, posY);
        sb.end();
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


}
