package test.touch;

import test.touch.TouchExample.TouchScreen;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

class DrawTime {
	public Bitmap numberSheet;
	private int number_size,number_height,scaleSize;
	private int level_w=100;
	private int level_h=50;
	private int time_w=100;
	private int time_h=100;
	public DrawTime(TouchScreen tscreen,ScreenProperties screenProperties){
		numberSheet = screenProperties.getNumberSheet();
		number_size = screenProperties.getNumber_size();
		number_height = screenProperties.getNumber_height();
		scaleSize = tscreen.getHeight()/25;
	}
	protected void onDraw(Canvas canvas, long min, long sec, int level){
		int st,nd;
		Rect crop,position;
		st = (int)min/10;
		nd = (int)min%10;
		
		crop = getCrop(st);
		position = getPosition(0);
		canvas.drawBitmap(numberSheet, crop, position, null);
		
		crop = getCrop(nd);
		position = getPosition(1);
		canvas.drawBitmap(numberSheet, crop, position, null);
		
		crop = getCrop(10);
		position = getPosition(2);
		canvas.drawBitmap(numberSheet, crop, position, null);
		
		st = (int)sec/10;
		nd = (int)sec%10;
		crop = getCrop(st);
		position = getPosition(3);
		canvas.drawBitmap(numberSheet, crop, position, null);
		
		crop = getCrop(nd);
		position = getPosition(4);
		canvas.drawBitmap(numberSheet, crop, position, null);
		
		st = (int) level/10;
		nd = (int) level%10;
		crop = getCrop(st);
		position = getPositionLevel(0);
		canvas.drawBitmap(numberSheet, crop, position, null);
		
		crop = getCrop(nd);
		position = getPositionLevel(1);
		canvas.drawBitmap(numberSheet, crop, position, null);
		
		
	}
	private Rect getCrop(int index) {
		int px = number_size*index;
		int py = number_height;
		return new Rect(px, 0, px + number_size , py);
	}

	private Rect getPosition(int index) {
		int px = index*scaleSize+time_w;
		return new Rect(px, time_h, px+scaleSize, time_h + scaleSize);
	}
	
	private Rect getPositionLevel(int index) {
		int px = index*scaleSize+level_w;
		return new Rect(px, level_h, px+scaleSize, level_h + scaleSize);
	}
}
