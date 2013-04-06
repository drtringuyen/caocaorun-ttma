package com.ttma.caocaorun;

import android.graphics.Bitmap;
import android.view.SurfaceView;

class ScreenProperties {
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public Bitmap getMenu() {
		return menu;
	}

	public Bitmap getPlay() {
		return play;
	}

	public Bitmap getCredits() {
		return credits;
	}

	public Bitmap getHighscore() {
		return highscore;
	}

	public Bitmap getOptions() {
		return options;
	}

	private int height;
	private int width;
	private Bitmap menu,play,credits,highscore,options;
	public ScreenProperties(SurfaceView screen, Bitmap[] bitmapCollection){
		this.height=screen.getHeight();
		this.width=screen.getWidth();
		this.menu=Bitmap.createBitmap(bitmapCollection[0]);
		this.play=Bitmap.createBitmap(bitmapCollection[1]);
	}

}
