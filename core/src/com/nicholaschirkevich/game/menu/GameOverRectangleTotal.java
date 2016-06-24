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
public class GameOverRectangleTotal extends Actor {
    private ShapeRenderer shapeRenderer;

    public GameOverRectangleTotal() {
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
        shapeRenderer.rect(20, 15, 276, 10);
        shapeRenderer.end();
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(20, 20, 276, 44);
        shapeRenderer.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        Color color = new Color(Color.rgba8888(28f/255f,163f/255f,252f/255f,1.0f));
        shapeRenderer.setColor(color);
        shapeRenderer.rect(23, 23, 270, 38);
        shapeRenderer.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        Color colorShadow = new Color(Color.rgba8888(141f/255f,209f/255f,254f/255f,0.5f));
        shapeRenderer.setColor(colorShadow);
        shapeRenderer.rect(23, 53, 270, 10);
        shapeRenderer.end();
       // shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//        Color colorLineDelimiter = new Color(Color.rgba8888(26f/255f,112f/255f,168f/255f,1f));
//        shapeRenderer.setColor(colorLineDelimiter);
//        shapeRenderer.rect(33, 63, 250, 50);
//        shapeRenderer.rect(33, 163, 250, 50);
//        shapeRenderer.rect(33, 263, 250, 50);
//        shapeRenderer.end();


        batch.begin();
    }
}
