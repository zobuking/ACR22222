package com.IDP.Group1.acr;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;


public class Dashboard extends Fragment {

	private GridView grid;

	int[] performance ={R.drawable.stat1,R.drawable.stat2,R.drawable.stat3,R.drawable.stat4};
	String[] val={"data1","data2","data3","data4"};
	private ArrayAdapter<String> adapter;
	Activity act;

	ImageView alert,sleep,alarm,noti,mute,settings;

	public Dashboard() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {

		act = getActivity();

		View V = inflater.inflate(R.layout.fragment_dashboard, container, false);

		alert =V.findViewById(R.id.alert);
		sleep =V.findViewById(R.id.sleep);
		alarm =V.findViewById(R.id.alarm);
		noti =V.findViewById(R.id.noti);
		mute =V.findViewById(R.id.mute);
		GridView G=V.findViewById(R.id.grid);

		status_cust_adapter adapter= new status_cust_adapter(act,performance,val);
		G.setAdapter(adapter);

		return V;
	}
}
