package com.nicholaschirkevich.game.listeners;

import com.nicholaschirkevich.game.entity.LeaderboardEntity;

import java.util.ArrayList;

/**
 * Created by Nikolas on 25.06.2016.
 */
public interface OnGetHightscoreList {
    void onGetHightscoreList(ArrayList<LeaderboardEntity> arrayList);
}
