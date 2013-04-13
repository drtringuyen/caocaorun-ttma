package com.ttma.caocaorun.draw.screen;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceView;

import com.ttma.caocaorun.ControlView;
import com.ttma.caocaorun.MainActivity;
import com.ttma.caocaorun.VisualFX.BubbleButton;
import com.ttma.caocaorun.utilities.BitmapCollection;
import com.ttma.caocaorun.utilities.BitmapSynchroniser;

public class HelpScreen {

	private Bitmap back, next, help1, help2, help3, help4;
	private Bitmap background;
	private BitmapCollection bitmapCollection;
	private BubbleButton help1Button, help2Button, help3Button, help4Button,
			backButton, nextButton;
	private int step = 1;

	private boolean selected = false;

	ControlView screen;

	public HelpScreen(SurfaceView screen, Resources resources) {

		// create Images
		bitmapCollection = new BitmapCollection();

		background = bitmapCollection.getHelpScreen(resources);

		back = bitmapCollection.getBackWood(resources);
		next = bitmapCollection.getNextWood(resources);

		help1 = bitmapCollection.getHelp1(resources);
		help2 = bitmapCollection.getHelp2(resources);
		help3 = bitmapCollection.getHelp3(resources);
		help4 = bitmapCollection.getHelp4(resources);

		this.screen = (ControlView) screen;
		// create Buttons
		help1Button = new BubbleButton("don't sweep", help1, 0.317f, 0.268f,
				0.1f);
		help2Button = new BubbleButton("stop at wall", help2, 0.758f, 0.471f,
				0.1f);
		help3Button = new BubbleButton("auto run", help3, 0.331f, 0.643f, 0.1f);
		
		help4Button = new BubbleButton("stop at wall", help4, 0.664f, 0.845f,
				0.1f);

		nextButton = new BubbleButton("backButton", next, 0.681f, 0.202f, 0.1f);
		backButton = new BubbleButton("backButton", back, 0.318f, 0.103f, 0.1f);

		backButton.staytill();
		nextButton.staytill();
	}

	protected void onDraw(Canvas canvas) {

		Rect[] backgroundFrame = BitmapSynchroniser
				.getBackGroundRects(background);

		canvas.drawBitmap(background, backgroundFrame[0], backgroundFrame[1],
				null);

		if (step >= 1)
			help1Button.updateAndDraw(canvas);
		if (step >= 2)
			help2Button.updateAndDraw(canvas);
		if (step >= 3)
			help3Button.updateAndDraw(canvas);
		if (step >= 4)
			help4Button.updateAndDraw(canvas);

		backButton.updateAndDraw(canvas);
		nextButton.updateAndDraw(canvas);
	}

	public boolean onResume() {
		if ((step <= 0)||(step>4)) {
			deselected();
			step=1;
			return true;
		} else {
			return false;
		}
	}

	public void activateButton() {
		int touchX = MainActivity.getTouchX();
		int touchY = MainActivity.getTouchY();

		if (nextButton.onTouch(touchX, touchY))
			step++;
		if (backButton.onTouch(touchX, touchY))
			step--;
		MainActivity.resetXY();
	}

	public void bringToTop(Canvas canvas) {
		selected();
		onDraw(canvas);
		activateButton();
		onResume();
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
