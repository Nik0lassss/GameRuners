package com.nicholaschirkevich.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Nikolas on 25.02.2016.
 */
public class StartLine {
    private Texture texture;
    private Vector2 position;
    private Rectangle bounds;



    public StartLine(int x, int y) {

        texture =  new Texture("start_line.png");
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
