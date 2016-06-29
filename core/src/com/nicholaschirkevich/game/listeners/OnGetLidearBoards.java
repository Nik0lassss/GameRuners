package com.nicholaschirkevich.game.listeners;

import com.nicholaschirkevich.game.entity.LeaderboardEntity;
import com.nicholaschirkevich.game.entity.VkUser;

import java.util.ArrayList;

/**
 * Created by Nikolas on 23.06.2016.
 */
public interface OnGetLidearBoards {
    void onGetLidearboardsData(ArrayList<LeaderboardEntity> leaderboardEntities);
    void onGetVkImageLidearboardsData(ArrayList<VkUser> leaderboardEntities);
    void onGetImage(byte[] imageByte);
    void onGetHighscoresFriends(ArrayList<VkUser> arrayList);
}
