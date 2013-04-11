package test.touch;

import com.ttma.caocaorun.utilities.SoundFactory;

import MazeFactory.Cell;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import test.touch.TouchExample.TouchScreen;

class DrawPoop {

	public Bitmap poop;
	public int SCALED_SIZE;

	Cell[][] Maze = new Cell[100][100];
	public int[] Patch = new int[10000];
	public int ROW;
	public int COLUMN;

	ControlPoop controlPoop;
	// /////////////////////////////////////

	// poop location
	float poop_x_pixel;
	float poop_y_pixel;
	int poop_maze_x;
	int poop_maze_y;
	int goTo=-1;

	public DrawPoop(TouchScreen tscreen, Cell[][] maze,
			ScreenProperties screenProperties) {
		this.poop = screenProperties.getPoop();
		this.poop_x_pixel = screenProperties.getMAZE_X();
		this.poop_y_pixel = screenProperties.getMAZE_Y();
		this.SCALED_SIZE = screenProperties.getSCALED_SIZE();
		this.Maze = maze;
		this.ROW = screenProperties.getROW();
		this.COLUMN = screenProperties.getCOLUMN();
		this.poop_maze_y = 0;
		this.poop_maze_x = 0;
		this.poop = Bitmap.createScaledBitmap(poop, SCALED_SIZE, SCALED_SIZE,
				false);
		controlPoop = new ControlPoop();
		// TODO Auto-generated constructor stub
	}

	public void onDraw(Canvas canvas, float x_end, float x_start, float y_end,
			float y_start, boolean isMove) {
		// TODO Auto-generated method stub

		if (isMove) {
			SoundFactory.playPoopSound();
			goTo=update(x_end, x_start, y_end, y_start);
		}
		//the else here use for auto-run, if you want to stop autorun, just lock it.
		else{
			goTo=autorun_update();
		}
		

		canvas.drawBitmap(poop, poop_x_pixel, poop_y_pixel, null);
	}

	private int update(float x_end, float x_start, float y_end, float y_start) {
		Cell currentCell = Maze[poop_maze_y][poop_maze_x];
		int _goTo = controlPoop
				.goTo(x_end, x_start, y_end, y_start, currentCell);
		if (checkPoopAtBegin() && _goTo == 0)
			_goTo = -1;
		if (checkPoopAtEnd() && _goTo == 2)
			_goTo = -1;
		update_poop_status(_goTo);
		return _goTo;
	}
	
	private int autorun_update(){
		Cell currentCell = Maze[poop_maze_y][poop_maze_x];
		int _goTo=-1;
		if(isAtCorner()==false){
			_goTo=controlPoop.autorun(currentCell, goTo);
		}
		else{
			_goTo=-1;
		}
		
		if (checkPoopAtBegin() && _goTo == 0)
			_goTo = -1;
		if (checkPoopAtEnd() && _goTo == 2)
			_goTo = -1;
		
		update_poop_status(_goTo);
		return _goTo;
	}

	private void update_poop_status(int goTo) {
		switch (goTo) {
		case 0:
			poop_maze_y = poop_maze_y - 1;
			poop_y_pixel = poop_y_pixel - SCALED_SIZE;
			break;
		case 1:
			poop_maze_x = poop_maze_x + 1;
			poop_x_pixel = poop_x_pixel + SCALED_SIZE;
			break;
		case 2:
			poop_maze_y = poop_maze_y + 1;
			poop_y_pixel = poop_y_pixel + SCALED_SIZE;
			break;
		case 3:
			poop_maze_x = poop_maze_x - 1;
			poop_x_pixel = poop_x_pixel - SCALED_SIZE;
			break;
		}
	}

	public boolean checkPoopAtBegin() {
		if (poop_maze_x == 0 && poop_maze_y == 0) {
			return true;
		}
		return false;
	}

	public boolean checkPoopAtEnd() {
		if (poop_maze_x == COLUMN - 1 && poop_maze_y == ROW - 1) {
			return true;
		}
		return false;
	}
	
	private boolean isAtCorner(){
		Cell currentCell = Maze[poop_maze_y][poop_maze_x];
		if(currentCell.getTotalWall()<2){
			return true;
		}
		return false;
	}
}
