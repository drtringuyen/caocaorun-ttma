package com.ttma.caocaorun.draw.screen;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.ttma.caocaorun.ControlView;
import com.ttma.caocaorun.MainActivity;
import com.ttma.caocaorun.VisualFX.BubbleButton;
import com.ttma.caocaorun.utilities.Sprite;

public class HomeScreen extends StandardViewScreen {

	public BubbleButton playButton, optionsButton, highScoresButton,
			creditsButton, valve1, valve2, helpButton;

	public Sprite poopAnimated;

	public Bitmap play, options, highscores, credits, bubble, valve, help,
			poop;

	public HomeScreen(ControlView controler, Resources resources,
			boolean isBrowser) {

		super(controler, resources, isBrowser);

		background = bitmapColection.getHomeScreen(resources);
		play = bitmapColection.getPlay(resources);
		options = bitmapColection.getOptions(resources);
		highscores = bitmapColection.getHighscores(resources);
		credits = bitmapColection.getCredits(resources);
		bubble = bitmapColection.getBubble(resources);
		valve = bitmapColection.getValveAnimated(resources);
		resumeBitmap = bitmapColection.getExit(resources);
		help = bitmapColection.getHelp(resources);
		poop = bitmapColection.getPoopAnimated(resources);

		playButton = new BubbleButton("playButton", play, 0.692f, 0.633f, 0.1f);
		optionsButton = new BubbleButton("optionsButton", options, 0.303f,
				0.222f, 0.1f);
		highScoresButton = new BubbleButton("highscoresButton", highscores,
				0.457f, 0.42f, 0.1f);
		creditsButton = new BubbleButton("creditsButton", credits, 0.304f,
				0.742f, 0.1f);
		valve1 = new Sprite("valve1", valve, 0.747f, 0.096f, 0.1f, 6);
		valve2 = new Sprite("valve2", valve, 0.163f, 0.513f, 0.1f, 6);
		resumeButton = new BubbleButton("exit", resumeBitmap, 0.671f, 0.906f,
				0.1f);
		helpButton = new BubbleButton("help", help, 0.669f, 0.281f, 0.1f);
		poopAnimated = new Sprite("help", poop, 0.306f, 0.611f, 0.1f, 15);

		highScoresButton.staytill();
		valve1.staytill();
		valve2.staytill();
		creditsButton.staytill();
		optionsButton.staytill();
		resumeButton.staytill();
		helpButton.staytill();
		poopAnimated.staytill();
	}

	public void onDraw(Canvas canvas) {

		super.onDraw(canvas);

		optionsButton.updateAndDraw(canvas);
		highScoresButton.updateAndDraw(canvas);
		creditsButton.updateAndDraw(canvas);
		resumeButton.updateAndDraw(canvas);
		helpButton.updateAndDraw(canvas);
		valve1.updateAndDraw(canvas);
		valve2.updateAndDraw(canvas);
		poopAnimated.updateAndDraw(canvas);
		playButton.updateAndDraw(canvas);
		if (onResume())
			exitGame();
	}

	public void activateButton() {

		int touchX = MainActivity.getTouchX();
		int touchY = MainActivity.getTouchY();

		if (playButton.onTouch(touchX, touchY))
			controler.getModeScreen().selected();

		if (optionsButton.onTouch(touchX, touchY))
			controler.getOptionScreen().selected();

		if (creditsButton.onTouch(touchX, touchY))
			controler.getCreditScreen().selected();

		if (highScoresButton.onTouch(touchX, touchY))
			controler.getHighScoreScreen().selected();

		if (resumeButton.onTouch(touchX, touchY))
			controler.exitGame();

		if (helpButton.onTouch(touchX, touchY))
			controler.getHelpScreen().selected();

		MainActivity.resetXY();
	}

	@Override
	public void bringToBack() {
		// TODO Auto-generated method stub
		playButton.staytill();
		super.bringToBack();
	}

	@Override
	public void bringToTop(Canvas canvas) {
		// TODO Auto-generated method stub
		playButton.fly();
		super.bringToTop(canvas);
	}

	public void exitGame() {
		controler.exitGame();
	}

}