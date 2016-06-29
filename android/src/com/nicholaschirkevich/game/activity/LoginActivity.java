package com.nicholaschirkevich.game.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.nicholaschirkevich.game.R;
import com.nicholaschirkevich.game.entity.VkUser;
import com.nicholaschirkevich.game.util.GameManager;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiUserFull;
import com.vk.sdk.api.model.VKList;

import java.util.ArrayList;

public class LoginActivity extends Activity {

    private String[] vkScope = new String[]{  VKScope.WALL,VKScope.PHOTOS,VKScope.ADS,VKScope.NOTES,VKScope.NOHTTPS,VKScope.PAGES,VKScope.STATS,VKScope.STATUS};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        VKSdk.login(this, vkScope);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
               VKRequest currentRequest = VKApi.users().get(VKParameters.from(VKApiConst.USER_IDS, res.userId, VKApiConst.FIELDS, "id,first_name,last_name,photo_100"));

                currentRequest.executeWithListener(new VKRequest.VKRequestListener() {
                    @Override
                    public void onComplete(VKResponse response) {
                        super.onComplete(response);


                        VKList<VKApiUserFull> usersArray = (VKList<VKApiUserFull>) response.parsedModel;


                        for (VKApiUserFull userFull : usersArray) {

                            GameManager.setVkUser(new VkUser(String.valueOf(userFull.id), userFull.photo_100, userFull.first_name, userFull.last_name));
                        }


                    }

                    @Override
                    public void attemptFailed(VKRequest request, int attemptNumber, int totalAttempts) {
                        super.attemptFailed(request, attemptNumber, totalAttempts);
                        Log.d("VkDemoApp", "attemptFailed " + request + " " + attemptNumber + " " + totalAttempts);
                    }

                    @Override
                    public void onError(VKError error) {
                        super.onError(error);
                        Log.d("VkDemoApp", "onError: " + error);
                    }

                    @Override
                    public void onProgress(VKRequest.VKProgressType progressType, long bytesLoaded, long bytesTotal) {
                        super.onProgress(progressType, bytesLoaded, bytesTotal);
                        Log.d("VkDemoApp", "onProgress " + progressType + " " + bytesLoaded + " " + bytesTotal);
                    }
                });
// Пользователь успешно авторизовался
            }
            @Override
            public void onError(VKError error) {
// Произошла ошибка авторизации (например, пользователь запретил авторизацию)
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
