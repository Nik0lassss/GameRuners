package com.nicholaschirkevich.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.nicholaschirkevich.game.GameRuners;
import com.nicholaschirkevich.game.admob.ActionResolver;
import com.nicholaschirkevich.game.entity.CarsType;
import com.nicholaschirkevich.game.interfaces.ResumeButtonListener;
import com.nicholaschirkevich.game.interfaces.UpdateGarageTable;
import com.nicholaschirkevich.game.menu.BackButton;
import com.nicholaschirkevich.game.menu.StartGameGarageButton;
import com.nicholaschirkevich.game.util.AssetsManager;
import com.nicholaschirkevich.game.util.CarSortingManager;
import com.nicholaschirkevich.game.util.Constants;
import com.nicholaschirkevich.game.util.GameManager;
import com.nicholaschirkevich.game.view_adapters.GarageAdapter;

import java.util.ArrayList;


/**
 * Created by Nikolas on 10.03.2016.
 */
public class CarShopState extends State implements ResumeButtonListener, UpdateGarageTable {
    OrthographicCamera camera;

    Stage stage;
    Texture cnr_line, garageNameTexture;
    Image image, garageNameImage;
    ScrollPane scrollPane2;
    BackButton backButton;
    StartGameGarageButton startGameGarageButton;
    Table table, container;
    private ActionResolver actionResolver;


    public CarShopState(GameStateManager gsm, ActionResolver actionResolver) {
        super(gsm);
        this.actionResolver = actionResolver;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        stage = new Stage(new StretchViewport(GameRuners.WIDTH / 2, GameRuners.HEIGHT / 2));
        image = new Image(AssetsManager.getTextureRegion(Constants.CNR_LINE_ID));
        image.setBounds(0, GameRuners.HEIGHT / 2 - 80, GameRuners.WIDTH / 2, 80);


        garageNameTexture = AssetsManager.getTextureRegion(Constants.TITLE_VEHICLES_ID).getTexture();
        garageNameImage = new Image(garageNameTexture);
        garageNameImage.setBounds(20, GameRuners.HEIGHT / 2 - 40, garageNameTexture.getWidth(), garageNameTexture.getHeight());


        setUpGarage();
        setUpBackButton();
        setUpStartButton();
        setUpResumeImageButton();
        stage.addActor(image);
        stage.addActor(garageNameImage);

        Gdx.input.setInputProcessor(stage);
    }

    public void setUpResumeImageButton()
    {
       ImageButton resumeImageButton = new ImageButton(new Image(AssetsManager.getTextureRegion(Constants.RESUME_BTTN_ID)).getDrawable(),new Image(AssetsManager.getTextureRegion(Constants.RESUME_BTTN_ID)).getDrawable());
        resumeImageButton.setBounds(GameRuners.WIDTH / 4-45,GameRuners.HEIGHT / 4-282,resumeImageButton.getWidth(),resumeImageButton.getHeight());
        resumeImageButton.addListener(new ClickListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                gsm.push(new GameState(gsm, false, true, actionResolver));
                return true;
            }
        });
        stage.addActor(resumeImageButton);
    }

    public void setUpBackButton() {

        float width = 43, height = 49;
        backButton = new BackButton(Constants.GARAGE_BTTN_X_VISIBLE-10, Constants.GARAGE_BTTN_Y - (height / 2)-10, width, height, gsm, actionResolver);
        stage.addActor(backButton);
    }

    public void setUpStartButton() {
        float x = Constants.RESUME_BTTN_X_VISIBLE, y = Constants.RESUME_BTTN_Y_VISIBLE - 190, width = 70, height = 55;
        startGameGarageButton = new StartGameGarageButton(x - (width / 2), y - (height / 2), width, height, gsm, actionResolver);
        stage.addActor(startGameGarageButton);
    }

    public void setUpGarage() {
        container = new Table();
        table = new Table();
        stage.addActor(container);
        ArrayList<CarsType> carsTypes = GameManager.getCarsTypes();
        GarageAdapter garageAdapter = new GarageAdapter(table, CarSortingManager.sortCars(carsTypes), actionResolver);
        table.top();
        table.padTop(20f);
        garageAdapter.loadTableData();

        scrollPane2 = new ScrollPane(table);
        scrollPane2.setBounds(0, 0, GameRuners.WIDTH / 2, GameRuners.HEIGHT / 2 - 70);
        scrollPane2.setScrollingDisabled(true, false);
        scrollPane2.setOverscroll(false, false);
        scrollPane2.invalidate();

        stage.addActor(scrollPane2);


    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
//        Gdx.gl.glClearColor(0.281f, 0.602f, 0.844f, 0);
        Gdx.gl.glClearColor(0.098f, 0.655f, 0.976f, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
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

    }

    @Override
    public void onSaveMe() {

    }


    @Override
    public void updateTable() {
        table.remove();
        setUpGarage();
//        table.remove();
//        stage.addActor(scrollPane2);
    }

    @Override
    public void setSelectedItme(int index) {

    }
}
