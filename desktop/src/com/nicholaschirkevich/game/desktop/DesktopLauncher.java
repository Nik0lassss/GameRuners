package com.nicholaschirkevich.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.nicholaschirkevich.game.GameRuners;
import com.nicholaschirkevich.game.admob.ActionResolver;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = GameRuners.WIDTH;
		config.height = GameRuners.HEIGHT;
		config.title = GameRuners.TITLE;
		new LwjglApplication(new GameRuners(new ActionResolver() {


			@Override
			public void showOrLoadInterstital(boolean isAfterGetBonus) {

			}

			@Override
			public boolean isAvailibleInternet() {
				return false;
			}

			@Override
			public boolean isIntertitalLoad() {
				return false;
			}

			@Override
			public boolean isIntertatlLoaded() {
				return false;
			}

			@Override
			public void showVkLoginActivity() {

			}

			@Override
			public void getVkStatusLogin() {

			}

			@Override
			public void vkLogout() {

			}

			@Override
			public void sendPostOnVk() {

			}

			@Override
			public boolean isVkLogin() {
				return false;
			}

			@Override
			public void showInviteBox() {

			}

			@Override
			public void buyProduct(String id) {

			}

			@Override
			public void goneDefaultImage() {

			}
		}), config);
	}
}
