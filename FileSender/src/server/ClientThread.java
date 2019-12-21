package server;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.text.SimpleDateFormat;
import java.util.Date;

import client.FileInfo;

public class ClientThread extends Thread{
	Socket client;
	String session;
	ClientThread(Socket sock) throws IOException{
		client = sock;
	}
	
	public void run() {
		try {
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		while(true) {
			try {
				InputStream is = client.getInputStream();
				ObjectInputStream ois = new ObjectInputStream(is);
                FileInfo info = (FileInfo)ois.readObject();

                System.out.println(info.name);

                
                File find = new File("files/"+info.name);
                if(find != null) {
                	Date date = new Date();
                	SimpleDateFormat formatForDateNow = new SimpleDateFormat("E-yyyy-MM-dd_hh-mm");
                	String dateFormat = formatForDateNow.format(date);
                	info.name = dateFormat+info.name;
                }
                FileOutputStream fOut = new FileOutputStream("files/"+info.name);
                fOut.write(info.file);
                fOut.close();

                if(client.isClosed()) {
					System.out.println("Client disconnect!");
				}
            } 
			catch (SocketTimeoutException e) {
				System.out.println(e.getMessage());
                this.stop();
            }
			catch(EOFException e) {

			}
			
			catch (Exception e) {
				System.out.println("error:"+e.getMessage());
				System.out.println(e);
                this.stop();
            }
			
		}
	}
}
