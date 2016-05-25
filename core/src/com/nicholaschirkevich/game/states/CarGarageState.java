package com.nicholaschirkevich.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.nicholaschirkevich.game.GameRuners;
import com.nicholaschirkevich.game.admob.ActionResolver;
import com.nicholaschirkevich.game.entity.Car;
import com.nicholaschirkevich.game.menu.BackButton;
import com.nicholaschirkevich.game.menu.StartGameGarageButton;
import com.nicholaschirkevich.game.model.side_objects.Bushs;
import com.nicholaschirkevich.game.model.side_objects.Road;
import com.nicholaschirkevich.game.util.AssetsManager;
import com.nicholaschirkevich.game.util.Constants;
import com.nicholaschirkevich.game.util.GameManager;

import java.util.ArrayList;


/**
 * Created by Nikolas on 10.03.2016.
 */
public class CarGarageState extends State {
    OrthographicCamera camera;
    Car prizeCar = GameManager.getRandomCarForGarage();
    //Texture prizeCarTexture = AssetsManager.getTextureRegion(GameManager.getCarsTypes().get(0).getCars().get(3).getID()).getTexture();
    Texture prizeCarTexture = AssetsManager.getTextureRegion(prizeCar.getID()).getTexture();
    TextureRegion textureRegion = new TextureRegion(prizeCarTexture);
    Stage stage;
    Texture cnr_line;
    Image image;
    SequenceAction sequence;
    TextButton resumeButton;
    Image resumeButtonUpImage, resumeButtonDownImage;

    private Animation garageAnimation;
    ArrayList<Bushs> bushsArrayLeft, bushsArrayRight;
    BackButton backButton;
    Texture garageTexture;
    Image garage, myCar;
    StartGameGarageButton startGameGarageButton;
    private float platTime = 0;
    private float posX = 85, posY = 460;
    private int heightTexture = prizeCarTexture.getHeight();
    private int fullHeight = prizeCarTexture.getHeight();
    Road road;
    private ActionResolver actionResolver;

    public CarGarageState(GameStateManager gsm, ActionResolver actionResolver) {
        super(gsm);
        this.actionResolver = actionResolver;
        bushsArrayLeft = new ArrayList<Bushs>();
        bushsArrayRight = new ArrayList<Bushs>();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        stage = new Stage(new StretchViewport(GameRuners.WIDTH / 2, GameRuners.HEIGHT / 2));
        cnr_line = AssetsManager.getTextureRegion(Constants.CNR_LINE_ID).getTexture();
        garageTexture = AssetsManager.getTextureRegion(Constants.GARAGE_ID).getTexture();
        garage = new Image(garageTexture);
        garage.setScale(0.4f, 0.4f);
        garage.setBounds(20, 320, garage.getPrefWidth(), garage.getPrefHeight());
        road = new Road();
        for (int i = (int) camera.viewportHeight + (int) camera.position.y; i > 0; i -= 70) {
            bushsArrayLeft.add(new Bushs(90, i, 10, false, Constants.ROAD_1_BUSH_1_STATIC_ASSETS_ID));
            bushsArrayRight.add(new Bushs(90, i, 10, true, Constants.ROAD_1_BUSH_1_STATIC_ASSETS_ID));
        }
        resumeButtonUpImage = new Image(AssetsManager.getTextureRegion(Constants.MAIN_MENU_PLAY_BTTN_UP_ID));
        resumeButtonDownImage = new Image(AssetsManager.getTextureRegion(Constants.MAIN_MENU_PLAY_BTTN_PRESSERD_ID));

        //textureRegion.setRegionHeight(70);
        sequence = new SequenceAction();
        textureRegion.setRegion(0, (int) heightTexture, textureRegion.getRegionWidth(), textureRegion.getRegionHeight() - heightTexture);

        myCar = new Image(textureRegion);
        myCar.setBounds(20, 320, textureRegion.getRegionWidth(), textureRegion.getRegionHeight());

        image = new Image(cnr_line);
        garageAnimation = AssetsManager.getAnimation(Constants.GATE_ASSETS_ID);
        image.setBounds(0, GameRuners.HEIGHT / 2 - 80, GameRuners.WIDTH / 2, 80);


//        garageNameTexture = new Texture("title_vehicles.png");
//        garageNameImage = new Image(garageNameTexture);
//        garageNameImage.setBounds(20, GameRuners.HEIGHT / 2 - 40, garageNameTexture.getWidth(), garageNameTexture.getHeight());


        //setUpGarage();
        setUpBackButton();
        setUpStartButton();
        stage.addActor(garage);
        //stage.addActor(myCar);
        //stage.addActor(image);
        // stage.addActor(garageNameImage);

        Gdx.input.setInputProcessor(stage);
    }

    public void setUpBackButton() {

        float width = 43, height = 49;
        backButton = new BackButton(Constants.GARAGE_BTTN_X_VISIBLE, Constants.GARAGE_BTTN_Y - (height / 2), width, height, gsm);
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


    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
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
            }
            else {
                setUpResume();
            }
        }


    }

    private void setUpResume() {

        float x = Constants.RESUME_BTTN_X_VISIBLE, y = Constants.RESUME_BTTN_Y_VISIBLE, width = 70, height = 55;
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.down = resumeButtonDownImage.getDrawable();
        textButtonStyle.up = resumeButtonUpImage.getDrawable();
        textButtonStyle.font = AssetsManager.getUiSkin().getFont("default-font");

        resumeButton = new TextButton("Play", textButtonStyle);
        resumeButton.getLabel().setFontScale(0.6f, 0.6f);
        resumeButton.getLabelCell().padLeft(25f);


        resumeButton.setBounds(x - resumeButtonUpImage.getWidth() / 2, y - resumeButtonUpImage.getHeight() / 2, resumeButtonUpImage.getWidth(), resumeButtonUpImage.getHeight());

        resumeButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                sequence.addAction(Actions.delay(0.3f));
                sequence.addAction(new Action() {
                    @Override
                    public boolean act(float delta) {
                        gsm.push(new GameState(gsm,false,true,actionResolver));
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
//        Gdx.gl.glClearColor(0.281f, 0.602f, 0.844f, 0);
        Gdx.gl.glClearColor(0.098f, 0.655f, 0.976f, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());

        sb.begin();

        sb.draw(road.getRoad1(), road.getPosRoad1().x, road.getPosRoad1().y);
        sb.draw(road.getRoad2(), road.getPosRoad2().x, road.getPosRoad2().y);
        for (Bushs bushs : bushsArrayRight) {
            sb.draw(bushs.getBushTexture(), bushs.getPosition().x, bushs.getPosition().y);
        }
        for (Bushs bushs : bushsArrayLeft) {
            sb.draw(bushs.getBushTexture(), bushs.getPosition().x, bushs.getPosition().y);
        }
        sb.end();
        stage.draw();
        sb.begin();
        sb.draw(garageAnimation.getKeyFrame(platTime, false), 65, 415);
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
