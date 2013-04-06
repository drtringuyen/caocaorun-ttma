package com.ttma.caocaorun;

import com.ttma.caocaorun.VisualFX.BubbleButton;
import com.ttma.caocaorun.utilities.BitmapCollection;
import com.ttma.caocaorun.utilities.BitmapSynchroniser;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceView;
class ModeScreenActivity{
	Bitmap back,quiz,horror,endless,custom;
	Bitmap background;
	BitmapCollection bitmapCollection;
	BubbleButton endlessButton,quizButton,horrorButton,customButton,backButton;
	SurfaceView screen;

	public ModeScreenActivity(SurfaceView _screen, Resources _resources){
		screen=_screen;
		bitmapCollection = new BitmapCollection();
		back = bitmapCollection.getBack(_resources);
		quiz = bitmapCollection.getQuiz(_resources);
		horror = bitmapCollection.getHorror(_resources);
		endless = bitmapCollection.getEndless(_resources);
		custom = bitmapCollection.getCustom(_resources);
		background = bitmapCollection.getModeScreen(_resources);
		
		endlessButton = new BubbleButton("endlessButton", 
				endless,0.41f, 0.3f, 0.1f);
		quizButton = new BubbleButton("quizButton",
				quiz, 0.63f, 0.45f, 0.1f);
		horrorButton = new BubbleButton("horrorButton", 
				horror,0.52f, 0.61f, 0.1f);
		customButton = new BubbleButton("customButton", 
				custom,0.61f, 0.76f, 0.1f);
		backButton = new BubbleButton("backButton", 
				back,0.83f, 0.11f, 0.1f);
		backButton.staytill();
	}
	protected void onDraw(Canvas _canvas){

		Rect[] backgroundFrame = BitmapSynchroniser.getSynchonisedRect(
				background, screen.getWidth() / 2,
				screen.getHeight() / 2, true, false);
		
		_canvas.drawBitmap(background, backgroundFrame[0],
				backgroundFrame[1], null);
		
		endlessButton.updateAndDraw(_canvas);
		quizButton.updateAndDraw(_canvas);
		horrorButton.updateAndDraw(_canvas);
		customButton.updateAndDraw(_canvas);
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
	
	public Intent modeScreenControlButton(int _touchX, int _touchY){
		if(endlessButton.onTouch(_touchX, _touchY)){
			Intent endlessIntent = new
			Intent("com.ttma.caocaorun.ENDLESSMODE");
			return endlessIntent;
		}
		return null;
	}
}
