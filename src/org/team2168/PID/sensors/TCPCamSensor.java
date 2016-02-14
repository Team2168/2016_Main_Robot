package org.team2168.PID.sensors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPCamSensor {
	
	
	private static volatile int tegraPort;
	
	private static Thread tcpServerThread;
	private static Thread manageConnectionThreads;
	
	private static volatile ServerSocket tcpServerSocket;
	private static volatile Socket tcpTegraConnectionSocket;
	private static volatile boolean tegraConnected;
	private static volatile BufferedReader tegraReadCommunication;
	private static volatile String dataString;
	private static volatile String[] splitDataString;
	
	private static boolean startConnection;
	
	private static volatile double rotationAngle;

	public TCPCamSensor(int port) {
		tegraPort = port;
		startConnection = true;
		tegraConnected = false;
		rotationAngle = -1.0;
		dataString = "";
	}
	
	public static void start() {
		
		tcpServerThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				while (startConnection) {
					while (tegraConnected) {
						
						try {
							dataString = tegraReadCommunication.readLine();
						} catch (IOException e) {
							e.printStackTrace();
						}
						
						splitDataString = dataString.split(",");
						
						rotationAngle = Double.parseDouble(splitDataString[0]);
						System.out.println(rotationAngle);
						
			        }
				}
			}
		});
		
		tcpServerThread.start();
		
	}
	
	public static void manageConnections() {
		
		manageConnectionThreads = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (startConnection) {
					try {
						tcpServerSocket = new ServerSocket(tegraPort);
						tcpTegraConnectionSocket = tcpServerSocket.accept();
						tegraReadCommunication = new BufferedReader(new InputStreamReader(tcpTegraConnectionSocket.getInputStream()));
					}catch (Exception ex) {
						System.out.println(ex);
					}
				}
			}
		});
		
	}
	
	
}