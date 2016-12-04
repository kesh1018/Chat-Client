package g53sqm.chat.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {
	
	private static final int port = 9000;
	private static final String serverName = "localhost";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Socket socket = null;
		
		try{
			System.out.println("Connecting to " + serverName + " on port " + port);
			socket = new Socket(serverName,port);
		
		    System.out.println("Just connected to " + socket.getRemoteSocketAddress());
		    
		    BufferedReader sysRead = new BufferedReader(new InputStreamReader(System.in));
		    BufferedReader server_in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		    BufferedWriter server_out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			String response = server_in.readLine();
			System.out.println("Received from Server: " + response);
			System.out.println("Type QUIT to close the server");
			
			boolean flag = true;
			while(flag){
				
				String cmd = sysRead.readLine();
				server_out.write(cmd + "\n");	
				server_out.flush();
		
	            if (cmd.equals("QUIT"))
	            {
	            	System.out.println("Closing connection.");
	                socket.close();
	                sysRead.close();
	                server_in.close();
	                server_out.close();
	                flag = false;
	            } else
	            {
	                String outputline;
	                if ((outputline = server_in.readLine()) != null)
	                    System.out.println(outputline);
	             
	            }
			}

		}catch(IOException e){
			e.printStackTrace();
            
		}
	
	}

}
