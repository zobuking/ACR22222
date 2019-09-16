package com.IDP.Group1.acr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SplashScreen extends AppCompatActivity {
	private ProgressBar pb;
	public FirebaseDatabase database;
	public DatabaseReference myRef;
	boolean loggedIn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

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

		database = FirebaseDatabase.getInstance();
		myRef = database.getReference("loggedIn");

		myRef.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				// This method is called once with the initial value and again
				// whenever data at this location is updated.
				String value = dataSnapshot.getValue(String.class);

				Toast.makeText(SplashScreen.this, "val = " + value, Toast.LENGTH_SHORT).show();

				if (value.equals("true")) {
					loggedIn = true;
				}
				else {
					loggedIn = false;
				}
				Toast.makeText(SplashScreen.this, "LoggedIN reading ok", Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onCancelled(DatabaseError error) {
				// Failed to read value
				Toast.makeText(SplashScreen.this, "LoggedIN reading error", Toast.LENGTH_SHORT).show();
			}
		});

		Log.d("rifat", "debugging");

	}

	private void startapp() {
		Intent intent;
		if (loggedIn) {
			intent = new Intent(this, user.class);
			startActivity(intent);
		}
		else {
			intent = new Intent(this, MainActivity.class);
			startActivity(intent);
		}
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
