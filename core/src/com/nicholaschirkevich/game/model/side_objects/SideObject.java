package com.nicholaschirkevich.game.model.side_objects;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.nicholaschirkevich.game.enums.SideObjectType;
import com.nicholaschirkevich.game.util.AssetsManager;
import com.nicholaschirkevich.game.util.Constants;
import com.nicholaschirkevich.game.util.GameManager;
import com.nicholaschirkevich.game.util.RandomUtil;

import java.util.ArrayList;

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
    private ArrayList<SideObject> appendSideObjectArrayList = new ArrayList<SideObject>();
    private int height, width;


    public Rectangle getShape() {
        return shape;
    }

    private Rectangle shape;


    private Vector3 position;


    private int distance;


    private boolean isLeft = false;

    public SideObject(SideObjectType sideObjectType) {
        this.sideObjectTexture = AssetsManager.getTextureRegion(sideObjectType.getKey()).getTexture();
        this.type = sideObjectType;
        if (RandomUtil.getRandomBoolean()) {
            position = sideObjectType.getPosRight().cpy();
            isLeft = false;
        } else {
            position = sideObjectType.getPosLeft().cpy();
            isLeft = true;

        }
        height = sideObjectTexture.getHeight();


    }

    public SideObject(SideObjectType sideObjectType, boolean isLeft) {
        this.sideObjectTexture = AssetsManager.getTextureRegion(sideObjectType.getKey()).getTexture();
        this.type = sideObjectType;
        height = sideObjectTexture.getHeight();
        width = sideObjectTexture.getWidth();
        position = isLeft != true ? sideObjectType.getPosRight().cpy() : sideObjectType.getPosLeft().cpy();
        position.x += RandomUtil.getNoRandomBoolean()==true?(RandomUtil.getRand(0,sideObjectType.getDeltaX())):(-RandomUtil.getRand(0,sideObjectType.getDeltaX()));
         shape = new Rectangle(position.x, position.y, width, height);
        //position = sideObjectType.getPosLeft().cpy();
    }

    public SideObject(SideObjectType sideObjectType, boolean isLeft, float posY) {
        this.sideObjectTexture = AssetsManager.getTextureRegion(sideObjectType.getKey()).getTexture();
        this.type = sideObjectType;
        height = sideObjectTexture.getHeight();
        width = sideObjectTexture.getWidth();
//        position = isLeft!=true?sideObjectType.getPosRight().cpy():sideObjectType.getPosLeft().cpy();
        position = sideObjectType.getPosLeft().cpy();
        position.x += RandomUtil.getRand(0, sideObjectType.getDeltaX());
        position.y = posY;


        for (SideObjectType sideObjectType1 : sideObjectType.getAppendSideTypeObject()) {
            Vector3 posLeft = getType().getPosLeft().cpy();
            Vector3 posRight = getType().getPosRight().cpy();
            sideObjectType1.setPosLeft(posLeft.add(sideObjectType1.getAppendX(), sideObjectType1.getAppendY(), 0));
            sideObjectType1.setPosRight(posRight.add(sideObjectType1.getAppendX(), sideObjectType1.getAppendY(), 0));
            SideObject sideObject = new SideObject(sideObjectType1, sideObjectType1.isLeft());
            if (sideObjectType1.getAppendY() > 0) {
                height = (int) sideObjectType1.getAppendY() + sideObject.height;
            }
            if (height < sideObject.getHeight()) height = sideObject.getHeight();

            if (sideObjectType1.getAppendX() > 0) {
                width = (int) sideObjectType1.getAppendX() + sideObject.width;
            }
            if (width < sideObject.getWidth()) width = sideObject.getWidth();
            Vector3 positionChildObject = position.cpy();
            positionChildObject.add(sideObjectType1.getAppendX(), sideObjectType1.getAppendY(), 0);
            sideObject.setPosition(positionChildObject);

             appendSideObjectArrayList.add(sideObject);

        }
        shape = new Rectangle(position.x, position.y, width, height);
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
        shape.setPosition(position.x, position.y);
        for (int i = this.appendSideObjectArrayList.size() - 1; i >= 0; --i) {
            ((SideObject) this.appendSideObjectArrayList.get(i)).update(camera, dt);
        }

    }

    public void draw(SpriteBatch sb) {
        sideObjectTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        sb.draw(sideObjectTexture, position.x, position.y);
        for (int i = appendSideObjectArrayList.size() - 1; i >= 0; i--) {
            appendSideObjectArrayList.get(i).draw(sb);
        }
    }

    public Vector3 getPosition() {
        return position;
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public boolean isLeft() {
        return isLeft;
    }

    public void setIsLeft(boolean isLeft) {
        this.isLeft = isLeft;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!SideObject.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final SideObject other = (SideObject) obj;
        if ((this.getType().getKey() == null) ? (other.getType().getKey() != null) : !this.getType().getKey().equals(other.getType().getKey())) {
            return false;
        }
        return true;
    }
}
