package com.nicholaschirkevich.game.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.nicholaschirkevich.game.R;
import com.nicholaschirkevich.game.adapter.FriendDialogListAdapter;
import com.nicholaschirkevich.game.vkmodel.User;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiUserFull;
import com.vk.sdk.api.model.VKUsersArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class FriendsInviteActivity extends Activity {
    // Log tag

    // Movies json url

    private ProgressDialog pDialog;

    private ListView listView;
    private FriendDialogListAdapter adapter;
    private VKRequest currentRequest;
    private Activity thisActivity;
    private Button button;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_invite);
        button = (Button) findViewById(R.id.invite_frined_activity_back_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        currentRequest = VKApi.friends().get(VKParameters.from(VKApiConst.FIELDS, "id,first_name,last_name,photo_100"));
        final ArrayList<User> users = new ArrayList<>();

        thisActivity = this;
        currentRequest.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                Log.d("VkDemoApp", getString(R.string.vk_invite_friends_message));

                VKUsersArray usersArray = (VKUsersArray) response.parsedModel;
                users.clear();

                for (VKApiUserFull userFull : usersArray) {

                    users.add(new User(userFull.toString(), userFull.id, userFull.photo_100));

                }

//				final Dialog dialog = new Dialog(getContext());
//
//				View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_main, null);

//				ListView lv = (ListView) findViewById(R.id.list_friends_dialog_main);
//
//
//				// Change MyActivity.this and myListOfItems to your own values
//				FriendDialogListAdapter clad = new FriendDialogListAdapter(getApplicationContext(), users);
//
//				lv.setAdapter(clad);


                listView = (ListView) findViewById(R.id.list_friends_dialog_main);
                adapter = new FriendDialogListAdapter(thisActivity, users);
                listView.setAdapter(adapter);

//				pDialog = new ProgressDialog(getApplicationContext());
//				// Showing progress dialog before making http request
//				pDialog.setMessage("Loading...");
//				pDialog.show();

                // changing action bar color

                //lv.setOnItemClickListener(........);


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

//		listView = (ListView) findViewById(R.id.list_friends_dialog_main);
//		adapter = new FriendDialogListAdapter(this, movieList);
//		listView.setAdapter(adapter);
//
//		pDialog = new ProgressDialog(this);
//		// Showing progress dialog before making http request
//		pDialog.setMessage("Loading...");
//		pDialog.show();
//
//		// changing action bar color
//		getActionBar().setBackgroundDrawable(
//				new ColorDrawable(Color.parseColor("#1b1b1b")));

//		getActionBar().setBackgroundDrawable(
//				new ColorDrawable(Color.parseColor("#1b1b1b")));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }


}
