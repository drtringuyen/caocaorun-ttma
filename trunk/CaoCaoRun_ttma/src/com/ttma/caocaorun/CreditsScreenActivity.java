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
import com.ttma.caocaorun.utilities.BitmapSynchroniser;
import com.ttma.caocaorun.utilities.BubleText;

public class CreditsScreenActivity extends Activity implements OnTouchListener {
	
	BubbleButton backButton;
	Bitmap back,fontSheet, creditsBackground, bubble;
	BubleText[] bubletext = new BubleText[5];
	
	private CreditsScreen creditsScreen;

	private Canvas canvas;// this object for drawings, passed in the buttons

	private int touchX, touchY;

	private int FPS = 24;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		creditsBackground = BitmapFactory.decodeResource(getResources(), R.drawable.background);
		fontSheet=BitmapFactory.decodeResource(getResources(), R.drawable.babycakefont);
		back = BitmapFactory.decodeResource(getResources(), R.drawable.back);
		
		creditsScreen = new CreditsScreen(this);
		creditsScreen.setOnTouchListener(this);
		setContentView(creditsScreen);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		creditsScreen.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		creditsScreen.onResume();
	}

	private class CreditsScreen extends SurfaceView implements Runnable {

		Thread control = null;
		SurfaceHolder holder;
		boolean isOk = false;
		boolean isLoadSP = false;

		long ticksPs = 1000 / FPS;
		long startTime;
		long sleepTime;

		public CreditsScreen(Context context) {
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
					BitmapSynchroniser.setInitialParameters(creditsScreen,
							creditsBackground);
					BubleText.setInitalParameter(fontSheet);
					
					bubletext[0]= new BubleText("CREDITS", 0.35f, 0.3f,24,0.2f);
					bubletext[1]= new BubleText("Nguyen Duc Tri", 0.25f, 0.4f,18,0.2f);
					bubletext[2]= new BubleText("Tran Huu Phuong Tai", 0.25f, 0.5f,18,0.2f);
					bubletext[3]= new BubleText("Tran Ngoc Khanh Minh", 0.25f, 0.6f,18,0.2f);
					bubletext[4]= new BubleText("Diep So Anh", 0.25f, 0.7f,18,0.2f);
					
					backButton=new BubbleButton("backButton", 
							back,0.83f, 0.11f, 0f);
					backButton.staytill();
					
					isLoadSP = true;
				}
				
				canvas = holder.lockCanvas();
				creditsScreen.onTouchButton();
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
					creditsBackground, creditsScreen.getWidth() / 2,
					creditsScreen.getHeight() / 2, true, false);
			canvas.drawBitmap(creditsBackground, backgroundFrame[0],
					backgroundFrame[1], null);
			
			bubletext[0].onDraw(canvas);
			bubletext[1].onDraw(canvas);
			bubletext[2].onDraw(canvas);
			bubletext[3].onDraw(canvas);
			bubletext[4].onDraw(canvas);
			
			backButton.updateAndDraw(canvas);
		}
		
		private void onTouchButton(){
			// go to other screen when touch button
			if (backButton.onTouch(touchX, touchY)) {
				finish(); // test for memory problem.
				}
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
