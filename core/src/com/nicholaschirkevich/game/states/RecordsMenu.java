package com.nicholaschirkevich.game.states;

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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
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
import com.nicholaschirkevich.game.listeners.OnLoginListenerInterface;
import com.nicholaschirkevich.game.menu.RecordRectangle;
import com.nicholaschirkevich.game.menu.customview.ScrollPanCustom;
import com.nicholaschirkevich.game.util.AssetsManager;
import com.nicholaschirkevich.game.util.Constants;
import com.nicholaschirkevich.game.util.GameManager;
import com.nicholaschirkevich.game.view_adapters.RecordsLeaderBoardAdapter;

import java.util.ArrayList;


/**
 * Created by Nikolas on 10.03.2016.
 */
public class RecordsMenu extends Group implements ResumeButtonListener, OnGetLidearBoards, OnLoginListenerInterface {
    private OrthographicCamera camera;
    private Texture cnr_line, backgroung_texture, backButtonTextureDown, backButtonTextureUp;
    private Image image, backgroung_image, backButtonImageDown, backButtonImageUp;

    private TextButton backBttn;
    private SequenceAction sequenceReturn;
    private Label menuName;

    private GameStateManager gsm;
    private ActionResolver actionResolver;
    private Group parentView;
    private RecordsMenu thisView;
    private ArrayList<LeaderboardEntity> leaderboardEntities = new ArrayList<LeaderboardEntity>();
    private Image progressBarImage;

    private float progressBarDelta = 0f;

    private boolean isLeaderboardsLoad = false, isHighScoreLoad = false;
    private boolean isLeftTabSelected = false;


    private TextButton.TextButtonStyle leftTextButtonStyle;
    private TextButton leftButtonFriends;
    private TextButton.TextButtonStyle rightTextButtonStyle;
    final float ySelect = GameRuners.HEIGHT / 2 - 150;
    final float yUnSelect = ySelect + 5;
    private TextButton rightButtonFriends;
    private boolean updateScrollLeaderboards = false;

    private RecordsLeaderBoardAdapter recordAdapter;

    @Override
    public void onLoginFb() {
        table1.clear();
        table1.padTop(0);
        if (actionResolver.isVkLogin()) {
            getHighscoresVkFriends(this);
        } else if (actionResolver.isFacebookLogin()) {
            getHighscoresFacebookFriends(this);
        } else {
            Label errorLabel = new Label(GameManager.getStrings().get(Constants.MP_POP_UP_TEXT), AssetsManager.getUiSkin());
            errorLabel.setFontScale(0.5f, 0.5f);
            errorLabel.setAlignment(Align.center, Align.center);
            table1.add(errorLabel);
            table1.padTop(20f);
            disableProgressBar();
        }
    }

    @Override
    public void onLoginVk() {
        table1.clear();
        table1.padTop(0);
        if (actionResolver.isVkLogin()) {
            getHighscoresVkFriends(this);
        } else if (actionResolver.isFacebookLogin()) {
            getHighscoresFacebookFriends(this);
        } else {
            Label errorLabel = new Label(GameManager.getStrings().get(Constants.MP_POP_UP_TEXT), AssetsManager.getUiSkin());
            errorLabel.setFontScale(0.5f, 0.5f);
            errorLabel.setAlignment(Align.center, Align.center);
            table1.add(errorLabel);
            table1.padTop(20f);
            disableProgressBar();
        }
    }

    @Override
    public void onLoginGoogle() {

    }

    @Override
    public void onLoginGoogleError() {

    }

    class HighScoreItem extends Group {
        private TextButton inviteFriend;
        ScrollPanCustom pane1;

        private void setUpSocialButtons() {

            TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
            textButtonStyle.up = new Image(AssetsManager.getTextureRegion(Constants.BUTTON_SOCIAL_ID)).getDrawable();
            textButtonStyle.down = new Image(AssetsManager.getTextureRegion(Constants.BUTTON_SOCIAL_PRESSED_ID)).getDrawable();
            textButtonStyle.font = AssetsManager.getUiSkin().getFont("default-font");
            TextButton buttonSocial = new TextButton(GameManager.getStrings().get(Constants.LEADERBOARD_LOGIN_FACEBOOK_TEXT), textButtonStyle);
            buttonSocial.getLabel().setFontScale(0.45f, 0.45f);
            buttonSocial.setPosition(10, 5);
            buttonSocial.setHeight(60);
            buttonSocial.setWidth(130);
            buttonSocial.addListener(new ClickListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    super.touchDown(event, x, y, pointer, button);
                    actionResolver.singInFb(thisView);
                    return true;
                }
            });
            addActor(buttonSocial);

            TextButton.TextButtonStyle textButtonFacebookStyle = new TextButton.TextButtonStyle();
            textButtonFacebookStyle.up = new Image(AssetsManager.getTextureRegion(Constants.BUTTON_SOCIAL_ID)).getDrawable();
            textButtonFacebookStyle.down = new Image(AssetsManager.getTextureRegion(Constants.BUTTON_SOCIAL_PRESSED_ID)).getDrawable();
            textButtonFacebookStyle.font = AssetsManager.getUiSkin().getFont("default-font");
            TextButton buttonVkSocial = new TextButton(GameManager.getStrings().get(Constants.LEADERBOARD_LOGIN_VK_TEXT), textButtonFacebookStyle);
            buttonVkSocial.getLabel().setFontScale(0.45f, 0.45f);
            buttonVkSocial.setPosition(160, 5);
            buttonVkSocial.setHeight(60);
            buttonVkSocial.setWidth(130);
            buttonVkSocial.addListener(new ClickListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    super.touchDown(event, x, y, pointer, button);
                    actionResolver.showVkLoginActivity(thisView);
                    return true;
                }
            });
            addActor(buttonVkSocial);

            pane1.setY(buttonVkSocial.getY() + buttonVkSocial.getPrefHeight());
        }

        public void setUpInviteButton() {
            TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
            textButtonStyle.up = new Image(AssetsManager.getTextureRegion(Constants.BUTTON_PLAY_WITH_A_FRIEND_ID)).getDrawable();
            textButtonStyle.down = new Image(AssetsManager.getTextureRegion(Constants.BUTTON_PLAY_WITH_A_FRIEND_ID)).getDrawable();
            textButtonStyle.font = AssetsManager.getUiSkin().getFont("default-font");
            inviteFriend = new TextButton(GameManager.getStrings().get(Constants.LEADERBOARD_INVITE_TEXT), textButtonStyle);
            inviteFriend.getLabel().setFontScale(0.5f, 0.5f);
            inviteFriend.setBounds(20, 5, 260, 55);
            inviteFriend.addListener(new ClickListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    if (actionResolver.isAvailibleInternet()) {
                        AssetsManager.playSound(Constants.SOUND_CLICK);
                        if (actionResolver.isVkLogin()) {
                            actionResolver.showInviteBox();
                        } else if (actionResolver.isFacebookLogin()) {
                            actionResolver.showInviteFacebook();
                        }

                        return true;
                    }
                    return super.touchDown(event, x, y, pointer, button);
                }
            });
            if (actionResolver.isVkLogin() || actionResolver.isFacebookLogin())
                addActor(inviteFriend);
            else{
                setUpSocialButtons();
            }
        }

        public void setUpPane(ScrollPanCustom scrollPane) {
            this.pane1 = scrollPane;
            this.addActor(pane1);
            pane1.setWidth(300);
            pane1.setHeight(GameRuners.HEIGHT / 2 - 285);
            setUpInviteButton();
            pane1.setY(inviteFriend.getY() + inviteFriend.getPrefHeight());

        }


    }


    ScrollPanCustom pane2;
    ScrollPane pane;

    final Table table1 = new Table();
    final Table table2 = new Table();
    final Table mytable = new Table();


    private ScrollPanCustom pane1;

    public RecordsMenu(GameStateManager gsm, ActionResolver actionResolver, Group parentView) {


        this.actionResolver = actionResolver;
        menuName = new Label(GameManager.getStrings().get(Constants.LB_HEADER_TEXT), AssetsManager.getUiSkin());
        menuName.setBounds(60, GameRuners.HEIGHT / 2 - 35, menuName.getWidth(), menuName.getHeight());
        menuName.setColor(255f / 255f, 128f / 255f, 0f / 255f, 1f);
        if (!GameManager.getLocale().equals(Constants.RU_LOCALE)) menuName.setFontScale(0.8f, 0.8f);
        Image imageTitle = new Image(AssetsManager.getTextureRegion(Constants.TITLE_LEADERBOARD_RUS_ID));
        imageTitle.setBounds(10, GameRuners.HEIGHT / 2 - 35, imageTitle.getWidth(), imageTitle.getHeight());

        mytable.top();
        table2.top();
        table1.top();
        pane1 = new ScrollPanCustom(table1);

        pane2 = new ScrollPanCustom(table2);
        HighScoreItem highScoreItem = new HighScoreItem();
        highScoreItem.setUpPane(pane1);
        pane2.setScrollingDisabled(true, false);


        pane1.setScrollingDisabled(true, false);


        mytable.add(highScoreItem).size(GameRuners.WIDTH / 2 - 22, GameRuners.HEIGHT / 2 - 210);
        mytable.add(pane2).size(GameRuners.WIDTH / 2 - 22, GameRuners.HEIGHT / 2 - 210);


        pane = new ScrollPane(mytable) {
            float lastScrollY = 0;

            @Override
            public void act(float delta) {
                super.act(delta);


                if (GameManager.getGestureListnener().getDeltaX() < -20 && Math.abs(GameManager.getGestureListnener().getDeltaY()) < 5) {
                    pane.setScrollX(-GameManager.getGestureListnener().getDeltaX() * 20);
                    pane1.cancel();
                    pane1.fling(0, 0, 0);
                    pane2.cancel();
                    pane2.fling(0, 0, 0);

                    rightTextButtonStyle.down = new Image(AssetsManager.getTextureRegion(Constants.RECORN_TAB_BUTTON_SELECTED_ID)).getDrawable();
                    rightTextButtonStyle.up = new Image(AssetsManager.getTextureRegion(Constants.RECORN_TAB_BUTTON_SELECTED_ID)).getDrawable();
                    rightButtonFriends.setBounds(GameRuners.WIDTH / 4 + 1, ySelect, GameRuners.WIDTH / 4 - 12, 62);
                    leftTextButtonStyle.down = new Image(AssetsManager.getTextureRegion(Constants.RECORN_TAB_BUTTON_UNSELECTED_ID)).getDrawable();
                    leftTextButtonStyle.up = new Image(AssetsManager.getTextureRegion(Constants.RECORN_TAB_BUTTON_UNSELECTED_ID)).getDrawable();
                    leftButtonFriends.setBounds(11, yUnSelect, GameRuners.WIDTH / 4 - 12, 57);
                    isLeftTabSelected = false;
                }

                if (GameManager.getGestureListnener().getDeltaX() > 20 && Math.abs(GameManager.getGestureListnener().getDeltaY()) < 5) {
                    pane.setScrollX(-GameManager.getGestureListnener().getDeltaX() * 20);
                    pane1.cancel();
                    pane1.fling(0, 0, 0);
                    pane2.cancel();
                    pane2.fling(0, 0, 0);

                    leftTextButtonStyle.down = new Image(AssetsManager.getTextureRegion(Constants.RECORN_TAB_BUTTON_SELECTED_ID)).getDrawable();
                    leftTextButtonStyle.up = new Image(AssetsManager.getTextureRegion(Constants.RECORN_TAB_BUTTON_SELECTED_ID)).getDrawable();
                    leftButtonFriends.setBounds(11, ySelect, GameRuners.WIDTH / 4 - 12, 62);
                    rightTextButtonStyle.down = new Image(AssetsManager.getTextureRegion(Constants.RECORN_TAB_BUTTON_UNSELECTED_ID)).getDrawable();
                    rightTextButtonStyle.up = new Image(AssetsManager.getTextureRegion(Constants.RECORN_TAB_BUTTON_UNSELECTED_ID)).getDrawable();
                    rightButtonFriends.setBounds(GameRuners.WIDTH / 4 + 1, yUnSelect, GameRuners.WIDTH / 4 - 12, 57);
                    isLeftTabSelected = true;

                }


            }
        };

        pane.setBounds(11, 62, GameRuners.WIDTH / 2 - 22, GameRuners.HEIGHT / 2 - 210);
        pane.setScrollingDisabled(false, true);
        pane.setOverscroll(false, false);
        pane1.setOverscroll(false, false);
        pane2.setOverscroll(false, false);
        pane.setCancelTouchFocus(false);
        if (false) {
            // This sizes the pane to the size of it's contents.
            pane.pack();
            // Then the height is hardcoded, leaving the pane the width of it's contents.
            pane.setHeight(Gdx.graphics.getHeight());
        } else {
            // This shows a hardcoded size.
            pane.setBounds(11, 62, GameRuners.WIDTH / 2 - 22, GameRuners.HEIGHT / 2 - 210);

        }


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

        image = new Image(cnr_line);
        image.setBounds(0, GameRuners.HEIGHT / 2 - 70, GameRuners.WIDTH / 2, 80);
        sequenceReturn = new SequenceAction();
        this.gsm = gsm;
        backgroung_image.setBounds(11, 63, GameRuners.WIDTH / 2 - 22, GameRuners.HEIGHT / 2 - 210);
        addActor(backgroung_image);

        getLidearBoards(thisView);
        if (actionResolver.isVkLogin()) {
            getHighscoresVkFriends(this);
        } else if (actionResolver.isFacebookLogin()) {
            getHighscoresFacebookFriends(this);
        } else {
            Label errorLabel = new Label(GameManager.getStrings().get(Constants.MP_POP_UP_TEXT), AssetsManager.getUiSkin());
            errorLabel.setFontScale(0.5f, 0.5f);
            errorLabel.setAlignment(Align.center, Align.center);
            table1.add(errorLabel);
            table1.padTop(120f);
            disableProgressBar();

        }
//        getHighscoresVkFriends(this);


        setUpBackButton();

        setUpTabImageButton();


        addActor(image);
        addActor(pane);
        addRectagleScroll();
        setUpProgressBar();

        addActor(imageTitle);
        addActor(menuName);
    }


    private void getHighscoresVkFriends(final OnGetLidearBoards onGetLidearBoards) {
        if (!isHighScoreLoad) {
            if (actionResolver.isAvailibleInternet()) {
                isHighScoreLoad = true;
                actionResolver.getHighscoresVkFriends(onGetLidearBoards);
                System.out.println("  actionResolver.getHighscoresVkFriends(onGetLidearBoards);");
            } else {
                Label errorLabel = new Label(GameManager.getStrings().get(Constants.NO_INTERNET_CONNECTION_ALERT), AssetsManager.getUiSkin());
                errorLabel.setFontScale(0.5f, 0.5f);
                errorLabel.setAlignment(Align.center, Align.center);
                table1.add(errorLabel);
                table1.padTop(120f);
                disableProgressBar();
            }
        }
    }

    private void getLidearBoards(OnGetLidearBoards onGetLidearBoards) {
        if (!isLeaderboardsLoad) {
            if (actionResolver.isAvailibleInternet()) {
                isLeaderboardsLoad = true;
                actionResolver.getLidearBoards(onGetLidearBoards);
            } else {
                Label errorLabel = new Label(GameManager.getStrings().get(Constants.NO_INTERNET_CONNECTION_ALERT), AssetsManager.getUiSkin());
                errorLabel.setFontScale(0.5f, 0.5f);
                errorLabel.setAlignment(Align.center, Align.center);
                table2.add(errorLabel);
                table2.padTop(120f);
                disableProgressBar();
            }
        }
    }

    private void getHighscoresFacebookFriends(final OnGetLidearBoards onGetLidearBoards) {
        if (!isHighScoreLoad) {
            if (actionResolver.isAvailibleInternet()) {
                isHighScoreLoad = true;
                actionResolver.getHighScoreFacebookFriends(onGetLidearBoards);
                System.out.println("  actionResolver.getHighscoresVkFriends(onGetLidearBoards);");
            }
        } else {
            Label errorLabel = new Label(GameManager.getStrings().get(Constants.NO_INTERNET_CONNECTION_ALERT), AssetsManager.getUiSkin());
            errorLabel.setFontScale(0.5f, 0.5f);
            errorLabel.setAlignment(Align.center, Align.center);
            table1.add(errorLabel);
            table1.padTop(120f);
            disableProgressBar();
        }
    }

    public void addRectagleScroll() {
        addActor(new RecordRectangle(10, 62, GameRuners.WIDTH / 2 - 20, GameRuners.HEIGHT / 2 - 208));
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
        if (!actionResolver.isAvailibleInternet()) progressBarImage.setVisible(false);
    }

    void showProgressBar() {
        if (progressBarImage != null) progressBarImage.setVisible(true);
    }

    void disableProgressBar() {
        if (progressBarImage != null) progressBarImage.setVisible(false);
    }

    public void setUpTabImageButton() {

        leftTextButtonStyle = new TextButton.TextButtonStyle();
        leftTextButtonStyle.down = new Image(AssetsManager.getTextureRegion(Constants.RECORN_TAB_BUTTON_SELECTED_ID)).getDrawable();
        leftTextButtonStyle.up = new Image(AssetsManager.getTextureRegion(Constants.RECORN_TAB_BUTTON_SELECTED_ID)).getDrawable();
        leftTextButtonStyle.font = AssetsManager.getUiSkin().getFont("default-font");
        leftButtonFriends = new TextButton(GameManager.getStrings().get(Constants.LEADERBOARD_FRIENDS_TEXT), leftTextButtonStyle);
        leftButtonFriends.getLabel().setFontScale(0.5f, 0.5f);
        leftButtonFriends.setBounds(11, ySelect, GameRuners.WIDTH / 4 - 12, 62);


        rightTextButtonStyle = new TextButton.TextButtonStyle();
        rightTextButtonStyle.down = new Image(AssetsManager.getTextureRegion(Constants.RECORN_TAB_BUTTON_UNSELECTED_ID)).getDrawable();
        rightTextButtonStyle.up = new Image(AssetsManager.getTextureRegion(Constants.RECORN_TAB_BUTTON_UNSELECTED_ID)).getDrawable();
        rightTextButtonStyle.font = AssetsManager.getUiSkin().getFont("default-font");
        rightButtonFriends = new TextButton(GameManager.getStrings().get(Constants.LEADERBOARD_GLOBAL_TEXT), rightTextButtonStyle);
        rightButtonFriends.getLabel().setFontScale(0.5f, 0.5f);
        rightButtonFriends.setBounds(GameRuners.WIDTH / 4 + 1, yUnSelect, GameRuners.WIDTH / 4 - 12, 57);

        leftButtonFriends.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                pane.setScrollX(-400 * 20);
                pane1.cancel();
                pane1.fling(0, 0, 0);
                pane2.cancel();
                pane2.fling(0, 0, 0);

                leftTextButtonStyle.down = new Image(AssetsManager.getTextureRegion(Constants.RECORN_TAB_BUTTON_SELECTED_ID)).getDrawable();
                leftTextButtonStyle.up = new Image(AssetsManager.getTextureRegion(Constants.RECORN_TAB_BUTTON_SELECTED_ID)).getDrawable();
                leftButtonFriends.setBounds(11, ySelect, GameRuners.WIDTH / 4 - 12, 62);
                rightTextButtonStyle.down = new Image(AssetsManager.getTextureRegion(Constants.RECORN_TAB_BUTTON_UNSELECTED_ID)).getDrawable();
                rightTextButtonStyle.up = new Image(AssetsManager.getTextureRegion(Constants.RECORN_TAB_BUTTON_UNSELECTED_ID)).getDrawable();
                rightButtonFriends.setBounds(GameRuners.WIDTH / 4 + 1, yUnSelect, GameRuners.WIDTH / 4 - 12, 57);
                isLeftTabSelected = true;

                return super.touchDown(event, x, y, pointer, button);

            }
        });

        rightButtonFriends.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                pane.setScrollX(400 * 20);
                pane1.cancel();
                pane1.fling(0, 0, 0);
                pane2.cancel();
                pane2.fling(0, 0, 0);

                rightTextButtonStyle.down = new Image(AssetsManager.getTextureRegion(Constants.RECORN_TAB_BUTTON_SELECTED_ID)).getDrawable();
                rightTextButtonStyle.up = new Image(AssetsManager.getTextureRegion(Constants.RECORN_TAB_BUTTON_SELECTED_ID)).getDrawable();
                rightButtonFriends.setBounds(GameRuners.WIDTH / 4 + 1, ySelect, GameRuners.WIDTH / 4 - 12, 62);
                leftTextButtonStyle.down = new Image(AssetsManager.getTextureRegion(Constants.RECORN_TAB_BUTTON_UNSELECTED_ID)).getDrawable();
                leftTextButtonStyle.up = new Image(AssetsManager.getTextureRegion(Constants.RECORN_TAB_BUTTON_UNSELECTED_ID)).getDrawable();
                leftButtonFriends.setBounds(11, yUnSelect, GameRuners.WIDTH / 4 - 12, 57);
                isLeftTabSelected = false;

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


    public void setUpLeaderboardsTable(final ArrayList<VkUser> arrayList) {


        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                recordAdapter = new RecordsLeaderBoardAdapter(table2, arrayList, actionResolver);
                recordAdapter.loadTableData();
                pane2.setAmountY(recordAdapter.getMyIndex() * 60 - 145);


            }
        });


    }

    public void setUpHighscoreFriendsTable(final ArrayList<VkUser> arrayList) {

        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                RecordsLeaderBoardAdapter recordAdapter = new RecordsLeaderBoardAdapter(table1, arrayList, actionResolver);
                recordAdapter.loadTableData();
            }
        });

    }


    @Override
    public void resumeButtonOnResume() {

    }

    @Override
    public void onSaveMe() {

    }


    @Override
    public void onGetLidearboardsData(ArrayList<LeaderboardEntity> leaderboard) {

        disableProgressBar();
        isLeaderboardsLoad = false;
        leaderboardEntities = leaderboard;
        actionResolver.getVkImageLidearBoards(this, leaderboardEntities);

        updateScrollLeaderboards = true;


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

        disableProgressBar();
        isHighScoreLoad = false;
        setUpHighscoreFriendsTable(arrayList);


    }

    @Override
    public void onGetFacebookHighscoresFriends(ArrayList<VkUser> arrayList) {

    }

    @Override
    public void onGetLeaderboardErrore(String errore) {
        disableProgressBar();
        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {

                Label errorLabel = new Label(GameManager.getStrings().get(Constants.LEADERBOARD_LOAD_ERROR_TEXT), AssetsManager.getUiSkin());
                errorLabel.setFontScale(0.5f, 0.5f);
                errorLabel.setAlignment(Align.center, Align.center);
                table2.add(errorLabel);
                table2.padTop(120f);
                disableProgressBar();
                //pane2.setAmountY(recordAdapter.getMyIndex() * 60 - 145);


            }
        });
    }

    @Override
    public void onGetVkLeaderboardErrore(String errore) {
        disableProgressBar();
        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {

                Label errorLabel = new Label(GameManager.getStrings().get(Constants.LEADERBOARD_LOAD_ERROR_TEXT), AssetsManager.getUiSkin());
                errorLabel.setFontScale(0.5f, 0.5f);
                errorLabel.setAlignment(Align.center, Align.center);
//                table1.add(errorLabel);
//                table1.padTop(20f);
                table1.add(errorLabel);
                table1.padTop(120f);
                disableProgressBar();
                //pane2.setAmountY(recordAdapter.getMyIndex() * 60 - 145);


            }
        });
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
