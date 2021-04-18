/**
 * 
 */
package exe1.udp.server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * This program demonstrate the implementation of DatagramPacket and 
 * DatagramSocket for UDP.  
 * 
 * This program represents a server-side application that receives  
 * a text data from the client.
 * 
 * 
 * @author - Chaorrupted X -
 *
 */
public class ServerApplication {

	/**
	 * 
	 */
	public ServerApplication() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Server UDP socket runs at this port
		final int serverPort = 50001;
		
		int bufferSize = 1024;
		
		System.out.println("Server-Side Application for UDP demo\n");
		
		try {
			
			// Create a new DatagramSocket to receive responses from the client
		    DatagramSocket serverSocket = new DatagramSocket(50001);
		    
		    // Create buffer to hold receiving data.
		    byte[] inData = new byte[bufferSize];
		    
		    // Create a UDP packet to store the client data using the buffer 
		    // for receiving data
		    DatagramPacket inputPacket = new DatagramPacket(inData,
		    		inData.length);
		    System.out.println("Ready to receive connection... ");
		    
		    // Receive data from the client and store in inputPacket
		    serverSocket.receive(inputPacket);
		    
		    // Printing out the client sent data
		    String data = new String(inputPacket.getData());
		    System.out.println("Data from the client: " + data);
		    
		    // Close the socket connection
		    serverSocket.close();
		      
		} catch (Exception ex) {
			
			System.out.println("Durian Tunggal... we got problem");
			ex.printStackTrace();
		}
		
		System.out.println("\nProgram at server-side ends");
	}

}
