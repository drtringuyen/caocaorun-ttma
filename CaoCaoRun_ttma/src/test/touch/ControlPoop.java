package test.touch;

import MazeFactory.Cell;

public class ControlPoop {
	
	private static float limitSin = 0.71f; 
	
	public int goTo(float x_end, float x_start,
			float y_end, float y_start, Cell currentCell){
		int goTo=-1;
		float delx = x_end-x_start;
		float dely = y_end-y_start;
		if(uselessTouch(delx, dely)) goTo=-1;
		else{
			goTo= findPoopWay(delx, dely);
			if(hasWall(currentCell, goTo))
				goTo=-1;
		}
		return goTo;
	}
	
	public int autorun(Cell currentCell, int curGoto){
		int nextGoTo=-1;
		if(hasWall(currentCell, curGoto)){
		switch(curGoto){
			case 0:
				if(currentCell.isWestWall()) nextGoTo=1;
				else nextGoTo=3;
				break;
			case 1:
				if(currentCell.isNorthWall()) nextGoTo=2;
				else nextGoTo=0;
				break;
			case 2:
				if(currentCell.isWestWall()) nextGoTo=1;
				else nextGoTo=3;
				break;
			case 3:
				if(currentCell.isNorthWall()) nextGoTo=2;
				else nextGoTo=0;
			}
		}
		else{
			nextGoTo=curGoto;
		}
		if(currentCell.getTotalWall()==3){
			nextGoTo=-1;
		}
		return nextGoTo;
	}
	
	private boolean hasWall(Cell currentCell, int goTo){
		switch(goTo){
		case 0:
			if(currentCell.isNorthWall())
				return true;
			break;
		case 1:
			if(currentCell.isEastWall())
				return true;
			break;
		case 2:
			if(currentCell.isSouthWall())
				return true;
			break;
		case 3:
			if(currentCell.isWestWall())
				return true;
			break;
		}
		return false;
	}
	
	private boolean uselessTouch(float _delx, float _dely){
		if(Math.abs(_delx)<30 && Math.abs(_dely)<30){			
			return true;
		}
		else return false;
	}
	
	private float calculateCurSin(float _delx, float _dely) {
		float canh_huyen = (float) Math.sqrt(_delx*_delx + _dely*_dely);
		
		return (_dely/canh_huyen);
	}
	
	private int findPoopWay(float _delx, float _dely){
		int goTo=-1;
		//Don't know why direction is reverse ???
		if(moveNorth(_delx, _dely, calculateCurSin(_delx, _dely))) goTo=2;
		if(moveSouth(_delx, _dely, calculateCurSin(_delx, _dely))) goTo=0;
		if(moveWest(_delx, _dely, calculateCurSin(_delx, _dely))) goTo=3;
		if(moveEast(_delx, _dely, calculateCurSin(_delx, _dely))) goTo=1;
		
		return goTo;
	}
	
	private boolean moveNorth(float _delx, float _dely, float _sinx){
		if(_delx>0 && _dely>0 && _sinx>limitSin)
			return true;
		if(_delx<0 && _dely>0 && _sinx>limitSin)
			return true;
		
		return false;
	}
	
	private boolean moveSouth(float _delx, float _dely, float _sinx){
		if(_delx<0 && _dely<0 && _sinx<(0.0f - limitSin))
			return true;
		if(_delx>0 && _dely<0 && _sinx<(0.0f - limitSin))
			return true;
		
		return false;
	}
	
	private boolean moveWest(float _delx, float _dely, float _sinx){
		if(_delx<0 && _dely>0 && _sinx<(limitSin))
			return true;
		if(_delx<0 && _dely<0 && _sinx>(0.0f - limitSin))
			return true;
		
		return false;
	}
	
	private boolean moveEast(float _delx, float _dely, float _sinx){
		if(_delx>0 && _dely>0 && _sinx<(limitSin))
			return true;
		if(_delx>0 && _dely<0 && _sinx>(0.0f - limitSin))
			return true;
		
		return false;
	}

}
