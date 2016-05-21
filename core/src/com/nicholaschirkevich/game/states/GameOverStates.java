package com.nicholaschirkevich.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.nicholaschirkevich.game.GameRuners;
import com.nicholaschirkevich.game.admob.ActionResolver;


public class GameOverStates extends State {

    private Texture background;
    private BitmapFont bitmapFont;
    private Texture playBtn;
    private ActionResolver actionResolver;

    public GameOverStates(GameStateManager gsm, ActionResolver actionResolver) {
        super(gsm);
        this.actionResolver= actionResolver;
        camera.setToOrtho(false, GameRuners.WIDTH / 2, GameRuners.HEIGHT / 2);
        background = new Texture("bg.jpg");
        playBtn = new Texture("bttn_resume.png");
        bitmapFont = new BitmapFont();

    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.set(new GameState(gsm,false,false,actionResolver));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {

        sb.begin();
        sb.setProjectionMatrix(camera.combined);
        sb.setProjectionMatrix(camera.combined);
        sb.draw(background, 0, 0, camera.viewportWidth,camera.viewportHeight);
        sb.draw(playBtn, (camera.viewportWidth / 2) - (playBtn.getWidth() / 2), camera.viewportHeight / 2);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }
}
