package com.nicholaschirkevich.game.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.nicholaschirkevich.game.GameRuners;
import com.nicholaschirkevich.game.action.ViewActionAlfa;
import com.nicholaschirkevich.game.action.ViewActorActionAlfa;
import com.nicholaschirkevich.game.admob.ActionResolver;
import com.nicholaschirkevich.game.util.AssetsManager;
import com.nicholaschirkevich.game.util.Constants;

/**
 * Created by Колян on 20.09.2016.
 */
public class ToastMessage extends Group {
    private Label messageLabel;
    private ToastMessageRectangle toastRectangle;
    private Image background;
    private Image progressBarImage;
    private ActionResolver actionResolver;
    private Group group;
    private float progressBarDelta = 0f;

    @Override
    public void act(float delta) {
        super.act(delta);
        progressBarDelta += delta;
        if (progressBarDelta > 0.1) {
            progressBarDelta = 0;
            if (progressBarImage != null)
                progressBarImage.setRotation(progressBarImage.getRotation() - 20f);
        }
    }

    public ToastMessage(String message, ActionResolver actionResolver) {
        messageLabel = new Label(message, AssetsManager.getUiSkin());

        messageLabel.setWrap(true);
        messageLabel.pack();

        messageLabel.setAlignment(Align.center);
        messageLabel.setPosition(40, 180);
        messageLabel.setFontScale(0.6f,0.6f);
        messageLabel.setWidth(250);
        messageLabel.setHeight(100);

        toastRectangle = new ToastMessageRectangle();


        group = new Group();

        setUpBackgroung();
        this.actionResolver = actionResolver;
        group.addActor(toastRectangle);
        setUpProgressBar();

        group.addActor(messageLabel);
//        SequenceAction sequenceAction = new SequenceAction();
//        sequenceAction.addAction(Actions.delay(2f));
//        sequenceAction.addAction(new ViewActorActionAlfa(group));
//        group.addAction(sequenceAction);
        addActor(group);
//        addActor(messageLabel);
//        addActor(toastRectangle);
    }


    void setUpProgressBar() {
        progressBarImage = new Image(AssetsManager.getTextureRegion(Constants.PROGRESS_CIRCLE_ID));
        progressBarImage.setScale(0.25f, 0.25f);
        progressBarImage.setOrigin(progressBarImage.getWidth() / 2, progressBarImage.getHeight() / 2);
        progressBarImage.setAlign(Align.center);
        progressBarImage.setPosition(GameRuners.WIDTH / 4 - progressBarImage.getPrefWidth() / 2, GameRuners.HEIGHT / 4 - progressBarImage.getPrefHeight() / 2 - 90);
        group.addActor(progressBarImage);
        if (!actionResolver.isAvailibleInternet()) progressBarImage.setVisible(false);
    }

    public void disableProgressBar()
    {
        progressBarImage.setVisible(false);
    }
    public void setText(final String text) {

        messageLabel.setText(text);

        messageLabel.setVisible(true);
    }

    public void setAlfa(float alfa) {
        toastRectangle.setAlfa(alfa);
    }

    public void resetText() {
        messageLabel.setText("");
    }


    private void setUpBackgroung() {

        background = new Image(AssetsManager.getTextureRegion(Constants.BACK_TILE_ID));
        Color color = background.getColor();
        background.setColor(color.r, color.g, color.b, 0.5f);
        background.setBounds(0, -20, GameRuners.WIDTH / 2, GameRuners.HEIGHT / 2 + 50);
        addActor(background);

    }
}
