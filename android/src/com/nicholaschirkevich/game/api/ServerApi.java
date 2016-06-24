package com.nicholaschirkevich.game.api;

import android.content.Context;
import android.graphics.Bitmap;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.nicholaschirkevich.game.R;
import com.nicholaschirkevich.game.entity.LeaderboardEntity;
import com.nicholaschirkevich.game.listeners.OnGetLidearBoards;
import com.nicholaschirkevich.game.mappers.Mapper;
import com.nicholaschirkevich.game.receiver.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by Nikolas on 23.06.2016.
 */
public class ServerApi {
    private static Receiver receiver;
    private static Context serverApiContext;
    private static OnGetLidearBoards onGetLeaderBoardsListenerServerApi;
    private static HashMap<Integer, OnGetLidearBoards> onGetLidearBoardsHashMap = new HashMap<>();
    private static HashMap<Integer, String> urlHashMap = new HashMap<>();
    private static int currentLoadImageIndex;
    private static Thread thread = new Thread();

    public static void setUpReciever(Context context) {
        serverApiContext = context;
        receiver = new Receiver(context);
    }

    private static Response.Listener getLeaderBoardsListener = new Response.Listener() {
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
    };


    private static Response.Listener<Bitmap> getBitmapListener = new Response.Listener<Bitmap>() {

        @Override
        public void onResponse(Bitmap bitmap) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            onGetLidearBoardsHashMap.get(currentLoadImageIndex).onGetImage(byteArray);
            onGetLidearBoardsHashMap.remove(currentLoadImageIndex);
            currentLoadImageIndex++;
            if (!onGetLidearBoardsHashMap.isEmpty()) {
                if (onGetLidearBoardsHashMap.size() < currentLoadImageIndex) {
                    receiver.getPicture(urlHashMap.get(currentLoadImageIndex), getBitmapListener, getBitmapErroreListener);
                    urlHashMap.remove(currentLoadImageIndex);
                }
            } else {
                thread.interrupt();
            }
        }
    };

    private static Response.ErrorListener getBitmapErroreListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {

        }
    };
    private static Response.ErrorListener getLeaderBoardsErroreListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {

        }
    };


    public static void getLeaderBoards(OnGetLidearBoards onGetLeaderBoardsListener) {
        onGetLeaderBoardsListenerServerApi = onGetLeaderBoardsListener;
        receiver.sendGetRequest(serverApiContext.getString(R.string.server_url) + serverApiContext.getString(R.string.server_url_get_leaderboards), getLeaderBoardsListener, getLeaderBoardsErroreListener);
    }

    public static void getImages(final OnGetLidearBoards onGetLeaderBoardsListener, String url)
    {

        receiver.getPicture(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                onGetLeaderBoardsListener.onGetImage(byteArray);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
    }

    public static void getImages(OnGetLidearBoards onGetLeaderBoardsListener, String url, int index) {
        //onGetLeaderBoardsListenerServerApi = onGetLeaderBoardsListener;
        onGetLidearBoardsHashMap.put(index, onGetLeaderBoardsListener);
        urlHashMap.put(index, url);
        //receiver.getPicture(url,getBitmapListener,getBitmapErroreListener);
        if (thread == null) startLoadImage(url, index);
        else if (!thread.isAlive()) {
            startLoadImage(url, index);
            currentLoadImageIndex = index;
        }
    }

    public static void startLoadImage(final String url, int index) {
        thread = new Thread() {
            @Override
            public void run() {
                while (!onGetLidearBoardsHashMap.isEmpty()) {
                    receiver.getPicture(url, getBitmapListener, getBitmapErroreListener);
                }
            }
        };

        thread.start();
        receiver.getPicture(url, getBitmapListener, getBitmapErroreListener);
    }


}

