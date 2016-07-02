package com.nicholaschirkevich.game.model.side_objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.nicholaschirkevich.game.util.GameManager;

/**
 * Created by Nikolas on 03.07.2016.
 */
public class SideObject {
    private String id;
    private Texture sideObjectTexture;
    private Vector3 position;

    public SideObject(Texture sideObjectTexture, Vector3 position) {
        this.sideObjectTexture = sideObjectTexture;
        this.position = position;

    }

    public SideObject(Texture sideObjectTexture) {
        this.sideObjectTexture = sideObjectTexture;
    }

    public Texture getSideObjectTexture() {
        return sideObjectTexture;
    }

    public void setSideObjectTexture(Texture sideObjectTexture) {
        this.sideObjectTexture = sideObjectTexture;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void update(float dt)
    {
        position.add(0, (-GameManager.getCurrentSpeed()) * dt, 0);
    }
    public void draw(SpriteBatch sb)
    {
        sb.draw(sideObjectTexture,position.x,position.y);
    }
}
