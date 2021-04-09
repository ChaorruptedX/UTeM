/**
 * 
 */
package calculation;
import java.util.Scanner;  // Import the Scanner class
import java.text.DecimalFormat;
/**
 * @author - Chaorrupted X -
 *
 */
public class CalculationView {
	/**
	 * @param args
	 */
	
	private static DecimalFormat df2 = new DecimalFormat("#.##");
	
	public void main() {
		// TODO Auto-generated method stub
		CalculationController controllerObj = new CalculationController();
		Scanner scannerObj = new Scanner(System.in);
		double[] all_weight = new double[2];
	    Double weight;
	    
	    // Enter user weight and press Enter
	    System.out.println("Enter your weight: "); // Input User Weight
	    weight = scannerObj.nextDouble();
	    scannerObj.close();

	    all_weight = controllerObj.calculateWeight(weight);
	       
	    System.out.println("Your weight on Earth is: " + df2.format(all_weight[0]));  // Output User Weight on Earth
	    System.out.println("Your weight on Mars is: " + df2.format(all_weight[1]));  // Output User Weight on Mars
	}
}