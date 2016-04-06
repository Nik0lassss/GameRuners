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

package com.nicholaschirkevich.game.menu;

import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.nicholaschirkevich.game.interfaces.ResumeButtonListener;
import com.nicholaschirkevich.game.util.Constants;

public class ResumeButton extends GameButton {
    private float x, y, width, height;


    private ResumeButtonListener listener;

    public ResumeButton(float x, float y, float width, float height, ResumeButtonListener listener) {
        super(x, y, width, height);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.listener = listener;
    }

    @Override
    protected String getRegionName() {
        //return GameManager.getInstance().getGameState() == GameState.PAUSED ? Constants.PLAY_REGION_NAME : Constants.PAUSE_REGION_NAME;
        return Constants.BTTN_RESUME_REGION_NAME;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
//        if (GameManager.getInstance().getGameState() == GameState.OVER) {
//            remove();
//        }
    }


    public void show()
    {
        MoveToAction moveDown = new MoveToAction();
        moveDown.setPosition(x, Constants.RESUME_BTTN_Y_VISIBLE);
        moveDown.setDuration(0.4f);
        addAction(moveDown);
    }

    public void hide()
    {
        MoveToAction moveDown = new MoveToAction();
        moveDown.setPosition(x, Constants.RESUME_BTTN_Y_INVISIBLE);
        moveDown.setDuration(0.4f);
        addAction(moveDown);
    }
    @Override
    public void touched() {
        System.out.println("Touched");
        hide();
        listener.resumeButtonOnResume();
//        if (GameManager.getInstance().getGameState() == GameState.PAUSED) {
//            listener.resumeButtonOnResume();
//        } else {
//            listener.resumeButtonOnPause();
//        }
    }

}
