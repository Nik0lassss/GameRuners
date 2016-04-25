package com.nicholaschirkevich.game.menu;

/**
 * Created by Nikolas on 20.04.2016.
 */

import com.badlogic.gdx.Gdx;
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
    TextButton resumeButton, playOnline, prizeButton;
    ImageButton carShop, coinShop, settingMenu, leaderBoard, leaderBoards;
    Image background;
    Image resumeButtonUpImage, resumeButtonDownImage, playOnlineDownImage, playOnlineUpImage, getPrizeUpButtonImage, getPrizeDownButtonImage, carShopImageUp, carShopImageDown, coinShomImageUp, coinShopImageDown, settingMenuImageUp, settingMenuImageDown, leaderBoardImageUp, leaderBoardImageDown, leaderBoardsImageUp, leaderBoardsImageDown;
    Texture resumeButtonUp, resumeButtonDown, playOnlineDownImageTexture, playOnlineUpImageTexture, getPrizeUpButtonImageTexture, getPrizeDownButtonImageTexture, carShopTextureUp, carShopTextureDown, coinShopTextureUp, coinShopTextureDown, settingMenuTextureUp, settingMenuTextureDown, leaderBoardTextureUp, leaderBoardTextureDown, leaderBoardsTextureUp, leaderBoardsTextureDown;
    Image imageLogo, bonusSaveMe;
    ResumeButtonListener listener;
    SequenceAction sequence,sequenceCarShop;
    GameStateManager gsm;


    public MenuSetting( GameStateManager gsm) {


        //car_texture = new  Texture("other_car_2_1.png");
        this.listener = listener;

        resumeButtonUp = new Texture("button_play_big.png");
        resumeButtonDown = new Texture("button_play_big_pressed.png");
        playOnlineDownImageTexture = new Texture("button_multiplayer_pressed.png");
        playOnlineUpImageTexture = new Texture("button_multiplayer.png");
        getPrizeUpButtonImageTexture = new Texture("button_win_a_prize.png");
        getPrizeDownButtonImageTexture = new Texture("button_win_a_prize_pressed.png");
        carShopTextureUp = new Texture("bttn_cars.png");
        carShopTextureDown = new Texture("bttn_cars_prssd.png");

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
        sequenceCarShop = new SequenceAction();
        resumeButtonUpImage = new Image(resumeButtonUp);
        resumeButtonDownImage = new Image(resumeButtonDown);

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
        this.gsm = gsm;

        setUpBackgroung(false);
        setUpResume();
        setUpImageLogo();
//        setUpPlayOnline();
//        setUpPrize();
//        setUpSaveMe();
//        setCarShop();
//        setCoinShop();
//        setSettingMenu();
//        setLeaderBoard();
//        setLeadersBoard();
        //setUpIcon();
        //setUpIconSpeed();
        //setUpIconWeight();
        //setUpIconSpeedBar();
        //setUpIconWeightBar();
        //setUpDelimiterSpeedBar();

        setBounds(0, 0, GameRuners.WIDTH / 2, GameRuners.HEIGHT / 2);
    }


    private void setUpImageLogo() {

        Texture logo = new Texture("sr_logo.png");
        imageLogo = new Image(logo);
        imageLogo.setBounds(Constants.LOGO_POSITION_X, Constants.LOGO_POSITION_Y, imageLogo.getWidth(), imageLogo.getHeight());
        addActor(imageLogo);
    }

    private void setUpSaveMe() {

        Texture saveMeBonus = new Texture("for_save_me_button_100.png");

        saveMeBonus.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        imageLogo = new Image(saveMeBonus);
        imageLogo.setAlign(Align.top);
        imageLogo.setRotation(0.5f);
        imageLogo.setBounds(Constants.SAVE_ME_BONUS_X, Constants.SAVE_ME_BONUS_Y, imageLogo.getWidth(), imageLogo.getHeight());
        addActor(imageLogo);
    }

    private void setUpBackgroung(boolean selected) {
        if (selected) {
            slot_vehicle = new Texture("slot_vehicle_2_selected.png");
        } else slot_vehicle = new Texture("slot_vehicle.png");
        background = new Image(slot_vehicle);
        background.setBounds(0, -20, GameRuners.WIDTH / 2, GameRuners.HEIGHT / 2 + 50);
        addActor(background);

    }

//    private void setUpIcon() {
//        Image icon = new Image(car_texture);
//        icon.setBounds(getX() + 39 - car_texture.getWidth() / 2, getY() + 7, car_texture.getWidth(), car_texture.getHeight());
//        addActor(icon);
//    }

    private void setUpIconSpeed() {
        Image iconSpeed = new Image(speed_text);
        iconSpeed.setBounds(getX() + 93, getY() + 40, 40, 10);
        addActor(iconSpeed);
    }

    private void setUpPrize() {

        float x = Constants.PRIZE_BTTN_X_VISIBLE, y = Constants.PRIZE_BTTN_Y_VISIBLE, width = 70, height = 55;
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.down = getPrizeDownButtonImage.getDrawable();
        textButtonStyle.up = getPrizeUpButtonImage.getDrawable();
        textButtonStyle.font = uiSkin.getFont("default-font");

        prizeButton = new TextButton("Sing in", textButtonStyle);
        prizeButton.getLabel().setFontScale(0.4f, 0.4f);
        prizeButton.getLabelCell().padLeft(5f);


        prizeButton.setBounds(x - resumeButtonUpImage.getWidth() / 2, y - resumeButtonUpImage.getHeight() / 2, resumeButtonUpImage.getWidth(), resumeButtonUpImage.getHeight());


        addActor(prizeButton);

    }

    private void setUpPlayOnline() {

        float x = Constants.PLAY_ONLINE_BTTN_X_VISIBLE, y = Constants.PLAY_ONLINE_BTTN_Y_VISIBLE, width = 70, height = 55;
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.down = playOnlineDownImage.getDrawable();
        textButtonStyle.up = playOnlineUpImage.getDrawable();
        textButtonStyle.font = uiSkin.getFont("default-font");

        playOnline = new TextButton("Play \n online", textButtonStyle);
        playOnline.getLabel().setFontScale(0.4f, 0.4f);
        playOnline.getLabelCell().padLeft(30f);


        playOnline.setBounds(x - resumeButtonUpImage.getWidth() / 2, y - resumeButtonUpImage.getHeight() / 2, resumeButtonUpImage.getWidth(), resumeButtonUpImage.getHeight());


        addActor(playOnline);

    }

    private void setCarShop() {

        float x = Constants.CAR_SHOP_BTTN_X_VISIBLE, y = Constants.CAR_SHOP_BTTN_Y_VISIBLE, width = 70, height = 55;

        carShop = new ImageButton(carShopImageUp.getDrawable(), carShopImageDown.getDrawable());
        carShop.setBounds(x - carShop.getWidth() / 2, y - carShop.getHeight() / 2, carShop.getWidth(), carShop.getHeight());
        carShop.addListener(new ClickListener() {

                                @Override
                                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                                    sequenceCarShop.addAction(Actions.delay(0.3f));
                                    sequenceCarShop.addAction(new Action() {
                                        @Override
                                        public boolean act(float delta) {
                                            gsm.push(new GarageState(gsm));
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

        float x = Constants.COIN_SHOP_BTTN_X_VISIBLE, y = Constants.COIN_SHOP_BTTN_Y_VISIBLE, width = 70, height = 55;

        coinShop = new ImageButton(coinShomImageUp.getDrawable(), coinShopImageDown.getDrawable());
        coinShop.setBounds(x - coinShomImageUp.getWidth() / 2, y - coinShomImageUp.getHeight() / 2, coinShomImageUp.getWidth(), coinShomImageUp.getHeight());
        addActor(coinShop);

    }

    private void setSettingMenu() {

        float x = Constants.SETTING_BTTN_X_VISIBLE, y = Constants.SETTING_BTTN_Y_VISIBLE, width = 70, height = 55;

        settingMenu = new ImageButton(settingMenuImageUp.getDrawable(), settingMenuImageDown.getDrawable());
        settingMenu.setBounds(x - settingMenuImageDown.getWidth() / 2, y - settingMenuImageDown.getHeight() / 2, settingMenuImageDown.getWidth(), settingMenuImageDown.getHeight());
        addActor(settingMenu);

    }

    private void setLeaderBoard() {

        float x = Constants.LEADERBOARD_BTTN_X_VISIBLE, y = Constants.LEADERBOARD_BTTN_Y_VISIBLE, width = 70, height = 55;

        leaderBoard = new ImageButton(leaderBoardImageUp.getDrawable(), leaderBoardImageDown.getDrawable());
        leaderBoard.setBounds(x - leaderBoardImageDown.getWidth() / 2, y - leaderBoardImageDown.getHeight() / 2, leaderBoardImageDown.getWidth(), leaderBoardImageDown.getHeight());
        addActor(leaderBoard);

    }

    private void setLeadersBoard() {

        float x = Constants.LEADERBOARDS_BTTN_X_VISIBLE, y = Constants.LEADERBOARDS_BTTN_Y_VISIBLE, width = 70, height = 55;

        leaderBoards = new ImageButton(leaderBoardsImageUp.getDrawable(), leaderBoardsImageDown.getDrawable());
        leaderBoards.setBounds(x - leaderBoardsImageDown.getWidth() / 2, y - leaderBoardsImageDown.getHeight() / 2, leaderBoardsImageDown.getWidth(), leaderBoardsImageDown.getHeight());
        addActor(leaderBoards);

    }


    private void setUpResume() {

        float x = Constants.RESUME_BTTN_X_VISIBLE, y = Constants.RESUME_BTTN_Y_VISIBLE, width = 70, height = 55;
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.down = resumeButtonDownImage.getDrawable();
        textButtonStyle.up = resumeButtonUpImage.getDrawable();
        textButtonStyle.font = uiSkin.getFont("default-font");

        resumeButton = new TextButton("Play", textButtonStyle);
        resumeButton.getLabel().setFontScale(0.6f, 0.6f);
        resumeButton.getLabelCell().padLeft(25f);


        resumeButton.setBounds(x - resumeButtonUpImage.getWidth() / 2, y - resumeButtonUpImage.getHeight() / 2, resumeButtonUpImage.getWidth(), resumeButtonUpImage.getHeight());

        resumeButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

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

    private void setUpIconSpeedBar() {

        Image iconSpeedBar = new Image(speed_bar);
        iconSpeedBar.setBounds(getX() + 93, getY() + 30, 90, 8);
        addActor(iconSpeedBar);
    }

//    private void setUpDelimiterSpeedBar() {
//
//        Image delimiter = new Image(delimiterTexture);
//        delimiter.setBounds(getX() + 78, getY() + 30, delimiter.getWidth(), delimiter.getHeight());
//        addActor(delimiter);
//    }

    private void setUpIconWeightBar() {
        Image iconWeightBar = new Image(weight_bar);
        iconWeightBar.setOrigin(40, 8);
        //iconWeightBar.setScaling(Scaling.fillY);
        iconWeightBar.setBounds(getX() + 93, getY() + 10, 20, 8);
        addActor(iconWeightBar);
    }

    private void setUpIconWeight() {
        Image iconWeight = new Image(weight_text);
        iconWeight.setBounds(getX() + 93, getY() + 20, 40, 10);
        addActor(iconWeight);
    }

    private void setUpCarName(String imageId) {
        Texture carNameTexture = AssetsManager.getTextureRegion(imageId).getTexture();
        Image carName = new Image(carNameTexture);
        carName.setBounds(getX() + 93, getY() + 65, carNameTexture.getWidth(), carNameTexture.getHeight());
        addActor(carName);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }


}
