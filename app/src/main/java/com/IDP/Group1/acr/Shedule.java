package com.IDP.Group1.acr;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

public class Shedule extends Fragment {

	private CustomExpandableListViewAdapter adapter;

	public Shedule() {
		// Required empty public constructor
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_shedule, container, false);

		ExpandableListView expandableListView = view.findViewById(R.id.expandableListViewID);

		List<SheduleClass> sheduleList;
		sheduleList = new ArrayList<>();

		int[] a = new int[7];

		a[0] = a[1] = a[2] = 1;
		a[3] = a[4] = a[5] = a[6] = 0;

		sheduleList.add(new SheduleClass(1, 10, 23 ,8, 2019, true));
		sheduleList.add(new SheduleClass(2, 20, 13 ,7, 2018, false));
		sheduleList.add(new SheduleClass(3, 30, a, true));

		adapter = new CustomExpandableListViewAdapter(view.getContext(), sheduleList);
		expandableListView.setAdapter(adapter);

		return view;
	}
}
