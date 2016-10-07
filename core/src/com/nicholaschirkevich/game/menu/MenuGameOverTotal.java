package com.nicholaschirkevich.game.menu;

/**
 * Created by Nikolas on 20.04.2016.
 */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
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
import com.badlogic.gdx.utils.SnapshotArray;
import com.nicholaschirkevich.game.GameRuners;
import com.nicholaschirkevich.game.admob.ActionResolver;
import com.nicholaschirkevich.game.interfaces.ResumeButtonListener;
import com.nicholaschirkevich.game.interfaces.UpdateCoinCountInterface;
import com.nicholaschirkevich.game.states.CarGarageState;
import com.nicholaschirkevich.game.states.CarShopState;
import com.nicholaschirkevich.game.states.CoinShopState;
import com.nicholaschirkevich.game.states.GameState;
import com.nicholaschirkevich.game.states.GameStateManager;
import com.nicholaschirkevich.game.states.LeaderboardMenu;
import com.nicholaschirkevich.game.states.RecordsMenu;
import com.nicholaschirkevich.game.util.AssetsManager;
import com.nicholaschirkevich.game.util.Constants;
import com.nicholaschirkevich.game.util.GameManager;
import com.nicholaschirkevich.game.util.RandomUtil;

/**
 * Created by Nikolas on 10.03.2016.
 */
public class MenuGameOverTotal extends Group implements UpdateCoinCountInterface {
    private Texture slot_vehicle;
    private Texture speed_text;

    private Texture backButtonTextureDown, backButtonTextureUp;
    private Image backButtonImageDown, backButtonImageUp;

    private TextButton resumeButton, playOnline, prizeButton, vkBttn;
    private ImageButton carShop, coinShop, settingMenu, leaderBoard, leaderBoards;
    private Image background;
    private Texture dangerousTexture, rocketTexture, destroyedTexture, springBoardTexture, godModeTexture;
    private Image dangerousImage, rocketImage, destroyedImage, springBoardImage, godModeImage;
    private Image resumeButtonUpImage, resumeButtonDownImage, playOnlineDownImage, playOnlineUpImage, getPrizeUpButtonImage, getPrizeDownButtonImage, carShopImageUp, carShopImageDown, coinShomImageUp, coinShopImageDown, settingMenuImageUp, settingMenuImageDown, leaderBoardImageUp, leaderBoardImageDown, leaderBoardsImageUp, leaderBoardsImageDown;
    private Texture resumeButtonUp, resumeButtonDown, playOnlineDownImageTexture, playOnlineUpImageTexture, getPrizeUpButtonImageTexture, getPrizeDownButtonImageTexture, carShopTextureUp, carShopTextureDown, coinShopTextureUp, coinShopTextureDown, settingMenuTextureUp, settingMenuTextureDown, leaderBoardTextureUp, leaderBoardTextureDown, leaderBoardsTextureUp, leaderBoardsTextureDown;
    private Image imageLogo;
    private ImageButton imageButton;
    private ResumeButtonListener listener;
    private SequenceAction sequence, sequenceCarShop, sequenceSetting, sequencePrizeButton, vkSequenceButton, sequenceCoinShop;
    private GameStateManager gsm;
    private Stage parentStage;
    private Label dangerous_count_label, rocket_count_label, destroyed_count_label, spring_bozrd_count_label, god_mode_count_label, total_label, total_count_label;
    private Label achive, achiveCount, bestAchive, bestAchiveCount, distance_label, boosters_label, dangerous_label, rocket_label, destroyed_label, spring_board_label, god_mode_label, distance_count_label;
    private ActionResolver actionResolver;
    private Label labelCoinCount;
    private TextButton backBttn;
    private SequenceAction sequenceReturn;

    private Group groupView;


    public MenuGameOverTotal(final GameStateManager gsm, ResumeButtonListener resumeButtonListener, ActionResolver actionResolver) {

        this.actionResolver = actionResolver;
        this.listener = resumeButtonListener;
        sequenceReturn = new SequenceAction();
        dangerous_count_label = new Label(String.valueOf(GameManager.getDangerousCount()), AssetsManager.getUiSkin());
        rocket_count_label = new Label(String.valueOf(GameManager.getRocketCount()), AssetsManager.getUiSkin());
        destroyed_count_label = new Label(String.valueOf(GameManager.getDestroyedCount()), AssetsManager.getUiSkin());
        spring_bozrd_count_label = new Label(String.valueOf(GameManager.getSpringBoardCount()), AssetsManager.getUiSkin());
        god_mode_count_label = new Label(String.valueOf(GameManager.getGodModeCount()), AssetsManager.getUiSkin());
        sequenceCoinShop = new SequenceAction();

        achive = new Label("", AssetsManager.getUiSkin());
        distance_count_label = new Label(String.valueOf((int) GameManager.getAchives()), AssetsManager.getUiSkin());
        achiveCount = new Label("", AssetsManager.getUiSkin());
        bestAchive = new Label("", AssetsManager.getUiSkin());
        bestAchiveCount = new Label("", AssetsManager.getUiSkin());
        distance_label = new Label("", AssetsManager.getUiSkin());
        boosters_label = new Label("", AssetsManager.getUiSkin());
        total_label = new Label(GameManager.getStrings().get(Constants.GO_TOP_LBL), AssetsManager.getUiSkin());
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
        //setUpResume();
        //setUpImageLogo();
        //setUpPlayOnline();


        if (GameManager.isNeedFreeCarPrize()) {
            //if (actionResolver.isGetBonusIntertitalLoad() && actionResolver.isGetBonusIntertatlLoaded()) {
            if (actionResolver.isGetBonusIntertatlLoaded()) {
                setGetBonus(Constants.THIRD_POSITION_BTTN_X_VISIBLE, Constants.THIRD_POSITION_BTTN_Y_VISIBLE);
                if (GameManager.getMyCars().size() < GameManager.getCarShopSize()) {
                    setUpPrize(Constants.SECOND_POSITION_BTTN_X_VISIBLE, Constants.SECOND_POSITION_BTTN_Y_VISIBLE);
                    setFreeForPrize(Constants.FREE_FOR_PRIZE_FIRST_LINE_BONUS_X, Constants.SECOND_POSITION_BTTN_Y_VISIBLE - 10);
                    GameManager.setNewFreeCarPrizeDate();
                }
            } else {
                if (GameManager.getMyCars().size() < GameManager.getCarShopSize()) {
                    setUpPrize(Constants.CENTER_POSITION_BTTN_X_VISIBLE, Constants.CENTER_POSITION_BTTN_Y_VISIBLE);
                    setFreeForPrize(Constants.FREE_FOR_PRIZE_SECOND_LINE_BONUS_X, Constants.CENTER_POSITION_BTTN_Y_VISIBLE - 10);
                    GameManager.setNewFreeCarPrizeDate();
                }
            }
        } else {

//            if (actionResolver.isGetBonusIntertitalLoad() && actionResolver.isGetBonusIntertatlLoaded()) {
            if (actionResolver.isGetBonusIntertatlLoaded() && GameManager.addAdMobCounter()) {
                setGetBonus(Constants.THIRD_POSITION_BTTN_X_VISIBLE, Constants.THIRD_POSITION_BTTN_Y_VISIBLE);
                if (GameManager.getMyCars().size() < GameManager.getCarShopSize()) {
                    setUpNextPrize(Constants.SECOND_POSITION_BTTN_X_VISIBLE, Constants.SECOND_POSITION_BTTN_Y_VISIBLE);
                }
            } else {
                if (GameManager.getMyCars().size() < GameManager.getCarShopSize()) {
                    setUpNextPrize(Constants.CENTER_POSITION_BTTN_X_VISIBLE, Constants.CENTER_POSITION_BTTN_Y_VISIBLE);
                }
            }
        }


//        setCarShop();
//        setCoinShop();
//        setSettingMenu();


//        setLeaderBoard();
//        setLeadersBoard();
        setUpAchive();
//        setUpBestAchive();
//        setUpBestAchiveCount();
//        setUpImageCoinCount();
//        setUpCoinCountLabel();
        setUpLeaderBoard();
        setUpBackButton();
        setUpAchiveCount();
        setUpPlayButton();
        setBounds(0, 0, GameRuners.WIDTH / 2, GameRuners.HEIGHT / 2);

    }


    public void setUpPlayButton() {
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = new Image(AssetsManager.getTextureRegion(Constants.BUTTON_PLAY_WITH_A_FRIEND_ID)).getDrawable();
        textButtonStyle.down = new Image(AssetsManager.getTextureRegion(Constants.BUTTON_PLAY_WITH_A_FRIEND_ID)).getDrawable();
        textButtonStyle.font = AssetsManager.getUiSkin().getFont("default-font");
        TextButton inviteFriend = new TextButton(GameManager.getStrings().get(Constants.MP_PLAY_BTN), textButtonStyle);
        inviteFriend.getLabel().setFontScale(0.5f, 0.5f);
        inviteFriend.setBounds(70, 5, 185, 47);
        inviteFriend.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                sequence.addAction(Actions.delay(0.3f));

                sequence.addAction(new Action() {
                    @Override
                    public boolean act(float delta) {
                        AssetsManager.playSound(Constants.SOUND_CLICK);
                        GameManager.setDefaultSpeed();
                        GameManager.pauseGame = false;
                        GameManager.resetTime();
                        gsm.set(new GameState(gsm, false, true, actionResolver));

                        return true;

                    }
                });
                sequence.addAction(Actions.removeActor());
                addAction(sequence);

                return true;
            }
        });
        addActor(inviteFriend);
    }


    public void setVisibleParent(Group group) {
        SnapshotArray<Actor> actors = group.getChildren();
        for (Actor actor : actors) {
            actor.setVisible(true);
        }
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
                        gsm.pop();
                        gsm.push(new GameState(gsm, false, false, actionResolver));
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


    public void setGetBonus(float x, float y) {
        //float x = Constants.THIRD_POSITION_BTTN_X_VISIBLE, y = Constants.THIRD_POSITION_BTTN_Y_VISIBLE, width = 70, height = 55;
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.down = new Image(AssetsManager.getTextureRegion(Constants.BTTN_GET_BONUS_PRESSED_ID).getTexture()).getDrawable();
        textButtonStyle.up = new Image(AssetsManager.getTextureRegion(Constants.BTTN_GET_BONUS_ID)).getDrawable();
        textButtonStyle.font = AssetsManager.getUiSkin().getFont("default-font");
        final SequenceAction getBonusSequence = new SequenceAction();

        final TextButton getBonusBttn = new TextButton(GameManager.getStrings().get(Constants.GO_BONUS_LBL) + "\n \n", textButtonStyle);
        getBonusBttn.getLabel().setFontScale(0.4f, 0.4f);
        getBonusBttn.getLabelCell().padLeft(60f);
        getBonusBttn.getLabel().setAlignment(Align.left);

        Label coinCountLabel = new Label("+179", AssetsManager.getUiSkin());

        coinCountLabel.setBounds(getX() + 95 - coinCountLabel.getWidth() / 2, getY() + 10, coinCountLabel.getWidth(), coinCountLabel.getHeight());
        coinCountLabel.setFontScale(0.7f, 0.7f);

        Image coinImage = new Image(AssetsManager.getAnimation(Constants.COIN_ASSETS_ID).getKeyFrames()[0]);
        coinImage.setBounds(getX() + 115, getY() + 15, coinImage.getWidth() - 3, coinImage.getHeight() - 3);

        getBonusBttn.setBounds(x - AssetsManager.getTextureRegion(Constants.BTTN_GET_BONUS_PRESSED_ID).getTexture().getWidth() / 2, y - AssetsManager.getTextureRegion(Constants.BTTN_GET_BONUS_PRESSED_ID).getTexture().getHeight() / 2, AssetsManager.getTextureRegion(Constants.BTTN_GET_BONUS_PRESSED_ID).getTexture().getWidth(), AssetsManager.getTextureRegion(Constants.BTTN_GET_BONUS_PRESSED_ID).getTexture().getHeight());
        getBonusBttn.addListener(new ClickListener() {

                                     @Override
                                     public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                                         AssetsManager.playSound(Constants.SOUND_CLICK);
                                         getBonusSequence.addAction(Actions.delay(0.3f));
                                         getBonusSequence.addAction(new Action() {
                                             @Override
                                             public boolean act(float delta) {
                                                 actionResolver.showInterstitaGetBonus();
                                                 getBonusBttn.remove();
                                                 return true;
                                             }
                                         });
                                         addAction(getBonusSequence);

                                         return true;
                                     }
                                 }
        );

        getBonusBttn.addActor(coinCountLabel);
        getBonusBttn.addActor(coinImage);
        addActor(getBonusBttn);
    }


    public void setUpNextPrize(float x, float y) {
        // float x = Constants.SECOND_POSITION_BTTN_X_VISIBLE, y = Constants.SECOND_POSITION_BTTN_Y_VISIBLE, width = 70, height = 55;
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.down = new Image(AssetsManager.getTextureRegion(Constants.BTTN_NEXT_PRIZE_PRESSED_ID).getTexture()).getDrawable();
        textButtonStyle.up = new Image(AssetsManager.getTextureRegion(Constants.BTTN_NEXT_PRIZE_ID)).getDrawable();
        textButtonStyle.font = AssetsManager.getUiSkin().getFont("default-font");
        final SequenceAction getNextPrizeSequence = new SequenceAction();

        TextButton getNextPrize = new TextButton(GameManager.getStrings().get(Constants.GO_NEXT_PRIZE_LBL) + "\n \n", textButtonStyle);
        getNextPrize.getLabel().setFontScale(0.35f, 0.35f);
        getNextPrize.getLabelCell().padLeft(45f);

        int coinCount = 300 - GameManager.getCoinCounter();
        GameManager.timeToNextFreePrize();
        if (coinCount > 0) {
//        if (true) {
            Label coinCountLabel = new Label("", AssetsManager.getUiSkin());
//            if (RandomUtil.getRandomBoolean()) {
//                coinCountLabel.setText("");
//                coinCountLabel.setText(String.valueOf(coinCount));
//            } else {
//                coinCountLabel.setText(GameManager.timeToNextFreePrize());
//            }

            coinCountLabel.setText(String.valueOf(coinCount));
            coinCountLabel.setBounds(getX() + 60 - coinCountLabel.getWidth() / 2, getY() + 20, coinCountLabel.getWidth(), coinCountLabel.getHeight());
            coinCountLabel.setFontScale(0.6f, 0.6f);

            Image coinImage = new Image(AssetsManager.getAnimation(Constants.COIN_ASSETS_ID).getKeyFrames()[0]);
            coinImage.setBounds(getX() + 115, getY() + 10, coinImage.getWidth() - 3, coinImage.getHeight() - 3);
            getNextPrize.addActor(coinCountLabel);
            getNextPrize.addActor(coinImage);
        } else {
            Label coinCountLabel = new Label("", AssetsManager.getUiSkin());
            coinCountLabel.setBounds(getX() + 90 - coinCountLabel.getWidth() / 2, getY() + 5, coinCountLabel.getWidth(), coinCountLabel.getHeight());
            coinCountLabel.setFontScale(0.6f, 0.6f);

            getNextPrize.setText(GameManager.getStrings().get(Constants.GO_WIN_LBL) + "\n" + GameManager.getStrings().get(Constants.GO_PRIZE_LBL));
            getNextPrize.padRight(45f);
            Image coinImage = new Image(AssetsManager.getAnimation(Constants.COIN_ASSETS_ID).getKeyFrames()[0]);
            coinImage.setBounds(getX() + 115, getY() + 10, coinImage.getWidth() - 3, coinImage.getHeight() - 3);
            textButtonStyle.down = new Image(AssetsManager.getTextureRegion(Constants.BTTN_WIN_A_PRIZE_PRESSED_ID).getTexture()).getDrawable();
            textButtonStyle.up = new Image(AssetsManager.getTextureRegion(Constants.BTTN_WIN_A_PRIZE_ID)).getDrawable();
            getNextPrize.addActor(coinCountLabel);
            getNextPrize.addActor(coinImage);
        }


        getNextPrize.setBounds(x - AssetsManager.getTextureRegion(Constants.BTTN_GET_BONUS_PRESSED_ID).getTexture().getWidth() / 2, y - AssetsManager.getTextureRegion(Constants.BTTN_GET_BONUS_PRESSED_ID).getTexture().getHeight() / 2, AssetsManager.getTextureRegion(Constants.BTTN_GET_BONUS_PRESSED_ID).getTexture().getWidth(), AssetsManager.getTextureRegion(Constants.BTTN_GET_BONUS_PRESSED_ID).getTexture().getHeight());
//        if (coinCount <= 0)
        getNextPrize.addListener(new ClickListener() {

                                     @Override
                                     public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                                         AssetsManager.playSound(Constants.SOUND_CLICK);
                                         getNextPrizeSequence.addAction(Actions.delay(0.3f));
                                         getNextPrizeSequence.addAction(new Action() {
                                             @Override
                                             public boolean act(float delta) {
                                                 gsm.push(new CarGarageState(gsm, actionResolver, false));
                                                 GameManager.pauseGame = false;
                                                 return true;
                                             }
                                         });
                                         getNextPrizeSequence.addAction(Actions.removeActor());
                                         addAction(getNextPrizeSequence);

                                         return true;
                                     }
                                 }
        );


        addActor(getNextPrize);

    }


    public void setUpAchive() {
        achive.setX(Constants.GAME_OVER_TOTAL_ACHIVE_X_VISIBLE);
        achive.setY(Constants.GAME_OVER_TOTAL_ACHIVE_Y_VISIBLE);
        achive.setAlignment(Align.center, Align.center);
        achive.setText(GameManager.getStrings().get(Constants.GO_SCORE_LBL));
        achive.setColor(Color.ORANGE);
        achive.setFontScale(1.5f, 1.5f);
        addActor(achive);
    }

    public void setUpImageCoinCount() {
        imageButton = new ImageButton(new Image(AssetsManager.getTextureRegion(Constants.COIN_ICON_1_NAME_ID)).getDrawable());
        //imageButton.setBounds(labelCoinCount.getX() + 50, labelCoinCount.getY() - 2, imageButton.getWidth(), imageButton.getHeight());
        imageButton.setBounds(GameRuners.WIDTH / 2 - 25, GameRuners.HEIGHT / 2 - 30, imageButton.getWidth(), imageButton.getHeight());
        addActor(imageButton);
    }

    public void setUpCoinCountLabel() {
        labelCoinCount = new Label(String.valueOf(GameManager.getCoinCounter()), AssetsManager.getUiSkin());
        labelCoinCount.setFontScale(0.60f, 0.60f);
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


    public void setUpLeaderBoard() {
        LeaderboardMenu leaderboardMenu = new LeaderboardMenu(gsm, actionResolver);
        addActor(leaderboardMenu);
    }

    public void setUpAchiveCount() {
        achiveCount.setText(String.valueOf((int) GameManager.getAchives()));
        achiveCount.setX(Constants.GAME_OVER_TOTAL_ACHIVE_COUNT_X_VISIBLE - achiveCount.getPrefWidth() / 2);
        achiveCount.setY(Constants.GAME_OVER_TOTAL_ACHIVE_COUNT_Y_VISIBLE);

        achiveCount.setFontScale(0.9f, 0.9f);
        addActor(achiveCount);
    }

    public void setUpBestAchive() {
        bestAchive.setX(Constants.GAME_OVER_BEST_ACHIVE_X_VISIBLE);
        bestAchive.setY(Constants.GAME_OVER_BEST_ACHIVE_Y_VISIBLE);
        bestAchive.setText(GameManager.getStrings().get(Constants.GO_TOP_LBL));
        bestAchive.setAlignment(Align.center, Align.center);
        bestAchive.setFontScale(0.5f, 0.5f);
        bestAchive.setColor(Color.WHITE);
        addActor(bestAchive);
    }

    public void setUpBestAchiveCount() {
        bestAchiveCount.setText(String.valueOf((int) GameManager.getBestAchives()));
        actionResolver.submitScore((int) GameManager.getBestAchives());
        bestAchiveCount.setX(Constants.GAME_OVER_BEST_ACHIVE_COUNT_X_VISIBLE - bestAchiveCount.getPrefWidth() / 2);
        bestAchiveCount.setY(Constants.GAME_OVER_BEST_ACHIVE_COUNT_Y_VISIBLE);

        bestAchiveCount.setFontScale(1f, 1f);
        addActor(bestAchiveCount);
    }

    private void setUpImageLogo() {

        Texture logo = new Texture("game_over.png");
        imageLogo = new Image(logo);
        imageLogo.setBounds(Constants.GAME_OVER_LOGO_POSITION_X, Constants.GAME_OVER_LOGO_POSITION_Y, imageLogo.getWidth(), imageLogo.getHeight());
        addActor(imageLogo);
    }

    private void setFreeForPrize(float x, float y) {

        Texture saveMeBonus = new Texture("free_for_prize.png");

        saveMeBonus.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        imageLogo = new Image(saveMeBonus);
        imageLogo.setAlign(Align.top);
        imageLogo.setRotation(0.5f);
        imageLogo.setBounds(x, y, imageLogo.getWidth(), imageLogo.getHeight());
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

    private void setUpPrize(float x, float y) {

        // float x = Constants.SECOND_POSITION_BTTN_X_VISIBLE, y = Constants.SECOND_POSITION_BTTN_Y_VISIBLE, width = 70, height = 55;
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.down = getPrizeDownButtonImage.getDrawable();
        textButtonStyle.up = getPrizeUpButtonImage.getDrawable();
        textButtonStyle.font = AssetsManager.getUiSkin().getFont("default-font");

        prizeButton = new TextButton(GameManager.getStrings().get(Constants.GO_WIN_LBL) + "\n" + GameManager.getStrings().get(Constants.GO_PRIZE_LBL), textButtonStyle);
        prizeButton.getLabel().setFontScale(0.4f, 0.4f);
        prizeButton.getLabelCell().padLeft(2f);


        prizeButton.setBounds(x - resumeButtonUpImage.getWidth() / 2, y - resumeButtonUpImage.getHeight() / 2, resumeButtonUpImage.getWidth(), resumeButtonUpImage.getHeight());
        prizeButton.addListener(new ClickListener() {

                                    @Override
                                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                                        AssetsManager.playSound(Constants.SOUND_CLICK);
                                        sequencePrizeButton.addAction(Actions.delay(0.3f));
                                        sequencePrizeButton.addAction(new Action() {
                                            @Override
                                            public boolean act(float delta) {
                                                gsm.push(new CarGarageState(gsm, actionResolver, true));
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

//    private void setUpVkShare() {
//
//        float x = Constants.VK_BTTN_X_VISIBLE_SECOND_LINE, y = Constants.VK_BTTN_Y_VISIBLE_SECOND_LINE;
//        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
//        Image down = new Image(AssetsManager.getTextureRegion(Constants.BTTN_VK_PRESSED_ID).getTexture());
//        textButtonStyle.down = down.getDrawable();
//        textButtonStyle.up = new Image(AssetsManager.getTextureRegion(Constants.BTTN_VK_PRESSED_ID).getTexture()).getDrawable();
//        textButtonStyle.font = AssetsManager.getUiSkin().getFont("default-font");
//
//        vkBttn = new TextButton("   Share \n in VK", textButtonStyle);
//        vkBttn.getLabel().setFontScale(0.4f, 0.4f);
//        vkBttn.getLabelCell().padLeft(10f);
//
//
//        vkBttn.setBounds(x - down.getWidth() / 2, y - down.getHeight() / 2, down.getWidth(), down.getHeight());
//        vkBttn.addListener(new ClickListener() {
//
//                               @Override
//                               public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//                                   vkSequenceButton.addAction(Actions.delay(0.3f));
//                                   vkSequenceButton.addAction(new Action() {
//                                       @Override
//                                       public boolean act(float delta) {
//                                           actionResolver.sendPostOnVk();
//                                           //actionResolver.showInviteBox();
//                                           return true;
//                                       }
//                                   });
//                                   vkSequenceButton.addAction(Actions.removeActor());
//                                   addAction(vkSequenceButton);
//
//                                   return true;
//                               }
//                           }
//        );
//
//        addActor(vkBttn);
//
//    }

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
                                    AssetsManager.playSound(Constants.SOUND_CLICK);
                                    sequenceCarShop.addAction(Actions.delay(0.3f));
                                    sequenceCarShop.addAction(new Action() {
                                        @Override
                                        public boolean act(float delta) {
                                            gsm.push(new CarShopState(gsm, actionResolver));
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
        coinShop.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                AssetsManager.playSound(Constants.SOUND_CLICK);
                // sequenceCoinShop.addAction(Actions.delay(0.1f));
                sequenceCoinShop.addAction(new Action() {
                    @Override
                    public boolean act(float delta) {

                        getStage().addActor(new CoinShopState(gsm, actionResolver, groupView));
                        Actions.removeActor(groupView);
//                        gsm.push(new CarShopState(gsm, actionResolver));
                        GameManager.pauseGame = false;
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
                super.touchDown(event, x, y, pointer, button);
                addActor(new RecordsMenu(gsm, actionResolver, groupView));
                return true;
            }
        });
        addActor(leaderBoards);

    }


    private void setUpResume() {


        ImageButton resumeImageButton = new ImageButton(new Image(AssetsManager.getTextureRegion(Constants.RESUME_BTTN_ID)).getDrawable(), new Image(AssetsManager.getTextureRegion(Constants.RESUME_BTTN_ID)).getDrawable());
        resumeImageButton.setBounds(GameRuners.WIDTH / 4 - 45, GameRuners.HEIGHT / 4 - 217, resumeImageButton.getWidth(), resumeImageButton.getHeight());

        float x = Constants.RESUME_BTTN_X_VISIBLE, y = Constants.RESUME_BTTN_Y_VISIBLE, width = 70, height = 55;
//        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
//        textButtonStyle.down =new Image(AssetsManager.getTextureRegion(Constants.RESUME_BTTN_PRESSED_ID)).getDrawable();
//        textButtonStyle.up = new Image(AssetsManager.getTextureRegion(Constants.RESUME_BTTN_ID)).getDrawable();
//        textButtonStyle.font = AssetsManager.getUiSkin().getFont("default-font");
//
//        resumeButton = new TextButton("", textButtonStyle);
//        resumeButton.getLabel().setFontScale(0.6f, 0.6f);
//        resumeButton.getLabelCell().padLeft(25f);


        // resumeButton.setBounds(x - resumeButtonUpImage.getWidth() / 2, y - resumeButtonUpImage.getHeight() / 2, resumeButtonUpImage.getWidth(), resumeButtonUpImage.getHeight());

        resumeImageButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                sequence.addAction(Actions.delay(0.3f));

                sequence.addAction(new Action() {
                    @Override
                    public boolean act(float delta) {
                        AssetsManager.playSound(Constants.SOUND_CLICK);
                        GameManager.setDefaultSpeed();
                        GameManager.pauseGame = false;
                        GameManager.resetTime();
                        gsm.set(new GameState(gsm, false, true, actionResolver));

                        return true;

                    }
                });
                sequence.addAction(Actions.removeActor());
                addAction(sequence);

                return true;
            }
        });

        addActor(resumeImageButton);

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
