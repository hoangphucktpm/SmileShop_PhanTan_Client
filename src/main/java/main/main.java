package main;

import Database.ConnectDatabase;
import xuly.CountDownThread;


public class main {
	public static void main(String[] args) {
		try {
			ConnectDatabase.getInstance().connect();
			CountDownThread countDownThread = new CountDownThread();
			countDownThread.start();

		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
	} 
	
}
