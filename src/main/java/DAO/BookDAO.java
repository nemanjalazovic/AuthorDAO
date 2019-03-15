package DAO;

import interfaces.IBookDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pkg.Author;
import pkg.Book;

public class BookDAO implements IBookDAO {
	@Override
	public ArrayList<Book> getAllBooks() {
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
			connection.close();
			return list;

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public Book getBook(int id) {
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
			connection.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean insertBook(Author author, Book book) {
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
			connection.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateBook(Book book) {
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
			connection.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteBook(int id) {
		DBConnection conn = new DBConnection();
		Connection connection = conn.getConnection();

		try {
			PreparedStatement stmt = ((Connection) connection)
					.prepareStatement("DELETE FROM books WHERE id=" + id);
			int i = stmt.executeUpdate();
			if (i == 1) {
				return true;
			}
			connection.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) {
		BookDAO daoBook = new BookDAO();
		AuthorDAO daoAuthor = new AuthorDAO();

		// System.out.println(daoBook.getBook(3));
		// System.out.println(daoBook.getAllBooks());

		Author a = daoAuthor.getAuthor(6);
		// daoBook.insertBook(a,b);

		// daoBook.deleteBook(11);

		Book b = new Book(10, "Prokleta Avlija");
		// daoBook.updateBook(b);
		// System.out.println(b);

	}

}
