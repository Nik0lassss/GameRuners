package com.nicholaschirkevich.game.admob;

import com.nicholaschirkevich.game.entity.LeaderboardEntity;
import com.nicholaschirkevich.game.listeners.BuyProduct;
import com.nicholaschirkevich.game.listeners.OnGetLidearBoards;

import java.util.ArrayList;

/**
 * Created by Nikolas on 20.05.2016.
 */
public interface ActionResolver {
    void showOrLoadInterstital(boolean isAfterGetBonus);
    void showInterstitaGetBonus();
    boolean isAvailibleInternet();
    boolean isSaveMeIntertitalLoad();
    boolean isGetBonusIntertitalLoad();
    boolean isIntertatlLoaded();
    boolean isGetBonusIntertatlLoaded();

    boolean getAdmobStatus();
    void setAdmobStatus(boolean statusAdMob);
    void showVkLoginActivity();
    void getVkStatusLogin();
    void vkLogout();
    void sendPostOnVk();
    boolean isVkLogin();
    void showInviteBox();
    String getMyId();
    void buyProduct(String id,BuyProduct buyProduct);
    void goneDefaultImage();
    void getLidearBoards(OnGetLidearBoards onGetLidearBoards);
    void getVkImageLidearBoards(OnGetLidearBoards onGetLidearBoards,ArrayList<LeaderboardEntity> leaderboardEntities);
    void getByteImage(OnGetLidearBoards onGetLidearBoards,String url, int index);
    void getHighscoresVkFriends(final OnGetLidearBoards onGetLidearBoards);


    void submitScore(int highScore);
    void signIn();
    void signOut();
    void showScore();
    boolean isSignedIn();


}
