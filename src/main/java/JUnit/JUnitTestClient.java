package JUnit;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pkg.Author;
import pkg.Client;
import DAO.AuthorDAO;
import DAO.ClientDAO;

public class JUnitTestClient {
	ClientDAO DAO;
	Client client;

	@Before
	public void setup() {
		DAO = new ClientDAO();

	}

	@Test
	public void testGetClient() {
		client = DAO.getClient(16);
		Assert.assertEquals(
				"Successfully fetched a single client from the table clients",
				"Helen", client.getName());
	}

	@Test
	public void TestGetAllClients() {

		Assert.assertEquals(
				"Successfully fetched a multiple clients from the table clients",
				14, DAO.getAllClients().size());
	}

	@Test
	public void TestUpdateClient() throws ClassNotFoundException {
		client = DAO.getClient(20);
		client.setName("Stiv");
		System.out.println(client.getName());
		Assert.assertEquals(
				"Successfully update a single client from the table clients",
				true, DAO.updateClient(client));
	}

	@Test
	public void TestDeleteClient() throws ClassNotFoundException {
		client = DAO.getClient(3);
		Assert.assertEquals(
				"Successfully deleted a single client from the table clients",
				true, DAO.deleteClient(7));
	}

	@Test
	public void TestInsertClient() throws ClassNotFoundException {
		client = new Client();
		client.setName("Jovan");
		client.setSurname("Jovanovic");
		client.setUsername("username1");
		client.setPassword("password1");
		Assert.assertEquals(
				"Successfully insert a single client in the table clients",
				true, DAO.insertClient(client));
	}

	@Test
	public void TestCRUDAuthor() throws ClassNotFoundException {
		// inserting the client
		client = new Client();
		client.setName("Jovan");
		client.setSurname("Jovanovic");
		client.setUsername("username1");
		client.setPassword("password1");
		Assert.assertEquals(
				"Successfully insert a single client in the table clients",
				true, DAO.insertClient(client));

		// fetching and updating the client
		client = DAO.getClient(20);
		client.setName("Stiv");
		System.out.println(client.getName());
		Assert.assertEquals(
				"Successfully update a single client from the table clients",
				true, DAO.updateClient(client));

		// delete the author
		client = DAO.getClient(3);
		Assert.assertEquals(
				"Successfully deleted a single client from the table clients",
				true, DAO.deleteClient(22));

	}

}
