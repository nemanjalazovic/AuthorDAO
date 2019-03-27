package com.lazovic.demorest.junit;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.lazovic.demorest.dao.AuthorDAO;
import com.lazovic.demorest.model.Author;

public class JUnitTestAuthor {
	AuthorDAO DAO;
	Author author;

	@Before
	public void setup() {
		DAO = new AuthorDAO();

	}

	@Test
	public void testGetAuthor() throws SQLException {
		author = DAO.getAuthor(4);
		Assert.assertEquals(
				"Successfully fetched a single author from the table author",
				"Emile Zola", author.getName());
	}

	@Test
	public void TestGetAllAuthors() throws SQLException {

		Assert.assertEquals(
				"Successfully fetched a multiple authors from the table author",
				7, DAO.getAllAuthors().size());
	}

	@Test
	public void TestUpdateAuthor() throws SQLException {
		author = DAO.getAuthor(3);
		author.setName("Lav Tolstoj");
		System.out.println(author.getName());
		Assert.assertEquals(
				"Successfully update a single author from the table author",
				true, DAO.updateAuthor(author));
	}

	@Test
	public void TestDeleteAuthor() throws SQLException {
		author = DAO.getAuthor(3);
		System.out.println(author.getName());
		Assert.assertEquals(
				"Successfully deleted a single author from the table author",
				true, DAO.deleteAuthor(author.getId()));
	}

	@Test
	public void TestInsertAuthor() throws SQLException {
		author = new Author();
		author.setId(8);
		author.setName("Jovan Ducic");
		Assert.assertEquals(
				"Successfully insert a single author from the table author",
				true, DAO.insertAuthor(author));
	}

	@Test
	public void TestCRUDAuthor() throws SQLException {
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