package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnect {
	public static Connection getConnection() {
		final String url = "jdbc:sqlserver://localhost:1433;databaseName=greencoffee;user=sa;password=rainsama123456";
		try {
			return DriverManager.getConnection(url);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
