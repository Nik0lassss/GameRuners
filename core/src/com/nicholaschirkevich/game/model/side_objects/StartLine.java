package com.nicholaschirkevich.game.model.side_objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.nicholaschirkevich.game.util.AssetsManager;
import com.nicholaschirkevich.game.util.Constants;

/**
 * Created by Nikolas on 25.02.2016.
 */
public class StartLine {
    private Texture texture;
    private Vector2 position;
    private Rectangle bounds;



    public StartLine(int x, int y) {

        texture = AssetsManager.getTextureRegion(Constants.START_LINE_ID).getTexture();
        position = new Vector2(x, y);
        bounds = new Rectangle(x, y, texture.getWidth(), texture.getHeight());

    }



    public Texture getTexture() {
        return texture;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Rectangle getBounds() {
        return bounds;
    }







}
