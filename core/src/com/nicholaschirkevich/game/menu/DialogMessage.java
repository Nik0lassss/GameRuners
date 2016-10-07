package com.nicholaschirkevich.game.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.nicholaschirkevich.game.GameRuners;
import com.nicholaschirkevich.game.action.ViewActorActionAlfa;
import com.nicholaschirkevich.game.admob.ActionResolver;
import com.nicholaschirkevich.game.interfaces.DialogMEssageCallback;
import com.nicholaschirkevich.game.util.AssetsManager;
import com.nicholaschirkevich.game.util.Constants;

/**
 * Created by Колян on 20.09.2016.
 */
public class DialogMessage extends Group {
    private TextButton yesButton;
    private TextButton noButton;
    private Label label;
    private ToastRectangle toastRectangle;
    private Group thisGroup;

    private DialogMEssageCallback dialogMEssageCallback;

    private Image backgroundImage;

    public DialogMessage(final DialogMEssageCallback dialogMEssageCallback) {
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();

        this.thisGroup = this;
        this.dialogMEssageCallback = dialogMEssageCallback;
        Image background = new Image(AssetsManager.getTextureRegion(Constants.BACK_TILE_ID));
        Color color = background.getColor();
        background.setColor(color.r, color.g, color.b, 0.5f);
        background.setBounds(0, -20, GameRuners.WIDTH / 2, GameRuners.HEIGHT / 2 + 50);
        addActor(background);

//        textButtonStyle.down = soundButtonDownImage.getDrawable();
        //textButtonStyle.up = soundButtonUpImage.getDrawable();
        textButtonStyle.font = AssetsManager.getUiSkin().getFont("default-font");
        label = new Label("Share \n this image? ", AssetsManager.getUiSkin());
        label.setAlignment(Align.center);
        label.setColor(Color.ORANGE);
        label.setFontScale(0.6f,0.6f);
        label.setPosition(35, 220);

        yesButton = new TextButton("YES", textButtonStyle);
        yesButton.getLabel().setFontScale(0.6f,0.6f);
        yesButton.setPosition(55, 165);

        noButton = new TextButton("NO", textButtonStyle);
        noButton.setPosition(205, 165);
        noButton.getLabel().setFontScale(0.6f,0.6f);
        toastRectangle = new ToastRectangle();
        yesButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                super.touchDown(event, x, y, pointer, button);

                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        remove();
                    }
                });

                dialogMEssageCallback.onYes();


                return true;
            }
        });
        noButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                super.touchDown(event, x, y, pointer, button);
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        remove();
                    }
                });

                dialogMEssageCallback.onNo();

                return true;
            }
        });

        addActor(toastRectangle);
        addActor(yesButton);
        addActor(noButton);
        addActor(label);
    }

    public void setAlfa(float alfa) {
        toastRectangle.setAlfa(alfa);
    }


}
