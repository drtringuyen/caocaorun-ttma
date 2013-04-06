package MazeFactory;
public class Cell {
	
	private int Y;
	private int X;
	
	private int North=1;
	private int South=1;
	private int West=1;
	private int East=1;
	
	private boolean special=false;
	
//coordinates getters and setters
	public int getYCoordinate() {
		return Y;
	}

	public void setY(int a) {
		this.Y = a;
	}
	public int getXCoordinate() {
		return X;
	}
	public void setX(int b) {
		this.X = b;
	}
//end of coordinates getters and setters

//for fun
	public int getNorthWall() {
		return North;
	}
	public int getSouthWall() {
		return South;
	}
	public int getWestWall() {
		return West;
	}
	public int getEastWall() {
		return East;
	}
//for fun
	
//set start or end!
	
	
//if wall or not???
	public boolean isNorthWall(){
		if (this.North!=0) return true;
		return false;
	}
	public boolean isEastWall(){
		if (this.East!=0) return true;
		return false;
	}
	public boolean isSouthWall(){
		if (this.South!=0) return true;
		return false;
	}
	public boolean isWestWall(){
		if (this.West!=0) return true;
		return false;
	}
//end of if wall or Not???
	
	
//break wall value
	public void breakNorthWall(){
		this.North=0;
	}
	public void breakEastWall(){
		this.East=0;
	}
	public void breakSouthWall(){
		this.South=0;
	}
	public void breakWestWall(){
		this.West=0;
	}
	//end of break wall values
	
	//find special
	public void setSpecial(){
		this.special=true;
	}
	public boolean isSpecial(){
		return this.special;
	}
	
	//get total wall exists
	public int getTotalWall(){
		return North+South+West+East;
	}
	
}
