package com.ttma.caocaorun.utilities;

import android.content.Context;
import android.media.MediaPlayer;

public class SoundFactory {
	
	private static MediaPlayer music;
	private static MediaPlayer poop;
	private static MediaPlayer bubble;
	
	private static boolean isPoopOn=true;
	private static boolean isBubbleOn=true;
	
	public static void setInititate(MediaPlayer music,MediaPlayer poop,MediaPlayer bubble){
		SoundFactory.music=music;
		SoundFactory.bubble=bubble;
		SoundFactory.bubble=bubble;
		music.setLooping(true);
	}
	
	public static void playMusic(){
		music.start();
	}
	public static void stopMusic(){
		music.pause();
	}
	public static void playPoopSound(){
		if(isPoopOn)
		poop.start();
	}
	public static void disablePoopSound(){
		isPoopOn=false;
	}
	public static void enablePoopSound(){
		isPoopOn=true;
	}
	public static void playBubbleSound(){
		if(isBubbleOn)
		bubble.start();
	}
	public static void disableBubbleSound(){
		isBubbleOn=false;
	}
	public static void enableBubbleSound(){
		isBubbleOn=true;
	}
}
