/**
 * 
 */
package tracking.multiple.threads;

/**
 * @author - Chaorrupted X -
 *
 */
public class MultiPrinterApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// Create objects of runnable
		Runnable numberPrinter1 = new TrackPrinter();
		Runnable numberPrinter2 = new TrackPrinter();
		
		// Create objects of thread with name
		Thread printerThread1 = new Thread(numberPrinter1, "text");
		Thread printerThread2 = new Thread(numberPrinter2);
		printerThread2.setName("word2");
		
		// Execute thread
		printerThread2.start();
		printerThread1.start();
	}

}
