package main;

import xuly.CountDownThread;


public class main {
	public static void main(String[] args) {
		try {
			CountDownThread countDownThread = new CountDownThread();
			countDownThread.start();

		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
	} 
	
}
