package test.touch;

import MazeFactory.Cell;
import MazeFactory.MazeGenerator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
	
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		Intent mainActivity = new Intent("com.ttma.caocaorun.HOMESCREEN");
		mainActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(mainActivity);
	}


	TouchScreen tscreen;
	ScreenProperties screenProperties;
	DrawMaze drawMaze;
	DrawPoop drawPoop;
	Bitmap spreadSheet, background,poop;
	TimeSystem timeSystem;

	public int row = 5;
	public int column = 5;
	
	//control sweep
	public float x_start;
	public float y_start;
	public float x_end;
	public float y_end;

	public int poop_maze_x;
	public int poop_maze_y;//N=0 , E=1, S=2, W=3
	public boolean isMove;
	
	public int FPS = 10;
	private long ticksPs = 1000 / FPS;
	private long startTime;
	private long sleepTime;
	
	private long standardMin =0;
	private long standardSec =15;
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
		Thread control=null;
		SurfaceHolder holder;
		boolean isIsOk = false;

		public TouchScreen(Context context) {
			super(context);
			//control thread
			holder = getHolder();
			
			// TODO Auto-generated constructor stub
		}

		@Override
		public void run() {
			Canvas c = null;
			// TODO Auto-generated method stub
			
			while(isIsOk==true){
				if(!holder.getSurface().isValid()){
					continue;
				}
				loadAllDrawClass();
				startTime = System.currentTimeMillis();
					c = holder.lockCanvas();					
					onDraw(c);
					holder.unlockCanvasAndPost(c);
					if(drawPoop.checkPoopAtEnd()){
						screenProperties=null;
						drawMaze=null;
						drawPoop=null;
						row=row+1;
						column=column+1;
					}
					
					updateTime();
			}			
		}
		protected void onDraw(Canvas canvas) {			
			drawMaze.onDraw(canvas);
			drawPoop.onDraw(canvas,x_end,x_start,
					y_end,y_start,isMove);
			Paint ptPaint = new Paint();
			ptPaint.setColor(Color.BLACK);
			canvas.drawText(Long.toString(timeSystem.showMin()), 200, 200, ptPaint);
			canvas.drawText(Long.toString(timeSystem.showSec()), 200, 250, ptPaint);
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
		
		private void updateTime() {
			sleepTime = ticksPs - (System.currentTimeMillis() - startTime);			
			try {
				if (sleepTime > 0)
					Thread.sleep(sleepTime);
				else
					Thread.sleep(0);
			} catch (Exception e) {
				// TODO: handle exception
			}
			timeSystem.decreasingTime(ticksPs);
		}
		
		private void loadAllDrawClass(){
			if(screenProperties==null){
				screenProperties = new ScreenProperties(tscreen,spreadSheet,background,poop,
						row,column);
				MazeGenerator mazeGenerator = new MazeGenerator(row, column);
				Maze = mazeGenerator.getMaze();
				poop_maze_x=0;
				poop_maze_y=0;
			}
			if(timeSystem==null){
				timeSystem = new TimeSystem();
			}
			if(drawMaze==null){
				drawMaze = new DrawMaze(tscreen,
						Maze,screenProperties);
				timeSystem.setTime(timeSystem.showMin()+standardMin, timeSystem.showSec()+standardSec);
			}
			if(drawPoop==null){
				drawPoop = new DrawPoop(tscreen,Maze,screenProperties);
			}
			
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
