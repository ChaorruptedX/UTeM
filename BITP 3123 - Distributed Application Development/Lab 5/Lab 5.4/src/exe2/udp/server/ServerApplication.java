/**
 * 
 */
package exe2.udp.server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
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

		try {
			
			// Instantiate a new DatagramSocket to receive responses from the client
		    DatagramSocket serverSocket = new DatagramSocket(50001);
		    
		    // Create buffers to hold receiving data.
		    byte receivingDataBuffer[] = new byte[1024];
		    
		    // Instantiate a UDP packet to store the client data using the buffer for receiving data
		    DatagramPacket inputPacket = new DatagramPacket(receivingDataBuffer, receivingDataBuffer.length);
		    System.out.println("Waiting for a client to connect...");
		    
		    // Receive data from the client and store in inputPacket
		    serverSocket.receive(inputPacket);
		    
		    // Printing out the client sent data
		    String receivedData = new String(inputPacket.getData());
		    System.out.println("Sent from the client: " + receivedData);
		    
		    // Process data - convert to upper case
		    String sendingData = receivedData.toUpperCase();
		    
		    // Creating corresponding buffer to send data
		    byte sendingDataBuffer[] = sendingData.getBytes();
		    
		    // Get client's address
		    InetAddress senderAddress = inputPacket.getAddress();
		    int senderPort = inputPacket.getPort();
		    
		    // Create new UDP packet with data to send to the client
		    DatagramPacket outputPacket = new DatagramPacket(sendingDataBuffer, 
		    		sendingDataBuffer.length, senderAddress,senderPort);
		    
		    // Send the created packet to client
		    serverSocket.send(outputPacket);
		    
		    
		    // Close the socket connection
		    serverSocket.close();
		      
		} catch (Exception ex) {
			
			System.out.println("Durian Tunggal... we got problem");
			ex.printStackTrace();
		}
	}

}
