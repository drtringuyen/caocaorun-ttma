package com.ttma.caocaorun;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

import com.ttma.caocaorun.VisualFX.BubbleButton;
import com.ttma.caocaorun.utilities.BitmapCollection;
import com.ttma.caocaorun.utilities.BitmapSynchroniser;

public class OptionScreenActivity extends Activity implements OnTouchListener {
	
	Bitmap optionBackground;
	Bitmap visualFxOn,soundOn,musicOn,back;
	Bitmap visualFxOff,soundOff,musicOff;
	BitmapCollection bitmapCollection;
	
	BubbleButton visualFxButton,soundButton,musicButton,backButton;
	
	boolean visualFxSwitch,soundSwitch,musicSwitch;
	
	private OptionScreen optionScreen;

	private Canvas canvas;// this object for drawings, passed in the buttons

	private int touchX, touchY;

	private int FPS = 24;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		bitmapCollection = new BitmapCollection();
		
		optionBackground = BitmapFactory.decodeResource(getResources(), R.drawable.optionssreen);
		visualFxOn = BitmapFactory.decodeResource(getResources(), R.drawable.fxon);
		visualFxOff = BitmapFactory.decodeResource(getResources(), R.drawable.fxoff);
		musicOn = BitmapFactory.decodeResource(getResources(), R.drawable.musicon);
		musicOff = BitmapFactory.decodeResource(getResources(), R.drawable.musicoff);
		soundOn = BitmapFactory.decodeResource(getResources(), R.drawable.soundon);
		soundOff = BitmapFactory.decodeResource(getResources(), R.drawable.soundoff);		
		back = BitmapFactory.decodeResource(getResources(), R.drawable.back);
		
		optionScreen = new OptionScreen(this);
		optionScreen.setOnTouchListener(this);
		setContentView(optionScreen);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		optionScreen.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		optionScreen.onResume();
	}

	private class OptionScreen extends SurfaceView implements Runnable {

		Thread control = null;
		SurfaceHolder holder;
		boolean isOk = false;
		boolean isLoadSP = false;

		long ticksPs = 1000 / FPS;
		long startTime;
		long sleepTime;

		public OptionScreen(Context context) {
			super(context);
			holder = getHolder();
		}

		@Override
		public void run() {
			canvas = null;
			// TODO Auto-generated method stub
			while (isOk == true) {
				startTime = System.currentTimeMillis();
				if (!holder.getSurface().isValid()) {
					continue;
				}
				if (!isLoadSP) {
					BitmapSynchroniser.setInitialParameters(optionScreen,
							optionBackground);
					backButton = new BubbleButton("resumeButton", 
							back,0.83f, 0.11f, 0f);
					backButton.staytill();

					visualFxButton = new BubbleButton("visualEffectButton", 
							visualFxOn,0.66f, 0.7f, 0.2f);
					soundButton = new BubbleButton("soundButton",
							soundOn, 0.77f, 0.51f, 0.2f);
					musicButton = new BubbleButton("musicButton", 
							musicOn,0.53f, 0.4f, 0.2f);
					getGameOption();
					
					isLoadSP = true;
				}
				
				canvas = holder.lockCanvas();
				optionScreen.onTouchButton();
				onDraw(canvas);
				holder.unlockCanvasAndPost(canvas);
				
				
				sleepTime = ticksPs - (System.currentTimeMillis() - startTime);
				try {
					if (sleepTime > 0)
						Thread.sleep(sleepTime);
					else
						Thread.sleep(0);
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		}

		public void onPause() {
			isOk = false;
			while (true) {
				try {
					control.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			}
			control = null;
		}

		public void onResume() {
			isOk = true;
			control = new Thread(this);
			control.start();
		}

		protected void onDraw(Canvas canvas) {

			Rect[] backgroundFrame = BitmapSynchroniser.getSynchonisedRect(
					optionBackground, optionScreen.getWidth() / 2,
					optionScreen.getHeight() / 2, true, false);
			canvas.drawBitmap(optionBackground, backgroundFrame[0],
					backgroundFrame[1], null);
			
			visualFxButton.updateAndDraw(canvas);
			soundButton.updateAndDraw(canvas);
			musicButton.updateAndDraw(canvas);			
			backButton.updateAndDraw(canvas);
			
		}
		
		private void getGameOption(){
			//use this to get Game Option from database
			visualFxSwitch=true;
			soundSwitch=true;
			musicSwitch=true;
		}
		
		private void changeGameOption(){
			//use this to update GameOption
		}
		
		private void onTouchButton(){
			// go to other screen when touch button
			if (backButton.onTouch(touchX, touchY)) {
				finish(); // test for memory problem.
				}
			
			if(visualFxButton.onTouch(touchX, touchY)){
				if(visualFxSwitch){
					visualFxButton.changeBimap(visualFxOff);
					visualFxSwitch=false;
				}
				else{
					visualFxButton.changeBimap(visualFxOn);
					visualFxSwitch=true;
				}
			}
			
			if(soundButton.onTouch(touchX, touchY)){
				if(soundSwitch){
					soundButton.changeBimap(soundOff);
					soundSwitch=false;
				}
				else{
					soundButton.changeBimap(soundOn);
					soundSwitch=true;
				}
			}
			
			if(musicButton.onTouch(touchX, touchY)){
				if(musicSwitch){
					musicButton.changeBimap(musicOff);
					musicSwitch=false;
				}
				else{
					musicButton.changeBimap(musicOn);
					musicSwitch=true;
				}
			}
			changeGameOption();
			touchX=0;
			touchY=0;
		}
			
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			this.touchX = 0;
			this.touchY = 0;
			break;
		case MotionEvent.ACTION_UP:
			this.touchX = (int)event.getX();
			this.touchY = (int)event.getY();
			break;
		}
		return true;
	}
	
}
