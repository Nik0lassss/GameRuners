package com.nicholaschirkevich.game.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.badlogic.gdx.backends.android.AndroidFragmentApplication;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.nicholaschirkevich.game.GameRuners;
import com.nicholaschirkevich.game.R;
import com.nicholaschirkevich.game.adapter.FriendDialogListAdapter;
import com.nicholaschirkevich.game.admob.ActionResolver;
import com.nicholaschirkevich.game.internet.InternetHelper;
import com.nicholaschirkevich.game.vkmodel.User;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiPhoto;
import com.vk.sdk.api.model.VKApiUserFull;
import com.vk.sdk.api.model.VKPhotoArray;
import com.vk.sdk.api.model.VKUsersArray;
import com.vk.sdk.api.photo.VKImageParameters;
import com.vk.sdk.api.photo.VKUploadImage;
import com.vk.sdk.dialogs.VKShareDialog;

import java.util.ArrayList;

import util.IabHelper;
import util.IabResult;
import util.Inventory;
import util.Purchase;

/**
 * Created by Nikolas on 20.05.2016.
 */
public class FragmentAdmob extends AndroidFragmentApplication implements ActionResolver {

    private InterstitialAd mInterstitialAd;
    private RewardedVideoAd mAd;
    private String appId = "ca-app-pub-3929550233974663/5014713038";
    private Button showButton, byButton;
    VKRequest currentRequest;
    private static final String TAG =
            "test_tag";
    private View view;

    private IabHelper.OnConsumeFinishedListener mConsumeFinishedListener;
    private IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener;

    IabHelper mHelper;
    GameRuners gameRuners;
    static final String ITEM_SKU = "android.test.purchased";
    static final String ITEM_SKU_SP = "com.example.sp000";
    //static final String ITEM_SKU = "com.example.sp";

    private String[] vkScope = new String[]{VKScope.WALL, VKScope.PHOTOS, VKScope.ADS, VKScope.NOTES, VKScope.NOHTTPS, VKScope.PAGES, VKScope.STATS, VKScope.STATUS};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        // VKSdk.login(getActivity(), vkScope);
        mInterstitialAd = new InterstitialAd(getContext());


        mInterstitialAd.setAdUnitId(appId);
        byButton = (Button) getActivity().findViewById(R.id.bttn_by);
        byButton.setEnabled(false);
         mPurchaseFinishedListener
                = new IabHelper.OnIabPurchaseFinishedListener() {
            public void onIabPurchaseFinished(IabResult result,
                                              Purchase purchase) {
                if (result.isFailure()) {

                    // Handle error
                    return;
                } else if (purchase.getSku().equals(ITEM_SKU)) {
                    consumeItem();
                    byButton.setEnabled(false);
                }

            }
        };

        byButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mHelper.launchPurchaseFlow(getActivity(), ITEM_SKU_SP, 10001,
//                        mPurchaseFinishedListener, getString(R.string.purchasetocken));
            }
        });
        showButton = (Button) getActivity().findViewById(R.id.bttn_show);
        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showButton.setEnabled(false);
                byButton.setEnabled(true);
                String base64EncodedPublicKey =
                        getString(R.string.base64EncodedKey);

                mHelper = new IabHelper(getContext(), base64EncodedPublicKey);

                mHelper.startSetup(new
                                           IabHelper.OnIabSetupFinishedListener() {
                                               public void onIabSetupFinished(IabResult result) {
                                                   if (!result.isSuccess()) {
                                                       Toast.makeText(getContext(), getString(R.string.inAppBuillingErrore), Toast.LENGTH_LONG).show();
                                                       Log.d(TAG, getString(R.string.inAppBuillingErrore) +
                                                               result);
                                                   } else {
                                                       Toast.makeText(getContext(), getString(R.string.inAppBuillingOk), Toast.LENGTH_LONG).show();
                                                       Log.d(TAG, getString(R.string.inAppBuillingOk));
                                                   }
                                               }
                                           });
                //showInterstitial();
            }
        });

        mConsumeFinishedListener
                =
                new IabHelper.OnConsumeFinishedListener() {
                    public void onConsumeFinished(Purchase purchase,
                                                  IabResult result) {

                        if (result.isSuccess()) {
                            showButton.setEnabled(true);
                        } else {
                            // handle error
                        }
                    }
                };

        IabHelper.QueryInventoryFinishedListener mReceivedInventoryListener
                = new IabHelper.QueryInventoryFinishedListener() {
            public void onQueryInventoryFinished(IabResult result,
                                                 Inventory inventory) {

                if (result.isFailure()) {
                    // Handle failure
                } else {
                    mHelper.consumeAsync(inventory.getPurchase(ITEM_SKU),
                            mConsumeFinishedListener);
                }
            }
        };


        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                startGame();
                gameRuners.onAdClose();
            }
        });
        startGame();
        gameRuners = new GameRuners(this);


        return initializeForView(gameRuners);


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                    Intent data)
    {
        if (!mHelper.handleActivityResult(requestCode,
                resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void consumeItem() {
        mHelper.queryInventoryAsync(new IabHelper.QueryInventoryFinishedListener() {

            @Override
            public void onQueryInventoryFinished(IabResult result, Inventory inv) {

            }
        });
    }


    private void showInterstitial() {


        try {
            runOnUiThread(new Runnable() {
                public void run() {
                    if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
                        mInterstitialAd.show();
                    } else {
                        Toast.makeText(getContext(), "Ad did not load", Toast.LENGTH_SHORT).show();
                        gameRuners.onAdClose();
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
            AdRequest adRequest = new AdRequest.Builder().addTestDevice("024E787E6EB1DF2F6E701EE93F986BA4").build();
            // AdRequest adRequest = new AdRequest.Builder().build();
            mInterstitialAd.loadAd(adRequest);
        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mHelper != null) mHelper.dispose();
        mHelper = null;
    }

    @Override
    public void showOrLoadInterstital() {
        showInterstitial();
    }

    @Override
    public boolean isAvailibleInternet() {
        return InternetHelper.hasConnection(getContext());
    }

    @Override
    public boolean isIntertitalLoad() {
        return !mInterstitialAd.isLoading();
    }

    @Override
    public void showVkLoginActivity() {
//        Intent intent = new Intent(getActivity(), LoginActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(intent);
        VKSdk.login(getActivity(), vkScope);
    }

    @Override
    public boolean isVkLogin() {
        return VKSdk.isLoggedIn();
    }

    @Override
    public void showInviteBox() {

        currentRequest = VKApi.friends().get(VKParameters.from(VKApiConst.FIELDS, "id,first_name,last_name"));
        final ArrayList<User> users = new ArrayList<>();

        currentRequest.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                Log.d("VkDemoApp", "onComplete " + response);

                VKUsersArray usersArray = (VKUsersArray) response.parsedModel;
                users.clear();

                for (VKApiUserFull userFull : usersArray) {

                    users.add(new User(userFull.toString(), userFull.id));
                }

                final Dialog dialog = new Dialog(getContext());

                View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_main, null);

                ListView lv = (ListView) view.findViewById(R.id.list_friends_dialog_main);


                // Change MyActivity.this and myListOfItems to your own values
                FriendDialogListAdapter clad = new FriendDialogListAdapter(getContext(), users);

                lv.setAdapter(clad);

                //lv.setOnItemClickListener(........);

                dialog.setContentView(view);

                dialog.show();
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


    }

    @Override
    public void buyProduct(String id) {
        mHelper.flagEndAsync();
        mHelper.launchPurchaseFlow(getActivity(), getString(R.string.packagePurchase) + id, 10001,
                mPurchaseFinishedListener, getString(R.string.purchasetocken));

    }


    public void getFriends() {
        VKRequest request = VKApi.users().get();
    }

    @Override
    public void getVkStatusLogin() {


        VKSdk.wakeUpSession(getActivity(), new VKCallback<VKSdk.LoginState>() {

            @Override
            public void onResult(VKSdk.LoginState loginState) {
                switch (loginState) {
                    case LoggedOut:

                        //showLogin();
                        break;
                    case LoggedIn:

                        //showLogout();
                        break;
                    case Pending:
                        break;
                    case Unknown:
                        break;
                }
            }

            @Override
            public void onError(VKError vkError) {

            }
        });
    }


    @Override
    public void vkLogout() {
        showLogout();
    }


    @Override
    public void sendPostOnVk() {

        VKSdk.wakeUpSession(getActivity(), new VKCallback<VKSdk.LoginState>() {
            @Override
            public void onResult(VKSdk.LoginState res) {

                switch (res) {
                    case LoggedOut:
                        showLogin();
                        break;
                    case LoggedIn:
                        //showLogout();
                        break;
                    case Pending:
                        break;
                    case Unknown:
                        break;
                }

            }

            @Override
            public void onError(VKError error) {

            }
        });

        Bitmap bm2 = BitmapFactory.decodeResource(getResources(), R.drawable.sr_logo);
        final Bitmap b = bm2;
        VKPhotoArray photos = new VKPhotoArray();
        photos.add(new VKApiPhoto(getString(R.string.photo_id)));
        new VKShareDialog()
                .setText(getString(R.string.VkDialogText))
                .setUploadedPhotos(photos)
                .setAttachmentImages(new VKUploadImage[]{
                        new VKUploadImage(b, VKImageParameters.pngImage())
                })
                .setAttachmentLink(getString(R.string.VkDialogLinkText), getString(R.string.VkDialogLink))
                .setShareDialogListener(new VKShareDialog.VKShareDialogListener() {
                    @Override
                    public void onVkShareComplete(int postId) {
                        //контент отправлен
                        Toast.makeText(getContext(), "контент отправлен", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onVkShareCancel() {
                        //Toast.makeText(getContext(), "отмена", Toast.LENGTH_LONG).show();
                        //отмена
                    }

                    @Override
                    public void onVkShareError(VKError error) {
                        // Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }).show(getFragmentManager(), "VK_SHARE_DIALOG");
    }

    private void showLogin() {
        VKSdk.login(getActivity(), vkScope);
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id., new LoginFragment())
//                .commitAllowingStateLoss();
    }

    private void showLogout() {
        VKSdk.logout();

//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id., new LoginFragment())
//                .commitAllowingStateLoss();
    }
}
