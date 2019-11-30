package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class server extends Thread{

	public void run() {
		try {
			ServerSocket server = new ServerSocket(2020);
			while(true) {
				Socket sock = server.accept();
				BufferedReader br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
				System.out.println(br.readLine());
			}
			
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
