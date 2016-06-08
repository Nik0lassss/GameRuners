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
import com.nicholaschirkevich.game.util.AssetsManager;
import com.nicholaschirkevich.game.util.Constants;
import com.nicholaschirkevich.game.util.GameManager;


public class GearView extends Actor {

    private TextureRegion textureRegion;
    private Label gearText,achiveLabel;
    private Rectangle bounds;
    private static float width = 64, height = 13;
    private String achivesText;


    public GearView(Rectangle bounds, String gearNumberText, final String achivesText) {
        this.bounds = bounds;
        this.achivesText = achivesText;
        gearText = new Label(gearNumberText, AssetsManager.getUiSkin());
        gearText.setBounds(GameRuners.WIDTH / 4 - gearText.getWidth() / 3, GameRuners.HEIGHT / 4 - (height / 2), width, height);
        gearText.setFontScale(0.6f, 0.6f);

        gearText.setColor(Color.ORANGE);

        achiveLabel = new Label(achivesText,AssetsManager.getUiSkin());
        achiveLabel.setBounds(GameRuners.WIDTH / 4 - achiveLabel.getWidth() / 2 +10, GameRuners.HEIGHT / 4 - (height / 2), width, height);
        achiveLabel.setFontScale(0.6f, 0.6f);

        achiveLabel.setColor(achiveLabel.getColor().r,achiveLabel.getColor().g,achiveLabel.getColor().b,0f);
        //gearText.setText(gearNumberText);

//        textureRegion = AssetsManager.getTextureRegion(assetsId);
//        gearText = new Label("",AssetsManager.getUiSkin());
//        gearText.setBounds(GameRuners.WIDTH/4-(width/2), GameRuners.HEIGHT/4-(height/2), width, height);
        SequenceAction sequenceAction = new SequenceAction();
        sequenceAction.addAction(Actions.delay(1.5f));
        sequenceAction.addAction(new ViewActionAlfa(gearText));
        sequenceAction.addAction(Actions.delay(0.5f));
        sequenceAction.addAction(new Action() {
            @Override
            public boolean act(float delta) {
                achiveLabel.setColor(gearText.getColor().r,gearText.getColor().g,gearText.getColor().b,1f);
                achiveLabel.setColor(Color.RED);
                achiveLabel.setText(achivesText);
                return true;
            }
        });
        sequenceAction.addAction(Actions.delay(1.5f));
        sequenceAction.addAction(new ViewActionAlfa(achiveLabel));
//        sequenceAction.addAction(new Action() {
//            @Override
//            public boolean act(float delta) {
//                float alfa = gearText.getColor().a;
//                if (alfa > 0) alfa -= 0.2f;
//                gearText.setColor(gearText.getColor().r, gearText.getColor().g, gearText.getColor().b, alfa);
//                return true;
//            }
//        });
        sequenceAction.addAction(Actions.removeActor());
        addAction(sequenceAction);
        setWidth(bounds.width);
        setHeight(bounds.height);

    }

    public static Actor getView(int gear) {
        AssetsManager.playSound(Constants.SOUND_GEAR);
//        //System.out.println("Get view gear "+gear);
//        switch (gear) {
//            case 1:
//                return new GearView(new Rectangle(GameRuners.WIDTH/4-(width/2), GameRuners.HEIGHT/4-(height/2), width, height), Constants.GEAR_1_ID);
//
//            case 2:
//                return new GearView(new Rectangle(GameRuners.WIDTH/4-(width/2), GameRuners.HEIGHT/4-(height/2), width, height), Constants.GEAR_2_ID);
//
//            case 3:
//                return new GearView(new Rectangle(GameRuners.WIDTH/4-(width/2), GameRuners.HEIGHT/4-(height/2), width, height), Constants.GEAR_3_ID);
//
//            case 4:
//                return new GearView(new Rectangle(GameRuners.WIDTH/4-(width/2), GameRuners.HEIGHT/4-(height/2), width, height), Constants.GEAR_4_ID);
//
//            case 5:
//                return new GearView(new Rectangle(GameRuners.WIDTH/4-(width/2), GameRuners.HEIGHT/4-(height/2), width, height), Constants.GEAR_5_ID);
//
//            case 6:
//                return new GearView(new Rectangle(GameRuners.WIDTH/4-(width/2), GameRuners.HEIGHT/4-(height/2), width, height), Constants.GEAR_6_ID);
//
//        }
//        return new GearView(new Rectangle(GameRuners.WIDTH/4-(width/2), GameRuners.HEIGHT/4-(height/2), width, height), Constants.GEAR_6_ID);
        //System.out.println("Get view gear "+gear);
        switch (gear) {
            case 1:
                return new GearView(new Rectangle(GameRuners.WIDTH / 4 - (width / 2), GameRuners.HEIGHT / 4 - (height / 2), width, height), GameManager.getStrings().get(Constants.GAME_GEAR_1_LBL),"x1");
            case 2:
                return new GearView(new Rectangle(GameRuners.WIDTH / 4 - (width / 2), GameRuners.HEIGHT / 4 - (height / 2), width, height), GameManager.getStrings().get(Constants.GAME_GEAR_2_LBL),"x2");

            case 3:
                return new GearView(new Rectangle(GameRuners.WIDTH / 4 - (width / 2), GameRuners.HEIGHT / 4 - (height / 2), width, height), GameManager.getStrings().get(Constants.GAME_GEAR_3_LBL),"x3");

            case 4:
                return new GearView(new Rectangle(GameRuners.WIDTH / 4 - (width / 2), GameRuners.HEIGHT / 4 - (height / 2), width, height), GameManager.getStrings().get(Constants.GAME_GEAR_4_LBL),"x4");

            case 5:
                return new GearView(new Rectangle(GameRuners.WIDTH / 4 - (width / 2), GameRuners.HEIGHT / 4 - (height / 2), width, height), GameManager.getStrings().get(Constants.GAME_GEAR_5_LBL),"x5");

            case 6:
                return new GearView(new Rectangle(GameRuners.WIDTH / 4 - (width / 2), GameRuners.HEIGHT / 4 - (height / 2), width, height), GameManager.getStrings().get(Constants.GAME_GEAR_6_LBL),"x6");

        }
        return new GearView(new Rectangle(GameRuners.WIDTH / 4 - (width / 2), GameRuners.HEIGHT / 4 - (height / 2), width, height), GameManager.getStrings().get(Constants.GAME_GEAR_6_LBL),"x6");

    }

    @Override
    public void act(float delta) {
        super.act(delta);
//        if (GameManager.getInstance().getGameState() == GameState.OVER) {
//            remove();
//        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        gearText.draw(batch, parentAlpha);
        achiveLabel.draw(batch,parentAlpha);
        //batch.draw(textureRegion, bounds.x, bounds.y, bounds.width, bounds.height);

    }
}
