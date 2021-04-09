package lab.exercise;

import java.io.FileInputStream;
import java.io.IOException;

public class InputStreamDemo {

	public static void main(String[] args) {
		
		// Declaration of source storage
		String sourceStorage = "demo1.dat";
		String sourceStorage2 = "demo2.dat";

		// Declaration of input stream object
		FileInputStream fileInputStream;
		FileInputStream fileInputStream2;
		
		System.out.println("Read binary data");

		try {

			// Construct input stream
			fileInputStream = new FileInputStream(sourceStorage);
			fileInputStream2 = new FileInputStream(sourceStorage2);

			// Read from input stream
			int data = fileInputStream.read();
			while (data != -1) {
				
				System.out.println(data);
				
				// Read next data
				data = fileInputStream.read();
				
			}

			System.out.println("\n");
			
			// Read from input stream
			int data2 = fileInputStream2.read();
			while (data2 != -1) {
				
				System.out.println(data2);
				
				// Read next data
				data2 = fileInputStream2.read();
				
			}

			System.out.println("\n");

			// Close stream
			fileInputStream.close();  

		} catch (IOException e) {

			System.out.println("Durian Tunggal... we got problem");

			e.printStackTrace();
		}  

		System.out.println("End of program.");

	}

}
