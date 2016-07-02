package com.nicholaschirkevich.game;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.Toast;

import com.badlogic.gdx.backends.android.AndroidFragmentApplication;
import com.nicholaschirkevich.game.entity.VkUser;
import com.nicholaschirkevich.game.fragment.FragmentAdmob;
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


public class AndroidLauncher extends FragmentActivity implements AndroidFragmentApplication.Callbacks  {

	FragmentAdmob gameFragment;
	private String[] vkScope = new String[]{VKScope.MESSAGES, VKScope.FRIENDS, VKScope.WALL,VKScope.PHOTOS};

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);
		gameFragment= new FragmentAdmob();
		FragmentTransaction tr = getSupportFragmentManager().beginTransaction();
		tr.replace(R.id.GameView,gameFragment,"FragmentAdmob");
		tr.commit();


	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {


		if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
			@Override
			public void onResult(VKAccessToken res) {
				Toast.makeText(getApplicationContext(), "Good", Toast.LENGTH_LONG).show();
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
//                listview = (ListView ) findViewById(R.id.listViewFriends);
//                VKRequest vkRequest = VKApi.friends().get(VKParameters.from(VKApiConst.FIELDS,"first_name","last_name"));
//                vkRequest.executeWithListener(new VKRequest.VKRequestListener() {
//                    @Override
//                    public void onComplete(VKResponse response) {
//                        super.onComplete(response);
//                        VKList vkList = (VKList) response.parsedModel;
//                        ArrayAdapter<String > arrayAdapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_expandable_list_item_1,vkList);
//                        listview.setAdapter(arrayAdapter);
//                    }
//
//                });
// Пользователь успешно авторизовался
			}

			@Override
			public void onError(VKError error) {
				Toast.makeText(getApplicationContext(), "Errore", Toast.LENGTH_LONG).show();
// Произошла ошибка авторизации (например, пользователь запретил авторизацию)
			}
		})) {
			super.onActivityResult(requestCode, resultCode, data);
		}
		FragmentManager fragmentManager = getSupportFragmentManager();
		Fragment fragment = fragmentManager.findFragmentByTag("FragmentAdmob");
		if (fragment != null)
		{
			((FragmentAdmob)fragment).onActivityResult(requestCode, resultCode, data);
		}
	}


	@Override
	public void exit() {

	}
}
