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

	BitmapCollection bitmapCollection;
	public BubbleButton playButton, optionsButton, highScoresButton,
			creditsButton;

	private boolean selected = false;

	private ControlView screen;

	public Bitmap background, play, options, highscores, credits, bubble;

	public HomeScreen(ControlView control, Resources resources) {

		this.screen = control;

		bitmapCollection = new BitmapCollection();

		background = bitmapCollection.getBackground(resources);
		play = bitmapCollection.getPlay(resources);
		options = bitmapCollection.getOptions(resources);
		highscores = bitmapCollection.getHighscores(resources);
		credits = bitmapCollection.getCredits(resources);
		bubble = bitmapCollection.getBubble(resources);

		playButton = new BubbleButton("playButton", play, 0.4f, 0.6f, 0.1f);
		optionsButton = new BubbleButton("optionsButton", options, 0.75f,
				0.47f, 0.1f);
		highScoresButton = new BubbleButton("highscoresButton", highscores,
				0.75f, 0.7f, 0.1f);
		creditsButton = new BubbleButton("creditsButton", credits, 0.53f,
				0.84f, 0.1f);
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
	
	public void bringToTop(Canvas canvas){
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
}