package test.touch;

import test.touch.TouchExample.TouchScreen;
import MazeFactory.Cell;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

class DrawMaze {
	Bitmap spreadSheet, background,poop;
	TouchScreen tscreen;
	Cell[][] Maze= new Cell[100][100];
	private int row;
	private int column;
	private int maze_y;
	private int maze_x;

	private int bitmap_size;
	private int scale_size;
	
	public DrawMaze(TouchScreen tscreen, Cell[][] maze, ScreenProperties screenProperties) {
		// TODO Auto-generated constructor stub
		this.tscreen=tscreen;
		this.spreadSheet=screenProperties.getSpreadSheet();
		this.background=screenProperties.getBackground();
		this.Maze=maze;
		
		this.bitmap_size = screenProperties.getBITMAP_SIZE();	
		this.row=screenProperties.getROW();
		this.column=screenProperties.getCOLUMN();
		
		this.scale_size = screenProperties.getSCALED_SIZE();

		this.maze_x = screenProperties.getMAZE_X();
		this.maze_y = screenProperties.getMAZE_Y();
		
		///////////////////////////////////////////
	}



	public void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		//updateInformation(canvas);
		canvas.drawBitmap(background, 0, 0, null);
		for (int y = 0; y < row; y++) {
			for (int x = 0; x < column; x++) {
				int imageCode = getImageCode(Maze[y][x]);
				Rect crop = getCrop(imageCode / 4, imageCode % 4);
				Rect position = getPosition(y, x);
				canvas.drawBitmap(spreadSheet, crop, position, null);
			}
		}
	}

	private Rect getCrop(int indexY, int indexX) {
		int px = indexX * bitmap_size + indexX;
		int py = indexY * bitmap_size + indexY;
		return new Rect(px, py, px + bitmap_size-indexX, py + bitmap_size-indexY);
	}

	private Rect getPosition(int positionY, int positionX) {
		int px = positionX * scale_size + maze_x;
		int py = positionY * scale_size + maze_y;
		return new Rect(px, py, px + scale_size, py + scale_size);
	}

	private int getImageCode(Cell curentCell) {
		return curentCell.getNorthWall() * 8 + curentCell.getEastWall() * 4
				+ curentCell.getSouthWall() * 2 + curentCell.getWestWall();
	}
	
}
