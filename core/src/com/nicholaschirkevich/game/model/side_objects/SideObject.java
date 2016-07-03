package com.nicholaschirkevich.game.model.side_objects;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.nicholaschirkevich.game.enums.SideObjectType;
import com.nicholaschirkevich.game.util.AssetsManager;
import com.nicholaschirkevich.game.util.Constants;
import com.nicholaschirkevich.game.util.GameManager;
import com.nicholaschirkevich.game.util.RandomUtil;

/**
 * Created by Nikolas on 03.07.2016.
 */
public class SideObject {
    public SideObjectType getType() {
        return type;
    }

    public void setType(SideObjectType type) {
        this.type = type;
    }

    private SideObjectType type;
    private String id;
    private Texture sideObjectTexture;
    private Vector3 position;

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    private int distance;

    public boolean isLeft() {
        return isLeft;
    }

    public void setIsLeft(boolean isLeft) {
        this.isLeft = isLeft;
    }

    private boolean isLeft = false;

    public SideObject(SideObjectType sideObjectType) {
        this.sideObjectTexture = AssetsManager.getTextureRegion(sideObjectType.getKey()).getTexture();
        this.type = sideObjectType;
        position = RandomUtil.getRandomBoolean()==true?sideObjectType.getPosRight().cpy():sideObjectType.getPosLeft().cpy();
    }
    public SideObject(SideObjectType sideObjectType, boolean isLeft) {
        this.sideObjectTexture = AssetsManager.getTextureRegion(sideObjectType.getKey()).getTexture();
        this.type = sideObjectType;
//        position = isLeft!=true?sideObjectType.getPosRight().cpy():sideObjectType.getPosLeft().cpy();
        position = sideObjectType.getPosLeft().cpy();
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

    public void update(Camera camera, float dt) {
        position.add(0, (-GameManager.getCurrentSpeed()) * dt, 0);

    }

    public void draw(SpriteBatch sb) {
        sideObjectTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        sb.draw(sideObjectTexture, position.x, position.y);
    }

    public Vector3 getPosition() {
        return position;
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }
}
