package com.IDP.Group1.acr;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class CustomExpandableListViewAdapter extends BaseExpandableListAdapter {

	Context context;
	List<SheduleClass> shedules;
	TextView text1, text2, summary, sum;
	ImageView deleteButton;
	CustomExpandableListViewAdapter adapter = this;
	int curDay, curMonth, curYear;

	public CustomExpandableListViewAdapter(Context context, List<SheduleClass> shedules) {
		this.context = context;
		this.shedules = shedules;

		DatePicker datePicker = new DatePicker(context);
		curDay = datePicker.getDayOfMonth();
		curMonth = datePicker.getMonth();
//		Toast.makeText(context, "curMonth = " + curMonth, Toast.LENGTH_SHORT).show();
		curYear = datePicker.getYear();
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
	public View getGroupView(final int i, boolean b, View view, final ViewGroup viewGroup) {
		final SheduleClass sh = (SheduleClass) getGroup(i);
		int[] day = sh.getDay();

		if (view == null) {
			LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = layoutInflater.inflate(R.layout.layout_header, null);
		}

		summary = view.findViewById(R.id.summaryTextID);
//		final LinearLayout linearLayout = view.findViewById(R.id.headerID);
//		final int defaultColor = Color.parseColor("#202124");
//		final int clickedColor = Color.parseColor("#2A2A30");

		if (sh.isType()) {
			summary.setText(getSummary(day));
		}
		else {
			summary.setText(getDate2(sh.getDate(), sh.getMonth(), sh.getYear()));
		}

		deleteButton = view.findViewById(R.id.deleteButtonID);

		deleteButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				shedules.remove(i);
				adapter.notifyDataSetChanged();
			}
		});

//		linearLayout.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				ExpandableListView expandableListView = (ExpandableListView) viewGroup;
//
//				if (expandableListView.isGroupExpanded(i)) {
//					linearLayout.setBackgroundColor(defaultColor);
//					expandableListView.collapseGroup(i);
//				}
//				else {
//					expandableListView.expandGroup(i);
//					linearLayout.setBackgroundColor(clickedColor);
//				}
//			}
//
//		});
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



		return view;
	}

	@Override
	public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
		final SheduleClass sh = (SheduleClass) getGroup(i);
		final int []day = sh.getDay();

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

		setDay(sat, 0, i, day);
		setDay(sun, 1, i, day);
		setDay(mon, 2, i, day);
		setDay(tues, 3, i, day);
		setDay(wed, 4, i, day);
		setDay(thur, 5, i, day);
		setDay(fri, 6, i, day);

		if (sh.isType()) {
			repeatCheckBox.setChecked(true);
			linearLayout.setVisibility(View.VISIBLE);
			button.setVisibility(View.GONE);
		}

		if (repeatCheckBox.isChecked()) {
			button.setText(getDate1(curDay, curMonth, curYear));
		}
		else {
			button.setText(getDate1(sh.getDate(), sh.getMonth(), sh.getYear()));
		}

		View parentView = viewGroup.getChildAt(i);
		sum = parentView.findViewById(R.id.summaryTextID);

		repeatCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
				if (repeatCheckBox.isChecked()) {
					linearLayout.setVisibility(View.VISIBLE);
					button.setVisibility(View.GONE);
					sum.setText(getSummary(day));
					Toast.makeText(context, getSummary(day), Toast.LENGTH_SHORT).show();
				}
				else {
					linearLayout.setVisibility(View.GONE);
					button.setVisibility(View.VISIBLE);

					if (sh.getDate() == -1 || sh.getMonth() == -1 || sh.getYear() == -1) {
						sum.setText(getDate2(curDay, curMonth, curYear));
					}
					else {
						sum.setText(getDate2(sh.getDate(), sh.getMonth(), sh.getYear()));
					}
				}
			}
		});



		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				DatePickerDialog dialog = new DatePickerDialog(
						context,
						new DatePickerDialog.OnDateSetListener() {
							@Override
							public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
								button.setText(getDate1(i2, i1+1, i));
								sum.setText(getDate2(i2, i1, i));
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

	private void setDay(final Button btn, final int i, final int id, final int[] day) {
		final SheduleClass sh = (SheduleClass) shedules.get(id);

		if (day[i] == 1) {
			btn.setTextColor(Color.parseColor("#3A5351"));
			btn.setBackgroundResource(R.drawable.round_button_selected);
		}
		else {
			btn.setTextColor(Color.parseColor("#97F2F3F3"));
			btn.setBackgroundResource(R.drawable.round_button);
		}

		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (day[i] == 0) {
//					Toast.makeText(context, "0", Toast.LENGTH_SHORT).show();
					day[i] = 1;
					btn.setTextColor(Color.parseColor("#3A5351"));
					btn.setBackgroundResource(R.drawable.round_button_selected);
				}
				else {
//					Toast.makeText(context, "1", Toast.LENGTH_SHORT).show();
					day[i] = 0;
					btn.setTextColor(Color.parseColor("#97F2F3F3"));
					btn.setBackgroundResource(R.drawable.round_button);
				}

				sh.setDay(day);
				sum.setText(getSummary(day));
			}
		});
	}

	String getDate1(int day, int month, int year) {
		String a = "", b = "";

		if (day < 10)		a = "0";
		if (month < 10)		b = "0";

		return (a + day + "/" + b + month + "/" + year);
	}

	String getDate2(int day, int month, int year) {
		final String []name;
		name = new String[12];

		name[0] = "Jan";
		name[1] = "Feb";
		name[2] = "Mar";
		name[3] = "Apr";
		name[4] = "May";
		name[5] = "Jun";
		name[6] = "Jul";
		name[7] = "Aug";
		name[8] = "Sep";
		name[9] = "Oct";
		name[10] = "Nov";
		name[11] = "Dec";

		String a = "", b = "";

		if (day < 10)		a = "0";

		return (a + day + "/" + name[month] + "/" + year);
	}


	String getSummary(int[] day) {
		boolean start = true;

		String s = " ";
		String []name;

		name = new String[7];

		name[0] = "Sat";
		name[1] = "Sun";
		name[2] = "Mon";
		name[3] = "Tues";
		name[4] = "Wed";
		name[5] = "Thu";
		name[6] = "Fri";

		for (int i = 0; i < 7; i++) {

			if (day[i] == 1) {
				if (!start) {
					s += ", ";
				}

				s += name[i];

				start = false;
			}
		}

		if (start) {
			return "No day selected!";
		}
		return s;
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
