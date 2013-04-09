package com.ttma.caocaorun;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.ttma.caocaorun.draw.screen.CreditScreen;
import com.ttma.caocaorun.draw.screen.HighScoreScreen;
import com.ttma.caocaorun.draw.screen.HomeScreen;
import com.ttma.caocaorun.draw.screen.ModeScreen;
import com.ttma.caocaorun.draw.screen.OptionScreen;

public class ControlView extends SurfaceView implements Runnable {

	private Thread controlViewScreen = null;
	private SurfaceHolder holder;
	
	Activity mainActivity;

	private boolean isOk = false;

	private HomeScreen homeScreen = null;
	private CreditScreen creditScreen = null;
	private OptionScreen optionScreen = null;
	private ModeScreen modeScreen = null;
	private HighScoreScreen highScoreScreen = null;

	public int FPS = 30;
	private long ticksPs = 1000 / FPS;
	private long startTime;
	private long sleepTime;

	public Canvas canvas;

	public ControlView(Context context) {
		super(context);
		this.mainActivity=(MainActivity)context;
		holder = getHolder();
	}

	@Override
	public void run() {
		canvas = null;

		while (isOk == true) {
			startTime = System.currentTimeMillis();

			if (!holder.getSurface().isValid())
				continue;

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
				controlViewScreen.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			break;
		}
		controlViewScreen = null;
	}

	public void onResume() {
		isOk = true;
		controlViewScreen = new Thread(this);
		controlViewScreen.start();
	}

	private void seleteScreenToView() {

		boolean isViewOptionScreen = optionScreen.isSelected();
		boolean isViewModeScreen = modeScreen.isSelected();
		boolean isViewCreditScreen = creditScreen.isSelected();
		boolean isViewHighScoreScreen = highScoreScreen.isSelected();

		if (isViewOptionScreen || isViewModeScreen || isViewCreditScreen
				|| isViewHighScoreScreen) {
			if (isViewOptionScreen)
				optionScreen.bringToTop(canvas);
			if (isViewModeScreen)
				modeScreen.bringToTop(canvas);
			if (isViewCreditScreen)
				creditScreen.bringToTop(canvas);
			if (isViewHighScoreScreen)
				highScoreScreen.bringToTop(canvas);
		} else
			homeScreen.bringToTop(canvas);
	}


	private void loadAllScreens() {
		if (homeScreen==null)
			homeScreen = new HomeScreen(this, getResources());
		if (optionScreen==null)
			optionScreen = new OptionScreen(this, getResources());
		if (modeScreen==null)
			modeScreen = new ModeScreen(this, getResources());
		if (creditScreen==null)
			creditScreen = new CreditScreen(this, getResources());
		if (highScoreScreen==null)
			highScoreScreen = new HighScoreScreen(this, getResources());
	}

	public void startIntent(Intent intent){
		mainActivity.startActivity(intent);
	}
	private void updateTime() {
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

	public HomeScreen getHomeScreen() {
		return homeScreen;
	}

	public CreditScreen getCreditScreen() {
		return creditScreen;
	}

	public OptionScreen getOptionScreen() {
		return optionScreen;
	}

	public ModeScreen getModeScreen() {
		return modeScreen;
	}

	public HighScoreScreen getHighScoreScreen() {
		return highScoreScreen;
	}
}
