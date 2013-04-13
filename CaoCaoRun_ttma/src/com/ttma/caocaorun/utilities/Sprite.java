package com.ttma.caocaorun.utilities;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.ttma.caocaorun.OptionSettings;
import com.ttma.caocaorun.VisualFX.BubbleButton;

public class Sprite extends BubbleButton {

	private int numberOfFrame;

	 public int FPS = 60;
	 private long ticksPs = 1000 / FPS;
	 private long startTime;
	 private long sleepTime;

	private int clock;

	// private Rect touchArea;// the destination rectangle for the button
	// private Bitmap bitmap;
	// private Rect boundary = null;// invisible box that the buble cannot get
	// out
	// private Rect buttonFrame;// get the original whole bitmap
	// private Rect bufferFrame;

//	private int x, y;

	public Sprite(String name, Bitmap bitmap, float percentX, float percentY,
			float percentExtension, int numberOfFrame) {
		super(name, bitmap, percentX, percentY, percentExtension);
		this.numberOfFrame=numberOfFrame;
		this.buttonFrame = BitmapSynchroniser.getScourceRectFromSprite(bitmap,
				0, this.numberOfFrame, 1);
		
		this.touchArea = BitmapSynchroniser.getDestinationRect(buttonFrame,
				percentX, percentY, true);

	}

	// @Override
	public void updateAndDraw(Canvas canvas) {
		// TODO Auto-generated method stub

		updateAndDrawBuffer(canvas);
		updateButtonFrame();

		canvas.drawBitmap(this.bitmap, this.buttonFrame, this.touchArea, null);
		updateTime();
	}

	protected void updateButtonFrame() {
		if (!OptionSettings.isFXOn)
			return;
		clock++;
		this.buttonFrame = BitmapSynchroniser.getScourceRectFromSprite(bitmap,
				(int)(clock/3)%numberOfFrame, numberOfFrame, 1);
	}
	
	private void updateTime() {
		sleepTime = ticksPs - (System.currentTimeMillis() - startTime);
		try {
			if (sleepTime > 0)
				Thread.sleep(sleepTime);
			else
				Thread.sleep(0);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
