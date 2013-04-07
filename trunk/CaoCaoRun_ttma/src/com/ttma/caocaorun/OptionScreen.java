package com.ttma.caocaorun;

import com.ttma.caocaorun.VisualFX.BubbleButton;
import com.ttma.caocaorun.utilities.BitmapCollection;
import com.ttma.caocaorun.utilities.BitmapSynchroniser;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceView;
class OptionScreen{
	Bitmap optionBackground;
	Bitmap visualFxOn,soundOn,musicOn,back;
	Bitmap visualFxOff,soundOff,musicOff;
	BitmapCollection bitmapCollection;
	
	BubbleButton visualFxButton,soundButton,musicButton,backButton;
	
	boolean visualFxSwitch,soundSwitch,musicSwitch;
	SurfaceView screen;

	Paint backgroundColor = new Paint();
	
	public OptionScreen(SurfaceView _screen, Resources _resources){
		backgroundColor.setARGB(150, 174, 207, 233);
		screen=_screen;
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
		visualFxButton = new BubbleButton("visualEffectButton", 
				visualFxOn,0.66f, 0.7f, 0.2f);
		soundButton = new BubbleButton("soundButton",
				soundOn, 0.77f, 0.51f, 0.2f);
		musicButton = new BubbleButton("musicButton", 
				musicOn,0.53f, 0.4f, 0.2f);
		
		backButton = new BubbleButton("resumeButton", 
				back,0.83f, 0.11f, 0f);
		backButton.staytill();
	}
	protected void onDraw(Canvas _canvas){
		_canvas.drawRect(0, 0, screen.getWidth(),
		screen.getHeight(), backgroundColor);
		
		Rect[] backgroundFrame = BitmapSynchroniser.getSynchonisedRect(
				optionBackground, screen.getWidth() / 2,
				screen.getHeight() / 2, true, false);
		
		_canvas.drawBitmap(optionBackground, backgroundFrame[0],
				backgroundFrame[1], null);
		
		visualFxButton.updateAndDraw(_canvas);
		soundButton.updateAndDraw(_canvas);
		musicButton.updateAndDraw(_canvas);
		
		backButton.updateAndDraw(_canvas);
	}
	
	public boolean onResume(int _touchX, int _touchY){
		if(backButton.onTouch(_touchX, _touchY)){
			return true;
		}
		else{
			return false;
		}
	}
	public void getGameOption(){
		visualFxSwitch=true;
		soundSwitch=true;
		musicSwitch=true;
	}
	
	public void changeGameOption(){

	}
	
	public void optionScreenSetControlButton(int _touchX, int _touchY){
		if(visualFxButton.onTouch(_touchX, _touchY)){
			if(visualFxSwitch){
				visualFxButton.changeBimap(visualFxOff);
				visualFxSwitch=false;
			}
			else{
				visualFxButton.changeBimap(visualFxOn);
				visualFxSwitch=true;
			}
		}
		
		if(soundButton.onTouch(_touchX, _touchY)){
			if(soundSwitch){
				soundButton.changeBimap(soundOff);
				soundSwitch=false;
			}
			else{
				soundButton.changeBimap(soundOn);
				soundSwitch=true;
			}
		}
		
		if(musicButton.onTouch(_touchX, _touchY)){
			if(musicSwitch){
				musicButton.changeBimap(musicOff);
				musicSwitch=false;
			}
			else{
				musicButton.changeBimap(musicOn);
				musicSwitch=true;
			}
		}
		changeGameOption();
	}
}
