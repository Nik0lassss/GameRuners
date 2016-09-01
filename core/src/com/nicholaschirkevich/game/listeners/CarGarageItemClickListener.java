package com.nicholaschirkevich.game.listeners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by Nikolas on 12.03.2016.
 */
public class CarGarageItemClickListener extends ClickListener {

    private com.nicholaschirkevich.game.interfaces.UpdateGarageCarItem updateGarageCarItem;

    public CarGarageItemClickListener(com.nicholaschirkevich.game.interfaces.UpdateGarageCarItem updateGarageCarItem) {
        this.updateGarageCarItem = updateGarageCarItem;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {

        updateGarageCarItem.onUpdateGarageCarItem(true);

    }
}
