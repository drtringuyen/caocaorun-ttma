package com.ttma.caocaorun.utilities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class BubleText {

	private static Bitmap fontsheet;

	private String content;

	private float x;
	private float y;
	private float spacing;

	private int row = 6;
	private int column = 16;
	
	private float fontSize;
	
	private float width, height;

	private Rect cropFix;

	public static void setInitalParameter(Bitmap fontsheet) {
		BubleText.fontsheet = fontsheet;
	}

	public BubleText(String content, float x, float y, int fontSize,
			float spacing) {
		
		this.content = content;
		this.x = x;
		this.y = y;
		this.spacing = spacing;
		
		this.cropFix = BitmapSynchroniser.getScourceRectFromSprite(fontsheet,
				0, column, row);
		this.height = cropFix.height();
		this.width = cropFix.width();
		this.fontSize=this.width / BitmapSynchroniser.getDefautWidth() * fontSize;
	}

	public void updateAndDraw(Canvas canvas, int time) {
		this.content = (time / 100000) % 100 + ":" + (time / 1000) % 100;
		drawRightToLeft(canvas);
	}
	
	public void onDraw(Canvas canvas){
		drawLeftToRight(canvas);
	}

	private void drawLeftToRight(Canvas canvas) {

		// prepare the first cursor for drawing as well as the size of 1 frame
		Rect cursor = BitmapSynchroniser.getDestinationRect(cropFix, x, y,
				true,fontSize);

		for (int i = 0; i < content.length(); i++) {
			// backward so we can rightIndent and overlap the behind characters
			int index = content.charAt(i) - 32;// map with our spread sheet
												// started with <space>
			Rect crop = BitmapSynchroniser.getScourceRectFromSprite(fontsheet,
					index, column, row);
			canvas.drawBitmap(fontsheet, crop, cursor, null);
			// prepare the cursor for next drawing
			cursor = BitmapSynchroniser.getNextToRightRect(cursor, true,
					adjustSpacing(index));
			
		}
	}
	
	private void drawRightToLeft(Canvas canvas) {

		// get the first cursor for drawing as well as the size of 1 frame
		Rect cursor = BitmapSynchroniser.getDestinationRect(cropFix, x, y,
				true,fontSize);

		for (int i = content.length()-1;i>0; i--) {
			// backward so we can rightIndent and overlap the behind characters
			int index = content.charAt(i) - 32;// map with our spread sheet
												// started with <space>
			Rect crop = BitmapSynchroniser.getScourceRectFromSprite(fontsheet,
					index, column, row);
			canvas.drawBitmap(fontsheet, crop, cursor, null);
			// prepare the next cursor to draw right after the previous one
			cursor = BitmapSynchroniser.getNextToLeftRect(cursor, true,
					adjustSpacing(index));
		}
	}

	public void onTouch(int touchX,int touchY){
		this.x=touchX/BitmapSynchroniser.getDefautWidth();
		this.y=touchY/BitmapSynchroniser.getDefautHeight();
	}
	// check for special character such as lower cases that smaller than the
	// others
	private float adjustSpacing(int index) {
		if (index == 0)
			return this.spacing * 3f;
		if (index > 59 || index < 2 || (index > 6 && index < 16)
				|| (index > 26 && index < 32))
			return this.spacing * 2.5f;
		return spacing;
	}

	// public static void drawToString(String input,Canvas canvas){
	//
	// int index= input.charAt(0)-32;
	// Rect crop=BitmapSynchroniser.getScourceRect1(fontsheet, index, 16, 6);
	// Rect position=BitmapSynchroniser.getDestinationRect(crop, x, y, true);
	// canvas.drawBitmap(fontsheet, crop, position, null);
	// }

}
