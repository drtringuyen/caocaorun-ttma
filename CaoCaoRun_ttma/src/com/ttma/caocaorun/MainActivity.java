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

	private ControlView controlViewHandler;
	
	private static int touchX, touchY;
	private static boolean isPress;
//	private static MediaPlayer mplayer;
	
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		MediaPlayer music = MediaPlayer.create(this, R.raw.music);
		MediaPlayer poop = MediaPlayer.create(this, R.raw.poop);
		MediaPlayer bubble = MediaPlayer.create(this, R.raw.bubble);
//		mplayer.start();
		
		controlViewHandler = new ControlView(this);// set draw component here
					
		controlViewHandler.setOnTouchListener(this);// set touch component here
		setContentView(controlViewHandler);
		
		BitmapCollection bitmapCollection=new BitmapCollection();
		
		Bitmap standardBitmap=bitmapCollection.getBackground(getResources());
		
		Display display = getWindowManager().getDefaultDisplay(); 
		
		BitmapSynchroniser.setInitialParameters(display,
					standardBitmap);
		SoundFactory.setInititate(music, poop, bubble);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {

		switch (event.getAction()) {
		
		case MotionEvent.ACTION_DOWN:
			float tempX = event.getX();
			float tempY = event.getY();
			touchX = (int) tempX;
			touchY = (int) tempY;
			isPress=true;
			break;
		case MotionEvent.ACTION_UP:
			
			isPress=false;
			break;
		}
		return true;
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		controlViewHandler.onPause();
	}

	
	@Override
	protected void onStart() {
		SoundFactory.playMusic();
		super.onStart();
	}

	@Override
	protected void onResume() {
		super.onResume();
		controlViewHandler.onResume();
	}

	
	public static int getTouchX() {
		return touchX;
	}

	public static int getTouchY() {
		return touchY;
	}

	public static void resetXY(){
		touchX=0;
		touchY=0;
	}
	public static boolean isPress(){
		return isPress;
	}
	public void startIntent(Intent intent){
		startActivity(intent);
	}
}
