package com.nicholaschirkevich.game.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nicholaschirkevich.game.enums.ToastLength;
import com.nicholaschirkevich.game.menu.SystemToastMessageRectangle;

/**
 * Created by Nikolas on 19.06.2016.
 */
public class ToastSystemMessage {
    private String message;
    private Color color;
    private ToastLength length;
    private SystemToastMessageRectangle systemToastMessageRectangle;

    public ToastSystemMessage(String message, Color color, ToastLength length) {
        this.message = message;

        this.color = color;

        this.length = length;
        systemToastMessageRectangle = new SystemToastMessageRectangle();
    }

    public void draw(SpriteBatch sb)
    {
        systemToastMessageRectangle.draw(sb,1f);
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
}

