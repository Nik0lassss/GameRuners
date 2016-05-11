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
public class GameOverRectangle extends Actor {
    private ShapeRenderer shapeRenderer;

    public GameOverRectangle() {
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
        shapeRenderer.rect(30, 85, 256, 10);
        shapeRenderer.end();
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(30, 90, 256, 306);
        shapeRenderer.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        Color color = new Color(Color.rgba8888(28f/255f,163f/255f,252f/255f,1.0f));
        shapeRenderer.setColor(color);
        shapeRenderer.rect(33, 93, 250, 300);
        shapeRenderer.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        Color colorShadow = new Color(Color.rgba8888(141f/255f,209f/255f,254f/255f,0.5f));
        shapeRenderer.setColor(colorShadow);
        shapeRenderer.rect(33, 383, 250, 10);
        shapeRenderer.end();


        batch.begin();
    }
}
