package DAO;

import interfaces.IAuthorDAO;
import interfaces.IBookDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.postgresql.Driver;

import pkg.Author;
import pkg.Book;
import pkg.USER_PASS;

public class DAO implements IAuthorDAO, IBookDAO {

	private Connection connect = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	public static final String URL = "jdbc:postgresql://127.0.0.1:5432/authors_books";

	/**
	 * Get a connection to database
	 * 
	 * @return Connection object
	 */
	public static Connection getConnection() {
		try {

			/*
			 * The firstly you have to make an class in which you save data
			 * about // user_name and password to make a connection to DB
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
			return DriverManager.getConnection(URL, user_pass.getUser(),
					user_pass.getPass());
		} catch (SQLException ex) {
			throw new RuntimeException("Error connecting to the database", ex);
		}
	}

	@Override
	public Author getAuthor(int id) {
		Connection connection = DAO.getConnection();
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
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<Author> getAllAuthors() {
		DAO connector = new DAO();
		Connection connection = connector.getConnection();
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
			return list;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean insertAuthor(Author author) {
		DAO connector = new DAO();
		Connection connection = connector.getConnection();
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
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateAuthor(Author author) {
		DAO connector = new DAO();
		Connection connection = connector.getConnection();
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
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteAuthor(int id) {
		DAO connector = new DAO();
		Connection connection = connector.getConnection();
		try {
			PreparedStatement stmt = connection
					.prepareStatement("DELETE FROM authors WHERE id=" + id);
			int i = stmt.executeUpdate();
			if (i == 1) {
				return true;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public String GetVersion() throws SQLException {
		Connection connection = DAO.getConnection();
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery("SELECT VERSION()");
		try {

			if (rs.next()) {
				System.out.println(rs.getString(1));
			}

		} catch (SQLException ex) {

			Logger lgr = Logger.getLogger(DAO.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
			System.out.println("error");
		}
		return null;
	}

	@Override
	public void writeMultipleRows(String query) throws SQLException {
		DAO connector = new DAO();
		Connection connection = connector.getConnection();
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

				Logger lgr = Logger.getLogger(DAO.class.getName());
				lgr.log(Level.SEVERE, ex.getMessage(), ex);
			}
		} while (isResult);

	}

	@Override
	public void writeMetaData(String query) throws SQLException {
		DAO connector = new DAO();
		Connection connection = connector.getConnection();
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

			Logger lgr = Logger.getLogger(DAO.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}

	}

	@Override
	public ArrayList<Book> getAllBooks() {
		DAO connector = new DAO();
		Connection connection = connector.getConnection();
		ArrayList<Book> list = new ArrayList<Book>();

		try {

			PreparedStatement stmt = connection
					.prepareStatement("select b.id,a.name,b.title from authors a join books b on a.id=b.author_id ");

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Book book = new Book();
				book.setId(rs.getInt("id"));
				book.setAuthor(rs.getString("name"));
				book.setTitle(rs.getString("title"));

				list.add(book);
			}
			return list;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public Book getBook(int id) {
		Connection connection = DAO.getConnection();
		try {
			PreparedStatement stmt = connection
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
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean insertBook(Book book, Author author) {
		DAO connector = new DAO();
		Connection connection = connector.getConnection();
		try {
			PreparedStatement ps = connection
					.prepareStatement("INSERT INTO books VALUES (?, ?)");
			// ps.setInt(1, book.getId());
			ps.setInt(2, author.getId());
			ps.setString(3, book.getTitle());

			int i = ps.executeUpdate();
			if (i == 1) {
				return true;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateBook(Book book, Author author) {
		DAO connector = new DAO();
		Connection connection = connector.getConnection();
		try {
			PreparedStatement ps = connection
					.prepareStatement("UPDATE books SET title=? WHERE id=?");
			ps.setString(1, book.getTitle());
			ps.setInt(2, book.getId());

			int i = ps.executeUpdate();
			if (i == 1) {
				return true;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteBook(int id) {
		DAO connector = new DAO();
		Connection connection = connector.getConnection();
		try {
			PreparedStatement stmt = connection
					.prepareStatement("DELETE FROM books WHERE id=" + id);
			int i = stmt.executeUpdate();
			if (i == 1) {
				return true;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) throws SQLException {
		DAO dao = new DAO();

		// System.out.println(dao.getAuthor(18));

		// System.out.println(dao.getAllAuthors());

		Author a1 = new Author(8, "Mark Tven");
		// dao.insertAuthor(a1);

		Author a2 = new Author(8, "Lav Tolstoj");
		// dao.updateAuthor(a2);

		// dao.deleteAuthor(8);

		// dao.GetVersion();

		String query1 = "SELECT id, name FROM authors where id=1;"
				+ "SELECT id, name FROM authors Where id=2;"
				+ "SELECT id, name FROM authors WHERE id=3";

		// dao.writeMultipleRows(query1);

		String query2 = "SELECT name, title FROM authors, books WHERE authors.id= books.id";
		// dao.writeMetaData(query2);

		// System.out.p
		// System.out.println(dao.getAllAuthors());rintln(dao.getAllAuthors());

		// System.out.println(dao.getBook(3));
		// System.out.println(dao.getAllBooks());

	}

}