package com.ttma.caocaorun.utilities;

import java.io.InputStream;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.ttma.caocaorun.R;

public class BitmapCollection{
//	private Bitmap background, play, options, highscores, credits, bubble;
	public Bitmap getBackground(Resources resources) {
		return BitmapFactory.decodeResource(resources, R.drawable.background);
	}
	public Bitmap getPlay(Resources resources) {
		return BitmapFactory.decodeResource(resources, R.drawable.play);
	}
	public Bitmap getOptions(Resources resources) {
		return BitmapFactory.decodeResource(resources, R.drawable.options);
	}
	public Bitmap getHighscores(Resources resources) {
		return BitmapFactory.decodeResource(resources, R.drawable.highscore);
	}
	public Bitmap getCredits(Resources resources) {
		return BitmapFactory.decodeResource(resources, R.drawable.credits);
	}
	public Bitmap getBubble(Resources resources) {
		return BitmapFactory.decodeResource(resources, R.drawable.buble);
	}
	public Bitmap getFontSheet(Resources resources) {
		return BitmapFactory.decodeResource(resources, R.drawable.babycakefont);
	}
	public Bitmap getOptionBackground(Resources resources) {
		return BitmapFactory.decodeResource(resources, R.drawable.optionssreen);
	}
	public Bitmap getVisualFxOn(Resources resources) {
		return BitmapFactory.decodeResource(resources, R.drawable.fxon);
	}
	public Bitmap getVisualFxOff(Resources resources) {
		return BitmapFactory.decodeResource(resources, R.drawable.fxoff);
	}
	public Bitmap getSoundOn(Resources resources) {
		return BitmapFactory.decodeResource(resources, R.drawable.soundon);
	}
	public Bitmap getSoundOff(Resources resources) {
		return BitmapFactory.decodeResource(resources, R.drawable.soundoff);
	}
	public Bitmap getMusicOn(Resources resources) {
		return BitmapFactory.decodeResource(resources, R.drawable.musicon);
	}
	public Bitmap getMusicOff(Resources resources) {
		return BitmapFactory.decodeResource(resources, R.drawable.musicoff);
	}
	public Bitmap getBack(Resources resources) {
		return BitmapFactory.decodeResource(resources, R.drawable.back);
	}
	public Bitmap getHorror(Resources resources) {
		return BitmapFactory.decodeResource(resources, R.drawable.horrorbutton);
	}
	public Bitmap getEndless(Resources resources) {
		return BitmapFactory.decodeResource(resources, R.drawable.enlessrunbutton);
	}
	public Bitmap getQuiz(Resources resources) {
		return BitmapFactory.decodeResource(resources, R.drawable.quizbutton);
	}
	public Bitmap getCustom(Resources resources) {
		return BitmapFactory.decodeResource(resources, R.drawable.custombutton);
	}
	public Bitmap getModeScreen(Resources resources) {
		return BitmapFactory.decodeResource(resources, R.drawable.modesscreen);
	}

}
