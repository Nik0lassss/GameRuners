package com.nicholaschirkevich.game.menu;

/**
 * Created by Nikolas on 20.04.2016.
 */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.nicholaschirkevich.game.GameRuners;
import com.nicholaschirkevich.game.admob.ActionResolver;
import com.nicholaschirkevich.game.interfaces.ResumeButtonListener;
import com.nicholaschirkevich.game.states.CarGarageState;
import com.nicholaschirkevich.game.states.CarShopState;
import com.nicholaschirkevich.game.states.GameState;
import com.nicholaschirkevich.game.states.GameStateManager;
import com.nicholaschirkevich.game.util.AssetsManager;
import com.nicholaschirkevich.game.util.Constants;
import com.nicholaschirkevich.game.util.GameManager;

/**
 * Created by Nikolas on 10.03.2016.
 */
public class MenuGameOverTotal extends Group {
    Texture slot_vehicle;
    Texture speed_text;

    TextButton resumeButton, playOnline, prizeButton, vkBttn;
    ImageButton carShop, coinShop, settingMenu, leaderBoard, leaderBoards;
    Image background;
    Texture dangerousTexture, rocketTexture, destroyedTexture, springBoardTexture, godModeTexture;
    Image dangerousImage, rocketImage, destroyedImage, springBoardImage, godModeImage;
    Image resumeButtonUpImage, resumeButtonDownImage, playOnlineDownImage, playOnlineUpImage, getPrizeUpButtonImage, getPrizeDownButtonImage, carShopImageUp, carShopImageDown, coinShomImageUp, coinShopImageDown, settingMenuImageUp, settingMenuImageDown, leaderBoardImageUp, leaderBoardImageDown, leaderBoardsImageUp, leaderBoardsImageDown;
    Texture resumeButtonUp, resumeButtonDown, playOnlineDownImageTexture, playOnlineUpImageTexture, getPrizeUpButtonImageTexture, getPrizeDownButtonImageTexture, carShopTextureUp, carShopTextureDown, coinShopTextureUp, coinShopTextureDown, settingMenuTextureUp, settingMenuTextureDown, leaderBoardTextureUp, leaderBoardTextureDown, leaderBoardsTextureUp, leaderBoardsTextureDown;
    Image imageLogo;
    ResumeButtonListener listener;
    SequenceAction sequence, sequenceCarShop, sequenceSetting, sequencePrizeButton, vkSequenceButton;
    GameStateManager gsm;
    Stage parentStage;
    Group groupView;
    Label dangerous_count_label, rocket_count_label, destroyed_count_label, spring_bozrd_count_label, god_mode_count_label, total_label, total_count_label;
    Label achive, achiveCount, bestAchive, bestAchiveCount, distance_label, boosters_label, dangerous_label, rocket_label, destroyed_label, spring_board_label, god_mode_label, distance_count_label;
    ActionResolver actionResolver;

    public MenuGameOverTotal(final GameStateManager gsm,ResumeButtonListener resumeButtonListener, ActionResolver actionResolver) {

        this.actionResolver = actionResolver;
        this.listener = resumeButtonListener;
        dangerous_count_label = new Label(String.valueOf(GameManager.getDangerousCount()), AssetsManager.getUiSkin());
        rocket_count_label = new Label(String.valueOf(GameManager.getRocketCount()), AssetsManager.getUiSkin());
        destroyed_count_label = new Label(String.valueOf(GameManager.getDestroyedCount()), AssetsManager.getUiSkin());
        spring_bozrd_count_label = new Label(String.valueOf(GameManager.getSpringBoardCount()), AssetsManager.getUiSkin());
        god_mode_count_label = new Label(String.valueOf(GameManager.getGodModeCount()), AssetsManager.getUiSkin());

        achive = new Label("", AssetsManager.getUiSkin());
        distance_count_label = new Label(String.valueOf((int) GameManager.getAchives()), AssetsManager.getUiSkin());
        achiveCount = new Label("", AssetsManager.getUiSkin());
        bestAchive = new Label("", AssetsManager.getUiSkin());
        bestAchiveCount = new Label("", AssetsManager.getUiSkin());
        distance_label = new Label("", AssetsManager.getUiSkin());
        boosters_label = new Label("", AssetsManager.getUiSkin());
        total_label = new Label(GameManager.getStrings().get(Constants.GO_TOTAL_TEXT), AssetsManager.getUiSkin());
        total_count_label = new Label(String.valueOf((int) GameManager.getAchives()), AssetsManager.getUiSkin());
        dangerous_label = new Label(GameManager.getStrings().get(Constants.GAME_DANGEROUS_LBL), AssetsManager.getUiSkin());
        rocket_label = new Label("Rocket", AssetsManager.getUiSkin());
        destroyed_label = new Label("Destroyed", AssetsManager.getUiSkin());
        spring_board_label = new Label("Spring Board", AssetsManager.getUiSkin());
        god_mode_label = new Label("God Mod", AssetsManager.getUiSkin());
        //car_texture = new  Texture("other_car_2_1.png");
        this.groupView = this;
        this.parentStage = parentStage;
        dangerousTexture = new Texture("danger.png");
        dangerousImage = new Image(dangerousTexture);
        rocketTexture = new Texture("rockets.png");
        rocketImage = new Image(rocketTexture);

        destroyedTexture = new Texture("destruction.png");
        destroyedImage = new Image(destroyedTexture);

        springBoardTexture = new Texture("booster.png");
        springBoardImage = new Image(springBoardTexture);

        godModeTexture = new Texture("skull_on_road.png");
        godModeImage = new Image(godModeTexture);

        resumeButtonUp = new Texture("button_play_big.png");
        resumeButtonDown = new Texture("button_play_big_pressed.png");
        playOnlineDownImageTexture = new Texture("button_multiplayer_pressed.png");
        playOnlineUpImageTexture = new Texture("button_multiplayer.png");
//        getPrizeUpButtonImageTexture = new Texture("button_next_prize.png");
//        getPrizeDownButtonImageTexture = new Texture("button_next_prize_pressed.png");
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
        vkSequenceButton = new SequenceAction();
        sequencePrizeButton = new SequenceAction();
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
        setUpResume();
        setUpImageLogo();
        //setUpPlayOnline();
        setUpPrize();
        setFreeForPrize();
        setCarShop();
        setCoinShop();
        setSettingMenu();
        setLeaderBoard();
        setLeadersBoard();
        setUpAchive();
        setUpBestAchive();
        setUpBestAchiveCount();

        setUpAchiveCount();
        setUpVkShare();
//        setUpBagroundRectagnle();
//        setUpBagroundRectagnleTotal();
//        setUpDistance();
//        setUpBoosters();
//        setUpDangerousImage();
//        setUpRocketImage();
//        setUpDestroyedImage();
//        setUpSpringBoardImage();
//        setUpGodModeImage();
//
//        setUpTotalLabel();
//        setUpTotalCountLabel();
//        setUpDisntanceCountLabel();
//
//        setUpDangerousLabel();
//        setUpRocketLabel();
//        setUpDestroyedLabel();
//        setUpSpringBoardLabel();
//        setUpGodModeLabel();
//
//        setUpDangerousCountLabel();
//        setUpRocketCountLabel();
//        setUpDestroyedCountLabel();
//        setUpSpringBoardCountLabel();
//        setUpGodModCountLabel();

        setBounds(0, 0, GameRuners.WIDTH / 2, GameRuners.HEIGHT / 2);

//        addListener(new ClickListener() {
//            @Override
//            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//
////                sequence.addAction(Actions.delay(0.3f));
//
////                sequence.addAction(new Action() {
////                    @Override
////                    public boolean act(float delta) {
//                GameManager.setDefaultSpeed();
//                GameManager.pauseGame = false;
//                GameManager.resetTime();
//                GameManager.resetCountBusters();
//                gsm.set(new GameState(gsm, false, false));
//                groupView.remove();
//                // return true;
//
////                    }
////                });
////                sequence.addAction(Actions.removeActor());
////                addAction(sequence);
//
//                return true;
//            }
//        });
    }


    public void setUpDisntanceCountLabel() {
        distance_count_label.setBounds(Constants.DISTANCE_COUNT_LABEL_X - distance_count_label.getPrefWidth() / 4 + 5, Constants.DISTANCE_COUNT_LABEL_Y, distance_count_label.getPrefWidth(), distance_count_label.getPrefHeight());
        distance_count_label.setFontScale(0.4f, 0.4f);
        addActor(distance_count_label);
    }

    public void setUpTotalLabel() {
        total_label.setBounds(Constants.TOTAL_LABEL_GAME_OVER_LABEL_X, Constants.TOTAL_LABEL_OVER_LABEL_Y, total_label.getPrefWidth(), total_label.getPrefHeight());
        total_label.setFontScale(0.5f, 0.5f);
        total_label.setColor(Color.ORANGE);
        addActor(total_label);
    }

    public void setUpTotalCountLabel() {
        total_count_label.setBounds(Constants.TOTAL_COUNT_LABEL_GAME_OVER_LABEL_X - total_count_label.getPrefWidth() / 4 + 5, Constants.TOTAL_COUNT_LABEL_OVER_LABEL_Y, total_count_label.getPrefWidth(), total_count_label.getPrefHeight());
        total_count_label.setFontScale(0.4f, 0.4f);
        addActor(total_count_label);
    }

    public void setUpDangerousCountLabel() {
        dangerous_count_label.setBounds(Constants.DANGEROUS_COUNT_LABEL_GAME_OVER_LABEL_X, Constants.DANGEROUS_COUNT_LABEL_OVER_LABEL_Y, dangerous_count_label.getPrefWidth(), dangerous_count_label.getPrefHeight());
        dangerous_count_label.setFontScale(0.4f, 0.4f);
        addActor(dangerous_count_label);
    }

    public void setUpRocketCountLabel() {
        rocket_count_label.setBounds(Constants.ROCKET_COUNT_LABEL_GAME_OVER_LABEL_X, Constants.ROCKET_COUNT_LABEL_OVER_LABEL_Y, rocket_count_label.getPrefWidth(), rocket_count_label.getPrefHeight());
        rocket_count_label.setFontScale(0.4f, 0.4f);
        addActor(rocket_count_label);
    }

    public void setUpDestroyedCountLabel() {
        destroyed_count_label.setBounds(Constants.DESTROYED_COUNT_LABEL_GAME_OVER_LABEL_X, Constants.DESTROYED_COUNT_LABEL_OVER_LABEL_Y, destroyed_count_label.getPrefWidth(), destroyed_count_label.getPrefHeight());
        destroyed_count_label.setFontScale(0.4f, 0.4f);
        addActor(destroyed_count_label);
    }

    public void setUpSpringBoardCountLabel() {
        spring_bozrd_count_label.setBounds(Constants.SPRING_BOARD_COUNT_LABEL_GAME_OVER_LABEL_X, Constants.SPRING_BOARD_COUNT_LABEL_OVER_LABEL_Y, spring_bozrd_count_label.getPrefWidth(), spring_bozrd_count_label.getPrefHeight());
        spring_bozrd_count_label.setFontScale(0.4f, 0.4f);
        addActor(spring_bozrd_count_label);
    }

    public void setUpGodModCountLabel() {
        god_mode_count_label.setBounds(Constants.GODE_MODE_COUNT_LABEL_GAME_OVER_LABEL_X, Constants.GODE_MODE_COUNT_LABEL_OVER_LABEL_Y, god_mode_count_label.getPrefWidth(), god_mode_count_label.getPrefHeight());
        god_mode_count_label.setFontScale(0.4f, 0.4f);
        addActor(god_mode_count_label);
    }


    public void setUpBagroundRectagnle() {
        addActor(new GameOverRectangle());
    }


    public void setUpBagroundRectagnleTotal() {
        addActor(new GameOverRectangleTotal());
    }

    public void setUpDangerousImage() {
        dangerousImage.setBounds(Constants.DANGEROUS_IMAGE_GAME_OVER_LABEL_X, Constants.DANGEROUS_IMAGE_OVER_LABEL_Y, dangerousImage.getPrefWidth(), dangerousImage.getPrefHeight());
        addActor(dangerousImage);
    }


    public void setUpDangerousLabel() {
        dangerous_label.setBounds(Constants.DANGEROUS_LABEL_GAME_OVER_LABEL_X, Constants.DANGEROUS_LABEL_OVER_LABEL_Y, dangerous_label.getPrefWidth(), dangerous_label.getPrefHeight());
        dangerous_label.setFontScale(0.4f, 0.4f);
        addActor(dangerous_label);
    }


    public void setUpRocketLabel() {
        rocket_label.setBounds(Constants.ROCKET_LABEL_GAME_OVER_LABEL_X, Constants.ROCKET_LABEL_OVER_LABEL_Y, rocket_label.getPrefWidth(), rocket_label.getPrefHeight());
        rocket_label.setFontScale(0.4f, 0.4f);
        addActor(rocket_label);
    }


    public void setUpDestroyedLabel() {
        destroyed_label.setBounds(Constants.DESTROYED_LABEL_GAME_OVER_LABEL_X, Constants.DESTROYED_LABEL_OVER_LABEL_Y, destroyed_label.getPrefWidth(), destroyed_label.getPrefHeight());
        destroyed_label.setFontScale(0.4f, 0.4f);
        addActor(destroyed_label);
    }


    public void setUpSpringBoardLabel() {
        spring_board_label.setBounds(Constants.SPRING_BOARD_LABEL_GAME_OVER_LABEL_X, Constants.SPRING_BOARD_LABEL_OVER_LABEL_Y, spring_board_label.getPrefWidth(), spring_board_label.getPrefHeight());
        spring_board_label.setFontScale(0.4f, 0.4f);
        addActor(spring_board_label);
    }


    public void setUpGodModeLabel() {

        god_mode_label.setBounds(Constants.GODE_MODE_LABEL_GAME_OVER_LABEL_X, Constants.GODE_MODE_LABEL_OVER_LABEL_Y, god_mode_label.getPrefWidth(), god_mode_label.getPrefHeight());
        god_mode_label.setFontScale(0.4f, 0.4f);
        addActor(god_mode_label);
    }


    public void setUpRocketImage() {
        rocketImage.setBounds(Constants.ROCKET_IMAGE_GAME_OVER_LABEL_X, Constants.ROCKET_IMAGE_OVER_LABEL_Y, dangerousImage.getPrefWidth(), dangerousImage.getPrefHeight());
        addActor(rocketImage);
    }

    public void setUpDestroyedImage() {
        destroyedImage.setBounds(Constants.DESTROYED_IMAGE_GAME_OVER_LABEL_X, Constants.DESTROYED_IMAGE_OVER_LABEL_Y, dangerousImage.getPrefWidth(), dangerousImage.getPrefHeight());
        addActor(destroyedImage);
    }

    public void setUpSpringBoardImage() {
        springBoardImage.setBounds(Constants.SPRING_BOARD_IMAGE_GAME_OVER_LABEL_X, Constants.SPRING_BOARD_IMAGE_OVER_LABEL_Y, dangerousImage.getPrefWidth(), dangerousImage.getPrefHeight());
        addActor(springBoardImage);
    }

    public void setUpGodModeImage() {
        godModeImage.setBounds(Constants.GODE_MODE_IMAGE_GAME_OVER_LABEL_X, Constants.GODE_MODE_IMAGE_OVER_LABEL_Y, dangerousImage.getPrefWidth(), dangerousImage.getPrefHeight());
        addActor(godModeImage);
    }

    public void setUpDistance() {

        distance_label.setFontScale(0.5f, 0.5f);
        distance_label.setColor(Color.ORANGE);
        distance_label.setBounds(Constants.DISTANCE_LABEL_X - distance_label.getPrefWidth(), Constants.DISTANCE_LABEL_Y, distance_label.getPrefWidth(), distance_label.getPrefHeight());
        distance_label.setText("Distance:");
        addActor(distance_label);
    }


    public void setUpBoosters() {

        boosters_label.setFontScale(0.5f, 0.5f);
        boosters_label.setColor(Color.YELLOW);
        boosters_label.setBounds(Constants.BOOSTERS_GAME_OVER_LABEL_X - boosters_label.getPrefWidth(), Constants.BOOSTERS_GAME_OVER_LABEL_Y, distance_label.getPrefWidth(), distance_label.getPrefHeight());
        boosters_label.setText("Boosters:");
        addActor(boosters_label);
    }

    public void setUpAchive() {
        achive.setX(Constants.GAME_OVER_TOTAL_ACHIVE_X_VISIBLE);
        achive.setY(Constants.GAME_OVER_TOTAL_ACHIVE_Y_VISIBLE);
        achive.setText(GameManager.getStrings().get(Constants.GO_YOUR_SCORE_LBL));
        achive.setColor(Color.ORANGE);
        achive.setFontScale(0.8f, 0.8f);
        addActor(achive);
    }

    public void setUpAchiveCount() {
        achiveCount.setText(String.valueOf((int) GameManager.getAchives()));
        achiveCount.setX(Constants.GAME_OVER_TOTAL_ACHIVE_COUNT_X_VISIBLE - achiveCount.getPrefWidth() / 3 - 5);
        achiveCount.setY(Constants.GAME_OVER_TOTAL_ACHIVE_COUNT_Y_VISIBLE);

        achiveCount.setFontScale(0.9f, 0.9f);
        addActor(achiveCount);
    }

    public void setUpBestAchive() {
        bestAchive.setX(Constants.GAME_OVER_BEST_ACHIVE_X_VISIBLE-bestAchive.getPrefWidth()/2);
        bestAchive.setY(Constants.GAME_OVER_BEST_ACHIVE_Y_VISIBLE);
        bestAchive.setText(GameManager.getStrings().get(Constants.PR_BEST_SCORE_TEXT));
        bestAchive.setFontScale(0.8f, 0.8f);
        bestAchive.setColor(Color.ORANGE);
        addActor(bestAchive);
    }

    public void setUpBestAchiveCount() {
        bestAchiveCount.setText(String.valueOf((int) GameManager.getBestAchives()));
        bestAchiveCount.setX(Constants.GAME_OVER_BEST_ACHIVE_COUNT_X_VISIBLE - bestAchiveCount.getPrefWidth() / 3 - 5);
        bestAchiveCount.setY(Constants.GAME_OVER_BEST_ACHIVE_COUNT_Y_VISIBLE);

        bestAchiveCount.setFontScale(0.9f, 0.9f);
        addActor(bestAchiveCount);
    }

    private void setUpImageLogo() {

        Texture logo = new Texture("game_over.png");
        imageLogo = new Image(logo);
        imageLogo.setBounds(Constants.GAME_OVER_LOGO_POSITION_X, Constants.GAME_OVER_LOGO_POSITION_Y, imageLogo.getWidth(), imageLogo.getHeight());
        addActor(imageLogo);
    }

    private void setFreeForPrize() {

        Texture saveMeBonus = new Texture("free_for_prize.png");

        saveMeBonus.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        imageLogo = new Image(saveMeBonus);
        imageLogo.setAlign(Align.top);
        imageLogo.setRotation(0.5f);
        imageLogo.setBounds(Constants.FREE_FOR_PRIZE_SECOND_LINE_BONUS_X, Constants.FREE_FOR_PRIZE_SECOND_LINE_BONUS_Y, imageLogo.getWidth(), imageLogo.getHeight());
        addActor(imageLogo);
    }

    private void setUpBackgroung() {

        slot_vehicle = AssetsManager.getTextureRegion(Constants.BACK_TILE_ID).getTexture();
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

        float x = Constants.SECOND_POSITION_BTTN_X_VISIBLE, y = Constants.SECOND_POSITION_BTTN_Y_VISIBLE, width = 70, height = 55;
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.down = getPrizeDownButtonImage.getDrawable();
        textButtonStyle.up = getPrizeUpButtonImage.getDrawable();
        textButtonStyle.font = AssetsManager.getUiSkin().getFont("default-font");

        prizeButton = new TextButton(GameManager.getStrings().get(Constants.GO_WIN_LBL)+"\n"+GameManager.getStrings().get(Constants.GO_PRIZE_LBL), textButtonStyle);
        prizeButton.getLabel().setFontScale(0.4f, 0.4f);
        prizeButton.getLabelCell().padLeft(2f);


        prizeButton.setBounds(x - resumeButtonUpImage.getWidth() / 2, y - resumeButtonUpImage.getHeight() / 2, resumeButtonUpImage.getWidth(), resumeButtonUpImage.getHeight());
        prizeButton.addListener(new ClickListener() {

                                    @Override
                                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                                        sequencePrizeButton.addAction(Actions.delay(0.3f));
                                        sequencePrizeButton.addAction(new Action() {
                                            @Override
                                            public boolean act(float delta) {
                                                gsm.push(new CarGarageState(gsm, actionResolver));
                                                GameManager.pauseGame = false;
                                                return true;
                                            }
                                        });
                                        sequencePrizeButton.addAction(Actions.removeActor());
                                        addAction(sequencePrizeButton);

                                        return true;
                                    }
                                }
        );

        addActor(prizeButton);

    }

    private void setUpVkShare() {

        float x = Constants.VK_BTTN_X_VISIBLE_SECOND_LINE, y = Constants.VK_BTTN_Y_VISIBLE_SECOND_LINE;
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        Image down = new Image(AssetsManager.getTextureRegion(Constants.BTTN_VK_PRESSED_ID).getTexture());
        textButtonStyle.down = down.getDrawable();
        textButtonStyle.up = new Image(AssetsManager.getTextureRegion(Constants.BTTN_VK_PRESSED_ID).getTexture()).getDrawable();
        textButtonStyle.font = AssetsManager.getUiSkin().getFont("default-font");

        vkBttn = new TextButton("   Share \n in VK", textButtonStyle);
        vkBttn.getLabel().setFontScale(0.4f, 0.4f);
        vkBttn.getLabelCell().padLeft(10f);


        vkBttn.setBounds(x - down.getWidth() / 2, y - down.getHeight() / 2, down.getWidth(), down.getHeight());
        vkBttn.addListener(new ClickListener() {

                                    @Override
                                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                                        vkSequenceButton.addAction(Actions.delay(0.3f));
                                        vkSequenceButton.addAction(new Action() {
                                            @Override
                                            public boolean act(float delta) {
                                               actionResolver.sendPostOnVk();
                                               //actionResolver.showInviteBox();
                                                return true;
                                            }
                                        });
                                        vkSequenceButton.addAction(Actions.removeActor());
                                        addAction(vkSequenceButton);

                                        return true;
                                    }
                                }
        );

        addActor(vkBttn);

    }

    private void setUpPlayOnline() {

        float x = Constants.PLAY_ONLINE_BTTN_X_VISIBLE, y = Constants.PLAY_ONLINE_BTTN_Y_VISIBLE, width = 70, height = 55;
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.down = playOnlineDownImage.getDrawable();
        textButtonStyle.up = playOnlineUpImage.getDrawable();
        textButtonStyle.font = AssetsManager.getUiSkin().getFont("default-font");

        playOnline = new TextButton(GameManager.getStrings().get(Constants.GO_PLAY_WITH_FRIEND_2_BTN), textButtonStyle);
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
                                            gsm.push(new CarShopState(gsm,actionResolver));
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
                        getStage().addActor(new MenuSetting(listener, gsm,actionResolver,groupView));
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
        textButtonStyle.font = AssetsManager.getUiSkin().getFont("default-font");

        resumeButton = new TextButton(GameManager.getStrings().get(Constants.MP_PLAY_BTN), textButtonStyle);
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
                        gsm.set(new GameState(gsm, false, true,actionResolver));

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
