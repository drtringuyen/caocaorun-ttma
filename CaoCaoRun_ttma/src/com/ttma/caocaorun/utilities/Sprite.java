package com.ttma.caocaorun.utilities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.ttma.caocaorun.VisualFX.BubbleButton;

public class Sprite extends BubbleButton {

	private int numberOfFrame;

	// public int FPS = 30;
	// private long ticksPs = 1000 / FPS;
	// private long startTime;
	// private long sleepTime;

	private long clock;
	private int timeToChangeNewFrame = 60;

	private Rect touchArea;// the destination rectangle for the button
	private Bitmap bitmap;
	private Rect boundary = null;// invisible box that the buble cannot get out
	private Rect buttonFrame;// get the original whole bitmap
	private Rect bufferFrame;

	private int x, y;

	public Sprite(String name, Bitmap bitmap, float percentX, float percentY,
			float percentExtension, int numberOfFrame) {

		super(name, bitmap, percentX, percentY, percentExtension);

	}

	@Override
	public void updateAndDraw(Canvas canvas, int touchX, int touchY) {
		// TODO Auto-generated method stub
		updateTime();
		
		updateButtonFrame(getNewFrameScr());
		
		updateButtonFrame(getNewFrameDist());
		
		super.updateAndDraw(canvas, touchX, touchY);
	}

	private Rect getNewFrameScr() {
		return BitmapSynchroniser.getScourceRectFromStoryLine(bitmap, 3, 5);
	}

	private Rect getNewFrameDist() {

		return BitmapSynchroniser.getDestinationFromStoryLine(bitmap, x, y,
				numberOfFrame);
	}

	private void updateTime() {
		clock++;
	}

	// private void updateTime() {
	// sleepTime = ticksPs - (System.currentTimeMillis() - startTime);
	// try {
	// if (sleepTime > 0)
	// Thread.sleep(sleepTime);
	// else
	// Thread.sleep(0);
	// } catch (Exception e) {
	// // TODO: handle exception
	// }
	// }

}
