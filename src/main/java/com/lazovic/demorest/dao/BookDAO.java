package com.lazovic.demorest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.lazovic.demorest.interfaces.IBookDAO;
import com.lazovic.demorest.model.Author;
import com.lazovic.demorest.model.Book;

public class BookDAO implements IBookDAO {
	private final Logger logger = Logger.getLogger(this.getClass());

	@Override
	public ArrayList<Book> getAllBooks() throws SQLException {
		DBConnection conn = new DBConnection();
		Connection connection = conn.getConnection();
		ArrayList<Book> list = new ArrayList<Book>();

		try {

			PreparedStatement stmt = ((Connection) connection)
					.prepareStatement("select b.id,a.name,b.title from authors a join books b on a.id=b.author_id ");

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Book book = new Book();
				book.setId(rs.getInt("id"));
				book.setAuthor(rs.getString("name"));
				book.setTitle(rs.getString("title"));

				list.add(book);
			}

		} catch (SQLException ex) {
			logger.info("Unsuccessful db connection", ex);
		} finally {
			connection.close();
			System.out.println("finally block executed");

		}

		return list;
	}

	@Override
	public Book getBook(int id) throws SQLException {
		DBConnection conn = new DBConnection();
		Connection connection = conn.getConnection();
		try {
			PreparedStatement stmt = ((Connection) connection)
					.prepareStatement("SELECT b.id,b.title,a.name FROM authors a join books b ON a.id=b.author_id WHERE b.id="
							+ id);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				Book book = new Book();
				book.setId(rs.getInt("id"));
				book.setAuthor(rs.getString("name"));
				book.setTitle(rs.getString("title"));

				return book;
			}

		} catch (SQLException ex) {
			logger.info("Unsuccessful db connection", ex);
		} finally {
			connection.close();
			System.out.println("finally block executed");

		}
		return null;
	}

	@Override
	public boolean insertBook(Author author, Book book) throws SQLException {
		DBConnection conn = new DBConnection();
		Connection connection = conn.getConnection();

		try {
			PreparedStatement ps = ((Connection) connection)
					.prepareStatement("INSERT INTO books(id,author_id,title) VALUES (default,?, ?)");
			// ps.setInt(1, book.getId());
			ps.setInt(1, author.getId());
			ps.setString(2, book.getTitle());

			int i = ps.executeUpdate();
			if (i == 1) {
				return true;
			}

		} catch (SQLException ex) {
			logger.info("Unsuccessful db connection", ex);
		} finally {
			connection.close();
			System.out.println("finally block executed");

		}
		return false;
	}

	@Override
	public boolean updateBook(Book book) throws SQLException {
		DBConnection conn = new DBConnection();
		Connection connection = conn.getConnection();

		try {
			PreparedStatement ps = ((Connection) connection)
					.prepareStatement("UPDATE books SET title=? WHERE id=?");
			ps.setString(1, book.getTitle());
			ps.setInt(2, book.getId());

			int i = ps.executeUpdate();
			if (i == 1) {
				return true;
			}

		} catch (SQLException ex) {
			logger.info("Unsuccessful db connection", ex);
		} finally {
			connection.close();
			System.out.println("finally block executed");

		}
		return false;
	}

	@Override
	public boolean deleteBook(int id) throws SQLException {
		DBConnection conn = new DBConnection();
		Connection connection = conn.getConnection();

		try {
			PreparedStatement stmt = ((Connection) connection)
					.prepareStatement("DELETE FROM books WHERE id=" + id);
			int i = stmt.executeUpdate();
			if (i == 1) {
				return true;
			}

		} catch (SQLException ex) {
			logger.info("Unsuccessful db connection", ex);
		} finally {
			connection.close();
			System.out.println("finally block executed");

		}
		return false;
	}

	public static void main(String[] args) throws SQLException {
		BookDAO daoBook = new BookDAO();
		AuthorDAO daoAuthor = new AuthorDAO();

		// System.out.println(daoBook.getBook(3));
		System.out.println(daoBook.getAllBooks());

		Author a = daoAuthor.getAuthor(6);
		// daoBook.insertBook(a,b);

		// daoBook.deleteBook(11);

		Book b = new Book(10, "Prokleta Avlija");
		// daoBook.updateBook(b);
		// System.out.println(b);

	}

}
