package MazeFactory;

import java.util.Random;
import java.util.Stack;

public class MazeGenerator {

	private Cell[][] maze = new Cell[100][100];

	private int HEIGHT = 0;
	private int WIDTH = 0;

	private int visitedCell;

	private Random generate = new Random();

	public MazeGenerator(int height, int width) {
		this.HEIGHT = height;
		this.WIDTH = width;
		initialize();
		createMaze();
		makeDoor(0,0,this.WIDTH-1,this.HEIGHT-1);
	}

	private void initialize() {
		// Initialize maze
		for (int i = 0; i < this.HEIGHT; i++) {
			for (int j = 0; j < this.WIDTH; j++) {
				maze[i][j] = new Cell();
				maze[i][j].setY(i);
				maze[i][j].setX(j);
			}
		}

	}
	
	private void makeDoor(int startX,int startY,int endX,int endY){
		maze[startY][startX].breakNorthWall();
		maze[endY][endX].breakSouthWall();
	}

	public void createMaze() {

		int totalCell = this.HEIGHT * this.WIDTH;
		Stack<Cell> cellStack = new Stack<Cell>();

		Cell curentCell =getStartCell();
		
		visitedCell = 1;

		while (visitedCell < totalCell) {
			
			int curentX = curentCell.getXCoordinate();
			int curentY = curentCell.getYCoordinate();

			if (canGoAnyDirection(curentY, curentX)) {//so inside, they will chose until 1 satisfies

				cellStack.push(curentCell);
				
				System.out.printf("%d:%d i:%d \n", curentX,curentY,visitedCell);
				
				Cell goingCell=null;
				
				
				while (goingCell==null) {// check by random after this part,should choose at least

					
					int randomDirection=generate.nextInt(4);
					
					
					goingCell = chooseDirectionToGo(curentY, curentX,
							curentCell, randomDirection);
					
				}
				
				curentCell=goingCell;
				visitedCell++;
				
				
			} else {//end of the road, pop out the last point with possible ways 
				curentCell = cellStack.lastElement();
				cellStack.pop();
			}
		}
	}

	// get maze and size
	public Cell[][] getMaze() {
		return maze;
	}

	public int getHEIGHT() {
		return HEIGHT;
	}

	public int getWIDTH() {
		return WIDTH;
	}

	// getCell from position-------------------------------------------
	private Cell getCurentCell(int Y, int X) {
		return maze[Y][X];
	}

	private Cell getNorthCell(int Y, int X) {
		return maze[Y - 1][X];
	}

	private Cell getEastCell(int Y, int X) {
		return maze[Y][X + 1];
	}

	private Cell getSouthCell(int Y, int X) {
		return maze[Y + 1][X];
	}

	private Cell getWestCell(int Y, int X) {
		return maze[Y][X - 1];
	}

	// -----------------------------------------------------------------------------end
	// of getCell--------------------------------------------

	// break the walls
	private Cell chooseDirectionToGo(int Y, int X, Cell curentCell,
			int direction) {

		if ((direction == 0) && (canGoNorth(Y, X))) {
			curentCell.breakNorthWall();
			curentCell = getNorthCell(Y, X);
			curentCell.breakSouthWall();
			return curentCell;
		}

		if ((direction == 1) && (canGoEast(Y, X))) {
			curentCell.breakEastWall();
			curentCell = getEastCell(Y, X);
			curentCell.breakWestWall();
			return curentCell;
		}

		if ((direction == 2) && (canGoSouth(Y, X))) {
			curentCell.breakSouthWall();
			curentCell = getSouthCell(Y, X);
			curentCell.breakNorthWall();
			return curentCell;
		}

		if ((direction == 3) && (canGoWest(Y, X))) {
			curentCell.breakWestWall();
			curentCell = getWestCell(Y, X);
			curentCell.breakEastWall();
			return curentCell;
		}
		return null;
	}

	// -------------------------------------------------------------------------------end
	// of break the wall---------------------------------
	// check adjection cells
	private boolean checkBoundary(int Y, int X) {
		if (Y > this.HEIGHT-1 || X > this.WIDTH-1 || Y < 0 || X <0)
			return true;
		return false;
	}

	private boolean canGoNorth(int Y, int X) {
		if (checkBoundary(Y - 1, X))
			return false;
		if (maze[Y - 1][X].getTotalWall() == 4)
			return true;
		return false;
	}

	private boolean canGoEast(int Y, int X) {
		if (checkBoundary(Y, X + 1))
			return false;
		int temp = maze[Y][X + 1].getTotalWall();
		if (temp == 4)
			return true;
		return false;
	}

	private boolean canGoSouth(int Y, int X) {
		if (checkBoundary(Y + 1, X))
			return false;
		if (maze[Y + 1][X].getTotalWall() == 4)
			return true;
		return false;
	}

	private boolean canGoWest(int Y, int X) {
		if (checkBoundary(Y, X - 1))
			return false;
		if (maze[Y][X - 1].getTotalWall() == 4)
			return true;
		return false;
	}

	// ------------------------------------------------------------------------end
	// of check adjection cells

	// check ways chosen
	private boolean canGoAnyDirection(int curentY, int curentX) {
		if (canGoNorth(curentY, curentX) || canGoEast(curentY, curentX)
				|| canGoSouth(curentY, curentX) || canGoWest(curentY, curentX))
			return true;
		return false;
	}

	private Cell getStartCell() {
		int randomY = generate.nextInt(HEIGHT);
		int randomX = generate.nextInt(WIDTH);
		Cell tempCell = getCurentCell(randomY, randomX);
		return tempCell;
	}

	// ---------------------------------------------------------------------------------set
	// special cell???
	public void setMazeSpecial(int Y, int X) {
		this.maze[Y][X].setSpecial();
	}
}
