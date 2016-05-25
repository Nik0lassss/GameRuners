package com.nicholaschirkevich.game.admob;

/**
 * Created by Nikolas on 20.05.2016.
 */
public interface ActionResolver {
    void showOrLoadInterstital();
    boolean isAvailibleInternet();
    boolean isIntertitalLoad();
    void showVkLoginActivity();
    void sendPostOnVk();

}
