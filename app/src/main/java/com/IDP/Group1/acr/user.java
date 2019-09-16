package com.IDP.Group1.acr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class user extends actionBar {

	FirebaseDatabase database;
	DatabaseReference myRef;
	Switch aSwitch;
	Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user);

		toolbar = (Toolbar) findViewById(R.id.toolbarID);
		setSupportActionBar(toolbar);

		aSwitch = (Switch) findViewById(R.id.switchID);

		// Write a message to the database
		database = FirebaseDatabase.getInstance();
		myRef = database.getReference("power");

		myRef.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				// This method is called once with the initial value and again
				// whenever data at this location is updated.
				String value = dataSnapshot.getValue(String.class);

				if (value.equals("on")) {
					aSwitch.setChecked(true);
				}
				else {
					aSwitch.setChecked(false);
				}
				Toast.makeText(user.this, "successful reading", Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onCancelled(DatabaseError error) {
				// Failed to read value
				Toast.makeText(user.this, "error reading", Toast.LENGTH_SHORT).show();
			}
		});


		aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
				if (b) {
					myRef.setValue("on");
				}
				else {
					myRef.setValue("false");
				}
			}
		});
	}
}
