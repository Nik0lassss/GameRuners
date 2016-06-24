package com.nicholaschirkevich.game.admob;

import com.nicholaschirkevich.game.entity.LeaderboardEntity;
import com.nicholaschirkevich.game.listeners.OnGetLidearBoards;

import java.util.ArrayList;

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
    void getLidearBoards(OnGetLidearBoards onGetLidearBoards);
    void getVkImageLidearBoards(OnGetLidearBoards onGetLidearBoards,ArrayList<LeaderboardEntity> leaderboardEntities);
    void getByteImage(OnGetLidearBoards onGetLidearBoards,String url, int index);

}
