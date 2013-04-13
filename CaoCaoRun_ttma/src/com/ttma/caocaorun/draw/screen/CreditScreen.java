package com.ttma.caocaorun.draw.screen;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceView;

import com.ttma.caocaorun.MainActivity;
import com.ttma.caocaorun.VisualFX.BubbleButton;
import com.ttma.caocaorun.utilities.BitmapCollection;
import com.ttma.caocaorun.utilities.BitmapSynchroniser;
public class CreditScreen{
	
	private BitmapCollection bitmapCollection;
	
	private BubbleButton backButton;
	
	private Bitmap back, background;
	private SurfaceView screen;

	Paint backgroundColor = new Paint();

	private boolean selected=false;
	
	public CreditScreen(SurfaceView screen, Resources resources){
		
		this.screen=screen;
		bitmapCollection = new BitmapCollection();
		
		background=bitmapCollection.getCreditScreen(resources);	
		back=bitmapCollection.getBack(resources);
		
		backButton = new BubbleButton("back", back, 0.671f, 0.906f, 0.1f);
		
		backButton.staytill();
	}
	protected void onDraw(Canvas canvas){
		canvas.drawRect(0, 0, screen.getWidth(),
				screen.getHeight(), backgroundColor);

		Rect[] backgroundFrame = BitmapSynchroniser.getBackGroundRects(
				background);
		
		canvas.drawBitmap(background, backgroundFrame[0],
				backgroundFrame[1], null);
		
		backButton.updateAndDraw(canvas);
	}
	
	public boolean onResume(){
		
		int touchX=MainActivity.getTouchX();
		int touchY=MainActivity.getTouchY();
		if(backButton.onTouch(touchX,touchY)){
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