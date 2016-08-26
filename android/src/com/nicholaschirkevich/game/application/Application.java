package com.nicholaschirkevich.game.application;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.nicholaschirkevich.game.R;
import com.nicholaschirkevich.game.api.ServerApi;
import com.nicholaschirkevich.game.helper.LruBitmapCache;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKAccessTokenTracker;
import com.vk.sdk.VKSdk;

/**
 * Created by Nikolas on 24.05.2016.
 */
public class Application extends android.app.Application {

    public static final String TAG = Application.class.getSimpleName();
    private static Tracker tracker;
    private static GoogleAnalytics analytics;

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    private static Application appInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        VKSdk.initialize(this);
        ServerApi.setUpReciever(this);
        appInstance = this;
        setUpTracker();


        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
    }

    public static Tracker tracker() {
        return tracker;
    }

    public void setUpTracker()
    {
        analytics = GoogleAnalytics.getInstance(this);

        // TODO: Replace the tracker-id with your app one from https://www.google.com/analytics/web/
        tracker = analytics.newTracker("UA-82375384-1");

        // Provide unhandled exceptions reports. Do that first after creating the tracker
        tracker.enableExceptionReporting(true);

        // Enable Remarketing, Demographics & Interests reports
        // https://developers.google.com/analytics/devguides/collection/android/display-features
        tracker.enableAdvertisingIdCollection(true);

        // Enable automatic activity tracking for your app
        tracker.enableAutoActivityTracking(true);

        tracker.send(new HitBuilders.ScreenViewBuilder().setCustomDimension(1, null).build());
    }


    public static synchronized Application getInstance() {
        return appInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(this.mRequestQueue,
                    new LruBitmapCache());
        }

        return this.mImageLoader;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}
