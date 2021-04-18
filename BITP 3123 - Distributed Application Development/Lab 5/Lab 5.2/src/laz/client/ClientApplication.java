/**
 * 
 */
package laz.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import laz.model.ItemProduct;

/**
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

		System.out.println("ClientSideApp: Welcome to Laz\n");

		// Request data
		ItemProduct itemProduct = new ItemProduct();
		itemProduct.setName("Lenovo Legion 15");
		itemProduct.setPrice((float) 4850.75);
		

		try {

			// Data to establish connection to server
			int portNo = 4228;
			InetAddress serverAddress = InetAddress.getLocalHost();

			// Connect to the server at localhost, port 4228
			Socket socket = new Socket(serverAddress, portNo);

			// Open stream to send object
			ObjectOutputStream objectOS = new ObjectOutputStream(socket.getOutputStream());

			// Send request to server
			System.out.println("Send object to server: " + itemProduct);
			objectOS.writeObject(itemProduct);
			objectOS.flush();
			
			// Open stream to receive object
			ObjectInputStream objectIS = new ObjectInputStream(socket.getInputStream());
			
			// Get object from stream and display details
			itemProduct = (ItemProduct) objectIS.readObject();
			System.out.println ("Id for " + itemProduct.getName() + " is " + itemProduct.getItemProductId());
			System.out.println ("The price is RM " + itemProduct.getPrice());
			
			// Close all closeable objects
			objectOS.close();
			objectIS.close();
			socket.close();

		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("\nClientSideApp: Thank you.\n");
	}

}
