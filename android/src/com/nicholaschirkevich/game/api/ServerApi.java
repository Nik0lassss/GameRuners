package com.nicholaschirkevich.game.api;

import android.content.Context;
import android.graphics.Bitmap;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.facebook.AccessToken;
import com.nicholaschirkevich.game.R;
import com.nicholaschirkevich.game.entity.LeaderboardEntity;
import com.nicholaschirkevich.game.entity.VkUser;
import com.nicholaschirkevich.game.listeners.OnGetHightscoreList;
import com.nicholaschirkevich.game.listeners.OnGetLidearBoards;
import com.nicholaschirkevich.game.mappers.Mapper;
import com.nicholaschirkevich.game.receiver.*;
import com.nicholaschirkevich.game.util.GameManager;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.api.VKApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Nikolas on 23.06.2016.
 */
public class ServerApi {
    private static Receiver receiver;
    private static Context serverApiContext;
    private static OnGetLidearBoards onGetLeaderBoardsListenerServerApi;
    private static int compress_procent = 100;

    public static void setUpReciever(Context context) {
        serverApiContext = context;
        receiver = new Receiver(context);
    }







    public static void getHighscoresFacebookFriends(final OnGetLidearBoards onGetLidearBoards, final ArrayList<VkUser> arrayList) {

        StringBuilder stringQuery = new StringBuilder();
        stringQuery.append("?");
        for (int i = 0; i < arrayList.size(); i++) {
            stringQuery.append(serverApiContext.getString(R.string.get_hisghscore_append_string) + arrayList.get(i).getId());
            if (i < arrayList.size() - 1) {
                stringQuery.append("&");
            }
        }
        final AccessToken token;
        token = AccessToken.getCurrentAccessToken();

        if (token != null) {
            stringQuery.append(serverApiContext.getString(R.string.get_highscore_facebook_friends_append)+token.getUserId());
            //Means user is not logged in
        }else
        {
            onGetLidearBoards.onGetVkLeaderboardErrore("");
        }

        receiver.sendGetRequest(serverApiContext.getString(R.string.server_url) + serverApiContext.getString(R.string.server_url_get_facebook_highscores) + stringQuery, new Response.Listener() {
            @Override
            public void onResponse(Object object) {
                try {

                    ArrayList<VkUser> vkUsers = new ArrayList<VkUser>();
                    JSONArray jsonObjects = new JSONArray((String) object);
                    for (int i = 0; i < jsonObjects.length(); i++) {
                        LeaderboardEntity highscoreEntity = Mapper.jsonToLeaderBoardEntity((JSONObject) jsonObjects.get(i));


                        for (VkUser vkUser : arrayList) {
                            if (highscoreEntity.getFb_id().equals(vkUser.getId())) {
                                vkUser.setHighscore(highscoreEntity.getHighscore());
                                vkUsers.add(vkUser);
                            }
                        }
                        if (token != null && GameManager.getVkUser() != null && highscoreEntity.getFb_id().equals(token.getUserId())) {
//
                        }
                    }

                    onGetLidearBoards.onGetHighscoresFriends(vkUsers);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                onGetLidearBoards.onGetVkLeaderboardErrore(volleyError.getMessage());
            }
        });
    }

    public static void getHighscoresFriends(final OnGetLidearBoards onGetLidearBoards, final ArrayList<VkUser> arrayList) {

        StringBuilder stringQuery = new StringBuilder();
        stringQuery.append("?");
        for (int i = 0; i < arrayList.size(); i++) {
            stringQuery.append(serverApiContext.getString(R.string.get_highscore_friends_val) + arrayList.get(i).getId());
            if (i < arrayList.size() - 1) {
                stringQuery.append("&");
            }
        }
        if (VKAccessToken.currentToken() != null)
            stringQuery.append(serverApiContext.getString(R.string.get_vk_friends_val)+VKAccessToken.currentToken().userId);
        else {
            onGetLidearBoards.onGetVkLeaderboardErrore("");
        }
        receiver.sendGetRequest(serverApiContext.getString(R.string.server_url) + serverApiContext.getString(R.string.server_url_get_highscores) + stringQuery, new Response.Listener() {
            @Override
            public void onResponse(Object object) {
                try {

                    ArrayList<VkUser> vkUsers = new ArrayList<VkUser>();
                    JSONArray jsonObjects = new JSONArray((String) object);
                    for (int i = 0; i < jsonObjects.length(); i++) {
                        LeaderboardEntity highscoreEntity = Mapper.jsonToLeaderBoardEntity((JSONObject) jsonObjects.get(i));


                        for (VkUser vkUser : arrayList) {
                            if (highscoreEntity.getVk_id().equals(vkUser.getId())) {
                                vkUser.setHighscore(highscoreEntity.getHighscore());
                                vkUsers.add(vkUser);
                            }
                        }
                        if (VKAccessToken.currentToken() != null && GameManager.getVkUser() != null && highscoreEntity.getVk_id().equals(GameManager.getVkUser().getId())) {
                            VkUser vkCurrentUser = GameManager.getVkUser();
                            vkCurrentUser.setHighscore(highscoreEntity.getHighscore());
                            vkUsers.add(GameManager.getVkUser());
                        }
                    }

                    onGetLidearBoards.onGetHighscoresFriends(vkUsers);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                onGetLidearBoards.onGetVkLeaderboardErrore(volleyError.getMessage());
            }
        });
    }

    public static void getLeaderBoards(final OnGetLidearBoards onGetLeaderBoardsListener) {
        onGetLeaderBoardsListenerServerApi = onGetLeaderBoardsListener;
        receiver.sendGetRequest(serverApiContext.getString(R.string.server_url) + serverApiContext.getString(R.string.server_url_get_leaderboards), new Response.Listener() {
            @Override
            public void onResponse(Object object) {
                try {
                    ArrayList<LeaderboardEntity> leaderboardEntities = new ArrayList<>();
                    JSONArray jsonObjects = new JSONArray((String) object);
                    for (int i = 0; i < jsonObjects.length(); i++) {
                        LeaderboardEntity leaderboardEntity = Mapper.jsonToLeaderBoardEntity((JSONObject) jsonObjects.get(i));

                        leaderboardEntities.add(leaderboardEntity);
                    }
                    onGetLeaderBoardsListenerServerApi.onGetLidearboardsData(leaderboardEntities);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                onGetLeaderBoardsListener.onGetLeaderboardErrore(volleyError.getMessage());
            }
        });
    }

    public static void getImages(final OnGetLidearBoards onGetLeaderBoardsListener, String url) {

        receiver.getPicture(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, compress_procent, stream);
                byte[] byteArray = stream.toByteArray();
                onGetLeaderBoardsListener.onGetImage(byteArray);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
    }


}

