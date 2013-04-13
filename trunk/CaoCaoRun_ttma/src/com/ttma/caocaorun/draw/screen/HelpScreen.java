package com.ttma.caocaorun.draw.screen;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.ttma.caocaorun.ControlView;
import com.ttma.caocaorun.MainActivity;
import com.ttma.caocaorun.VisualFX.BubbleButton;

public class HelpScreen extends StandardViewScreen {

	private Bitmap nextBitmap, help1, help2, help3, help4, poop;
	private BubbleButton help1Button, help2Button, help3Button, help4Button;
	private BubbleButton nextButton, poopStayTill;
	private int step = 1;

	public HelpScreen(ControlView screen, Resources resources) {

		// create Images
		super(screen, resources);

		background=bitmapColection.getHelpScreen(resources);
		poop = bitmapColection.getPoopStaytill(resources);

		resumeBitmap = bitmapColection.getBackWood(resources);
		nextBitmap = bitmapColection.getNextWood(resources);

		help1 = bitmapColection.getHelp1(resources);
		help2 = bitmapColection.getHelp2(resources);
		help3 = bitmapColection.getHelp3(resources);
		help4 = bitmapColection.getHelp4(resources);

		// create Buttons
		help1Button = new BubbleButton("don't sweep", help1, 0.317f, 0.268f,
				0.1f);
		help2Button = new BubbleButton("stop at wall", help2, 0.758f, 0.471f,
				0.1f);
		help3Button = new BubbleButton("auto run", help3, 0.331f, 0.643f, 0.1f);

		help4Button = new BubbleButton("stop at wall", help4, 0.664f, 0.845f,
				0.1f);

		nextButton = new BubbleButton("backButton", nextBitmap, 0.681f, 0.202f, 0.1f);
		nextButton.staytill();

		resumeButton=new BubbleButton("resume", resumeBitmap, 0.1f, 0.3f, 0.1f);
		
		poopStayTill = new BubbleButton("poop", poop, 0.3f, 0.888f, 0.1f);
		poopStayTill.staytill();
	}

	public void onDraw(Canvas canvas) {

		super.onDraw(canvas);

		if (step >= 1)
			help1Button.updateAndDraw(canvas);
		if (step >= 2)
			help2Button.updateAndDraw(canvas);
		if (step >= 3)
			help3Button.updateAndDraw(canvas);
		if (step >= 4)
			help4Button.updateAndDraw(canvas);

		// backButton.updateAndDraw(canvas);
		if (step == 4)
			nextButton.changeBimap(resumeBitmap);
		if (step == 1)
			nextButton.changeBimap(nextBitmap);
		nextButton.updateAndDraw(canvas);

		poopStayTill.updateAndDraw(canvas);
	}

	public boolean onResume() {
		if ((step <= 0) || (step > 4)) {
			deselected();
			step = 1;
			MainActivity.resetXY();
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
		MainActivity.resetXY();
	}

}
