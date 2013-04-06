package test.touch;

import android.graphics.Bitmap;
import test.touch.TouchExample.TouchScreen;

class ScreenProperties {
	private TouchScreen tscreen;
	private int row;
	private int column;
	
	private Bitmap spreadSheet, background,poop;
	
	private int maze_y;
	private int maze_x;
	private int height;
	private int width;

	private int padding;
	private int bitmap_size;
	private int scale_size;
	private int border_size;
	public TouchScreen getTscreen() {
		return tscreen;
	}

	public int getROW() {
		return row;
	}

	public int getCOLUMN() {
		return column;
	}

	public Bitmap getSpreadSheet() {
		return spreadSheet;
	}

	public Bitmap getBackground() {
		return background;
	}

	public Bitmap getPoop() {
		return poop;
	}

	public int getMAZE_Y() {
		return maze_y;
	}

	public int getMAZE_X() {
		return maze_x;
	}

	public int getHEIGHT() {
		return height;
	}

	public int getWIDTH() {
		return width;
	}

	public int getPADDING() {
		return padding;
	}

	public int getBITMAP_SIZE() {
		return bitmap_size;
	}

	public int getSCALED_SIZE() {
		return scale_size;
	}

	public int getBORDER_SIDE() {
		return border_size;
	}
	
	public ScreenProperties(TouchScreen tscreen,Bitmap spreadSheet, Bitmap background, Bitmap poop, 
			int row, int column){
		this.tscreen=tscreen;
		
		this.column=column;
		this.row=row;
		
		this.bitmap_size = spreadSheet.getWidth() / 4;	
		this.height = tscreen.getHeight();
		this.width = tscreen.getWidth();

		this.padding=height/100;
		this.border_size=width/10;
		
		this.scale_size = (width - border_size * 2) / this.column;

		this.maze_x = width / 2 - scale_size * this.column / 2;
		this.maze_y = height / 2 - scale_size * row / 2;
		
		this.spreadSheet=spreadSheet;
		this.background=Bitmap.createScaledBitmap(background, width, height, false);
		this.poop=Bitmap.createScaledBitmap(poop, scale_size, scale_size, false);
		
	}
	
	
}
