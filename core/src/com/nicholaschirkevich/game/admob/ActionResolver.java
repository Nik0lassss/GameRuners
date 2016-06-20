package com.nicholaschirkevich.game.admob;

/**
 * Created by Nikolas on 20.05.2016.
 */
public interface ActionResolver {
    void showOrLoadInterstital(boolean isAfterGetBonus);
    boolean isAvailibleInternet();
    boolean isIntertitalLoad();
    boolean isIntertatlLoaded();
    void showVkLoginActivity();
    void getVkStatusLogin();
    void vkLogout();
    void sendPostOnVk();
    boolean isVkLogin();
    void showInviteBox();
    void buyProduct(String id);
    void goneDefaultImage();

}
