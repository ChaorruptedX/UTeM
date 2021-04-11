/**
 * 
 */
package tcpserverword;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This class launch the server side application using TCP.
 * The server process length of text.
 * Each connected client will received word count from the server.
 * 
 * @author - Chaorrupted X -
 *
 */
public class ServerWordApplication {

	/**
	 * 
	 */
	public ServerWordApplication() {
		// TODO Auto-generated constructor stub
	}

	/**
	 *
	 * Main entry point to the server side application
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// Launch the server frame
		ServerWordFrame serverFrame = new ServerWordFrame();
		serverFrame.setVisible(true);
		
		// Binding to a port or any other port no you are fancy of
		int portNo = 4228;
		ServerSocket serverSocket = new ServerSocket(portNo);
		
		WordGenerator wordGenerator = new WordGenerator();
		
		// Counter to keep track the number of requested connection
		int totalRequest = 0;
		
		// Server needs to be alive forever
		while (true) {
			
			// Message to indicate server is alive
			serverFrame.updateServerStatus(false);
			
			
			// Accept client request for connection
			Socket clientSocket = serverSocket.accept();
			
			// Get word count
			String wordCount = wordGenerator.getWordCount();
			
			// Create stream to write data on the network
			DataOutputStream outputStream = 
					new DataOutputStream(clientSocket.getOutputStream());
			
			// Send word count back to the client
			outputStream.writeBytes(wordCount);
			
			// Close the socket
			clientSocket.close();
		
			// Update the request status
			serverFrame.updateRequestStatus(
					"Word count sent to the client: " + wordCount);
			serverFrame.updateRequestStatus("Accepted connection to from the "
					+ "client. Total request = " + ++totalRequest );
		}
	}

}