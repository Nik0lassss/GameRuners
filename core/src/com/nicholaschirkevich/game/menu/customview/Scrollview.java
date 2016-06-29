package com.nicholaschirkevich.game.menu.customview;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Created by Nikolas on 25.06.2016.
 */
public class Scrollview extends ScrollPane {
    public Scrollview(Actor widget) {
        super(widget);
    }

    public Scrollview(Actor widget, Skin skin) {
        super(widget, skin);
    }

    public Scrollview(Actor widget, Skin skin, String styleName) {
        super(widget, skin, styleName);
    }

    public Scrollview(Actor widget, ScrollPaneStyle style) {
        super(widget, style);
    }
}
