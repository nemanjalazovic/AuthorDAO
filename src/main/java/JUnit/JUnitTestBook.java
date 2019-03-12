package JUnit;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pkg.Author;
import pkg.Book;
import DAO.DAO;

public class JUnitTestBook {
	DAO DAO;
	Book book;
	Author author;

	@Before
	public void setup() {
		DAO = new DAO();

	}

	@Test
	public void testGetBook() {
		book = DAO.getBook(4);
		Assert.assertEquals(
				"Successfully fetched a single book from the table books",
				"Cousin Bette", book.getTitle());
	}

	@Test
	public void TestGetAllBooks() {

		Assert.assertEquals(
				"Successfully fetched a multiple books from the table books",
				10, DAO.getAllBooks().size());
	}

	@Test
	public void TestUpdateBook() {
		book = DAO.getBook(10);
		book.setTitle("Prokleta avlija");
		System.out.println(book.getTitle());
		Assert.assertEquals(
				"Successfully update a single book from the table books", true,
				DAO.updateBook(book));
	}

	@Test
	public void TestDeleteBook() {
		book = DAO.getBook(22);
		System.out.println(book.getTitle());
		Assert.assertEquals(
				"Successfully deleted a single book from the table books",
				true, DAO.deleteBook(book.getId()));
	}

	@Test
	public void TestInsertBook() {
		book = new Book();
		author = new Author();
		author.setId(19);
		book.setTitle("testInsert");
		Assert.assertEquals(
				"Successfully insert a single author from the table author",
				true, DAO.insertBook(author, book));
	}

}
