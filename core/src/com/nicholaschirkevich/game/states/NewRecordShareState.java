package com.nicholaschirkevich.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.nicholaschirkevich.game.GameRuners;
import com.nicholaschirkevich.game.action.ViewActorActionAlfa;
import com.nicholaschirkevich.game.admob.ActionResolver;
import com.nicholaschirkevich.game.entity.ImageByteEntity;
import com.nicholaschirkevich.game.interfaces.DialogMEssageCallback;
import com.nicholaschirkevich.game.interfaces.OnShareVkPost;
import com.nicholaschirkevich.game.interfaces.OnSharePost;
import com.nicholaschirkevich.game.interfaces.ResumeButtonListener;
import com.nicholaschirkevich.game.interfaces.UpdateCoinCountInterface;
import com.nicholaschirkevich.game.menu.DialogMessage;
import com.nicholaschirkevich.game.menu.MenuGameOver;
import com.nicholaschirkevich.game.menu.NewRecordScreenshotTotal;
import com.nicholaschirkevich.game.menu.ToastMessage;
import com.nicholaschirkevich.game.util.AssetsManager;
import com.nicholaschirkevich.game.util.Constants;
import com.nicholaschirkevich.game.util.GameManager;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Колян on 14.09.2016.
 */
public class NewRecordShareState extends Group implements UpdateCoinCountInterface, ResumeButtonListener, OnSharePost, DialogMEssageCallback, OnShareVkPost {
    private Image progressBarImage;
    private float progressBarDelta = 0f;
    private GameStateManager gsm;
    private ActionResolver actionResolver;
    private byte[] pixels;
    private Pixmap pixmap;
    private ImageButton imageButton;
    private Label labelCoinCount;
    private Group thisGroup;
    private ImageByteEntity imageByteEntity;
    private int width, height;
    private ImageButton back_button;
    final int shareImageWidth = 450, shareImageHeight = 565;
    boolean isGetScreenShot = false, isSetUpBackButton = false;
    byte[] pixels2;
    private Image background;
    private ToastMessage toastMessage;


    public NewRecordShareState(GameStateManager gsm, ActionResolver actionResolver, byte[] pixels, int width, int height, int countAchives) {
        this.gsm = gsm;
        this.actionResolver = actionResolver;
        this.pixels = pixels;
        this.thisGroup = this;
        this.width = width;
        this.height = height;


        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);

        BufferUtils.copy(pixels, 0, pixmap.getPixels(), pixels.length);
//
        imageByteEntity = new ImageByteEntity();
        Texture texture = new Texture(pixmap);
//
//        PixmapIO.writePNG(Gdx.files.external("mypixmap.png"), pixmap);
//        File file = Gdx.files.external("mypixmap.png").file();


//        pixmap.dispose();

        Image image = new Image(texture);

        image.setPosition(40, 175);
        image.setWidth(240);
        image.setHeight(280);
        setUpBackgroung();
        addActor(new NewRecordScreenshotTotal());
        addActor(image);


        setUpConuses();
        setUpSrLogo();
        setUpTextNewRecord();
        setUpShareButton();
        setUpImageCoinCount();
        setUpCoinCountLabel();
        setUpAchivesLabel(countAchives);


//        pixmap.dispose();

    }


    public void setUpBackButton() {
        Texture upTexture, downTexture;
        upTexture = AssetsManager.getTextureRegion(Constants.BUTTON_BACK_ID).getTexture();
        downTexture = AssetsManager.getTextureRegion(Constants.BUTTON_BACK_PRSSD_ID).getTexture();
        upTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        downTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        back_button = new ImageButton((new Image(upTexture)).getDrawable(), ((new Image(downTexture)).getDrawable()));
        back_button.setPosition(1, 465);
        back_button.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                super.touchDown(event, x, y, pointer, button);
                getStage().addActor(new MenuGameOver(gsm, (ResumeButtonListener) thisGroup, actionResolver));
                thisGroup.remove();


                return true;
            }
        });
        addActor(back_button);
    }

    public void setUpConuses() {
        int posX = 90, posY = 200;
        ArrayList<Image> conuses = new ArrayList<Image>();

        Image conus1 = new Image(AssetsManager.getTextureRegion(Constants.CONE_ID));
        conus1.setPosition(posX, posY);
        addActor(conus1);
        posX += 40;
        posY += 10;
        Image conus2 = new Image(AssetsManager.getTextureRegion(Constants.CONE_ID));
        conus2.setPosition(posX, posY);
        addActor(conus2);
        posX += 65;
        posY += -10;
        Image conus3 = new Image(AssetsManager.getTextureRegion(Constants.CONE_ID));
        conus3.setPosition(posX, posY);
        addActor(conus3);
        posX += 30;
        posY -= 5;
        Image conus4 = new Image(AssetsManager.getTextureRegion(Constants.CONE_ID));
        conus4.setPosition(posX, posY);
        addActor(conus4);


    }


    public void setUpTextNewRecord() {

        Label label = new Label(GameManager.getStrings().get(Constants.GO_N_LBL), AssetsManager.getUiSkin());
        Label label2 = new Label(GameManager.getStrings().get(Constants.GO_R_LBL), AssetsManager.getUiSkin());
        label.setColor(243f / 255f, 101f / 255f, 15f / 255f, 1f);
        label2.setColor(244f / 255f, 144f / 255f, 2f / 255f, 1f);
        label.setAlignment(Align.center);
        label2.setAlignment(Align.center);
        Container container = new Container(label);
        Container container2 = new Container(label2);
        container.setTransform(true);
        container2.setTransform(true);
        container.setPosition(70, 425);
        container2.setPosition(90, 405);
        container.addAction(Actions.rotateTo(45));
        container2.addAction(Actions.rotateTo(45));
        addActor(container);
        addActor(container2);
    }


    public void setUpSrLogo() {
        Image image = new Image(AssetsManager.getTextureRegion(Constants.MAIN_MENU_LOGO_ID).getTexture());
        image.setPosition(79, 95);
        image.setScale(0.6f, 0.6f);
        addActor(image);
    }

    public void setUpAchivesLabel(int count) {
        Label label = new Label(String.valueOf(count), AssetsManager.getUiSkin());
        label.setPosition(190, 420);
        label.setAlignment(Align.center);
        addActor(label);
    }

    public void setUpShareButton() {
        Texture upTexture, downTexture;
        upTexture = AssetsManager.getTextureRegion(Constants.BTTN_SHARE_ID).getTexture();
        downTexture = AssetsManager.getTextureRegion(Constants.BTTN_SHARE_PRSSD_ID).getTexture();
        upTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        downTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        ImageButton shareButton = new ImageButton((new Image(upTexture)).getDrawable(), ((new Image(downTexture)).getDrawable()));
        shareButton.setPosition(120, 10);

        shareButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                super.touchDown(event, x, y, pointer, button);


                Pixmap pixmapShare = new Pixmap(shareImageWidth, shareImageHeight, Pixmap.Format.RGBA8888);

                BufferUtils.copy(pixels2, 0, pixmapShare.getPixels(), pixels2.length);
//
                imageByteEntity = new ImageByteEntity();


                String path = "mypixmap" + TimeUtils.millis() + ".png";
                PixmapIO.writePNG(Gdx.files.external(path), pixmapShare);
                File fileShare = Gdx.files.external(path).file();

                imageByteEntity.setFile(fileShare);
                //Color color = background.getColor();
                //background.setColor(color.r, color.g, color.b, 0.95f);

                //actionResolver.sendPostOnVk(imageByteEntity);
                if (actionResolver.isFacebookLogin())
                    addActor(new DialogMessage((DialogMEssageCallback) thisGroup));

                else if (actionResolver.isVkLogin())
                    actionResolver.sendPostOnVk(imageByteEntity, (OnShareVkPost) thisGroup);

//                actionResolver.sendPostOnVk(imageByteEntity);

                //actionResolver.sendPostFb(imageByteEntity, (OnSharePost) thisGroup);
                return true;
            }
        });

        addActor(shareButton);
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
                CoinShopState coinShopState = new CoinShopState(gsm, actionResolver, thisGroup);


                addActor(coinShopState);

                return true;
            }
        });
        addActor(labelCoinCount);
    }


    @Override
    public void updateCoinCountView() {
        labelCoinCount.setText(String.valueOf(GameManager.getCoinCounter()));
        labelCoinCount.invalidate();
        labelCoinCount.setBounds(imageButton.getX() - labelCoinCount.getPrefWidth() - 5, imageButton.getY(), labelCoinCount.getWidth(), labelCoinCount.getHeight());
    }

    @Override
    public void resumeButtonOnResume() {

    }

    @Override
    public void onSaveMe() {

    }

    @Override
    public void act(float delta) {
        super.act(delta);
//        progressBarDelta += delta;
//        if (progressBarDelta > 0.1) {
//            progressBarDelta = 0;
//            if (progressBarImage != null)
//                progressBarImage.setRotation(progressBarImage.getRotation() - 20f);
//        }
        if (!isSetUpBackButton && isGetScreenShot) {
            isSetUpBackButton = true;
            setUpBackButton();
        }

    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (!isGetScreenShot) {
            isGetScreenShot = true;

            pixels2 = ScreenUtils.getFrameBufferPixels(15, 110, shareImageWidth, shareImageHeight, true);
        }
    }

    private void setUpBackgroung() {

        background = new Image(AssetsManager.getTextureRegion(Constants.BACK_TILE_ID));
        Color color = background.getColor();
        background.setColor(color.r, color.g, color.b, 0.5f);
        background.setBounds(0, -20, GameRuners.WIDTH / 2, GameRuners.HEIGHT / 2 + 50);
        addActor(background);

    }

    @Override
    public void onShare() {
        if (toastMessage != null) {
            SequenceAction sequenceAction = new SequenceAction();
            sequenceAction.addAction(Actions.delay(2f));
            sequenceAction.addAction(new ViewActorActionAlfa(toastMessage));
            sequenceAction.addAction(new ViewActorActionAlfa(thisGroup));
            sequenceAction.addAction(Actions.delay(0.2f));
            sequenceAction.addAction(new Action() {
                @Override
                public boolean act(float delta) {
                    toastMessage.remove();
                    getStage().addActor(new MenuGameOver(gsm, (ResumeButtonListener) thisGroup, actionResolver));
                    thisGroup.remove();
                    return true;
                }
            });
            toastMessage.addAction(sequenceAction);
            toastMessage.setText(GameManager.getStrings().get(Constants.PUBLISH_SUCCESS));
            toastMessage.disableProgressBar();
        } else {
            toastMessage = new ToastMessage(Constants.PUBLISH_SUCCESS, actionResolver);

            SequenceAction sequenceAction = new SequenceAction();
            sequenceAction.addAction(Actions.delay(2f));
            sequenceAction.addAction(new ViewActorActionAlfa(toastMessage));
            sequenceAction.addAction(new ViewActorActionAlfa(thisGroup));
            sequenceAction.addAction(Actions.delay(0.2f));
            sequenceAction.addAction(new Action() {
                @Override
                public boolean act(float delta) {
                    toastMessage.remove();
                    getStage().addActor(new MenuGameOver(gsm, (ResumeButtonListener) thisGroup, actionResolver));
                    thisGroup.remove();

                    return true;
                }
            });
            Color color = background.getColor();
            background.setColor(color.r, color.g, color.b, 0.5f);
            toastMessage.disableProgressBar();

            toastMessage.addAction(sequenceAction);
            addActor(toastMessage);
        }
//        toastMessage = new ToastMessage("Success", actionResolver);
//
//        SequenceAction sequenceAction = new SequenceAction();
//        sequenceAction.addAction(Actions.delay(2f));
//        sequenceAction.addAction(new ViewActorActionAlfa(toastMessage));
//        Color color = background.getColor();
//        background.setColor(color.r, color.g, color.b, 0.5f);
//
//
//        toastMessage.addAction(sequenceAction);
//        addActor(toastMessage);

//        ToastMessage toastMessage = new ToastMessage("Success");
//        SequenceAction sequenceAction = new SequenceAction();
//        sequenceAction.addAction(Actions.delay(2f));
//        sequenceAction.addAction(new ViewActorActionAlfa(toastMessage));
//        toastMessage.addAction(sequenceAction);
//        addActor(toastMessage);
    }

    @Override
    public void onError(String error) {
        if (actionResolver.isAvailibleInternet()) {
            actionResolver.sendPostFb(imageByteEntity, (OnSharePost) thisGroup);
        } else if (toastMessage != null) {
            SequenceAction sequenceAction = new SequenceAction();
            sequenceAction.addAction(Actions.delay(2f));
            sequenceAction.addAction(new ViewActorActionAlfa(toastMessage));
            toastMessage.addAction(sequenceAction);
            toastMessage.setText(GameManager.getStrings().get(Constants.ERROR_ALERT) + "\n" + GameManager.getStrings().get(Constants.ERROR_FB_TRY));
            toastMessage.disableProgressBar();
        } else {
            toastMessage = new ToastMessage(GameManager.getStrings().get(Constants.ERROR_ALERT) + "\n" + GameManager.getStrings().get(Constants.ERROR_FB_TRY), actionResolver);
            toastMessage.disableProgressBar();
            SequenceAction sequenceAction = new SequenceAction();
            sequenceAction.addAction(Actions.delay(2f));
            sequenceAction.addAction(new ViewActorActionAlfa(toastMessage));
            Color color = background.getColor();
            background.setColor(color.r, color.g, color.b, 0.5f);


            toastMessage.addAction(sequenceAction);
            addActor(toastMessage);
        }
//        Gdx.app.postRunnable(new Runnable() {
//            @Override
//            public void run() {
////                ToastMessage toastMessage = new ToastMessage("errore");
////                SequenceAction sequenceAction = new SequenceAction();
////                sequenceAction.addAction(Actions.delay(2f));
////                sequenceAction.addAction(new ViewActorActionAlfa(toastMessage));
////                toastMessage.addAction(sequenceAction);
////                addActor(toastMessage);
//
////                addActor(new ToastMessage("errore"));
//            }
//        });

    }

    @Override
    public void onYes() {


        toastMessage = new ToastMessage("Please Wait", actionResolver);

        Color color = background.getColor();
        background.setColor(color.r, color.g, color.b, 0.5f);

        addActor(toastMessage);

        actionResolver.sendPostFb(imageByteEntity, (OnSharePost) thisGroup);
    }

    @Override
    public void onNo() {

    }

    @Override
    public void onShareVk() {
        SequenceAction sequenceAction = new SequenceAction();
        sequenceAction.addAction(new Action() {
            @Override
            public boolean act(float delta) {
                getStage().addActor(new MenuGameOver(gsm, (ResumeButtonListener) thisGroup, actionResolver));
                thisGroup.remove();

                return true;
            }
        });
        addAction(sequenceAction);
    }

    @Override
    public void onErrorShareVk() {

    }
}
