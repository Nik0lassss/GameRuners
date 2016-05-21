package com.nicholaschirkevich.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nicholaschirkevich.game.GameRuners;
import com.nicholaschirkevich.game.admob.ActionResolver;


public class MenuStates extends State {

    private Texture background;
    private Texture playBtn;
    private ActionResolver actionResolver;

    public MenuStates(GameStateManager gsm,ActionResolver actionResolver) {
        super(gsm);
        this.actionResolver = actionResolver;
        camera.setToOrtho(false, GameRuners.WIDTH / 2, GameRuners.HEIGHT / 2);
        background = new Texture("bg.jpg");

        playBtn = new Texture("bttn_resume.png");
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.set(new GameState(gsm,true,false,actionResolver));
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
        sb.draw(background, 0, 0, camera.viewportWidth,camera.viewportHeight);
        sb.draw(playBtn, (camera.viewportWidth / 2) - (playBtn.getWidth() / 2), camera.viewportHeight / 2);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }
}
