package com.ttma.caocaorun.draw.screen;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.ttma.caocaorun.ControlView;
import com.ttma.caocaorun.VisualFX.BubbleButton;
import com.ttma.caocaorun.draw.standardclass.StandardViewScreen;

public class HighScoreScreen extends StandardViewScreen {

	private Paint backgroundColor = new Paint();

	public HighScoreScreen(ControlView controler, Resources resources, boolean isBrowser) {
		super(controler, resources,isBrowser);
		backgroundColor.setARGB(50, 255, 255, 255);
	}

	@Override
	public void onDraw(Canvas canvas) {
		canvas.drawRect(0, 0, controler.getWidth(), controler.getHeight(),
				backgroundColor);
		super.onDraw(canvas);
		resumeButton.updateAndDraw(canvas);
	}

	@Override
	protected void loadAllBitmap(Resources resources) {
		// TODO Auto-generated method stub
		background = bitmapColection.getHighScoreScreen(resources);
		resumeBitmap = bitmapColection.getBack(resources);
	}

	@Override
	protected void createButtons() {
		// TODO Auto-generated method stub
		resumeButton = new BubbleButton("resumeButton", resumeBitmap, 0.67f,
				0.906f, 0f);
		resumeButton.staytill();
	}

	@Override
	protected void loadAnimation() {
		// TODO Auto-generated method stub
	}

}