package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.postgresql.Driver;

import pkg.USER_PASS;

public class DBConnection {

	private Connection connect = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	public static final String URL = "jdbc:postgresql://127.0.0.1:5432/authors_books";

	public Connection getConnection() {
		try {

			/*
			 * The firstly you have to make an class in which you save data
			 * about user_name and password to make a connection to DB
			 * 
			 * first way to make connection
			 */
			USER_PASS user_pass = new USER_PASS();

			// The another way to make a connection

			/*
			 * Scanner scanner = new Scanner(System.in);
			 * 
			 * 
			 * System.out.println("Please enter user_name to connect DB: ");
			 * String USER= scanner.nextLine();
			 * 
			 * System.out.println("Please enter password to connect DB: ");
			 * String PASS= scanner.nextLine();
			 */

			DriverManager.registerDriver(new Driver());
			return (Connection) DriverManager.getConnection(URL,
					user_pass.getUser(), user_pass.getPass());
		} catch (SQLException ex) {
			throw new RuntimeException("Error connecting to the database", ex);
		}
	}

	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
