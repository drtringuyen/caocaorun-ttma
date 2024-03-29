package com.ttma.caocaorun;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;

import com.ttma.caocaorun.utilities.BitmapCollection;
import com.ttma.caocaorun.utilities.BitmapSynchroniser;
import com.ttma.caocaorun.utilities.SoundFactory;

public class MainActivity extends Activity implements OnTouchListener {

	private ControlView controlView;

	private static int touchX = 0, touchY = 0;
	
	private static boolean isPress;
	
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		loadSound();

		controlView = new ControlView(this);// set draw component here

		controlView.setOnTouchListener(this);// set touch component here

		setInitialValues();
		
		controlView.load();// this to load data required
	}

	private void setInitialValues() {
		// TODO Auto-generated method stub
		setContentView(controlView);

		BitmapCollection bitmapCollection = new BitmapCollection();

		Bitmap standardBitmap = bitmapCollection.getBackground(getResources());

		Display display = getWindowManager().getDefaultDisplay();

		BitmapSynchroniser.setInitialParameters(display, standardBitmap);
		
	}

	private void loadSound() {
		// TODO Auto-generated method stub
		MediaPlayer music, poop, bubble;
		music = MediaPlayer.create(this, R.raw.music);
		poop = MediaPlayer.create(this, R.raw.poop);
		bubble = MediaPlayer.create(this, R.raw.bubble);
		SoundFactory.setInititate(music, poop, bubble);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {

		switch (event.getAction()) {

		case MotionEvent.ACTION_DOWN:
			float tempX = event.getX();
			float tempY = event.getY();
			touchX = (int) tempX;
			touchY = (int) tempY;
			isPress = true;
			break;
		case MotionEvent.ACTION_UP:

			isPress = false;
			break;
		}
		return true;
	}

	@Override
	protected void onPause() {
		super.onPause();
		controlView.onPause();
	}

	@Override
	protected void onStart() {
		SoundFactory.playMusic();
		super.onStart();
	}

	@Override
	protected void onResume() {
		super.onResume();
		controlView.onResume();
	}

	public static int getTouchX() {
		return touchX;
	}

	public static int getTouchY() {
		return touchY;
	}

	public static void resetXY() {
		touchX = -100;
		touchY = -100;
	}

	public static boolean isPress() {
		return isPress;
	}

	public void startIntent(Intent intent) {
		startActivity(intent);
		// finish();
	}

	public void exitGame() {
		SoundFactory.stopMusic();
		finish();
	}
}
