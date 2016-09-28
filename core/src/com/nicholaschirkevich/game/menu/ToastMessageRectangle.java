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
public class ToastMessageRectangle extends Actor {
    private ShapeRenderer shapeRenderer;
    private float mainRectX = 21, mainRectY = 150, mainRectWidth = 280, mainRectHeight = 160;
    private float whiteRectX = mainRectX + 4, whiteRectY = mainRectY + 4, whiteRectWidth = mainRectWidth - 8, whiteRectHeight = mainRectHeight - 8;
    private float buttonBorderX = mainRectX + 4, buttonBorderY = mainRectY + 64, buttonBordertWidth = mainRectWidth - 8, buttonBorderHeight = 4;
    private float buttonDelimiterBorderX = mainRectX + 154, buttonDelimiterBorderY = mainRectY+4, buttonDelimiterBorderWidth =4, buttonDelimiterBorderHeight = 62;
    private float imageBorderRectX = mainRectX + 26, imageBorderRectY = mainRectY + 91, imageBorderWidth = mainRectWidth - 52, imageBorderRectHeight = mainRectHeight - 122;

    public float getAlfa() {
        return alfa;
    }

    public void setAlfa(float alfa) {
        this.alfa = alfa;
    }

    private float alfa = 1;

    public ToastMessageRectangle() {
        shapeRenderer = new ShapeRenderer();

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        batch.end();
        Gdx.graphics.getGL20().glEnable(GL20.GL_BLEND);
        batch.setColor(84f / 255f, 124f / 255f, 154f / 255f, 0.2f);
        // shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//        Color colorShadowBackground = new Color(Color.rgba8888(0f/255f,0f/255f,0f/255f,0.5f));
//        shapeRenderer.setColor(colorShadowBackground);
//        shapeRenderer.rect(17, 15, 276, 276);
//        shapeRenderer.end();
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK.r, Color.BLACK.g, Color.BLACK.b, alfa);
        shapeRenderer.rect(mainRectX, mainRectY, mainRectWidth, mainRectHeight);
        shapeRenderer.end();
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(new Color(Color.rgba8888(28f / 255f, 163f / 255f, 252f / 255f, alfa)));
        shapeRenderer.rect(whiteRectX, whiteRectY, whiteRectWidth, whiteRectHeight);
        shapeRenderer.end();


//        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//        Color color = new Color(Color.rgba8888(28f/255f,163f/255f,252f/255f,1.0f));
//        shapeRenderer.setColor(color);
//        shapeRenderer.rect(20, 23, 270, 38);
//        shapeRenderer.end();
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//        Color colorShadow = new Color(Color.rgba8888(141f/255f,209f/255f,254f/255f,0.5f));
//        shapeRenderer.setColor(colorShadow);
//        shapeRenderer.rect(20, 53, 270, 10);
//        shapeRenderer.end();
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
