package com.IDP.Group1.acr;

public class user {

	String name, email, machineID;
	String[] notification, feedback;
	boolean power, sleep;
	int battery;
	Conversation conversation;

	user() {
		name = email = machineID = "";
		power = sleep = false;
		battery = 0;
		notification = new String[100];
		feedback = new String[1000];
		conversation = new Conversation();
	}


}
