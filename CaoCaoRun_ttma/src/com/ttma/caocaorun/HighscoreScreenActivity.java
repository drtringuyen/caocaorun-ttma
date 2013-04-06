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
import com.ttma.caocaorun.utilities.BubleText;

public class HighscoreScreenActivity extends Activity implements OnTouchListener {
	
	Bitmap back;
	BitmapCollection bitmapCollection;
	BubbleButton backButton;
	
	private HighscoreScreen highscoreScreen;

	private Bitmap fontSheet, background;

	private Canvas canvas;// this object for drawings, passed in the buttons

	private int touchX, touchY;

	private int FPS = 24;
	
	private BubleText[] bubletext = new BubleText[22];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		
		back = BitmapFactory.decodeResource(getResources(),
				R.drawable.back);
		
		background = BitmapFactory.decodeResource(getResources(),
				R.drawable.background);

		fontSheet=BitmapFactory.decodeResource(getResources(),
				R.drawable.babycakefont);

		highscoreScreen = new HighscoreScreen(this);// set draw component here
		highscoreScreen.setOnTouchListener(this);// set touch component here
		setContentView(highscoreScreen);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		highscoreScreen.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		highscoreScreen.onResume();
	}

	private class HighscoreScreen extends SurfaceView implements Runnable {

		Thread control = null;
		SurfaceHolder holder;
		boolean isOk = false;
		boolean isLoadSP = false;

		long ticksPs = 1000 / FPS;
		long startTime;
		long sleepTime;

		public HighscoreScreen(Context context) {
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
					BitmapSynchroniser.setInitialParameters(highscoreScreen,
							background);
					backButton = new BubbleButton("resumeButton", 
							back,0.83f, 0.11f, 0f);
					backButton.staytill();
					BubleText.setInitalParameter(fontSheet);
					
					bubletext[0]= new BubleText("NAME", 0.2f, 0.3f,24,0.2f);
					bubletext[1]= new BubleText("SCORE", 0.6f, 0.3f,24,0.2f);
					
					isLoadSP = true;
				}
				canvas = holder.lockCanvas();
				onDraw(canvas);
				holder.unlockCanvasAndPost(canvas);
				highscoreScreen.onTouchButton();
				
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
					background, highscoreScreen.getWidth() / 2,
					highscoreScreen.getHeight() / 2, true, false);
			canvas.drawBitmap(background, backgroundFrame[0],
					backgroundFrame[1], null);
			
			bubletext[0].onDraw(canvas);
			bubletext[1].onDraw(canvas);
			// BubleText.drawToString(30,30,canvas);
			backButton.updateAndDraw(canvas, touchX, touchY);
		}
		
		public void onTouchButton(){
			// go to other screen when touch button
			if (backButton.onTouch(touchX, touchY)) {
				finish(); // test for memory problem.
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
