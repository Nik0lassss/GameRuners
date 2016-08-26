package com.nicholaschirkevich.game.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Nikolas on 11.05.2016.
 */
public class SystemToastMessageRectangle extends Actor {
    private ShapeRenderer shapeRenderer;

    public SystemToastMessageRectangle() {
        shapeRenderer = new ShapeRenderer();

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        batch.end();
        Gdx.graphics.getGL20().glEnable(GL20.GL_BLEND);
        batch.setColor(84f / 255f, 124f / 255f, 154f / 255f, 0.2f);
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        Color colorShadowBackground = new Color(Color.rgba8888(0f/255f,0f/255f,0f/255f,0.5f));
        shapeRenderer.setColor(colorShadowBackground);
        shapeRenderer.rect(20, 65, 276, 10);
        shapeRenderer.end();
        batch.begin();
    }
}
