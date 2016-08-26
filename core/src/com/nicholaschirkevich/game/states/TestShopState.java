package com.nicholaschirkevich.game.states;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.SnapshotArray;
import com.nicholaschirkevich.game.GameRuners;
import com.nicholaschirkevich.game.admob.ActionResolver;
import com.nicholaschirkevich.game.entity.LeaderboardEntity;
import com.nicholaschirkevich.game.entity.VkUser;
import com.nicholaschirkevich.game.interfaces.ResumeButtonListener;
import com.nicholaschirkevich.game.listeners.OnGetLidearBoards;
import com.nicholaschirkevich.game.menu.RecordRectangle;
import com.nicholaschirkevich.game.menu.customview.OverscrollListener;
import com.nicholaschirkevich.game.menu.customview.ScrollPanCustom;
import com.nicholaschirkevich.game.menu.items.RecordsItem;
import com.nicholaschirkevich.game.util.AssetsManager;
import com.nicholaschirkevich.game.util.Constants;
import com.nicholaschirkevich.game.util.GameManager;
import com.nicholaschirkevich.game.view_adapters.RecordsLeaderBoardAdapter;

import java.util.ArrayList;


/**
 * Created by Nikolas on 10.03.2016.
 */
public class TestShopState extends Group implements ResumeButtonListener, OnGetLidearBoards {
    private OrthographicCamera camera;
    private Texture cnr_line, backgroung_texture, backButtonTextureDown, backButtonTextureUp;
    private Image image, backgroung_image, backButtonImageDown, backButtonImageUp;
    private ScrollPanCustom scrollRight;
    private ScrollPanCustom scrollPaneLeft;
    private TextButton backBttn;
    private SequenceAction sequenceReturn;
    private Table tableRight, container, tableLeft;
    private GameStateManager gsm;
    private ActionResolver actionResolver;
    private Group parentView;
    private TestShopState thisView;
    private ArrayList<LeaderboardEntity> leaderboardEntities = new ArrayList<LeaderboardEntity>();
    private Image progressBarImage;
    private TextButton inviteFriend;
    private float progressBarDelta = 0f;

    private boolean isLeaderboardsLoad = false, isHighScoreLoad = false;
    private boolean isLeftTabSelected = false;


    public TestShopState(GameStateManager gsm, ActionResolver actionResolver, Group parentView) {

        thisView = this;
        this.actionResolver = actionResolver;
        this.parentView = parentView;
        setInvisibleParent(parentView);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        cnr_line = AssetsManager.getTextureRegion(Constants.CNR_LINE_ID).getTexture();
        backgroung_texture = AssetsManager.getTextureRegion(Constants.RECORN_TAB_BUTTON_SELECTED_ID).getTexture();
        backgroung_image = new Image(backgroung_texture);
        backgroung_image.setBounds(0, 0, GameRuners.WIDTH / 2, GameRuners.HEIGHT / 2);
        //addActor(backgroung_image);
        image = new Image(cnr_line);
        image.setBounds(0, GameRuners.HEIGHT / 2 - 70, GameRuners.WIDTH / 2, 80);
        sequenceReturn = new SequenceAction();
        this.gsm = gsm;

//        garageNameTexture = AssetsManager.getTextureRegion(Constants.COIN_SHOP_NAME_ID).getTexture();
//        garageNameImage = new Image(garageNameTexture);
//        garageNameImage.setBounds(5, GameRuners.HEIGHT / 2 - 40, garageNameTexture.getWidth(), garageNameTexture.getHeight());
        //setUpHighscoreFriendsTable(new ArrayList<VkUser>());
        backgroung_image.setBounds(11, 73, GameRuners.WIDTH / 2 - 22, GameRuners.HEIGHT / 2 - 220);
        addActor(backgroung_image);
         actionResolver.getLidearBoards(this);
        //getHighscoresVkFriends(this);
        addRectagleScroll();

        setUpBackButton();
        setUpTabImageButton();

//        setUpBackButton();
//        setUpStartButton();
        addActor(image);
        setUpProgressBar();
        // addActor(garageNameImage);


        //setUpImageCoinCount();
        //setUpCoinCountLabel();
        //setUpResumeImageButton();

    }


    private void getHighscoresVkFriends(final OnGetLidearBoards onGetLidearBoards) {
        if (!isHighScoreLoad) {
            isHighScoreLoad = true;
            actionResolver.getHighscoresVkFriends(onGetLidearBoards);
            System.out.println("  actionResolver.getHighscoresVkFriends(onGetLidearBoards);");
        }
    }

    private void getLidearBoards(OnGetLidearBoards onGetLidearBoards) {
        if (!isLeaderboardsLoad) {
            isLeaderboardsLoad = true;
            actionResolver.getLidearBoards(onGetLidearBoards);
        }
    }

    public void addRectagleScroll() {
        addActor(new RecordRectangle(10, 72, GameRuners.WIDTH / 2 - 20, GameRuners.HEIGHT / 2 - 218));
    }

    public void setInvisibleParent(Group group) {
        SnapshotArray<Actor> actors = group.getChildren();
        for (Actor actor : actors) {
            actor.setVisible(false);
        }
    }

    public void setVisibleParent(Group group) {
        SnapshotArray<Actor> actors = group.getChildren();
        for (Actor actor : actors) {
            actor.setVisible(true);
        }
    }

    void setUpProgressBar() {
        progressBarImage = new Image(AssetsManager.getTextureRegion(Constants.PROGRESS_CIRCLE_ID));
        progressBarImage.setScale(0.25f, 0.25f);
        progressBarImage.setOrigin(progressBarImage.getWidth() / 2, progressBarImage.getHeight() / 2);
        progressBarImage.setAlign(Align.center);
        progressBarImage.setPosition(GameRuners.WIDTH / 4 - progressBarImage.getPrefWidth() / 2, GameRuners.HEIGHT / 4 - progressBarImage.getPrefHeight() / 2);
        addActor(progressBarImage);
    }

    void showProgressBar() {
        if (progressBarImage != null) progressBarImage.setVisible(true);
    }

    void disableProgressBar() {
        if (progressBarImage != null) progressBarImage.setVisible(false);
    }

    public void setUpTabImageButton() {

        final float ySelect = GameRuners.HEIGHT / 2 - 150;
        final float yUnSelect = ySelect + 5;
        final TextButton.TextButtonStyle leftTextButtonStyle = new TextButton.TextButtonStyle();
        leftTextButtonStyle.down = new Image(AssetsManager.getTextureRegion(Constants.RECORN_TAB_BUTTON_SELECTED_ID)).getDrawable();
        leftTextButtonStyle.up = new Image(AssetsManager.getTextureRegion(Constants.RECORN_TAB_BUTTON_SELECTED_ID)).getDrawable();
        leftTextButtonStyle.font = AssetsManager.getUiSkin().getFont("default-font");
        final TextButton leftButtonFriends = new TextButton(GameManager.getStrings().get(Constants.LEADERBOARD_FRIENDS_TEXT), leftTextButtonStyle);
        leftButtonFriends.getLabel().setFontScale(0.5f, 0.5f);
        leftButtonFriends.setBounds(11, ySelect, GameRuners.WIDTH / 4 - 12, 62);


        final TextButton.TextButtonStyle rightTextButtonStyle = new TextButton.TextButtonStyle();
        rightTextButtonStyle.down = new Image(AssetsManager.getTextureRegion(Constants.RECORN_TAB_BUTTON_UNSELECTED_ID)).getDrawable();
        rightTextButtonStyle.up = new Image(AssetsManager.getTextureRegion(Constants.RECORN_TAB_BUTTON_UNSELECTED_ID)).getDrawable();
        rightTextButtonStyle.font = AssetsManager.getUiSkin().getFont("default-font");
        final TextButton rightButtonFriends = new TextButton(GameManager.getStrings().get(Constants.LEADERBOARD_GLOBAL_TEXT), rightTextButtonStyle);
        rightButtonFriends.getLabel().setFontScale(0.5f, 0.5f);
        rightButtonFriends.setBounds(GameRuners.WIDTH / 4 + 1, yUnSelect, GameRuners.WIDTH / 4 - 12, 57);
        leftButtonFriends.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (!isLeftTabSelected) {
                    if (scrollRight != null) scrollRight.setVisible(false);

                    showProgressBar();
                    getHighscoresVkFriends(thisView);
                    //setUpHighscoreFriendsTable(new ArrayList<VkUser>());
                    leftTextButtonStyle.down = new Image(AssetsManager.getTextureRegion(Constants.RECORN_TAB_BUTTON_SELECTED_ID)).getDrawable();
                    leftTextButtonStyle.up = new Image(AssetsManager.getTextureRegion(Constants.RECORN_TAB_BUTTON_SELECTED_ID)).getDrawable();
                    leftButtonFriends.setBounds(11, ySelect, GameRuners.WIDTH / 4 - 12, 62);
                    rightTextButtonStyle.down = new Image(AssetsManager.getTextureRegion(Constants.RECORN_TAB_BUTTON_UNSELECTED_ID)).getDrawable();
                    rightTextButtonStyle.up = new Image(AssetsManager.getTextureRegion(Constants.RECORN_TAB_BUTTON_UNSELECTED_ID)).getDrawable();
                    rightButtonFriends.setBounds(GameRuners.WIDTH / 4 + 1, yUnSelect, GameRuners.WIDTH / 4 - 12, 57);
                    isLeftTabSelected = true;
                }
                return super.touchDown(event, x, y, pointer, button);

            }
        });

        rightButtonFriends.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (isLeftTabSelected) {
                    if (scrollPaneLeft != null & inviteFriend != null) {
                        scrollPaneLeft.setVisible(false);
                        inviteFriend.setVisible(false);
                    }


                    showProgressBar();
                    setUpLeaderboardsTable(new ArrayList<VkUser>());
                    getLidearBoards(thisView);
                    rightTextButtonStyle.down = new Image(AssetsManager.getTextureRegion(Constants.RECORN_TAB_BUTTON_SELECTED_ID)).getDrawable();
                    rightTextButtonStyle.up = new Image(AssetsManager.getTextureRegion(Constants.RECORN_TAB_BUTTON_SELECTED_ID)).getDrawable();
                    rightButtonFriends.setBounds(GameRuners.WIDTH / 4 + 1, ySelect, GameRuners.WIDTH / 4 - 12, 62);
                    leftTextButtonStyle.down = new Image(AssetsManager.getTextureRegion(Constants.RECORN_TAB_BUTTON_UNSELECTED_ID)).getDrawable();
                    leftTextButtonStyle.up = new Image(AssetsManager.getTextureRegion(Constants.RECORN_TAB_BUTTON_UNSELECTED_ID)).getDrawable();
                    leftButtonFriends.setBounds(11, yUnSelect, GameRuners.WIDTH / 4 - 12, 57);
                    isLeftTabSelected = false;
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        addActor(new RecordRectangle(10, GameRuners.HEIGHT / 2 - 146, GameRuners.WIDTH / 4 - 10, 59));

        addActor(new RecordRectangle(GameRuners.WIDTH / 4, GameRuners.HEIGHT / 2 - 146, GameRuners.WIDTH / 4 - 10, 59));


        addActor(rightButtonFriends);
        addActor(leftButtonFriends);
    }


    private void setUpBackButton() {
        backButtonTextureDown = AssetsManager.getTextureRegion(Constants.BACK_BUTTON_PRESSED_ID).getTexture();
        backButtonTextureUp = AssetsManager.getTextureRegion(Constants.BACK_BUTTON_ID).getTexture();

        backButtonImageDown = new Image(backButtonTextureDown);
        backButtonImageUp = new Image(backButtonTextureUp);


        float x = Constants.BACK_BTTN_X_VISIBLE, y = Constants.BACK_BTTN_Y_VISIBLE - 35, width = 70, height = 55;
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.down = backButtonImageDown.getDrawable();
        textButtonStyle.up = backButtonImageUp.getDrawable();
        textButtonStyle.font = AssetsManager.getUiSkin().getFont("default-font");

        backBttn = new TextButton("", textButtonStyle);
        backBttn.getLabel().setFontScale(0.4f, 0.4f);
        backBttn.getLabelCell().padLeft(25f);


        backBttn.setBounds(x - backBttn.getWidth() / 2, y - backBttn.getHeight() / 2, backBttn.getWidth(), backBttn.getHeight());

        backBttn.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                sequenceReturn.addAction(Actions.delay(0.1f));
                sequenceReturn.addAction(new Action() {
                    @Override
                    public boolean act(float delta) {
                        setVisibleParent(parentView);
                        getStage().addActor(parentView);

                        //getStage().addActor(new MenuTest(listenerResume, gsm, actionResolver));
                        return true;
                    }
                });
                sequenceReturn.addAction(Actions.removeActor());


                addAction(sequenceReturn);

                return true;
            }
        });

        addActor(backBttn);

    }


    public void setUpLeaderboardsTable(ArrayList<VkUser> arrayList) {
        //scrollRight.remove();
        container = new Table();
        tableRight = new Table();

        addActor(container);

        RecordsLeaderBoardAdapter recordAdapter = new RecordsLeaderBoardAdapter(tableRight, arrayList, actionResolver);

        tableRight.top().left();
        tableRight.padTop(3f);
        for( int i=0;i<arrayList.size();i++)
        {
            tableRight.add(new RecordsItem(arrayList.get(i),i,actionResolver));
        }
        //recordAdapter.loadTableData();

        if (scrollRight == null) {
            scrollRight = new ScrollPanCustom(tableRight);
            scrollRight.setBounds(11, 73, GameRuners.WIDTH / 2 - 22, GameRuners.HEIGHT / 2 - 220);
            scrollRight.setScrollingDisabled(true, false);
            scrollRight.setOverscroll(false, true);
            scrollRight.setForceScroll(true, true);
            scrollRight.setSmoothScrolling(false);
            scrollRight.setAmountY(recordAdapter.getMyIndex() * 60 - 95);
            scrollRight.setOverscrollListener(new OverscrollListener() {
                @Override
                public void onOverscroll() {
                    getLidearBoards(thisView);
                }
            });
            addActor(scrollRight);
        }
        else scrollRight.setVisible(true);
        scrollRight.invalidate();
        if (scrollPaneLeft != null) {

            scrollPaneLeft.setVisible(false);
        }
        if(inviteFriend!=null)
            inviteFriend.setVisible(false);




    }

    public void setUpHighscoreFriendsTable(ArrayList<VkUser> arrayList) {
        //scrollPaneLeft.remove();
        container = new Table();
        tableLeft = new Table();

        addActor(container);

        RecordsLeaderBoardAdapter vkAdapter = new RecordsLeaderBoardAdapter(tableLeft, arrayList, actionResolver);
        tableLeft.top().left();
        tableLeft.padTop(3f);

        vkAdapter.loadTableData();

        if (scrollPaneLeft == null) {
            scrollPaneLeft = new ScrollPanCustom(tableLeft);
            scrollPaneLeft.setBounds(11, 103, GameRuners.WIDTH / 2 - 22, GameRuners.HEIGHT / 2 - 250);
            scrollPaneLeft.setScrollingDisabled(true, false);
            scrollPaneLeft.setOverscroll(false, true);
            addActor(scrollPaneLeft);
        } else scrollPaneLeft.setVisible(true);
        scrollPaneLeft.invalidate();

        if (scrollRight != null) scrollRight.setVisible(false);

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = new Image(AssetsManager.getTextureRegion(Constants.CAR_GARAGE_BTTN_GREEN_UP)).getDrawable();
        textButtonStyle.down = new Image(AssetsManager.getTextureRegion(Constants.CAR_GARAGE_BTTN_GREEN_DOWN)).getDrawable();
        textButtonStyle.font = AssetsManager.getUiSkin().getFont("default-font");
        if (inviteFriend == null) {
            inviteFriend = new TextButton(GameManager.getStrings().get(Constants.LEADERBOARD_INVITE_TEXT), textButtonStyle);
            inviteFriend.getLabel().setFontScale(0.5f, 0.5f);
            inviteFriend.setBounds(30, 80, 260, 55);
            inviteFriend.addListener(new ClickListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    if (actionResolver.isAvailibleInternet()) {
                        AssetsManager.playSound(Constants.SOUND_CLICK);
                        actionResolver.showInviteBox();
                        return true;
                    }
                    return super.touchDown(event, x, y, pointer, button);
                }
            });
            addActor(inviteFriend);
        } else inviteFriend.setVisible(true);


    }


    @Override
    public void resumeButtonOnResume() {

    }

    @Override
    public void onSaveMe() {

    }


    @Override
    public void onGetLidearboardsData(ArrayList<LeaderboardEntity> leaderboard) {
        if (!isLeftTabSelected) {
            disableProgressBar();
            isLeaderboardsLoad = false;
            leaderboardEntities = leaderboard;
            actionResolver.getVkImageLidearBoards(this, leaderboardEntities);
//            scrollRight.setVisible(true);
        }
    }

    @Override
    public void onGetVkImageLidearboardsData(ArrayList<VkUser> vkUserArrayList) {
        disableProgressBar();
        isLeaderboardsLoad = false;
        for (VkUser vkUser : vkUserArrayList) {
            for (LeaderboardEntity leaderboard : leaderboardEntities) {
                if (vkUser.getId().equals(leaderboard.getVk_id())) {
                    vkUser.setHighscore(leaderboard.getHighscore());
                }
            }
        }

        setUpLeaderboardsTable(vkUserArrayList);

    }

    @Override
    public void onGetImage(byte[] imageByte) {

    }

    @Override
    public void onGetHighscoresFriends(ArrayList<VkUser> arrayList) {
        if (isLeftTabSelected) {
            disableProgressBar();
            isHighScoreLoad = false;
            setUpHighscoreFriendsTable(arrayList);
            scrollPaneLeft.setVisible(true);
            inviteFriend.setVisible(true);
        }

    }

    @Override
    public void onGetFacebookHighscoresFriends(ArrayList<VkUser> arrayList) {

    }

    @Override
    public void onGetLeaderboardErrore(String errore) {

    }

    @Override
    public void onGetVkLeaderboardErrore(String errore) {

    }


    @Override
    public void act(float delta) {
        super.act(delta);
        progressBarDelta += delta;
        if (progressBarDelta > 0.1) {
            progressBarDelta = 0;
            if (progressBarImage != null)
                progressBarImage.setRotation(progressBarImage.getRotation() - 20f);
        }
    }
}
