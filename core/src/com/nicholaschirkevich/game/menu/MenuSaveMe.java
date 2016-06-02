package com.nicholaschirkevich.game.menu;

/**
 * Created by Nikolas on 20.04.2016.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
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
public class MenuSaveMe extends Group {


    TextButton saveMeButton;

    Texture slot_vehicle;
    Image background;
    Image saveMeButtonUpImage, saveMeButtonDownImage, saveMeBarImage, saveMeBarImageBorder;
    Texture saveMeButtonUpTexture, saveMeButtonDownTexture, saveMeBarTexture, saveMeBarBorderTexture;
    TextureRegion saveMeImageTextureRegion;
    SequenceAction saveMeSequence;
    float time = 0;
    float x = Constants.SAVE_ME_BAR_X_VISIBLE, y = Constants.SAVE_ME_BAR_Y_VISIBLE;
    private GameStateManager gsm;
    ResumeButtonListener resumeButtonListener;
    ActionResolver actionResolver;

    public MenuSaveMe(ResumeButtonListener listener, GameStateManager gsm, ActionResolver actionResolver) {

        this.actionResolver = actionResolver;
        saveMeButtonUpTexture = AssetsManager.getTextureRegion(Constants.BTTN_REVIVAL_ID).getTexture();
        saveMeButtonDownTexture =AssetsManager.getTextureRegion(Constants.BTTN_REVIVAL_PRESSED_ID).getTexture();
        saveMeButtonDownTexture =AssetsManager.getTextureRegion(Constants.BTTN_REVIVAL_PRESSED_ID).getTexture();
        saveMeBarTexture = AssetsManager.getTextureRegion(Constants.PROGRESS_BAR_ID).getTexture();
        saveMeBarBorderTexture =AssetsManager.getTextureRegion(Constants.PROGRESS_BAR_FRAME_ID).getTexture();
        saveMeImageTextureRegion = new TextureRegion(saveMeBarTexture);
        saveMeBarImage = new Image(saveMeImageTextureRegion);
        saveMeBarImageBorder = new Image(saveMeBarBorderTexture);
        saveMeButtonUpImage = new Image(saveMeButtonUpTexture);
        saveMeButtonDownImage = new Image(saveMeButtonDownTexture);
        saveMeSequence = new SequenceAction();
        resumeButtonListener = listener;
        setUpBackgroung(false);
        setUpSaveMeBar();
        setUpSaveMeButton();


        this.gsm = gsm;
        setBounds(0, 0, GameRuners.WIDTH / 2, GameRuners.HEIGHT / 2);
    }

    public void setUpSaveMeButton() {
        float x = Constants.SAVE_ME_BTTN_X_VISIBLE, y = Constants.SAVE_ME_BTTN_Y_VISIBLE;
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.down = saveMeButtonDownImage.getDrawable();
        textButtonStyle.up = saveMeButtonUpImage.getDrawable();
        textButtonStyle.font = AssetsManager.getUiSkin().getFont("default-font");


        saveMeButton = new TextButton(GameManager.getStrings().get(Constants.GO_SAVE_LBL), textButtonStyle);
        saveMeButton.setWidth(saveMeBarImageBorder.getWidth());
        saveMeButton.addListener(new ClickListener() {

                                     @Override
                                     public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                                         saveMeSequence.addAction(Actions.delay(0.3f));
                                         saveMeSequence.addAction(new Action() {
                                             @Override
                                             public boolean act(float delta) {
//                                                 resumeButtonListener.onSaveMe();
//                                                 GameManager.pauseGame = false;
//                                                 GameManager.setIsCollision(false);
                                                 actionResolver.showOrLoadInterstital();
                                                 return true;
                                             }
                                         });
                                         saveMeSequence.addAction(Actions.removeActor());
                                         addAction(saveMeSequence);

                                         return true;
                                     }
                                 }
        );
        saveMeButton.getLabel().setFontScale(0.4f, 0.4f);
        saveMeButton.getLabelCell().padLeft(25f);
        saveMeButton.setBounds(x - saveMeButton.getWidth() / 2, y - saveMeButton.getHeight() / 2, saveMeButton.getWidth(), saveMeButton.getHeight());
        addActor(saveMeButton);
    }


    public void setUpSaveMeBar() {

        float xBarBorder = Constants.SAVE_ME_BAR_BORDER_X_VISIBLE, yBarBorder = Constants.SAVE_ME_BAR_BORDER_Y_VISIBLE;
        saveMeBarImage.setX(x);
        saveMeBarImage.setY(y);
        saveMeBarImageBorder.setX(xBarBorder);
        saveMeBarImageBorder.setY(yBarBorder);
        addActor(saveMeBarImage);
        addActor(saveMeBarImageBorder);
    }

    @Override
    public void act(float delta) {

        super.act(delta);

        int width = saveMeImageTextureRegion.getRegionWidth();
        time += delta;
        if (time > 0.02) {
            if (width > 0)
                width -= 0.01;
            else {
                getStage().addActor(new MenuGameOver(gsm, actionResolver));
                remove();
                System.out.println("getStage().addActor(new MenuGameOver(gsm));");
            }
            time = 0;
        }
        saveMeBarImage.remove();
        saveMeImageTextureRegion = new TextureRegion(saveMeBarTexture);
        saveMeImageTextureRegion.setRegionWidth(width);
        saveMeBarImage = new Image(saveMeImageTextureRegion);
        saveMeBarImage.setX(x);
        saveMeBarImage.setY(y);
        addActor(saveMeBarImage);
        System.out.println("width " + width);

    }


    public void onAdCLose()
    {
        resumeButtonListener.onSaveMe();
        GameManager.pauseGame = false;
        GameManager.setIsCollision(false);
    }
    private void setUpBackgroung(boolean selected) {
        if (selected) {
            slot_vehicle = new Texture("slot_vehicle_2_selected.png");
        } else slot_vehicle = new Texture("slot_vehicle.png");
        background = new Image(slot_vehicle);
        background.setBounds(0, -20, GameRuners.WIDTH / 2, GameRuners.HEIGHT / 2 + 50);
        background.addListener(new ClickListener() {

                                   @Override
                                   public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                                       getStage().addActor(new MenuGameOver(gsm, actionResolver));
                                       remove();
                                       return true;
                                   }
                               }
        );
        addActor(background);

    }

}
