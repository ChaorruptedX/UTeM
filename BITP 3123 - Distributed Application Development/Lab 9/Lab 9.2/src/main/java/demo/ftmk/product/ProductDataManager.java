/**
 * 
 */
package demo.ftmk.product;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import demo.ftmk.connector.DbConnector;

/**
 * @author - Chaorrupted X -
 *
 */
public class ProductDataManager {
	
	
	private DbConnector dbConn;
	
	public ProductDataManager () {
		
		dbConn = new DbConnector();
		
	}
	
	/**
	 * This method add a new product into the database
	 * @param product: A new product to be added
	 * @return productId
	 * @throws Exception 
	 */
	public int addProduct (Product product) throws Exception {
		
		// Define closeable objects
		Connection conn = null;
		PreparedStatement preStmt = null;
		ResultSet results = null;

		// Form SQL 
		String sql = "insert into Product (productId, name, price)\n" + 
				"values (?, ?, ?)";
		
		int key = 0;
		
		try {
			
			// Establish connection
			conn = dbConn.getConnection();
			
			// Set parameter
			preStmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			preStmt.setInt(1, product.getProductId());
			preStmt.setString(2, product.getName());
			preStmt.setDouble(3, product.getPrice());
			
			// Execute SQL
			int row = preStmt.executeUpdate();
			results = preStmt.getGeneratedKeys();
			
			 
			// Get key
			key = row;
			while (results.next())
				key = results.getInt(1);
			
		} catch (ClassNotFoundException | SQLException exception) {

				throw exception;
			
		} finally {
			// Close all closeable objects
			
			if (results != null)
				results.close();
			
			if (preStmt != null)
				preStmt.close();
			
			if (conn != null)
				conn.close();
			
		}
		
		return key;
		
	}
	
	
	/**
	 * A method to select a list of products from the database
	 * @return A list of products
	 * @throws Exception
	 */
	protected List<Product> selectProducts() throws Exception  {
		
		// Define closeable objects
		Connection conn = null;
		Statement stmt = null;
		ResultSet results = null;
		
		// Form SQL 
		String sql = "select p.productId, p.name, p.price \n" + 
				"from Product p";
		
		List<Product> products = new ArrayList<Product>();
		
		try {
			
			// Establish connection
			conn = dbConn.getConnection();
			
			// Execute SQL  
			stmt = conn.createStatement();
			results = stmt.executeQuery(sql); 
			
			// Wrap result in a list
			while (results.next()) {
				
				Product product = new Product();
				product.setProductId(results.getInt(1));
				product.setName(results.getString(2));
				product.setPrice(results.getDouble(3));
				
				// Add into list
				products.add(product);
				
			}
			
		} catch (ClassNotFoundException | SQLException exception) {
			
			throw exception;
			
		} finally {
			// Close all closeable objects
			
			if (results != null)
				results.close();
			
			if (stmt != null)
				stmt.close();
			
			if (conn != null)
				conn.close();
			
		}
		
		return products;
		
	}
	
	
	
	/**
	 * A method to get a list of product from a text file
	 * @return A list of product
	 * @throws IOException 
	 */
	public List<Product> getProducts() throws IOException {
		
		
		List<Product> products = new ArrayList<Product>(); 
			
		try {
			
			// Read from product.txt
			FileReader fileReader = new FileReader("product.txt");
			BufferedReader bufferedReader = new BufferedReader (fileReader);
			
			// Read and wrap data until end of file
			String line = bufferedReader.readLine();
			while (line != null) {
				
				// Split and wrap data into object
				String data[] = line.split(";");
				Product product = new Product();
				product.setProductId(Integer.parseInt(data[0]));
				product.setName(data[1]);
				product.setPrice(Double.parseDouble(data[2]));
				
				// Add data into list
				products.add(product);
				
				// Read next data
				line = bufferedReader.readLine();
			}
			
			// Close streams
			bufferedReader.close();
			fileReader.close();
			
		} catch (IOException exception) {
			
			throw exception;
		
		} 
		
		return products;
		
		
	}
	
	
	

}

