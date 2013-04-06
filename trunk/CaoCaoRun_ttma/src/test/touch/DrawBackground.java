package test.touch;

import test.touch.TouchExample.TouchScreen;
import android.graphics.Bitmap;
import android.graphics.Canvas;

class DrawBackground {
	Bitmap spreadSheet, background;
	TouchScreen tscreen;
	
	private int ROW;
	private int COLUMN;
	private int MAZE_Y;
	private int MAZE_X;
	private int HEIGHT;
	private int WIDTH;
	private int PADDING;

	private int BITMAP_SIZE;
	private int SCALED_SIZE;
	private int BORDER_SIDE;
	
	public int top_h_start;
	public int top_h_end;
	public int bottom_h_start;
	public int bottom_h_end;
	public DrawBackground(TouchScreen touchScreen, Bitmap background,
			Bitmap button, Bitmap logo, Bitmap footer, int rOW, int cOLUMN) {
		// TODO Auto-generated constructor stub
		tscreen=touchScreen;
		this.background=background;
		ROW=rOW;
		COLUMN=cOLUMN;
	}

	public void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		
	}
	
	private void provideInput(){
		HEIGHT = tscreen.getHeight();
		WIDTH = tscreen.getWidth();
		PADDING=HEIGHT/100;
		BORDER_SIDE=WIDTH/10;
		SCALED_SIZE = (WIDTH - BORDER_SIDE * 2) / COLUMN;
		
		MAZE_X = WIDTH / 2 - SCALED_SIZE * COLUMN / 2;
		MAZE_Y = HEIGHT / 2 - SCALED_SIZE * ROW / 2;
		
		top_h_start=PADDING;
		top_h_end=MAZE_Y-PADDING;
		bottom_h_start=MAZE_Y+ROW*SCALED_SIZE;
		bottom_h_end=HEIGHT-PADDING;
	}

}
