/**
 * 
 */
package demo.ftmk.tester;

import java.io.IOException;
import java.util.List;

import demo.ftmk.product.Product;
import demo.ftmk.product.ProductDataManager;
import demo.ftmk.product.ProductFacade;

/**
 * @author - Chaorrupted X -
 *
 */
public class ProductApp {

	public static void main(String[] args) {
		
		ProductFacade productFacade = new ProductFacade();
		
		try {
			List<Product> products = productFacade.selectProducts();
			System.out.println("Size of products: " + products.size());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
