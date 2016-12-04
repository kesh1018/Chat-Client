package g53sqm.chat.client;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;


import org.junit.Test;

public class ClientTest {
	
	private static final int TEST_PORT = 9000;
	private static final String TEST_SERVER = "localhost";
	
	@Test
    public void testClientSendMessage() {
		
		Socket socket = null;
		
        try {
        	socket = new Socket(TEST_SERVER,TEST_PORT);
        	assertNotNull(socket);
        	
        	BufferedReader sysRead = new BufferedReader(new InputStreamReader(System.in));
        	BufferedWriter server_out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		    BufferedReader server_in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		    		           
            String result = server_in.readLine();
            System.out.println(result);
            assertNotNull(result);
            
            boolean flag = true;
			while(flag){
				
				String cmd = sysRead.readLine();
				server_out.write(cmd + "\n");	
				server_out.flush();
		
	            if (cmd.equals("QUIT")) {
	            	
	            	assertEquals(cmd,"QUIT");
	            	System.out.println("Closing connection.");
	            	
	                socket.close();
	                sysRead.close();
	                server_in.close();
	                server_out.close();
	                flag = false;
	                
	                assertFalse(flag);
	            } else {
	            	String outputline;
	                
	                if ((outputline = server_in.readLine()) != null){
	                	System.out.println(outputline);                
	                }
	                assertNotNull(outputline);
	                assertTrue(flag);
	              
	            }
			}
 
        } catch (Exception e) {
            e.printStackTrace();
        	assertTrue(false);
        }
 
        try {
        	socket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            assertTrue(false);
        }
        
    }

}
