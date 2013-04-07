package com.ttma.caocaorun;

import com.ttma.caocaorun.VisualFX.BubbleButton;
import com.ttma.caocaorun.utilities.BitmapCollection;
import com.ttma.caocaorun.utilities.BitmapSynchroniser;
import com.ttma.caocaorun.utilities.BubleText;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceView;

class HighScoreScreen{
	BitmapCollection bitmapCollection;
	BubbleButton resumeButton;
	Bitmap back,fontSheet, background, bubble;
	BubleText[] bubletext = new BubleText[5];
	SurfaceView screen;
	
	public HighScoreScreen(SurfaceView _screen, Resources _resources){
		screen=_screen;
		bitmapCollection = new BitmapCollection();
		
		back=bitmapCollection.getBack(_resources);
		background=bitmapCollection.getBackground(_resources);	
		fontSheet=bitmapCollection.getFontSheet(_resources);
		bubble=bitmapCollection.getBubble(_resources);
		
		BubleText.setInitalParameter(fontSheet);
		
		bubletext[0]= new BubleText("CREDITS", 0.35f, 0.3f,24,0.2f);
		bubletext[1]= new BubleText("Nguyen Duc Tri", 0.25f, 0.4f,18,0.2f);
		bubletext[2]= new BubleText("Tran Huu Phuong Tai", 0.25f, 0.5f,18,0.2f);
		bubletext[3]= new BubleText("Tran Ngoc Khanh Minh", 0.25f, 0.6f,18,0.2f);
		bubletext[4]= new BubleText("Diep So Anh", 0.25f, 0.7f,18,0.2f);
		
		resumeButton=new BubbleButton("resumeButton", 
				back,0.83f, 0.11f, 0f);
		resumeButton.staytill();
	}
	protected void onDraw(Canvas canvas){
		
		Rect[] backgroundFrame = BitmapSynchroniser.getSynchonisedRect(
				background, screen.getWidth() / 2,
				screen.getHeight() / 2, true, false);
		
		canvas.drawBitmap(background, backgroundFrame[0],
				backgroundFrame[1], null);
		
		bubletext[0].onDraw(canvas);
		bubletext[1].onDraw(canvas);
		bubletext[2].onDraw(canvas);
		bubletext[3].onDraw(canvas);
		bubletext[4].onDraw(canvas);
		
		resumeButton.updateAndDraw(canvas);
		
	}
	
	public boolean onResume(int _touchX, int _touchY){
		if(resumeButton.onTouch(_touchX, _touchY)){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void creditsScreenControlButton(int _touchX, int _touchY){
		
	}
}