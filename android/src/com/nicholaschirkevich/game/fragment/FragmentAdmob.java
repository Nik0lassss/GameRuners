package com.nicholaschirkevich.game.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidFragmentApplication;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.games.Games;

import com.google.example.games.basegameutils.GameHelper;
import com.nicholaschirkevich.game.GameRuners;
import com.nicholaschirkevich.game.R;
import com.nicholaschirkevich.game.activity.FriendsInviteActivity;
import com.nicholaschirkevich.game.admob.ActionResolver;
import com.nicholaschirkevich.game.api.ServerApi;
import com.nicholaschirkevich.game.entity.LeaderboardEntity;
import com.nicholaschirkevich.game.entity.VkUser;
import com.nicholaschirkevich.game.internet.InternetHelper;
import com.nicholaschirkevich.game.listeners.BuyProduct;
import com.nicholaschirkevich.game.listeners.OnGetHightscoreList;
import com.nicholaschirkevich.game.listeners.OnGetLidearBoards;
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
import com.vk.sdk.api.model.VKApiPhoto;
import com.vk.sdk.api.model.VKApiUserFull;
import com.vk.sdk.api.model.VKList;
import com.vk.sdk.api.model.VKPhotoArray;
import com.vk.sdk.api.photo.VKImageParameters;
import com.vk.sdk.api.photo.VKUploadImage;
import com.vk.sdk.dialogs.VKShareDialog;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.logging.Logger;

import util.IabHelper;
import util.IabResult;
import util.Inventory;
import util.Purchase;

/**
 * Created by Nikolas on 20.05.2016.
 */
public class FragmentAdmob extends AndroidFragmentApplication implements ActionResolver, OnGetHightscoreList {

    private SharedPreferences sPref;
    private InterstitialAd mInterstitialAdSaveMe, interstitialGetBonus;
    private ImageView defaultImage;
    private RewardedVideoAd mAd;
    private String appId = "ca-app-pub-3929550233974663/5014713038";
    private String getBonusAdmobId = "ca-app-pub-3929550233974663/1475819439";
    private Button showButton, byButton;
    private GameHelper gameHelper;
    private boolean isAdmobOn = true;
    private final static int requestCode = 1;
    VKRequest currentRequest;
    private static final String TAG =
            "test_tag";
    private View view;

    private IabHelper.OnConsumeFinishedListener mConsumeFinishedListener;
    private IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener;

    private BuyProduct buyProduct;
    IabHelper mHelper;
    GameRuners gameRuners;
    static final String ITEM_SKU = "android.test.purchased";
    static final String ITEM_SKU_SP = "com.example.sp000";

    //static final String ITEM_SKU = "com.example.sp";

    private String[] vkScope = new String[]{VKScope.WALL, VKScope.PHOTOS, VKScope.NOHTTPS, VKScope.PAGES};


    private void showPreviousPurchases() {
        //Logger.printMessage(TAG, "previous purchases", Logger.DEBUG);
        if (mHelper.mService == null) {
            Toast.makeText(getActivity(), "Something Went Wrong. Try later",
                    Toast.LENGTH_LONG).show();
            return;
        }
        Bundle ownedItems = null;
        ;
        try {
            ownedItems = mHelper.mService.getPurchases(3, getActivity().getPackageName(), "inapp",
                    null);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (ownedItems == null) {
            //Logger.printMessage(TAG, "criical error ", Logger.DEBUG);
            return;
        }
        int response = ownedItems.getInt("RESPONSE_CODE");
        if (response == 0) {
            ArrayList<String> ownedSkus = ownedItems
                    .getStringArrayList("INAPP_PURCHASE_ITEM_LIST");
            ArrayList<String> purchaseDataList = ownedItems
                    .getStringArrayList("INAPP_PURCHASE_DATA_LIST");
    /*  ArrayList<String> signatureList = ownedItems
                .getStringArrayList("INAPP_DATA_SIGNATURE");
        String continuationToken = ownedItems
                .getString("INAPP_CONTINUATION_TOKEN");*/

            for (int i = 0; i < purchaseDataList.size(); ++i) {
                String purchaseData = purchaseDataList.get(i);
//                Logger.printMessage(TAG, "json  = " + purchaseData,
//                        Logger.DEBUG);
                // String signature = signatureList.get(i);
                String sku = ownedSkus.get(i);

                //addChipsAndMakeItConsumable(purchaseData);
                // do something with this purchase information
                // e.g. display the updated list of products owned by user
            }

            // if continuationToken != null, call getPurchases again
            // and pass in the token to retrieve more items
        }
        //mHelper.consumeAsync();


        Purchase purchase;
        try {
            purchase = new Purchase("inapp", "{\"packageName\":\"com.nicholaschirkevich.game\",\"orderId\":\"transactionId.android.test.purchased\",\"productId\":\"android.test.purchased\",\"developerPayload\":\"speedy_road_tocken\",\"purchaseTime\":0,\"purchaseState\":0,\"purchaseToken\":\"inapp:com.nicholaschirkevich.game:android.test.purchased\"}", "");
            mHelper.consumeAsync(purchase, new IabHelper.OnConsumeFinishedListener() {

                @Override
                public void onConsumeFinished(Purchase purchase, IabResult result) {
                    Log.d("TAG", "Result: " + result);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void initialSPreferences() {
        sPref = this.getActivity().getSharedPreferences(getString(R.string.preferenceskey), Context.MODE_PRIVATE);
        isAdmobOn = sPref.getBoolean(getString(R.string.admobestatus), true);
    }

    private void setAdMobStatus(boolean statusOn) {
        if (sPref != null) {
            sPref.edit().putBoolean(getString(R.string.admobestatus), statusOn).commit();
            isAdmobOn = statusOn;

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        initialSPreferences();
        gameHelper = new GameHelper(getActivity(), GameHelper.CLIENT_GAMES);
        gameHelper.enableDebugLog(true);
        final GameHelper.GameHelperListener gameHelperListener = new GameHelper.GameHelperListener() {
            @Override
            public void onSignInFailed() {
                System.out.println("Sing in faild");
            }

            @Override
            public void onSignInSucceeded() {
                System.out.println("Sing in success");
            }
        };
        gameHelper.setup(gameHelperListener);

//        Games.Leaderboards.submitScore(mGoogleApiClient, getString(R.string.leaderboard_id), 20);
        // VKSdk.login(getActivity(), vkScope);
        mInterstitialAdSaveMe = new InterstitialAd(getContext());
        mInterstitialAdSaveMe.setAdUnitId(appId);
        interstitialGetBonus = new InterstitialAd(getContext());
        interstitialGetBonus.setAdUnitId(getBonusAdmobId);


        defaultImage = (ImageView) getActivity().findViewById(R.id.default_image);

        byButton = (Button) getActivity().findViewById(R.id.bttn_by);
        //byButton.setEnabled(false);
        mPurchaseFinishedListener
                = new IabHelper.OnIabPurchaseFinishedListener() {
            public void onIabPurchaseFinished(IabResult result,
                                              Purchase purchase) {
                if (result.isFailure()) {

                    buyProduct.onErroreBuy();
                    Toast.makeText(getActivity(), result.getMessage(), Toast.LENGTH_LONG).show();
                    // Handle error
                    return;
                } else if (purchase.getSku().equals(ITEM_SKU)) {
                    buyProduct.onBuyProduct();
                    Toast.makeText(getActivity(), purchase.toString(), Toast.LENGTH_LONG).show();
                    consumeItem();
                    //byButton.setEnabled(false);
                }

            }
        };


        initialBillingServie();
        byButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gameHelper.isSignedIn() == true) {
                    Games.Leaderboards.submitScore(gameHelper.getApiClient(),
                            getString(R.string.leaderboard_leaderboard), 100);
                }
//                mHelper.launchPurchaseFlow(getActivity(), ITEM_SKU_SP, 10001,
//                        mPurchaseFinishedListener, getString(R.string.purchasetocken));
            }
        });
        showButton = (Button) getActivity().findViewById(R.id.bttn_show);
        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gameHelper.beginUserInitiatedSignIn();
                if (gameHelper.isSignedIn()) {
                    startActivityForResult(Games.Leaderboards.getLeaderboardIntent(gameHelper.getApiClient(),
                            getString(R.string.leaderboard_leaderboard)), requestCode);
                } else {
                    gameHelper.beginUserInitiatedSignIn();
                }


                //   showPreviousPurchases();
//                showButton.setEnabled(false);
//                byButton.setEnabled(true);
//                String base64EncodedPublicKey =
//                        getString(R.string.base64EncodedKey);
//
//                mHelper = new IabHelper(getContext(), base64EncodedPublicKey);
//
//                mHelper.startSetup(new
//                                           IabHelper.OnIabSetupFinishedListener() {
//                                               public void onIabSetupFinished(IabResult result) {
//                                                   if (!result.isSuccess()) {
//                                                       Toast.makeText(getContext(), getString(R.string.inAppBuillingErrore), Toast.LENGTH_LONG).show();
//                                                       Log.d(TAG, getString(R.string.inAppBuillingErrore) +
//                                                               result);
//                                                   } else {
//                                                       Toast.makeText(getContext(), getString(R.string.inAppBuillingOk), Toast.LENGTH_LONG).show();
//                                                       Log.d(TAG, getString(R.string.inAppBuillingOk));
//                                                   }
//                                               }
//                                           });


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
                            Toast.makeText(getContext(), "Consume succes" + result.getMessage(), Toast.LENGTH_LONG).show();
                        } else {
                            mHelper.consumeAsync(purchase,
                                    mConsumeFinishedListener);
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


        startGame();
        gameRuners = new GameRuners(this);


        return initializeForView(gameRuners);


    }


    @Override
    public void onStart() {
        super.onStart();
        gameHelper.onStart(getActivity());
    }

    @Override
    public void onStop() {
        super.onStop();
        gameHelper.onStop();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent data) {
        if (!mHelper.handleActivityResult(requestCode,
                resultCode, data)) {
            gameHelper.onActivityResult(requestCode, resultCode, data);
            super.onActivityResult(requestCode, resultCode, data);
        }

    }

    public void consumeItem() {

        mHelper.queryInventoryAsync(new IabHelper.QueryInventoryFinishedListener() {

            @Override
            public void onQueryInventoryFinished(IabResult result, Inventory inv) {

                if (result.isFailure()) {
                    // Handle failure
                } else {
                    Toast.makeText(getActivity(), "ok consume Item", Toast.LENGTH_LONG).show();
                    mHelper.consumeAsync(inv.getPurchase(ITEM_SKU),
                            mConsumeFinishedListener);
                }
            }
        });
    }


    private void showGetBonusInterstitial() {


        try {
            runOnUiThread(new Runnable() {


                public void run() {

                    mInterstitialAdSaveMe.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            startGame();

                            gameRuners.onAdCloseAfterGetBonus();

                        }
                    });

                    if (mInterstitialAdSaveMe != null && mInterstitialAdSaveMe.isLoaded()) {
                        mInterstitialAdSaveMe.show();
                    } else {
                        Toast.makeText(getContext(), "Ad did not load", Toast.LENGTH_SHORT).show();
                        startGame();

                    }
                }
            });
        } catch (Exception e) {
        }

    }

    private void showInterstitial(final boolean isAfterGetBonus) {


        try {
            runOnUiThread(new Runnable() {


                public void run() {

                    mInterstitialAdSaveMe.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            startGame();
                            if (isAfterGetBonus) {
                                gameRuners.onAdCloseAfterGetBonus();
                            } else {

                                gameRuners.onAdClose();
                            }
                        }
                    });

                    if (mInterstitialAdSaveMe != null && mInterstitialAdSaveMe.isLoaded()) {
                        mInterstitialAdSaveMe.show();
                    } else {
                        Toast.makeText(getContext(), "Ad did not load", Toast.LENGTH_SHORT).show();
                        startGame();
                        if (isAfterGetBonus) {

                        } else {
                            gameRuners.onAdClose();
                            startGame();
                        }
                    }
                }
            });
        } catch (Exception e) {
        }

    }

    private void startGame() {
        if (isAdmobOn) {
            // Request a new ad if one isn't already loaded, hide the button, and kick off the timer.
            if (!mInterstitialAdSaveMe.isLoading() && !mInterstitialAdSaveMe.isLoaded()) {
                //AdRequest adRequest = new AdRequest.Builder().addTestDevice("024E787E6EB1DF2F6E701EE93F986BA4").build();
                AdRequest adRequest = new AdRequest.Builder().build();
                mInterstitialAdSaveMe.loadAd(adRequest);
            }
            if (!interstitialGetBonus.isLoading() && !interstitialGetBonus.isLoaded()) {
                //AdRequest adRequest = new AdRequest.Builder().addTestDevice("024E787E6EB1DF2F6E701EE93F986BA4").build();
                AdRequest adRequest = new AdRequest.Builder().build();
                interstitialGetBonus.loadAd(adRequest);
            }
        }
    }

    private void initialBillingServie() {
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


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mHelper != null) mHelper.dispose();
        mHelper = null;
    }

    @Override
    public void showOrLoadInterstital(boolean isAfterGetBonus) {
        showInterstitial(isAfterGetBonus);
    }

    @Override
    public void showInterstitaGetBonus() {
        showGetBonusInterstitial();
    }

    @Override
    public boolean isAvailibleInternet() {
        return InternetHelper.hasConnection(getContext());
    }

    @Override
    public boolean isSaveMeIntertitalLoad() {
        return !mInterstitialAdSaveMe.isLoading();
    }

    @Override
    public boolean isGetBonusIntertitalLoad() {
        return !interstitialGetBonus.isLoading();
    }

    @Override
    public boolean isIntertatlLoaded() {
        final boolean[] isLoaded = {false};
        try {


            runOnUiThread(new Runnable() {


                public void run() {
                    isLoaded[0] = mInterstitialAdSaveMe.isLoaded();

                }
            });
        } catch (Exception e) {
        }
        return isLoaded[0];
    }

    @Override
    public boolean isGetBonusIntertatlLoaded() {
        final boolean[] isLoaded = {false};
        try {


            runOnUiThread(new Runnable() {


                public void run() {
                    isLoaded[0] = interstitialGetBonus.isLoaded();

                }
            });
        } catch (Exception e) {
        }
        return isLoaded[0];
    }

    @Override
    public boolean getAdmobStatus() {
        return isAdmobOn;
    }

    @Override
    public void setAdmobStatus(boolean statusAdMob) {
        setAdMobStatus(statusAdMob);
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

        Intent intent = new Intent(getActivity(), FriendsInviteActivity.class);
        startActivity(intent);
//        currentRequest = VKApi.friends().get(VKParameters.from(VKApiConst.FIELDS, "id,first_name,last_name,photo_100"));
//        final ArrayList<User> users = new ArrayList<>();
//
//        currentRequest.executeWithListener(new VKRequest.VKRequestListener() {
//            @Override
//            public void onComplete(VKResponse response) {
//                super.onComplete(response);
//                Log.d("VkDemoApp", "onComplete " + response);
//
//                VKUsersArray usersArray = (VKUsersArray) response.parsedModel;
//                users.clear();
//
//                for (VKApiUserFull userFull : usersArray) {
//
//                    users.add(new User(userFull.toString(), userFull.id, userFull.photo_100));
//
//                }
//
//                final Dialog dialog = new Dialog(getContext());
//
//                View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_main, null);
//
//                ListView lv = (ListView) view.findViewById(R.id.list_friends_dialog_main);
//
//
//                // Change MyActivity.this and myListOfItems to your own values
//                FriendDialogListAdapter clad = new FriendDialogListAdapter(getContext(), users);
//
//                lv.setAdapter(clad);
//
        //               lv.setOnItemClickListener(........);
//
//                dialog.setContentView(view);
//
//                dialog.show();
//            }
//
//            @Override
//            public void attemptFailed(VKRequest request, int attemptNumber, int totalAttempts) {
//                super.attemptFailed(request, attemptNumber, totalAttempts);
//                Log.d("VkDemoApp", "attemptFailed " + request + " " + attemptNumber + " " + totalAttempts);
//            }
//
//            @Override
//            public void onError(VKError error) {
//                super.onError(error);
//                Log.d("VkDemoApp", "onError: " + error);
//            }
//
//            @Override
//            public void onProgress(VKRequest.VKProgressType progressType, long bytesLoaded, long bytesTotal) {
//                super.onProgress(progressType, bytesLoaded, bytesTotal);
//                Log.d("VkDemoApp", "onProgress " + progressType + " " + bytesLoaded + " " + bytesTotal);
//            }
//        });
//

    }

    @Override
    public String getMyId() {
        if (VKAccessToken.currentToken() != null)
            return VKAccessToken.currentToken().userId;
        else return null;
    }

    @Override
    public void buyProduct(String id, BuyProduct buyProduct) {
        this.buyProduct = buyProduct;
        mHelper.flagEndAsync();
        mHelper.launchPurchaseFlow(getActivity(), ITEM_SKU, 10001,
                mPurchaseFinishedListener, getString(R.string.purchasetocken));


    }


    @Override
    public void goneDefaultImage() {
        runOnUiThread(new Runnable() {
            public void run() {
                defaultImage.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void getLidearBoards(OnGetLidearBoards onGetLidearBoards) {
        ServerApi.getLeaderBoards(onGetLidearBoards);
    }

    @Override
    public void getVkImageLidearBoards(final OnGetLidearBoards onGetLidearBoards, ArrayList<LeaderboardEntity> leaderboardEntities) {
        StringBuilder ids = new StringBuilder();
        for (int i = 0; i < leaderboardEntities.size(); i++) {
            LeaderboardEntity leaderboardEntity = leaderboardEntities.get(i);
            if (!leaderboardEntity.getVk_id().equals("null"))
                if (i + 1 < leaderboardEntities.size())
                    ids.append(leaderboardEntity.getVk_id() + ",");
                else ids.append(leaderboardEntity.getVk_id());

        }
        VKRequest currentRequest;
        currentRequest = VKApi.users().get(VKParameters.from(VKApiConst.USER_IDS, ids, VKApiConst.FIELDS, "id,first_name,last_name,photo_100"));
        final ArrayList<VkUser> images = new ArrayList<>();

        currentRequest.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                Log.d("VkDemoApp", "onComplete " + response);

                VKList<VKApiUserFull> usersArray = (VKList<VKApiUserFull>) response.parsedModel;
                images.clear();

                for (VKApiUserFull userFull : usersArray) {

                    images.add(new VkUser(String.valueOf(userFull.id), userFull.photo_100, userFull.first_name, userFull.last_name));

                }
                onGetLidearBoards.onGetVkImageLidearboardsData(images);

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
    public void getByteImage(OnGetLidearBoards onGetLidearBoards, String url, int index) {
        ServerApi.getImages(onGetLidearBoards, url);
    }

    @Override
    public void getHighscoresVkFriends(final OnGetLidearBoards onGetLidearBoards) {
        VKRequest currentRequest;
        currentRequest = VKApi.friends().get(VKParameters.from(VKApiConst.FIELDS, "id,first_name,last_name,photo_100"));
        final ArrayList<VkUser> friends = new ArrayList<>();

        currentRequest.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                Log.d("VkDemoApp", "onComplete " + response);

                VKList<VKApiUserFull> usersArray = (VKList<VKApiUserFull>) response.parsedModel;
                friends.clear();

                for (VKApiUserFull userFull : usersArray) {

                    friends.add(new VkUser(String.valueOf(userFull.id), userFull.photo_100, userFull.first_name, userFull.last_name));

                }
                ServerApi.getHighscoresFriends(onGetLidearBoards, friends);
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
    public void submitScore(int highScore) {
        if (isSignedIn() == true) {
            Games.Leaderboards.submitScore(gameHelper.getApiClient(),
                    getString(R.string.leaderboard_leaderboard), highScore);
        }
    }

    @Override
    public void signIn() {
        try
        {
            runOnUiThread(new Runnable()
            {
                @Override
                public void run()
                {
                    gameHelper.beginUserInitiatedSignIn();
                }
            });
        }
        catch (Exception e)
        {
            Gdx.app.log("MainActivity", "Log in failed: " + e.getMessage() + ".");
        }
    }

    @Override
    public void signOut() {
        try
        {
            runOnUiThread(new Runnable()
            {
                @Override
                public void run()
                {
                    gameHelper.signOut();
                }
            });
        }
        catch (Exception e)
        {
            Gdx.app.log("MainActivity", "Log out failed: " + e.getMessage() + ".");
        }
    }

    @Override
    public void showScore() {
        if (isSignedIn() == true)
        {
            startActivityForResult(Games.Leaderboards.getLeaderboardIntent(gameHelper.getApiClient(),
                    getString(R.string.leaderboard_leaderboard)), requestCode);
        }
        else
        {
            signIn();
        }
    }

    @Override
    public boolean isSignedIn() {
        return gameHelper.isSignedIn();
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

    @Override
    public void onGetHightscoreList(ArrayList<LeaderboardEntity> arrayList) {

    }


}
