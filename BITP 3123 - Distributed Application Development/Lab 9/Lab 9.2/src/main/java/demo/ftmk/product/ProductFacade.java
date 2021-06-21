/**
 * 
 */
package demo.ftmk.product;

import java.util.List;

/**
 * @author - Chaorrupted X -
 *
 */
public class ProductFacade {

	private ProductDataManager productMgr;
	
	public ProductFacade() {
		
		productMgr = new ProductDataManager();
		
	}
	
	/**
	 * This method add a new product into the database
	 * @param product: A new product to be added
	 * @return productId
	 * @throws Exception 
	 */
	public int addProduct (Product product) throws Exception {
		
		return productMgr.addProduct(product);
	}
	
	/**
	 * A method to select a list of products from the database
	 * @return A list of products
	 * @throws Exception
	 */
	public List<Product> selectProducts() throws Exception  {
		
		return productMgr.selectProducts();
	}

}
