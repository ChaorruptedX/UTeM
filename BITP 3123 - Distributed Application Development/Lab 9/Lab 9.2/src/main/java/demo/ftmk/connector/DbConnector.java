/**
 * 
 */
package demo.ftmk.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * @author - Chaorrupted X -
 *
 */
public class DbConnector {

	/**
	 * @param args
	 */
	public Connection getConnection () throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.jdbc.Driver");  
		
		Connection connection = DriverManager.getConnection
				("jdbc:mysql://localhost:3306/mcdonaldsdb", "root", "Zaki0123");  
		
		return connection;
		
	}

}
