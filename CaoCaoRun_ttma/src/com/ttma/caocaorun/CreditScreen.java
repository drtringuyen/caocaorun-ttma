package com.ttma.caocaorun;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceView;

import com.ttma.caocaorun.VisualFX.BubbleButton;
import com.ttma.caocaorun.utilities.BitmapCollection;
import com.ttma.caocaorun.utilities.BitmapSynchroniser;
import com.ttma.caocaorun.utilities.BubleText;
class CreditScreen{
	BitmapCollection bitmapCollection;
	BubbleButton resumeButton;
	Bitmap back,fontSheet, background, bubble;
	private BubleText[] bubletext = new BubleText[5];
	SurfaceView screen;

	Paint backgroundColor = new Paint();

	
	public CreditScreen(SurfaceView _screen, Resources _resources){
		backgroundColor.setARGB(150, 174, 207, 233);
		screen=_screen;
		bitmapCollection = new BitmapCollection();
		
		background=bitmapCollection.getBackground(_resources);	
		fontSheet=bitmapCollection.getFontSheet(_resources);
		bubble=bitmapCollection.getBubble(_resources);
		back=bitmapCollection.getBack(_resources);
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
	protected void onDraw(Canvas _canvas){
		_canvas.drawRect(0, 0, screen.getWidth(),
				screen.getHeight(), backgroundColor);

		Rect[] backgroundFrame = BitmapSynchroniser.getSynchonisedRect(
				background, screen.getWidth() / 2,
				screen.getHeight() / 2, true, false);
		_canvas.drawBitmap(background, backgroundFrame[0],
				backgroundFrame[1], null);
		bubletext[0].onDraw(_canvas);
		bubletext[1].onDraw(_canvas);
		bubletext[2].onDraw(_canvas);
		bubletext[3].onDraw(_canvas);
		bubletext[4].onDraw(_canvas);
		
		resumeButton.updateAndDraw(_canvas);
	}
	
	public boolean onResume(int _touchX, int _touchY){
		if(resumeButton.onTouch(_touchX, _touchY)){
			return true;
		}
		else{
			return false;
		}
	}
	
}


//package com.ttma.caocaorun;
//
//import com.ttma.caocaorun.VisualFX.BubbleButton;
//import com.ttma.caocaorun.utilities.BitmapCollection;
//import com.ttma.caocaorun.utilities.BitmapSynchroniser;
//import com.ttma.caocaorun.utilities.BubleText;
//
//import android.content.res.Resources;
//import android.graphics.Bitmap;
//import android.graphics.Canvas;
//import android.graphics.Paint;
//import android.graphics.Rect;
//import android.view.SurfaceView;
//class CreditScreen{
//	BitmapCollection bitmapCollection;
//	BubbleButton resumeButton;
//	Bitmap play,fontSheet, background, bubble;
//	private BubleText[] bubletext = new BubleText[5];
//	SurfaceView screen;
//
//	Paint backgroundColor = new Paint();
//	public CreditScreen(SurfaceView _screen, Resources _resources){
//		
//		backgroundColor.setARGB(150, 174, 207, 233);
//		screen=_screen;
//		bitmapCollection = new BitmapCollection();
//		
//		background=bitmapCollection.getBackground(_resources);	
//		fontSheet=bitmapCollection.getFontSheet(_resources);
//		bubble=bitmapCollection.getBubble(_resources);
//		
//		BubleText.setInitalParameter(fontSheet);
//		
//		bubletext[0]= new BubleText("CREDITS", 0.35f, 0.3f,24,0.2f);
//		bubletext[1]= new BubleText("Nguyen Duc Tri", 0.25f, 0.4f,18,0.2f);
//		bubletext[2]= new BubleText("Tran Huu Phuong Tai", 0.25f, 0.5f,18,0.2f);
//		bubletext[3]= new BubleText("Tran Ngoc Khanh Minh", 0.25f, 0.6f,18,0.2f);
//		bubletext[4]= new BubleText("Diep So Anh", 0.25f, 0.7f,18,0.2f);
//		
//		resumeButton=new BubbleButton("resumeButton", 
//				play,0.5f, 0.8f, 0f);
//	}
//	protected void onDraw(Canvas _canvas){
//		_canvas.drawRect(0, 0, screen.getWidth(),
//				screen.getHeight(), backgroundColor);
//
//		Rect[] backgroundFrame = BitmapSynchroniser.getSynchonisedRect(
//				background, screen.getWidth() / 2,
//				screen.getHeight() / 2, true, false);
//		_canvas.drawBitmap(background, backgroundFrame[0],
//				backgroundFrame[1], null);
//		bubletext[0].onDraw(_canvas);
//		bubletext[1].onDraw(_canvas);
//		bubletext[2].onDraw(_canvas);
//		bubletext[3].onDraw(_canvas);
//		bubletext[4].onDraw(_canvas);
//		
//		resumeButton.updateAndDraw(_canvas);
//		
//	}
//	
//	public boolean onResume(int _touchX, int _touchY){
//		if(resumeButton.onTouch(_touchX, _touchY)){
//			return true;
//		}
//		else{
//			return false;
//		}
//	}
//	
//	public void optionScreenControlButton(int _touchX, int _touchY){
//		
//	}
//}
//
