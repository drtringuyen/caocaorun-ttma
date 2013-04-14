package com.ttma.caocaorun.draw.standardclass;

import MazeFactory.Cell;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.ttma.caocaorun.utilities.BitmapCollection;
import com.ttma.caocaorun.utilities.BitmapSynchroniser;

class StandardMazeView {
	
	Bitmap spreadSheet;
	
	Cell[][] maze;
	private int row;
	private int column;
	private int maze_y;
	private int maze_x;

	private int scale_size;

	public StandardMazeView(Cell[][] maze,Resources resources) {
		
		// TODO Auto-generated constructor stub
		this.spreadSheet = BitmapCollection.getEndlessModeSpreadSheet(resources);
		this.maze = maze;
		
		this.row = this.maze.length;
		this.column = this.maze[0].length;
		
		int width=(int) BitmapSynchroniser.getDefautWidth();
		int height=(int) BitmapSynchroniser.getDefautHeight();
		
		int border_size=width/10;

		this.scale_size = (width - border_size * 2) / this.column;

		this.maze_x = width / 2 - scale_size * this.column / 2;
		this.maze_y = height / 2 - scale_size * row / 2;

		// /////////////////////////////////////////
	}
	
	public void setMaze(Cell[][] maze){
		this.maze=maze;
	}

	public void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		for (int y = 0; y < row; y++) {
			for (int x = 0; x < column; x++) {
				int imageCode = getImageCode(maze[y][x]);
				Rect crop = getCrop(imageCode);
				Rect position = getPosition(y, x);
				canvas.drawBitmap(spreadSheet, crop, position, null);
			}
		}
	}

	private Rect getCrop(int imageCode) {
		return BitmapSynchroniser.getScourceRectFromSprite(spreadSheet,
				imageCode, 4, 4);
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
