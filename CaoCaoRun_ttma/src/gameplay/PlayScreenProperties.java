package gameplay;

import gameplay.GamePlayEndless.PlayScreen;
import android.graphics.Bitmap;


class PlayScreenProperties {
	private PlayScreen playScreen;
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
	
	public PlayScreen getTscreen() {
		return playScreen;
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
	
	public PlayScreenProperties(PlayScreen playScreen,Bitmap spreadSheet, Bitmap background, Bitmap poop, 
			int row, int column){
		this.playScreen=playScreen;
		
		this.column=column;
		this.row=row;
		
		this.bitmap_size = spreadSheet.getWidth() / 4;	
		this.height = playScreen.getHeight();
		this.width = playScreen.getWidth();

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
