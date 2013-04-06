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

public class HomeScreenActivity extends Activity implements OnTouchListener {

	HomeScreen homeScreen;
	CreditsScreenActivity creditsScreen;
	OptionScreenActivity optionScreen;
	ModeScreenActivity modeScreen;
	BitmapCollection bitmapCollection;
	
	private Bitmap background, play, options, highscores, credits, bubble;

	private Paint backgroundColor = new Paint();

	private Canvas canvas;// this object for drawings, passed in the buttons

	private int touchX, touchY;

	private int FPS = 30;
	
	boolean isOptionScreen=false;
	boolean isModeScreen=false;
	boolean isCreditsScreen=false;

	// these are buttons that used for homepage
	BubbleButton playButton, optionsButton, highScoresButton, creditsButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		bitmapCollection=new BitmapCollection();
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

	private class HomeScreen extends SurfaceView implements Runnable {

		Thread controlHomeScreen = null;
		SurfaceHolder holder;
		boolean isOk = false;
		boolean isLoadSP = false;
		boolean isLoadOptionView = false;
		boolean isLoadModeView = false;
		boolean isLoadCreditsView=false;
		long ticksPs = 1000 / FPS;
		long startTime;
		long sleepTime;

		public HomeScreen(Context context) {
			super(context);
			holder = getHolder();
		}

		@Override
		public void run(){
			canvas = null;
			// TODO Auto-generated method stub
			while (isOk == true) {
				startTime = System.currentTimeMillis();
				if (!holder.getSurface().isValid()) {
					continue;
				}
				if (!isLoadSP) {
					BitmapSynchroniser.setInitialParameters(homeScreen,
							background);

					// create buttons here.
					// to adjust the position, try 2 first digits
					// the last digit is for the boundary of the bubble
					playButton = new BubbleButton("playButton", play, 0.4f,
							0.6f, 0.1f);
					optionsButton = new BubbleButton("optionsButton", options,
							0.75f, 0.47f, 0.1f);
					highScoresButton = new BubbleButton("highscoresButton",
							highscores, 0.75f, 0.7f, 0.1f);
					creditsButton = new BubbleButton("creditsButton", credits,
							0.53f, 0.84f, 0.1f);

					backgroundColor.setARGB(255, 152, 162, 210);
					// backgroundColor.setColor(Color.BLACK);
					isLoadSP = true;
				}
				if(!isLoadOptionView){
					optionScreen=new OptionScreenActivity(homeScreen,getResources());
					isLoadOptionView=true;
				}
				if(!isLoadModeView){
					modeScreen= new ModeScreenActivity(homeScreen, getResources());
					isLoadModeView=true;
				}
				if(!isLoadCreditsView){
					creditsScreen= new CreditsScreenActivity(homeScreen, getResources());
					isLoadCreditsView=true;
				}
				
				canvas = holder.lockCanvas();
				if(isOptionScreen || isModeScreen || isCreditsScreen ){
					playButton.staytill();
					optionsButton.staytill();
					highScoresButton.staytill();
					creditsButton.staytill();
					onDrawHomeScreen(canvas);
					if(isOptionScreen){				
						onDrawOptionScreen(canvas);
						optionScreen.optionScreenControlButton(touchX, touchY);
						if(optionScreen.onResume(touchX, touchY)){
							isOptionScreen=false;
						}
					}
					if(isModeScreen){
						onDrawModeScreen(canvas);
						Intent modeIntent = modeScreen.modeScreenControlButton(touchX, touchY);
						if(modeIntent!=null){
							startActivity(modeIntent);
						}
						if(modeScreen.onResume(touchX, touchY)){
							isModeScreen=false;
						}
					}
					if(isCreditsScreen){
						onDrawCreditsScreen(canvas);
						if(creditsScreen.onResume(touchX, touchY)){
							isCreditsScreen=false;
						}
					}
					touchX=0;
					touchY=0;
				}
				else{
					playButton.fly();
					optionsButton.fly();
					highScoresButton.fly();
					creditsButton.fly();
					onDrawHomeScreen(canvas);
					homeScreen.homeScreenControlButton();
				}
				
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
		
		protected void onDrawHomeScreen(Canvas canvas){
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
		protected void onDrawModeScreen(Canvas canvas){
			modeScreen.onDraw(canvas);
		}
		protected void onDrawOptionScreen(Canvas canvas) {			
			optionScreen.onDraw(canvas);
		}
		protected void onDrawCreditsScreen(Canvas canvas) {			
			creditsScreen.onDraw(canvas);
		}
		//end onDraw
		public void homeScreenControlButton(){
			if(true){
			if(playButton.onTouch(touchX, touchY)){
				 isModeScreen=true;
			}
			if(optionsButton.onTouch(touchX, touchY)){
				 isOptionScreen=true;
			}
			if(highScoresButton.onTouch(touchX, touchY)){
				Intent highScoreIntent = new
				Intent("com.ttma.caocaorun.HIGHSCORESCREEN");
				startActivity(highScoreIntent);
			}
			 if(creditsButton.onTouch(touchX, touchY)){
				isCreditsScreen=true;
			}
			 touchX=0;
			 touchY=0;
			}
		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			float temp_x = event.getX();
			float temp_y = event.getY();
			this.touchX = (int) temp_x;
			this.touchY = (int) temp_y;
			break;
		case MotionEvent.ACTION_UP:
			this.touchX = 0;
			this.touchY = 0;
		}
		return true;
	}
}
