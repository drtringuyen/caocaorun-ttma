package com.ttma.caocaorun.draw.screen;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.ttma.caocaorun.ControlView;
import com.ttma.caocaorun.VisualFX.BubbleButton;
import com.ttma.caocaorun.draw.standardclass.StandardViewScreen;
import com.ttma.caocaorun.utilities.Sprite;

public class CreditScreen extends StandardViewScreen {

	private Sprite poopAnimation;

	private Bitmap poop;

	public CreditScreen(ControlView controler, Resources resources,
			boolean isBrowser) {
		super(controler, resources, isBrowser);
	}
	
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		resumeButton.updateAndDraw(canvas);
		poopAnimation.updateAndDraw(canvas);
	}

	@Override
	protected void loadAllBitmap(Resources resources) {
		background = bitmapColection.getCreditScreen(resources);
		poop = bitmapColection.getPoopAnimated(resources);
		resumeBitmap = bitmapColection.getBackWood(resources);
	}

	@Override
	protected void createButtons() {
		resumeButton = new BubbleButton("back", resumeBitmap, 0.323f, 0.103f,
				0.1f);
		resumeButton.staytill();
	}

	@Override
	protected void loadAnimation() {
		// TODO Auto-generated method stub
		poopAnimation = new Sprite("poop", poop, 0.397f, 0.888f, 0.1f,15);
		poopAnimation.staytill();
	}
}