package com.ttma.caocaorun;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;

import com.ttma.caocaorun.VisualFX.BubbleButton;
import com.ttma.caocaorun.utilities.BitmapCollection;
import com.ttma.caocaorun.utilities.BitmapSynchroniser;

public class MainActivity extends Activity implements OnTouchListener {

	HomeScreen homeScreen;
	CreditScreen creditsScreen;
	OptionScreen optionScreen;
	ModeScreen modeScreen;
	HighScoreScreen highScoreScreen;
	
	BitmapCollection bitmapCollection;

	public Bitmap background, play, options, highscores, credits, bubble;

	public Paint backgroundColor = new Paint();

	public Canvas canvas;// this object for drawings, passed in the buttons

	public int touchX, touchY;

	public int FPS = 30;

	public boolean isOptionScreen = false;
	public boolean isModeScreen = false;
	public boolean isCreditScreen = false;
	public boolean isHighScoreScreen=false;

	// these are buttons that used for homepage
	public BubbleButton playButton, optionsButton, highScoresButton, creditsButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		bitmapCollection = new BitmapCollection();

		background = bitmapCollection.getBackground(getResources());

		play = bitmapCollection.getPlay(getResources());

		options = bitmapCollection.getOptions(getResources());

		highscores = bitmapCollection.getHighscores(getResources());

		credits = bitmapCollection.getCredits(getResources());

		bubble = bitmapCollection.getBubble(getResources());

		homeScreen = new HomeScreen(this);// set draw component here
		homeScreen.setOnTouchListener(this);// set touch component here
		setContentView(homeScreen);
	}



	private class HomeScreen extends SurfaceView implements Runnable {

		Thread controlHomeScreen = null;
		SurfaceHolder holder;
		boolean isOk = false;
		
		boolean isLoadHomeScreen = false;
		boolean isLoadOptionView = false;
		boolean isLoadModeView = false;
		boolean isLoadCreditsView = false;
		boolean isLoadHighScoreView=false;
		
		long ticksPs = 1000 / FPS;
		long startTime;
		long sleepTime;

		public HomeScreen(Context context) {
			super(context);
			holder = getHolder();
		}

		@Override
		public void run() {
			canvas = null;
			// TODO Auto-generated method stub
			while (isOk == true) {
				startTime = System.currentTimeMillis();
				
				if (!holder.getSurface().isValid()) continue;
				
				loadAllScreens();
				canvas = holder.lockCanvas();
				
				seleteScreenToView();
				
				holder.unlockCanvasAndPost(canvas);
				updateTime();
			}
		}

		public void onPause() {
			isOk = false;
			while (true) {
				try {
					controlHomeScreen.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			}
			controlHomeScreen = null;
		}
		public void onResume() {
			isOk = true;
			controlHomeScreen = new Thread(this);
			controlHomeScreen.start();
		}
		
		public void loadAllScreens(){
			if (!isLoadHomeScreen) loadHomeScreen();
			if (!isLoadOptionView) loadOptionScreen();
			if (!isLoadModeView) loadModeScreen();
			if (!isLoadCreditsView) loadCreditsScreen();
			if (!isLoadHighScoreView) loadHighScoreScreen();
		}
		
		private void onDrawHomeScreen(Canvas canvas) {
			canvas.drawRect(0, 0, homeScreen.getWidth(),
					homeScreen.getHeight(), backgroundColor);

			Rect[] backgroundFrame = BitmapSynchroniser.getSynchonisedRect(
					background, homeScreen.getWidth() / 2,
					homeScreen.getHeight() / 2, true, false);

			canvas.drawBitmap(background, backgroundFrame[0],
					backgroundFrame[1], null);
			// method to view and update the buttons
			// can use button.onTouch(touchX,touchY) to check events
			playButton.updateAndDraw(canvas, touchX, touchY);
			optionsButton.updateAndDraw(canvas, touchX, touchY);
			highScoresButton.updateAndDraw(canvas, touchX, touchY);
			creditsButton.updateAndDraw(canvas, touchX, touchY);
		}
		private void onDrawModeScreen(Canvas canvas) {
			modeScreen.onDraw(canvas);
		}
		private void onDrawOptionScreen(Canvas canvas) {
			optionScreen.onDraw(canvas);
		}
		private void onDrawCreditsScreen(Canvas canvas) {
			creditsScreen.onDraw(canvas);
		}
		private void onDrawHighScoreScreen(Canvas canvas){
			highScoreScreen.onDraw(canvas);
		}

		private void homeScreenControlButton() {
				if (playButton.onTouch(touchX, touchY)) isModeScreen = true;
				if (optionsButton.onTouch(touchX, touchY)) isOptionScreen = true;
				if (highScoresButton.onTouch(touchX, touchY)) isHighScoreScreen=true;
				if (creditsButton.onTouch(touchX, touchY)) isCreditScreen = true;
				
				touchX = 0;
				touchY = 0;
		}

		// separate
		public void drawPauseHomeScreen(Canvas canvas) {
			playButton.staytill();
			optionsButton.staytill();
			highScoresButton.staytill();
			creditsButton.staytill();
			onDrawHomeScreen(canvas);
		}

		private void seleteScreenToView(){
			if (isOptionScreen || isModeScreen || isCreditScreen||isHighScoreScreen) {
				
//				drawPauseHomeScreen(canvas);
				
				if (isOptionScreen) bringOptionToTop();
				if (isModeScreen) bringModeSreenToTop();
				if (isCreditScreen) bringCreditScreenToTop();
				if (isHighScoreScreen) bringHighScoreScreenToTop();
				
			} else bringHomeScreenToTop();
		}
		private void bringHomeScreenToTop(){
			playButton.fly();
			optionsButton.fly();
			highScoresButton.fly();
			creditsButton.fly();
			onDrawHomeScreen(canvas);
			homeScreen.homeScreenControlButton();
		}
		private void bringOptionToTop(){
			onDrawOptionScreen(canvas);
			optionScreen.optionScreenSetControlButton(touchX, touchY);
			if (optionScreen.onResume(touchX, touchY)) {
				isOptionScreen = false;
			}
			touchX=0;
			touchY=0;
		}
		private void bringModeSreenToTop(){
			onDrawModeScreen(canvas);
			Intent modeIntent = modeScreen.modeScreenControlButton(
					touchX, touchY);
			if (modeIntent != null) {
				startActivity(modeIntent);
			}
			if (modeScreen.onResume(touchX, touchY)) {
				isModeScreen = false;
			}
		}
		private void bringCreditScreenToTop(){
			onDrawCreditsScreen(canvas);
			if (creditsScreen.onResume(touchX, touchY)) {
				isCreditScreen = false;
			}
		}
		private void bringHighScoreScreenToTop(){
			onDrawHighScoreScreen(canvas);
			if (highScoreScreen.onResume(touchX, touchY)) {
				isHighScoreScreen = false;
			}
		}
		
		public void loadHomeScreen() {
			// create buttons here.
			// to adjust the position, try 2 first digits
			// the last digit is for the boundary of the bubble
			BitmapSynchroniser.setInitialParameters(homeScreen,
					background);
			
			isLoadHomeScreen = true;
			playButton = new BubbleButton("playButton", play, 0.4f, 0.6f, 0.1f);
			optionsButton = new BubbleButton("optionsButton", options, 0.75f,
					0.47f, 0.1f);
			highScoresButton = new BubbleButton("highscoresButton", highscores,
					0.75f, 0.7f, 0.1f);
			creditsButton = new BubbleButton("creditsButton", credits, 0.53f,
					0.84f, 0.1f);

			backgroundColor.setARGB(255, 152, 162, 210);
		}
		public void loadOptionScreen(){
			optionScreen = new OptionScreen(homeScreen,
					getResources());
			isLoadOptionView = true;
		}
		public void loadModeScreen(){
			modeScreen = new ModeScreen(homeScreen,
					getResources());
			isLoadModeView = true;
		}
		public void loadCreditsScreen(){
			creditsScreen = new CreditScreen(homeScreen,
					getResources());
			isLoadCreditsView = true;
		}
		public void loadHighScoreScreen(){
			highScoreScreen = new HighScoreScreen(homeScreen,
					getResources());
			isLoadHighScoreView = true;
		}
		
		public void updateTime(){
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

	@Override
	public boolean onTouch(View v, MotionEvent event) {

		switch (event.getAction()) {
		
		case MotionEvent.ACTION_DOWN:
			break;
		case MotionEvent.ACTION_UP:
			float temp_x = event.getX();
			float temp_y = event.getY();
			this.touchX = (int) temp_x;
			this.touchY = (int) temp_y;
			break;
		}
		return true;
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		homeScreen.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		homeScreen.onResume();
	}
}
