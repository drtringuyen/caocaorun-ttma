package test.touch;

import MazeFactory.Cell;
import MazeFactory.MazeGenerator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;

import com.ttma.caocaorun.R;

public class TouchExample extends Activity implements OnTouchListener {
	TouchScreen tscreen;
	
	ScreenProperties screenProperties;
	DrawMaze drawMaze;
	DrawPoop drawPoop;
	Bitmap spreadSheet, background,poop;

	public int row = 10;
	public int column = 10;
	
	//control sweep
	public float x_start;
	public float y_start;
	public float x_end;
	public float y_end;

	public int poop_maze_x;
	public int poop_maze_y;//N=0 , E=1, S=2, W=3
	public boolean isMove;
	///////////////////////////////////////
	
	public Cell[][] Maze = new Cell[100][100];
	
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
        tscreen = new TouchScreen(this);
        tscreen.setOnTouchListener(this);
        setContentView(tscreen);
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		tscreen.pause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		tscreen.resume();
	}

	public class TouchScreen extends SurfaceView implements Runnable{
		int delayTime = 100;
		Thread control=null;
		SurfaceHolder holder;
		boolean isIsOk = false;
		boolean isLoadProperty=false;
		boolean isLoadDrawPoop=false;
		boolean isLoadProvideInput=false;
		boolean isLoadDrawMaze=false;
		
		public TouchScreen(Context context) {
			super(context);
			MazeGenerator mazeGenerator = new MazeGenerator(row, column);
			Maze = mazeGenerator.getMaze();
			poop_maze_x=0;
			poop_maze_y=0;
			//control thread
			holder = getHolder();
			
			// TODO Auto-generated constructor stub
		}

		@Override
		public void run() {
			Canvas c = null;
			// TODO Auto-generated method stub
			
			while(isIsOk==true){
				try {
					Thread.sleep(delayTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(!holder.getSurface().isValid()){
					continue;
				}
				if(!isLoadProperty){
					screenProperties = new ScreenProperties(tscreen,spreadSheet,background,poop,
							row,column);
					isLoadProperty=true;
				}
				if(!isLoadDrawMaze){
					drawMaze = new DrawMaze(tscreen,
							Maze,screenProperties);
					isLoadDrawMaze=true;
				}
				if(!isLoadDrawPoop){
					drawPoop = new DrawPoop(tscreen,Maze,screenProperties);
					isLoadDrawPoop=true;
				}
					c = holder.lockCanvas();					
					onDraw(c);
					holder.unlockCanvasAndPost(c);				
			}			
		}
		protected void onDraw(Canvas canvas) {			
			drawMaze.onDraw(canvas);
			drawPoop.onDraw(canvas,x_end,x_start,
					y_end,y_start,isMove);
			isMove=false;
		}
		
		public void pause(){
			isIsOk=false;
			while(true){
				try{
					control.join();
				}
				catch (InterruptedException e){
					e.printStackTrace();
				}
				break;
			}
			control=null;
		}
		
		public void resume(){
			isIsOk=true;
			control = new Thread(this);
			control.start();
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
