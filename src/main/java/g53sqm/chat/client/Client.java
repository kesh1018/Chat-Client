package g53sqm.chat.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		String serverName = "localhost";
		int port = 9000;
		PrintWriter server_out = null;
	    BufferedReader server_in = null;
		
		try{
			System.out.println("Connecting to " + serverName + " on port " + port);
			Socket socket = new Socket(serverName,port);
		    System.out.println("Just connected to " + socket.getRemoteSocketAddress());
		    
		    server_in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			server_out = new PrintWriter(socket.getOutputStream(), true);
			
			
			String lineread = "";
			boolean done = false;
		      while ((lineread = server_in.readLine()) != null && !done){
		        System.out.println("Received from Server: " + lineread);
		      
		       
		      } 
		      
		      
		      System.out.println("Closing connection.");
		      server_in.close();
		      server_out.close();
		      socket.close();
		}catch(Exception e){
			e.printStackTrace();
            System.exit(1);
		}
	
	}

}
