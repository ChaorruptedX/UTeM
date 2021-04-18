/**
 * 
 */
package laz.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import laz.model.ItemProduct;

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

		try {
			
			// Port to receive and respond to request
			int portNo = 4228;
			ServerSocket serverSocket = new ServerSocket(portNo);
			
			System.out.println("Ready for request");
			
			// Server need to be alive forever thus the while(true)
			while (true) {
				
				// Accept client request for connection
				Socket socket = serverSocket.accept();
				
				// Create input stream to read object
				ObjectInputStream objectIS = new ObjectInputStream(socket.getInputStream());
				
				// Read object from stream and cast to ItemProduct
				ItemProduct itemProduct = (ItemProduct) objectIS.readObject();
				
				// Process object
				itemProduct.setItemProductId(1);
				
				// Create output stream to send object
				ObjectOutputStream objectOS = new ObjectOutputStream(socket.getOutputStream());
				objectOS.writeObject(itemProduct);
				
				
				System.out.println("Ready for next request");
				
				// Close all streams
				objectIS.close();
				objectOS.close();
			}
			
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
