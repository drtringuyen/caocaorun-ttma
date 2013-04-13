package com.ttma.caocaorun;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class SplashScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.manufacturer_screen);
		
		Thread control = new Thread() {
			public void run() {
				try {
					try {
						sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Intent homescreen = new Intent(
							"com.ttma.caocaorun.HOMESCREEN");
					startActivity(homescreen);
				} finally {
					finish();
				}
			}
		};
		control.start();
	}
	
	
}
