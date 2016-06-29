package com.nicholaschirkevich.game.view_adapters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.nicholaschirkevich.game.admob.ActionResolver;
import com.nicholaschirkevich.game.entity.VkUser;
import com.nicholaschirkevich.game.menu.items.RecordsItem;

import java.util.ArrayList;

/**
 * Created by Nikolas on 12.03.2016.
 */
public class RecordsLeaderBoardAdapter {
    private Table table;
    private int selectedItmeIndex;
    private ArrayList<VkUser> leaderboardEntitis = new ArrayList<VkUser>();
    private ActionResolver actionResolver;
    private String myId;

    public int getMyIndex() {
        return myIndex;
    }

    private int myIndex;

    public RecordsLeaderBoardAdapter(Table table, ArrayList<VkUser> leaderboardEntitis, ActionResolver actionResolver) {
        this.table = table;
        this.leaderboardEntitis = leaderboardEntitis;
        this.actionResolver = actionResolver;
        myId = actionResolver.getMyId();


    }


    public void loadTableData() {


                int i = 0;
                for (VkUser leaderboardEntity : leaderboardEntitis) {
                    if (myId != null && myId.equals(leaderboardEntity.getId())) {
                        myIndex = i;
                        table.add(new RecordsItem(leaderboardEntity, i, actionResolver,true)).size(300, 60).row();
                    } else
                        table.add(new RecordsItem(leaderboardEntity, i, actionResolver)).size(300, 60).row();
                    i++;

                }
            }




}
