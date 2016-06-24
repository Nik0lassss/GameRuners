package com.nicholaschirkevich.game.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Queue;
import com.nicholaschirkevich.game.GameRuners;
import com.nicholaschirkevich.game.action.ViewActionAlfa;
import com.nicholaschirkevich.game.entity.ToastMessage;
import com.nicholaschirkevich.game.enums.ToastLength;


/**
 * Created by Nikolas on 18.06.2016.
 */
public class ToastHelper {

    private static Queue<ToastMessage> messagesUp;
    private static Queue<ToastMessage> messagesCenter;
    private static Label upLabel = new Label("", AssetsManager.getUiSkin());
    private static Label upLabelSecond = new Label("", AssetsManager.getUiSkin());
    private static Label centerLabel = new Label("", AssetsManager.getUiSkin());
    private static float scaleXUpLabel = 0.7f;
    private static float scaleYUpLabel = 0.7f;
    private static SequenceAction sequenceUpLabelAction;
    private static SequenceAction sequenceUpLabelSecondAction;
    private static SequenceAction sequenceCenterLabelAction;
    private static Stage mainStage;



    public static void setUpToastHelper(Stage stage) {
        upLabel.setPosition(GameRuners.WIDTH / 4, GameRuners.HEIGHT / 4+ 200);
        upLabel.setFontScale(scaleXUpLabel, scaleYUpLabel);
        upLabelSecond.setFontScale(scaleXUpLabel,scaleYUpLabel);
        sequenceUpLabelAction = new SequenceAction();
        sequenceUpLabelSecondAction =new SequenceAction();
        sequenceCenterLabelAction = new SequenceAction();
        mainStage = stage;
        messagesUp = new Queue<ToastMessage>();
        messagesCenter = new Queue<ToastMessage>();
    }

    public static void showUpMessage(String message,String secondLineMessage,Color color, Color colorSecondLine, ToastLength toastLength) {
        messagesUp.addLast(new ToastMessage(message, secondLineMessage, color, colorSecondLine, toastLength));
    }

    public static void showCenterMessage(String message,String secondLineMessage,Color color, Color colorSecondLine, ToastLength toastLength) {
        messagesCenter.addLast(new ToastMessage(message, secondLineMessage, color, colorSecondLine, toastLength));
    }

    public static void onAct(float dt) {
        if (messagesUp.size!=0) {
            sequenceUpLabelAction.reset();
            sequenceUpLabelSecondAction.reset();
            showUpMessage(messagesUp.first());
            showUpSecondMessage(messagesUp.first());
            messagesUp.clear();
        }
        if(messagesCenter.size!=0)
        {
            sequenceCenterLabelAction.reset();
            showCenterMessage(messagesCenter.first());
            messagesCenter.clear();
        }
    }


    private static void showUpMessage(ToastMessage toastMessage) {
        upLabel.setColor(toastMessage.getColor());
        upLabel.setText(toastMessage.getMessage());
        upLabel.setPosition(GameRuners.WIDTH / 4, GameRuners.HEIGHT / 4 + 200);
        upLabel.setAlignment(Align.center,Align.center);
        sequenceUpLabelAction.addAction(Actions.delay(toastMessage.getLength().getLength()));
        sequenceUpLabelAction.addAction(new ViewActionAlfa(upLabel));
        sequenceUpLabelAction.addAction(new Action() {
            @Override
            public boolean act(float delta) {
                upLabel.setText("");
                sequenceUpLabelAction.reset();
                return true;
            }
        });
        upLabel.addAction(sequenceUpLabelAction);
        mainStage.addActor(upLabel);
    }

    private static void showUpSecondMessage(ToastMessage toastMessage) {
        upLabelSecond.setColor(toastMessage.getColorSecondLine());
        upLabelSecond.setText(toastMessage.getMessageSecondLine());
        upLabelSecond.setPosition(GameRuners.WIDTH / 4, GameRuners.HEIGHT / 4 + 160);
        upLabelSecond.setAlignment(Align.center,Align.center);
        //upLabelSecond.setX(upLabelSecond.getX() - upLabelSecond.getPrefWidth() / 2);
        sequenceUpLabelSecondAction.addAction(Actions.delay(toastMessage.getLength().getLength()));
        sequenceUpLabelSecondAction.addAction(new ViewActionAlfa(upLabelSecond));
        sequenceUpLabelSecondAction.addAction(new Action() {
            @Override
            public boolean act(float delta) {
                upLabelSecond.setText("");
                sequenceUpLabelSecondAction.reset();
                return true;
            }
        });
        upLabelSecond.addAction(sequenceUpLabelSecondAction);
        mainStage.addActor(upLabelSecond);
    }
    private static void showCenterMessage(final ToastMessage toastMessage) {
        centerLabel.setColor(toastMessage.getColor());
        centerLabel.setText(toastMessage.getMessage());
        centerLabel.setPosition(GameRuners.WIDTH / 4, GameRuners.HEIGHT / 4);
        centerLabel.setFontScale(0.6f, 0.6f);
        centerLabel.setAlignment(Align.center,Align.center);
        //centerLabel.setX(centerLabel.getX() - centerLabel.getPrefWidth() / 2);
        sequenceCenterLabelAction.addAction(Actions.delay(toastMessage.getLength().getLength()));
        sequenceCenterLabelAction.addAction(new ViewActionAlfa(centerLabel));
        sequenceCenterLabelAction.addAction(new Action() {
            @Override
            public boolean act(float delta) {
                centerLabel.setText("");
                return true;
            }
        });
        sequenceCenterLabelAction.addAction(new Action() {
            @Override
            public boolean act(float delta) {
                centerLabel.setText(toastMessage.getMessageSecondLine());
                centerLabel.setColor(toastMessage.getColorSecondLine());
                centerLabel.setAlignment(Align.center,Align.center);
                centerLabel.setPosition(GameRuners.WIDTH / 4, GameRuners.HEIGHT / 4);
                centerLabel.setX(centerLabel.getX());
                return true;
            }
        });
        sequenceCenterLabelAction.addAction(Actions.delay(toastMessage.getLength().getLength()));
        sequenceCenterLabelAction.addAction(new ViewActionAlfa(centerLabel));
        sequenceCenterLabelAction.addAction(new Action() {
            @Override
            public boolean act(float delta) {
                centerLabel.setText("");
                return true;
            }
        });
        centerLabel.addAction(sequenceCenterLabelAction);
        mainStage.addActor(centerLabel);
    }

    public static void resetToasts()
    {
        sequenceCenterLabelAction.reset();
        sequenceUpLabelAction.reset();
        sequenceUpLabelSecondAction.reset();
        centerLabel.setText("");
        upLabel.setText("");
        upLabelSecond.setText("");
    }
}
