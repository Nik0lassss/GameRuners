package com.nicholaschirkevich.game.util;

import com.badlogic.gdx.graphics.Color;
import com.nicholaschirkevich.game.entity.ToastSystemMessage;
import com.nicholaschirkevich.game.enums.ToastLength;

/**
 * Created by Nikolas on 21.07.2016.
 */
public class SystemToastHelper {
    ToastSystemMessage toastSystemMessage;

    public SystemToastHelper() {
        this.toastSystemMessage =new ToastSystemMessage("Test", Color.RED, ToastLength.TOAST_LONG);
    }
}
