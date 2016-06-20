package com.nicholaschirkevich.game.entity;

import com.badlogic.gdx.graphics.Color;
import com.nicholaschirkevich.game.enums.ToastLength;

/**
 * Created by Nikolas on 19.06.2016.
 */
public class ToastMessage {
    private String message,messageSecondLine;
    private Color color, colorSecondLine;
    private ToastLength length;

    public ToastMessage(String message, String messageSecondLine, Color color, Color colorSecondLine, ToastLength length) {
        this.message = message;
        this.messageSecondLine = messageSecondLine;
        this.color = color;
        this.colorSecondLine = colorSecondLine;
        this.length = length;
    }


    public String getMessage() {
        return message;
    }

    public Color getColor() {
        return color;
    }

    public ToastLength getLength() {
        return length;
    }

    public String getMessageSecondLine() {
        return messageSecondLine;
    }

    public Color getColorSecondLine() {
        return colorSecondLine;
    }
}

