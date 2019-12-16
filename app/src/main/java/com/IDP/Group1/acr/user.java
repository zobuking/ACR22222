package com.IDP.Group1.acr;

import java.util.ArrayList;
import java.util.List;

public class user {

	String name, email, machineID, contactInfo;
	List<String> notification;
	boolean power, sleep;
	int battery;

	user() {
		name = email = machineID = "";
		power = sleep = false;
		battery = 0;
		notification = new ArrayList<String>();
	}


}
