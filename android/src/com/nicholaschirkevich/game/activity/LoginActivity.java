package com.nicholaschirkevich.game.activity;

import android.app.Activity;
import android.os.Bundle;

import com.nicholaschirkevich.game.R;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;

public class LoginActivity extends Activity {

    private String[] vkScope = new String[]{  VKScope.WALL,VKScope.PHOTOS,VKScope.ADS,VKScope.NOTES,VKScope.NOHTTPS,VKScope.PAGES,VKScope.STATS,VKScope.STATUS};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        VKSdk.login(this, vkScope);
    }
}
