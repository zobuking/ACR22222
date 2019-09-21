package com.IDP.Group1.acr;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.gms.common.internal.Objects;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;

public class actionBar extends AppCompatActivity {

    AppBarConfiguration mAppBarConfiguration;
    private FirebaseAuth mAuth;

    actionBar(){
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.activity_menu, menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuShareID){
            ApplicationInfo api = getApplicationContext().getApplicationInfo();
            String apkPath = api.sourceDir;

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("application/vnd.android.package-archive");
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(apkPath)));
            startActivity(Intent.createChooser(intent, "Share app using"));
        }
        else if (item.getItemId() == R.id.feedbackID){
            Intent intent = new Intent(this, feedback.class);
            startActivity(intent);
        }
        else if (item.getItemId() == R.id.aboutID){
            Intent intent = new Intent(this, about.class);
            startActivity(intent);
        }
        else if(item.getItemId() == R.id.exitID) {
            finish();
        }
        else if (item.getItemId() == R.id.logOutID) {
            FirebaseAuth.getInstance().signOut();
            finish();

            Toast.makeText(actionBar.this, "ok sign out", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    private void logOut() {
        // Write a message to the database

    }

}
