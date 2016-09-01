/*
 * Copyright (c) 2014. William Mora
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nicholaschirkevich.game.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.nicholaschirkevich.game.GameRuners;
import com.nicholaschirkevich.game.action.ViewActionAlfa;
import com.nicholaschirkevich.game.enums.ToastLength;
import com.nicholaschirkevich.game.util.AssetsManager;
import com.nicholaschirkevich.game.util.Constants;
import com.nicholaschirkevich.game.util.GameManager;
import com.nicholaschirkevich.game.util.ToastHelper;


public class GearView extends Actor {

    public static final float GEAR_TEXT_FONT_SCALE_X = 0.6f;
    public static final float GEAR_TEXT_FONT_SCALE_Y = 0.6f;
    public static final float ACHIVE_LABEL_FONT_SCALE_X = 0.6f;
    public static final float ACHIVE_LABEL_FONT_SCALE_Y = 0.6f;
    public static final String X_2 = "x2";
    public static final String X_3 = "x3";
    public static final String X_4 = "x4";
    public static final String X_5 = "x5";
    public static final String X_6 = "x6";
    private TextureRegion textureRegion;
    private Label gearText, achiveLabel;
    private Rectangle bounds;
    private static float width = 64, height = 13;
    private String achivesText;


    public GearView(Rectangle bounds, String gearNumberText, final String achivesText) {
        this.bounds = bounds;
        this.achivesText = achivesText;
        gearText = new Label(gearNumberText, AssetsManager.getUiSkin());
        gearText.setBounds(GameRuners.WIDTH / 4 - gearText.getWidth() / 3, GameRuners.HEIGHT / 4 - (height / 2), width, height);
        gearText.setFontScale(GEAR_TEXT_FONT_SCALE_X, GEAR_TEXT_FONT_SCALE_Y);

        gearText.setColor(Color.ORANGE);

        achiveLabel = new Label(achivesText, AssetsManager.getUiSkin());
        achiveLabel.setBounds(GameRuners.WIDTH / 4 - achiveLabel.getWidth() / 2 + 10, GameRuners.HEIGHT / 4 - (height / 2), width, height);
        achiveLabel.setFontScale(ACHIVE_LABEL_FONT_SCALE_X, ACHIVE_LABEL_FONT_SCALE_Y);

        achiveLabel.setColor(achiveLabel.getColor().r, achiveLabel.getColor().g, achiveLabel.getColor().b, 0f);

        SequenceAction sequenceAction = new SequenceAction();
        sequenceAction.addAction(Actions.delay(1.5f));
        sequenceAction.addAction(new ViewActionAlfa(gearText));
        sequenceAction.addAction(Actions.delay(0.5f));
        sequenceAction.addAction(new Action() {
            @Override
            public boolean act(float delta) {
                achiveLabel.setColor(gearText.getColor().r, gearText.getColor().g, gearText.getColor().b, 1f);
                achiveLabel.setColor(Color.RED);
                achiveLabel.setText(achivesText);
                return true;
            }
        });
        sequenceAction.addAction(Actions.delay(1.5f));
        sequenceAction.addAction(new ViewActionAlfa(achiveLabel));
//
        sequenceAction.addAction(Actions.removeActor());
        addAction(sequenceAction);
        setWidth(bounds.width);
        setHeight(bounds.height);

    }

    public static void getView(int gear) {
        AssetsManager.playSound(Constants.SOUND_GEAR);
//
        switch (gear) {
            case 1:
                ToastHelper.showCenterMessage(GameManager.getStrings().get(Constants.GAME_GEAR_2_LBL), X_2, Color.ORANGE, Color.RED, ToastLength.TOAST_LONG);
            case 2:
                ToastHelper.showCenterMessage(GameManager.getStrings().get(Constants.GAME_GEAR_3_LBL), X_3, Color.ORANGE, Color.RED, ToastLength.TOAST_LONG);

            case 3:
                ToastHelper.showCenterMessage(GameManager.getStrings().get(Constants.GAME_GEAR_4_LBL), X_4, Color.ORANGE, Color.RED, ToastLength.TOAST_LONG);

            case 4:
                ToastHelper.showCenterMessage(GameManager.getStrings().get(Constants.GAME_GEAR_5_LBL), X_5, Color.ORANGE, Color.RED, ToastLength.TOAST_LONG);

            case 5:
                ToastHelper.showCenterMessage(GameManager.getStrings().get(Constants.GAME_GEAR_6_LBL), X_6, Color.ORANGE, Color.RED, ToastLength.TOAST_LONG);


        }

    }

    @Override
    public void act(float delta) {
        super.act(delta);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        gearText.draw(batch, parentAlpha);
        achiveLabel.draw(batch, parentAlpha);

    }
}
