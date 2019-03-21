package JUnit;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pkg.Author;
import pkg.Book;
import DAO.BookDAO;

public class JUnitTestBook {
	BookDAO DAO;
	Book book;
	Author author;

	@Before
	public void setup() {
		DAO = new BookDAO();

	}

	@Test
	public void testGetBook() throws SQLException {
		book = DAO.getBook(4);
		Assert.assertEquals(
				"Successfully fetched a single book from the table books",
				"Cousin Bette", book.getTitle());
	}

	@Test
	public void TestGetAllBooks() throws SQLException {

		Assert.assertEquals(
				"Successfully fetched a multiple books from the table books",
				10, DAO.getAllBooks().size());
	}

	@Test
	public void TestUpdateBook() throws SQLException {
		book = DAO.getBook(10);
		book.setTitle("Prokleta avlija");
		System.out.println(book.getTitle());
		Assert.assertEquals(
				"Successfully update a single book from the table books", true,
				DAO.updateBook(book));
	}

	@Test
	public void TestDeleteBook() throws SQLException {
		book = DAO.getBook(22);
		System.out.println(book.getTitle());
		Assert.assertEquals(
				"Successfully deleted a single book from the table books",
				true, DAO.deleteBook(book.getId()));
	}

	@Test
	public void TestInsertBook() throws SQLException {
		book = new Book();
		author = new Author();
		author.setId(19);
		book.setTitle("testInsert");
		Assert.assertEquals(
				"Successfully insert a single book from the table books",
				true, DAO.insertBook(author, book));
	}

}
