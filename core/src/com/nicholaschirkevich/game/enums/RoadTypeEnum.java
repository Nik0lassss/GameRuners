package com.nicholaschirkevich.game.enums;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.nicholaschirkevich.game.GameRuners;
import com.nicholaschirkevich.game.util.GameManager;

/**
 * Created by Nikolas on 17.07.2016.
 */
public class RoadTypeEnum {

    private boolean isCrossroad = false;
    private Texture roadTexture;
    private Rectangle bounds;
    private Vector2 posRoad;


    public RoadTypeEnum(boolean isCrossroad, Texture roadTexture) {
        this.isCrossroad = isCrossroad;
        this.roadTexture = roadTexture;
    }

    public void update(float dt)
    {

        if (0 > posRoad.y + 120 + roadTexture.getHeight() * 2 * dt) {
            posRoad.add(0, 1120);
        }

        posRoad.set(GameRuners.WIDTH / 4 - roadTexture.getWidth() / 2, posRoad.y - GameManager.getCurrentSpeed() * dt);
        bounds.set(posRoad.x,posRoad.y,roadTexture.getWidth(),roadTexture.getHeight());
    }

    public void draw (SpriteBatch sb)
    {
        sb.draw(roadTexture,posRoad.x,posRoad.y);
    }

    public boolean isCrossroad() {
        return isCrossroad;
    }

    public void setIsCrossroad(boolean isCrossroad) {
        this.isCrossroad = isCrossroad;
    }

    public Texture getRoadTexture() {
        return roadTexture;
    }

    public void setRoadTexture(Texture roadTexture) {
        this.roadTexture = roadTexture;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public Vector2 getPosRoad() {
        return posRoad;
    }

    public void setPosRoad(Vector2 posRoad) {
        this.posRoad = posRoad;
    }
}
