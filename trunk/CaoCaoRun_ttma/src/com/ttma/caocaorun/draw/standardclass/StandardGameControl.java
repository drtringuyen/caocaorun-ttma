package com.ttma.caocaorun.draw.standardclass;

import MazeFactory.Cell;
import MazeFactory.MazeGenerator;

public class StandardGameControl {
	protected Cell[][] maze;

	protected int row = 10;
	protected int column = 10;

	protected MazeGenerator mazeGenerator;

	public StandardGameControl() {
		this.mazeGenerator= new MazeGenerator(row, column);
		this.maze=mazeGenerator.getMaze();
	}

	public Cell[][] getMaze() {
		return maze;
	}
	
	public Cell[][] reGenerateMaze(){
		this.mazeGenerator= new MazeGenerator(row, column);
		this.maze=mazeGenerator.getMaze();
		return maze;
	}

}
