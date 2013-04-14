package com.ttma.caocaorun.draw.screen;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.ttma.caocaorun.ControlView;
import com.ttma.caocaorun.VisualFX.BubbleButton;

public class HighScoreScreen extends StandardViewScreen {

	private Paint backgroundColor = new Paint();

	public HighScoreScreen(ControlView controler, Resources resources, boolean isBrowser) {
		super(controler, resources,isBrowser);
		
		backgroundColor.setARGB(50, 255, 255, 255);
		
		background = bitmapColection.getHighScoreScreen(resources);

		resumeBitmap = bitmapColection.getBack(resources);

		resumeButton = new BubbleButton("resumeButton", resumeBitmap, 0.67f,
				0.906f, 0f);
		resumeButton.staytill();
	}

	@Override
	public void onDraw(Canvas canvas) {
		canvas.drawRect(0, 0, controler.getWidth(), controler.getHeight(),
				backgroundColor);
		super.onDraw(canvas);
		resumeButton.updateAndDraw(canvas);
	}

}