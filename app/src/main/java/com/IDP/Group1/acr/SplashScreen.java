package com.IDP.Group1.acr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class SplashScreen extends AppCompatActivity {
	private ProgressBar pb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Log.d("rifat", "debugging");

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_splash_screen);

		pb = (ProgressBar) findViewById(R.id.progressBarID);

		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				doWork();
				startapp();
			}
		});
		thread.start();

	}

	private void startapp() {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		finish();
	}

	private void doWork() {
		for (int p = 20; p <= 100; p += 20){
			try {
				Thread.sleep(500);
				pb.setProgress(p);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
