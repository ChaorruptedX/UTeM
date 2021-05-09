/**
 * 
 */
package simple.multithreading.app;

/**
 * This class demonstrate name of thread objects and display current time
 * @author - Chaorrupted X -
 *
 */
public class MultiPrinterApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// Create objects of runnable
		Runnable numberPrinter1 = new TimePrinter();
		Runnable numberPrinter2 = new TimePrinter();
		
		// Create objects of thread with name
		Thread printerThread1 = new Thread(numberPrinter1, "printerThread1");
		Thread printerThread2 = new Thread(numberPrinter2);
		printerThread2.setName("printerThread2");
		
		// Execute thread
		printerThread2.start();
		printerThread1.start();

	}

}
