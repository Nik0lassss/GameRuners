package com.nicholaschirkevich.game.entity;

/**
 * Created by Nikolas on 11.03.2016.
 */
public class RightRocketPosition {
    Integer x;
    Integer y;

    public RightRocketPosition() {
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public void setX(String x) {
        this.x =Integer.valueOf(x);
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {

        this.y = y;
    }

    public void setY(String y) {

        this.y =Integer.valueOf(y);
    }
}
