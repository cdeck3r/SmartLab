package simulator.dbconnector;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class DBConnector {
	public Connection getDBVerbindung() {
		Connection connection = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

		} catch (ClassNotFoundException e) {
			System.out.println("Error: unable to load driver class!");
			System.exit(1);
		}

		try {
			connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/smartlab", "root",
					"adesso");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return connection;
	}
}
