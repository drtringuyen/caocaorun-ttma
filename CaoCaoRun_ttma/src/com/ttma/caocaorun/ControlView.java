package com.ttma.caocaorun;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.ttma.caocaorun.draw.screen.CreditScreen;
import com.ttma.caocaorun.draw.screen.HelpScreen;
import com.ttma.caocaorun.draw.screen.HighScoreScreen;
import com.ttma.caocaorun.draw.screen.HomeScreen;
import com.ttma.caocaorun.draw.screen.ModeScreen;
import com.ttma.caocaorun.draw.screen.OptionScreen;
import com.ttma.caocaorun.draw.standardclass.StandardGameView;
import com.ttma.caocaorun.draw.standardclass.StandardViewScreen;

public class ControlView extends SurfaceView implements Runnable {

	private Thread controlViewScreen = null;
	private SurfaceHolder holder;

	private MainActivity mainActivity;

	private boolean isOk = false;

	private HomeScreen homeScreen = null;
	private CreditScreen creditScreen = null;
	private OptionScreen optionScreen = null;
	private ModeScreen modeScreen = null;
	private StandardViewScreen highScoreScreen = null;
	private HelpScreen helpScreen = null;
	private StandardGameView gamePlayScreen=null;

	public int FPS = 30;
	private long ticksPs = 1000 / FPS;
	private long startTime;
	private long sleepTime;

	public Canvas canvas;

	public ControlView(Context context) {
		super(context);
		this.mainActivity = (MainActivity) context;
		holder = getHolder();
	}

	@Override
	public void run() {
		canvas = null;

		while (isOk == true) {
			//start the time
			startTime = System.currentTimeMillis();

			//check if holder is ok to take action
			if (!holder.getSurface().isValid())
				continue;
			
			//lock canvas to start paint
			canvas = holder.lockCanvas();
			
			//select approriate screen to view
			seleteScreenToView();
			
			//unlock canvas and show to the world
			holder.unlockCanvasAndPost(canvas);
			
			//update the time
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
		boolean isViewHelpScreen = helpScreen.isSelected();
		boolean isViewGamePlayScreen = gamePlayScreen.isSelected();

		if (isViewOptionScreen || isViewModeScreen || isViewCreditScreen
				|| isViewHighScoreScreen || isViewHelpScreen||isViewGamePlayScreen) {

			if (isViewOptionScreen)
				optionScreen.bringToTop(canvas);
			if (isViewModeScreen)
				modeScreen.bringToTop(canvas);
			if (isViewCreditScreen)
				creditScreen.bringToTop(canvas);
			if (isViewHighScoreScreen)
				highScoreScreen.bringToTop(canvas);
			if (isViewHelpScreen)
				helpScreen.bringToTop(canvas);
			if (isViewGamePlayScreen)
				gamePlayScreen.bringToTop(canvas);
			
		} else {
			homeScreen.bringToTop(canvas);
		}
	}


	private void loadAllScreens() {
		if (homeScreen == null)
			homeScreen = new HomeScreen(this, getResources(),true);
		if (optionScreen == null)
			optionScreen = new OptionScreen(this, getResources(),false);
		if (modeScreen == null)
			modeScreen = new ModeScreen(this, getResources(),true);
		if (creditScreen == null)
			creditScreen = new CreditScreen(this, getResources(),false);
		if (highScoreScreen == null)
			highScoreScreen = new HighScoreScreen(this, getResources(),false);
		if (helpScreen == null)
			helpScreen = new HelpScreen(this, getResources(),false);
		if (gamePlayScreen == null)
			gamePlayScreen = new StandardGameView(this, getResources(),false);
	}

	public void startIntent(Intent intent) {
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
	public StandardViewScreen getHighScoreScreen() {
		return highScoreScreen;
	}
	public HelpScreen getHelpScreen() {
		return helpScreen;
	}
	public StandardGameView getGamePlayScreen() {
		return gamePlayScreen;
	}
	public void exitGame() {
		mainActivity.exitGame();
	}
	
	public void load() {
		// TODO Auto-generated method stub
		loadAllScreens();
	}
}
