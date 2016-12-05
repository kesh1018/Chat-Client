package g53sqm.chat.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/* - This program provides a user interface in Command Line form to interact with server on a certain port
 * - It's able to send text message to the server
 * - It's able to receive text messages from the server
 * - It's able to show the names of users who're currently connected to the server*/

public class Client {
	
	private static final int PORT = 9000;
	private static final String SERVERNAME = "localhost";
	
	public static void main(String[] args) {

		Socket socket = null;
		
		try{
			System.out.println("Connecting to " + SERVERNAME + " on port " + PORT);
			socket = new Socket(SERVERNAME,PORT);
		
		    System.out.println("Just connected to " + socket.getRemoteSocketAddress());
		    
		    final BufferedReader sysRead = new BufferedReader
		    		(new InputStreamReader(System.in));
		    final BufferedReader server_in = new BufferedReader
		    		(new InputStreamReader(socket.getInputStream()));
		    final BufferedWriter server_out = new BufferedWriter
		    		(new OutputStreamWriter(socket.getOutputStream()));
			
			final String response = server_in.readLine();
			System.out.println("Received from Server: " + response);
			System.out.println("Type QUIT to close the server");
			
			boolean flag = true;
			while(flag){
				
				final String cmd = sysRead.readLine();
				server_out.write(cmd + "\n");	
				server_out.flush();
				
				if(cmd != null){
					
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
		                if ((outputline = server_in.readLine()) != null){
		                	System.out.println(outputline);
		                }
		            }
				}
	            
			}

		}catch(IOException e){
			e.printStackTrace();
            
		}
	
	}

}
