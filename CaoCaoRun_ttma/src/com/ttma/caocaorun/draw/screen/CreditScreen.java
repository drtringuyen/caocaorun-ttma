package com.ttma.caocaorun.draw.screen;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.ttma.caocaorun.ControlView;
import com.ttma.caocaorun.VisualFX.BubbleButton;

public class CreditScreen extends StandardViewScreen {

	private BubbleButton poopStayTill;

	private Bitmap poop;

	public CreditScreen(ControlView screen, Resources resources) {

		super(screen, resources);

		background = bitmapColection.getCreditScreen(resources);

		poop = bitmapColection.getPoopStaytill(resources);

		resumeBitmap = bitmapColection.getBackWood(resources);

		resumeButton = new BubbleButton("back", resumeBitmap, 0.323f, 0.103f,
				0.1f);

		resumeButton.staytill();

		poopStayTill = new BubbleButton("poop", poop, 0.397f, 0.888f, 0.1f);
		poopStayTill.staytill();
	}

	public void onDraw(Canvas canvas){
		super.onDraw(canvas);
		resumeButton.updateAndDraw(canvas);
		poopStayTill.updateAndDraw(canvas);
	}
}