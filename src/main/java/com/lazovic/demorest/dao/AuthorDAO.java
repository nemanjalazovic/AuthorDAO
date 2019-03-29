package com.lazovic.demorest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;

import org.apache.log4j.Logger;

import com.lazovic.demorest.interfaces.IAuthorDAO;
import com.lazovic.demorest.model.Author;

public class AuthorDAO implements IAuthorDAO {
	private final Logger logger = Logger.getLogger(this.getClass());

	@Override
	public Author getAuthor(int id) throws SQLException {
		DBConnection conn = new DBConnection();
		Connection connection = conn.getConnection();
		try {
			PreparedStatement stmt = connection
					.prepareStatement("SELECT * FROM authors WHERE id=" + id);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				Author author = new Author();
				author.setId(rs.getInt("id"));
				author.setName(rs.getString("name"));
				return author;
			}

		} catch (SQLException ex) {
			logger.error("Unsuccessful db connection", ex);
		} finally {
			connection.close();
			System.out.println("finally block executed");

		}
		return null;
	}

	@Override
	public ArrayList<Author> getAllAuthors() throws SQLException {
		DBConnection conn = new DBConnection();
		Connection connection = conn.getConnection();
		ArrayList<Author> list = new ArrayList<Author>();

		try {
			PreparedStatement stmt = connection
					.prepareStatement("SELECT * FROM authors");

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Author author = new Author();
				author.setId(rs.getInt("id"));
				author.setName(rs.getString("name"));
				list.add(author);
			}

		}

		catch (SQLException ex) {
			logger.error("Unsuccessful db connection", ex);
		} finally {
			connection.close();
			System.out.println("finally block executed");

		}
		return list;
	}

	@Override
	public boolean insertAuthor(Author author) throws SQLException {
		DBConnection conn = new DBConnection();
		Connection connection = conn.getConnection();
		try {
			PreparedStatement ps = connection
					.prepareStatement("INSERT INTO authors VALUES (?, ?)");
			ps.setInt(1, author.getId());
			ps.setString(2, author.getName());

			int i = ps.executeUpdate();
			if (i == 1) {
				return true;
			}

		} catch (SQLException ex) {
			logger.error("Unsuccessful db connection", ex);
		} finally {
			connection.close();
			System.out.println("finally block executed");

		}
		return false;
	}

	@Override
	public boolean updateAuthor(Author author) throws SQLException {
		DBConnection conn = new DBConnection();
		Connection connection = conn.getConnection();
		try {
			PreparedStatement ps = connection
					.prepareStatement("UPDATE authors SET name=? WHERE id=?");
			ps.setString(1, author.getName());
			ps.setInt(2, author.getId());

			int i = ps.executeUpdate();
			if (i == 1) {
				return true;
			}

		} catch (SQLException ex) {
			logger.error("Unsuccessful db connection", ex);
		} finally {
			connection.close();
			System.out.println("finally block executed");

		}
		return false;
	}

	@Override
	public boolean deleteAuthor(int id) throws SQLException {
		DBConnection conn = new DBConnection();
		Connection connection = conn.getConnection();
		try {
			PreparedStatement stmt = connection
					.prepareStatement("DELETE FROM authors WHERE id=" + id);
			int i = stmt.executeUpdate();
			if (i == 1) {
				return true;
			}

		} catch (SQLException ex) {
			logger.error("Unsuccessful db connection", ex);
		} finally {
			connection.close();
			System.out.println("finally block executed");

		}
		return false;
	}

	@Override
	public String GetVersion() throws SQLException {
		DBConnection conn = new DBConnection();
		Connection connection = conn.getConnection();
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery("SELECT VERSION()");
		try {

			if (rs.next()) {
				System.out.println(rs.getString(1));
			}

		} catch (SQLException ex) {

			logger.error("Unsuccessful db connection", ex);
		} finally {
			connection.close();
			System.out.println("finally block executed");

		}
		return null;
	}

	public static void main(String[] args) throws SQLException {
		AuthorDAO dao = new AuthorDAO();

		System.out.println(dao.getAuthor(18));
		// System.out.println(dao.getAllAuthors());

		Author a1 = new Author(8, "Mark Tven");
		// dao.insertAuthor(a1);

		Author a2 = new Author(8, "Lav Tolstoj");
		// dao.updateAuthor(a2);

		// dao.deleteAuthor(8);

		// dao.GetVersion();

		System.out.println(dao.getAllAuthors());

	}

}
