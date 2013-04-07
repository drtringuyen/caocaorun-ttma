package test.touch;

import MazeFactory.Cell;
import MazeFactory.MazeGenerator;
import android.app.Activity;
import android.content.Context;
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
	TouchScreen tscreen;
	
	ScreenProperties screenProperties;
	DrawMaze drawMaze;
	DrawPoop drawPoop;
	Bitmap spreadSheet, background,poop;

	
	public int FPS = 30;
	
	//control sweep
	public float x_start;
	public float y_start;
	public float x_end;
	public float y_end;
	public float delx;
	public float dely;

	public int poop_maze_x;
	public int poop_maze_y;//N=0 , E=1, S=2, W=3
	public boolean updatePoop = false;
	///////////////////////////////////////
	
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
		long ticksPs = 1000 / FPS;
		long startTime;
		long sleepTime;
		
		long time_update_poop=0;
		long temp=0;
		
		int row = 5;
		int column = 5;
		Cell[][] Maze;
		
		Thread control=null;
		SurfaceHolder holder;
		boolean isIsOk = false;
		boolean isLoadProperty=false;
		boolean isLoadDrawPoop=false;
		boolean isLoadProvideInput=false;
		boolean isLoadDrawMaze=false;
		boolean isGenerateMaze=false;
		
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
				if(!isLoadProperty){
					screenProperties = new ScreenProperties(tscreen,spreadSheet,background,poop,
							row,column);
					isLoadProperty=true;
				}
				if(!isGenerateMaze){
					MazeGenerator mazeGenerator = new MazeGenerator(row, column);
					
					Maze = mazeGenerator.getMaze();
					poop_maze_x=0;
					poop_maze_y=0;
					isGenerateMaze=true;
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
				if(drawPoop.checkPoopAtEnd()){
					row=row+1;
					column=column+1;
					isGenerateMaze=false;
					isLoadDrawMaze=false;
					isLoadDrawPoop=false;
					isLoadProperty=false;
				}
					c = holder.lockCanvas();
					onDraw(c,updatePoop);
					holder.unlockCanvasAndPost(c);				
			}			
		}
		
		protected void onDraw(Canvas canvas,boolean updatePoop) {			
			drawMaze.onDraw(canvas);
			drawPoop.onDraw(canvas,x_end,x_start,
					y_end,y_start,updatePoop);
			
			
			Paint textPaint = new Paint();
			textPaint.setColor(Color.CYAN);
			canvas.drawText(Boolean.toString(updatePoop), 100, 100, textPaint);
			
			canvas.drawText(Float.toString(x_start), 100, 150, textPaint);
			canvas.drawText(Float.toString(y_start), 150, 150, textPaint);
			canvas.drawText(Float.toString(x_end), 100, 200, textPaint);
			canvas.drawText(Float.toString(y_end), 150, 200, textPaint);
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
		case MotionEvent.ACTION_MOVE:
			x_end=event.getX();
			y_end=event.getY();
			delx= x_end-x_start;
			dely= y_end-y_start;
			if(Math.abs(delx)>10 || Math.abs(dely)>10){
//			if(Math.abs(delx)>screenProperties.getSCALED_SIZE() || Math.abs(dely)>screenProperties.getSCALED_SIZE()){
				updatePoop=true;
				x_start=x_end;
				y_start=y_end;
			}else{updatePoop=false;}
			break;
		case MotionEvent.ACTION_UP:
			x_start=0;
			x_end=0;
			y_start=0;
			y_end=0;
			break;
		}
		
//		switch(event.getAction()){
//		case MotionEvent.ACTION_DOWN:
//			x_start=event.getX();
//			y_start=event.getY();
//			break;
//		case MotionEvent.ACTION_UP:
//			x_end=event.getX();
//			y_end=event.getY();
//			isMove=true;
//			break;
//		}
		return true;
	}
	

}
