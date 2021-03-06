package com.nicholaschirkevich.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by Nikolas on 10.03.2016.
 */
public class ShopScreen implements Screen {


    OrthographicCamera camera;

    Stage stage;

    ScrollPane scrollPane;

    //container is new
    Table table, container;

    Texture texture1, texture2, texture3, texture4, texture5, texture6;
    ImageButton button1, button2, button3, button4, button5, button6;

    public ShopScreen() {


        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        stage = new Stage();

        //Add

        container = new Table();

        table = new Table();

        stage.addActor(container);

        container.setFillParent(true);

        final ScrollPane scrollPane = new ScrollPane(table);

        float myPadTop = Gdx.graphics.getHeight() / 2 - scrollPane.getHeight() / 2;
        float myPadBottom = Gdx.graphics.getHeight() / 2 - scrollPane.getHeight() / 2;

        scrollPane.setFlickScroll(true);

        container.add(scrollPane).padTop(myPadTop).padBottom(myPadBottom);

        table.pad(10).defaults().expandX().space(4);

        //

        texture1 = new Texture(Gdx.files.internal("road_6_tile.png"));
        texture2 = new Texture(Gdx.files.internal("road_6_tile.png"));
        texture3 = new Texture(Gdx.files.internal("road_6_tile.png"));
        texture4 = new Texture(Gdx.files.internal("road_6_tile.png"));
        texture5 = new Texture(Gdx.files.internal("road_6_tile.png"));
        texture6 = new Texture(Gdx.files.internal("road_6_tile.png"));

        button1 = new ImageButton(new Image(texture1).getDrawable());
        button2 = new ImageButton(new Image(texture2).getDrawable());
        button3 = new ImageButton(new Image(texture3).getDrawable());
        button4 = new ImageButton(new Image(texture4).getDrawable());
        button5 = new ImageButton(new Image(texture5).getDrawable());
        button6 = new ImageButton(new Image(texture6).getDrawable());

        //table.setFillParent(true);//Remove
        //table.defaults().width(Gdx.graphics.getWidth()/2.5f).height(Gdx.graphics.getHeight()/(8*Gd//x.graphics.getHeight()/Gdx.graphics.getWidth()));//Remove

        table.add(button1).row();
        table.add(button2).row();
        table.add(button3).row();
        table.add(button4).row();
        table.add(button5).row();
        table.add(button6).row();

        //scrollPane.setX(Gdx.graphics.getWidth()/2-scrollPane.getWidth()/2);//Remove
        //scrollPane.setY(Gdx.graphics.getHeight()/2-scrollPane.getHeight()/2);//Remove


        button1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Test");
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.281f, 0.602f, 0.844f, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void show() {
        //stage.addActor(scrollPane);
        Gdx.input.setInputProcessor(stage);
    }
}