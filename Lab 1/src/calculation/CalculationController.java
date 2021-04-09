/**
 * 
 */
package calculation;
/**
 * @author - Chaorrupted X -
 *
 */
public class CalculationController {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CalculationView viewObj = new CalculationView();
		
		viewObj.main();
	}
	
	public double[] calculateWeight(Double weight) {
		// TODO Auto-generated method stub
		double[] all_weight = new double[2];
		double weight_on_mars;
		
		weight_on_mars = (weight / 9.81) * 3.711;
		
		all_weight[0] = weight;
		all_weight[1] = weight_on_mars;

		return all_weight;
	}
}