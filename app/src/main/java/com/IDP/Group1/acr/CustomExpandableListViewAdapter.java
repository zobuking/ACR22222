package com.IDP.Group1.acr;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.List;

class CustomExpandableListViewAdapter extends BaseExpandableListAdapter {

	Context context;
	List<SheduleClass> shedules;
	TextView text1, text2;

	public CustomExpandableListViewAdapter(Context context, List<SheduleClass> shedules) {
		this.context = context;
		this.shedules = shedules;
	}

	public CustomExpandableListViewAdapter() {
	}

	@Override
	public int getGroupCount() {
		return shedules.size();
	}

	@Override
	public int getChildrenCount(int i) {
		return 1;
	}

	@Override
	public Object getGroup(int i) {
		return shedules.get(i);
	}

	@Override
	public Object getChild(int i, int i1) {
		return shedules.get(i);
	}

	@Override
	public long getGroupId(int i) {
		return i;
	}

	@Override
	public long getChildId(int i, int i1) {
		return i;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
		final SheduleClass sh = (SheduleClass) getGroup(i);

		if (view == null) {
			LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = layoutInflater.inflate(R.layout.layout_header, null);
		}

		text1 = view.findViewById(R.id.headerText1ViewID);
		text2 = view.findViewById(R.id.headerText2ViewID);

		String time = sh.getHour() + ":" + sh.getMinute();
		text1.setText(time);

		if (sh.isAM)
			text2.setText("am");
		else
			text2.setText("pm");

		text1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getTime(sh);
				Toast.makeText(context, "prepared", Toast.LENGTH_SHORT).show();
			}
		});

		text2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getTime(sh);
				Toast.makeText(context, "prepared", Toast.LENGTH_SHORT).show();

			}
		});

		return view;
	}

	@Override
	public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
		final SheduleClass sh = (SheduleClass) getGroup(i);

		if (sh.date == -1) {
			DatePicker datePicker = new DatePicker(context);
			sh.date = datePicker.getDayOfMonth();
			sh.month = datePicker.getMonth() + 1;
			sh.year = datePicker.getYear();
		}

		if (view == null) {
			LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = layoutInflater.inflate(R.layout.layout_footer, null);
		}

		final CheckBox repeatCheckBox = view.findViewById(R.id.repeatCheckBoxID);
		final LinearLayout linearLayout = view.findViewById(R.id.RepeatedID);
		final Button button = view.findViewById(R.id.datePickerButton);
		final Button sat = view.findViewById(R.id.buttonSatID);
		final Button sun = view.findViewById(R.id.buttonSunID);
		final Button mon = view.findViewById(R.id.buttonMonID);
		final Button tues = view.findViewById(R.id.buttonTuesID);
		final Button wed = view.findViewById(R.id.buttonWedID);
		final Button thur = view.findViewById(R.id.buttonThursID);
		final Button fri = view.findViewById(R.id.buttonFriID);

		button.setText(sh.getDate() + "/" + sh.getMonth() + "/" + sh.getYear());

		repeatCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
				if (repeatCheckBox.isChecked()) {
					linearLayout.setVisibility(View.VISIBLE);
					button.setVisibility(View.GONE);
				}
				else {
					linearLayout.setVisibility(View.GONE);
					button.setVisibility(View.VISIBLE);

				}
			}
		});

		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				DatePicker datePicker = new DatePicker(context);
				int curDay = datePicker.getDayOfMonth();
				int curMonth = datePicker.getMonth();
				int curYear = datePicker.getYear();

				DatePickerDialog dialog = new DatePickerDialog(
						context,
						new DatePickerDialog.OnDateSetListener() {
							@Override
							public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
								button.setText(i2 + "/" + (i1+1) + "/" + i);
							}
						},
						curYear,
						curMonth,
						curDay
				);

				dialog.show();
			}
		});

		return view;
	}

	private void getTime(final SheduleClass sh) {

		Toast.makeText(context, "getTime", Toast.LENGTH_SHORT).show();

		TimePicker timePicker = new TimePicker(context);
		final int curHour = timePicker.getCurrentHour();
		final int curMinute = timePicker.getCurrentMinute();

		TimePickerDialog dialog = new TimePickerDialog(
				context,
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

						text1.setText(i + ":" + i1);
						if (isAM)
							text2.setText("am");
						else
							text2.setText("pm");
					}
				},
				curHour,
				curMinute,
				false
		);
		dialog.show();
	}

	@Override
	public boolean isChildSelectable(int i, int i1) {
		return false;
	}
}
