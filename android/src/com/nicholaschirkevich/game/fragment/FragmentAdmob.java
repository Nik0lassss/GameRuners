package com.nicholaschirkevich.game.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.badlogic.gdx.backends.android.AndroidFragmentApplication;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.nicholaschirkevich.game.GameRuners;
import com.nicholaschirkevich.game.R;
import com.nicholaschirkevich.game.admob.ActionResolver;

/**
 * Created by Nikolas on 20.05.2016.
 */
public class FragmentAdmob extends AndroidFragmentApplication implements ActionResolver {

    private InterstitialAd mInterstitialAd;
    private RewardedVideoAd mAd;
    private String appId = "ca-app-pub-3929550233974663/5014713038";
    private Button showButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mInterstitialAd = new InterstitialAd(getContext());


        mInterstitialAd.setAdUnitId(appId);
        showButton = (Button) getActivity().findViewById(R.id.bttn_show);
        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInterstitial();
            }
        });

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                startGame();
            }
        });
        return initializeForView(new GameRuners(this));


    }

    private void showInterstitial() {

        // Show the ad if it's ready. Otherwise toast and restart the game.

        try {
            runOnUiThread(new Runnable() {
                public void run() {
                    if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
                        mInterstitialAd.show();
                    } else {
                        Toast.makeText(getContext(), "Ad did not load", Toast.LENGTH_SHORT).show();
                        startGame();
                    }
                }
            });
        } catch (Exception e) {
        }

    }

    private void startGame() {
        // Request a new ad if one isn't already loaded, hide the button, and kick off the timer.
        if (!mInterstitialAd.isLoading() && !mInterstitialAd.isLoaded()) {
            AdRequest adRequest = new AdRequest.Builder().build();
            mInterstitialAd.loadAd(adRequest);
        }


    }

    @Override
    public void showOrLoadInterstital() {
        showInterstitial();
    }
}
