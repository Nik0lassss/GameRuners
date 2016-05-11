package com.nicholaschirkevich.game.model.side_objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.nicholaschirkevich.game.util.GameManager;

/**
 * Created by Nikolas on 25.02.2016.
 */
public class RoadHole {
    private Texture texture;
    private Vector2 position;
    private Rectangle bounds;



    public RoadHole(float x, float y) {

        texture =  new Texture("road_hole.png");
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

    public void update(float dt)
    {
        position.set(position.x, position.y + (-GameManager.getCurrentSpeed()) * dt);
    }






}
