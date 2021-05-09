/**
 * 
 */
package suspending.some.threads;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author - Chaorrupted X -
 *
 */
public class SuspendPrinter implements Runnable {

	public void printWords (String currentThread) {

		String[] word = { "It", "is", "recommended", "to", "use", "Calendar", "class" };
		
		if (currentThread == "text") {
			for (String value : word) {
				
				System.out.println(currentThread + "->" + value);
			}
		}
		else if (currentThread == "word1" || currentThread == "word2") {
			
			List<String> intList = Arrays.asList(word);

			Collections.shuffle(intList);

			intList.toArray(word);
			
			for (String value : word) {
				
				System.out.println(currentThread + "->" + value);
			}
		}
	}
	
	@Override
	public void run() {
		
		// Get current thread
		Thread currentThread = Thread.currentThread();
		
		// Execute task
		printWords( currentThread.getName());
		
	}

}
