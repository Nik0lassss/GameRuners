package com.nicholaschirkevich.game.menu;

/**
 * Created by Nikolas on 20.04.2016.
 */

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.nicholaschirkevich.game.GameRuners;
import com.nicholaschirkevich.game.admob.ActionResolver;
import com.nicholaschirkevich.game.interfaces.ResumeButtonListener;
import com.nicholaschirkevich.game.interfaces.UpdateCoinCountInterface;
import com.nicholaschirkevich.game.listeners.OnLoginListenerInterface;
import com.nicholaschirkevich.game.states.CarShopState;
import com.nicholaschirkevich.game.states.CoinShopState;
import com.nicholaschirkevich.game.states.GameStateManager;
import com.nicholaschirkevich.game.states.RecordsMenu;
import com.nicholaschirkevich.game.util.AssetsManager;
import com.nicholaschirkevich.game.util.Constants;
import com.nicholaschirkevich.game.util.GameManager;

/**
 * Created by Nikolas on 10.03.2016.
 */
public class MainMenu extends Group implements UpdateCoinCountInterface {
    public static final String DEFAULT_FONT = "default-font";
    public static final float LABEL_COUNT_FONT_SCALE_X = 0.60f;
    public static final float LABEL_COUNT_FONT_SCALE_Y = 0.60f;
    Texture slot_vehicle;
    Texture speed_text;
    TextButton resumeButton, playOnline, prizeButton;
    ImageButton carShop, coinShop, settingMenu, leaderBoard, leaderBoards;
    Image background;
    Image resumeButtonUpImage, resumeButtonDownImage, playOnlineDownImage, playOnlineUpImage, getPrizeUpButtonImage, getPrizeDownButtonImage, carShopImageUp, carShopImageDown, coinShomImageUp, coinShopImageDown, settingMenuImageUp, settingMenuImageDown, leaderBoardImageUp, leaderBoardImageDown, leaderBoardsImageUp, leaderBoardsImageDown;
    Image imageLogo;
    ResumeButtonListener listener;
    SequenceAction sequence, sequenceCarShop, sequenceSetting, sequenceCoinShop, logoShowSequence;
    GameStateManager gsm;
    Group groupView;
    private ActionResolver actionResolver;
    private ImageButton imageButton;
    private Label labelCoinCount;
    private TextButton socialFbTextButton;
    private TextButton socialVkTextButton;


    public MainMenu(ResumeButtonListener listener, GameStateManager gsm, ActionResolver actionResolver) {


        this.groupView = this;
        this.listener = listener;
        this.actionResolver = actionResolver;


        logoShowSequence = new SequenceAction();


        coinShomImageUp = new Image(AssetsManager.getTextureRegion(Constants.MAIN_MENU_COIN_SHOP_UP_ID));
        coinShopImageDown = new Image(AssetsManager.getTextureRegion(Constants.MAIN_MENU_COIN_SHOP_PRESSERD_ID));

        sequenceSetting = new SequenceAction();
        sequenceCoinShop = new SequenceAction();
        sequence = new SequenceAction();
        sequenceCarShop = new SequenceAction();
        resumeButtonUpImage = new Image(AssetsManager.getTextureRegion(Constants.MAIN_MENU_PLAY_BTTN_UP_ID));
        resumeButtonDownImage = new Image(AssetsManager.getTextureRegion(Constants.MAIN_MENU_PLAY_BTTN_PRESSERD_ID));

        leaderBoardsImageDown = new Image(AssetsManager.getTextureRegion(Constants.MAIN_MENU_LEADERBOARDS_PRESSERD_ID));
        leaderBoardsImageUp = new Image(AssetsManager.getTextureRegion(Constants.MAIN_MENU_LEADERBOARDS_UP_ID));

        leaderBoardImageDown = new Image(AssetsManager.getTextureRegion(Constants.MAIN_MENU_LEADERBOARD_PRESSERD_ID));
        leaderBoardImageUp = new Image(AssetsManager.getTextureRegion(Constants.MAIN_MENU_LEADERBOARD_UP_ID));

        settingMenuImageDown = new Image(AssetsManager.getTextureRegion(Constants.MAIN_MENU_SET_PRESSERD_ID));
        settingMenuImageUp = new Image(AssetsManager.getTextureRegion(Constants.MAIN_MENU_SET_UP_ID));

        playOnlineDownImage = new Image(AssetsManager.getTextureRegion(Constants.MAIN_MENU_MULTIPLAYER_PRESSERD_ID));
        playOnlineUpImage = new Image(AssetsManager.getTextureRegion(Constants.MAIN_MENU_MULTIPLAYER_UP_ID));

        getPrizeDownButtonImage = new Image(AssetsManager.getTextureRegion(Constants.MAIN_MENU_WIN_PRIZE_PRESSERD_ID));
        getPrizeUpButtonImage = new Image(AssetsManager.getTextureRegion(Constants.MAIN_MENU_WIN_PRIZE_UP_ID));

        carShopImageUp = new Image(AssetsManager.getTextureRegion(Constants.MAIN_MENU_CARS_UP_ID));
        carShopImageDown = new Image(AssetsManager.getTextureRegion(Constants.MAIN_MENU_CARS_PRESSERD_ID));
        this.gsm = gsm;

        setUpBackgroung(false);
        setUpResume();
        setUpImageLogo();

        setCarShop();
        setCoinShop();
        setSettingMenu();
        setLeaderBoard();
        setLeadersBoard();

        setUpImageCoinCount();
        setUpCoinCountLabel();
        if (!actionResolver.isSignedIn()) {
            setUpSocialButton();
        } else {
            setUpVkSocialButton();
            setUpFacebookSocialButton();
        }


        setBounds(0, 0, GameRuners.WIDTH / 2, GameRuners.HEIGHT / 2);
    }


    private void setUpImageLogo() {


        imageLogo = new Image(AssetsManager.getTextureRegion(Constants.MAIN_MENU_LOGO_ID));
        imageLogo.setBounds(Constants.LOGO_POSITION_X, Constants.LOGO_POSITION_Y + 200, imageLogo.getWidth(), imageLogo.getHeight());
        MoveToAction moveToAction = new MoveToAction();

        moveToAction.setPosition(Constants.LOGO_POSITION_X, Constants.LOGO_POSITION_Y);
        moveToAction.setDuration(0.8f);
        logoShowSequence.addAction(Actions.delay(0.1f));
        logoShowSequence.addAction(moveToAction);
        imageLogo.addAction(logoShowSequence);
        addActor(imageLogo);
    }

//


    private void setUpSocialButton() {
        TextButton.TextButtonStyle socialTextButtonStyle = new TextButton.TextButtonStyle();
        socialTextButtonStyle.up = new Image(AssetsManager.getTextureRegion(Constants.BUTTON_SOCIAL_GOOGLE_ID).getTexture()).getDrawable();
        socialTextButtonStyle.down = new Image(AssetsManager.getTextureRegion(Constants.BUTTON_SOCIAL_GOOGLE_PRESSED_ID).getTexture()).getDrawable();
        socialTextButtonStyle.font = AssetsManager.getUiSkin().getFont("default-font");


        Texture texture = AssetsManager.getTextureRegion(Constants.FOR_SAVE_ME_BUTTON_100_ID).getTexture();
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Image bonusImage = new Image(texture);
        bonusImage.setPosition(105, 10);

        final TextButton socialTextButton = new TextButton(GameManager.getStrings().get(Constants.MS_SIGN_IN_LBL), socialTextButtonStyle);
        socialTextButton.addActor(bonusImage);
        socialTextButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                super.touchDown(event, x, y, pointer, button);
                actionResolver.signIn(new OnLoginListenerInterface() {
                    @Override
                    public void onLoginFb() {

                    }

                    @Override
                    public void onLoginVk() {

                    }

                    @Override
                    public void onLoginGoogle() {
                        socialTextButton.remove();
                        GameManager.addCoin(100);
                        updateCoinCountView();
                    }

                    @Override
                    public void onLoginGoogleError() {

                    }
                });
                return true;
            }
        });
        socialTextButton.padLeft(30f);
        socialTextButton.getLabel().setFontScale(0.45f, 0.45f);
        socialTextButton.setPosition(Constants.SOCIAL_BTTN_X_VISIBLE - socialTextButton.getWidth() / 2, 110);
        addActor(socialTextButton);
    }

    private void setUpVkSocialButton() {
        TextButton.TextButtonStyle socialTextButtonStyle = new TextButton.TextButtonStyle();
        socialTextButtonStyle.up = new Image(AssetsManager.getTextureRegion(Constants.BTTN_VK_ID).getTexture()).getDrawable();
        socialTextButtonStyle.down = new Image(AssetsManager.getTextureRegion(Constants.BTTN_VK_PRESSED_ID).getTexture()).getDrawable();
        socialTextButtonStyle.font = AssetsManager.getUiSkin().getFont("default-font");


        Texture texture = AssetsManager.getTextureRegion(Constants.FOR_SAVE_ME_BUTTON_100_ID).getTexture();
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Image bonusImage = new Image(texture);
        bonusImage.setPosition(105, 10);

        socialVkTextButton = new TextButton(GameManager.getStrings().get(Constants.MS_SIGN_IN_LBL), socialTextButtonStyle);
        socialVkTextButton.addActor(bonusImage);
        socialVkTextButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                super.touchDown(event, x, y, pointer, button);
                actionResolver.showVkLoginActivity(new OnLoginListenerInterface() {
                    @Override
                    public void onLoginFb() {

                    }

                    @Override
                    public void onLoginVk() {
                        socialFbTextButton.remove();
                        socialVkTextButton.remove();
                        GameManager.addCoin(100);
                        updateCoinCountView();

                    }

                    @Override
                    public void onLoginGoogle() {

                    }

                    @Override
                    public void onLoginGoogleError() {

                    }
                });

                return true;
            }
        });
        socialVkTextButton.padLeft(30f);
        socialVkTextButton.getLabel().setFontScale(0.45f, 0.45f);
        socialVkTextButton.setPosition(Constants.SOCIAL_BTTN_X_VISIBLE - socialVkTextButton.getWidth() / 2, 110);
        addActor(socialVkTextButton);
    }

    private void setUpFacebookSocialButton() {
        TextButton.TextButtonStyle socialTextButtonStyle = new TextButton.TextButtonStyle();
        socialTextButtonStyle.up = new Image(AssetsManager.getTextureRegion(Constants.BTTN_FACEBOOK_ID).getTexture()).getDrawable();
        socialTextButtonStyle.down = new Image(AssetsManager.getTextureRegion(Constants.BTTN_FACEBOOK_PRESSED_ID).getTexture()).getDrawable();
        socialTextButtonStyle.font = AssetsManager.getUiSkin().getFont("default-font");


        Texture texture = AssetsManager.getTextureRegion(Constants.FOR_SAVE_ME_BUTTON_100_ID).getTexture();
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Image bonusImage = new Image(texture);
        bonusImage.setPosition(105, 10);

        socialFbTextButton = new TextButton(GameManager.getStrings().get(Constants.MS_SIGN_IN_LBL), socialTextButtonStyle);
        socialFbTextButton.addActor(bonusImage);
        socialFbTextButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                super.touchDown(event, x, y, pointer, button);
                actionResolver.singInFb(new OnLoginListenerInterface() {
                    @Override
                    public void onLoginFb() {
                        socialVkTextButton.remove();
                        socialFbTextButton.remove();
                        GameManager.addCoin(100);
                        updateCoinCountView();
                    }

                    @Override
                    public void onLoginVk() {

                    }

                    @Override
                    public void onLoginGoogle() {

                    }

                    @Override
                    public void onLoginGoogleError() {

                    }
                });

                return true;
            }
        });
        socialFbTextButton.padLeft(30f);
        socialFbTextButton.getLabel().setFontScale(0.45f, 0.45f);
        socialFbTextButton.setPosition(Constants.SOCIAL_BTTN_X_VISIBLE - socialFbTextButton.getWidth() / 2, 185);
        addActor(socialFbTextButton);
    }

    private void setUpBackgroung(boolean selected) {
        if (selected) {
            slot_vehicle = AssetsManager.getTextureRegion(Constants.SLOT_VEHICLE_SELECTED_ID).getTexture();
        } else
            slot_vehicle = AssetsManager.getTextureRegion(Constants.SLOT_VEHICLE_ID).getTexture();
        background = new Image(slot_vehicle);
        background.setBounds(0, -20, GameRuners.WIDTH / 2, GameRuners.HEIGHT / 2 + 50);
        addActor(background);

    }

//


    public void setUpImageCoinCount() {
        imageButton = new ImageButton(new Image(AssetsManager.getTextureRegion(Constants.COIN_ICON_1_NAME_ID)).getDrawable());

        imageButton.setBounds(GameRuners.WIDTH / 2 - 25, GameRuners.HEIGHT / 2 - 30, imageButton.getWidth(), imageButton.getHeight());
        addActor(imageButton);
    }

    public void setUpCoinCountLabel() {
        labelCoinCount = new Label(String.valueOf(GameManager.getCoinCounter()), AssetsManager.getUiSkin());
        labelCoinCount.setFontScale(LABEL_COUNT_FONT_SCALE_X, LABEL_COUNT_FONT_SCALE_Y);
        labelCoinCount.setBounds(imageButton.getX() - labelCoinCount.getPrefWidth() - 5, imageButton.getY(), labelCoinCount.getWidth(), labelCoinCount.getHeight());


        labelCoinCount.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                super.touchDown(event, x, y, pointer, button);
                getStage().addActor(new CoinShopState(gsm, actionResolver, groupView));
                groupView.remove();
                return true;
            }
        });
        addActor(labelCoinCount);
    }

    private void setCarShop() {

        float x = Constants.CAR_SHOP_BTTN_X_VISIBLE, y = Constants.CAR_SHOP_BTTN_Y_VISIBLE, width = 70, height = 55;

        carShop = new ImageButton(carShopImageUp.getDrawable(), carShopImageDown.getDrawable());
        carShop.setBounds(x - carShop.getWidth() / 2, y - carShop.getHeight() / 2, carShop.getWidth(), carShop.getHeight());
        carShop.addListener(new ClickListener() {

                                @Override
                                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                                    AssetsManager.playSound(Constants.SOUND_CLICK);
                                    sequenceCarShop.addAction(Actions.delay(0.3f));
                                    sequenceCarShop.addAction(new Action() {
                                        @Override
                                        public boolean act(float delta) {
                                            gsm.push(new CarShopState(gsm, actionResolver));
                                            return true;
                                        }
                                    });
                                    sequenceCarShop.addAction(Actions.removeActor());
                                    addAction(sequenceCarShop);

                                    return true;
                                }
                            }
        );
        addActor(carShop);

    }

    private void setCoinShop() {

        float x = Constants.COIN_SHOP_BTTN_X_VISIBLE, y = Constants.COIN_SHOP_BTTN_Y_VISIBLE;

        coinShop = new ImageButton(coinShomImageUp.getDrawable(), coinShopImageDown.getDrawable());
        coinShop.setBounds(x - coinShomImageUp.getWidth() / 2, y - coinShomImageUp.getHeight() / 2, coinShomImageUp.getWidth(), coinShomImageUp.getHeight());
        coinShop.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                AssetsManager.playSound(Constants.SOUND_CLICK);
                // sequenceCoinShop.addAction(Actions.delay(0.1f));
                sequenceCoinShop.addAction(new Action() {
                    @Override
                    public boolean act(float delta) {

                        getStage().addActor(new CoinShopState(gsm, actionResolver, groupView));
                        return true;
                    }
                });
                sequenceCoinShop.addAction(Actions.removeActor(groupView));
                coinShop.addAction(sequenceCoinShop);
                return true;
            }
        });
        addActor(coinShop);


    }

    private void setSettingMenu() {

        float x = Constants.SETTING_BTTN_X_VISIBLE, y = Constants.SETTING_BTTN_Y_VISIBLE, width = 70, height = 55;

        settingMenu = new ImageButton(settingMenuImageUp.getDrawable(), settingMenuImageDown.getDrawable());
        settingMenu.setBounds(x - settingMenuImageDown.getWidth() / 2, y - settingMenuImageDown.getHeight() / 2, settingMenuImageDown.getWidth(), settingMenuImageDown.getHeight());

        settingMenu.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                AssetsManager.playSound(Constants.SOUND_CLICK);
                sequenceSetting.addAction(Actions.delay(0.1f));

                sequenceSetting.addAction(new Action() {
                    @Override
                    public boolean act(float delta) {
                        getStage().addActor(new MenuSetting(listener, gsm, actionResolver, groupView));
                        return true;
                    }
                });
                sequenceSetting.addAction(Actions.removeActor(groupView));
                settingMenu.addAction(sequenceSetting);
                return true;
            }
        });
        addActor(settingMenu);

    }

    private void setLeaderBoard() {

        float x = Constants.LEADERBOARD_BTTN_X_VISIBLE, y = Constants.LEADERBOARD_BTTN_Y_VISIBLE, width = 70, height = 55;

        leaderBoard = new ImageButton(leaderBoardImageUp.getDrawable(), leaderBoardImageDown.getDrawable());
        leaderBoard.setBounds(x - leaderBoardImageDown.getWidth() / 2, y - leaderBoardImageDown.getHeight() / 2, leaderBoardImageDown.getWidth(), leaderBoardImageDown.getHeight());
        leaderBoard.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                super.touchDown(event, x, y, pointer, button);
                actionResolver.showScore();
                return true;
            }
        });
        addActor(leaderBoard);

    }

    private void setLeadersBoard() {

        float x = Constants.LEADERBOARDS_BTTN_X_VISIBLE, y = Constants.LEADERBOARDS_BTTN_Y_VISIBLE, width = 70, height = 55;

        leaderBoards = new ImageButton(leaderBoardsImageUp.getDrawable(), leaderBoardsImageDown.getDrawable());
        leaderBoards.setBounds(x - leaderBoardsImageDown.getWidth() / 2, y - leaderBoardsImageDown.getHeight() / 2, leaderBoardsImageDown.getWidth(), leaderBoardsImageDown.getHeight());
        leaderBoards.addListener(new ClickListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                AssetsManager.playSound(Constants.SOUND_CLICK);
//
                super.touchDown(event, x, y, pointer, button);
                addActor(new RecordsMenu(gsm, actionResolver, groupView));


                return true;
            }
        });

        addActor(leaderBoards);

    }


    private void setUpResume() {

        float x = Constants.RESUME_BTTN_X_VISIBLE, y = Constants.RESUME_BTTN_Y_VISIBLE, width = 70, height = 55;
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.down = resumeButtonDownImage.getDrawable();
        textButtonStyle.up = resumeButtonUpImage.getDrawable();
        textButtonStyle.font = AssetsManager.getUiSkin().getFont(DEFAULT_FONT);

        resumeButton = new TextButton(GameManager.getStrings().get(Constants.MP_PLAY_BTN), textButtonStyle);
        resumeButton.getLabel().setFontScale(0.55f, 0.55f);
        resumeButton.getLabelCell().padLeft(35f);


        resumeButton.setBounds(x - resumeButtonUpImage.getWidth() / 2, y - resumeButtonUpImage.getHeight() / 2, resumeButtonUpImage.getWidth(), resumeButtonUpImage.getHeight());

        resumeButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                AssetsManager.playSound(Constants.SOUND_CLICK);
                sequence.addAction(Actions.delay(0.3f));
                sequence.addAction(new Action() {
                    @Override
                    public boolean act(float delta) {

                        listener.resumeButtonOnResume();
                        return true;
                    }
                });
                sequence.addAction(Actions.removeActor());
                addAction(sequence);

                return true;
            }
        });

        addActor(resumeButton);

    }


    @Override
    public void act(float delta) {
        super.act(delta);
    }


    @Override
    public void updateCoinCountView() {
        labelCoinCount.setText(String.valueOf(GameManager.getCoinCounter()));
        labelCoinCount.invalidate();

        labelCoinCount.setBounds(imageButton.getX() - labelCoinCount.getPrefWidth() - 5, imageButton.getY(), labelCoinCount.getWidth(), labelCoinCount.getHeight());

    }
}
