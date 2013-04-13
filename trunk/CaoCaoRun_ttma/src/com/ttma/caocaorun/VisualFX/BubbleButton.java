package com.ttma.caocaorun.VisualFX;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.ttma.caocaorun.OptionSettings;
import com.ttma.caocaorun.utilities.BitmapSynchroniser;
import com.ttma.caocaorun.utilities.SoundFactory;

public class BubbleButton {

	protected String name;

	protected Rect touchArea;// the destination rectangle for the button
	protected Bitmap bitmap;
	protected Rect boundary = null;// invisible box that the buble cannot get out
	protected Rect buttonFrame;// get the original whole bitmap
	protected Rect bufferFrame;

	protected int x, y;
	protected int dx, dy;

	protected static Paint backgroundColor = new Paint();
	protected static Paint bufferPaint = new Paint();
	protected Random generate = new Random();

	protected boolean canFly = true;

	public BubbleButton(Rect touchArea, Bitmap bitmap) {
		this.touchArea = touchArea;
		this.bitmap = bitmap;
		this.buttonFrame = BitmapSynchroniser.getSourceRect(this.bitmap);
	}

	public BubbleButton(String name, Bitmap bitmap, float percentX,
			float percentY, float percentExtension) {

		setBasicInfo(name, bitmap);

		this.touchArea = BitmapSynchroniser.getDestinationRect(this.bitmap,
				percentX, percentY, true);

		this.boundary = BitmapSynchroniser.getBoundaryRect(touchArea,
				percentExtension);
	}

	public boolean onTouch(int touchX, int touchY) {
		if (touchArea.contains(touchX, touchY)) {
			SoundFactory.playBubbleSound();
			return true;
		}
		return false;
	}

	public boolean onPress(int touchX, int touchY, boolean isPress) {
		if (touchArea.contains(touchX, touchY) && (isPress)) {
			// if (isPress) this.staytill(); else fly();
			return true;
		}
		return false;
	}

	public void changeBimap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}

	public void updateAndDraw(Canvas canvas) {
		
		updateAndDrawBuffer(canvas);
		
		reCalculatePosition();
		
		canvas.drawBitmap(this.bitmap, buttonFrame, touchArea, null);
	}
	
	protected void updateAndDrawBuffer(Canvas canvas){
		if (!boundary.equals(null) && canFly && OptionSettings.isFXOn) {
			bufferFrame = touchArea;
			canvas.drawBitmap(this.bitmap, buttonFrame, bufferFrame,
					bufferPaint);
			updateTouchArea();// update the new touchArea
		}
	}
	
	protected void reCalculatePosition(){
		if (!OptionSettings.isFXOn||!canFly) resetOriginalPosition();
	}

	public void updateAndDraw(Canvas canvas, int touchX, int touchY) {
		updateAndDraw(canvas);
		if (this.onTouch(touchX, touchY))
			canvas.drawText(this.name + "Touched", 30, 30, backgroundColor);
	}
	
	protected void resetOriginalPosition() {
		int x = boundary.centerX();
		int y = boundary.centerY();
		Rect newTouchArea = BitmapSynchroniser.getDestinationRect(bitmap, x, y,
				true, false);
		this.touchArea=newTouchArea;
	}

	public void updateTouchArea() {
		this.touchArea = getNewTouchArea();
	}
	
	protected Rect getNewTouchArea(){
		
		x = dx + touchArea.centerX();
		y = dy + touchArea.centerY();

		Rect newTouchArea = BitmapSynchroniser.getDestinationRect(bitmap, x, y,
				true, false);

		if ((newTouchArea.left < boundary.left)
				|| (newTouchArea.right > boundary.right)) {
			dx = -dx;
		}

		if ((newTouchArea.top < boundary.top)
				|| (newTouchArea.bottom > boundary.bottom)) {
			dy = -dy;
		}

		return newTouchArea;
	}

	protected void setBasicInfo(String name, Bitmap bitmap) {
		this.name = name;
		this.bitmap = bitmap;
		this.buttonFrame = BitmapSynchroniser.getSourceRect(this.bitmap);
		backgroundColor.setARGB(255, 0, 0, 0);
		bufferPaint.setAlpha(125);
		while ((dx == 0) || (dy == 0) || (dx == dy)) {
			this.dx = generate.nextInt(3);
			this.dy = generate.nextInt(3);
		}
	}

	public void staytill() {
		// this.boundary=this.touchArea;
		canFly = false;
	}

	public void fly() {
		// this.boundary=this.touchArea;
		canFly = true;
	}

	public static void enableFly() {
		OptionSettings.isFXOn = true;
	}

	public static void disableFly() {
		OptionSettings.isFXOn = false;
	}
}
