/**
 * 
 */
package clienttranslation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author - Chaorrupted X -
 *
 */
public class ClientTranslationApplication {

	/**
	 * 
	 */
	public ClientTranslationApplication() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			// Connect to the server at localhost, port 4228
			Socket socket = new Socket(InetAddress.getLocalHost(), 4228);
			
			// Create input stream
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			// Read from the network and display the current data
			String text = bufferedReader.readLine();
			System.out.println(text);
			
			// Close everything
			bufferedReader.close();
			socket.close();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

}
