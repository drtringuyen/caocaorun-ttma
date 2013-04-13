package com.ttma.caocaorun.utilities;

import com.ttma.caocaorun.OptionSettings;

import android.media.MediaPlayer;

public class SoundFactory {
	
	private static MediaPlayer music;
	private static MediaPlayer poop;
	private static MediaPlayer bubble;
	
	public static void setInititate(MediaPlayer music,MediaPlayer poop,MediaPlayer bubble){
		SoundFactory.music=music;
		SoundFactory.poop=poop;
		SoundFactory.bubble=bubble;
		music.setLooping(true);
	}
	
	public static void playMusic(){
		if(OptionSettings.isMusicOn)
		music.start();
	}
	public static void stopMusic(){
		music.pause();
	}
	public static void playBubbleSound(){
		if(OptionSettings.isSoundOn)
		bubble.start();
	}
	public static void playPoopSound(){
		if(OptionSettings.isSoundOn)
		poop.start();
	}
	public static void disableSoundFX(){
		OptionSettings.isFXOn=false;
	}
	public static void enableSoundFX(){
		OptionSettings.isFXOn=true;
	}

	public static void updateMusic() {
		if (OptionSettings.isMusicOn) music.start();
		else music.pause();
		
	}

	public static void updateSound() {
		OptionSettings.isSoundOn=!OptionSettings.isSoundOn;
	}
	
	public static void updateFX() {
		OptionSettings.isFXOn=!OptionSettings.isFXOn;
	}
}
