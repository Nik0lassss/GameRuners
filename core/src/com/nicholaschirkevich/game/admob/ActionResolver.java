package com.nicholaschirkevich.game.admob;

import com.nicholaschirkevich.game.entity.ImageByteEntity;
import com.nicholaschirkevich.game.entity.LeaderboardEntity;
import com.nicholaschirkevich.game.interfaces.OnSharePost;
import com.nicholaschirkevich.game.listeners.BuyProduct;
import com.nicholaschirkevich.game.listeners.OnGetLidearBoards;
import com.nicholaschirkevich.game.listeners.OnLoginListenerInterface;

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

    void updateIntertionalState();

    boolean getAdmobStatus();

    void setAdmobStatus(boolean statusAdMob);

    void showVkLoginActivity(OnLoginListenerInterface onLoginListenerInterface);

    void getVkStatusLogin();

    void vkLogout();

    void sendPostOnVk(ImageByteEntity imageByteEntity);

    boolean isVkLogin();

    void showInviteBox();

    String getMyId();

    void buyProduct(String id, BuyProduct buyProduct);

    void goneDefaultImage();

    void getLidearBoards(OnGetLidearBoards onGetLidearBoards);

    void getVkImageLidearBoards(OnGetLidearBoards onGetLidearBoards, ArrayList<LeaderboardEntity> leaderboardEntities);

    void getByteImage(OnGetLidearBoards onGetLidearBoards, String url, int index);

    void getHighscoresVkFriends(final OnGetLidearBoards onGetLidearBoards);

    void getHighScoreFacebookFriends(final OnGetLidearBoards onGetLidearBoards);

    void sendPostFb(ImageByteEntity imageByteEntity, OnSharePost onSharePost);


    boolean isFacebookLogin();

    void submitScore(int highScore);

    void signIn(OnLoginListenerInterface onLoginListenerInterface);

    void signOut();

    void showScore();

    boolean isSignedIn();

    void singInFb(OnLoginListenerInterface onLoginListenerInterface);

    void singOutFb(OnLoginListenerInterface onLoginListenerInterface);

    void showInviteFacebook();

    void onVkLogin();

}
