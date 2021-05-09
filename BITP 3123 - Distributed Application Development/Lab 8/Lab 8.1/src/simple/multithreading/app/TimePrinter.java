/**
 * 
 */
package simple.multithreading.app;

import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;  
/**
 * This class demonstrate the task to print numbers and current time
 * @author - Chaorrupted X -
 *
 */
public class TimePrinter extends Thread{

	/**
	 * This method prints name of thread and numbers
	 * 
	 * @param currentThread - name of current Thread
	 * @throws InterruptedException 
	 */
	public void printNumbers (String currentThread) throws InterruptedException {

		for (int counter = 0; counter < 10; counter++) {
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		    LocalDateTime now = LocalDateTime.now();
			   
			Thread.sleep(1000);
			System.out.println(currentThread + "->" + dtf.format(now));
		}
	}
	
	@Override
	public void run() {
		
		// Get current thread
		Thread currentThread = Thread.currentThread();
		
		// Execute task
		try {
			printNumbers( currentThread.getName());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
