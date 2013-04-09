package com.ttma.caocaorun.draw.screen;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceView;

import com.ttma.caocaorun.ControlView;
import com.ttma.caocaorun.MainActivity;
import com.ttma.caocaorun.VisualFX.BubbleButton;
import com.ttma.caocaorun.utilities.BitmapCollection;
import com.ttma.caocaorun.utilities.BitmapSynchroniser;

public class ModeScreen {

	private Bitmap back, quiz, horror, endless, custom;
	private Bitmap background;
	private BitmapCollection bitmapCollection;
	private BubbleButton endlessButton, quizButton, horrorButton, customButton,
			backButton;

	private boolean selected = false;
	
	ControlView screen;

	public ModeScreen(SurfaceView screen, Resources resources) {

		// create Images
		bitmapCollection = new BitmapCollection();
		back = bitmapCollection.getBack(resources);
		quiz = bitmapCollection.getQuiz(resources);
		horror = bitmapCollection.getHorror(resources);
		endless = bitmapCollection.getEndless(resources);
		custom = bitmapCollection.getCustom(resources);
		background = bitmapCollection.getModeScreen(resources);

		this.screen=(ControlView)screen;
		// create Buttons
		endlessButton = new BubbleButton("endlessButton", endless, 0.61f,
				0.28f, 0.1f);
		quizButton = new BubbleButton("quizButton", quiz, 0.63f, 0.42f, 0.1f);
		horrorButton = new BubbleButton("horrorButton", horror, 0.63f, 0.6f,
				0.1f);
		customButton = new BubbleButton("customButton", custom, 0.63f, 0.74f,
				0.1f);
		backButton = new BubbleButton("backButton", back, 0.83f, 0.11f, 0.1f);
		backButton.staytill();
	}

	protected void onDraw(Canvas canvas) {

		Rect[] backgroundFrame = BitmapSynchroniser
				.getBackGroundRects(background);

		canvas.drawBitmap(background, backgroundFrame[0], backgroundFrame[1],
				null);

		endlessButton.updateAndDraw(canvas);
		quizButton.updateAndDraw(canvas);
		horrorButton.updateAndDraw(canvas);
		customButton.updateAndDraw(canvas);
		backButton.updateAndDraw(canvas);
	}

	public boolean onResume() {
		int touchX = MainActivity.getTouchX();
		int touchY = MainActivity.getTouchY();
		if (backButton.onTouch(touchX, touchY)) {
			deselected();
			return true;
		} else {
			return false;
		}
	}

	public void activateButton() {
		int touchX = MainActivity.getTouchX();
		int touchY = MainActivity.getTouchY();
		
		if (endlessButton.onTouch(touchX, touchY)) {
			Intent endlessIntent = new Intent("com.ttma.caocaorun.ENDLESSMODE");
			screen.startIntent(endlessIntent);
		}
	}

	public void bringToTop(Canvas canvas) {
		selected();
		onDraw(canvas);
		activateButton();
		onResume();
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
