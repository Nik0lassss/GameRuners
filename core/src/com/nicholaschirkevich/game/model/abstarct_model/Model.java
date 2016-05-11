package com.nicholaschirkevich.game.model.abstarct_model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Nikolas on 29.04.2016.
 */
public interface Model {
    void draw(SpriteBatch sb);
    void update(float dt);
}
