package id;

import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(1919);
			while(true) {
				MultiClient mc = new MultiClient(ss.accept());
				if(mc.client.isConnected()) {
					mc.start();
				}
			
			}
		}
		catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
	public void run() {
		
		
	}
	
	
}
