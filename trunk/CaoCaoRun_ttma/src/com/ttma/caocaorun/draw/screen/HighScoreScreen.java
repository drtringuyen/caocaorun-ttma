package com.ttma.caocaorun.draw.screen;

import com.ttma.caocaorun.MainActivity;
import com.ttma.caocaorun.VisualFX.BubbleButton;
import com.ttma.caocaorun.utilities.BitmapCollection;
import com.ttma.caocaorun.utilities.BitmapSynchroniser;
import com.ttma.caocaorun.utilities.BubleText;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceView;

public class HighScoreScreen{
	
	private BitmapCollection bitmapCollection;
	private BubbleButton resumeButton;
	private Bitmap back,fontSheet, background;
	private BubleText[] bubletext = new BubleText[5];
	
	private boolean selected=false;
	
	public HighScoreScreen(SurfaceView screen, Resources resources){
		bitmapCollection = new BitmapCollection();
		
		back=bitmapCollection.getBack(resources);
		background=bitmapCollection.getBackground(resources);	
		fontSheet=bitmapCollection.getFontSheet(resources);
		
		BubleText.setInitalParameter(fontSheet);
		
		bubletext[0]= new BubleText("NAME", 0.1f, 0.3f,24,0.2f);
		bubletext[1]= new BubleText("SCORE", 0.55f, 0.3f,24,0.2f);
		
		resumeButton=new BubbleButton("resumeButton", 
				back,0.83f, 0.11f, 0f);
		resumeButton.staytill();
	}
	protected void onDraw(Canvas canvas){
		
		Rect[] backgroundFrame = BitmapSynchroniser.getBackGroundRects(
				background);
		
		canvas.drawBitmap(background, backgroundFrame[0],
				backgroundFrame[1], null);
		
		bubletext[0].onDraw(canvas);
		bubletext[1].onDraw(canvas);
		
		resumeButton.updateAndDraw(canvas);
//		onResume();
		
	}
	
	public boolean onResume(){
		int touchX=MainActivity.getTouchX();
		int touchY=MainActivity.getTouchY();
		if(resumeButton.onTouch(touchX, touchY)){
			deselected();
			return true;
		}
		else{
			return false;
		}
	}
	
	public void bringToTop(Canvas canvas){
		selected();
		onDraw(canvas);
		onResume();
	}
	
	public void creditsScreenControlButton(int _touchX, int _touchY){
		
	}
	public void selected(){
		this.selected=true;
	}
	public void deselected(){
		this.selected=false;
	}
	public boolean isSelected(){
		return this.selected;
	}
}