package com.nicholaschirkevich.game.userdata;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Nikolas on 16.03.2016.
 */
public class MyCarDataType extends UserData {

    protected boolean isBlow = false;
    protected boolean isHaveLadle = false;
    protected boolean isGodMode = false;
    protected boolean isFly = false;
    protected boolean isContact = false;

    public boolean isJump() {
        return isJump;
    }

    public void setIsJump(boolean isJump) {
        this.isJump = isJump;
    }

    protected boolean isJump = false;
    protected Integer x = 0;
    protected Integer y = 0;
    Rectangle bounds;

    public MyCarDataType() {
        userDataType = UserDataType.MY_CAR;
    }

    public boolean isBlow() {
        return isBlow;
    }

    public void setIsBlow(boolean isBlow) {
        this.isBlow = isBlow;
    }

    public boolean isHaveLadle() {
        return isHaveLadle;
    }

    public void setIsHaveLadle(boolean isHaveLadle) {
        this.isHaveLadle = isHaveLadle;
    }

    public boolean isGodMode() {
        return isGodMode;
    }

    public void setIsGodMode(boolean isGodMode) {
        this.isGodMode = isGodMode;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public boolean isFly() {
        return isFly;
    }

    public void setIsFly(boolean isFly) {
        this.isFly = isFly;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public boolean isContact() {
        return isContact;
    }

    public void setIsContact(boolean isContact) {
        this.isContact = isContact;
    }
}
