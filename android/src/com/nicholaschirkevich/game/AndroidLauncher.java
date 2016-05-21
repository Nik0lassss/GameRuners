package com.nicholaschirkevich.game;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.badlogic.gdx.backends.android.AndroidFragmentApplication;
import com.nicholaschirkevich.game.fragment.FragmentAdmob;


public class AndroidLauncher extends FragmentActivity implements AndroidFragmentApplication.Callbacks  {

	FragmentAdmob gameFragment;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);
		gameFragment= new FragmentAdmob();
		FragmentTransaction tr = getSupportFragmentManager().beginTransaction();
		tr.replace(R.id.GameView,gameFragment);
		tr.commit();


	}

	@Override
	public void exit() {

	}
}
