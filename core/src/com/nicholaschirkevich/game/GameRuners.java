package com.nicholaschirkevich.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nicholaschirkevich.game.states.GameState;
import com.nicholaschirkevich.game.states.GameStateManager;
import com.nicholaschirkevich.game.states.GarageState;
import com.nicholaschirkevich.game.util.AssetsManager;
import com.nicholaschirkevich.game.util.GameManager;

public class GameRuners extends ApplicationAdapter {
    public static final int WIDTH = 640;
    public static final int HEIGHT = 1136;

    public static final String TITLE = "Game";
    private GameStateManager gsm;
    private SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();
        gsm = new GameStateManager();
        Gdx.gl.glClearColor(1, 1, 1, 1);
        GameManager.loadData();
        AssetsManager.loadCars();
        AssetsManager.loadAssets();


        gsm.push(new GameState(gsm, true, false));


        //gsm.push(new GarageState(gsm));

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.render(batch);


    }

    @Override
    public void pause() {
        super.pause();
        gsm.pause();
    }

    @Override
    public void resume() {
        super.resume();
        gsm.resume();
        System.out.println("Resume frm GAME runers");
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
