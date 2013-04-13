package com.ttma.caocaorun.utilities;

import android.media.MediaPlayer;

public class SoundFactory {
	
	private static MediaPlayer music;
	private static MediaPlayer poop;
	private static MediaPlayer bubble;
	
	private static boolean musicOn=false;
	
	private static boolean soundFXOn=true;
	
	public static void setInititate(MediaPlayer music,MediaPlayer poop,MediaPlayer bubble){
		SoundFactory.music=music;
		SoundFactory.poop=poop;
		SoundFactory.bubble=bubble;
		music.setLooping(true);
	}
	
	public static void playMusic(){
		music.start();
	}
	public static void stopMusic(){
		music.pause();
	}
	public static void playBubbleSound(){
		if(soundFXOn)
		bubble.start();
	}
	public static void playPoopSound(){
		if(soundFXOn)
		poop.start();
	}
	public static void disableSoundFX(){
		soundFXOn=false;
	}
	public static void enableSoundFX(){
		soundFXOn=true;
	}
}
