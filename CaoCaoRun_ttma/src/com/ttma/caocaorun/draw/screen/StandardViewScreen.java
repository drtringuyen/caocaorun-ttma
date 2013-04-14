package com.ttma.caocaorun.draw.screen;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.ttma.caocaorun.ControlView;
import com.ttma.caocaorun.MainActivity;
import com.ttma.caocaorun.VisualFX.BubbleButton;
import com.ttma.caocaorun.utilities.BitmapCollection;
import com.ttma.caocaorun.utilities.BitmapSynchroniser;

public class StandardViewScreen {

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

	public void activateButton() {
		//set fly the buttons
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