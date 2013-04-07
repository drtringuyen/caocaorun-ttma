package com.ttma.caocaorun.draw.screen;

import com.ttma.caocaorun.MainActivity;
import com.ttma.caocaorun.VisualFX.BubbleButton;
import com.ttma.caocaorun.utilities.BitmapCollection;
import com.ttma.caocaorun.utilities.BitmapSynchroniser;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceView;

public class OptionScreen {
	private Bitmap optionBackground;
	private Bitmap visualFxOn, soundOn, musicOn, back;
	private Bitmap visualFxOff, soundOff, musicOff;
	private BitmapCollection bitmapCollection;

	private boolean selected = false;

	private BubbleButton visualFxButton, soundButton, musicButton, backButton;

	private boolean visualFxSwitch, soundSwitch, musicSwitch;

	private Paint backgroundColor = new Paint();

	public OptionScreen(SurfaceView screen, Resources _resources) {
		backgroundColor.setARGB(150, 174, 207, 233);
		bitmapCollection = new BitmapCollection();

		optionBackground = bitmapCollection.getOptionBackground(_resources);
		visualFxOn = bitmapCollection.getVisualFxOn(_resources);
		visualFxOff = bitmapCollection.getVisualFxOff(_resources);
		musicOn = bitmapCollection.getMusicOn(_resources);
		musicOff = bitmapCollection.getMusicOff(_resources);
		soundOn = bitmapCollection.getSoundOn(_resources);
		soundOff = bitmapCollection.getSoundOff(_resources);

		back = bitmapCollection.getBack(_resources);

		getGameOption();
		visualFxButton = new BubbleButton("visualEffectButton", visualFxOn,
				0.66f, 0.7f, 0.2f);
		soundButton = new BubbleButton("soundButton", soundOn, 0.77f, 0.51f,
				0.2f);
		musicButton = new BubbleButton("musicButton", musicOn, 0.53f, 0.4f,
				0.2f);

		backButton = new BubbleButton("resumeButton", back, 0.83f, 0.11f, 0f);
		backButton.staytill();
	}

	protected void onDraw(Canvas canvas) {
		Rect[] backgroundFrame = BitmapSynchroniser
				.getBackGroundRects(optionBackground);

		canvas.drawBitmap(optionBackground, backgroundFrame[0],
				backgroundFrame[1], null);

		visualFxButton.updateAndDraw(canvas);
		soundButton.updateAndDraw(canvas);
		musicButton.updateAndDraw(canvas);

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

	public void getGameOption() {
		visualFxSwitch = true;
		soundSwitch = true;
		musicSwitch = true;
	}

	public void changeGameOption() {

	}

	public void activateButton() {
		int touchX = MainActivity.getTouchX();
		int touchY = MainActivity.getTouchY();
		boolean isPress=MainActivity.isPress();

		switchFX(touchX, touchY);
		switchSound(touchX, touchY);
		switchMusic(touchX, touchY);
		
		MainActivity.resetXY();
		changeGameOption();
	}

	private void switchMusic(int touchX, int touchY) {
		if (musicButton.onTouch(touchX, touchY)) {
			musicSwitch = !musicSwitch;
			if (musicSwitch)
				musicButton.changeBimap(musicOff);
			else
				musicButton.changeBimap(musicOn);
		}
	}

	private void switchSound(int touchX, int touchY) {
		if (soundButton.onTouch(touchX, touchY)) {
			soundSwitch = !soundSwitch;
			if (soundSwitch){
				soundButton.changeBimap(soundOff);
//				soundButton.staytill();
			}else{
				soundButton.changeBimap(soundOn);
//				soundButton.fly();
			}
		}
	}

	private void switchFX(int touchX, int touchY) {
		if (visualFxButton.onTouch(touchX, touchY)) {
			visualFxSwitch = !visualFxSwitch;
			if (visualFxSwitch)
				visualFxButton.changeBimap(visualFxOff);
			else
				visualFxButton.changeBimap(visualFxOn);
		}
	}

	public void bringToTop(Canvas canvas) {
		selected();
		onDraw(canvas);
		onResume();
		activateButton();
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