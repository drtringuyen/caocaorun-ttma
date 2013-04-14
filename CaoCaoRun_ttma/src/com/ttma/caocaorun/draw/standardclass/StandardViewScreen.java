package com.ttma.caocaorun.draw.standardclass;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.ttma.caocaorun.ControlView;
import com.ttma.caocaorun.MainActivity;
import com.ttma.caocaorun.VisualFX.BubbleButton;
import com.ttma.caocaorun.utilities.BitmapCollection;
import com.ttma.caocaorun.utilities.BitmapSynchroniser;

public abstract class StandardViewScreen {

	protected BitmapCollection bitmapColection;
	protected Bitmap background;
	protected boolean isView = false;
	protected Bitmap resumeBitmap;
	protected BubbleButton resumeButton;
	protected ControlView controler;
	
	protected boolean isBrowser=false;

	public StandardViewScreen(ControlView controler, Resources resources, boolean isBrowser) {
		this.isBrowser=isBrowser;
		this.controler = controler;
		bitmapColection = new BitmapCollection();
		
		loadAllBitmap(resources);
		createButtons();
		loadAnimation();
	}

	public void onDraw(Canvas canvas) {

		Rect[] backgroundFrame = BitmapSynchroniser
				.getBackGroundRects(background);

		canvas.drawBitmap(background, backgroundFrame[0], backgroundFrame[1],
				null);
	}

	public boolean onResume() {
		int touchX = MainActivity.getTouchX();
		int touchY = MainActivity.getTouchY();
		if (resumeButton.onTouch(touchX, touchY)) {
			bringToBack();
			MainActivity.resetXY();
			return true;
		} else {
			return false;
		}
	}
	
	protected abstract void loadAllBitmap(Resources resources);
	
	protected abstract void createButtons();
	
	protected abstract void loadAnimation();

	public void activateButton() {
		//set fly the buttons
		//in case the screen has subscreen, 
		//ensure that the subscreen is on top
		//so bring this browser to back of the subscreen
		if (isBrowser) this.bringToBack();
		MainActivity.resetXY();
	}
	
	public void deActivateButton(){
		//set staytill the buttons
		MainActivity.resetXY();
	}

	public void bringToTop(Canvas canvas) {
		selected();
		onDraw(canvas);
		onResume();
		activateButton();
	}
	
	public void bringToBack(){
		deselected();
	}

	public void selected() {
		this.isView = true;
	}

	public void deselected() {
		this.isView = false;
	}

	public boolean isSelected() {
		return this.isView;
	}

}