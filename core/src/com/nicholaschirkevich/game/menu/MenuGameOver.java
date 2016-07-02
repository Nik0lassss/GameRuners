package com.nicholaschirkevich.game.menu;

/**
 * Created by Nikolas on 20.04.2016.
 */

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.nicholaschirkevich.game.GameRuners;
import com.nicholaschirkevich.game.admob.ActionResolver;
import com.nicholaschirkevich.game.interfaces.ResumeButtonListener;
import com.nicholaschirkevich.game.states.GameState;
import com.nicholaschirkevich.game.states.GameStateManager;
import com.nicholaschirkevich.game.states.CarShopState;
import com.nicholaschirkevich.game.util.AssetsManager;
import com.nicholaschirkevich.game.util.Constants;
import com.nicholaschirkevich.game.util.GameManager;

/**
 * Created by Nikolas on 10.03.2016.
 */
public class MenuGameOver extends Group {


    Texture speed_text;

    TextButton resumeButton, playOnline, prizeButton;
    ImageButton carShop, coinShop, settingMenu, leaderBoard, leaderBoards;
    Image background;
    Image dangerousImage, rocketImage, destroyedImage, springBoardImage, godModeImage;
    Image resumeButtonUpImage, resumeButtonDownImage, playOnlineDownImage, playOnlineUpImage, getPrizeUpButtonImage, getPrizeDownButtonImage, carShopImageUp, carShopImageDown, coinShomImageUp, coinShopImageDown, settingMenuImageUp, settingMenuImageDown, leaderBoardImageUp, leaderBoardImageDown, leaderBoardsImageUp, leaderBoardsImageDown;

    Image imageLogo;
    ResumeButtonListener listener;
    SequenceAction sequence, sequenceCarShop, sequenceSetting;
    GameStateManager gsm;
    Group groupView;
    Label dangerous_count_label, rocket_count_label, destroyed_count_label, spring_bozrd_count_label, god_mode_count_label, total_label, total_count_label;
    Label achive, achiveCount, bestAchive, bestAchiveCount, distance_label, boosters_label, dangerous_label, rocket_label, destroyed_label, spring_board_label, god_mode_label, distance_count_label;
    ActionResolver actionResolver;

    public MenuGameOver(final GameStateManager gsm, final ResumeButtonListener resumeButtonListener, final ActionResolver actionResolver) {

        this.actionResolver = actionResolver;
        this.listener = resumeButtonListener;
        dangerous_count_label = new Label(String.valueOf(GameManager.getDangerousCount()), AssetsManager.getUiSkin());
        rocket_count_label = new Label(String.valueOf(GameManager.getRocketCount()), AssetsManager.getUiSkin());
        destroyed_count_label = new Label(String.valueOf(GameManager.getDestroyedCount()), AssetsManager.getUiSkin());
        spring_bozrd_count_label = new Label(String.valueOf(GameManager.getSpringBoardCount()), AssetsManager.getUiSkin());
        god_mode_count_label = new Label(String.valueOf(GameManager.getGodModeCount()), AssetsManager.getUiSkin());

        achive = new Label("", AssetsManager.getUiSkin());
        distance_count_label = new Label(String.valueOf((int) GameManager.getDistance()), AssetsManager.getUiSkin());
        achiveCount = new Label("", AssetsManager.getUiSkin());
        bestAchive = new Label("", AssetsManager.getUiSkin());
        bestAchiveCount = new Label("", AssetsManager.getUiSkin());
        distance_label = new Label("", AssetsManager.getUiSkin());
        boosters_label = new Label("", AssetsManager.getUiSkin());
        total_label = new Label(GameManager.getStrings().get(Constants.GO_TOTAL_TEXT), AssetsManager.getUiSkin());
        total_count_label = new Label(String.valueOf((int) GameManager.getAchives()), AssetsManager.getUiSkin());
        dangerous_label = new Label(GameManager.getStrings().get(Constants.GAME_DANGEROUS_LBL), AssetsManager.getUiSkin());
        rocket_label = new Label(GameManager.getStrings().get(Constants.GO_ROCKET_LBL), AssetsManager.getUiSkin());
        destroyed_label = new Label(GameManager.getStrings().get(Constants.GO_DESTROYED_LBL), AssetsManager.getUiSkin());
        spring_board_label = new Label(GameManager.getStrings().get(Constants.GO_SPRING_BOARD_LBL), AssetsManager.getUiSkin());
        god_mode_label = new Label(GameManager.getStrings().get(Constants.GO_GOD_MOD_LBL), AssetsManager.getUiSkin());
        //car_texture = new  Texture("other_car_2_1.png");
        this.groupView = this;

        dangerousImage = new Image(AssetsManager.getTextureRegion(Constants.DANGER_ID));

        rocketImage = new Image(AssetsManager.getTextureRegion(Constants.ROCKETS_ID));


        destroyedImage = new Image(AssetsManager.getTextureRegion(Constants.DESTRICTION_ID));


        springBoardImage = new Image(AssetsManager.getTextureRegion(Constants.BOOSTER_ID));


        godModeImage = new Image(AssetsManager.getTextureRegion(Constants.SKULL_ON_ROAD_ID));


        this.gsm = gsm;
        GameManager.setBestAchives();
        setUpBackgroung();

        setUpAchive();


        setUpAchiveCount();
        setUpBagroundRectagnle();
        setUpBagroundRectagnleTotal();
        setUpDistance();
        setUpBoosters();
        setUpDangerousImage();
        setUpRocketImage();
        setUpDestroyedImage();
        setUpSpringBoardImage();
        setUpGodModeImage();

        setUpTotalLabel();
        setUpTotalCountLabel();
        setUpDisntanceCountLabel();

        setUpDangerousLabel();
        setUpRocketLabel();
        setUpDestroyedLabel();
        setUpSpringBoardLabel();
        setUpGodModeLabel();

        setUpDangerousCountLabel();
        setUpRocketCountLabel();
        setUpDestroyedCountLabel();
        setUpSpringBoardCountLabel();
        setUpGodModCountLabel();
        if(GameManager.getCoinCount()>0) setUpCoinCount();

        setBounds(0, 0, GameRuners.WIDTH / 2, GameRuners.HEIGHT / 2);

        addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                AssetsManager.playSound(Constants.SOUND_CLICK);

                getStage().addActor(new MenuGameOverTotal(gsm, resumeButtonListener, actionResolver));
                remove();

                return true;
            }
        });
    }


    public void setUpCoinCount() {
        Label coinCountLabel = new Label("+" + String.valueOf(GameManager.getCoinCount()), AssetsManager.getUiSkin());
        coinCountLabel.setPosition(Constants.GAME_OVER_COINT_COUNT_LABEL_X - coinCountLabel.getPrefWidth(), Constants.GAME_OVER_COINT_COUNT_LABEL_Y);
        Image imageCoin = new Image(AssetsManager.getTextureRegion(Constants.COIN_ICON_2_ID));
        imageCoin.setScale(0.5f, 0.5f);
        imageCoin.setPosition(Constants.GAME_OVER_COINT_IMAGE_COIN_X, Constants.GAME_OVER_COINT_IMAGE_COIN_Y);
        addActor(coinCountLabel);
        addActor(imageCoin);
    }

    public void setUpDisntanceCountLabel() {
        distance_count_label.setBounds(Constants.DISTANCE_COUNT_LABEL_X - distance_count_label.getPrefWidth() / 4 + 5, Constants.DISTANCE_COUNT_LABEL_Y, distance_count_label.getPrefWidth(), distance_count_label.getPrefHeight());
        distance_count_label.setFontScale(0.6f, 0.6f);
        addActor(distance_count_label);
    }

    public void setUpTotalLabel() {
        total_label.setBounds(Constants.TOTAL_LABEL_GAME_OVER_LABEL_X, Constants.TOTAL_LABEL_OVER_LABEL_Y, total_label.getPrefWidth(), total_label.getPrefHeight());
        total_label.setFontScale(0.6f, 0.6f);
        total_label.setColor(Color.ORANGE);
        addActor(total_label);
    }

    public void setUpTotalCountLabel() {
        total_count_label.setBounds(Constants.TOTAL_COUNT_LABEL_GAME_OVER_LABEL_X, Constants.TOTAL_COUNT_LABEL_OVER_LABEL_Y, total_count_label.getPrefWidth(), total_count_label.getPrefHeight());
        total_count_label.setFontScale(0.5f, 0.5f);
        total_count_label.setX(Constants.TOTAL_COUNT_LABEL_GAME_OVER_LABEL_X-total_count_label.getPrefWidth() );
        addActor(total_count_label);
    }

    public void setUpDangerousCountLabel() {
        dangerous_count_label.setBounds(Constants.DANGEROUS_COUNT_LABEL_GAME_OVER_LABEL_X, Constants.DANGEROUS_COUNT_LABEL_OVER_LABEL_Y, dangerous_count_label.getPrefWidth(), dangerous_count_label.getPrefHeight());
        dangerous_count_label.setFontScale(0.5f, 0.5f);
        dangerous_count_label.setX(Constants.DANGEROUS_COUNT_LABEL_GAME_OVER_LABEL_X-dangerous_count_label.getPrefWidth());
        addActor(dangerous_count_label);
    }

    public void setUpRocketCountLabel() {
        rocket_count_label.setBounds(Constants.ROCKET_COUNT_LABEL_GAME_OVER_LABEL_X, Constants.ROCKET_COUNT_LABEL_OVER_LABEL_Y, rocket_count_label.getPrefWidth(), rocket_count_label.getPrefHeight());

        rocket_count_label.setFontScale(0.5f, 0.5f);
        rocket_count_label.setX(Constants.ROCKET_COUNT_LABEL_GAME_OVER_LABEL_X-rocket_count_label.getPrefWidth());
        addActor(rocket_count_label);
    }

    public void setUpDestroyedCountLabel() {
        destroyed_count_label.setBounds(Constants.DESTROYED_COUNT_LABEL_GAME_OVER_LABEL_X, Constants.DESTROYED_COUNT_LABEL_OVER_LABEL_Y, destroyed_count_label.getPrefWidth(), destroyed_count_label.getPrefHeight());
        destroyed_count_label.setFontScale(0.5f, 0.5f);

        destroyed_count_label.setX(Constants.DESTROYED_COUNT_LABEL_GAME_OVER_LABEL_X-destroyed_count_label.getPrefWidth());
        addActor(destroyed_count_label);
    }

    public void setUpSpringBoardCountLabel() {
        spring_bozrd_count_label.setBounds(Constants.SPRING_BOARD_COUNT_LABEL_GAME_OVER_LABEL_X, Constants.SPRING_BOARD_COUNT_LABEL_OVER_LABEL_Y, spring_bozrd_count_label.getPrefWidth(), spring_bozrd_count_label.getPrefHeight());
        spring_bozrd_count_label.setFontScale(0.5f, 0.5f);

        spring_bozrd_count_label.setX(Constants.SPRING_BOARD_COUNT_LABEL_GAME_OVER_LABEL_X-spring_bozrd_count_label.getPrefWidth());
        addActor(spring_bozrd_count_label);
    }

    public void setUpGodModCountLabel() {
        god_mode_count_label.setBounds(Constants.GODE_MODE_COUNT_LABEL_GAME_OVER_LABEL_X, Constants.GODE_MODE_COUNT_LABEL_OVER_LABEL_Y, god_mode_count_label.getPrefWidth(), god_mode_count_label.getPrefHeight());
        god_mode_count_label.setFontScale(0.5f, 0.5f);

        god_mode_count_label.setX(Constants.GODE_MODE_COUNT_LABEL_GAME_OVER_LABEL_X-god_mode_count_label.getPrefWidth());
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
        dangerous_label.setFontScale(0.5f, 0.5f);
        addActor(dangerous_label);
    }


    public void setUpRocketLabel() {
        rocket_label.setBounds(Constants.ROCKET_LABEL_GAME_OVER_LABEL_X, Constants.ROCKET_LABEL_OVER_LABEL_Y, rocket_label.getPrefWidth(), rocket_label.getPrefHeight());
        rocket_label.setFontScale(0.5f, 0.5f);
        addActor(rocket_label);
    }


    public void setUpDestroyedLabel() {
        destroyed_label.setBounds(Constants.DESTROYED_LABEL_GAME_OVER_LABEL_X, Constants.DESTROYED_LABEL_OVER_LABEL_Y, destroyed_label.getPrefWidth(), destroyed_label.getPrefHeight());
        destroyed_label.setFontScale(0.5f, 0.5f);
        addActor(destroyed_label);
    }


    public void setUpSpringBoardLabel() {
        spring_board_label.setBounds(Constants.SPRING_BOARD_LABEL_GAME_OVER_LABEL_X, Constants.SPRING_BOARD_LABEL_OVER_LABEL_Y, spring_board_label.getPrefWidth(), spring_board_label.getPrefHeight());
        spring_board_label.setFontScale(0.5f, 0.5f);
        addActor(spring_board_label);
    }


    public void setUpGodModeLabel() {

        god_mode_label.setBounds(Constants.GODE_MODE_LABEL_GAME_OVER_LABEL_X, Constants.GODE_MODE_LABEL_OVER_LABEL_Y, god_mode_label.getPrefWidth(), god_mode_label.getPrefHeight());
        god_mode_label.setFontScale(0.5f, 0.5f);
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

        distance_label.setFontScale(0.65f, 0.65f);
        distance_label.setColor(Color.WHITE);
        distance_label.setBounds(Constants.DISTANCE_LABEL_X - distance_label.getPrefWidth(), Constants.DISTANCE_LABEL_Y, distance_label.getPrefWidth(), distance_label.getPrefHeight());
        distance_label.setText(GameManager.getStrings().get(Constants.GO_DISTANCE_TEXT));
        addActor(distance_label);
    }


    public void setUpBoosters() {

        boosters_label.setFontScale(0.65f, 0.65f);
        boosters_label.setColor(255f/255f,205f/255f,0f/255f,1f);
        boosters_label.setBounds(Constants.BOOSTERS_GAME_OVER_LABEL_X - boosters_label.getPrefWidth(), Constants.BOOSTERS_GAME_OVER_LABEL_Y, distance_label.getPrefWidth(), distance_label.getPrefHeight());
        boosters_label.setText(GameManager.getStrings().get(Constants.GO_BUSTERS_TEXT));
        addActor(boosters_label);
    }

    public void setUpAchive() {

        achive.setY(Constants.GAME_OVER_ACHIVE_Y_VISIBLE);
        achive.setText(GameManager.getStrings().get(Constants.GO_YOUR_LBL));
        achive.setColor(Color.ORANGE);
        achive.setFontScale(1f, 1f);
        //achive.setAlignment(Align.center, Align.center);
        achive.setX(Constants.GAME_OVER_ACHIVE_X_VISIBLE-achive.getPrefWidth()/2);
        Label achiveSecondLine = new Label(GameManager.getStrings().get(Constants.GO_SCORE_LBL), AssetsManager.getUiSkin());

        achiveSecondLine.setColor(Color.ORANGE);
        achiveSecondLine.setFontScale(1f, 1f);
        achiveSecondLine.setY(Constants.GAME_OVER_ACHIVE_SECOND_LINE_Y_VISIBLE);
        achiveSecondLine.setX(Constants.GAME_OVER_ACHIVE_SECOND_LINE_X_VISIBLE - achiveSecondLine.getPrefWidth() / 2);
        addActor(achive);
        addActor(achiveSecondLine);
    }

    public void setUpAchiveCount() {
        achiveCount.setText(String.valueOf((int) GameManager.getAchives()));
        achiveCount.setFontScale(1.3f, 1.3f);
        achiveCount.setX(Constants.GAME_OVER_ACHIVE_COUNT_X_VISIBLE - achiveCount.getPrefWidth() / 2);
        achiveCount.setY(Constants.GAME_OVER_ACHIVE_COUNT_Y_VISIBLE);


        addActor(achiveCount);
    }

    public void setUpBestAchive() {
        bestAchive.setX(Constants.GAME_OVER_BEST_ACHIVE_X_VISIBLE);
        bestAchive.setY(Constants.GAME_OVER_BEST_ACHIVE_Y_VISIBLE);
        bestAchive.setText(GameManager.getStrings().get(Constants.PR_BEST_SCORE_TEXT));
        bestAchive.setFontScale(0.6f, 0.6f);
        addActor(bestAchive);
    }


    private void setUpBackgroung() {


        background = new Image(AssetsManager.getTextureRegion(Constants.BACK_TILE_ID));
        Color color = background.getColor();
        background.setColor(color.r, color.g, color.b, 0.5f);
        background.setBounds(0, -20, GameRuners.WIDTH / 2, GameRuners.HEIGHT / 2 + 50);
        addActor(background);

    }


    @Override
    public void act(float delta) {

        super.act(delta);
    }


}
