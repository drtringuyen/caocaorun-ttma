package gameplay;

import com.ttma.caocaorun.R;

import gameplay.draw.bg.DrawBgFactory;
import gameplay.draw.maze.DrawMazeFactory;
import gameplay.draw.path.DrawPathFactory;
import gameplay.draw.poop.DrawPoopFactory;
import MazeFactory.Cell;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnTouchListener;

public class GamePlayEndless extends Activity implements OnTouchListener {
	PlayScreen playScreen;
	
	DrawBgFactory drawBg;
	DrawMazeFactory drawMaze;
	DrawPoopFactory drawPoop;
	DrawPathFactory drawPath;
	PlayScreenProperties playScreenProperties;
	
	public int row = 10;
	public int column = 10;
	
	//control sweep
	public float x_start;
	public float y_start;
	public float x_end;
	public float y_end;

	public int poop_maze_x;
	public int poop_maze_y;
	public boolean isMove;
	///////////////////////////////////////
	
	public Cell[][] Maze = new Cell[100][100];
	
	Bitmap spreadSheet, background,poop;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //input information
		spreadSheet = BitmapFactory.decodeResource(getResources(),
				R.drawable.spreadsheet2);
		background = BitmapFactory.decodeResource(getResources(),
				R.drawable.background2);
		poop = BitmapFactory.decodeResource(getResources(), R.drawable.poop);
		/////////////////////////////////////////////////
		
		/////////////////////////////////////////////////
        playScreen = new PlayScreen(this);
        playScreen.setOnTouchListener(this);
        setContentView(playScreen);
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		playScreen.pause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		playScreen.resume();
	}

	public class PlayScreen extends SurfaceView implements Runnable{
		Thread gameplayControl = null;
		boolean isLoadOk=false;
		public PlayScreen(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			
		}
		
		public void pause(){
			isLoadOk=false;
			while(true){
				try{
					gameplayControl.join();
				}
				catch (InterruptedException e){
					e.printStackTrace();
				}
				break;
			}
			gameplayControl=null;
		}
		
		public void resume(){
			isLoadOk=true;
			gameplayControl = new Thread(this);
			gameplayControl.start();
		}
		
		
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN:
			x_start=event.getX();
			y_start=event.getY();
			break;
		case MotionEvent.ACTION_UP:
			x_end=event.getX();
			y_end=event.getY();
			isMove=true;
			break;
		}
		return true;
	}

}
