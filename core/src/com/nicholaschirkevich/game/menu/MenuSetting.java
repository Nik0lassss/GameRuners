package com.nicholaschirkevich.game.menu;

/**
 * Created by Nikolas on 20.04.2016.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.nicholaschirkevich.game.GameRuners;
import com.nicholaschirkevich.game.interfaces.ResumeButtonListener;
import com.nicholaschirkevich.game.states.GameStateManager;
import com.nicholaschirkevich.game.states.GarageState;
import com.nicholaschirkevich.game.util.AssetsManager;
import com.nicholaschirkevich.game.util.Constants;

/**
 * Created by Nikolas on 10.03.2016.
 */
public class MenuSetting extends Group {
    Texture slot_vehicle;
    Skin uiSkin = new Skin(Gdx.files.internal("uiskin_digit.json"));
    Texture speed_text;
    Texture speed_bar;
    Texture weight_text;
    Texture weight_bar;
    Texture delimiterTexture;
    TextButton soundButton, swipe_controll_button, block_rds_button,restore_button,singInVkBttn,singInFbBttn, backBttn;
    Image background;
    Image soundButtonUpImage, soundButtonDownImage, playOnlineDownImage, playOnlineUpImage, getPrizeUpButtonImage, getPrizeDownButtonImage, carShopImageUp, carShopImageDown, coinShomImageUp, coinShopImageDown, settingMenuImageUp, settingMenuImageDown, leaderBoardImageUp, leaderBoardImageDown, leaderBoardsImageUp, leaderBoardsImageDown;
    Texture sound_on, sound_off;
    Image sound_on_image, sound_off_image,backButtonImageUp,backButtonImageDown;
    Image swipe_image, rds_image,restore_image, singInVkImage,singInFbImage;
    Texture swipe_texture,rds_texture, restore_texture,singInVk,singInFb,backButtonTextureUp,backButtonTextureDown;
    Texture soundButtonUp, soundButtonDown, playOnlineDownImageTexture, playOnlineUpImageTexture, getPrizeUpButtonImageTexture, getPrizeDownButtonImageTexture, carShopTextureUp, carShopTextureDown, coinShopTextureUp, coinShopTextureDown, settingMenuTextureUp, settingMenuTextureDown, leaderBoardTextureUp, leaderBoardTextureDown, leaderBoardsTextureUp, leaderBoardsTextureDown;
    Image imageLogo;
    ResumeButtonListener listener;
    SequenceAction sequence, sequenceCarShop,swipeButtonSequence, sequenceReturn;
    GameStateManager gsm;
    ResumeButtonListener listenerResume;


    public MenuSetting(ResumeButtonListener listenerResume, GameStateManager gsm) {


        //car_texture = new  Texture("other_car_2_1.png");
        this.listener = listener;
        this.listenerResume = listenerResume;
        soundButtonUp = new Texture("bttn_blue.png");
        soundButtonDown = new Texture("bttn_blue_prssd.png");
        playOnlineDownImageTexture = new Texture("button_multiplayer_pressed.png");
        playOnlineUpImageTexture = new Texture("button_multiplayer.png");
        getPrizeUpButtonImageTexture = new Texture("button_win_a_prize.png");
        getPrizeDownButtonImageTexture = new Texture("button_win_a_prize_pressed.png");
        carShopTextureUp = new Texture("bttn_cars.png");
        carShopTextureDown = new Texture("bttn_cars_prssd.png");
        sound_on = new Texture("icon_sound_on.png");
        sound_off = new Texture("icon_sound_off.png");
        sound_on_image = new Image(sound_on);
        sound_off_image = new Image(sound_off);

        sequenceReturn = new SequenceAction();
        coinShopTextureDown = new Texture("bttn_coins_prssd.png");
        coinShopTextureUp = new Texture("bttn_coins.png");

        leaderBoardsTextureDown = new Texture("bttn_leaderboards_prssd.png");
        leaderBoardsTextureUp = new Texture("bttn_leaderboards.png");

        leaderBoardTextureDown = new Texture("bttn_leaderbord_pressed.png");
        leaderBoardTextureUp = new Texture("bttn_leaderboard.png");

        settingMenuTextureDown = new Texture("bttn_set_prssd.png");
        settingMenuTextureUp = new Texture("bttn_set.png");

        coinShomImageUp = new Image(coinShopTextureUp);
        coinShopImageDown = new Image(coinShopTextureDown);

        sequence = new SequenceAction();
        swipeButtonSequence = new SequenceAction();
        sequenceCarShop = new SequenceAction();
        soundButtonUpImage = new Image(soundButtonUp);
        soundButtonDownImage = new Image(soundButtonDown);

        leaderBoardsImageDown = new Image(leaderBoardsTextureDown);
        leaderBoardsImageUp = new Image(leaderBoardsTextureUp);

        leaderBoardImageDown = new Image(leaderBoardTextureDown);
        leaderBoardImageUp = new Image(leaderBoardTextureUp);

        settingMenuImageDown = new Image(settingMenuTextureDown);
        settingMenuImageUp = new Image(settingMenuTextureUp);

        playOnlineDownImage = new Image(playOnlineDownImageTexture);
        playOnlineUpImage = new Image(playOnlineUpImageTexture);

        getPrizeDownButtonImage = new Image(getPrizeDownButtonImageTexture);
        getPrizeUpButtonImage = new Image(getPrizeUpButtonImageTexture);

        carShopImageUp = new Image(carShopTextureUp);
        carShopImageDown = new Image(carShopTextureDown);

        swipe_texture = new Texture("icon_control_setting.png");
        swipe_image = new Image(swipe_texture);

        rds_texture = new Texture("icon_block_ads.png");
        rds_image = new Image(rds_texture);

        restore_texture = new Texture("icon_return_p.png");
        restore_image = new Image(restore_texture);

        singInVk = new Texture("icon_vk.png");
        singInFb = new Texture("icon_fb.png");

        singInFbImage = new Image(singInFb);
        singInVkImage = new Image(singInVk);

        backButtonTextureDown = new Texture("bttn_back_prssd.png");
        backButtonTextureUp = new Texture("bttn_back.png");

        backButtonImageDown =new Image(backButtonTextureDown);
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

        Texture logo = new Texture("settings.png");
        imageLogo = new Image(logo);
        imageLogo.setBounds(Constants.SETTING_LOGO_POSITION_X, Constants.SETTING_LOGO_POSITION_Y, imageLogo.getWidth(), imageLogo.getHeight());
        addActor(imageLogo);
    }


    private void setUpBackgroung() {

        slot_vehicle = new Texture("back_tile.png");
        background = new Image(slot_vehicle);
        Color color = background.getColor();
        background.setColor(color.r,color.g,color.b,0.5f);
        background.setBounds(0, -20, GameRuners.WIDTH / 2, GameRuners.HEIGHT / 2 + 50);
        addActor(background);

    }









    private void setUpSound() {

        float x = Constants.SOUND_BTTN_X_VISIBLE, y = Constants.SOUND_BTTN_Y_VISIBLE, width = 70, height = 55;
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.down = soundButtonDownImage.getDrawable();
        textButtonStyle.up = soundButtonUpImage.getDrawable();
        textButtonStyle.font = uiSkin.getFont("default-font");

        soundButton = new TextButton("Sound off", textButtonStyle);
        soundButton.getLabel().setFontScale(0.4f, 0.4f);
        soundButton.getLabelCell().padLeft(25f);

        sound_on_image.setPosition(soundButton.getX()+8,soundButton.getY()+13);
        soundButton.addActor(sound_on_image);


        soundButton.setBounds(x - soundButtonUpImage.getWidth() / 2, y - soundButtonUpImage.getHeight() / 2, soundButtonUpImage.getWidth(), soundButtonUpImage.getHeight());

        soundButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

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
        textButtonStyle.font = uiSkin.getFont("default-font");

        swipe_controll_button = new TextButton("Swipe game \n control", textButtonStyle);
        swipe_controll_button.getLabel().setFontScale(0.4f, 0.4f);
        swipe_controll_button.getLabelCell().padLeft(25f);

        swipe_image.setPosition(getX()+8,getY()+13);
        swipe_controll_button.addActor(swipe_image);


        swipe_controll_button.setBounds(x - soundButtonUpImage.getWidth() / 2, y - soundButtonUpImage.getHeight() / 2, soundButtonUpImage.getWidth(), soundButtonUpImage.getHeight());

        swipe_controll_button.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

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

        addActor(swipe_controll_button);

    }

    private void setUpBlockRds() {

        float x = Constants.BLOCK_BTTN_X_VISIBLE, y = Constants.BLOCK_BTTN_Y_VISIBLE, width = 70, height = 55;
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.down = soundButtonDownImage.getDrawable();
        textButtonStyle.up = soundButtonUpImage.getDrawable();
        textButtonStyle.font = uiSkin.getFont("default-font");

        block_rds_button = new TextButton("Block \n rds", textButtonStyle);
        block_rds_button.getLabel().setFontScale(0.4f, 0.4f);
        block_rds_button.getLabelCell().padLeft(25f);

        rds_image.setPosition(getX() + 8, getY() + 13);
        block_rds_button.addActor(rds_image);


        block_rds_button.setBounds(x - soundButtonUpImage.getWidth() / 2, y - soundButtonUpImage.getHeight() / 2, soundButtonUpImage.getWidth(), soundButtonUpImage.getHeight());

        block_rds_button.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

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
        textButtonStyle.font = uiSkin.getFont("default-font");

        restore_button = new TextButton("Restore \n purchases", textButtonStyle);
        restore_button.getLabel().setFontScale(0.4f, 0.4f);
        restore_button.getLabelCell().padLeft(25f);

        restore_image.setPosition(getX() + 8, getY() + 13);
        restore_button.addActor(restore_image);


        restore_button.setBounds(x - soundButtonUpImage.getWidth() / 2, y - soundButtonUpImage.getHeight() / 2, soundButtonUpImage.getWidth(), soundButtonUpImage.getHeight());

        restore_button.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

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
        textButtonStyle.font = uiSkin.getFont("default-font");

        singInVkBttn = new TextButton("Sing in", textButtonStyle);
        singInVkBttn.getLabel().setFontScale(0.4f, 0.4f);
        singInVkBttn.getLabelCell().padLeft(25f);

        singInVkImage.setPosition(getX() + 8, getY() + 13);
        singInVkBttn.addActor(singInVkImage);


        singInVkBttn.setBounds(x - singInVkBttn.getWidth() / 2, y - singInVkBttn.getHeight() / 2, singInVkBttn.getWidth(), singInVkBttn.getHeight());

        singInVkBttn.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

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

        addActor(singInVkBttn);

    }
    private void setUpSingInFb() {

        float x = Constants.SING_IN_FB_BTTN_X_VISIBLE, y = Constants.SING_IN_FB_Y_VISIBLE, width = 70, height = 55;
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.down = soundButtonDownImage.getDrawable();
        textButtonStyle.up = soundButtonUpImage.getDrawable();
        textButtonStyle.font = uiSkin.getFont("default-font");

        singInFbBttn = new TextButton("Sing in", textButtonStyle);
        singInFbBttn.getLabel().setFontScale(0.4f, 0.4f);
        singInFbBttn.getLabelCell().padLeft(25f);

        singInFbImage.setPosition(getX() + 8, getY() + 13);
        singInFbBttn.addActor(singInFbImage);


        singInFbBttn.setBounds(x - singInFbBttn.getWidth() / 2, y - singInFbBttn.getHeight() / 2, singInFbBttn.getWidth(), singInFbBttn.getHeight());

        singInFbBttn.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

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

    private void setUpBackButton() {

        float x = Constants.BACK_BTTN_X_VISIBLE, y = Constants.BACK_BTTN_Y_VISIBLE, width = 70, height = 55;
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.down = backButtonImageDown.getDrawable();
        textButtonStyle.up = backButtonImageUp.getDrawable();
        textButtonStyle.font = uiSkin.getFont("default-font");

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
                        getStage().addActor(new MenuTest(listenerResume,gsm));
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
