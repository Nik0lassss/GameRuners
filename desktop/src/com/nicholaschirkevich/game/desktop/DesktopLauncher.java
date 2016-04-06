package com.nicholaschirkevich.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.nicholaschirkevich.game.GameRuners;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = GameRuners.WIDTH;
		config.height = GameRuners.HEIGHT;
		config.title = GameRuners.TITLE;
		new LwjglApplication(new GameRuners(), config);
	}
}
