package com.IDP.Group1.acr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class User extends actionBar {

	private Toolbar UserToolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user);

		UserToolbar = (Toolbar) findViewById(R.id.userToolbarID);
		setSupportActionBar(UserToolbar);

	}
}
