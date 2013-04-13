package com.ttma.caocaorun.draw.screen;

import com.ttma.caocaorun.MainActivity;
import com.ttma.caocaorun.VisualFX.BubbleButton;
import com.ttma.caocaorun.utilities.BitmapCollection;
import com.ttma.caocaorun.utilities.BitmapSynchroniser;
import com.ttma.caocaorun.utilities.SoundFactory;

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
	
	private SurfaceView screen;

	public OptionScreen(SurfaceView screen, Resources resources) {
		
		this.screen=screen;
		
		backgroundColor.setARGB(50, 255, 255, 255);
		bitmapCollection = new BitmapCollection();

		optionBackground = bitmapCollection.getOptionBackground(resources);
		visualFxOn = bitmapCollection.getVisualFxOn(resources);
		visualFxOff = bitmapCollection.getVisualFxOff(resources);
		musicOn = bitmapCollection.getMusicOn(resources);
		musicOff = bitmapCollection.getMusicOff(resources);
		soundOn = bitmapCollection.getSoundOn(resources);
		soundOff = bitmapCollection.getSoundOff(resources);

		back = bitmapCollection.getBack(resources);

		getGameOption();
		visualFxButton = new BubbleButton("visualEffectButton", visualFxOn,
				0.66f, 0.65f, 0.2f);
		soundButton = new BubbleButton("soundButton", soundOn, 0.77f, 0.47f,
				0.2f);
		musicButton = new BubbleButton("musicButton", musicOn, 0.53f, 0.36f,
				0.2f);

		backButton = new BubbleButton("resumeButton", back, 0.67f, 0.906f, 0f);
		backButton.staytill();
	}

	protected void onDraw(Canvas canvas) {
		
		Rect[] backgroundFrame = BitmapSynchroniser
				.getBackGroundRects(optionBackground);
		
		canvas.drawRect(0, 0, screen.getWidth(),
				screen.getHeight(), backgroundColor);

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
//		boolean isPress = MainActivity.isPress();

		switchFX(touchX, touchY);
		switchSound(touchX, touchY);
		switchMusic(touchX, touchY);

		MainActivity.resetXY();
		changeGameOption();
	}

	private void switchMusic(int touchX, int touchY) {
		if (musicButton.onTouch(touchX, touchY)) {
			musicSwitch = !musicSwitch;
			if (musicSwitch) {
				musicButton.changeBimap(musicOff);
				SoundFactory.stopMusic();
			} else {
				musicButton.changeBimap(musicOn);
				SoundFactory.playMusic();
			}
		}
	}

	private void switchSound(int touchX, int touchY) {
		if (soundButton.onTouch(touchX, touchY)) {
			soundSwitch = !soundSwitch;
			if (soundSwitch) {
				soundButton.changeBimap(soundOff);
				SoundFactory.disableSoundFX();
			} else {
				soundButton.changeBimap(soundOn);
				SoundFactory.enableSoundFX();
			}
		}
	}

	private void switchFX(int touchX, int touchY) {
		if (visualFxButton.onTouch(touchX, touchY)) {
			visualFxSwitch = !visualFxSwitch;
			if (visualFxSwitch) {
				visualFxButton.changeBimap(visualFxOff);
				BubbleButton.disableFly();
			} else {
				visualFxButton.changeBimap(visualFxOn);
				BubbleButton.enableFly();
			}
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