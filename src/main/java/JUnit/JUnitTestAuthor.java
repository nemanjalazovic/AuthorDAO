package JUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pkg.Author;
import DAO.DAO;

public class JUnitTestAuthor {
	DAO DAO;
	Author author;

	@Before
	public void setup() {
		DAO = new DAO();

	}

	@Test
	public void testGetAuthor() {
		author = DAO.getAuthor(4);
		Assert.assertEquals(
				"Successfully fetched a single author from the table author",
				"Emile Zola", author.getName());
	}

	@Test
	public void TestGetAllAuthors() {

		Assert.assertEquals(
				"Successfully fetched a multiple authors from the table author",
				7, DAO.getAllAuthors().size());
	}

	@Test
	public void TestUpdateAuthor() {
		author = DAO.getAuthor(3);
		author.setName("Lav Tolstoj");
		System.out.println(author.getName());
		Assert.assertEquals(
				"Successfully update a single author from the table author",
				true, DAO.updateAuthor(author));
	}

	@Test
	public void TestDeleteAuthor() {
		author = DAO.getAuthor(3);
		System.out.println(author.getName());
		Assert.assertEquals(
				"Successfully deleted a single author from the table author",
				true, DAO.deleteAuthor(author.getId()));
	}

	@Test
	public void TestInsertAuthor() {
		author = new Author();
		author.setId(8);
		author.setName("Jovan Ducic");
		Assert.assertEquals(
				"Successfully insert a single author from the table author",
				true, DAO.insertAuthor(author));
	}

	@Test
	public void TestCRUDAuthor() {
		// inserting the author
		author = new Author();
		author.setId(11);
		author.setName("Fjodor Dostojevski");
		Assert.assertEquals(
				"Successfully insert a single author from the table author",
				true, DAO.insertAuthor(author));

		author = new Author();
		author.setId(10);
		author.setName("Herman Melvil");
		Assert.assertEquals(
				"Successfully insert a single author from the table author",
				true, DAO.insertAuthor(author));

		// fetching and updating the author
		author = DAO.getAuthor(9);
		author.setName("Stiv Dostojevski");
		Assert.assertEquals(
				"Successfully update a single author from the table author",
				true, DAO.updateAuthor(author));

		// delete the author
		author = DAO.getAuthor(11);
		Assert.assertEquals(
				"Successfully deleted a single author from the table author",
				true, DAO.deleteAuthor(author.getId()));

	}

}