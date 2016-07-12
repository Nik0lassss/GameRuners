package com.nicholaschirkevich.game.userdata;

import com.badlogic.gdx.math.Rectangle;
import com.nicholaschirkevich.game.enums.CollisionPasserCarType;

/**
 * Created by Nikolas on 16.03.2016.
 */
public class PasserCarDataType extends UserData {

    protected boolean isBlow = false;
    protected boolean isLadleCollision = false;
    protected boolean isGodMode = false;
    protected boolean isCollisionThrons = false;
    protected boolean isAfterHoleCollision = false;
    protected boolean isCollisionWhithPasser = false;
    protected boolean isContact = false;
    protected boolean isDangerEvolution = false;
    protected boolean flyCarContact = false;

    public boolean isSideObjectContact() {
        return isSideObjectContact;
    }

    public void setIsSideObjectContact(boolean isSideObjectContact) {
        this.isSideObjectContact = isSideObjectContact;
    }

    protected boolean isSideObjectContact = false;
    protected Float x = 0f;
    protected Float y = 0f;
    protected Rectangle bounds;
    protected Rectangle myCarBounds;

    public boolean isBefore() {
        return before;
    }

    public void setBefore(boolean before) {
        this.before = before;
    }

    protected boolean before = true;
    protected CollisionPasserCarType collisionPasserCarType = CollisionPasserCarType.NONE;

    public boolean isOvertaking() {
        return overtaking;
    }

    public void setOvertaking(boolean overtaking) {
        this.overtaking = overtaking;
    }

    protected boolean overtaking = false;

    public PasserCarDataType() {
        isBlow = false;
        isLadleCollision = false;
        userDataType = UserDataType.PASSER_CAR;
    }

    public boolean isBlow() {
        return isBlow;
    }

    public void setIsBlow(boolean isBlow) {
        this.isBlow = isBlow;
    }

    public boolean isLadleCollision() {
        return isLadleCollision;
    }

    public void setIsLadleCollision(boolean isLadleCollision) {
        this.isLadleCollision = isLadleCollision;
    }

    public boolean isGodMode() {
        return isGodMode;
    }

    public void setIsGodMode(boolean isGodMode) {
        this.isGodMode = isGodMode;
    }

    public boolean isCollisionThrons() {
        return isCollisionThrons;
    }

    public void setIsCollisionThrons(boolean isCollisionThrons) {
        this.isCollisionThrons = isCollisionThrons;
    }

    public Float getX() {
        return x;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public CollisionPasserCarType getCollisionPasserCarType() {
        return collisionPasserCarType;
    }

    public void setCollisionPasserCarType(CollisionPasserCarType collisionPasserCarType) {
        this.collisionPasserCarType = collisionPasserCarType;
    }

    public boolean isAfterHoleCollision() {
        return isAfterHoleCollision;
    }

    public void setIsAfterHoleCollision(boolean isAfterHoleCollision) {
        this.isAfterHoleCollision = isAfterHoleCollision;
    }

    public boolean isCollisionWhithPasser() {
        return isCollisionWhithPasser;
    }

    public void setIsCollisionWhithPasser(boolean isCollisionWhithPasser) {
        this.isCollisionWhithPasser = isCollisionWhithPasser;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public Rectangle getMyCarBounds() {
        return myCarBounds;
    }

    public void setMyCarBounds(Rectangle myCarBounds) {
        this.myCarBounds = myCarBounds;
    }

    public boolean isContact() {
        return isContact;
    }

    public void setIsContact(boolean isContact) {
        this.isContact = isContact;
    }

    public boolean isDangerEvolution() {
        return isDangerEvolution;
    }

    public void setIsDangerEvolution(boolean isDangerEvolution) {
        this.isDangerEvolution = isDangerEvolution;
    }

    public boolean isFlyCarContact() {
        return flyCarContact;
    }

    public void setFlyCarContact(boolean flyCarContact) {
        this.flyCarContact = flyCarContact;
    }
}
