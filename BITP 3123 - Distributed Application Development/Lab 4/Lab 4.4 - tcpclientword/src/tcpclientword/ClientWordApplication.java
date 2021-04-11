/**
 * 
 */
package tcpclientword;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * This method launch the frame and manage the connection to the server.
 * 
 * @author - Chaorrupted X -
 *
 */
public class ClientWordApplication {

	/**
	 * 
	 */
	public ClientWordApplication() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub

		// Launch client-side frame
		ClientWordFrame clientWordFrame = new ClientWordFrame();
		clientWordFrame.setVisible(true);
		
		// Connect to the server @ localhost, port 4228
		Socket socket = new Socket(InetAddress.getLocalHost(), 4228);
		
		// Update the status of the connection
		clientWordFrame.updateConnectionStatus(socket.isConnected());
		
		// Read from network
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(socket.getInputStream()));
		
		// Display the current date
		String wordCount = bufferedReader.readLine();
		clientWordFrame.updateServerWordCount(wordCount);
		
		// Close everything
		bufferedReader.close();
		socket.close();
	}

}
