package lab.exercise;

/**
 * This program demonstrate the application of output stream that processed data as raw bytes.
 * The data is stored in a file.
 * 
 * @author emalianakasmuri
 * 
 */


import java.io.FileOutputStream;
import java.io.IOException;

public class OutputStreamDemo {

	public static void main(String[] args) {
		
		
		// Declaration of target storage
		String targetStorage = "demo1.dat";
		String targetStorage2 = "demo2.dat";
		
		// Declaration of output stream object
		FileOutputStream fileOutputStream;
		FileOutputStream fileOutputStream2;
		
		System.out.println("Generate binary data");
		
		try {
			
			// Create an output stream between this file and data.dat
			fileOutputStream = new FileOutputStream(targetStorage);
			fileOutputStream2 = new FileOutputStream(targetStorage2);
			
			// Write three characters 
			fileOutputStream.write(64);
			fileOutputStream2.write(65);
			
			fileOutputStream.flush();
			fileOutputStream2.flush();
						
			// Close the stream
			fileOutputStream.close();  
			fileOutputStream2.close();  
	         
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		// Indicate end of program - Could be succcessful
		System.out.println("End of program.");
		System.out.println("Right click on labWeek3. Select Refresh.");
		System.out.println(targetStorage + " should be there. Check it out!");   
	}

}