package com.ttma.caocaorun.draw.screen;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.ttma.caocaorun.ControlView;
import com.ttma.caocaorun.MainActivity;
import com.ttma.caocaorun.VisualFX.BubbleButton;
import com.ttma.caocaorun.utilities.BitmapCollection;
import com.ttma.caocaorun.utilities.BitmapSynchroniser;

public class HomeScreen {

	private BitmapCollection bitmapCollection;
	
	public BubbleButton playButton, optionsButton, highScoresButton,
			creditsButton, valve1, valve2, exitButton, helpButton,poopStaytill;
	
//	public Sprite poopAnimated;

	private boolean selected = false;

	private ControlView screen;

	public Bitmap background, play, options, highscores, credits, bubble,
			valve, exit, help, poop;

	public HomeScreen(ControlView control, Resources resources) {

		this.screen = control;

		bitmapCollection = new BitmapCollection();

		background = bitmapCollection.getHomeScreen(resources);
		play = bitmapCollection.getPlay(resources);
		options = bitmapCollection.getOptions(resources);
		highscores = bitmapCollection.getHighscores(resources);
		credits = bitmapCollection.getCredits(resources);
		bubble = bitmapCollection.getBubble(resources);
		valve = bitmapCollection.getValve(resources);
		exit = bitmapCollection.getExit(resources);
		help = bitmapCollection.getHelp(resources);
//		poop= bitmapCollection.getPoopAnimated(resources);
		poop=bitmapCollection.getPoopStaytill(resources);

		playButton = new BubbleButton("playButton", play, 0.692f, 0.633f, 0.1f);
		optionsButton = new BubbleButton("optionsButton", options, 0.303f,
				0.222f, 0.1f);
		highScoresButton = new BubbleButton("highscoresButton", highscores,
				0.457f, 0.42f, 0.1f);
		creditsButton = new BubbleButton("creditsButton", credits, 0.304f,
				0.732f, 0.1f);
		valve1 = new BubbleButton("valve1", valve, 0.747f, 0.096f, 0.1f);
		valve2 = new BubbleButton("valve2", valve, 0.163f, 0.513f, 0.1f);
		exitButton = new BubbleButton("exit", exit, 0.671f, 0.906f, 0.1f);
		helpButton =  new BubbleButton("help", help, 0.669f, 0.281f,
				0.1f);
//		poopAnimated=new Sprite("help", poop, 0.306f, 0.611f,
//				0.1f,15);
		poopStaytill=new BubbleButton("help", poop, 0.306f, 0.611f,
				0.1f);

		highScoresButton.staytill();
		valve1.staytill();
		valve2.staytill();
		creditsButton.staytill();
		optionsButton.staytill();
		exitButton.staytill();
		helpButton.staytill();
//		poopAnimated.staytill();
		poopStaytill.staytill();
	}

	public void onDraw(Canvas canvas) {
		Rect[] backgroundFrame = BitmapSynchroniser
				.getBackGroundRects(background);

		canvas.drawBitmap(background, backgroundFrame[0], backgroundFrame[1],
				null);

		playButton.updateAndDraw(canvas);
		optionsButton.updateAndDraw(canvas);
		highScoresButton.updateAndDraw(canvas);
		creditsButton.updateAndDraw(canvas);
		exitButton.updateAndDraw(canvas);
		helpButton.updateAndDraw(canvas);
		valve1.updateAndDraw(canvas);
		valve2.updateAndDraw(canvas);
//		poopAnimated.updateAndDraw(canvas);
		poopStaytill.updateAndDraw(canvas);
	}

	public void activateButton() {

		int touchX = MainActivity.getTouchX();
		int touchY = MainActivity.getTouchY();

		if (playButton.onTouch(touchX, touchY))
			screen.getModeScreen().selected();
		if (optionsButton.onTouch(touchX, touchY))
			screen.getOptionScreen().selected();
		if (creditsButton.onTouch(touchX, touchY))
			screen.getCreditScreen().selected();
		if (highScoresButton.onTouch(touchX, touchY))
			screen.getHighScoreScreen().selected();

		MainActivity.resetXY();
	}

	public void bringToTop(Canvas canvas) {
		selected();
		activateButton();
		onDraw(canvas);
	}

	public void selected() {
		this.selected = true;
	}

	public void deselected() {
		this.selected = false;
	}

	public boolean isSelected() {
		return this.selected;
	}

//	public void bringToBack(Canvas canvas) {
//		Rect[] backgroundFrame = BitmapSynchroniser
//				.getBackGroundRects(background);
//
//		canvas.drawRect(backgroundFrame[1], b);
//	}
}