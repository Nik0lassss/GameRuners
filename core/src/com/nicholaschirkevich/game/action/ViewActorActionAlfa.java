package com.nicholaschirkevich.game.action;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.nicholaschirkevich.game.menu.ToastMessage;

/**
 * Created by Nikolas on 02.06.2016.
 */
public class ViewActorActionAlfa extends Action {
    private Group group;
    private float alfa = 1;

    public ViewActorActionAlfa(Group group) {
        this.group = group;
    }

    @Override
    public boolean act(float delta) {
        if (alfa > 0) {
            alfa -= 0.05f;

            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                    group.setColor(group.getColor().r, group.getColor().g, group.getColor().b, alfa);
                    ((ToastMessage) group).setAlfa(alfa);
                }
            });

            System.out.println("act false");
            return false;
        } else
        {
            System.out.println("act true");
            return true;
        }

    }
}
