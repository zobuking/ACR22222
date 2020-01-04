package com.IDP.Group1.acr;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Shedule extends Fragment {

	private CustomExpandableListViewAdapter adapter;
	View lastClicked;

	public Shedule() {
		// Required empty public constructor
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, final ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		final View view = inflater.inflate(R.layout.fragment_shedule, container, false);

		final ExpandableListView expandableListView = view.findViewById(R.id.expandableListViewID);

//		expandableListView.setGroupIndicator(null);

		final List<SheduleClass> sheduleList;
		sheduleList = new ArrayList<>();

		int[] a = new int[7];

		a[0] = a[1] = a[2] = 1;
		a[3] = a[4] = a[5] = a[6] = 0;

		sheduleList.add(new SheduleClass(1, 10, 23 ,8, 2019, true));
		sheduleList.add(new SheduleClass(2, 20, 13 ,7, 2018, false));
		sheduleList.add(new SheduleClass(3, 30, a, true));

////		final boolean []isExpanded = new boolean[sheduleList.size()];
////
////		for (int i = 0; i < sheduleList.size(); i++) {
////			isExpanded[i] = false;
////		}
//
//		expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
//			@Override
//			public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
////				isExpanded[groupPosition] = !isExpanded[groupPosition];
////
////				View view1 = expandableListView.getChildAt(groupPosition);
////				if (parent.isGroupExpanded(groupPosition)){
////					view1.setBackgroundColor(Color.parseColor("#202124"));
////				}
////				else {
////					view1.setBackgroundColor(Color.parseColor("#2A2A30"));
////				}
////				for (int i = 0; i < sheduleList.size(); i++) {
////					LinearLayout linearLayout = expandableListView.getChildAt(i).findViewById(R.id.headerID);
////
////					int defaultColor = Color.parseColor("#202124");
////					int clickedColor = Color.parseColor("#2A2A30");
////
////					if (isExpanded[groupPosition]) {
//////						isExpanded[groupPosition] = false;
////						v.setBackgroundColor(clickedColor);
////					}
////					else {
//////						isExpanded[groupPosition] = true;
////						v.setBackgroundColor(defaultColor);
////					}
////				}
//				return false;
//			}
//		});
//
//		expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
//			@Override
//			public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
//
//
//				return false;
//			}
//		});

		adapter = new CustomExpandableListViewAdapter(view.getContext(), sheduleList);
		expandableListView.setAdapter(adapter);

		FloatingActionButton fab = view.findViewById(R.id.fab);

		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View view) {
				DatePicker datePicker = new DatePicker(getContext());
				final int curDay = datePicker.getDayOfMonth();
				final int curMonth = datePicker.getMonth() + 1;
				final int curYear = datePicker.getYear();

				TimePicker timePicker = new TimePicker(getContext());
				final int curHour = timePicker.getCurrentHour();
				final int curMinute = timePicker.getCurrentMinute();

				TimePickerDialog dialog = new TimePickerDialog(
						getContext(),
						new TimePickerDialog.OnTimeSetListener() {
							@Override
							public void onTimeSet(TimePicker timePicker, int i, int i1) {
								boolean isAM = true;
								if (i > 12) {
									isAM = false;
									i -= 12;
								}
								else if (i == 0) {
									i = 12;
								}

								sheduleList.add(new SheduleClass(i, i1, curDay ,curMonth, curYear, isAM));
								adapter = new CustomExpandableListViewAdapter(view.getContext(), sheduleList);
								expandableListView.setAdapter(adapter);
							}
						},
						curHour,
						curMinute,
						false
				);
				dialog.show();
			}
		});
		return view;
	}
}
