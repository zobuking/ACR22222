package com.IDP.Group1.acr;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends actionBar {

	private Toolbar toolbar;
	private FirebaseAuth mAuth;
	EditText EmaileditText, passwordEditText;
	Button logInButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		toolbar = (Toolbar) findViewById(R.id.toolbarID);
		setSupportActionBar(toolbar);

		mAuth = FirebaseAuth.getInstance();
		EmaileditText = (EditText) findViewById(R.id.emailID);
		passwordEditText = (EditText) findViewById(R.id.passwordID);
		logInButton = (Button) findViewById(R.id.logInID);

		logInButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				userLogin();
			}
		});


	}

	private void userLogin() {
		String email = EmaileditText.getText().toString();
		String password = passwordEditText.getText().toString();

		if (email.isEmpty()) {
			EmaileditText.setError("Enter an Email Address");
			EmaileditText.requestFocus();
			return;
		}

		if ( !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
			EmaileditText.setError("Enter an valid Email Address");
			EmaileditText.requestFocus();
			return;
		}

		if (password.isEmpty()) {
			passwordEditText.setError("Enter an Password");
			passwordEditText.requestFocus();
			return;
		}


		mAuth.signInWithEmailAndPassword(email, password)
				.addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
					@Override
					public void onComplete(@NonNull Task<AuthResult> task) {
						if (task.isSuccessful()) {
							finish();
							Intent intent = new Intent(getApplicationContext(), User.class);
							startActivity(intent);
							Toast.makeText(MainActivity.this, "ok", Toast.LENGTH_SHORT).show();
						} else {
							Toast.makeText(MainActivity.this, "failed", Toast.LENGTH_SHORT).show();
						}

					}
				});

	}


}
