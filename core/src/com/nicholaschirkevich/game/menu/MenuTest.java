package com.nicholaschirkevich.game.menu;

/**
 * Created by Nikolas on 20.04.2016.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.nicholaschirkevich.game.GameRuners;
import com.nicholaschirkevich.game.admob.ActionResolver;
import com.nicholaschirkevich.game.interfaces.ResumeButtonListener;
import com.nicholaschirkevich.game.states.CarShopState;
import com.nicholaschirkevich.game.states.CoinShopState;
import com.nicholaschirkevich.game.states.GameStateManager;
import com.nicholaschirkevich.game.util.AssetsManager;
import com.nicholaschirkevich.game.util.Constants;
import com.nicholaschirkevich.game.util.GameManager;

/**
 * Created by Nikolas on 10.03.2016.
 */
public class MenuTest extends Group {
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
    BitmapFont font = new BitmapFont(Gdx.files.internal("SRFont.fnt"), Gdx.files.internal("SRFont.png"), false);

    public MenuTest(ResumeButtonListener listener, GameStateManager gsm, ActionResolver actionResolver) {


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
        //setUpPlayOnline();
        //setUpPrize();
        //setUpSaveMe();
        setCarShop();
        setCoinShop();
        setSettingMenu();
        setLeaderBoard();
        setLeadersBoard();
        //setUpIcon();
        //setUpIconSpeed();
        //setUpIconWeight();
        //setUpIconSpeedBar();
        //setUpIconWeightBar();
        //setUpDelimiterSpeedBar();

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

//    private void setUpSaveMe() {
//
//        Texture saveMeBonus = new Texture("for_save_me_button_100.png");
//
//        saveMeBonus.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
//
//        imageLogo = new Image(saveMeBonus);
//        imageLogo.setAlign(Align.top);
//        imageLogo.setRotation(0.5f);
//        imageLogo.setBounds(Constants.SAVE_ME_BONUS_X, Constants.SAVE_ME_BONUS_Y, imageLogo.getWidth(), imageLogo.getHeight());
//        addActor(imageLogo);
//    }

    private void setUpBackgroung(boolean selected) {
        if (selected) {
            slot_vehicle = AssetsManager.getTextureRegion(Constants.SLOT_VEHICLE_SELECTED_ID).getTexture();
        } else
            slot_vehicle = AssetsManager.getTextureRegion(Constants.SLOT_VEHICLE_ID).getTexture();
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
        textButtonStyle.font = AssetsManager.getUiSkin().getFont("default-font");

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
//        String RUSSIAN_FONT_NAME = "fonts/SpeedyRoadStr.ttf";
//        String RUSSIAN_CHARACTERS = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ"
//                + "абвгдеёжзийклмнопрстуфхцчшщъыьэюя"
//                + "1234567890.,:;_¡!¿?\"'+-*/()[]={}";
//
//        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
//        parameter.characters = RUSSIAN_CHARACTERS;
//        parameter.size = 24;
//
//        // Generate font
//        FreeTypeFontGenerator generator = new FreeTypeFontGenerator( Gdx.files.internal(RUSSIAN_FONT_NAME) );
//        BitmapFont font = generator.generateFont(parameter);
//        // Dispose resources
//        generator.dispose();
        //BitmapFont bitmapFont =AssetsManager.getUiSkin().getFont("default-font");

        //BitmapFont font = new BitmapFont (Gdx.files.internal ("SRFont.fnt"), Gdx.files.internal ("SRFont.png"), false);
        textButtonStyle.font = textButtonStyle.font = AssetsManager.getUiSkin().getFont("default-font");
        ;

        String playOnlineText = GameManager.getStrings().get(Constants.GO_PLAY_WITH_FRIEND_2_BTN);
        //playOnline = new TextButton( GameManager.getStrings().get(Constants.GO_PLAY_WITH_FRIEND_2_BTN), textButtonStyle);
        playOnline = new TextButton(playOnlineText, textButtonStyle);
        playOnline.getLabel().setFontScale(0.5f, 0.4f);
        playOnline.getLabel().setColor(Color.WHITE);
        playOnline.getLabelCell().padLeft(35f);


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

                        getStage().addActor(new CoinShopState(listener, gsm, actionResolver));
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
                        getStage().addActor(new MenuSetting(listener, gsm, actionResolver,groupView));
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


}
