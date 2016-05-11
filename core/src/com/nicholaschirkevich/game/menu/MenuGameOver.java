package com.nicholaschirkevich.game.menu;

/**
 * Created by Nikolas on 20.04.2016.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.nicholaschirkevich.game.GameRuners;
import com.nicholaschirkevich.game.interfaces.ResumeButtonListener;
import com.nicholaschirkevich.game.states.GameState;
import com.nicholaschirkevich.game.states.GameStateManager;
import com.nicholaschirkevich.game.states.GarageState;
import com.nicholaschirkevich.game.util.Constants;
import com.nicholaschirkevich.game.util.GameManager;

/**
 * Created by Nikolas on 10.03.2016.
 */
public class MenuGameOver extends Group {
    Texture slot_vehicle;
    Skin uiSkin = new Skin(Gdx.files.internal("uiskin_digit.json"));
    Texture speed_text;

    TextButton resumeButton, playOnline, prizeButton;
    ImageButton carShop, coinShop, settingMenu, leaderBoard, leaderBoards;
    Image background;
    Image resumeButtonUpImage, resumeButtonDownImage, playOnlineDownImage, playOnlineUpImage, getPrizeUpButtonImage, getPrizeDownButtonImage, carShopImageUp, carShopImageDown, coinShomImageUp, coinShopImageDown, settingMenuImageUp, settingMenuImageDown, leaderBoardImageUp, leaderBoardImageDown, leaderBoardsImageUp, leaderBoardsImageDown;
    Texture resumeButtonUp, resumeButtonDown, playOnlineDownImageTexture, playOnlineUpImageTexture, getPrizeUpButtonImageTexture, getPrizeDownButtonImageTexture, carShopTextureUp, carShopTextureDown, coinShopTextureUp, coinShopTextureDown, settingMenuTextureUp, settingMenuTextureDown, leaderBoardTextureUp, leaderBoardTextureDown, leaderBoardsTextureUp, leaderBoardsTextureDown;
    Image imageLogo;
    ResumeButtonListener listener;
    SequenceAction sequence, sequenceCarShop, sequenceSetting;
    GameStateManager gsm;
    Stage parentStage;
    Group groupView;
    Label achive, achiveCount, bestAchive, bestAchiveCount,distance_label,boosters_label;


    public MenuGameOver(GameStateManager gsm) {

        achive = new Label("", uiSkin);
        achiveCount = new Label("", uiSkin);
        bestAchive = new Label("", uiSkin);
        bestAchiveCount = new Label("", uiSkin);
        distance_label = new Label("",uiSkin);
        boosters_label =new Label("",uiSkin);
        //car_texture = new  Texture("other_car_2_1.png");
        this.groupView = this;
        this.parentStage = parentStage;
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

        sequenceSetting = new SequenceAction();
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
        GameManager.setBestAchives();
        setUpBackgroung();
        // setUpResume();
//        setUpImageLogo();
        //setUpPlayOnline();
        //setUpPrize();
        //setUpSaveMe();
//        setCarShop();
//        setCoinShop();
//        setSettingMenu();
//        setLeaderBoard();
//        setLeadersBoard();
        setUpAchive();
//        setUpBestAchive();
//        setUpBestAchiveCount();

        setUpAchiveCount();
        setUpBagroundRectagnle();
        setUpDistance();
        setUpBoosters();

        setBounds(0, 0, GameRuners.WIDTH / 2, GameRuners.HEIGHT / 2);
    }


    public void setUpBagroundRectagnle() {
        addActor(new GameOverRectangle());
    }


    public void setUpDistance()
    {

        distance_label.setFontScale(0.5f, 0.5f);
        distance_label.setColor(Color.ORANGE);
        distance_label.setBounds(Constants.DISTANCE_LABEL_X-distance_label.getPrefWidth(), Constants.DISTANCE_LABEL_Y,distance_label.getPrefWidth() , distance_label.getPrefHeight());
        distance_label.setText("Distance:");
        addActor(distance_label);
    }



    public void setUpBoosters()
    {

        boosters_label.setFontScale(0.5f, 0.5f);
        boosters_label.setColor(Color.YELLOW);
        boosters_label.setBounds(Constants.BOOSTERS_GAME_OVER_LABEL_X-boosters_label.getPrefWidth(), Constants.BOOSTERS_GAME_OVER_LABEL_Y,distance_label.getPrefWidth() , distance_label.getPrefHeight());
        boosters_label.setText("Boosters:");
        addActor(boosters_label);
    }
    public void setUpAchive() {
        achive.setX(Constants.GAME_OVER_ACHIVE_X_VISIBLE);
        achive.setY(Constants.GAME_OVER_ACHIVE_Y_VISIBLE);
        achive.setText("  You  Score:");
        achive.setColor(Color.ORANGE);
        achive.setFontScale(0.8f, 0.8f);
        addActor(achive);
    }

    public void setUpAchiveCount() {
        achiveCount.setText(String.valueOf((int) GameManager.getAchives()));
        achiveCount.setX(Constants.GAME_OVER_ACHIVE_COUNT_X_VISIBLE - achiveCount.getPrefWidth() / 3);
        achiveCount.setY(Constants.GAME_OVER_ACHIVE_COUNT_Y_VISIBLE);

        achiveCount.setFontScale(0.9f, 0.9f);
        addActor(achiveCount);
    }

    public void setUpBestAchive() {
        bestAchive.setX(Constants.GAME_OVER_BEST_ACHIVE_X_VISIBLE);
        bestAchive.setY(Constants.GAME_OVER_BEST_ACHIVE_Y_VISIBLE);
        bestAchive.setText("Best score:");
        bestAchive.setFontScale(0.6f, 0.6f);
        addActor(bestAchive);
    }

    public void setUpBestAchiveCount() {
        bestAchiveCount.setText(String.valueOf((int) GameManager.getBestAchives()));
        bestAchiveCount.setX(Constants.GAME_OVER_BEST_ACHIVE_COUNT_X_VISIBLE - bestAchiveCount.getPrefWidth() / 3);
        bestAchiveCount.setY(Constants.GAME_OVER_BEST_ACHIVE_COUNT_Y_VISIBLE);

        bestAchiveCount.setFontScale(0.6f, 0.6f);
        addActor(bestAchiveCount);
    }

    private void setUpImageLogo() {

        Texture logo = new Texture("game_over.png");
        imageLogo = new Image(logo);
        imageLogo.setBounds(Constants.GAME_OVER_LOGO_POSITION_X, Constants.GAME_OVER_LOGO_POSITION_Y, imageLogo.getWidth(), imageLogo.getHeight());
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

    private void setUpBackgroung() {

        slot_vehicle = new Texture("back_tile.png");
        background = new Image(slot_vehicle);
        Color color = background.getColor();
        background.setColor(color.r, color.g, color.b, 0.5f);
        background.setBounds(0, -20, GameRuners.WIDTH / 2, GameRuners.HEIGHT / 2 + 50);
        addActor(background);

    }


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
                                            GameManager.pauseGame = false;
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

        settingMenu.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                sequenceSetting.addAction(Actions.delay(0.1f));
                sequenceSetting.addAction(new Action() {
                    @Override
                    public boolean act(float delta) {
                        getStage().addActor(new MenuSetting(listener, gsm));
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
                        GameManager.setDefaultSpeed();
                        GameManager.pauseGame = false;
                        GameManager.resetTime();
                        gsm.set(new GameState(gsm, false, true));

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


}
