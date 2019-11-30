package id;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MultiClient extends Thread{
	Socket client;
	String session;
	MultiClient(Socket sock) throws IOException{
		client = sock;
	}
	public void run() {
		while(true) {
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
				String s = "";
				if ((s = br.readLine()) != null) {
					System.out.println(s);
					String[] user_data = s.split(":");
					session = user_data[0];
					Date date = new Date();
					OutputStream out = client.getOutputStream();
					ObjectOutputStream oos = new ObjectOutputStream(out);
					String result = "";
					if(user_data[1].equals("get_log")) {
						
						List<String> logs = new ArrayList<String>();
						FileReader fr = new FileReader(session+"log.txt");
						Scanner sc = new Scanner(fr);
						while(sc.hasNextLine()) {
							logs.add(sc.nextLine());
						}
						sc.close();
						fr.close();
						oos.writeObject(logs);
						oos.close();
					}
					
					else 
						
					{
						FileWriter fw = new FileWriter(session+"log.txt", true);
						BufferedWriter bf = new BufferedWriter(fw);
						result = new Calculator().Calc(user_data[1]);
						bf.write(date + " - Data:" + s +"\n");
						bf.write(date + " - Result:" + result +"\n");
						bf.flush();
						fw.close();
						out.write(result.getBytes());
					}
					
				}
				if(client.isClosed()) {
					System.out.println("Client disconnect!");
				}
				
			}
			
			catch (Exception e){
				System.out.println("Fail to connect:" + e.getMessage());
				try {
					Date date = new Date();
					FileWriter fw = new FileWriter(session+"log.txt", true);
					BufferedWriter bf = new BufferedWriter(fw);
					bf.write(date + " - Fail to connect:" + e.getMessage()+"\n");
					
					bf.flush();
					fw.close();
				}
				catch(IOException e1) {
					System.out.println(e1.getMessage());
				}
				
				this.stop();
			}
		}
	}
}
