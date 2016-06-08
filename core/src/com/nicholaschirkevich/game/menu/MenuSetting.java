package com.nicholaschirkevich.game.menu;

/**
 * Created by Nikolas on 20.04.2016.
 */

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.nicholaschirkevich.game.GameRuners;
import com.nicholaschirkevich.game.admob.ActionResolver;
import com.nicholaschirkevich.game.interfaces.ResumeButtonListener;
import com.nicholaschirkevich.game.states.GameStateManager;
import com.nicholaschirkevich.game.util.AssetsManager;
import com.nicholaschirkevich.game.util.Constants;
import com.nicholaschirkevich.game.util.GameManager;

/**
 * Created by Nikolas on 10.03.2016.
 */
public class MenuSetting extends Group {
    Texture slot_vehicle;
    Texture speed_bar;
    TextButton soundButton, swipe_controll_button, block_rds_button, restore_button, singInVkBttn, singInFbBttn, backBttn;
    Image background;
    Image soundButtonUpImage, soundButtonDownImage, playOnlineDownImage, playOnlineUpImage, getPrizeUpButtonImage, getPrizeDownButtonImage, carShopImageUp, carShopImageDown, coinShomImageUp, coinShopImageDown, settingMenuImageUp, settingMenuImageDown, leaderBoardImageUp, leaderBoardImageDown, leaderBoardsImageUp, leaderBoardsImageDown;
    Texture sound_on, sound_off;
    Image sound_on_image, sound_off_image, backButtonImageUp, backButtonImageDown;
    Image swipe_image, rds_image, restore_image, singInVkImage, singInFbImage;
    Texture swipe_texture, rds_texture, restore_texture, singInVk, singInFb, backButtonTextureUp, backButtonTextureDown;
    Texture soundButtonUp, soundButtonDown, playOnlineDownImageTexture, playOnlineUpImageTexture, getPrizeUpButtonImageTexture, getPrizeDownButtonImageTexture, carShopTextureUp, carShopTextureDown, coinShopTextureUp, coinShopTextureDown, settingMenuTextureUp, settingMenuTextureDown, leaderBoardTextureUp, leaderBoardTextureDown, leaderBoardsTextureUp, leaderBoardsTextureDown;
    Image imageLogo;
    ResumeButtonListener listener;
    SequenceAction sequence, sequenceCarShop, swipeButtonSequence, sequenceReturn;
    GameStateManager gsm;
    ResumeButtonListener listenerResume;
    private ActionResolver actionResolver;
    private int addWidth = 5;
    private Group parentView;

    public MenuSetting(ResumeButtonListener listenerResume, GameStateManager gsm, ActionResolver actionResolver, Group parentView) {

        this.parentView = parentView;
        this.listenerResume = listenerResume;
        this.actionResolver = actionResolver;
        soundButtonUp = AssetsManager.getTextureRegion(Constants.BTTN_BLUE_ID).getTexture();
        soundButtonDown = AssetsManager.getTextureRegion(Constants.BTTN_BLUE_PRESSED_ID).getTexture();
        sound_on = AssetsManager.getTextureRegion(Constants.SOUND_ON_ID).getTexture();
        sound_off = AssetsManager.getTextureRegion(Constants.SOUND_OFF_ID).getTexture();
        sound_on_image = new Image(sound_on);
        sound_off_image = new Image(sound_off);

        sequenceReturn = new SequenceAction();


        sequence = new SequenceAction();
        swipeButtonSequence = new SequenceAction();

        soundButtonUpImage = new Image(soundButtonUp);
        soundButtonDownImage = new Image(soundButtonDown);

        swipe_texture = AssetsManager.getTextureRegion(Constants.ICON_CONTROL_SETTING_ID).getTexture();
        swipe_image = new Image(swipe_texture);

        rds_texture = AssetsManager.getTextureRegion(Constants.ICON_BLOCK_ADS_ID).getTexture();
        rds_image = new Image(rds_texture);

        restore_texture = AssetsManager.getTextureRegion(Constants.ICON_RETURN_P_ID).getTexture();
        restore_image = new Image(restore_texture);

        singInVk = AssetsManager.getTextureRegion(Constants.ICON_ICON_VK_ID).getTexture();
        singInFb = AssetsManager.getTextureRegion(Constants.ICON_ICON_FB_ID).getTexture();

        singInFbImage = new Image(singInFb);
        singInVkImage = new Image(singInVk);

        backButtonTextureDown = AssetsManager.getTextureRegion(Constants.BACK_BUTTON_PRESSED_ID).getTexture();
        backButtonTextureUp = AssetsManager.getTextureRegion(Constants.BACK_BUTTON_ID).getTexture();

        backButtonImageDown = new Image(backButtonTextureDown);
        backButtonImageUp = new Image(backButtonTextureUp);

        this.gsm = gsm;


        setUpBackgroung();
        setUpSound();
        setUpImageLogo();
        setUpControll();
        setUpBlockRds();
        setUpRestore();
        setUpSingInVk();
        setUpSingInFb();
        setUpBackButton();

        setBounds(0, 0, GameRuners.WIDTH / 2, GameRuners.HEIGHT / 2);
    }


    private void setUpImageLogo() {


        imageLogo = new Image(AssetsManager.getTextureRegion(Constants.SETTINGS_ID));
        imageLogo.setBounds(Constants.SETTING_LOGO_POSITION_X, Constants.SETTING_LOGO_POSITION_Y, imageLogo.getWidth() + addWidth, imageLogo.getHeight());
        addActor(imageLogo);
    }


    private void setUpBackgroung() {

        background = new Image(AssetsManager.getTextureRegion(Constants.BACK_TILE_ID));
        Color color = background.getColor();
        background.setColor(color.r, color.g, color.b, 0.5f);
        background.setBounds(0, -20, GameRuners.WIDTH / 2, GameRuners.HEIGHT / 2 + 50);
        addActor(background);

    }


    private void setUpSound() {

        float x = Constants.SOUND_BTTN_X_VISIBLE, y = Constants.SOUND_BTTN_Y_VISIBLE, width = 70, height = 55;
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.down = soundButtonDownImage.getDrawable();
        textButtonStyle.up = soundButtonUpImage.getDrawable();
        textButtonStyle.font = AssetsManager.getUiSkin().getFont("default-font");
        if (GameManager.isSoundEnable())
            soundButton = new TextButton(GameManager.getStrings().get(Constants.SETTINGS_SOUND_ON_LBL), textButtonStyle);
        else
            soundButton = new TextButton(GameManager.getStrings().get(Constants.SETTINGS_SOUND_OFF_LBL), textButtonStyle);
        soundButton.getLabel().setFontScale(0.4f, 0.4f);
        soundButton.getLabelCell().padLeft(25f);

        sound_on_image.setPosition(soundButton.getX() + 5, soundButton.getY() + 13);
        soundButton.addActor(sound_on_image);


        soundButton.setBounds(x - soundButtonUpImage.getWidth() / 2, y - soundButtonUpImage.getHeight() / 2, soundButtonUpImage.getWidth() + addWidth, soundButtonUpImage.getHeight());

        soundButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                AssetsManager.playSound(Constants.SOUND_CLICK);
                if (GameManager.isSoundEnable()) {
                    soundButton.setText(GameManager.getStrings().get(Constants.SETTINGS_SOUND_OFF_LBL));
                    GameManager.setIsSoundEnable(false);
                } else {
                    soundButton.setText(GameManager.getStrings().get(Constants.SETTINGS_SOUND_ON_LBL));
                    GameManager.setIsSoundEnable(true);
                }
                sequence.addAction(Actions.delay(0.3f));
                sequence.addAction(new Action() {
                    @Override
                    public boolean act(float delta) {
                        return true;
                    }
                });
                //sequence.addAction(Actions.removeActor());
                addAction(sequence);

                return true;
            }
        });

        addActor(soundButton);

    }

    private void setUpControll() {

        float x = Constants.CONTROL_BTTN_X_VISIBLE, y = Constants.CONTROL_BTTN_Y_VISIBLE, width = 70, height = 55;
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.down = soundButtonDownImage.getDrawable();
        textButtonStyle.up = soundButtonUpImage.getDrawable();
        textButtonStyle.font = AssetsManager.getUiSkin().getFont("default-font");

//        swipe_controll_button = new TextButton("Swipe game \n control", textButtonStyle);
        if (!GameManager.isTouchControl())
            swipe_controll_button = new TextButton(GameManager.getStrings().get(Constants.SETTINGS_TAP_CONTROLL_FIRST_ROW_LBL) + "\n" + GameManager.getStrings().get(Constants.SETTINGS_TAP_CONTROLL_SECOND_ROW_LBL), textButtonStyle);
        else {
            swipe_controll_button = new TextButton(GameManager.getStrings().get(Constants.SETTINGS_SWIPE_CONTROLL_FIRST_ROW_LBL) + "\n" + GameManager.getStrings().get(Constants.SETTINGS_SWIPE_CONTROLL_SECOND_ROW_LBL), textButtonStyle);
        }
        swipe_controll_button.getLabel().setFontScale(0.4f, 0.4f);
        swipe_controll_button.getLabelCell().padLeft(25f);

        swipe_image.setPosition(getX() + 5, getY() + 13);
        swipe_controll_button.addActor(swipe_image);


        swipe_controll_button.setBounds(x - soundButtonUpImage.getWidth() / 2, y - soundButtonUpImage.getHeight() / 2, soundButtonUpImage.getWidth() + addWidth, soundButtonUpImage.getHeight());

        swipe_controll_button.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                swipeButtonSequence.addAction(Actions.delay(0.3f));
                swipeButtonSequence.addAction(new Action() {
                                                  @Override
                                                  public boolean act(float delta) {
                                                      AssetsManager.playSound(Constants.SOUND_CLICK);
                                                      if (GameManager.isTouchControl()) {
                                                          GameManager.setIsTouchControl(false);
                                                          swipe_controll_button.setText(GameManager.getStrings().get(Constants.SETTINGS_TAP_CONTROLL_FIRST_ROW_LBL) + "\n" + GameManager.getStrings().get(Constants.SETTINGS_TAP_CONTROLL_SECOND_ROW_LBL));
                                                      } else {
                                                          swipe_controll_button.setText(GameManager.getStrings().get(Constants.SETTINGS_SWIPE_CONTROLL_FIRST_ROW_LBL) + "\n" + GameManager.getStrings().get(Constants.SETTINGS_SWIPE_CONTROLL_SECOND_ROW_LBL));
                                                          GameManager.setIsTouchControl(true);
                                                      }

                                                      //listener.resumeButtonOnResume();
                                                      return true;
                                                  }
                                              }

                );

                //swipeButtonSequence.addAction(Actions.removeActor());
                addAction(swipeButtonSequence);

                return true;
            }
        });

        addActor(swipe_controll_button);

    }

    private void setUpBlockRds() {

        float x = Constants.BLOCK_BTTN_X_VISIBLE, y = Constants.BLOCK_BTTN_Y_VISIBLE, width = 70, height = 55;
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.down = soundButtonDownImage.getDrawable();
        textButtonStyle.up = soundButtonUpImage.getDrawable();
        textButtonStyle.font = AssetsManager.getUiSkin().getFont("default-font");

        block_rds_button = new TextButton(GameManager.getStrings().get(Constants.SETTINGS_BLOCK_LBL) + "\n" + GameManager.getStrings().get(Constants.SETTINGS_ADS_LBL), textButtonStyle);
        block_rds_button.getLabel().setFontScale(0.4f, 0.4f);
        block_rds_button.getLabelCell().padLeft(25f);

        rds_image.setPosition(getX() + 5, getY() + 13);
        block_rds_button.addActor(rds_image);


        block_rds_button.setBounds(x - soundButtonUpImage.getWidth() / 2, y - soundButtonUpImage.getHeight() / 2, soundButtonUpImage.getWidth() + addWidth, soundButtonUpImage.getHeight());

        block_rds_button.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                AssetsManager.playSound(Constants.SOUND_CLICK);
                swipeButtonSequence.addAction(Actions.delay(0.3f));
                swipeButtonSequence.addAction(new Action() {
                    @Override
                    public boolean act(float delta) {
                        //listener.resumeButtonOnResume();
                        return true;
                    }
                });
                //swipeButtonSequence.addAction(Actions.removeActor());
                addAction(swipeButtonSequence);

                return true;
            }
        });

        addActor(block_rds_button);

    }

    private void setUpRestore() {

        float x = Constants.RESTORE_BTTN_X_VISIBLE, y = Constants.RESTORE_BTTN_Y_VISIBLE, width = 70, height = 55;
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.down = soundButtonDownImage.getDrawable();
        textButtonStyle.up = soundButtonUpImage.getDrawable();
        textButtonStyle.font = AssetsManager.getUiSkin().getFont("default-font");

        restore_button = new TextButton(GameManager.getStrings().get(Constants.SETTINGS_PURCHASE_LBL), textButtonStyle);
        restore_button.getLabel().setFontScale(0.4f, 0.4f);
        restore_button.getLabelCell().padLeft(25f);

        restore_image.setPosition(getX() + 5, getY() + 13);
        restore_button.addActor(restore_image);


        restore_button.setBounds(x - soundButtonUpImage.getWidth() / 2, y - soundButtonUpImage.getHeight() / 2, soundButtonUpImage.getWidth() + addWidth, soundButtonUpImage.getHeight());

        restore_button.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                AssetsManager.playSound(Constants.SOUND_CLICK);
                swipeButtonSequence.addAction(Actions.delay(0.3f));
                swipeButtonSequence.addAction(new Action() {
                    @Override
                    public boolean act(float delta) {
                        //listener.resumeButtonOnResume();
                        return true;
                    }
                });
                //swipeButtonSequence.addAction(Actions.removeActor());
                addAction(swipeButtonSequence);

                return true;
            }
        });

        addActor(restore_button);

    }


    private void setUpSingInVk() {

        float x = Constants.SING_IN_VK_BTTN_X_VISIBLE, y = Constants.SING_IN_VK_Y_VISIBLE, width = 70, height = 55;
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.down = soundButtonDownImage.getDrawable();
        textButtonStyle.up = soundButtonUpImage.getDrawable();
        textButtonStyle.font = AssetsManager.getUiSkin().getFont("default-font");
        if (actionResolver.isVkLogin())
            singInVkBttn = new TextButton(GameManager.getStrings().get(Constants.SETTINGS_VK_LOGOUT_LBL), textButtonStyle);
        else
            singInVkBttn = new TextButton(GameManager.getStrings().get(Constants.MS_SIGN_IN_LBL), textButtonStyle);
        singInVkBttn.getLabel().setFontScale(0.4f, 0.4f);
        singInVkBttn.getLabelCell().padLeft(25f);

        singInVkImage.setPosition(getX() + 5, getY() + 13);
        singInVkBttn.addActor(singInVkImage);


        singInVkBttn.setBounds(x, y - singInVkBttn.getHeight() / 2, 158 + addWidth, singInVkBttn.getHeight());

        singInVkBttn.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                AssetsManager.playSound(Constants.SOUND_CLICK);
                swipeButtonSequence.addAction(Actions.delay(0.3f));
                swipeButtonSequence.addAction(new Action() {
                    @Override
                    public boolean act(float delta) {
                        if (actionResolver.isVkLogin()) {

                            actionResolver.vkLogout();

                            if (actionResolver.isAvailibleInternet()) {
                                singInVkBttn.setText(GameManager.getStrings().get(Constants.MS_SIGN_IN_LBL));
                                setUpSingInFb();
                            }
                        } else {
                            actionResolver.showVkLoginActivity();
                            singInFbBttn.remove();
                            singInVkBttn.setText(GameManager.getStrings().get(Constants.SETTINGS_VK_LOGOUT_LBL));
                        }

                        //listener.resumeButtonOnResume();
                        return true;
                    }
                });
                //swipeButtonSequence.addAction(Actions.removeActor());
                addAction(swipeButtonSequence);

                return true;
            }
        });

        addActor(singInVkBttn);

    }

    private void setUpSingInFb() {

        if (!actionResolver.isVkLogin()) {
            float x = Constants.SING_IN_FB_BTTN_X_VISIBLE, y = Constants.SING_IN_FB_Y_VISIBLE, width = 70, height = 55;
            TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
            textButtonStyle.down = soundButtonDownImage.getDrawable();
            textButtonStyle.up = soundButtonUpImage.getDrawable();
            textButtonStyle.font = AssetsManager.getUiSkin().getFont("default-font");

            singInFbBttn = new TextButton(GameManager.getStrings().get(Constants.MS_SIGN_IN_LBL), textButtonStyle);
            singInFbBttn.getLabel().setFontScale(0.4f, 0.4f);
            singInFbBttn.getLabelCell().padLeft(25f);

            singInFbImage.setPosition(getX() + 5, getY() + 13);
            singInFbBttn.addActor(singInFbImage);


            singInFbBttn.setBounds(x, y - singInFbBttn.getHeight() / 2, 158 + addWidth, singInFbBttn.getHeight());

            singInFbBttn.addListener(new ClickListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    AssetsManager.playSound(Constants.SOUND_CLICK);

                    swipeButtonSequence.addAction(Actions.delay(0.3f));
                    swipeButtonSequence.addAction(new Action() {
                        @Override
                        public boolean act(float delta) {
                            //listener.resumeButtonOnResume();
                            return true;
                        }
                    });
                    //swipeButtonSequence.addAction(Actions.removeActor());
                    addAction(swipeButtonSequence);

                    return true;
                }
            });

            addActor(singInFbBttn);
        }
    }

    private void setUpBackButton() {

        float x = Constants.BACK_BTTN_X_VISIBLE, y = Constants.BACK_BTTN_Y_VISIBLE, width = 70, height = 55;
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
                AssetsManager.playSound(Constants.SOUND_CLICK);
                sequenceReturn.addAction(Actions.delay(0.1f));
                sequenceReturn.addAction(new Action() {
                    @Override
                    public boolean act(float delta) {

                        getStage().addActor(parentView);
                        // getStage().addActor(new MenuTest(listenerResume, gsm, actionResolver));
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


    private void setUpIconSpeedBar() {

        Image iconSpeedBar = new Image(speed_bar);
        iconSpeedBar.setBounds(getX() + 93, getY() + 30, 90, 8);
        addActor(iconSpeedBar);
    }


    @Override
    public void act(float delta) {
        super.act(delta);
    }


}
