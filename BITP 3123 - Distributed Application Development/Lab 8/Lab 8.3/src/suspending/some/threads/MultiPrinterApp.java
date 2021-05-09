/**
 * 
 */
package suspending.some.threads;

/**
 * @author - Chaorrupted X -
 *
 */
public class MultiPrinterApp {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {

		// Create objects of runnable
		Runnable numberPrinter1 = new SuspendPrinter();
		Runnable numberPrinter2 = new SuspendPrinter();
		Runnable numberPrinter3 = new SuspendPrinter();
		
		// Create objects of thread with name
		Thread printerThread1 = new Thread(numberPrinter1, "text");
		Thread printerThread2 = new Thread(numberPrinter2);
		printerThread2.setName("word1");
		Thread printerThread3 = new Thread(numberPrinter3, "word2");
		
		// Execute thread
		printerThread1.start();
		printerThread2.start();
		printerThread3.start();
		
		printerThread2.suspend();
		System.out.println("Thread word1 going to sleep for 5 seconds");
		Thread.sleep(5000);
		System.out.println("Thread word1 Resumed");
		printerThread2.resume();
	}

}
