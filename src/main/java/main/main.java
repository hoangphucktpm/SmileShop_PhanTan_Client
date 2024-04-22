package main;

import DAOTest.NhaCungCapDao;
import xuly.CountDownThread;

import java.rmi.Naming;
import java.rmi.RemoteException;


public class main {
	private static final String URL = "rmi://192.168.1.16:6541/";

	public static void main(String[] args) {
		try {

			CountDownThread countDownThread = new CountDownThread();
			countDownThread.start();

		} catch (Exception  e) {
			e.printStackTrace();

		}

	}

}
