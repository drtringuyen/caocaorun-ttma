package com.ttma.caocaorun.draw.screen;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.ttma.caocaorun.ControlView;
import com.ttma.caocaorun.MainActivity;
import com.ttma.caocaorun.OptionSettings;
import com.ttma.caocaorun.VisualFX.BubbleButton;
import com.ttma.caocaorun.draw.standardclass.StandardViewScreen;
import com.ttma.caocaorun.utilities.SoundFactory;

public class OptionScreen extends StandardViewScreen {

	private Bitmap visualFxOn, soundOn, musicOn;
	private Bitmap visualFxOff, soundOff, musicOff;

	private BubbleButton visualFxButton, soundButton, musicButton;

	private Paint backgroundColor = new Paint();

	public OptionScreen(ControlView controler, Resources resources,boolean isBrowser) {

		super(controler, resources,isBrowser);
		backgroundColor.setARGB(50, 255, 255, 255);
	}

	private void createSoundButton() {
		if (OptionSettings.isSoundOn)
			musicButton = new BubbleButton("musicButton", musicOn, 0.53f,
					0.36f, 0.2f);
		else
			musicButton = new BubbleButton("musicButton", musicOff, 0.53f,
					0.36f, 0.2f);
		
	}

	private void createFxButton() {
		if (OptionSettings.isSoundOn)
			soundButton = new BubbleButton("soundButton", soundOn, 0.77f,
					0.47f, 0.2f);
		else
			soundButton = new BubbleButton("soundButton", soundOff, 0.77f,
					0.47f, 0.2f);
		
	}

	private void createMusicButton() {
		if (OptionSettings.isFXOn)
			visualFxButton = new BubbleButton("visualEffectButton", visualFxOn,
					0.66f, 0.65f, 0.2f);
		else
			visualFxButton = new BubbleButton("visualEffectButton",
					visualFxOff, 0.66f, 0.65f, 0.2f);
	}

	public void onDraw(Canvas canvas) {

		canvas.drawRect(0, 0, controler.getWidth(), controler.getHeight(),
				backgroundColor);

		super.onDraw(canvas);

		visualFxButton.updateAndDraw(canvas);
		soundButton.updateAndDraw(canvas);
		musicButton.updateAndDraw(canvas);

		resumeButton.updateAndDraw(canvas);
	}

	public void activateButton() {
		int touchX = MainActivity.getTouchX();
		int touchY = MainActivity.getTouchY();

		switchFX(touchX, touchY);
		switchSound(touchX, touchY);
		switchMusic(touchX, touchY);

		MainActivity.resetXY();
	}

	private void switchMusic(int touchX, int touchY) {
		if (musicButton.onTouch(touchX, touchY)) {

			if (OptionSettings.isMusicOn) {
				musicButton.changeBimap(musicOff);
			} else {
				musicButton.changeBimap(musicOn);
			}
			OptionSettings.isMusicOn = !OptionSettings.isMusicOn;
			SoundFactory.updateMusic();
		}
	}

	private void switchSound(int touchX, int touchY) {
		if (soundButton.onTouch(touchX, touchY)) {

			if (OptionSettings.isSoundOn) {
				soundButton.changeBimap(soundOff);
			} else {
				soundButton.changeBimap(soundOn);
			}
			SoundFactory.updateSound();
		}
	}

	private void switchFX(int touchX, int touchY) {
		if (visualFxButton.onTouch(touchX, touchY)) {

			if (OptionSettings.isFXOn) {
				visualFxButton.changeBimap(visualFxOff);
			} else {
				visualFxButton.changeBimap(visualFxOn);
			}
			SoundFactory.updateFX();
		}
	}

	@Override
	protected void loadAllBitmap(Resources resources) {
		// TODO Auto-generated method stub
		background = bitmapColection.getOptionBackground(resources);
		visualFxOn = bitmapColection.getVisualFxOn(resources);
		visualFxOff = bitmapColection.getVisualFxOff(resources);
		musicOn = bitmapColection.getMusicOn(resources);
		musicOff = bitmapColection.getMusicOff(resources);
		soundOn = bitmapColection.getSoundOn(resources);
		soundOff = bitmapColection.getSoundOff(resources);
		resumeBitmap = bitmapColection.getBack(resources);
	}

	@Override
	protected void createButtons() {
		// TODO Auto-generated method stub
		createMusicButton();
		createFxButton();
		createSoundButton();

		resumeButton = new BubbleButton("resumeButton", resumeBitmap, 0.67f,
				0.906f, 0f);
		resumeButton.staytill();
	}

	@Override
	protected void loadAnimation() {
		// TODO Auto-generated method stub
	}
}