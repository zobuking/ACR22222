package com.IDP.Group1.acr;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;

public class User extends actionBar {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user);

		Log.d("rifat", "User.java begin");

		Toolbar toolbar = (Toolbar) findViewById(R.id.UserToolbarID);
		setSupportActionBar(toolbar);

		Log.d("rifat", "User.java ENd");
//

		DrawerLayout drawer = findViewById(R.id.drawer_layout);
		NavigationView navigationView = findViewById(R.id.nav_view);
		// Passing each menu ID as a set of Ids because each
		// menu should be considered as top level destinations.

		Log.d("rifat", "actionBar1");
		mAppBarConfiguration = new AppBarConfiguration.Builder(
				R.id.nav_dashboard, R.id.nav_shedule, R.id.nav_scanRoom,
				R.id.nav_clean, R.id.nav_setting)
				.setDrawerLayout(drawer)
				.build();

		Log.d("rifat", "actionBar2");

		NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
		NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
		NavigationUI.setupWithNavController(navigationView, navController);

		Log.d("rifat", "actionBar_All");

	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.activity_menu, menu);
//		return true;
//	}


}
