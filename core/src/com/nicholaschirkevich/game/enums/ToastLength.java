package com.nicholaschirkevich.game.enums;

/**
 * Created by Nikolas on 19.06.2016.
 */
public enum ToastLength {
    TOAST_LONG(2f),
    TOAST_SHOT(1f);

    private float length;

    ToastLength(float length) {
        this.length = length;
    }

    public float getLength() {
        return length;
    }
}
