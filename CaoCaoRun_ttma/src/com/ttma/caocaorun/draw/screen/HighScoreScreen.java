package com.ttma.caocaorun.draw.screen;

import com.ttma.caocaorun.MainActivity;
import com.ttma.caocaorun.VisualFX.BubbleButton;
import com.ttma.caocaorun.utilities.BitmapCollection;
import com.ttma.caocaorun.utilities.BitmapSynchroniser;
import com.ttma.caocaorun.utilities.SoundFactory;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceView;

public class HighScoreScreen {
	
	private Bitmap highScoreScreen;
	private Bitmap back;
	
	private BitmapCollection bitmapCollection;

	private boolean selected = false;

	private BubbleButton backButton;

	private boolean visualFxSwitch, soundSwitch, musicSwitch;

	private Paint backgroundColor = new Paint();
	
	private SurfaceView screen;

	public HighScoreScreen(SurfaceView screen, Resources resources) {
		
		this.screen=screen;
		
		backgroundColor.setARGB(50, 255, 255, 255);
		bitmapCollection = new BitmapCollection();

		highScoreScreen = bitmapCollection.getHighScoreScreen(resources);

		back = bitmapCollection.getBack(resources);

		backButton = new BubbleButton("resumeButton", back, 0.67f, 0.906f, 0f);
		backButton.staytill();
	}

	protected void onDraw(Canvas canvas) {
		
		Rect[] backgroundFrame = BitmapSynchroniser
				.getBackGroundRects(highScoreScreen);
		
		canvas.drawRect(0, 0, screen.getWidth(),
				screen.getHeight(), backgroundColor);

		canvas.drawBitmap(highScoreScreen, backgroundFrame[0],
				backgroundFrame[1], null);

		backButton.updateAndDraw(canvas);
	}

	public boolean onResume() {
		int touchX = MainActivity.getTouchX();
		int touchY = MainActivity.getTouchY();
		if (backButton.onTouch(touchX, touchY)) {
			deselected();
			MainActivity.resetXY();
			return true;
		} else {
			return false;
		}
	}

	public void getGameOption() {
		visualFxSwitch = true;
		soundSwitch = true;
		musicSwitch = true;
	}

	public void changeGameOption() {

	}

	public void activateButton() {
		int touchX = MainActivity.getTouchX();
		int touchY = MainActivity.getTouchY();
//		boolean isPress = MainActivity.isPress();

		MainActivity.resetXY();
		changeGameOption();
	}


	public void bringToTop(Canvas canvas) {
		selected();
		onDraw(canvas);
		onResume();
		activateButton();
	}

	public void selected() {
		this.selected = true;
	}

	public void deselected() {
		this.selected = false;
	}

	public boolean isSelected() {
		return this.selected;
	}
}