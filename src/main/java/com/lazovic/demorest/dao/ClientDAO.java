package com.lazovic.demorest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.lazovic.demorest.interfaces.IClientDAO;
import com.lazovic.demorest.model.Client;
import com.lazovic.demorest.model.LoginUser;
import com.lazovic.demorest.others.EncryptString;

public class ClientDAO implements IClientDAO {

	private final Logger logger = Logger.getLogger(this.getClass());

	@Override
	public Client getClient(int id) throws SQLException {
		Client client = null;
		DBConnection conn = new DBConnection();
		Connection connection = conn.getConnection();
		try {

			PreparedStatement stm = connection
					.prepareStatement("SELECT * FROM clients WHERE id_client= ?");
			stm.setInt(1, id);
			stm.executeQuery();
			ResultSet resultSet = stm.getResultSet();
			while (resultSet.next()) {
				client = new Client();
				client.setId_client(resultSet.getInt(1));
				client.setName(resultSet.getString(2));
				client.setSurname(resultSet.getString(3));
				client.setUsername(resultSet.getString(4));
				client.setPassword(resultSet.getString(5));

			}

		} catch (SQLException ex) {
			logger.error("Unsuccessful db connection", ex);
		} finally {
			connection.close();
			System.out.println("finally block executed");

		}
		return client;
	}

	@Override
	public ArrayList<Client> getAllClients() throws SQLException {
		DBConnection conn = new DBConnection();
		Connection connection = conn.getConnection();
		ArrayList<Client> list = new ArrayList<Client>();

		try {

			PreparedStatement stmt = connection
					.prepareStatement("SELECT * FROM clients");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Client client = new Client();
				client.setId_client(rs.getInt("id_client"));
				client.setName(rs.getString("name"));
				client.setSurname(rs.getString("surname"));
				client.setUsername(rs.getString("username"));
				client.setPassword(rs.getString("password"));

				list.add(client);
			}

		} catch (SQLException ex) {
			logger.error("Unsuccessful db connection", ex);
		} finally {
			connection.close();
			System.out.println("finally block executed");

		}
		return list;

	}

	@Override
	public boolean insertClient(Client client) throws ClassNotFoundException,
			SQLException {
		DBConnection conn = new DBConnection();
		Connection connection = conn.getConnection();
		EncryptString encrypt = new EncryptString();

		try {
			PreparedStatement ps = connection
					.prepareStatement("INSERT INTO clients(name ,surname ,username ,password ) VALUES (?, ?, ?, ?)");
			// ps.setInt(1, client.getId_client());
			ps.setString(1, client.getName());
			ps.setString(2, client.getSurname());
			ps.setString(3, client.getUsername());
			ps.setString(4, encrypt.encryptPassword(client.getPassword()));

			int i = ps.executeUpdate();
			if (i == 1) {
				System.out.println("Successfull insert");

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
	public boolean updateClient(Client client) throws ClassNotFoundException,
			SQLException {
		DBConnection conn = new DBConnection();
		Connection connection = conn.getConnection();
		EncryptString encrypt = new EncryptString();

		try {
			PreparedStatement ps = connection
					.prepareStatement("UPDATE clients SET name=?,surname=?,username=?, password =? WHERE id_client= ?");
			ps.setString(1, client.getName());
			ps.setString(2, client.getSurname());
			ps.setString(3, client.getUsername());
			ps.setString(4, encrypt.encryptPassword(client.getPassword()));
			ps.setInt(5, client.getId_client());
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
	public boolean deleteClient(int id) throws ClassNotFoundException,
			SQLException {
		DBConnection conn = new DBConnection();
		Connection connection = conn.getConnection();

		try {
			PreparedStatement stmt = ((Connection) connection)
					.prepareStatement("DELETE FROM clients WHERE id_client="
							+ id);
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

	public Client Login(LoginUser log) throws ClassNotFoundException,
			SQLException {
		Client client = new Client();

		DBConnection conn = new DBConnection();
		Connection connection = conn.getConnection();

		EncryptString encrypt = new EncryptString();
		String password = encrypt.encryptPassword(log.getPassword());

		try {
			PreparedStatement pst = connection
					.prepareStatement("select * from clients where username=?");
			pst.setString(1, log.getUsername());
			pst.execute();
			ResultSet rs = pst.getResultSet();
			if (rs.next()) {
				client.setId_client(rs.getInt(1));
				client.setName(rs.getString(2));
				client.setSurname(rs.getString(3));
				client.setUsername(rs.getString(4));
				client.setPassword(rs.getString(5));
			}
			String userPassword = client.getPassword();
			if (userPassword.equals(password)) {
				client.setPassword(userPassword);
				System.out.println("Successful login");
				return client;

			} else {
				System.out.println("Wrong username or password!");

			}
		} catch (SQLException ex) {
			logger.error("Unsuccessful db connection", ex);
		} finally {
			connection.close();
			System.out.println("finally block executed");

		}

		return null;
	}

	public static void main(String[] args) throws ClassNotFoundException,
			SQLException {
		ClientDAO dao = new ClientDAO();

		Client c = new Client();
		c.setName("Helen");
		c.setSurname("Cole");
		c.setUsername("myaccount");
		c.setPassword("Hello21");

		// dao.insertClient(c);

		// System.out.println(dao.getClient(20));

		Client c1 = new Client("janko", "Jankovic", "proba", "proba1234");

		Client c2 = new Client(20, "Manko", "Jankovic", "proba", "proba1234");

		// dao.insertClient(c1);

		// System.out.println(dao.getAllClients());

		// dao.updateClient(c2);
		LoginUser user = new LoginUser();
		user.setUsername("proba");
		user.setPassword("proba1234");
		dao.Login(user);

	}

}
