package com.IDP.Group1.acr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;


public class Dashboard extends Fragment {

	private GridView grid;
	private User user;

	int[] performance ={R.drawable.stat1,R.drawable.stat2,R.drawable.stat3,R.drawable.stat4};
	String[] val={"Battery","Dust","Clean rate","Power on time"};
	private ArrayAdapter<String> adapter;
	Activity act;
	boolean isRecording;
	ImageView alert,sleep,clean,notification,mic,settings, battery;
	TextView batteryText;
	Switch aSwitch;

	public Dashboard() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {

		act = getActivity();

		View V = inflater.inflate(R.layout.fragment_dashboard, container, false);

		user = new User();

		aSwitch = V.findViewById(R.id.switchID);
		alert =V.findViewById(R.id.alertID);
		sleep =V.findViewById(R.id.sleepID);
		clean =V.findViewById(R.id.cleanID);
		notification =V.findViewById(R.id.notificationID);
		mic =V.findViewById(R.id.micID);
		GridView G=V.findViewById(R.id.grid);
		battery = V.findViewById(R.id.batteryID);
		batteryText = V.findViewById(R.id.batteryTextID);


		isRecording = false;

		if (user.isPowerOn) {
			aSwitch.setChecked(true);
		}
		else {
			aSwitch.setChecked(false);
		}

		aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

			}
		});

		checkBatteryData();


		battery.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				user.battery +=20;

				if (user.battery > 100)
					user.battery = 20;

				checkBatteryData();
			}
		});

		clean.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentManager fragmentManager = getFragmentManager();

				Clean clean = new Clean();
				fragmentManager.beginTransaction()
						.replace(R.id.nav_host_fragment, clean).commit();

				View view = getActivity().findViewById(R.id.nav_host_fragment);
				Snackbar.make(view, "Cleaning Right Now. Please Wait", Snackbar.LENGTH_LONG)
						.setAction("Action", null).show();
			}
		});

		alert.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				user.isRinging = !user.isRinging;

				if (user.isRinging) {
					alert.setImageResource(R.drawable.alert_on);
					Toast.makeText(getContext(), "Ringing", Toast.LENGTH_SHORT).show();
				}
				else {
					alert.setImageResource(R.drawable.alert_off);
				}
			}
		});

		sleep.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				user.isSleep = !user.isSleep;

				if (user.isSleep) {
					sleep.setImageResource(R.drawable.sleep_on);
					Toast.makeText(getContext(), "going to sleep", Toast.LENGTH_SHORT).show();
				}
				else {
					sleep.setImageResource(R.drawable.sleep_off);
					Toast.makeText(getContext(), "waking up", Toast.LENGTH_SHORT).show();
				}
			}
		});

		notification.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), Notification.class);
				startActivity(intent);
			}
		});

		mic.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				isRecording = !isRecording;

				if (isRecording) {
					mic.setImageResource(R.drawable.mic_on);
					Toast.makeText(getContext(), "tell me your command", Toast.LENGTH_SHORT).show();
				}
				else {
					mic.setImageResource(R.drawable.mic_off);
				}
			}
		});

		status_cust_adapter adapter= new status_cust_adapter(act,performance,val);
		G.setAdapter(adapter);

		return V;
	}

	private void checkBatteryData() {
		int b = user.battery;
		batteryText.setText(b + "%");

		if (b >= 0 && b <= 20) {
			battery.setImageResource(R.drawable.battery20);
		}
		else if (b > 20 && b <= 40) {
			battery.setImageResource(R.drawable.battery40);
		}
		else if (b > 40 && b <= 60) {
			battery.setImageResource(R.drawable.battery60);
		}
		else if (b > 60 && b <= 80) {
			battery.setImageResource(R.drawable.battery80);
		}
		else if (b > 80 && b <= 100) {
			battery.setImageResource(R.drawable.battery100);
		}
	}
}
