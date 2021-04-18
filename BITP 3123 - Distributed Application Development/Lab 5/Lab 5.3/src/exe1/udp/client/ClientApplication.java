/**
 * 
 */
package exe1.udp.client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


/**
 * This program demonstrate the implementation of DatagramPacket and 
 * DatagramSocket for UDP.  
 * 
 * This program represents a client-side application that sends 
 * a text data to the server.
 * 
 * How to run this application?
 * 1. Run from Eclipse
 * 
 * @author - Chaorrupted X -
 *
 */
public class ClientApplication {

	/**
	 * 
	 */
	public ClientApplication() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// The server port to which the client socket is going to connect
		final int SERVERPORT = 50001;
		
		int bufferSize = 1024;
		
		System.out.println("Client-Side Application for UDP demo\n");
		
		try {
			
			
			// Create client socket
		    DatagramSocket clientSocket = new DatagramSocket();
		    
		    // Get the IP address of the server
		    InetAddress serverAddress = InetAddress.getByName("localhost");
		    
		    // Creating corresponding buffer to send data
		    byte outDataBuffer[] = new byte[bufferSize];
		    
		    // Converting data to bytes and storing them in the sending buffer
		    String sentence = "Fakulti Teknologi Maklumat dan Komunikasi";
		    outDataBuffer = sentence.getBytes();
		    
		    // Creating a UDP packet 
		    DatagramPacket outPacket = new DatagramPacket(outDataBuffer,
		    		outDataBuffer.length, serverAddress, SERVERPORT);
		    
		    // Sending UDP packet to the server
		    System.out.println("Sending '" + sentence + "'. "
		    		+ "Size = " + outDataBuffer.length);
		    clientSocket.send(outPacket);
		    
		    // Closing the socket connection with the server
		    clientSocket.close();
		    
		} catch (Exception ex) {
			
			System.out.println("Durian Tunggal... we got problem");
			ex.printStackTrace();
		}
		
		System.out.println("\nProgram at client-side ends");
	}

}
