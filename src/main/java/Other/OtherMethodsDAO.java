package Other;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;

import DAO.AuthorDAO;
import DAO.DBConnection;

public class OtherMethodsDAO {
	
	
	public void writeMultipleRows(String query) throws SQLException {
		DBConnection conn = new DBConnection();
		Connection connection = conn.getConnection();
		PreparedStatement pst = connection.prepareStatement(query);

		boolean isResult = pst.execute();

		do {
			try (ResultSet rs = pst.getResultSet()) {

				while (rs.next()) {

					System.out.print(rs.getInt(1));
					System.out.print(": ");
					System.out.println(rs.getString(2));
				}

				isResult = pst.getMoreResults();

			} catch (SQLException ex) {

				Logger lgr = Logger.getLogger(AuthorDAO.class.getName());
				lgr.log(Level.SEVERE, ex.getMessage(), ex);
			}
		} while (isResult);

	}

	
	public void writeMetaData(String query) throws SQLException {
		DBConnection conn = new DBConnection();
		Connection connection = conn.getConnection();
		PreparedStatement pst = connection.prepareStatement(query);
		ResultSet rs = pst.executeQuery();
		try {

			ResultSetMetaData meta = rs.getMetaData();

			String colname1 = meta.getColumnName(1);
			String colname2 = meta.getColumnName(2);

			Formatter fmt1 = new Formatter();
			fmt1.format("%-21s%s", colname1, colname2);
			System.out.println(fmt1);

			while (rs.next()) {

				Formatter fmt2 = new Formatter();
				fmt2.format("%-21s", rs.getString(1));
				System.out.print(fmt2);
				System.out.println(rs.getString(2));
			}

		} catch (SQLException ex) {

			Logger lgr = Logger.getLogger(AuthorDAO.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}

	}
	
	public static void main() throws SQLException{
		
		OtherMethodsDAO dao = new OtherMethodsDAO();

		
		String query1 = "SELECT id, name FROM authors where id=1;"
				+ "SELECT id, name FROM authors Where id=2;"
				+ "SELECT id, name FROM authors WHERE id=3";
		 dao.writeMultipleRows(query1);

		String query2 = "SELECT name, title FROM authors, books WHERE authors.id= books.id";
		 dao.writeMetaData(query2);

		
	}

}
