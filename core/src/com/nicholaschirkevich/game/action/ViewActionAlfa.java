package com.nicholaschirkevich.game.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * Created by Nikolas on 02.06.2016.
 */
public class ViewActionAlfa extends Action {
    private Label textLabel;
    private float alfa = 1;

    public ViewActionAlfa(Label label) {
        this.textLabel = label;
    }

    @Override
    public boolean act(float delta) {
        if (alfa > 0)
        {
            alfa -= 0.05f;
            textLabel.setColor(textLabel.getColor().r, textLabel.getColor().g, textLabel.getColor().b, alfa);
            return false;
        }else  return true;

    }
}
