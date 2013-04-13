package com.ttma.caocaorun.draw.screen;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.ttma.caocaorun.ControlView;
import com.ttma.caocaorun.MainActivity;
import com.ttma.caocaorun.VisualFX.BubbleButton;
import com.ttma.caocaorun.utilities.BitmapCollection;

public class ModeScreen extends StandardViewScreen{

	private Bitmap back, quiz, horror, endless, custom;
	private BubbleButton endlessButton, quizButton, horrorButton, customButton;

	public ModeScreen(ControlView screen, Resources resources) {

		// create Images
		super(screen, resources);
		bitmapColection = new BitmapCollection();
		back = bitmapColection.getBack(resources);
		quiz = bitmapColection.getQuiz(resources);
		horror = bitmapColection.getHorror(resources);
		endless = bitmapColection.getEndless(resources);
		custom = bitmapColection.getCustom(resources);
		background = bitmapColection.getModeScreen(resources);

		this.screen=(ControlView)screen;
		// create Buttons
		endlessButton = new BubbleButton("endlessButton", endless, 0.61f,
				0.28f, 0.1f);
		quizButton = new BubbleButton("quizButton", quiz, 0.63f, 0.42f, 0.1f);
		horrorButton = new BubbleButton("horrorButton", horror, 0.63f, 0.6f,
				0.1f);
		customButton = new BubbleButton("customButton", custom, 0.63f, 0.74f,
				0.1f);
		resumeButton = new BubbleButton("backButton", back,  0.671f, 0.906f, 0.1f);
		resumeButton.staytill();
	}

	public void onDraw(Canvas canvas) {

		super.onDraw(canvas);
		resumeButton.updateAndDraw(canvas);
		endlessButton.updateAndDraw(canvas);
		quizButton.updateAndDraw(canvas);
		horrorButton.updateAndDraw(canvas);
		customButton.updateAndDraw(canvas);
		
	}

	public void activateButton() {
		int touchX = MainActivity.getTouchX();
		int touchY = MainActivity.getTouchY();
		
		if (endlessButton.onTouch(touchX, touchY)) {
			
			Intent endlessIntent = new Intent("com.ttma.caocaorun.ENDLESSMODE");
			endlessIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			screen.startIntent(endlessIntent);
			MainActivity.resetXY();
		}
	}

}
